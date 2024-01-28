package com.comprathor.service;

import com.comprathor.model.AttributeModel;
import com.comprathor.repository.entity.Attribute;

import java.util.List;
public interface IAttributeService {
    List<AttributeModel> getAllAttributes();

    AttributeModel saveAttribute(Attribute attribute);

    AttributeModel updateAttribute(int id, Attribute updatedAttribute);

    String deleteAttribute(int id);

    AttributeModel getAttributeById(int id);
}
