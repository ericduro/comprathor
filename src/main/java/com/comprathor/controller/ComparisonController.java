package com.comprathor.controller;

import com.comprathor.model.ComparisonModel;
import com.comprathor.repository.entity.Comparison;
import com.comprathor.service.IComparisonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comparison")
public class ComparisonController {

    @Autowired
    private IComparisonService comparisonService;

    @GetMapping("/all")
    public ResponseEntity<List<ComparisonModel>> getAllComparisons() {
        return ResponseEntity.ok(comparisonService.getAllComparisons());
    }

    @PostMapping("/save")
    public ResponseEntity<ComparisonModel> saveComparison(@RequestBody Comparison comparison) {
        return ResponseEntity.ok(comparisonService.saveComparison(comparison));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ComparisonModel> updateComparison(@PathVariable int id, @RequestBody Comparison updatedComparison) {
        return ResponseEntity.ok(comparisonService.updateComparison(id, updatedComparison));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteComparison(@PathVariable int id) {
        return ResponseEntity.ok(comparisonService.deleteComparison(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComparisonModel> getComparisonById(@PathVariable int id) {
        return ResponseEntity.ok(comparisonService.getComparisonById(id));
    }
}
