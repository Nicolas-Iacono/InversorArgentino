import React from 'react'
import { Grid, Box } from '@mui/material'
import { useGetFetch } from '../api/useFetch';
import { getLastFourArticulos } from '../api/articulos';
import { ParagraphCard } from './ContainerFullWidth';
import { TitleCard } from './ContainerFullWidth';
export const CardCarrusel =  ({ articulo }) => {



  return (
    <Grid sx={{width:"100%",height:"300px",  display:"flex", justifyContent:"center", alignItems:"center", marginTop:"30px"}}>
    <Grid sx={{display:"flex", width:"80%", margin: "0 auto", height:"100%" }}>
        <Box sx={{ width:"70%"}}>
          <img src={articulo.imagenes} alt="" width="100%" height="100%"/>
        </Box>
        <Box sx={{backgroundColor:"#E8E8EF", width:"40%", padding:"20px"}}>
          <TitleCard>{articulo.titulo}</TitleCard>
          <ParagraphCard>{articulo.subtitulo}</ParagraphCard>
        </Box>
     
    </Grid>
  </Grid>
  )
}

export default CardCarrusel