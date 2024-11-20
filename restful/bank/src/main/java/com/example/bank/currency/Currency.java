package com.example.bank.currency;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
public class Currency {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long id;
        String country;
        String code;
        String name;
        @Setter
        float rate;
}