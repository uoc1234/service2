package com.nms.uoc.model.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Quarterfinals {
    private Club[] round_1;
    private Club[] round_2;
    private Club[] round_3;
    private Club[] round_4;
}
