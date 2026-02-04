package com.Caliente.api.service.Impl;

import com.Caliente.api.dto.request.PlaylistRequest;
import com.Caliente.api.dto.response.PlaylistResponse;
import com.Caliente.api.entity.Playlist;
import com.Caliente.api.mapper.PlaylistMapper;
import com.Caliente.api.repository.PlaylistRepository;
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
class PlaylistServiceImplTest {

    @Mock
    private PlaylistRepository playlistRepository;

    @Mock
    private PlaylistMapper playlistMapper;

    @InjectMocks
    private PlaylistServiceImpl service;

    @Test
    void getAllPlaylists_returnsMappedResponses() {
        Playlist p = Playlist.builder().id(1L).name("My Playlist").build();
        List<Playlist> entities = List.of(p);
        PlaylistResponse resp = mock(PlaylistResponse.class);

        when(playlistRepository.findAll()).thenReturn(entities);
        when(playlistMapper.toResponseList(entities)).thenReturn(List.of(resp));

        List<PlaylistResponse> result = service.getAllPlaylists();

        assertThat(result).containsExactly(resp);
        verify(playlistRepository).findAll();
        verify(playlistMapper).toResponseList(entities);
    }

    @Test
    void getAllPlaylists_empty_returnsEmpty() {
        when(playlistRepository.findAll()).thenReturn(Collections.emptyList());
        when(playlistMapper.toResponseList(Collections.emptyList())).thenReturn(Collections.emptyList());

        List<PlaylistResponse> result = service.getAllPlaylists();

        assertThat(result).isEmpty();
        verify(playlistRepository).findAll();
    }

    @Test
    void getPlaylistById_found_returnsResponse() {
        Playlist p = Playlist.builder().id(2L).name("P").build();
        PlaylistResponse resp = mock(PlaylistResponse.class);

        when(playlistRepository.findById(2L)).thenReturn(Optional.of(p));
        when(playlistMapper.toResponse(p)).thenReturn(resp);

        PlaylistResponse result = service.getPlaylistById(2L);

        assertThat(result).isSameAs(resp);
        verify(playlistRepository).findById(2L);
    }

    @Test
    void getPlaylistById_notFound_throws() {
        when(playlistRepository.findById(5L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> service.getPlaylistById(5L));
        verify(playlistRepository).findById(5L);
    }

    @Test
    void createPlaylist_savesAndReturnsResponse() {
        PlaylistRequest req = mock(PlaylistRequest.class);
        Playlist entity = Playlist.builder().name("New").build();
        Playlist saved = Playlist.builder().id(10L).name("New").build();
        PlaylistResponse resp = mock(PlaylistResponse.class);

        when(playlistMapper.toEntity(req)).thenReturn(entity);
        when(playlistRepository.save(entity)).thenReturn(saved);
        when(playlistMapper.toResponse(saved)).thenReturn(resp);

        PlaylistResponse result = service.createPlaylist(req);

        assertThat(result).isSameAs(resp);
        verify(playlistMapper).toEntity(req);
        verify(playlistRepository).save(entity);
    }

    @Test
    void deletePlaylist_callsRepository() {
        doNothing().when(playlistRepository).deleteById(7L);

        service.deletePlaylist(7L);

        verify(playlistRepository).deleteById(7L);
    }
}
