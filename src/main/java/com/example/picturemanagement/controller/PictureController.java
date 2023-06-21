package com.example.picturemanagement.controller;


import com.example.picturemanagement.model.Category;
import com.example.picturemanagement.model.Picture;
import com.example.picturemanagement.service.category.ICategoryService;
import com.example.picturemanagement.service.picture.IPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pictures")
public class PictureController {
    @Autowired
    private IPictureService pictureService;
    @Autowired
    private ICategoryService categoryService;
    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getCategories() {
        List<Category> categories = (List<Category>) categoryService.findAll();
        return ResponseEntity.ok().body(categories);
    }
    @GetMapping
    public ResponseEntity<Iterable<Picture>> allPicture() {
        return new ResponseEntity<>(pictureService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/api/picture")
    public ResponseEntity<Picture> createSmartphone(@RequestBody Picture picture) {
        return new ResponseEntity<>(pictureService.save(picture), HttpStatus.CREATED);
    }
    @DeleteMapping("/api/picture/{id}")
    public ResponseEntity<Picture> deletePicture(@PathVariable Long id) {
        Optional<Picture> pictureOptional = pictureService.findById(id);
        if (!pictureOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        pictureService.remove(id);
        return new ResponseEntity<>(pictureOptional.get(), HttpStatus.NO_CONTENT);
    }
    @GetMapping("/api/picture/{id}")
    public ResponseEntity<Picture> getPictureById(@PathVariable Long id) {
        Optional<Picture> pictureOptional = pictureService.findById(id);
        if (!pictureOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pictureOptional.get(), HttpStatus.OK);
    }
    @PutMapping("/api/picture/{id}")
    public ResponseEntity<Picture> updatePicture(@PathVariable Long id, @RequestBody Picture picture) {
        Optional<Picture> pictureOptional = pictureService.findById(id);
        if (!pictureOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Picture updatedPicture = pictureOptional.get();
        updatedPicture.setName(picture.getName());
        updatedPicture.setHeight(picture.getHeight());
        updatedPicture.setWidth(picture.getWidth());
        updatedPicture.setMaterial(picture.getMaterial());
        updatedPicture.setDescription(picture.getDescription());
        updatedPicture.setPrice(picture.getPrice());
        return new ResponseEntity<>(pictureService.save(updatedPicture), HttpStatus.OK);
    }


    @ModelAttribute("category")
    public Iterable<Category> category(){
        return categoryService.findAll();
    }


    @GetMapping("/create-picture")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/picture/create");
        modelAndView.addObject("picture", new Picture());
        return modelAndView;
    }


    @PostMapping("/create-picture")
    public ModelAndView saveCustomer(@ModelAttribute("picture") Picture picture) {
        pictureService.save(picture);
        ModelAndView modelAndView = new ModelAndView("/picture/create");
        modelAndView.addObject("picture", new Picture());
        modelAndView.addObject("message", "New picture created successfully");
        return modelAndView;
    }
    @GetMapping("/pictures")
    public ModelAndView listPicture() {
        ModelAndView modelAndView = new ModelAndView("picture/list");
        modelAndView.addObject("pictures", pictureService.findAll());
        return modelAndView;
    }
    @GetMapping("/edit-picture/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Optional<Picture> picture = pictureService.findById(id);
        if (picture.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/picture/edit");
            modelAndView.addObject("picture", picture.get());
            return modelAndView;
        } else {
            return new ModelAndView("/error.404");
        }
    }

    @PostMapping("/edit-picture")
    public ModelAndView updateCustomer(@ModelAttribute("picture") Picture picture) {
        pictureService.save(picture);
        ModelAndView modelAndView = new ModelAndView("/picture/edit");
        modelAndView.addObject("picture", picture);
        modelAndView.addObject("message", "picture updated successfully");
        return modelAndView;
    }
    @GetMapping("/delete-picture/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Optional<Picture> picture = pictureService.findById(id);
        if (picture.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/picture/delete");
            modelAndView.addObject("picture", picture.get());
            return modelAndView;

        } else {
            return new ModelAndView("/error.404");
        }
    }

    @PostMapping("/delete-picture")
    public ModelAndView deleteCustomer(@ModelAttribute("picture") Picture picture) {
        pictureService.remove(picture.getId());
        ModelAndView modelAndView = new ModelAndView("picture/list");
        modelAndView.addObject("pictures", pictureService.findAll());
        return modelAndView;
    }
}
