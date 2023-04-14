package com.example.lab3_20203554.controller;

import com.example.lab3_20203554.entity.Doctor;
import com.example.lab3_20203554.entity.Hospital;
import com.example.lab3_20203554.entity.Paciente;
import com.example.lab3_20203554.repository.DoctorRepository;
import com.example.lab3_20203554.repository.HospitalRepository;
import com.example.lab3_20203554.repository.PacienteRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class PacienteController {
    final PacienteRepository pacienteRepository;
    final DoctorRepository doctorRepository;
    final HospitalRepository hospitalRepository;

    public PacienteController(PacienteRepository pacienteRepository, DoctorRepository doctorRepository, HospitalRepository hospitalRepository) {
        this.pacienteRepository = pacienteRepository;
        this.doctorRepository = doctorRepository;
        this.hospitalRepository = hospitalRepository;
    }


    @GetMapping("/paciente")
    String listaPacienteXDoctor(@RequestParam (name="idDoctor", required = false) Integer idDoctor,
                                @RequestParam(name="idHospital", required = false) Integer idHospital,
                                Model model){
        if(idDoctor!=null&&idHospital==null) {
            Optional<Doctor> optDoctor = doctorRepository.findById(idDoctor);
            if(optDoctor.isPresent()){
                List<Paciente> lista = pacienteRepository.buscarPorIdDoctor(idDoctor);
                model.addAttribute("listaPacientes", lista);
                model.addAttribute("name", optDoctor.get().getNombre());
                model.addAttribute("idDoctor",true);
                return "listaPaciente";
            }else {
                return "redirect:/doctor";
            }
        } else if (idDoctor==null&&idHospital!=null) {
            Optional<Hospital> optHospital = hospitalRepository.findById(idHospital);
            if(optHospital.isPresent()){
                List<Paciente> lista = pacienteRepository.findByhospital_id(idHospital);
                model.addAttribute("listaPacientes", lista);
                model.addAttribute("name", optHospital.get().getNombre());
                model.addAttribute("idHospital",true);
                return "listaPaciente";
            }else {
                return "redirect:/hospital";
            }
        }else {
            List<Paciente> lista = pacienteRepository.findAll();
            model.addAttribute("listaPacientes", lista);
            return "listaPaciente";
        }
    }
    @GetMapping("/paciente/pendiente")
    String listaPacientePendiente(@RequestParam (name = "idDoctor") int idDoctor,
                                  Model model){
        Optional<Doctor> optDoctor = doctorRepository.findById(idDoctor);
        if(optDoctor.isPresent()){
            List<Paciente> lista = pacienteRepository.buscarPendiente(idDoctor);
            model.addAttribute("listaPacientes", lista);
            model.addAttribute("name", optDoctor.get().getNombre());
            return "listaPaciente";
        }else{
            return "redirect:/paciente";
        }
    }
    @GetMapping("/paciente/derivar")
    String derivarPaciente(Model model){
        List<Doctor> lista = doctorRepository.findAll();
        model.addAttribute("listaDoctores",lista);
        return "derivarPaciente";
    }

    @PostMapping("/paciente/derivar")
    String derivarPost(@RequestParam ("idDerivado") int idDerivado,
                       @RequestParam ("idDerivador") int idDerivador){
        pacienteRepository.derivarConDoctor(idDerivado,idDerivador);
        return "redirect:/paciente";
    }

    @GetMapping("/paciente/editar")
    String vistaEditar(@RequestParam("id") int id,
                       Model model){
        Optional<Paciente> optPaciente = pacienteRepository.findById(id);
        if(optPaciente.isPresent()){
            model.addAttribute("paciente", optPaciente.get());
            return "editarPaciente";
        }else{
            return "redirect:/paciente";
        }
    }

    @PostMapping("/paciente/guardar")
    String guardarCambioHabitacion(@RequestParam("id") int id,
                                   @RequestParam("numeroHabitacion") int numHabitacion){
        pacienteRepository.cambiarHabitacion(numHabitacion,id);
        return "redirect:/paciente";
    }
}
