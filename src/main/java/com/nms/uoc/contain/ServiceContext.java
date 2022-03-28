package com.nms.uoc.contain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ServiceContext {
    private Integer pageNumber;
    private Integer pageSize;
    private String sortField;
    private String sortType;
    private String key;
}
