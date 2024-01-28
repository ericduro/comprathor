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
public class ProductModel {
    private int id_product;
    private String name;
    private String description;
    private String image;
    private Category id_category;
}
