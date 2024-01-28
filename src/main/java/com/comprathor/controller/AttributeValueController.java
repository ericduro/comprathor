package com.comprathor.controller;

import com.comprathor.model.AttributeValueModel;
import com.comprathor.repository.entity.AttributeValue;
import com.comprathor.service.IAttributeValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attributevalue")
public class AttributeValueController {

    @Autowired
    private IAttributeValueService attributeValueService;

    @GetMapping("/all")
    public ResponseEntity<List<AttributeValueModel>> getAllAttributeValues() {
        return ResponseEntity.ok(attributeValueService.getAllAttributeValues());
    }

    @PostMapping("/save")
    public ResponseEntity<AttributeValueModel> saveAttributeValue(@RequestBody AttributeValue attributeValue) {
        AttributeValueModel result = attributeValueService.saveAttributeValue(attributeValue);
        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AttributeValueModel> updateAttributeValue(@PathVariable int id, @RequestBody AttributeValue updatedAttributeValue) {
        AttributeValueModel result = attributeValueService.updateAttributeValue(id, updatedAttributeValue);
        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAttributeValue(@PathVariable int id) {
        return ResponseEntity.ok(attributeValueService.deleteAttributeValue(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttributeValueModel> getAttributeValueById(@PathVariable int id) {
        AttributeValueModel result = attributeValueService.getAttributeValueById(id);
        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(404).build();
        }
    }
}
