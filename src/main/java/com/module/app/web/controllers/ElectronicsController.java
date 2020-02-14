package com.module.app.web.controllers;

import com.module.app.web.entity.Electronics;
import com.module.app.web.repository.ElectronicsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

/**
 * Kontroler zawierający endpointy dotyczące sprzętów
 *
 * @author Artur
 */
@Slf4j
@RestController
@RequestMapping(path = "/electronics")
@CrossOrigin(origins = "*")
public class ElectronicsController {
    private final ElectronicsRepository electronicsRepository;

    public ElectronicsController(ElectronicsRepository electronicsRepository) {
        this.electronicsRepository = electronicsRepository;
    }

    /**
     * Endpoint odpowiedzialny za pobranie pełnej listy sprzętów
     *
     * @return - odpowiedz http zawierająca liste i kod http
     */
    @GetMapping("/list")
    public ResponseEntity<List<Electronics>> getElectronics() {
        log.info("GETTING DATA FROM CONTROLLER /list");
        return new ResponseEntity(electronicsRepository.findAll(), HttpStatus.OK);
    }

    /**
     * Endpoint odpowiedzialny za pobranie jednego sprzętu
     *
     * @return - odpowiedz http zawierająca jeden obiekt sprzętu i kod http
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<Electronics> getElectronicById(@PathVariable("id") Long id) {
        return new ResponseEntity(electronicsRepository.findById(id), HttpStatus.OK);
    }

    /**
     * Endpoint odpowiedzialny za dodanie sprzętu (na chwile obecną nie używany)
     *
     * @return - odpowiedz z kodem http
     */
    @PostMapping("/add")
    public ResponseEntity<Void> addElectronics(@RequestBody Electronics electronics) {
        electronicsRepository.save(electronics);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Endpoint odpowiedzialny za raportowanie uszkodzenia danego sprzętu
     *
     * @return - odpowiedz z kodem http
     */
    @PutMapping("/report/{id}")
    public ResponseEntity<Void> reportDamage(@RequestBody Electronics electronics, @PathVariable Long id) {
        if (electronics.getComment().equals("")) {
            electronics.setBroken(false);
        } else {
            electronics.setBroken(true);
        }
        electronicsRepository.save(electronics);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Endpoint odpowiedzialny za usunięcie danego sprzętu
     *
     * @return - odpowiedz z kodem http
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteElectronics(@PathVariable("id") Long id) {
        if (electronicsRepository.findById(id).isPresent()) {
            electronicsRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
