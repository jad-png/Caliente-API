package com.Caliente.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PlaylistRequest {
    @NotBlank(message = "Le nom de la playlist est obligatoire")
    @Size(min = 3, max = 50)
    private String name;

    private String description;
}
