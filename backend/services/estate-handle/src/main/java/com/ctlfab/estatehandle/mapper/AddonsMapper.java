package com.ctlfab.estatehandle.mapper;

import com.ctlfab.estatehandle.dto.AddonsDTO;
import com.ctlfab.estatehandle.dto.EstateDTO;
import com.ctlfab.estatehandle.model.Addons;
import com.ctlfab.estatehandle.model.Estate;
import com.ctlfab.estatehandle.repository.AddonsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AddonsMapper {
    private final AddonsRepository repository;
    private final EstateMapper estateMapper;

    public AddonsDTO mapToDTO(Addons addons) {
        return AddonsDTO.builder()
                .id(addons.getId())
                .name(addons.getName())
                .build();
    }

    public Addons mapToEntity(AddonsDTO addonsDTO) {
        if(addonsDTO.getId() != null){
            Optional<Addons> addonsOptional = repository.findById(addonsDTO.getId());
            if(addonsOptional.isPresent()){
                return addonsOptional.get();
            }
        }

        return Addons.builder()
                .name(addonsDTO.getName())
                .build();
    }
}
