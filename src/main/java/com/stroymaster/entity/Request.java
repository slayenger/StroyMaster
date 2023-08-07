package com.stroymaster.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String surname;
    private String email;
    //добавить больше валидации
    @DecimalMin(value = "0")
    private String phoneNumber;
    private String address;
    //возможно нужно добавить связь с сущностью Job?
    private String service;


}
