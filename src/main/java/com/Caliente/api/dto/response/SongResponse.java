package com.Caliente.api.dto.response;

import lombok.Data;

@Data
public class SongResponse {
    private Long id;
    private String title;
    private String artist;
    private String album;
    private String duration;
    private String coverPath;
    private Long playlistId;
}
