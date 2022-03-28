package com.nms.uoc.model.RequestDTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ClubRequestDTO {
    private String name;
    private String logo;
    private String description;
    private long countryId;
    private String stadium;
    private Long capacity;
}
