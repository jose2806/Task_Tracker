import { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from 'axios'
import '../styles/login.css';

function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const navigate = useNavigate();


  const handleLogin = async(e) =>{
    e.preventDefault();
    setError("");
    try {
      const response = await axios.post("http://localhost:8080/login",{
        email, password
      });
      if(response.data.jwtToken){    
        localStorage.setItem("token", response.data.jwtToken);
        navigate("/home");
      } else {
        setError("Credenciales incoreectas");
      }
    } catch (error) {
      setError("Error al iniciar sesión. Verifica tus datos.");
      console.log("Login error:", error);      
    }
  };
   

  return(
    <div className="login-container">
      <h2>Iniciar Sesion</h2>
      {error && <p className="error">{error}</p>}
      <form onSubmit={handleLogin}>
        <input type="email" placeholder="Correo electronico" value={email}
        onChange={(e) => setEmail(e.target.value)} required />
        <input type="password" placeholder="Contraseña" value={password}
        onChange={(e) => setPassword(e.target.value)} required />
        <button  className="login" type="submit">Ingresar</button>
        <button className="signin" onClick={() => navigate("/register")}>Crear Cuenta</button>
      </form>   
    </div>
  );
}

export default Login;