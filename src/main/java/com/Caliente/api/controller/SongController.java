package com.Caliente.api.controller;

import com.Caliente.api.dto.request.SongRequest;
import com.Caliente.api.dto.response.SongResponse;
import com.Caliente.api.mapper.SongMapper;
import com.Caliente.api.service.SongService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/songs")
@RequiredArgsConstructor
public class SongController {
    private final SongService songService;

    @GetMapping
    public List<SongResponse> getAll() { return songService.getAllSongs(); }

    @GetMapping("/{id}")
    public SongResponse getOne(@PathVariable Long id) { return songService.getSongById(id); }

    @PostMapping
    public SongResponse create(@RequestBody SongRequest request) { return songService.createSong(request); }

    @PutMapping("/{id}")
    public SongResponse update(@PathVariable Long id, @RequestBody SongRequest request) {
        return songService.updateSong(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { songService.deleteSong(id); }
}
