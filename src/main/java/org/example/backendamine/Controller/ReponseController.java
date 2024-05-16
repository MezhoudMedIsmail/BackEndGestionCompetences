package org.example.backendamine.Controller;

import lombok.RequiredArgsConstructor;
import org.example.backendamine.Entities.Reponse;
import org.example.backendamine.Repository.ReponseRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/reponse")
@RequiredArgsConstructor
public class ReponseController {
    private final ReponseRepository reponseRepository;

    @GetMapping
    public List<Reponse> getReponse() {
        return reponseRepository.findAll();
    }

    @PostMapping
    public List<Reponse> addReponse(@RequestBody List<Reponse> reponse) {
        return reponseRepository.saveAll(reponse);
    }

    @PutMapping("/{id}")
    public Reponse updateReponse(@PathVariable long id, @RequestBody Reponse reponse) {
        reponse.setId(id);
        return reponseRepository.save(reponse);
    }
    @GetMapping("/{id}")
    public Reponse getReponseById(@PathVariable long id) {
        return reponseRepository.findById(id).orElseThrow(() -> new RuntimeException("Reponse not found"));
    }
}