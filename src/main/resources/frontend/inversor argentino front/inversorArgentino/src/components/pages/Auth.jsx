import React from "react";
import ContainerFullWidth from "../common/ContainerFullWidth";
import { Grid,Button, TextField, Paper, Box } from "@mui/material";
import { validationSchema } from "../common/formValidation/Validation";
import { validationLoginSchema } from "../common/formValidation/Validation";
import { Formik, Form, Field, ErrorMessage } from 'formik';
import { useState } from "react";
import { BtnRegister } from "../common/ContainerFullWidth";
const Auth = () => {
    const[login, setLogin] = useState(false)

  const formChange = () =>{
    setLogin(!login)
  }
  const initialValuesLogin = {
    email: '',
    password: '',
  };

  const initialValuesRegister = {
    username: "",
    email: "",
    password: "",
    confirmPassword: "",
  };
  const handleSubmit = (e,values) => {
    e.preventDefault();
    console.log("Form data", values);
    // Aquí puedes manejar el envío del formulario, como enviar los datos a una API
  };

  const handleSubmitLogin = (values) => {
    console.log('Form data', values);
    // Aquí puedes manejar el envío del formulario, como enviar los datos a una API
  };

  return (
    <ContainerFullWidth
      sx={{ height: "100vh", display: "flex" }}
    >
      <Grid item sx={{ width: "60%", backgroundColor:"black", color:"white", display:"flex", justifyContent:"center", alignItems:"center"}}>
        <h1>El inversor Argentino</h1>
      </Grid>
      <Grid item sx={{ width: "40%", }}>

      <div>
      <h1>Registro</h1>

      {
        login ? (

<Formik
        initialValues={initialValuesRegister}
        validationSchema={validationSchema}
        onSubmit={handleSubmit}
      >
        {({ isSubmitting }) => (
          <Form style={{display:"flex",flexDirection:"column", justifyContent:"space-around", alignItems:"center", gap:"40px" }}>
            <Box sx={{display:"flex", flexDirection:"column" ,width:"60%"}}>
              <label htmlFor="username">Nombre de Usuario</label>
              <TextField type="text" id="username" name="username" />
              <ErrorMessage name="username" component="div" />
            </Box>
            <Box sx={{display:"flex", flexDirection:"column" ,width:"60%"}}>
              <label htmlFor="email">Correo Electrónico</label>
              <TextField type="email" id="email" name="email" />
              <ErrorMessage name="email" component="div" />
            </Box>
            <Box sx={{display:"flex", flexDirection:"column" ,width:"60%"}}>
              <label htmlFor="password">Contraseña</label>
              <TextField type="password" id="password" name="password" />
              <ErrorMessage name="password" component="div" />
            </Box>
            <Box sx={{display:"flex", flexDirection:"column",  width:"60%"}}>
              <label htmlFor="confirmPassword">Confirmar Contraseña</label>
              <TextField type="password" id="confirmPassword" name="confirmPassword" />
              <ErrorMessage name="confirmPassword" component="div" />
            </Box>
            <BtnRegister type="submit" disabled={isSubmitting}>
              Registrarse
            </BtnRegister>
          </Form>
        )}
      </Formik>

        ):(
          <>
          <h1>login</h1>
          <Formik
          initialValues={initialValuesLogin}
          validationSchema={validationLoginSchema}
          onSubmit={handleSubmitLogin}
        >
          {({ isSubmitting }) => (
            <Form>
              <div>
                <label htmlFor="email">Correo Electrónico</label>
                <Field type="email" id="email" name="email" />
                <ErrorMessage name="email" component="div" />
              </div>
              <div>
                <label htmlFor="password">Contraseña</label>
                <Field type="password" id="password" name="password" />
                <ErrorMessage name="password" component="div" />
              </div>
              <button type="submit" disabled={isSubmitting}>
                Iniciar sesión
              </button>
            </Form>
          )}
        </Formik>
        </>
        )

      }
      {
        
        login ? (
          <Button onClick={formChange}>Ya tengo una cuenta</Button>
        ):(
<Button onClick={formChange}>Necito una cuenta</Button>
        )
      }
    </div>
      </Grid>
    </ContainerFullWidth>
  );
};

export default Auth;
