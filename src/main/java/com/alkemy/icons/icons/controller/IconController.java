package com.alkemy.icons.icons.controller;

import com.alkemy.icons.icons.dto.IconBasicDTO;
import com.alkemy.icons.icons.dto.IconDTO;
import com.alkemy.icons.icons.service.IconService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("icon")
public class IconController {

    @Autowired
    private IconService iconService;

    @PostMapping("/save")
    public ResponseEntity<Object> saveIcon(@RequestBody IconDTO iconDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(iconService.saveIcon(iconDTO));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateIcon(@PathVariable Long id, @RequestBody IconDTO iconDTO) {
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(iconService.updateIcon(id, iconDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<IconBasicDTO>> allIcons(){
        return ResponseEntity.status(HttpStatus.OK).body(iconService.getAllIcons());
    }

    @GetMapping("/all/detalles")
    public ResponseEntity<List<IconDTO>> getAllIconsDetalis(){
        return ResponseEntity.status(HttpStatus.OK).body(iconService.getAllIconDetails());
    }

    @GetMapping("/icon/details/{id}")
    public ResponseEntity<Object> getIconDetailsById(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(iconService.getIconDetailsById(id));
        }catch (Exception e){
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteIconById(@PathVariable Long id){
        iconService.deleteIcon(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public ResponseEntity<List<IconDTO>> getDetailsByFilters(@RequestParam(required = false) String name, @RequestParam(required = false) String date,
                                                             @RequestParam(required = false) List<Long> cities, @RequestParam(required = false, defaultValue = "ASC") String order){
            return ResponseEntity.status(HttpStatus.OK).body(iconService.getByFilters(name, date, cities, order));

    }
}
