package com.ctlfab.estatesearch.repositories;

import com.ctlfab.estatesearch.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    /**
     * Check if the location already exists.
     * @param street Street of address.
     * @param city City of address.
     * @param postalCode Postal code of city.
     * @param county County of city.
     * @param countyCode County code of county.
     * @return {@link Location} if location's data exists, null otherwise.
     */
    @Query("SELECT l FROM Location l " +
            "WHERE l.street = :street AND l.city = :city AND l.postalCode = :postalCode " +
            "AND l.county = :county AND l.countyCode = :countyCode")
    Optional<Location> findByFullAddress(String street, String city, String postalCode, String county, String countyCode);
}
