import React, { useEffect, useState, useRef } from 'react'; // Add 'useRef' to the import statement
import { kc } from '../App';
import { Dropdown } from 'primereact/dropdown';
import { Dialog } from 'primereact/dialog';
import './Product.css'; // Importa un archivo CSS para estilos adicionales

import { backend } from '../Variables';

function Product() {
  const [products, setProducts] = useState([]);
  const [categories, setCategories] = useState([]);
  const [filteredProducts, setFilteredProducts] = useState([]);
  const [selectedCategory, setSelectedCategory] = useState('');
  const [selectedCategoryDialog, setSelectedCategoryDialog] = useState(''); // Nuevo estado para el Dropdown del diálogo
  const [visible, setVisible] = useState(false);
  const dialogRef = useRef(null);

  const showDialog = () => {
    setVisible(true);
  };

  const hideDialog = () => {
    setVisible(false);
  };

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
          setFilteredProducts(productData);
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
    setSelectedCategory(event.value);

    if (event.value) {
      const categoryId = Number(event.value);

      // Filtra los productos por la categoría seleccionada
      const filteredProducts = products.filter((product) => {
        const productCategoryId = product.id_category.id_category || product.id_category;

        return productCategoryId === categoryId;
      });

      setFilteredProducts(filteredProducts);
    } else {
      setFilteredProducts(products);
    }
  };

  const handleCategoryChangeDialog = (event) => {
    setSelectedCategoryDialog(event.value);
  };
  
  const addProduct = async (formData) => {
    try {
      await kc.updateToken(10);
      // Asegúrate de enviar el valor seleccionado del dropdown
      formData.id_category = {
        id_category: Number(selectedCategoryDialog),
      };
  
      const response = await fetch(backend + '/product/save', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${kc.token}`
        },
        body: JSON.stringify(formData),
      });
  
      if (response.ok) {
        console.log('Producto agregado con éxito');
        hideDialog();
      } else {
        console.error('Error al agregar el producto:', response.statusText);
      }
    } catch (error) {
      console.error('Error al realizar la solicitud:', error);
    }
  };

  const handleSubmit = (event) => {
    event.preventDefault();
  
    // Log the event target to inspect its structure
    console.log('Event target:', event.target);
  
    // Recoge los valores del formulario y llama a la función para agregar el producto
    const formData = {
      name: event.target.name.value,
      description: event.target.description.value,
      image: event.target.image.value,
      id_category: {
        id_category: Number(selectedCategory),
      },
    };
  
    console.log('Form data:', formData);
  
    addProduct(formData);
  };
  
  

  return (
    <main>
      <div className="filter-container" style={{ margin: '10px' }}>
        <Dropdown
          value={selectedCategory}
          options={[
            { label: 'Todas las categorías', value: '' },
            ...categories.map((category) => ({ label: category.name, value: category.id_category })),
          ]}
          onChange={handleCategoryChange}
          placeholder="Select a category"
          style={{ fontSize: 'inherit' }}
        />
        <button onClick={showDialog}>Añadir Producto</button>
      </div>

      <div className="product-grid">
        {filteredProducts.map(product => (
          <div key={product.id} className="product-card">
            <h3>{product.name}</h3>
            <img
              src={product.image}
              alt={product.name}
              style={{ maxWidth: '100%' }} 
            />
            <div>Detalles: {product.description}</div>
          </div>
        ))}
      </div>

      <Dialog
        header="Añadir Nuevo Producto"
        visible={visible}
        style={{ width: '50vw' }}
        onHide={hideDialog}
        ref={dialogRef}
      >
        <form onSubmit={handleSubmit}>
          <div>
            <label htmlFor="name">Nombre:</label>
            <input type="text" id="name" required />
          </div>
          <div>
            <label htmlFor="description">Descripción:</label>
            <textarea id="description" required />
          </div>
          <div>
            <label htmlFor="image">Imagen (URL):</label>
            <input type="text" id="image" required />
          </div>
          <div>
            <label htmlFor="category">Categoría:</label>
            <Dropdown
              value={selectedCategoryDialog}
              options={[
                { label: 'Todas las categorías', value: '' },
                ...categories.map((category) => ({ label: category.name, value: category.id_category })),
              ]}
              onChange={handleCategoryChangeDialog}
              placeholder="Select a category"
              id="categoryDialog"
              required
              style={{ fontSize: 'inherit' }}
            />
          </div>
          <div>
            <button type="submit">Añadir Producto</button>
          </div>
        </form>
      </Dialog>
    </main>
  );
}

export default Product;
