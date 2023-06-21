package com.example.picturemanagement.repository.category;

import com.example.picturemanagement.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<Category, Long> {
}
