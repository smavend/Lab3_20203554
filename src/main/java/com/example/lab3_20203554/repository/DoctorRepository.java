package com.example.lab3_20203554.repository;

import com.example.lab3_20203554.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.print.Doc;
import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Integer> {
    @Query(nativeQuery = true,value = "select * from doctor where hospital_id = ?1")
    List<Doctor> buscarPorIdHospital(int hospitalId);
}
