package com.nms.uoc.model.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Semifinal {
    private Club[] round_1;
    private Club[] round_2;
}
