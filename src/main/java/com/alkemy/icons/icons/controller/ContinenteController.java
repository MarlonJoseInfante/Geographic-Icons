package com.alkemy.icons.icons.controller;

import com.alkemy.icons.icons.dto.ContinenteDTO;
import com.alkemy.icons.icons.service.ContinenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("continente")
public class ContinenteController {
    @Autowired
    private ContinenteService continenteService;

    @PostMapping
    public ResponseEntity<ContinenteDTO> save(@RequestBody ContinenteDTO continente){
        ContinenteDTO continenteDTO=  continenteService.save(continente);
        return ResponseEntity.status(HttpStatus.CREATED).body(continenteDTO);
    }

    @GetMapping
    public ResponseEntity<List<ContinenteDTO>> getAll(){
        return ResponseEntity.ok().body(continenteService.getAllContinentes());
    }
}
