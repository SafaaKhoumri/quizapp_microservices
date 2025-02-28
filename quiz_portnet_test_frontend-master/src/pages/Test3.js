import React, { useState, useEffect } from 'react'; 
import axios from 'axios';
import { grey } from '@mui/material/colors';
import CloseIcon from '@mui/icons-material/Close';
import Navbar from '../components/Navbar';
import Swal from 'sweetalert2';
import {
  Typography,
  Container,
  TextField,
  Button,
  Modal,
  Box,
  CircularProgress,
} from '@mui/material';
import { useLocation } from 'react-router-dom';

function Test3() {
  const location = useLocation();
  const { adminEmail, domain, role, level, selectedCompetencies } = location.state || {};
  const [candidates, setCandidates] = useState([{ name: '', email: '' }]);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [questions, setQuestions] = useState([]);
  const [testName, setTestName] = useState('');
  const [isSubmitDisabled, setIsSubmitDisabled] = useState(true);
  const [isLoading, setIsLoading] = useState(false);
  const [errorMessage, setErrorMessage] = useState('');

  const checkIfFormIsComplete = () => {
    if (!testName.trim()) return false;
    return candidates.every(candidate => candidate.name.trim() && candidate.email.trim());
  };

  useEffect(() => {
    setIsSubmitDisabled(!checkIfFormIsComplete());
  }, [testName, candidates]);

  const handleCandidateChange = (index, e) => {
    const { name, value } = e.target;
    const newCandidates = [...candidates];
    newCandidates[index][name] = value;
    setCandidates(newCandidates);
  };

  const handleAddCandidate = () => {
    if (candidates.some(candidate => !candidate.email.trim())) {
      alert('Veuillez remplir lemail avant dajouter un nouveau candidat.');
      return;
    }
    setCandidates([...candidates, { name: '', email: '' }]);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setIsLoading(true);
    setErrorMessage('');
    
    try {
      const competencyIds = selectedCompetencies.map(comp => comp.id);
      const response = await axios.post('/api/tests', {
        testName,
        adminEmail,
        domaineId: domain,
        roleId: role,
        levelId: level,
        competencyIds,
        candidates
      });

      const testId = response.data.id;
      for (const candidate of candidates) {
        await axios.post(`/api/email/sendTestLink`, {
          email: candidate.email,
          subject: 'Invitation au test',
          message: `Bonjour ${candidate.name}, vous êtes invité à passer le test ${testName}.\n\n` +
                   `Cliquez sur ce lien pour commencer : http://localhost:3000/TakeTest/${testId}?email=${candidate.email}`
        });
      }

      Swal.fire('Succès', 'Test créé et invitations envoyées.', 'success');
    } catch (error) {
      setErrorMessage(error.response?.data || 'Erreur lors de la création du test.');
    } finally {
      setIsLoading(false);
    }
  };

  const handleVisualizeTest = async () => {
    try {
      const competencyIds = selectedCompetencies.map(comp => comp.id);
      const response = await axios.get('/api/questions', { params: { competencyIds: competencyIds.join(',') } });
      setQuestions(response.data);
      setIsModalOpen(true);
    } catch (error) {
      console.error('Erreur lors de la récupération des questions:', error);
    }
  };

  return (
    <div style={{ textAlign: 'center', padding: '5%' }}>
      <Navbar />
      <Typography variant="h2">Créer un test</Typography>
      <Container>
        <form onSubmit={handleSubmit}>
          <TextField
            label="Nom du test"
            value={testName}
            onChange={(e) => setTestName(e.target.value)}
            fullWidth
          />
          {candidates.map((candidate, index) => (
            <div key={index} style={{ display: 'flex', alignItems: 'center', marginTop: '1rem' }}>
              <TextField
                label="Nom"
                name="name"
                value={candidate.name}
                onChange={(e) => handleCandidateChange(index, e)}
                style={{ marginRight: '10px' }}
              />
              <TextField
                label="Email"
                name="email"
                value={candidate.email}
                onChange={(e) => handleCandidateChange(index, e)}
              />
              <Button onClick={() => handleAddCandidate()}>+</Button>
            </div>
          ))}
          <Button type="submit" disabled={isSubmitDisabled}>Créer et Envoyer</Button>
        </form>
      </Container>
    </div>
  );
}

export default Test3;
