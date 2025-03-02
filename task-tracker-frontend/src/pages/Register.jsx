import { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import "../styles/Register.css";

function Register() {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const navigate = useNavigate();

  const handleRegister = async(e) =>{
    e.preventDefault();
    try {
      const response = await axios.post("http://localhost:8080/users",{
        name, email, password
      });
      if(response.status === 201){
        alert("Cuenta creada con exito");
        navigate("/login");
      }   
    } catch (error) {
      setError("Error al registrar. Intenta de nuevo.");
      console.log("Register error:", error);
    }
  };

  return(
    <div className="register-container">
      <h2>Crear Cuenta</h2>
      {error && <p className="error">{error}</p>}
      <form onSubmit={handleRegister}>
        <input type="text" placeholder="Nombre" value={name} onChange={(e)=>setName(e.target.value)} required/>
        <input type="email" placeholder="Correo electronico" value={email} onChange={(e)=>setEmail(e.target.value)} required/>
        <input type="password" placeholder="Contraseña" value={password} onChange={(e)=>setPassword(e.target.value)} required/>
        <button className="signin" type="submit">Registrar</button>
        <button className="back" onClick={() => navigate("/login")}>Volver al login</button>
      </form>
    </div>
  );
}

export default Register;