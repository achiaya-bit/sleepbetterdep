import React, { useEffect, useState } from "react";
import axios from "axios";

const Home = () => {
  const [message, setMessage] = useState("");

  useEffect(() => {
    axios.get("http://localhost:8080/api/test") // route backend
      .then((response) => {
        setMessage(response.data);
      })
      .catch((error) => {
        console.error("Erreur API:", error);
      });
  }, []);

  return (
    <div style={{ textAlign: "center", marginTop: "50px" }}>
      <h1>Bienvenue sur SleepBetter App !</h1>
      <p>Message du backend: {message}</p>
    </div>
  );
};

export default Home;
