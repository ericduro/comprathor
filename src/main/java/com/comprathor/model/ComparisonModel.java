package com.comprathor.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComparisonModel {
    private int id_comparison;
    private Date date;
    private String name;
    private String description;
    private int valoration;
}
