package com.comprathor.repository;

import com.comprathor.repository.entity.Comparison;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComparisonRepo extends JpaRepository<Comparison, Integer> {
}
