package com.module.app.web.services;

import com.module.app.web.entity.Electronics;
import com.module.app.web.repository.ElectronicsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ElectronicsService {
    private final ElectronicsRepository electronicsRepository;

    public Electronics setDamage(Electronics electronics, Long id) {
        electronics.setBroken(!electronics.getComment().isEmpty());
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
