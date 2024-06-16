import { Grid, Box} from '@mui/material'
import React from 'react'
import {ContainerFullWidth} from "../common/ContainerFullWidth"
import Avatar from '@mui/material/Avatar';
export const BlogPerfil = () => {
  return (
    
    <ContainerFullWidth sx={{backgroundColor:"red", display:'flex'}}>
          <Grid sx={{width:"20%", backgroundColor:"violet", height:"100%", display:"flex" ,flexDirection:"column", justifyContent: "start", alignItems:"center",}}>
          <Avatar alt="Remy Sharp" src="/static/images/avatar/1.jpg" />
              <h1>Nicolas Iacono</h1>
          </Grid>
          <Grid sx={{width:"80%",backgroundColor:"blue", height:"100%"}}>
              <h1>cantidad de blogs escritos: </h1>
          </Grid>
    </ContainerFullWidth>
  )
}

export default BlogPerfil