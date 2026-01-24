package com.Caliente.api.mapper;

import com.Caliente.api.dto.request.PlaylistRequest;
import com.Caliente.api.dto.response.PlaylistResponse;
import com.Caliente.api.entity.Playlist;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {SongMapper.class})
public interface PlaylistMapper {
    PlaylistResponse toResponse(Playlist playlist);
    Playlist toEntity(PlaylistRequest request);
}
