package com.module.app.web.repository;

import com.module.app.web.entity.Electronics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * Repozytorium dla sprzętu
 *
 * @author Artur
 */
@Repository
public interface ElectronicsRepository extends JpaRepository<Electronics, Long> {
}
