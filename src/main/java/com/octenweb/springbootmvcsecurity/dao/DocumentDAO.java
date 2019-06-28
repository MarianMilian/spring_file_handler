package com.octenweb.springbootmvcsecurity.dao;

import com.octenweb.springbootmvcsecurity.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentDAO extends JpaRepository<Document,Integer> {
}
