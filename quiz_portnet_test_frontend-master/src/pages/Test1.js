import React, { useState, useEffect } from 'react';
import { TextField, Typography, Select, MenuItem, FormControl, InputLabel, Container, Button, Box } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import Navbar from '../components/Navbar';
import { useUser } from '../UserContext';

function Test1() {
  const navigate = useNavigate();
  const { user } = useUser(); // Récupérer l'utilisateur connecté
  const [form, setForm] = useState({
    adminEmail: user?.email || '', // Utiliser l'email de l'utilisateur connecté
    theme: '',
    role: '',
    level: '',
  });

  const [themes, setThemes] = useState([]);
  const [roles, setRoles] = useState([]);
  const [levels, setLevels] = useState([]);
  const [error, setError] = useState('');

  useEffect(() => {
    axios.get('http://localhost:8087/theme/themes', { withCredentials: true })
      .then(response => {
        console.log("📌 Réponse reçue dans React:", response.data);
        setThemes(response.data);
      })
      .catch(error => {
        console.error("🚨 Erreur Axios:", error.response ? error.response : error);
        setError(`Impossible de charger les thèmes : ${error.message}`);
      });
}, []);

  
  // 🔥 Charger les rôles et niveaux en fonction du thème sélectionné
  useEffect(() => {
    if (form.theme) {
      axios.get(`http://localhost:8087/role/theme/${form.theme}`)
        .then(response => {
          console.log("Données reçues depuis l'API (rôles) :", response.data);
          setRoles(response.data);
        })
        .catch(error => {
          console.error("Erreur lors de la récupération des rôles :", error);
          setError("Impossible de charger les rôles.");
        });

      axios.get(`http://localhost:8087/level/theme/${form.theme}`)
        .then(response => {
          console.log("Données reçues depuis l'API (niveaux) :", response.data);
          setLevels(response.data);
        })
        .catch(error => {
          console.error("Erreur lors de la récupération des niveaux :", error);
          setError("Impossible de charger les niveaux.");
        });
    } else {
      setRoles([]);
      setLevels([]);
    }
  }, [form.theme]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setForm((prevForm) => ({
      ...prevForm,
      [name]: value,
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (form.adminEmail && form.theme && form.role && form.level) {
      setError('');
      navigate('/Test2', { state: form });
    } else {
      setError("Tous les champs sont requis");
    }
  };

  const isFormComplete = form.adminEmail && form.theme && form.role && form.level;

  return (
    <Box
      sx={{
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
        justifyContent: 'center',
        minHeight: '100vh',
        background: 'linear-gradient(to bottom, #f0f0f0 65%, #232A56 35%)',
        color: '#000',
        overflowX: 'hidden',
      }}
    >
      <Navbar />

      <Typography variant="h2" sx={{ marginTop: '15vh', fontSize: '3.5em', fontStyle: 'italic' }}>
        Créer un test
      </Typography>

      <Container sx={{ backgroundColor: '#fff', padding: 3, borderRadius: 1, boxShadow: '0 2px 4px rgba(0, 0, 0, 0.3)', margin: 3 }}>
        <form onSubmit={handleSubmit} style={{ display: 'flex', flexDirection: 'column' }}>
          {error && <Typography color="error">{error}</Typography>}
          <Box sx={{ display: 'flex', justifyContent: 'space-between', marginBottom: 2 }}>
            <TextField
              name="adminEmail"
              placeholder="Entrer votre email"
              value={form.adminEmail}
              variant="outlined"
              sx={{ padding: 1, fontSize: '1em', margin: 1, width: 'calc(50% - 20px)' }}
              InputProps={{ readOnly: true }}
            />
            <FormControl variant="outlined" sx={{ padding: 1, fontSize: '1em', margin: 1, width: 'calc(50% - 20px)' }}>
              <InputLabel>Choisir le thème</InputLabel>
              <Select
                name="theme"
                value={form.theme}
                onChange={handleChange}
                label="Choisir le thème"
              >
                {themes.length > 0 ? (
                  themes.map(theme => (
                    <MenuItem key={theme.id} value={theme.id}>{theme.name}</MenuItem>
                  ))
                ) : (
                  <MenuItem disabled>Aucun thème disponible</MenuItem>
                )}
              </Select>
            </FormControl>
          </Box>
          <Box sx={{ display: 'flex', justifyContent: 'space-between', marginBottom: 2 }}>
            <FormControl variant="outlined" sx={{ padding: 1, fontSize: '1em', margin: 1, width: 'calc(50% - 20px)' }}>
              <InputLabel>Choisir le rôle</InputLabel>
              <Select
                name="role"
                value={form.role}
                onChange={handleChange}
                label="Choisir le rôle"
                disabled={!form.theme}
              >
                {roles.length > 0 ? (
                  roles.map(role => (
                    <MenuItem key={role.id} value={role.id}>{role.name}</MenuItem>
                  ))
                ) : (
                  <MenuItem disabled>Aucun rôle disponible</MenuItem>
                )}
              </Select>
            </FormControl>
            <FormControl variant="outlined" sx={{ padding: 1, fontSize: '1em', margin: 1, width: 'calc(50% - 20px)' }}>
              <InputLabel>Choisir le niveau</InputLabel>
              <Select
                name="level"
                value={form.level}
                onChange={handleChange}
                label="Choisir le niveau"
                disabled={!form.theme}
              >
                {levels.length > 0 ? (
                  levels.map(level => (
                    <MenuItem key={level.id} value={level.id}>{level.name}</MenuItem>
                  ))
                ) : (
                  <MenuItem disabled>Aucun niveau disponible</MenuItem>
                )}
              </Select>
            </FormControl>
          </Box>
          <Button
            type="submit"
            variant="contained"
            disabled={!isFormComplete}
            sx={{
              width: '40%',
              backgroundColor: isFormComplete ? '#232A56' : '#aaa',
              color: '#fff',
              cursor: 'pointer',
              borderRadius: 30,
              margin: '0 auto',
              '&:hover': {
                backgroundColor: isFormComplete ? '#C0C0C0' : '#aaa',
                color: '#232A56',
              },
            }}
          >
            Suivant
          </Button>
        </form>
      </Container>
    </Box>
  );
}

export default Test1;
