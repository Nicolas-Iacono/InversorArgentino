import React, { useState, useEffect } from 'react';
import ContainerFullWidth from '../common/ContainerFullWidth';
import { Grid, Box, Typography, Button, IconButton } from '@mui/material';
import { AgregarArt } from '../common/ContainerFullWidth';
import TextEditor from '../common/TextEditor';
import { InputArticulo } from '../common/ContainerFullWidth';
import AddRoundedIcon from '@mui/icons-material/AddRounded';
import PersonIcon from '@mui/icons-material/Person';
import ListAltIcon from '@mui/icons-material/ListAlt';
import { UseEditorGlobalContext } from '../context/EditorGlobal';
import { postArticulo } from '../api/articulos';
import { Outlet } from 'react-router-dom';
import { AsideContextProvider, UseAsideContextGlobal } from '../context/AsideContext';
export const AsideAdmin = () => {

  const {nuevoArticulo, handleClickNuevo, perfil, handleClickPerfil, lista, handleClickLista} = UseAsideContextGlobal()



  return (
    <ContainerFullWidth sx={{  height: "90vh", display: "flex" }}>
    <Grid item sx={{ width: '5%', height: "100%", display: "flex", alignItems: "center", flexDirection: "column", gap: '30px', borderRight:"1px solid grey", justifyContent:"center", gap:"50px"}}>
      <IconButton onClick={handleClickNuevo}>
        <AddRoundedIcon />
      </IconButton>
      <IconButton onClick={handleClickPerfil}>
        <PersonIcon />
      </IconButton>
      <IconButton onClick={handleClickLista}>
        <ListAltIcon />
      </IconButton>
    </Grid>
    <Outlet/>
  </ContainerFullWidth>
  )
}

export default AsideAdmin