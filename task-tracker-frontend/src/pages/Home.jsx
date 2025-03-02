import { useEffect, useState } from "react";
import axios from "axios";
import {useNavigate} from 'react-router-dom';
import "../styles/Home.css";


function Home() {
  const [tasks,setTasks] = useState([]);
  const navigate = useNavigate();

  useEffect(()=>{
    const token = localStorage.getItem("token");
    axios.get("http://localhost:8080/tasks",{
      headers: { Authorization: `Bearer ${token}`,"Content-Type":"application/json"}})
    .then(response => setTasks(response.data.content))
    .catch(error => console.error("Error cargando tareas", error))
    
  },[]);
  
  const handleDelete = async (id) =>{
    const token = localStorage.getItem("token");
    if (window.confirm("¿Estás seguro de que deseas eliminar esta tarea?")) {
      try {
        await axios.delete(`http://localhost:8080/tasks/${id}`,{
          headers:{Authorization: `Bearer ${token}`}});
        setTasks(tasks.filter((task)=> task.id !== id));
      } catch (error) {
        console.log("Error eliminando tarea", error);
      }
    }
  };

  return(
    <div className="container">
      <h1>Lista de Tareas</h1>
      { tasks.map(task =>(
        <div key={task.id} className="task">
          <h3>{task.description}</h3>
          <p>Estado: {task.status}</p>
          <button className="edit-btn" onClick={() => navigate(`/edit/${task.id}`)}> ✏️ Editar</button>
          <button onClick={() => handleDelete(task.id)} className="delete-btn">🗑️ Eliminar</button>
        </div>
      ))}
    </div>
  );
}

export default Home;