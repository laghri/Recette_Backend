package com.example.recette_backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @Column(name = "name")
    private String name;


    @Column(name = "ingredient")
    private String ingredients;



    @Column(name = "step")
    private String  steps;
    @Column(name = "preparationTime")
    private int preparationTime;
    @Column(name = "photo")
    private String photo;
}