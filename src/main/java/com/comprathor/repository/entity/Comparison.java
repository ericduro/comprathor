package com.comprathor.repository.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "comparison")
public class Comparison {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_comparison;
    private Date date;
    private String name;
    private String description;
    private int valoration;
}
