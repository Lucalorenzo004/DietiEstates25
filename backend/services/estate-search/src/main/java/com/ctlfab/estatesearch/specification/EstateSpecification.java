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

    private EstateSpecification() {}

    /**
     * Builds a Specification for Estate based on the given filter criteria.
     * @param filterDTO the filter criteria containing different constraints
     * @return a Specification<Estate> {@link Specification} to be used in repository queries
     */
    public static Specification<Estate> getSpecification(FilterDTO filterDTO) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new LinkedList<>();

            addEqualPredicate(cb, predicates, root.get("category"), filterDTO.getCategory());
            addEqualPredicate(cb, predicates, root.get("rental"), filterDTO.getRental());
            addEqualPredicate(cb, predicates, root.get("userId"), filterDTO.getUserId());

            addRangePredicate(cb, predicates, root.get("price"), filterDTO.getMinPrice(), filterDTO.getMaxPrice());
            addRangePredicate(cb, predicates, root.get("mtq"), filterDTO.getMinMtq(), filterDTO.getMaxMtq());
            addRangePredicate(cb, predicates, root.get("rooms"), filterDTO.getMinRooms(), filterDTO.getMaxRooms());
            addRangePredicate(cb, predicates, root.get("services"), filterDTO.getMinServices(), filterDTO.getMaxServices());

            addAddonPredicates(cb, predicates, root, filterDTO.getAddons());

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    /**
     * Adds an equality condition to the predicates list if the given value is not null.
     * @param cb the CriteriaBuilder used to build the predicate
     * @param predicates the list of predicates to which the condition is added
     * @param path the entity attribute to compare
     * @param value the value to match
     * @param <T> the type of the attribute
     */
    private static <T> void addEqualPredicate(CriteriaBuilder cb, List<Predicate> predicates, Path<T> path, T value) {
        if (value != null) {
            predicates.add(cb.equal(path, value));
        }
    }

    /**
     * Adds range-based conditions (greater than or equal to, less than or equal to) to the predicates list.
     * @param cb the CriteriaBuilder used to build the predicate
     * @param predicates the list of predicates to which the conditions are added
     * @param path the entity attribute to compare
     * @param min the minimum value (greater than or equal to)
     * @param max the maximum value (less than or equal to)
     * @param <T> the type of the attribute (must be Comparable)
     */

    private static <T extends Comparable<T>> void addRangePredicate(CriteriaBuilder cb, List<Predicate> predicates, Path<T> path, T min, T max) {
        if (min != null) {
            predicates.add(cb.greaterThanOrEqualTo(path, min));
        }
        if (max != null) {
            predicates.add(cb.lessThanOrEqualTo(path, max));
        }
    }
    /**
     * Adds predicates for filtering estates based on associated addons.
     * @param cb the CriteriaBuilder used to build the predicate
     * @param predicates the list of predicates to which the condition is added
     * @param root the root entity (Estate)
     * @param addons the list of AddonDTOs representing the filter criteria
     */
    private static void addAddonPredicates(CriteriaBuilder cb, List<Predicate> predicates, Root<Estate> root, List<AddonDTO> addons) {
        if (addons != null && !addons.isEmpty()) {
            Join<Estate, Addon> addonJoin = root.join("addons");
            List<Predicate> addonPredicates = addons.stream()
                    .map(addon -> cb.equal(addonJoin.get("name"), addon.getName()))
                    .toList();

            predicates.add(cb.or(addonPredicates.toArray(new Predicate[0])));
        }
    }

}


