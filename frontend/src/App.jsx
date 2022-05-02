import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { createGlobalStyle, ThemeProvider } from "styled-components";
import Home from "./pages/Home";
import Signin from "./pages/Signin";
import { Theme } from "./Theme";

const Global = createGlobalStyle`
    * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
        font-family: 'Encode Sana Expanded',sans-serif;
    }
`;

function App() {
  return (
    <Router>
      <ThemeProvider theme={Theme}>
        <Global />
        <Routes>
          <Route path="/" element={<Home />} exact />
          <Route path="/signin" element={<Signin />} exact />
        </Routes>
      </ThemeProvider>
    </Router>
  );
}

export default App;
