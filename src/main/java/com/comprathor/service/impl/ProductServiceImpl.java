package com.comprathor.service.impl;

import com.comprathor.model.ProductModel;
import com.comprathor.repository.ProductRepo;
import com.comprathor.repository.entity.Product;
import com.comprathor.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductRepo productRepo;

    @Override
    public List<ProductModel> getAllProducts() {
        List<Product> products = productRepo.findAll();
        return products.stream()
                .map(this::convertToProductModel)
                .collect(Collectors.toList());
    }

    @Override
    public ProductModel saveProduct(Product product) {
        Product savedProduct = productRepo.save(product);
        return convertToProductModel(savedProduct);
    }

    @Override
    public ProductModel updateProduct(int id, Product updatedProduct) {
        Optional<Product> existingProductOptional = productRepo.findById(id);

        if (existingProductOptional.isPresent()) {
            Product existingProduct = existingProductOptional.get();
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setDescription(updatedProduct.getDescription());
            existingProduct.setImage(updatedProduct.getImage());
            existingProduct.setId_category(updatedProduct.getId_category());

            productRepo.save(existingProduct);
            return convertToProductModel(existingProduct);
        } else {
            throw new RuntimeException("Error, producto no encontrado");
        }
    }

    @Override
    public String deleteProduct(int id) {
        if (productRepo.existsById(id)) {
            productRepo.deleteById(id);
            return "Producto eliminado";
        } else {
            throw new RuntimeException("Error, producto no encontrado");
        }
    }

    @Override
    public ProductModel getProductById(int id) {
        Optional<Product> productOptional = productRepo.findById(id);

        if (productOptional.isPresent()) {
            return convertToProductModel(productOptional.get());
        } else {
            throw new RuntimeException("Error, producto no encontrado");
        }
    }

    private ProductModel convertToProductModel(Product product) {
        return ProductModel.builder()
                .id_product(product.getId_product())
                .name(product.getName())
                .description(product.getDescription())
                .image(product.getImage())
                .id_category(product.getId_category())
                .build();
    }
}
