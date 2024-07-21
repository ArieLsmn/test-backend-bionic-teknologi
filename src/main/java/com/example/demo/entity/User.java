package com.example.demo.entity;


import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.*;
import lombok.*;


import java.util.Collection;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name="_user")
public class User{

    @Id
    @GeneratedValue
    private int id;
    private String username;
    private String password;
    private String role;


}
