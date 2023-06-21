package com.example.picturemanagement.service.picture;

import com.example.picturemanagement.model.Picture;

import java.util.Optional;

public interface IPictureService {
    Iterable<Picture> findAll();
    Optional<Picture> findById(Long id);
    Picture save(Picture picture);
    void remove(Long id);
}
