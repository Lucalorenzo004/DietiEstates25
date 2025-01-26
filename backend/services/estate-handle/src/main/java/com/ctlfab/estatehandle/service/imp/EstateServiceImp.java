package com.ctlfab.estatehandle.service.imp;

import com.ctlfab.estatehandle.dto.EstateDTO;
import com.ctlfab.estatehandle.mapper.EstateMapper;
import com.ctlfab.estatehandle.model.Estate;
import com.ctlfab.estatehandle.repository.EstateRepository;
import com.ctlfab.estatehandle.service.EstateService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
@Transactional
public class EstateServiceImp implements EstateService {
    private final EstateRepository repository;
    private final EstateMapper mapper;

    @Override
    public List<EstateDTO> getAllEstates() {
        log.info("Fetching all estates");

        List<EstateDTO> estatesDTO = new ArrayList<>();
        for(Estate estate : repository.findAll()){
            estatesDTO.add(mapper.toDto(estate));
        }

        log.info("Estates fetched successfully");
        return estatesDTO;
    }

    @Override
    public EstateDTO saveEstate(EstateDTO estateDTO) {
        log.info("Saving estate {}", estateDTO);

        Estate estate = mapper.toEntity(estateDTO);
        estate = repository.save(estate);
        estateDTO = mapper.toDto(estate);

        log.info("Estate {} saved successfully", estateDTO);
        return estateDTO;
    }

    @Override
    public EstateDTO editEstate(EstateDTO estateDTO) {
        log.info("Updating estate {}", estateDTO);

        Estate estate = mapper.toEntity(estateDTO);
        estate = repository.save(estate);
        estateDTO = mapper.toDto(estate);

        log.info("Estate {} updated successfully", estateDTO);
        return estateDTO;
    }

    @Override
    public boolean deleteEstate(long estateId) {
        log.info("Deleting estate {}", estateId);

        repository.deleteById(estateId);

        log.info("Estate {} deleted successfully", estateId);
        return true;
    }
}
