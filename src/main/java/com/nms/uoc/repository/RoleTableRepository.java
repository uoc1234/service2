package com.nms.uoc.repository;

import com.nms.uoc.model.entity.RoleTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface RoleTableRepository extends JpaRepository<RoleTable, Long> {
    List<RoleTable> findAllByNameAndDeleted(String name, Integer deleted);

    List<RoleTable> findAllByIdInAndDeleted(List<Long> id, Integer status);

    @Modifying
    @Query(value = "INSERT INTO roles_permission gr (gr.id,  gr.name , gr.role_id )  VALUES (ROLES_PERMISION_SEQ.nextval,  :name , :role_id)", nativeQuery = true)
    void addRolesPermission(@Param("name") String name, @Param("role_id") Long role_id);

    @Modifying
    @Query(value = "UPDATE role_table  SET STATUS = :status where ID = :id", nativeQuery = true)
    void changeStatusInt(@Param("status") String status, @Param("id") Long id);

    @Modifying
    @Query(value = "DELETE FROM groups_roles WHERE role_id = :role_id", nativeQuery = true)
    void deleteGroupsRoles(@Param("role_id") Long role_id);

    @Modifying
    @Query(value = "DELETE FROM roles_permission WHERE role_id = :role_id", nativeQuery = true)
    void deleteRolesPermission(@Param("role_id") Long role_id);
}

