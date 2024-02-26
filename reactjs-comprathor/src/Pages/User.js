import '../Styles/main.css'

import React, { useState } from 'react';
import { kc } from '../App';

import { Button } from 'primereact/button';
import { Card } from 'primereact/card';

import { frontend } from '../Variables';

function User(){

  const [infoMessage, setInfoMessage] = useState(''); 

    return (
      
      <div className="App">
        <div className='grid'>
          <div className='col-12'>
            <h1>Gestión de usuario</h1>
          </div>
        </div>

        <div className='grid'>
          <div className='col-1'></div>
          <div className='col-2'>
            <div className="col">
              <Button onClick={() => { setInfoMessage(kc.authenticated ? 'Authenticated: TRUE' : 'Authenticated: FALSE') }}
                className="m-1 custom-btn-style"
                label='Usuario autenticado?' />

              <Button onClick={() => { setInfoMessage(kc.hasRealmRole('admin').toString()) }}
                className="m-1 custom-btn-style"
                label='Tiene rol administrador?'
                severity="info" />
              

              <Button onClick={() => { setInfoMessage(kc.token) }}
                className="m-1 custom-btn-style"
                label='Mostrar token de acceso'
                severity="info" />

              <Button onClick={() => { setInfoMessage(JSON.stringify(kc.tokenParsed)) }}
                className="m-1 custom-btn-style"
                label='Mostrar análisis del token de acceso'
                severity="warning" />

              <Button onClick={() => { kc.updateToken(10).then((refreshed) => { setInfoMessage('Se acaba de refrescar el token: ' + refreshed.toString()) }, (e) => { setInfoMessage('Refresh Error') }) }}
                className="m-1 custom-btn-style"
                severity="warning"
                label='Refrescar token (si ha expirado o está a punto de expirar)' />  {/** 10 seconds */} 
              

              
              <Button onClick={() => { kc.logout({ redirectUri: frontend }) }}
                className="m-1 custom-btn-style"
                label='Cerrar sesión'
                severity="danger" />

            </div>
          </div>
          <div className='col-6'>

            <Card>
              <p style={{ wordBreak: 'break-all' }} id='infoPanel'>
                {infoMessage}
              </p>
            </Card>
          </div>

          <div className='col-2'></div>
        </div>

      </div>
      
    )
}

export default User;