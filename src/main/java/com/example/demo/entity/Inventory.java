package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "inventory")
@Table(name = "inventory", schema = "public")
public class Inventory implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    @JsonProperty("id")
    private int id;
    @Column(name = "name")
    @JsonProperty("name")
    private String title;
    @Column(name= "quantity")
    @JsonProperty("quantity")
    private int quantity;
    @Column(name= "type")
    @JsonProperty("type")
    private String category;


}
