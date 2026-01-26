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
    public Optional<SongResponse> getSongById(Long id) {
        Song song = songRepo.findById(id).orElseThrow();
        return Optional.ofNullable(mapper.toResponse(song));
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
    public SongResponse updateSong(SongRequest req) {
        return null;
    }

    @Override
    public void deleteSong(Long id) {

    }
}
