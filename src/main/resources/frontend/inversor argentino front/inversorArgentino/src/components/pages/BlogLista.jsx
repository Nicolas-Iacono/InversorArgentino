import React, { useState, useEffect } from 'react';
import axios from 'axios';
import {
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Paper,
  Button,
  IconButton
} from '@mui/material';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import VisibilityIcon from '@mui/icons-material/Visibility';
import styles from "../styles/tabla.module.css"
import { Navigate, useNavigate } from 'react-router-dom';
export const BlogLista = () => {
  const [articles, setArticles] = useState([]);
  const navigate = useNavigate();
  useEffect(() => {
    fetchArticles();
  }, []);

  const fetchArticles = async () => {
    try {
      const response = await axios.get('http://localhost:8080/api/articulos');
      setArticles(response.data);
    } catch (error) {
      console.error('Error fetching articles:', error);
    }
  };
  console.log(articles)
  const handleUpdate = (id) => {
    // Lógica para actualizar el artículo
    console.log('Actualizar artículo', id);
  };

  const handleDelete = async (id) => {
    try {
      await axios.delete(`http://localhost:8080/api/articulos/${id}`);
      setArticles(articles.filter(article => article.id !== id));
      console.log('Eliminar artículo', id);
    } catch (error) {
      console.error('Error eliminando el artículo:', error);
    }
  };

  const handleView = (id) => {
    // Lógica para ver el artículo
    navigate(`/api/articulos/${id}`)
    console.log('Ver artículo', id );
  };

  return (

    
    <TableContainer className={styles.noScrollbar} component={Paper} sx={{width:"100%", height:"100%", margin:"0 auto"}}>
      <Table>
        <TableHead sx={{backgroundColor:"black", color:"white"}}>
          <TableRow>
            <TableCell sx={{textAlign:"center", color:"white"}}>Portada</TableCell>
            <TableCell sx={{ color:"white"}}>Título</TableCell>
            <TableCell sx={{ color:"white"}}>Subtítulo</TableCell>
            <TableCell sx={{textAlign:"center",  color:"white"}}>Acciones</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {articles.map((article) => (
            <TableRow key={article.id}>
              <TableCell> <img src={article.imagenes} alt={article.title}  width="100px"/></TableCell>
              <TableCell>{article.titulo}</TableCell>
              <TableCell>{article.subtitulo}</TableCell>
              <TableCell sx={{width:"170px"}}>
                <IconButton onClick={() => handleView(article.id)} color="primary">
                  <VisibilityIcon />
                </IconButton>
                <IconButton onClick={() => handleUpdate(article.id)} color="primary">
                  <EditIcon />
                </IconButton>
                <IconButton onClick={() => handleDelete(article.id)} color="secondary">
                  <DeleteIcon />
                </IconButton>
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
};

export default BlogLista;
