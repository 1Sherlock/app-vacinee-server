package uz.rootec.appvacineserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.rootec.appvacineserver.entity.Patient;
import uz.rootec.appvacineserver.payload.ApiResponse;
import uz.rootec.appvacineserver.payload.ReqPatient;
import uz.rootec.appvacineserver.repository.PatientRepository;

import java.util.UUID;

/**
 * Created by Sherlock on 04.09.2021.
 */
@RestController
@RequestMapping("api/patient")
public class PatientController {
    @Autowired
    private PatientRepository patientRepository;

    //    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/save")
    public HttpEntity<?> save(@RequestBody ReqPatient reqPatient) {
        try {
            Patient patient;

            if (reqPatient.getId() == null)
                patient = new Patient();
            else
                patient = patientRepository.getOne(reqPatient.getId());


            patient.setSerial(reqPatient.getSerial());
            patient.setVacinePlace(reqPatient.getVacinePlace());
            patient.setVacineType(reqPatient.getVacineType());
            patient.setVacineSerials(reqPatient.getVacineSerials());
            patient.setVacineDates(reqPatient.getVacineDates());
            patient.setPassportNumber(reqPatient.getPassportNumber());
            patient.setPinfl(reqPatient.getPinfl());
            patient.setFullName(reqPatient.getFullName());
            patient.setBirthDate(reqPatient.getBirthDate());
            patient.setGender(reqPatient.getGender());
            patient.setGivenDate(reqPatient.getGivenDate());

            patientRepository.save(patient);

            if (reqPatient.getId() == null) {
                return ResponseEntity.ok(new ApiResponse(true, "Сохранено"));
            } else {
                return ResponseEntity.ok(new ApiResponse(true, "Изменено"));
            }
        } catch (Exception e) {
            return ResponseEntity.ok(new ApiResponse(false, e.getMessage()));
        }
    }

    @GetMapping
    public HttpEntity<?> getAll(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size, @RequestParam(defaultValue = "") String search) {
        return ResponseEntity.ok(new ApiResponse(true, "", patientRepository.findAllByFullNameContainsOrderByCreatedAtDesc(search, PageRequest.of(page, size))));
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable UUID id) {
        return ResponseEntity.ok(new ApiResponse(true, "", patientRepository.getOne(id)));
    }

    //    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePatient(@PathVariable UUID id) {
        try {
            patientRepository.deleteById(id);
            return ResponseEntity.ok(new ApiResponse(true, "Удалено"));
        } catch (Exception e) {
            return ResponseEntity.ok(new ApiResponse(false, e.getMessage()));
        }
    }
}
