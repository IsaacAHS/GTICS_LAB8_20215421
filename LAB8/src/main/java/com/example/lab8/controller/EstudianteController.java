package com.example.lab8.controller;

import com.example.lab8.entity.Estudiante;
import com.example.lab8.repository.EstudianteRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/estudiante")
public class EstudianteController {
    final EstudianteRepository estudianteRepository;
    public EstudianteController(EstudianteRepository estudianteRepository){
        this.estudianteRepository= estudianteRepository;
    }
    //LISTAR
    @GetMapping(value={"/list",""})
    public List<Estudiante> listaEstudiante(){
        return estudianteRepository.findAll();
    }

    //OBTENER
    @GetMapping(value = "/{id}")
    public ResponseEntity<HashMap<String,Object>> buscarEstudiante(@PathVariable("id") String idStr){
        try{
            int id= Integer.parseInt(idStr);
            Optional<Estudiante> byId= estudianteRepository.findById(id);
            HashMap<String,Object> respuesta = new HashMap<>();
            if(byId.isPresent()){
                respuesta.put("result","ok");
                respuesta.put("estudiante", byId.get());
            }else{
                respuesta.put("result","no existe");
            }
            return ResponseEntity.ok(respuesta);
        }catch (NumberFormatException e){
            return ResponseEntity.badRequest().body(null);
        }
    }
    //CREAR
    @PostMapping(value = {"/crear", "/"})
    public ResponseEntity<HashMap<String, Object>> guardarEstudiante(
            @RequestBody Estudiante estudiante,
            @RequestParam(value = "fetchId", required = false) boolean fetchId) {

        HashMap<String, Object> responseJson = new HashMap<>();

        estudianteRepository.save(estudiante);
        if (fetchId) {
            responseJson.put("id", estudiante.getId());
        }
        responseJson.put("estado", "creado");
        return ResponseEntity.status(HttpStatus.CREATED).body(responseJson);
    }
    //ACTUALIZAR
    @PutMapping(value = {"", "/"}, consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<HashMap<String, Object>> actualizar(Estudiante estudianteRecibido) {

        HashMap<String, Object> rpta = new HashMap<>();

        if (estudianteRecibido.getId() != null && estudianteRecibido.getId() > 0) {

            Optional<Estudiante> byId = estudianteRepository.findById(estudianteRecibido.getId());
            if (byId.isPresent()) {
                Estudiante estudianteFromDb = byId.get();

                if (estudianteRecibido.getNombre() != null)
                    estudianteFromDb.setNombre(estudianteRecibido.getNombre());

                if (estudianteRecibido.getGpa() != null)
                    estudianteFromDb.setGpa(estudianteRecibido.getGpa());

                if (estudianteRecibido.getFacultad() != null)
                    estudianteFromDb.setFacultad(estudianteRecibido.getFacultad());

                if (estudianteRecibido.getCreditoscompletados() != null)
                    estudianteFromDb.setCreditoscompletados(estudianteRecibido.getCreditoscompletados());

                if (estudianteRecibido.getRanking() != null)
                    estudianteFromDb.setRanking(estudianteRecibido.getRanking());


                estudianteRepository.save(estudianteFromDb);
                rpta.put("result", "ok");
                return ResponseEntity.ok(rpta);
            } else {
                rpta.put("result", "error");
                rpta.put("msg", "El ID del producto enviado no existe");
                return ResponseEntity.badRequest().body(rpta);
            }
        } else {
            rpta.put("result", "error");
            rpta.put("msg", "debe enviar un producto con ID");
            return ResponseEntity.badRequest().body(rpta);
        }
    }
    //DELETE
    @DeleteMapping("/delete")
    public ResponseEntity<HashMap<String, Object>> borrar(@RequestParam("id") String idStr){

        try{
            int id = Integer.parseInt(idStr);

            HashMap<String, Object> rpta = new HashMap<>();

            Optional<Estudiante> byId = estudianteRepository.findById(id);
            if(byId.isPresent()){
                estudianteRepository.deleteById(id);
                rpta.put("result","ok");
            }else{
                rpta.put("result","no ok");
                rpta.put("msg","el ID enviado no existe");
            }

            return ResponseEntity.ok(rpta);
        }catch (NumberFormatException e){
            return ResponseEntity.badRequest().body(null);
        }

    }

    @GetMapping(value = "/prueba", produces = MediaType.APPLICATION_JSON_VALUE)
    public String prueba() {
        return "{\"msg\": \"esto es una prueba\"}";
    }

    @GetMapping("/buscar/{id}")
    public Estudiante buscarF1(@PathVariable("id") int id) {
        Optional<Estudiante> byId = estudianteRepository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        } else {
            return null;
        }
    }

    @GetMapping("/buscar2/{id}")
    public HashMap<String, Object> buscarF2(@PathVariable("id") int id) {

        HashMap<String, Object> respuesta = new HashMap<>();

        Optional<Estudiante> byId = estudianteRepository.findById(id);
        if (byId.isPresent()) {
            respuesta.put("result", "ok");
            respuesta.put("producto", byId.get());
            return respuesta;
        } else {
            respuesta.put("result", "no existe");
            return respuesta;
        }
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<HashMap<String, String>> gestionException(HttpServletRequest request) {
        HashMap<String, String> responseMap = new HashMap<>();
        if (request.getMethod().equals("POST") || request.getMethod().equals("PUT")) {
            responseMap.put("estado", "error");
            responseMap.put("msg", "Debe enviar un producto");
        }
        return ResponseEntity.badRequest().body(responseMap);
    }

}
