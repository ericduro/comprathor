package com.comprathor.service;

import com.comprathor.model.ProductModel;
import com.comprathor.repository.entity.Product;

import java.util.List;

public interface IProductService {

    List<ProductModel> getAllProducts();

    ProductModel saveProduct(Product product);

    ProductModel updateProduct(int id, Product updatedProduct);

    String deleteProduct(int id);

    ProductModel getProductById(int id);
}
