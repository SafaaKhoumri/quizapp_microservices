import React from 'react';
import { Button, Typography, Box } from '@mui/material';
import { styled } from '@mui/system';
import { common, grey, blue } from '@mui/material/colors';
import QuizIcon from '@mui/icons-material/Quiz';

const GradientBackground = styled('div')(({ theme }) => ({
  display: 'flex',
  flexDirection: 'column',
  alignItems: 'center',
  justifyContent: 'center',
  minHeight: '100vh',
  background: 'linear-gradient(to bottom, #232A56, #f5f5f5)', 
}));

const IntroBox = styled(Box)(({ theme }) => ({
  padding: '2rem',
  width: '100%',
  maxWidth: '600px',
  textAlign: 'center',
  borderRadius: '8px',
  boxShadow: '0px 0px 10px rgba(0, 0, 0, 0.5)',
}));

const StartButton = styled(Button)(({ theme }) => ({
  marginTop: '2rem',
  backgroundColor: '#232A56',
  color: common.white,
  '&:hover': {
    backgroundColor: blue[800],
    color: common.white
  }
}));

const TestIntroduction = ({ onStart }) => {
  return (
    <GradientBackground>
      <IntroBox>
        <QuizIcon sx={{ fontSize: 100, color: common.white }} />
        <Typography variant="h2" component="h1" style={{ color: common.white, marginBottom: '2rem' }}>
          Bienvenue au Test
        </Typography>
        <Typography variant="h6" component="p" style={{ color: common.white, marginBottom: '2rem' }}>
          Veuillez lire les instructions avant de commencer le test.
        </Typography>
        <Typography variant="body1" component="p" style={{ color: common.white, marginBottom: '2rem', marginLeft:'0%' }}>
          1. Vous aurez un nombre limité de temps pour répondre à chaque question.<br/>
          2. Assurez-vous de lire chaque question attentivement avant de répondre.<br/>
          3. Assurez-vous d'être dans un endroit calme avant de commencer.
        </Typography>
        <StartButton
          variant="contained"
          onClick={onStart}
          sx={{ backgroundColor: '#232A56', '&:hover': { 
            backgroundColor: grey[400], 
            color: '#232A56' 
          },  }}
          
        >
          Commencer le Test
        </StartButton>
      </IntroBox>
    </GradientBackground>
  );
};

export default TestIntroduction;