package com.example.bank.clint;

import com.example.bank.phone.Phone;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.FetchType;
import jakarta.persistence.CascadeType;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Client {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(
                updatable = false,
                length = 20
        )
        private String accountNo;

        @Column(length = 25)
        private String pinCode;

        @Column(length = 25)
        private String firstName;

        @Column(length = 25)
        private String secondName;

        @Column(length = 25)
        private String lastName;

        @Column(length = 100)
        private String email;

        private double accountBalance;

        @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "client")
        @JsonManagedReference // In order to avoid infinite recursion
        List<Phone> numbers;
}