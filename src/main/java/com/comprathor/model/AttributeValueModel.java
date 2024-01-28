package com.comprathor.model;

import com.comprathor.repository.entity.Attribute;
import com.comprathor.repository.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttributeValueModel {
    private int id_attributevalue;
    private String value;
    private Attribute id_attribute;

    private Product id_product;
}
