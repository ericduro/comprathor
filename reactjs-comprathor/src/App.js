import React from 'react';
import Navbar from "./Components/Navbar"
import "primereact/resources/themes/lara-light-indigo/theme.css";
import "primereact/resources/primereact.min.css";
import '/node_modules/primeflex/primeflex.css'
import './App.css';
import { BrowserRouter, Routes, Route} from 'react-router-dom';

import Home from "./Pages/Home"
import Product from "./Pages/Product"
import Comparative from "./Pages/Comparison"
import User from "./Pages/User"
import PageNotFound from "./Pages/PageNotFound"


import { httpClient } from './HttpClient';

import Keycloak from 'keycloak-js';



let initOptions = {
  url: 'http://localhost:9090/',
  realm: 'SpringBootKeycloak',
  clientId: 'login-app',
}

export const kc = new Keycloak(initOptions);

kc.init({
  onLoad: 'login-required',
  checkLoginIframe: true,
  pkceMethod: 'S256',
}).then((auth) => {
  if (!auth){
    window.location.reload();
  } else {
    //console.info("Authenticated");
    //console.log('auth', auth)
    //console.log('Keycloak', kc)
    //console.log('Access Token', kc.token)
    
    httpClient.defaults.headers.common['Authorization'] = 'Bearer ' + kc.token;

    kc.onTokenExpired = () => {
      console.log('token expired')
    }
  }
}, () => {
  console.error("Authentication Failed")
});

function App() {
  

  /*useEffect(() => {
    // Llama al método getAllProducts al montar el componente
    productApi.getAllProducts((error, data) => {
      if (error) {
        console.error('Error al obtener todos los productos:', error);
      } else {
        console.log('Productos obtenidos con éxito:', data);
        setProducts(data); // Actualiza el estado con los productos obtenidos
      }
    });
  }, []);*/

  /*const callBackend = async () => {
    try {
      // Realiza una solicitud directa al backend utilizando fetch
      const response = await fetch('http://localhost:8080/category/all', {
        method: 'GET',
        headers: {
          'Authorization': `Bearer ${kc.token}`
        },
      });

      //console.log('Token de Acceso:', kc.token);
      //console.log('Encabezado de Autorización:', 'Bearer ' + kc.token);
      //console.log('Roles del Token:', kc.tokenParsed.realm_access.roles);
      //console.log('Roles del Token:', kc.tokenParsed.realm_access.roles);

      if (response.ok) {
        // Si la respuesta es exitosa, obtén los datos y muestra en la consola
        const data = await response.json();
        console.log('Respuesta del servidor:', data);
      } else {
        // Si la respuesta no es exitosa, muestra un mensaje de error
        console.error('Error en la respuesta del servidor:', response.statusText);
      }
    } catch (error) {
      // Maneja errores de la solicitud
      console.error('Error al realizar la solicitud:', error);
    }
  };*/
  

  return (
    <React.Fragment>
    <BrowserRouter>
    <Navbar />
        <Routes>
            <Route path="/" element={<Home />}/>
            <Route path="/Product" element={<Product />}/>
            <Route path="/Comparative" element={<Comparative />}/>
            <Route path="/User" element={<User />}/>
            <Route path="/*" element={<PageNotFound />}/>    
            </Routes>
    </BrowserRouter>
    </React.Fragment>
  );
}

export default App;
