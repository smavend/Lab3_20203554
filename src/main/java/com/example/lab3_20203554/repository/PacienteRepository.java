package com.example.lab3_20203554.repository;

import com.example.lab3_20203554.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente,Integer> {
    @Query(nativeQuery = true, value = "select * from paciente where doctor_id = ?1")
    List<Paciente> buscarPorIdDoctor(int doctorId);

    List<Paciente> findByhospital_id (int id);
    @Query(nativeQuery = true, value = "select *  from paciente where (fecha_cita-date(now()))>0 and doctor_id = ?1")
    List<Paciente> buscarPendiente(int doctorId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "update paciente set doctor_id = ?1 where doctor_id=?2")
    void derivarConDoctor (int doctorNuevo, int doctorActual);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "update paciente set numero_habitacion = ?1 where id = ?2")
    void cambiarHabitacion(int numHabitacion, int id);
}
