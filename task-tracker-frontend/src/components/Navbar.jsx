import { Link, useNavigate } from "react-router-dom";
import "../styles/Navbar.css"

function Navbar() {
  const navigate = useNavigate();

  const handleLogout = () =>{
    localStorage.removeItem("token");
    navigate("/login");
  };

  return(
    <nav className="navbar">
      <Link to="/home">Home</Link>
      <Link to="/add">Agregar Tarea</Link>
      <button onClick={handleLogout} className="logout-button">Cerrar Sesión</button>
    </nav>
  );
}

export default Navbar;