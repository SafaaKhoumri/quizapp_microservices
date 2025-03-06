import React from 'react';
import { AppBar, Toolbar, Button} from '@mui/material';
import { useNavigate, useLocation, Link } from 'react-router-dom';
import logo from '../images/logo.png';

const Navbar = () => {
  const navigate = useNavigate();
  const location = useLocation();
  const currentPath = location.pathname;

  const linkStyle = (path) => ({
    margin: '0 25px',
    textDecoration: 'none',
    color: '#000',
    fontWeight: 'inherit',
    cursor: 'pointer',
    padding: '10px',
    borderRadius: '5px',
    backgroundColor: currentPath === path ? '#f0f0f0' : 'transparent',
  });

  return (
    <AppBar position="fixed" style={{ backgroundColor: '#fff', boxShadow: '0 2px 4px rgba(0, 0, 0, 0.1)' }}>
      <Toolbar style={{ display: 'flex', justifyContent: 'space-between', width: '100%' }}>
        <img src={logo} alt="Logo" style={{ width: 100 }} />
        <nav style={{ alignItems: 'center' }}>
        <Link to="#" style={linkStyle('/More')}>Bienvenue Dans Votre Espace</Link>
        <Link to="/TestList" style={linkStyle('/TestList')}>Tests</Link>
        <Link to="/TestResultsPage" style={linkStyle('/TestResultsPage')}>Résultats</Link>
        <Link to="/Login" style={linkStyle('/Login')}>Déconnexion</Link>
        </nav>
        <Button
          variant="outlined"
          color="primary"
          style={{
            backgroundColor: '#fff',
            color: '#000000',
            padding: '10px 20px',
            borderRadius: 30,
            cursor: 'pointer',
            width: 'auto',
            marginRight: '3%',
            border: '2px solid #232A56',
          }}
          onClick={() => navigate('/Test1')}
        >
          Créer un test
        </Button>
      </Toolbar>
    </AppBar>
  );
};

export default Navbar;
