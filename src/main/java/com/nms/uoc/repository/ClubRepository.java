package com.nms.uoc.repository;

import com.nms.uoc.model.entity.Club;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {
        @Query(value = "SELECT t.* " +
            "FROM db.club t " +
            "WHERE description LIKE '%%' " +
            "OR logo LIKE '%:key%' " +
            "OR name LIKE '%:key%' " +
            "OR stadium LIKE '%:key%'", nativeQuery = true)
    Page<Club> search(@Param("key")String key, Pageable pageable);
}
