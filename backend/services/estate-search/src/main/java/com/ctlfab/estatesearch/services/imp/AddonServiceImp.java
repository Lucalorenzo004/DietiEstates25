package com.ctlfab.estatesearch.services.imp;

import com.ctlfab.estatesearch.dto.AddonDTO;
import com.ctlfab.estatesearch.entities.Addon;
import com.ctlfab.estatesearch.mappers.AddonMapper;
import com.ctlfab.estatesearch.repositories.AddonRepository;
import com.ctlfab.estatesearch.services.AddonService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
@Transactional
public class AddonServiceImp implements AddonService {
    private final AddonRepository repository;
    private final AddonMapper mapper;

    @Override
    public List<AddonDTO> getAll() {
        log.info("Fetching all Addons");

        List<Addon> addons = repository.findAll();
        List<AddonDTO> addonsDTO = new LinkedList<>();
        for (Addon addon : addons) {
            addonsDTO.add(mapper.toDTO(addon));
        }

        log.info("Addons fetched successfully");
        return addonsDTO;
    }
}
