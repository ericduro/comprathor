package com.comprathor.model;

import com.comprathor.repository.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttributeModel {
    private int id_attribute;
    private String name;
    private Category id_category;


}
