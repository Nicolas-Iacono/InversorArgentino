import React, { createContext, useContext, useState } from "react";

const AsideContextGlobal = createContext();

export const UseAsideContextGlobal = () => {
  return useContext(AsideContextGlobal);
};

export const AsideContextProvider = ({ children }) => {
  const [nuevoArticulo, setNuevoArticulo] = useState(false);
  const [perfil, setPerfil] = useState(false);
  const [lista, setLista] = useState(false);

  const handleClickNuevo = () => {
    setNuevoArticulo(!nuevoArticulo);
    if(perfil || lista == true) {
      setLista(false)
      setPerfil(false)
    }
  };
  const handleClickPerfil = () => {
    setPerfil(!perfil);
    if(nuevoArticulo || lista == true) {
      setLista(false)
      setNuevoArticulo(false)
    }

  };
  const handleClickLista = () => {
    setLista(!lista);
    if(perfil || nuevoArticulo == true) {
      setPerfil(false)
      setNuevoArticulo(false)
    }
  }
  return (
    <AsideContextGlobal.Provider value={{ nuevoArticulo, handleClickNuevo, perfil, handleClickPerfil, lista, handleClickLista }}>
      {children}
    </AsideContextGlobal.Provider>
  );
};
