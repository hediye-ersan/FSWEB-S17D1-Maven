package com.workintech.fswebs17d1.controller;

import com.workintech.fswebs17d1.entity.Animal;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/workintech/animal")
public class AnimalController {
    private final Map<Integer, Animal> animals = new HashMap<>();

    @Value("${project.developer.fullname}")
    private String developerName;

    @Value("${course.name}")
    private String courseName;

    @GetMapping("/values")
    public String getValues(){
        return developerName + "---" + courseName;
    }


    @GetMapping
    public List<Animal> listAnimal(){
        return animals.values().stream().toList();
    }

    @GetMapping("/{id}")
    public Animal findAnimal(@PathVariable int id){
        return animals.get(id);
    }

    @PostMapping
    public Animal addAnimal(@RequestBody Animal animal){
        animals.put(animal.getId(), animal);
        return animal;
    }


    @PutMapping("/{id}")
    public Animal updateAnimal(@PathVariable int id, @RequestBody Animal animal){
        animals.put(id, new Animal(id, animal.getName()));
        return animals.get(id);
    }

    @DeleteMapping("/{id}")
    public Animal delete(@PathVariable int id){
        Animal animal = animals.get(id);
        animals.remove(id);
        return animal;
    }
}
