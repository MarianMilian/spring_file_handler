package com.octenweb.springbootmvcsecurity.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransitionalDocument {
    private int id;
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    @Enumerated(EnumType.STRING)
    private Type type = Type.TYPE_PASSPORT;
    private int size;
    private MultipartFile multipartFile;
}
