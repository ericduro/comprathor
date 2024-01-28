package com.comprathor.service.impl;

import com.comprathor.model.ComparisonModel;
import com.comprathor.repository.ComparisonRepo;
import com.comprathor.repository.entity.Comparison;
import com.comprathor.service.IComparisonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ComparisonServiceImpl implements IComparisonService {

    @Autowired
    private ComparisonRepo comparisonRepo;

    @Override
    public List<ComparisonModel> getAllComparisons() {
        List<Comparison> comparisons = comparisonRepo.findAll();
        return comparisons.stream()
                .map(this::convertToComparisonModel)
                .collect(Collectors.toList());
    }

    @Override
    public ComparisonModel saveComparison(Comparison comparison) {
        Comparison savedComparison = comparisonRepo.save(comparison);
        return convertToComparisonModel(savedComparison);
    }

    @Override
    public ComparisonModel updateComparison(int id, Comparison updatedComparison) {
        Optional<Comparison> existingComparisonOptional = comparisonRepo.findById(id);

        if (existingComparisonOptional.isPresent()) {
            Comparison existingComparison = existingComparisonOptional.get();
            existingComparison.setDate(updatedComparison.getDate());
            existingComparison.setName(updatedComparison.getName());
            existingComparison.setDescription(updatedComparison.getDescription());
            existingComparison.setValoration(updatedComparison.getValoration());

            comparisonRepo.save(existingComparison);
            return convertToComparisonModel(existingComparison);
        } else {
            throw new RuntimeException("Error, comparación no encontrada");
        }
    }

    @Override
    public String deleteComparison(int id) {
        if (comparisonRepo.existsById(id)) {
            comparisonRepo.deleteById(id);
            return "Comparación eliminada";
        } else {
            throw new RuntimeException("Error, comparación no encontrada");
        }
    }

    @Override
    public ComparisonModel getComparisonById(int id) {
        Optional<Comparison> comparisonOptional = comparisonRepo.findById(id);

        if (comparisonOptional.isPresent()) {
            return convertToComparisonModel(comparisonOptional.get());
        } else {
            throw new RuntimeException("Error, comparación no encontrada");
        }
    }

    private ComparisonModel convertToComparisonModel(Comparison comparison) {
        return ComparisonModel.builder()
                .id_comparison(comparison.getId_comparison())
                .date(comparison.getDate())
                .name(comparison.getName())
                .description(comparison.getDescription())
                .valoration(comparison.getValoration())
                .build();
    }
}
