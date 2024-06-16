import React, { useState, useEffect } from 'react';
import ContainerFullWidth from '../common/ContainerFullWidth';
import { Grid, Box, Typography, Button } from '@mui/material';
import { AgregarArt } from '../common/ContainerFullWidth';
import TextEditor from '../common/TextEditor';
import { InputArticulo } from '../common/ContainerFullWidth';
import AddRoundedIcon from '@mui/icons-material/AddRounded';
import PersonIcon from '@mui/icons-material/Person';
import ListAltIcon from '@mui/icons-material/ListAlt';
import { UseEditorGlobalContext } from '../context/EditorGlobal';
import { postArticulo } from '../api/articulos';
import AsideAdmin from '../layout/AsideAdmin';
import { UseAsideContextGlobal } from '../context/AsideContext';
import {BlogPerfil} from "../pages/BlogPerfil"
import BlogLista from './BlogLista';
export const Blog = () => {
 

  const { parrafo, addParagraph } = UseEditorGlobalContext();

  const [articulo, setArticulo] = useState({
    titulo: "",
    subtitulo: "",
    imagenes: '',
    parrafo: "",
    autor: 1,
  });

  const {nuevoArticulo,perfil,lista} = UseAsideContextGlobal();

  useEffect(() => {
    setArticulo(prevState => ({ ...prevState, parrafo: parrafo }));
  }, [parrafo]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setArticulo(prevState => ({
      ...prevState,
      [name]: value
    }));
  };

  const clickSubmit = async () => {
    await postArticulo(articulo);
    console.log('Artículo guardado:', articulo);
  };

  return (
    <ContainerFullWidth sx={{ backgroundColor: "white", height: "90vh", display: "flex" }}>
    
        {nuevoArticulo ? (
          <Box sx={{ padding: "30px", width:"100%", display:"flex", flexDirection:"column" }}>
            <form>
              <Grid sx={{ height: "50px", width: "100%", display: "flex", justifyContent: "flex-start", alignItems: "center", padding: "10px", gap: "10px", marginBottom:"20px" }}>
                <InputArticulo
                  placeholder='Título'
                  name="titulo"
                  value={articulo.titulo}
                  onChange={handleChange}
                />
                <InputArticulo
                  placeholder='Subtítulo'
                  name="subtitulo"
                  value={articulo.subtitulo}
                  onChange={handleChange}
                />
                <InputArticulo
                  placeholder='Imagen Portada'
                  name="imagenes"
                  value={articulo.imagenes}
                  onChange={handleChange}
                />
              </Grid>
              <TextEditor />
              <Button
                onClick={clickSubmit}
                variant="contained"
                color="primary"
                sx={{ marginTop: "10px", }}
              >
                Guardar Artículo
              </Button>
            </form>
          </Box>
        ) : (
          <></>
        )}
        {
          perfil ? (
              <BlogPerfil/>
          ):(
            <></>
          )}
          {
            lista ? (
              <BlogLista/>
            ) : (
              <></>
            )
          }


    </ContainerFullWidth>
  );
};

export default Blog;
