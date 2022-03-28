package com.nms.uoc.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "roles_permission")
public class RolePermission {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleTable roleTable;

}
