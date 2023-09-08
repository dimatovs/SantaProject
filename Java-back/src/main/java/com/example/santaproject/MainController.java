package com.example.santaproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@CrossOrigin()
@RestController
@RequestMapping("/desires")
public class MainController {

    private final DesireRepository desireRepository;

    public MainController(@Autowired DesireRepository desireRepository) {
        this.desireRepository = desireRepository;
    }

    @GetMapping("/getDesires")
    public Iterable<Desire> getAllDesires() {
        if(desireRepository.findAll().isEmpty()) {throw new NullPointerException();}
        return desireRepository.findAll();
    }

    @PostMapping("/addDesires")
    public void addNewDesires(@RequestBody Desire[] desire) {
        List<Desire> result = Arrays.asList(desire);
        desireRepository.saveAll(result);
    }

    @DeleteMapping("/deleteALL")
    public String deleteAllDesires() {
        desireRepository.deleteAll();
        return "Desires deleted";
    }

    @PostMapping("/updateDesire")
    public void updateDesire(@RequestBody Desire desire) {
        desireRepository.save(desire);
    }

    @DeleteMapping("/deleteDesire/{id}")
    public void deleteDesire(@PathVariable long id) {
        desireRepository.deleteById(id);
    }

}
