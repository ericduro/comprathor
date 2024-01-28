package com.comprathor.service.impl;

import com.comprathor.model.AttributeModel;
import com.comprathor.repository.AttributeRepo;
import com.comprathor.repository.entity.Attribute;
import com.comprathor.service.IAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AttributeServiceImpl implements IAttributeService {

    @Autowired
    private AttributeRepo attributeRepo;

    @Override
    public List<AttributeModel> getAllAttributes() {
        List<Attribute> attributes = attributeRepo.findAll();
        return attributes.stream()
                .map(attribute -> AttributeModel.builder()
                        .id_attribute(attribute.getId_attribute()) // Ajustado para incluir el id_attribute
                        .name(attribute.getName())
                        .id_category(attribute.getId_category())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public AttributeModel saveAttribute(Attribute attribute) {
        Attribute result = attributeRepo.save(attribute);
        return AttributeModel.builder()
                .id_attribute(result.getId_attribute())  // Agregamos el id_attribute
                .name(result.getName())
                .id_category(result.getId_category())
                .build();
    }

    @Override
    public AttributeModel updateAttribute(int id, Attribute updatedAttribute) {
        Optional<Attribute> existingAttributeOptional = attributeRepo.findById(id);

        return existingAttributeOptional.map(existingAttribute -> {
            existingAttribute.setName(updatedAttribute.getName());
            existingAttribute.setId_category(updatedAttribute.getId_category());

            attributeRepo.save(existingAttribute);
            return AttributeModel.builder()
                    .id_attribute(existingAttribute.getId_attribute()) // Ajustado para incluir el id_attribute
                    .name(existingAttribute.getName())
                    .id_category(existingAttribute.getId_category())
                    .build();
        }).orElse(null); // Devuelve null o algún valor por defecto en caso de que no se encuentre el atributo
    }

    @Override
    public String deleteAttribute(int id) {
        if (attributeRepo.existsById(id)) {
            attributeRepo.deleteById(id);
            return "Atributo eliminado";
        } else {
            return "Error, atributo no encontrado";
        }
    }

    @Override
    public AttributeModel getAttributeById(int id) {
        Optional<Attribute> attributeOptional = attributeRepo.findById(id);

        return attributeOptional.map(attribute -> AttributeModel.builder()
                .id_attribute(attribute.getId_attribute())  // Agregamos el id_attribute
                .name(attribute.getName())
                .id_category(attribute.getId_category())
                .build()
        ).orElse(null);
    }
}
