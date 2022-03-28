package com.nms.uoc.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "COUNTRY")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Country {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "NAME_OF_TOURNAMENTS")
    private String nameOfTournaments;
}
