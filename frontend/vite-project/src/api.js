import axios from "axios";

// Base URL de ton backend
const API = axios.create({
  baseURL: import.meta.env.VITE_API_URL || "http://localhost:8080"
});

export default API;
