package com.ctlfab.estatehandle.service.impl;

import com.ctlfab.estatehandle.dto.AddonsDTO;
import com.ctlfab.estatehandle.model.Addons;
import com.ctlfab.estatehandle.repository.AddonsRepository;
import com.ctlfab.estatehandle.service.AddonsService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
@Transactional
public class AddonsServiceImp implements AddonsService {
    private final AddonsRepository addonsRepository;

    @Override
    public AddonsDTO saveAddons(AddonsDTO addonsDTO) {
        log.info("Saving addons {}", addonsDTO);

        Addons addons = mapToEntity(addonsDTO);
        addons = addonsRepository.save(addons);

        log.info("Addons {} saved successfully", addons);
        return mapToDTO(addons);
    }

    @Override
    public AddonsDTO editAddons(AddonsDTO addonsDTO) {
        log.info("Updating addons {}", addonsDTO);

        Addons addons = mapToEntity(addonsDTO);
        addons = addonsRepository.save(addons);

        log.info("Addons {} updated successfully", addons);
        return mapToDTO(addons);
    }

    @Override
    public boolean deleteAddons(long addonsId) {
        log.info("Deleting addons {}", addonsId);

        addonsRepository.deleteById(addonsId);

        log.info("Addons {} deleted successfully", addonsId);
        return true;
    }
}
