package com.Caliente.api.service.Impl;

import com.Caliente.api.dto.request.SongRequest;
import com.Caliente.api.dto.response.SongResponse;
import com.Caliente.api.entity.Song;
import com.Caliente.api.mapper.SongMapper;
import com.Caliente.api.repository.PlaylistRepository;
import com.Caliente.api.repository.SongRepository;
import com.Caliente.api.service.SongService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SongServiceImpl implements SongService {
    private final SongRepository songRepo;
    private final PlaylistRepository playlistRepo;
    private final SongMapper mapper;

    @Override
    public List<SongResponse> getAllSongs() {
        var songs = songRepo.findAll();
        return mapper.toResponseList(songs);
    }

    @Override
    public SongResponse getSongById(Long id) {
        Song song = songRepo.findById(id).orElseThrow();
        return mapper.toResponse(song);
    }

    @Override
    public SongResponse createSong(SongRequest req) {
        Song song = mapper.toEntity(req);
        if (req.getPlaylistId() != null) {
            song.setPlaylist(playlistRepo.getReferenceById(req.getPlaylistId()));
        }

        Song createdSong = songRepo.save(song);
        return mapper.toResponse(createdSong);
    }

    @Override
    public SongResponse updateSong(Long id, SongRequest req) {
        Song song = songRepo.findById(id).orElseThrow();
        mapper.updateEntityFromRequest(req, song);
        if(req.getPlaylistId() != null) {
            song.setPlaylist(playlistRepo.getReferenceById(req.getPlaylistId()));
        }

        Song saved = songRepo.save(song);

        return mapper.toResponse(saved);
    }

    @Override
    public void deleteSong(Long id) {
        songRepo.deleteById(id);
    }
}
