package com.module.app.web.services;

import com.module.app.web.entity.Electronics;
import com.module.app.web.repository.ElectronicsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ElectronicsService {
    private final ElectronicsRepository electronicsRepository;

    public ElectronicsService(ElectronicsRepository electronicsRepository) {
        this.electronicsRepository = electronicsRepository;
    }

    public Electronics setDamage(Electronics electronics, Long id) {
        if (electronics.getComment().isEmpty()) {
            electronics.setBroken(false);
        } else {
            electronics.setBroken(true);
        }
        return electronicsRepository.save(electronics);
    }

    public ResponseEntity<Void> removeElectronics(Long id) {
        if (electronicsRepository.findById(id).isPresent()) {
            electronicsRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
