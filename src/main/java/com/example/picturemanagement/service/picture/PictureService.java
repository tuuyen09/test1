package com.example.picturemanagement.service.picture;

import com.example.picturemanagement.model.Picture;
import com.example.picturemanagement.repository.picture.IPictureRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class PictureService implements IPictureService{
    private IPictureRepository pictureRepository;

    @Override
    public Iterable<Picture> findAll() {
        return pictureRepository.findAll();
    }

    @Override
    public Optional<Picture> findById(Long id) {
        return pictureRepository.findById(id);
    }

    @Override
    public Picture save(Picture picture) {
        return pictureRepository.save(picture);
    }

    @Override
    public void remove(Long id) {
        pictureRepository.deleteById(id);
    }
}
