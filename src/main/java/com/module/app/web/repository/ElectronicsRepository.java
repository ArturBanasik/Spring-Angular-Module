package com.module.app.web.repository;

import com.module.app.web.entity.Electronics;
import org.springframework.data.repository.CrudRepository;

/**
 * Repozytorium dla sprzętu
 *
 * @author Artur
 */
public interface ElectronicsRepository extends CrudRepository<Electronics, Long> {
}
