import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import axios from "axios";
import "../styles/EditTask.css";


function EditTask() {
  const {id} = useParams();
  const [description, setDescription] = useState("");
  const [status, setStatus] = useState("TODO");
  const navigate = useNavigate();
  const [error, setError] = useState("");

  useEffect(() =>{
    const token = localStorage.getItem("token");
    axios.get(`http://localhost:8080/tasks/${id}`,{
      headers: { Authorization: `Bearer ${token}`,"Content-Type":"application/json"}})
    .then(response =>{
      setDescription(response.data.description);
      setStatus(response.data.status);
    })
    .catch(error => {
      console.error("Error cargando tarea", error);
      setError("No se pudo cargar la tarea. Verifica tu sesión.");
    });
  },[id]);

  const handleSubmit = async(e) =>{
    e.preventDefault();
    setError("");
    const token = localStorage.getItem("token");
    try{
      await axios.put(`http://localhost:8080/tasks/${id}`,
      {id,description,status},
      { headers: { Authorization: `Bearer ${token}`,"Content-Type":"application/json"}});
    navigate("/home");
    } catch(error) {
      console.error("Error actualizando tarea", error);
      setError("Error al actualizar tarea. Verifica tu conexión.");
    };
  };

  return(
    <div className="form-container">
      <h1>Editar Tarea</h1>
      {error && <p className="error">{error}</p>}
      <form onSubmit={handleSubmit}>
        <input type="text" value={description} onChange={(e) => setDescription(e.target.value)} required />
        <select value={status} onChange={(e) => setStatus(e.target.value)}>
          <option value="TODO">TODO</option>
          <option value="IN_PROGRESS">IN_PROGRESS</option>
          <option value="DONE">DONE</option>
        </select>
        <button type="submit">Actualizar</button>
      </form>
    </div>
  );
}

export default EditTask;