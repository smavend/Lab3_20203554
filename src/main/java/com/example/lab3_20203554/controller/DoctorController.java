package com.example.lab3_20203554.controller;

import com.example.lab3_20203554.entity.Doctor;
import com.example.lab3_20203554.entity.Hospital;
import com.example.lab3_20203554.repository.DoctorRepository;
import com.example.lab3_20203554.repository.HospitalRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class DoctorController {
    final DoctorRepository doctorRepository;
    final HospitalRepository hospitalRepository;

    public DoctorController(DoctorRepository doctorRepository, HospitalRepository hospitalRepository) {
        this.doctorRepository = doctorRepository;
        this.hospitalRepository = hospitalRepository;
    }

    @GetMapping("/doctor")
    private String buscarPorHospital(@RequestParam(name="idHospital", required = false) Integer idHospital,
                                     Model model){
        if(idHospital!=null){
            Optional<Hospital> optHospital = hospitalRepository.findById(idHospital);
            if(optHospital.isPresent()){
                List<Doctor> lista = doctorRepository.buscarPorIdHospital(idHospital);
                model.addAttribute("listaDoctores", lista);
                model.addAttribute("name", optHospital.get().getNombre());
                return "listaDoctor";
            }else {
                return "redirect:/hospital";
            }
        }else{
            List<Doctor> lista = doctorRepository.findAll();
            model.addAttribute("listaDoctores", lista);
            return "listaDoctor";
        }
    }
}
