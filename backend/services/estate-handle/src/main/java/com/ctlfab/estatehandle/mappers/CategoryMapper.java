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

    /**
     * Map {@link CategoryDTO} to {@link Category}
     * @param categoryDTO {@link CategoryDTO} to map
     * @return {@link Category} object
     */
    Category toEntity(CategoryDTO categoryDTO);

    /**
     * Map {@link Category} to {@link CategoryDTO}
     * @param category {@link Category} to map
     * @return {@link CategoryDTO} object
     */
    CategoryDTO toDTO(Category category);
}
