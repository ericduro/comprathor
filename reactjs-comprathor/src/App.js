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

import { keycloak } from './Variables';

let initOptions = {
  url: keycloak,
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
    console.info("Authenticated");
    console.log('auth', auth)
    console.log('Keycloak', kc)
    console.log('Access Token', kc.token)
    
    httpClient.defaults.headers.common['Authorization'] = 'Bearer ' + kc.token;

    kc.onTokenExpired = () => {
      console.log('token expired')
    }
  }
}, () => {
  console.error("Authentication Failed")
});

function App() {  

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
