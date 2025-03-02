import React, { useState } from 'react';
import { TextField, Typography, Box, Button, AppBar, Toolbar } from '@mui/material';
import logo from '../images/logo.png';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import { useUser } from '../UserContext';

function Login() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [message, setMessage] = useState('');
  const navigate = useNavigate();
  const { login } = useUser();

 const handleSubmit = async (e) => {
    e.preventDefault();
    console.log("Tentative de connexion avec :", { email, password });

    try {
        const response = await axios.post("http://localhost:8088/api/auth/login",
            { email, password },
            { headers: { 
                "Content-Type": "application/json",
                "Accept": "application/json",
                "Authorization": localStorage.getItem("token") || "" // Si besoin
            }} 
        );

        console.log("Réponse du serveur :", response);

        if (response.status === 200) {
            localStorage.setItem("user", JSON.stringify({ email }));
            login(email);
            setMessage("Connexion réussie !");
            navigate("/Test1");
        }
    } catch (error) {
        console.error("Erreur de connexion :", error.response?.data || error.message);
        console.log("Détails de l'erreur :", error.response);
        setMessage("Échec de connexion. Vérifiez vos identifiants.");
    }
};

  
  

  return (
    <Box
      sx={{
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
        justifyContent: 'center',
        height: '100vh',
        backgroundImage: `url(${require('../images/background.jpg')})`,
        backgroundSize: 'cover',
        color: '#000',
      }}
    >
      <AppBar position="fixed" sx={{ backgroundColor: 'rgba(255, 255, 255, 0.7)', boxShadow: 'none' }}>
        <Toolbar sx={{ justifyContent: 'space-between' }}>
          <img src={logo} alt="Logo" style={{ width: 170, height: 70, marginLeft: 30 }} />
        </Toolbar>
      </AppBar>

      <Box
        sx={{
          display: 'flex',
          flexDirection: 'column',
          alignItems: 'center',
          justifyContent: 'center',
          color: '#000',
          paddingTop: 10,
          fontSize: 20,
          marginBottom: 2,
        }}
      >
        <Typography variant="h4">Bienvenue sur la plateforme de gestion des tests.</Typography>
        <Typography variant="h5">Veuillez entrer vos identifiants pour accéder à l'application</Typography>
      </Box>

      <Box
        sx={{
          display: 'flex',
          justifyContent: 'center',
          alignItems: 'center',
          height: '100vh',
          width: '35%',
        }}
      >
        <Box
          sx={{
            backgroundColor: 'rgba(255, 255, 255, 0.4)',
            padding: 3,
            borderRadius: 1,
            boxShadow: '0px 0px 10px rgba(0, 0, 0, 0.2)',
            textAlign: 'center',
            width: '100%',
          }}
        >
          <Typography variant="h2" sx={{ color: '#232A56' }}>Login</Typography>

          <form onSubmit={handleSubmit}>
            <Box mb={2}>
              <TextField
                type="email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                label="Email"
                variant="outlined"
                fullWidth
                required
                sx={{
                  width: '95%',
                }}
              />
            </Box>
            <Box mb={2}>
              <TextField
                type="password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                label="Mot de passe"
                variant="outlined"
                fullWidth
                required
                sx={{
                  width: '95%',
                }}
              />
            </Box>
            <Button type="submit" variant="contained" sx={{ backgroundColor: '#232A56' }}>
              Connexion
            </Button>
          </form>
          {message && <p>{message}</p>}
        </Box>
      </Box>
    </Box>
  );
}

export default Login;
