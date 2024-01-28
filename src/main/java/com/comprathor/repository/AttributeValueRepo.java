package com.comprathor.repository;

import com.comprathor.repository.entity.AttributeValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttributeValueRepo extends JpaRepository<AttributeValue, Integer> {
}
