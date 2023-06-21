package com.example.picturemanagement.repository.picture;

import com.example.picturemanagement.model.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPictureRepository extends JpaRepository<Picture, Long> {
}
