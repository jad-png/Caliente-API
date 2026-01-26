package com.Caliente.api.service;

import com.Caliente.api.dto.request.PlaylistRequest;
import com.Caliente.api.dto.response.PlaylistResponse;

import java.util.List;

public interface PlaylistService {
    List<PlaylistResponse> getAllPlaylists();
    PlaylistResponse getPlaylistById(Long id);
    PlaylistResponse createPlaylist(PlaylistRequest req);
    void deletePlaylist(Long id);
}
