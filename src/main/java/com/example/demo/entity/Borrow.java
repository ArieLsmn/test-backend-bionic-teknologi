package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "borrow")
@Table(name = "borrow", schema = "public")
public class Borrow {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    @JsonProperty("id")
    private int id;
    @Column(name = "user_id")
    @JsonProperty("user_id")
    private int userId;
    @Column(name = "item_id")
    @JsonProperty("item_id")
    private int itemId;
    @Column(name = "quantity")
    @JsonProperty("quantity")
    private int quantity;
    @Column(name = "returned")
    @JsonProperty("returned")
    private boolean returned;

}
