package com.Caliente.api.service.Impl;

import com.Caliente.api.dto.request.PlaylistRequest;
import com.Caliente.api.dto.response.PlaylistResponse;
import com.Caliente.api.service.PlaylistService;

import java.util.List;

public class PlaylistServiceImpl implements PlaylistService {
    @Override
    public List<PlaylistResponse> getAllPlaylists() {
        return List.of();
    }

    @Override
    public PlaylistResponse getPlaylistById(Long id) {
        return null;
    }

    @Override
    public PlaylistResponse createPlaylist(PlaylistRequest req) {
        return null;
    }

    @Override
    public PlaylistResponse updatePlaylist(PlaylistRequest req) {
        return null;
    }

    @Override
    public void deletePlaylist(Long id) {

    }
}
