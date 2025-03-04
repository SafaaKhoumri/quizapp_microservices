import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Box, Typography, List, ListItem, ListItemText, CircularProgress, Container, TextField } from '@mui/material';
import Navbar from '../components/Navbar';
import { green, orange, red } from '@mui/material/colors';

function TestResultsPage() {
  const [tests, setTests] = useState([]);
  const [selectedTest, setSelectedTest] = useState(null);
  const [candidates, setCandidates] = useState([]);
  const [loading, setLoading] = useState(false);
  const [searchTerm, setSearchTerm] = useState('');
  const [searchBgColor, setSearchBgColor] = useState('#f4f4f4');

  useEffect(() => {
    axios.get('http://localhost:8087/tests')
      .then(response => setTests(response.data))
      .catch(error => console.error('Error fetching tests:', error));
  }, []);

  useEffect(() => {
    if (selectedTest) {
      setLoading(true);
      axios.get(`http://localhost:8087/score/test/${selectedTest}/results`)
        .then(response => {
          console.log(response.data); // Ajoutez cette ligne pour vérifier les données
          const candidatesData = response.data.map(candidate => ({
            ...candidate,
            scorePercentage: candidate.scorePercentage !== undefined ? candidate.scorePercentage : null
          }));
          setCandidates(candidatesData);
        })
        .catch(error => console.error('Error fetching candidates:', error))
        .finally(() => setLoading(false));
    }
  }, [selectedTest]);
  

  const handleTestClick = (testId) => {
    setSelectedTest(testId);
    setCandidates([]); // Réinitialiser les candidats lors du changement de test
  };
  

  const getScoreColor = (score) => {
    if (score >= 80) return green[500];
    if (score >= 50) return orange[500];
    return red[500];
  };

  const filteredCandidates = candidates.filter(candidate => 
    candidate.candidatName.toLowerCase().includes(searchTerm.toLowerCase()) &&
    (candidate.scorePercentage !== null || candidate.scorePercentage !== undefined)
  );
  
  return (
    <div style={{ display: 'flex', height: '100vh' }}>
      <Navbar />
      <div style={{ display: 'flex', width: '100%', marginTop: '64px', backgroundColor: '#D9D9D9' }}>
        <Box sx={{ width: '20%', backgroundColor: '#f4f4f4', padding: 2, boxShadow: '2px 0px 5px rgba(0,0,0,0.1)' }}>
          <Typography variant="h6" sx={{ marginBottom: '5%' }}>Tests</Typography>
          <List>
            {tests.map((test) => (
              <ListItem 
                button 
                key={test.id} 
                onClick={() => handleTestClick(test.id)}
                sx={{ 
                  marginBottom: 1, 
                  borderRadius: '8px', 
                  backgroundColor: selectedTest === test.id ? '#232A56' : '#fff', 
                  color: selectedTest === test.id ? '#fff' : '#000',
                  '&:before': {
                    content: selectedTest === test.id ? '"▶"' : '""',
                    marginRight: '8px'
                  },
                }}
              >
                <ListItemText primary={test.name} />
              </ListItem>
            ))}
          </List>
        </Box>

        <Container sx={{ width: '80%', padding: 4, overflowY: 'auto' }}>
          {loading ? (
            <CircularProgress />
          ) : selectedTest ? (
            <>
              <Typography variant="h4" sx={{ marginTop: '3%', marginBottom: '3%', color: 'rgba(35, 42, 86, 0.66)', textAlign: 'center' }}>Résultats des candidats</Typography>
              <TextField 
                label="Rechercher un candidat" 
                variant="outlined" 
                fullWidth 
                sx={{ 
                  marginBottom: '16px', 
                  width: '80%', 
                  marginLeft: '10%', 
                  backgroundColor: searchBgColor 
                }}
                onChange={(e) => setSearchTerm(e.target.value)}
                onFocus={() => setSearchBgColor('#fff')} 
                onBlur={() => setSearchBgColor('#f4f4f4')} 
              />
              <Box sx={{ display: 'flex', flexDirection: 'column', gap: 2, marginTop: '3%', alignItems: 'center' }}>
  {candidates.length > 0 ? (
    candidates.map(({ candidatId, candidatName, scorePercentage }) => (
      <Box 
        key={candidatId} 
        sx={{ 
          width: '95%', 
          backgroundColor: '#fff', 
          borderRadius: 2, 
          boxShadow: '0px 4px 10px rgba(0, 0, 0, 0.1)', 
          padding: 2,
          display: 'flex',
          alignItems: 'center', 
          justifyContent: 'space-between'
        }}
      >
        <Box sx={{ display: 'flex', flexDirection: 'column' }}>
          <Typography variant="h6">{candidatName}</Typography>
        </Box>
        <Box 
          sx={{ 
            width: 60, 
            height: 60, 
            borderRadius: '50%', 
            backgroundColor: scorePercentage !== null ? getScoreColor(scorePercentage) : '#ccc', 
            display: 'flex', 
            alignItems: 'center', 
            justifyContent: 'center' 
          }}
        >
          <Typography variant="h6" sx={{ color: '#fff' }}>
            {scorePercentage !== null ? `${scorePercentage}%` : 'N/A'}
          </Typography>
        </Box>
      </Box>
    ))    
  ) : (
    <Typography variant="body1" color="textSecondary">Aucun candidat n'a été trouvé pour ce test.</Typography>
  )}
</Box>

            </>
          ) : (
            <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', marginTop: '64px', color: '#888888' }}>
              <Typography variant="h4">Sélectionnez un test pour voir les résultats.</Typography>
            </div>
          )}
        </Container>
      </div>
    </div>
  );
}

export default TestResultsPage;
