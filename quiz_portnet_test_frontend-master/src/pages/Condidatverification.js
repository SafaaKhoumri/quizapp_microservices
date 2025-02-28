import React, { useState } from 'react';
import { Box, Typography, TextField, Button, AppBar, Toolbar } from '@mui/material';
import logo from '../images/logo.png';
import { grey } from '@mui/material/colors';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

function Condidatverification() {
  const [form, setForm] = useState({
    condidat_email: ''
  });
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleChange = (e) => {
    const { name, value } = e.target;
    setForm(prevForm => ({
      ...prevForm,
      [name]: value
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      // Call an endpoint to verify the email
      const response = await axios.post('http://localhost:8088/api/verifyEmail', form.condidat_email);



      if (response.data.valid) {
        // Redirect to the test page if email is valid
        navigate(`/TakeTest/${response.data.testId}`, { state: { email: form.condidat_email } });
      } else {
        setError('Email incorrect. Veuillez saisir une adresse email valide pour passer le test.');
      }
    } catch (error) {
      setError('Erreur lors de la vérification de l\'email.');
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
        background: 'linear-gradient(to bottom, #f0f0f0 65%, #232A56 35%)',
        color: '#000',
      }}
    >
      <AppBar position="fixed" sx={{ backgroundColor: '#fff', boxShadow: 'none' }}>
        <Toolbar sx={{ justifyContent: 'space-between' }}>
          <img src={logo} alt="Logo" style={{ width: 100 }} />
        </Toolbar>
      </AppBar>

      <Typography variant="h1" sx={{ marginTop: '10%', fontSize: '3.5em', fontStyle: 'italic' }}>
        Verification
      </Typography>

      <Box
        sx={{
          backgroundColor: '#fff',
          padding: 3,
          borderRadius: 1,
          boxShadow: '0 2px 4px rgba(0, 0, 0, 0.3)',
          width: '50%',
          margin: 3,
          height: 'auto',
        }}
      >
        <form onSubmit={handleSubmit}>
          <Typography variant="h2" sx={{ marginTop: 2, fontSize: '1em', paddingLeft: '1%' }}>
            Veuillez entrer votre adresse e-mail
          </Typography>
          <Box sx={{ display: 'flex', flexDirection: 'column', alignItems: 'center', marginTop: 2 }}>
            <TextField
              type="email"
              name="condidat_email"
              placeholder="Entrer votre email"
              value={form.condidat_email}
              onChange={handleChange}
              sx={{ width: '100%', marginBottom: 2 }}
            />
            {error && <Typography sx={{ color: 'red', marginBottom: 2, fontSize: '14px' }}>{error}</Typography>}
            <Button type="submit" variant="contained" sx={{ backgroundColor: '#232A56', width: '45%', marginLeft: '53%', borderRadius: 30, '&:hover': { backgroundColor: grey[400], color: '#232A56' } }}>
              OK
            </Button>
          </Box>
        </form>
      </Box>
    </Box>
  );
}

export default Condidatverification;
