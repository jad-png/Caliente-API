package com.Caliente.api.service.Impl;

import com.Caliente.api.dto.request.PlaylistRequest;
import com.Caliente.api.dto.response.PlaylistResponse;
import com.Caliente.api.entity.Playlist;
import com.Caliente.api.mapper.PlaylistMapper;
import com.Caliente.api.repository.PlaylistRepository;
import com.Caliente.api.service.PlaylistService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class PlaylistServiceImpl implements PlaylistService {
    private final PlaylistRepository playlistRepository;
    private final PlaylistMapper playlistMapper;

    public List<PlaylistResponse> getAllPlaylists() {
        List<Playlist> playlists = playlistRepository.findAll();
        return playlistMapper.toResponseList(playlists);
    }

    public PlaylistResponse getPlaylistById(Long id) {
        Playlist playlist = playlistRepository.findById(id).orElseThrow();
        return playlistMapper.toResponse(playlist);
    }

    public PlaylistResponse createPlaylist(PlaylistRequest req) {
        Playlist playlist = playlistMapper.toEntity(req);
        return playlistMapper.toResponse(playlistRepository.save(playlist));
    }

    public void deletePlaylist(Long id) {
        playlistRepository.deleteById(id);
    }
}
