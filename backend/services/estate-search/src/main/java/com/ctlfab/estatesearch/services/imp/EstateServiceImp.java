package com.ctlfab.estatesearch.services.imp;

import com.ctlfab.estatesearch.dto.EstateDTO;
import com.ctlfab.estatesearch.dto.FilterDTO;

import com.ctlfab.estatesearch.entities.Estate;

import com.ctlfab.estatesearch.mappers.EstateMapper;
import com.ctlfab.estatesearch.repositories.EstateRepository;

import com.ctlfab.estatesearch.services.EstateService;

import com.ctlfab.estatesearch.specification.EstateSpecification;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
@Transactional(rollbackOn = {SQLException.class})
public class EstateServiceImp implements EstateService {
    private final EstateRepository repository;
    private final EstateMapper mapper;

    /**
     * Fetch all the estate that match the filter passed as the argument.
     * @param filterDTO Filter criteria {@link FilterDTO}.
     * @return List of {@link EstateDTO}.
     */
    @Override
    public List<EstateDTO> getAll(FilterDTO filterDTO) {
        log.info("Fetching all estates");

        Specification<Estate> specification = EstateSpecification.getSpecification(filterDTO);

        List<EstateDTO> estatesDTO = new ArrayList<>();
        for(Estate estate : repository.findAll(specification)){
            estatesDTO.add(mapper.toDTO(estate));
        }

        log.info("Estates fetched successfully");
        return estatesDTO;
    }

    /**
     * Fetch estate by its ID.
     * @param id ID of estate.
     * @return {@link EstateDTO} object.
     */
    @Cacheable(cacheNames = "estate-cache", key = "#id")
    @Override
    public EstateDTO getById(long id) {
        log.info("Fetching estate with id {}", id);

        Estate estate = repository.findById(id).orElse(null);
        EstateDTO estateDTO = mapper.toDTO(estate);

        log.info("EstateDTO fetched successfully");
        return estateDTO;
    }

    /**
     * Fetch favorite user estate.
     *
     * @param userId ID of user.
     * @return {@link EstateDTO} object.
     */
    @Override
    public List<EstateDTO> getFavorite(long userId) {
        log.info("Fetching all favorite estates");

        List<EstateDTO> estatesDTO = new ArrayList<>();
        for(Estate estate : repository.getFavoriteEstate(userId)){
            estatesDTO.add(mapper.toDTO(estate));
        }

        log.info("Favorite estates fetched successfully");
        return estatesDTO;
    }
}
