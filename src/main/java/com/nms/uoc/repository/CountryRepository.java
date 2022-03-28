package com.nms.uoc.repository;

import com.nms.uoc.model.entity.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    @Query(value = "SELECT t.* FROM country t WHERE name LIKE :key OR name_of_tournaments LIKE :key", nativeQuery = true)
    Page<Country> search(@Param("key") String key, Pageable pageable);
}
