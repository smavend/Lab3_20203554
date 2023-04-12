package com.example.lab3_20203554.controller;

import com.example.lab3_20203554.repository.DoctorRepository;
import org.springframework.stereotype.Controller;

@Controller
public class DoctorController {
    final DoctorRepository doctorRepository;

    public DoctorController(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }
    private
}
