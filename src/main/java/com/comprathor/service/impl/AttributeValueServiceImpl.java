package com.comprathor.service.impl;

import com.comprathor.model.AttributeValueModel;
import com.comprathor.repository.AttributeValueRepo;
import com.comprathor.repository.entity.AttributeValue;
import com.comprathor.service.IAttributeValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AttributeValueServiceImpl implements IAttributeValueService {

    @Autowired
    private AttributeValueRepo attributeValueRepo;

    @Override
    public List<AttributeValueModel> getAllAttributeValues() {
        return attributeValueRepo.findAll().stream()
                .map(this::convertToAttributeValueModel)
                .collect(Collectors.toList());
    }

    @Override
    public AttributeValueModel saveAttributeValue(AttributeValue attributeValue) {
        AttributeValue result = attributeValueRepo.save(attributeValue);
        return convertToAttributeValueModel(result);
    }

    @Override
    public AttributeValueModel updateAttributeValue(int id, AttributeValue updatedAttributeValue) {
        Optional<AttributeValue> existingAttributeValueOptional = attributeValueRepo.findById(id);

        if (existingAttributeValueOptional.isPresent()) {
            AttributeValue existingAttributeValue = existingAttributeValueOptional.get();
            existingAttributeValue.setValue(updatedAttributeValue.getValue());
            existingAttributeValue.setId_attribute(updatedAttributeValue.getId_attribute());
            existingAttributeValue.setId_product(updatedAttributeValue.getId_product());

            attributeValueRepo.save(existingAttributeValue);
            return convertToAttributeValueModel(existingAttributeValue);
        }
        return null;
    }

    @Override
    public String deleteAttributeValue(int id) {
        if (attributeValueRepo.existsById(id)) {
            attributeValueRepo.deleteById(id);
            return "Valor de atributo eliminado";
        } else {
            return "Error, valor de atributo no encontrado";
        }
    }

    @Override
    public AttributeValueModel getAttributeValueById(int id) {
        Optional<AttributeValue> attributeValueOptional = attributeValueRepo.findById(id);

        return attributeValueOptional.map(this::convertToAttributeValueModel).orElse(null);
    }

    private AttributeValueModel convertToAttributeValueModel(AttributeValue attributeValue) {
        return AttributeValueModel.builder()
                .id_attributevalue(attributeValue.getId_attributevalue())
                .value(attributeValue.getValue())
                .id_attribute(attributeValue.getId_attribute())
                .id_product(attributeValue.getId_product())
                .build();
    }
}
