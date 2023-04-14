package com.example.lab3_20203554.controller;

import com.example.lab3_20203554.entity.Hospital;
import com.example.lab3_20203554.repository.HospitalRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HospitalController {
    final HospitalRepository hospitalRepository;

    public HospitalController(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    @GetMapping(value = {"/","/hospital"})
    public String listaHospital(Model model){
        List<Hospital> lista = hospitalRepository.findAll();
        model.addAttribute("listaHospitales", lista);
        return "listaHospital";
    }
}
