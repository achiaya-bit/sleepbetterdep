import axios from "axios";

// Base URL de ton backend
const API = axios.create({
  baseURL: "http://localhost:8080", // change si ton backend est sur un autre port
});

export default API;
