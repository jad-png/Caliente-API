package com.Caliente.api.service;

import com.Caliente.api.dto.request.SongRequest;
import com.Caliente.api.dto.response.SongResponse;

import java.util.List;
import java.util.Optional;

public interface SongService {
    List<SongResponse> getAllSongs();
    Optional<SongResponse> getSongById(Long id);
    SongResponse createSong(SongRequest req);
    SongResponse updateSong(SongRequest req);
    void deleteSong(Long id);
}
