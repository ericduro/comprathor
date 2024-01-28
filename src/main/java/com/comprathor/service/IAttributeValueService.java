package com.comprathor.service;

import com.comprathor.model.AttributeValueModel;
import com.comprathor.repository.entity.AttributeValue;

import java.util.List;

public interface IAttributeValueService {

    List<AttributeValueModel> getAllAttributeValues();

    AttributeValueModel saveAttributeValue(AttributeValue attributeValue);

    AttributeValueModel updateAttributeValue(int id, AttributeValue updatedAttributeValue);

    String deleteAttributeValue(int id);

    AttributeValueModel getAttributeValueById(int id);
}
