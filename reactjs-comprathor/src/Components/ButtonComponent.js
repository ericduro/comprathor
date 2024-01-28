import React from 'react';
import { Button } from 'primereact/button';

function CustomButton({ onClick, label, severity }) {
  return (
    <Button onClick={onClick} label={label} severity={severity} />
  );
}

export default CustomButton;
