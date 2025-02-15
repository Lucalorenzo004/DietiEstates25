package com.ctlfab.estatehandle.mappers;

import com.ctlfab.estatehandle.dto.CategoryDTO;
import com.ctlfab.estatehandle.entities.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        uses = EstateMapper.class,
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface CategoryMapper {
    Category toEntity(CategoryDTO categoryDTO);

    CategoryDTO toDTO(Category category);
}
