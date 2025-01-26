package com.ctlfab.estatehandle.mapper;

import com.ctlfab.estatehandle.dto.EstateDTO;
import com.ctlfab.estatehandle.enumeration.EnergyClass;
import com.ctlfab.estatehandle.model.Estate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(
        uses = {LocationMapper.class, AddonsMapper.class, FileMapper.class},
        componentModel = "spring"
)
public interface EstateMapper {
    @Mapping(source = "energyClass", target = "energyClass", qualifiedByName = "mapEnergyClassToString")
    EstateDTO toDto(Estate estate);

    @Mapping(source = "energyClass", target = "energyClass", qualifiedByName = "mapStringToEnergyClass")
    Estate toEntity(EstateDTO estateDTO);


    @Named("mapEnergyClassToString")
    default String mapEnergyClassToString(EnergyClass energyClass) {
        return energyClass != null ? energyClass.name() : null;
    }

    @Named("mapStringToEnergyClass")
    default EnergyClass mapStringToEnergyClass(String energyClass) {
        return energyClass != null ? EnergyClass.valueOf(energyClass) : null;
    }
}
