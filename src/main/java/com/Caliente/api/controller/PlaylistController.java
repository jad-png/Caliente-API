package com.Caliente.api.controller;

import com.Caliente.api.dto.request.PlaylistRequest;
import com.Caliente.api.dto.response.PlaylistResponse;
import com.Caliente.api.service.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/playlists")
@RequiredArgsConstructor
public class PlaylistController {
    private final PlaylistService playlistService;

    @GetMapping
    public List<PlaylistResponse> getAll() { return playlistService.getAllPlaylists(); }

    @GetMapping("/{id}")
    public PlaylistResponse getOne(@PathVariable Long id) { return playlistService.getPlaylistById(id); }

    @PostMapping
    public PlaylistResponse create(@RequestBody PlaylistRequest request) { return playlistService.createPlaylist(request); }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { playlistService.deletePlaylist(id); }
}
