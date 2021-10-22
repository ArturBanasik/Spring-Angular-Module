package com.module.app.web.controllers;

import com.module.app.web.entity.Electronics;
import com.module.app.web.repository.ElectronicsRepository;
import com.module.app.web.services.ElectronicsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Kontroler zawierający endpointy dotyczące sprzętów
 *
 * @author Artur
 */
@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/electronics")
@RequiredArgsConstructor
public class ElectronicsController {
    private final ElectronicsRepository electronicsRepository;
    private final ElectronicsService electronicsService;


    /**
     * Endpoint odpowiedzialny za pobranie pełnej listy sprzętów
     *
     * @return - odpowiedz http zawierająca liste i kod http
     */
    @GetMapping("/list")
    public List<Electronics> getElectronics() {
        return (List<Electronics>) electronicsRepository.findAll();
    }

    /**
     * Endpoint odpowiedzialny za pobranie jednego sprzętu
     *
     * @return - odpowiedz http zawierająca jeden obiekt sprzętu i kod http
     */
    @GetMapping("/get/{id}")
    public Optional<Electronics> getElectronicById(@PathVariable("id") Long id) {
        return electronicsRepository.findById(id);
    }

    /**
     * Endpoint odpowiedzialny za dodanie sprzętu (na chwile obecną nie używany)
     *
     * @return - odpowiedz z kodem http, wraz z obiektem dodanego sprzętem
     */
    @PostMapping("/add")
    public Optional<Electronics> addElectronics(@RequestBody Electronics electronics) {
        return electronicsRepository.findById(electronicsRepository.save(electronics).getId());
    }

    /**
     * Endpoint odpowiedzialny za raportowanie uszkodzenia danego sprzętu
     */
    @PutMapping("/report/{id}")
    public Electronics reportDamage(@RequestBody Electronics electronics, @PathVariable Long id) {
        return electronicsService.setDamage(electronics, id);
    }

    /**
     * Endpoint odpowiedzialny za usunięcie danego sprzętu
     *
     * @return - odpowiedz z kodem http
     */
    @DeleteMapping("/delete/{id}")
    public void deleteElectronics(@PathVariable("id") Long id) {
        electronicsService.removeElectronics(id);
    }
}
