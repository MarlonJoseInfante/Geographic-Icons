package com.alkemy.icons.icons.controller;

import com.alkemy.icons.icons.dto.PaisBasicDTO;
import com.alkemy.icons.icons.dto.PaisDTO;
import com.alkemy.icons.icons.dto.PaisFiltersDTO;
import com.alkemy.icons.icons.service.PaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paises")
public class PaisesController {

    @Autowired
    private PaisService paisService;

    @PostMapping("/save")
    public ResponseEntity<Object> savePais(@RequestBody PaisDTO paisDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(paisService.savePais(paisDTO));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deletePaisById(@PathVariable Long id){
        paisService.deletePais(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<PaisDTO>> allPaises(){
        return ResponseEntity.status(HttpStatus.OK).body(paisService.getAllPaises());
    }

    @GetMapping("/allBasic")
    public ResponseEntity<List<PaisBasicDTO>> allPaisesBasic(){
        return ResponseEntity.status(HttpStatus.OK).body(paisService.getAllPaisBasic());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PaisDTO> updatePais(@PathVariable Long id, @RequestBody PaisDTO paisDTO){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(paisService.updatePais(id, paisDTO));
    }

    @PostMapping("/addIcon/{idPais}/{idIcon}")
    public ResponseEntity<Void> addIcon(@PathVariable Long idPais, @PathVariable Long idIcon){
        paisService.addIcon(idPais, idIcon);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/removeIcon/{idPais}/{idIcon}")
    public ResponseEntity<Void> removeIcon(@PathVariable Long idPais, @PathVariable Long idIcon){
        paisService.removeIconFromPais(idPais,idIcon);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @GetMapping("/getByFilters")
    public ResponseEntity<List<PaisDTO>> getDetailsByFilters(@RequestParam(required = false)String name,
                                                      @RequestParam(required = false)String continent,
                                                      @RequestParam(required = false, defaultValue = "ASC")String order){

        return ResponseEntity.status(HttpStatus.OK).body(paisService.getByFilters(name,continent,order));
    }

    @GetMapping("/deatilsById/{id}")
    public ResponseEntity<PaisDTO> getDetailsById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(paisService.getPaisDetailsById(id));
    }
}
