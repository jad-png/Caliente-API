package com.Caliente.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SongRequest {
    @NotBlank(message = "Le titre est obligatoire")
    private String title;

    @NotBlank(message = "L'artiste est obligatoire")
    private String artist;

    private String album;
    private String duration;
    private String filePath;
    private Long playlistId;
}
