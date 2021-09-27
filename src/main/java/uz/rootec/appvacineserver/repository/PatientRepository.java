package uz.rootec.appvacineserver.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.rootec.appvacineserver.entity.Patient;

import java.util.UUID;

/**
 * Created by Sherlock on 04.09.2021.
 */
public interface PatientRepository extends JpaRepository<Patient, UUID> {
    Page<Patient> findAllByFullNameContainsOrderByCreatedAtDesc(String fullName, Pageable pageable);
}
