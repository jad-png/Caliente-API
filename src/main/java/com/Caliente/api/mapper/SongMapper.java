package com.Caliente.api.mapper;

import com.Caliente.api.dto.request.SongRequest;
import com.Caliente.api.dto.response.SongResponse;
import com.Caliente.api.entity.Song;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SongMapper {
    @Mapping(source = "playlist.id", target = "playlistId")
    SongResponse toResponse(Song song);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "playlist", ignore = true)
    Song toEntity(SongRequest request);

    List<SongResponse> toResponseList(List<Song> songs);

    void updateEntityFromRequest(SongRequest request, @MappingTarget Song song);
}
