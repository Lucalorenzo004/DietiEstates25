package com.ctlfab.estatesearch.specification;

import com.ctlfab.estatesearch.dto.AddonDTO;
import com.ctlfab.estatesearch.dto.FilterDTO;
import com.ctlfab.estatesearch.entities.Addon;
import com.ctlfab.estatesearch.entities.Estate;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.LinkedList;
import java.util.List;

public class EstateSpecification{

    public static Specification<Estate> getSpecification(FilterDTO filterDTO) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new LinkedList<>();

            if (filterDTO.getCategory() != null) {
                predicates.add(cb.equal(root.get("category"), filterDTO.getCategory()));
            }
            if (filterDTO.getRental() != null) {
                predicates.add(cb.equal(root.get("rental"), filterDTO.getRental()));
            }
            if (filterDTO.getMinPrice() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("price"), filterDTO.getMinPrice()));
            }
            if (filterDTO.getMaxPrice() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("price"), filterDTO.getMaxPrice()));
            }
            if (filterDTO.getMinMtq() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("mtq"), filterDTO.getMinMtq()));
            }
            if (filterDTO.getMaxMtq() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("mtq"), filterDTO.getMaxMtq()));
            }
            if (filterDTO.getMinRooms() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("rooms"), filterDTO.getMinRooms()));
            }
            if (filterDTO.getMaxRooms() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("rooms"), filterDTO.getMaxRooms()));
            }
            if (filterDTO.getMinServices() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("services"), filterDTO.getMinServices()));
            }
            if (filterDTO.getMaxServices() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("services"), filterDTO.getMaxServices()));
            }
            if (filterDTO.getUserId() != null) {
                predicates.add(cb.equal(root.get("user_id"), filterDTO.getUserId()));
            }
            if (filterDTO.getAddons() != null && !filterDTO.getAddons().isEmpty()) {
                Join<Estate, Addon> addonJoin = root.join("addons");
                List<Predicate> addonPredicates = new LinkedList<>();

                for (AddonDTO addon : filterDTO.getAddons()) {
                    addonPredicates.add(cb.equal(addonJoin.get("name"), addon.getName()));
                }

                predicates.add(cb.or(addonPredicates.toArray(new Predicate[0])));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

}


