package com.api.booksmanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@Entity
@Table
@NoArgsConstructor @AllArgsConstructor
public class Book implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idBook;
    @Column(unique = true, nullable = false, length = 50)
    private String name;
    @Column(nullable = false)
    private int edition;
    @Column(length = 255)
    private String description;
}
