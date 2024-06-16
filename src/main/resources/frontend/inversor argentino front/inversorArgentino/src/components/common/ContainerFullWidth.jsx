import { Grid, TextField, Typography, styled, Button } from '@mui/material'
import React from 'react'

export const ContainerFullWidth = styled(Grid)(() => ({
  width:"100%",

})) 
  

export default ContainerFullWidth

export const ParagraphCard = styled(Typography)(() => ({
  fontSize:"15px",
  color:"#7079A8"

})) 
export const TitleCard = styled(Typography)(() => ({
  fontSize:"20px",
  color:"#020051"

})) 

export const TitleSectionBlog = styled(Typography)(() => ({
  fontSize:"30px",
  color:"Black"

})) 
export const AgregarArt = styled(Button)(() => ({
  fontSize:"6px",
  color:"black",
  width:"20px",
  height:"50px",
  borderRadius:"50%",
  border:"3px solid blue",
  ":hover":{
    backgroundColor:"#DB2B2E",
    color:"white"
  }
})) 

export const InputArticulo = styled(TextField)(() => ({
  fontSize:"10px",
  height:'100%',
  color:"black",
  width:"20%",
  height:"40px",
  borderRadius:"10px",
  backgroundColor:"white",
  placeHolder:"Titulo",
  ":hover":{
    color:"white"
  }
})) 

export const BtnRegister = styled(Button)(() => ({
  fontSize:"15px",
  height:'100%',
  color:"white",
  width:"30%",
  height:"50px",
  borderRadius:"5px",
  backgroundColor:"#1338B4",
  placeHolder:"Titulo",
  ":hover":{
    color:"white",
    backgroundColor:"#123199"
  }
})) 