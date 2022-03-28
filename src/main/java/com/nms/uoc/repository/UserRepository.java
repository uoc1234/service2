package com.nms.uoc.repository;

import com.nms.uoc.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);

    @Modifying
    @Query(value = "SELECT permission_id FROM roles_permission WHERE role_id = :role_id", nativeQuery = true)
    List<String> findAllPermissionByRole(@Param("role_id") Long role_id);

    @Modifying
    @Query(value = "SELECT name FROM roles_permission WHERE role_id = (:roleId)", nativeQuery = true)
    Collection<String> listPermissionByRole(@Param("roleId") Long roleId);
}
