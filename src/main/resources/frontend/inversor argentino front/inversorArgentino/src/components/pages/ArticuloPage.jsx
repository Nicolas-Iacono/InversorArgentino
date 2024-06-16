import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import axios from 'axios';
import parse from 'html-react-parser';
import ContainerFullWidth from '../common/ContainerFullWidth';
import { Grid } from '@mui/material';
const ArticuloPage = () => {
  const { id } = useParams();
  const [article, setArticle] = useState(null);
  console.log("articulo con " + id)
  useEffect(() => {
    // Reemplaza con la URL de tu API
    axios.get(`http://localhost:8080/api/articulos/${id}`)
    .then(response => {
      // Decodificar el contenido HTML escapado
      const decodedContent = decodeHtml(response.data.parrafo);
      setArticle({ ...response.data, parrafo: decodedContent });
    })
      .catch(error => console.error('Error fetching article:', error));
  }, [id]);


  if (!article) {
    return <div>Cargando...</div>;
  }
console.log(article)
  return (
    <ContainerFullWidth>

      <Grid sx={{width:"80%", margin: "0 auto"}}>{parse(article.parrafo)}</Grid>
    </ContainerFullWidth>
  );
};
const decodeHtml = (html) => {
  const textArea = document.createElement('textarea');
  textArea.innerHTML = html;
  return textArea.value;
};
export default ArticuloPage;
