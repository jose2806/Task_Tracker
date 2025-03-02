import { useState } from "react";
import axios from "axios";
import {useNavigate} from 'react-router-dom'
import "../styles/AddTask.css";

function AddTask() {
  const[description, setDescription] = useState("");
  const navigate = useNavigate();

  const handleSubmit = async(e) =>{
    e.preventDefault();
    const token = localStorage.getItem("token");
    try {
      await axios.post("http://localhost:8080/tasks",{description},
        {headers: {
          Authorization: `Bearer ${token}`,"Content-Type":"application/json",
        }});
        navigate("/home");
    } catch (error) {
      console.error("Error al agregra tarea",error);  
    }
  }

  return(
    <div className="form-container">
        <h1>Agregar Nueva Tarea</h1>
        <form onSubmit={handleSubmit}>
          <input type="text" value={description} 
          onChange={(e) => setDescription(e.target.value)}
           placeholder="Descripcion" required />
          <button className="add-btn" type="submit">Agregar</button>
        </form>     
    </div>
  );  
}

export default AddTask;