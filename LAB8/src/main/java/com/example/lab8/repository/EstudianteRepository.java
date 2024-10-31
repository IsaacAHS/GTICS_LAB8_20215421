package com.example.lab8.repository;

import com.example.lab8.entity.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante,Integer>{
    List<Estudiante> findByFacultadOrderByGpaDesc(String facultad);
    Long countByFacultad(String facultad);
}
