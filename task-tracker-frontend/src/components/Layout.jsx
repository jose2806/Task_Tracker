import { Routes, Route, useLocation } from "react-router-dom";
import Login from "../pages/Login";
import Home from "../pages/Home";
import Navbar from "./Navbar"
import Add from "../pages/AddTask";
import Register from "../pages/Register";
import Edit from "../pages/EditTask";

function Layout() {
  const location = useLocation();

  return (
    <>
      {location.pathname !== "/login" && location.pathname !== "/" 
      && location.pathname !== "/register" && <Navbar />}
      <Routes>
        <Route path="/" element={<Login/>}/>
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/home" element={<Home />} />
        <Route path="/add" element={<Add />} />
        <Route path="/edit/:id" element={<Edit />} />
      </Routes>
    </>
  );
}

export default Layout;