import { useGetFetch, postFetch, putFetch } from "./useFetch";

const URL_ARTICULOS = 'http://localhost:8080/api/articulos'
const URL_LAST_FOUR_ARTICLES =  'http://localhost:8080/api/articulos/ultimos-cuatro'

export const getArticulos = () =>{
  return useGetFetch(URL_ARTICULOS)
}

export const getLastFourArticulos = () =>{
  return useGetFetch(URL_LAST_FOUR_ARTICLES)
}

export const postArticulo = (payload) => { 
  return postFetch(URL_ARTICULOS, payload)
}


