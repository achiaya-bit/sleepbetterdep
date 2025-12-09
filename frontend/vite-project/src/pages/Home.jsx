import { useEffect, useState } from "react";
import API from "../api";

const Home = () => {
  const [message, setMessage] = useState("");

  useEffect(() => {
    API.get("/api/test")
      .then((response) => {
        setMessage(response.data);
      })
      .catch((error) => {
        setMessage("Erreur API");
        console.error(error);
      });
  }, []);

  return (
    <div style={{ textAlign: "center", marginTop: "50px" }}>
      <h1>Bienvenue sur SleepBetter App !</h1>
      <p>Message du backend : {message}</p>
    </div>
  );
};

export default Home;

