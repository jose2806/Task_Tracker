import axios from "axios";

const API_URL = "http://localhost:8080/tasks";

export const getTasks = async () =>{
  try{
    const response = await axios.get(API_URL);
    return response.data;
  } catch(error){
    console.log("Error al obtener tareas", error);
    throw error;
  }
};