package com.example.lab3_20203554.controller;

import com.example.lab3_20203554.entity.Doctor;
import com.example.lab3_20203554.repository.DoctorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class DoctorController {
    final DoctorRepository doctorRepository;

    public DoctorController(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }
    @GetMapping("/doctor")
    private String buscarPorHospital(@RequestParam("idhospital") int  idHospital,
                                     Model model){
        List<Doctor> lista = doctorRepository.buscarPorIdHospital(idHospital);
        model.addAttribute("listaDoctores", lista);
        return "listaDoctor";
    }
}
