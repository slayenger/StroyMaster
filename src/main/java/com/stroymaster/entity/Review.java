package com.stroymaster.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String author;
    private String comment;

    @Enumerated(EnumType.STRING)
    private ReviewStatus status;

    private LocalDateTime createdAt;

}
