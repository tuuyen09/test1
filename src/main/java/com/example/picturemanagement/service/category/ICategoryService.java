package com.example.picturemanagement.service.category;

import com.example.picturemanagement.model.Category;
import com.example.picturemanagement.model.Picture;

import java.util.Optional;

public interface ICategoryService {
    Iterable<Category> findAll();

    Optional<Category> findById(Long id);

    Category save(Category category);

    void remove(Long id);
}
