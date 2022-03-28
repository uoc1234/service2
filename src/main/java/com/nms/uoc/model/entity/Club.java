package com.nms.uoc.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "CLUB")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Club {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "LOGO")
    private String logo;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToOne
    @JoinColumn(name = "COUNTRY_ID", nullable = false)
    private Country country;

    @Column(name = "STADIUM")
    private String stadium;

    @Column(name = "CAPACITY")
    private Long capacity;
}
