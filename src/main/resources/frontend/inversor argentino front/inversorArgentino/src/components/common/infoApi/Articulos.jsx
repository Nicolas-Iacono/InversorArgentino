import React, { useState, useEffect } from 'react';
import { useGetFetch } from  '../../api/useFetch';
import { Box, Grid, Typography } from '@mui/material';
import ContainerFullWidth from '../ContainerFullWidth';
import { getArticulos } from '../../api/articulos';

export const Articulos = () => {

  const {data: articulos, error, isLoading} = getArticulos();

console.log(articulos)
  if (isLoading) {
    return <div>Cargando...</div>;
  }

  if (error) {
    return <div>Error al cargar los artículos</div>;
  }

  return (
    <ContainerFullWidth>
      <Grid sx={{display:'flex', flexWrap:'wrap', gap:'10px'}}>

      {articulos && Array.isArray(articulos) && articulos.length > 0 ? (
        articulos.map((articulo, index) => (
          <Grid key={index} sx={{ width: "260px", marginTop:'20px'}}>
            <Box sx={{ width: "260px" }}>
              <img src={articulo.imagenes} alt={articulo.titulo} style={{width:"260px"}}  />
            </Box>
            <Typography variant="h6">
              {articulo.titulo}
            </Typography>
            <Typography variant="body1">
              {articulo.subtitulo}
            </Typography>
           
          </Grid>
        ))
      ) : (
        <div>No hay artículos disponibles</div>
      )}
      </Grid>

    </ContainerFullWidth>
  );
};

export default Articulos;