package com.Caliente.api.service.Impl;

import com.Caliente.api.dto.request.SongRequest;
import com.Caliente.api.dto.response.SongResponse;
import com.Caliente.api.entity.Playlist;
import com.Caliente.api.entity.Song;
import com.Caliente.api.mapper.SongMapper;
import com.Caliente.api.repository.PlaylistRepository;
import com.Caliente.api.repository.SongRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SongServiceImplTest {

    @Mock
    private SongRepository songRepo;

    @Mock
    private PlaylistRepository playlistRepo;

    @Mock
    private SongMapper mapper;

    @InjectMocks
    private SongServiceImpl service;

    @Test
    void getAllSongs_returnsMapped() {
        Song s = new Song();
        List<Song> entities = List.of(s);
        SongResponse resp = mock(SongResponse.class);

        when(songRepo.findAll()).thenReturn(entities);
        when(mapper.toResponseList(entities)).thenReturn(List.of(resp));

        List<SongResponse> result = service.getAllSongs();

        assertThat(result).containsExactly(resp);
        verify(songRepo).findAll();
    }

    @Test
    void getAllSongs_empty_returnsEmpty() {
        when(songRepo.findAll()).thenReturn(Collections.emptyList());
        when(mapper.toResponseList(Collections.emptyList())).thenReturn(Collections.emptyList());

        List<SongResponse> result = service.getAllSongs();

        assertThat(result).isEmpty();
    }

    @Test
    void getSongById_found_returnsResponse() {
        Song song = new Song();
        SongResponse resp = mock(SongResponse.class);

        when(songRepo.findById(1L)).thenReturn(Optional.of(song));
        when(mapper.toResponse(song)).thenReturn(resp);

        SongResponse result = service.getSongById(1L);

        assertThat(result).isSameAs(resp);
    }

    @Test
    void getSongById_notFound_throws() {
        when(songRepo.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> service.getSongById(99L));
    }

    @Test
    void createSong_withoutPlaylist_setsNoPlaylist() {
        SongRequest req = new SongRequest();
        req.setTitle("t");
        req.setArtist("a");
        req.setPlaylistId(null);

        Song entity = new Song();
        Song saved = new Song();
        SongResponse resp = mock(SongResponse.class);

        when(mapper.toEntity(req)).thenReturn(entity);
        when(songRepo.save(entity)).thenReturn(saved);
        when(mapper.toResponse(saved)).thenReturn(resp);

        SongResponse result = service.createSong(req);

        assertThat(result).isSameAs(resp);
        verify(playlistRepo, never()).getReferenceById(anyLong());
        verify(songRepo).save(entity);
    }

    @Test
    void createSong_withPlaylist_setsPlaylistReference() {
        SongRequest req = new SongRequest();
        req.setTitle("t");
        req.setArtist("a");
        req.setPlaylistId(5L);

        Song entity = new Song();
        Playlist pl = Playlist.builder().id(5L).name("pl").build();
        Song saved = new Song();
        SongResponse resp = mock(SongResponse.class);

        when(mapper.toEntity(req)).thenReturn(entity);
        when(playlistRepo.getReferenceById(5L)).thenReturn(pl);
        when(songRepo.save(entity)).thenReturn(saved);
        when(mapper.toResponse(saved)).thenReturn(resp);

        SongResponse result = service.createSong(req);

        assertThat(result).isSameAs(resp);
        verify(playlistRepo).getReferenceById(5L);
        verify(songRepo).save(entity);
    }

    @Test
    void updateSong_found_updatesAndReturns() {
        SongRequest req = new SongRequest();
        req.setTitle("new");
        req.setArtist("art");

        Song existing = new Song();
        Song saved = new Song();
        SongResponse resp = mock(SongResponse.class);

        when(songRepo.findById(2L)).thenReturn(Optional.of(existing));
        doNothing().when(mapper).updateEntityFromRequest(req, existing);
        when(songRepo.save(existing)).thenReturn(saved);
        when(mapper.toResponse(saved)).thenReturn(resp);

        SongResponse result = service.updateSong(2L, req);

        assertThat(result).isSameAs(resp);
        verify(songRepo).findById(2L);
        verify(songRepo).save(existing);
    }

    @Test
    void updateSong_notFound_throws() {
        SongRequest req = new SongRequest();
        when(songRepo.findById(42L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> service.updateSong(42L, req));
    }

    @Test
    void deleteSong_callsRepository() {
        doNothing().when(songRepo).deleteById(3L);

        service.deleteSong(3L);

        verify(songRepo).deleteById(3L);
    }
}
