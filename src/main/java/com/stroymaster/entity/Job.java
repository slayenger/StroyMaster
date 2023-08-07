package com.stroymaster.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    @Size(max = 100)
    private String title;

    @NotBlank
    @Size(max = 500)
    private String description;

    @DecimalMin(value = "0")
    @NotBlank
    private String price;

}
