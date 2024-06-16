import React from 'react'
import Button from "@mui/material/Button"
import { Divider, Grid } from '@mui/material'
import TextEditor from '../common/TextEditor'
import CardHomeArticule from '../common/CardHomeArticule'
import { TitleSectionBlog } from '../common/ContainerFullWidth'
import ContainerFullWidth from '../common/ContainerFullWidth'
import {Articulos} from '../common/infoApi/Articulos'
const Home = () => {
  return (
    <>
   <Grid container>
    <CardHomeArticule/>
   </Grid>


   
      <ContainerFullWidth>
      <Grid sx={{width:'80%', margin: "40px auto"}}>
        <TitleSectionBlog>
          Articulos de blog
        </TitleSectionBlog>
        <Divider/>
        <Articulos/>
        </Grid> 

      </ContainerFullWidth>
   </>

    
  )
}

export default Home