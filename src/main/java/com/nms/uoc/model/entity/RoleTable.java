package com.nms.uoc.model.entity;

import com.nms.uoc.contain.DELETED;
import com.nms.uoc.contain.STATUS;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ROLE_TABLE")
public class RoleTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private STATUS status;

    public DELETED deleted;

    private String description;
}
