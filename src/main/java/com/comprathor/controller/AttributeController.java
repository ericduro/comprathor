package com.comprathor.controller;

import com.comprathor.model.AttributeModel;
import com.comprathor.repository.entity.Attribute;
import com.comprathor.service.IAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attribute")
public class AttributeController {

    @Autowired
    private IAttributeService iAttributeService;

    // Obtener todos los atributos
    @GetMapping("/all")
    public ResponseEntity<List<AttributeModel>> getAllAttributes() {
        List<AttributeModel> response = iAttributeService.getAllAttributes();
        System.out.println(response.size());
        return ResponseEntity.ok(response);
    }

    // Guardar un nuevo atributo
    @PostMapping("/save")
    public ResponseEntity<AttributeModel> saveAttribute(@RequestBody Attribute attribute) {
        AttributeModel result = iAttributeService.saveAttribute(attribute);
        return ResponseEntity.ok(result);
    }

    // Actualizar un atributo
    @PutMapping("/update/{id}")
    public ResponseEntity<AttributeModel> updateAttribute(@PathVariable int id, @RequestBody Attribute updatedAttribute) {
        AttributeModel result = iAttributeService.updateAttribute(id, updatedAttribute);
        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(404).body(null); // O algún valor por defecto en caso de error
        }
    }

    // Eliminar un atributo
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteAttribute(@PathVariable int id) {
        String result = iAttributeService.deleteAttribute(id);
        if ("Atributo eliminado".equals(result)) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(404).body(result);
        }
    }

    // Obtener un atributo por ID
    @GetMapping("/{id}")
    public ResponseEntity<AttributeModel> getAttributeById(@PathVariable int id) {
        AttributeModel result = iAttributeService.getAttributeById(id);
        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(404).body(null); // O algún valor por defecto en caso de error
        }
    }
}
