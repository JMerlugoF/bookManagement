package com.api.booksmanagement.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;

import java.util.UUID;


@Data
public class BookDTO {

    private String name;
    private int edition;
    private String description;

}
