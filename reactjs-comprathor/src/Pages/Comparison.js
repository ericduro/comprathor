import React, { useEffect, useState } from 'react';
import { kc } from '../App';
import { Dropdown } from 'primereact/dropdown';
import './Comparison.css';

import { backend } from '../Variables';

function Comparison() {
  const [categories, setCategories] = useState([]);
  const [selectedCategory, setSelectedCategory] = useState('');
  const [products, setProducts] = useState([]);
  const [selectedProductLeft, setSelectedProductLeft] = useState(null);
  const [selectedProductRight, setSelectedProductRight] = useState(null);
  const [filteredProductsByCategory, setFilteredProductsByCategory] = useState([]);


  useEffect(() => {
    const fetchData = async () => {
      try {
        const productResponse = await fetch(backend + '/product/all', {
          method: 'GET',
          headers: {
            'Authorization': `Bearer ${kc.token}`
          },
        });

        if (productResponse.ok) {
          const productData = await productResponse.json();
          setProducts(productData);
        } else {
          console.error('Error en la respuesta del servidor (productos):', productResponse.statusText);
        }

        const categoryResponse = await fetch(backend + '/category/all', {
          method: 'GET',
          headers: {
            'Authorization': `Bearer ${kc.token}`
          },
        });

        if (categoryResponse.ok) {
          const categoryData = await categoryResponse.json();
          setCategories(categoryData);
        } else {
          console.error('Error en la respuesta del servidor (categorías):', categoryResponse.statusText);
        }
      } catch (error) {
        console.error('Error al realizar la solicitud:', error);
      }
    };

    fetchData();
  }, []);

  const handleCategoryChange = (event) => {
    const selectedCategoryId = event.value;
  
    setSelectedCategory(selectedCategoryId);
    setSelectedProductLeft(null);
    setSelectedProductRight(null);
  
    // Filtra los productos por la categoría seleccionada
    const filteredProducts = selectedCategoryId
      ? products.filter((product) => {
          const productCategoryId = product.id_category.id_category || product.id_category;
          return productCategoryId === Number(selectedCategoryId);
        })
      : [];
  
    setFilteredProductsByCategory(filteredProducts);
  };
  

  const handleProductLeftChange = (event) => {
    const selectedProductId = event.value;

    const selectedProduct = products.find((product) => product.id_product === selectedProductId);
    setSelectedProductLeft(selectedProduct);
  };

  const handleProductRightChange = (event) => {
    const selectedProductId = event.value;

    const selectedProduct = products.find((product) => product.id_product === selectedProductId);
    setSelectedProductRight(selectedProduct);
  };

  const renderProductDetails = (selectedProduct) => {
    if (!selectedProduct) {
      return null;
    }

    return (
      <div className="product-details">
        <h2>{selectedProduct.name}</h2>
        <img src={selectedProduct.image} alt={selectedProduct.name} style={{ maxWidth: '100%' }} />
        <div>{selectedProduct.description}</div>
      </div>
    );
  };

  return (
    <main className="comparison-container">
      <div className="filter-container" style={{ margin: '10px' }}>
        <Dropdown
          value={selectedCategory}
          options={[
            { label: 'Todas las categorías', value: '' },
            ...categories.map((category) => ({ label: category.name, value: category.id_category })),
          ]}
          onChange={handleCategoryChange}
          placeholder="Selecciona una categoría"
          style={{ fontSize: 'inherit' }}
        />
      </div>

      <div className="product-grid comparison-content">
        <div className="product-column">
          <Dropdown
            value={selectedProductLeft ? selectedProductLeft.id_product : null}
            options={[
              { label: 'Selecciona un producto', value: null },
              ...filteredProductsByCategory.map((product) => ({ label: product.name, value: product.id_product })),
            ]}
            onChange={handleProductLeftChange}
            placeholder="Selecciona un producto"
            style={{ fontSize: 'inherit', width: '100%' }}
          />
          {renderProductDetails(selectedProductLeft)}
        </div>

        <div className="product-column">
          <Dropdown
            value={selectedProductRight ? selectedProductRight.id_product : null}
            options={[
              { label: 'Selecciona un producto', value: null },
              ...filteredProductsByCategory.map((product) => ({ label: product.name, value: product.id_product })),
            ]}
            onChange={handleProductRightChange}
            placeholder="Selecciona un producto"
            style={{ fontSize: 'inherit', width: '100%' }}
          />
          {renderProductDetails(selectedProductRight)}
        </div>
      </div>
    </main>
  );
}

export default Comparison;
