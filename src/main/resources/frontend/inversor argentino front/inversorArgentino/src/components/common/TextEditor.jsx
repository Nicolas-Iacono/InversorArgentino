import React, { useState } from 'react';
import { Editor } from '@tinymce/tinymce-react';
import { Button, Grid } from '@mui/material';
import { UseEditorGlobalContext } from '../context/EditorGlobal';
import ContainerFullWidth from './ContainerFullWidth';

const TextEditor = () => {
  const { parrafo, addParagraph } = UseEditorGlobalContext();
  const [contenido, setContenido] = useState(parrafo);

  const handleEditorChange = (content) => {
    setContenido(content);
  };

  const handlerSubmit = (e) => {
    e.preventDefault();
    addParagraph(contenido);
  };

  return (
    <ContainerFullWidth sx={{height:"400px",}}>
      <Editor
        apiKey="yk10ygeb6q71ucxlc2kqvhzpliekkdjmjgw8bxrxbxmvbl6y"
        initialValue="<h1>Nuevo articulo</h1>"
        init={{
          width: "100%",
          height: 400,
          menubar: true,
          plugins: [
            'advlist autolink lists link image charmap print preview anchor',
            'searchreplace visualblocks code fullscreen',
            'insertdatetime media table paste code help wordcount',
            'image'
          ],
          toolbar:
            'undo redo | formatselect | bold italic backcolor | ' +
            'alignleft aligncenter alignright alignjustify | ' +
            'bullist numlist outdent indent | removeformat | help | ' +
            'image',
          image_title: true,
          automatic_uploads: true,
          file_picker_types: 'image',
          file_picker_callback: function (cb, value, meta) {
            var input = document.createElement('input');
            input.setAttribute('type', 'file');
            input.setAttribute('accept', 'image/*');
            input.onchange = function () {
              var file = this.files[0];
              var reader = new FileReader();
              reader.onload = function () {
                var id = 'blobid' + (new Date()).getTime();
                var blobCache = tinymce.activeEditor.editorUpload.blobCache;
                var base64 = reader.result.split(',')[1];
                var blobInfo = blobCache.create(id, file, base64);
                blobCache.add(blobInfo);
                cb(blobInfo.blobUri(), { title: file.name });
              };
              reader.readAsDataURL(file);
            };
            input.click();
          }
        }}
        onEditorChange={handleEditorChange}
      />
      <Grid sx={{ width: "100%", justifyContent: "flex-end", display: 'flex', padding: "10px" }}>
        <Button onClick={handlerSubmit} variant="contained" color="primary">
          Guardar
        </Button>
      </Grid>
    </ContainerFullWidth>
  );
};

export default TextEditor;
