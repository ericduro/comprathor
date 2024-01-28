package com.comprathor.service;

import com.comprathor.model.ComparisonModel;
import com.comprathor.repository.entity.Comparison;

import java.util.List;

public interface IComparisonService {

    List<ComparisonModel> getAllComparisons();

    ComparisonModel saveComparison(Comparison comparison);

    ComparisonModel updateComparison(int id, Comparison updatedComparison);

    String deleteComparison(int id);

    ComparisonModel getComparisonById(int id);
}
