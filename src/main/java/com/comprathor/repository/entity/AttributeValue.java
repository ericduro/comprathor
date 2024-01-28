package com.comprathor.repository.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "attributevalue")
public class AttributeValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_attributevalue;
    private String value;
    @ManyToOne
    @JoinColumn(name = "id_attribute", nullable = false)
    private Attribute id_attribute;

    @ManyToOne
    @JoinColumn(name = "id_product", nullable = false)
    private Product id_product;
}
