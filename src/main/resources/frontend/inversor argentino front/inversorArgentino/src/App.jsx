import Home from "../src/components/pages/Home";
import "./App.css";
import { Route, Routes, BrowserRouter } from "react-router-dom";
import { Layout } from "./components/layout/Layout";
import Blog from "../src/components/pages/Blog";
import { EditorTextContextProvider } from "./components/context/EditorGlobal";
import AsideAdmin from "./components/layout/AsideAdmin";
import { AsideContextProvider } from "./components/context/AsideContext";
import ArticuloPage from "./components/pages/ArticuloPage";
import Auth from "./components/pages/Auth";
function App() {
  return (
    <EditorTextContextProvider>
      <BrowserRouter>
        <AsideContextProvider>
          <Routes>
            <Route element={<Layout />}>
              <Route path="/" element={<Home />} />
              <Route element={<AsideAdmin />}>
                <Route path="/blog" element={<Blog />} />
              </Route>
              <Route path="api/articulos/:id" element={<ArticuloPage />} />
            </Route>
            <Route path="/auth" element={<Auth/>}/>
          </Routes>
        </AsideContextProvider>
      </BrowserRouter>
    </EditorTextContextProvider>
  );
}

export default App;
