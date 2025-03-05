import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { grey} from '@mui/material/colors';
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
  const { adminEmail, theme, role, level, selectedCompetencies } = location.state || {};
  const [candidates, setCandidates] = useState([{ name: '', email: '' }]);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [questions, setQuestions] = useState([]);
  const [selectedQuestions, setSelectedQuestions] = useState([]);
  const [testName, setTestName] = useState('');
  const [isSubmitDisabled, setIsSubmitDisabled] = useState(true);
  const [isLoading, setIsLoading] = useState(false);
  const [errorMessage, setErrorMessage] = useState('');
  const [roleData, setRoleData] = useState(null);
  const [levelData, setLevelData] = useState(null);
  const [themeData, setThemeData] = useState(null);
  const [adminData, setAdminData] = useState(null);
  const [competencyData, setCompetencyData] = useState([]);
  const [isAdminLoaded, setIsAdminLoaded] = useState(false);

  useEffect(() => {
    if (adminEmail) {
        axios.get(`http://localhost:8088/api/auth/email/${adminEmail}`)
            .then(response => {
                console.log("📌 Admin récupéré :", response.data);
                setAdminData(response.data);
                setIsAdminLoaded(true); // ✅ Marquer comme chargé
            })
            .catch(error => {
                console.error("❌ Erreur récupération admin :", error.response?.data || error.message);
                setIsAdminLoaded(false);
            });
    }
}, [adminEmail]);



  useEffect(() => {
    if (role) {
        axios.get(`http://localhost:8087/role/${role}`)
            .then(response => setRoleData(response.data))
            .catch(error => console.error("❌ Erreur récupération role:", error));
    }
    if (level) {
        axios.get(`http://localhost:8087/level/${level}`)
            .then(response => setLevelData(response.data))
            .catch(error => console.error("❌ Erreur récupération level:", error));
    }
    if (theme) {
        axios.get(`http://localhost:8087/theme/${theme}`)
            .then(response => setThemeData(response.data))
            .catch(error => console.error("❌ Erreur récupération theme:", error));
    }
}, [role, level, theme]);



// Vérifier si les données sont prêtes avant l'envoi
const isDataReady = roleData && levelData && themeData;

   // Vérifie si tous les champs requis sont remplis
   const checkIfFormIsComplete = () => {
    if (!testName.trim()) {
      return false;
    }
    for (let candidate of candidates) {
      if (!candidate.name.trim() || !candidate.email.trim()) {
        return false;
      }
    }
    return true;
  };


 /* useEffect(() => {
    setIsSubmitDisabled(!checkIfFormIsComplete());
  }, [testName, candidates]);
*/
useEffect(() => {
  setIsSubmitDisabled(!testName.trim() || candidates.some(c => !c.name.trim() || !c.email.trim()) || selectedQuestions.length === 0);
}, [testName, candidates, selectedQuestions]);

  const handleCandidateChange = (index, e) => {
    const { name, value } = e.target;
    const newCandidates = [...candidates];
  
    // Vérification pour empêcher les emails en double
    if (name === 'email') {
      const emailExists = newCandidates.some((candidate, i) => candidate.email === value && i !== index);
      if (emailExists) {
        alert('Un candidat avec cet email existe déjà.');
        return;
      }
    }
  
    newCandidates[index][name] = value;
    setCandidates(newCandidates);
  };
  
  const handleAddCandidate = () => {
    const existingEmails = candidates.map(candidate => candidate.email);
  
    // Vérification avant d'ajouter un nouveau candidat
    if (existingEmails.includes('')) {
      alert('Veuillez remplir l\'email du candidat actuel avant d\'ajouter un nouveau candidat.');
    } else {
      setCandidates([...candidates, { name: '', email: '' }]);
    }
  };
  

  const handleSubmit = async (e) => {
    e.preventDefault();
    setIsLoading(true);
    setErrorMessage('');

    console.log("📌 Vérification `adminData` avant envoi :", adminData);

    if (!adminData || !adminData.id || !adminData.email) {
        alert("❌ L'admin n'a pas été chargé correctement.");
        setIsLoading(false);
        return;
    }

    const competencyIds = selectedCompetencies.map(comp => comp.id);
    
    const formattedQuestions = questions.map(q => ({
        id: q.id,
        questionText: q.questionText
    }));

    try {
        console.log("📌 Données envoyées :", {
            testName,
            admin: adminData,
            theme: themeData,
            role: roleData,
            level: levelData,
            competences: competencyData,
            questions: formattedQuestions,
            candidates
        });

        const response = await axios.post('http://localhost:8087/tests/addTest', {
          testName,
          admin: adminData,
          theme: themeData,
          role: roleData,
          level: levelData,
          competences: competencyData,  // <-- ICI
          questions: formattedQuestions,
          candidates,
      });
      

        Swal.fire({
            title: 'Succès',
            text: response.data.message || 'Test créé et envoyé avec succès!',
            icon: 'success',
            confirmButtonText: 'OK'
        });

    } catch (error) {
        console.error('❌ Erreur lors de la création du test:', error.response?.data || error.message);
        setErrorMessage(error.response?.data?.message || "Échec de l'envoi des emails et de l'enregistrement du test.");
    } finally {
        setIsLoading(false);
    }
};


const handleVisualizeTest = async () => {
  try {
      if (!selectedCompetencies || selectedCompetencies.length === 0) {
          throw new Error('Aucune compétence sélectionnée.');
      }

      const competencyIds = selectedCompetencies.map(comp => comp.id).join(',');

      console.log("📌 Envoi requête API :", `http://localhost:8087/question/questions?competencyIds=${competencyIds}`);

      const response = await axios.get(`http://localhost:8087/question/questions?competencyIds=${competencyIds}`);

      console.log("📌 Réponse reçue :", response.data);
      setQuestions(response.data);
      setSelectedQuestions(response.data);

      setIsModalOpen(true);
  } catch (error) {
      console.error("❌ Erreur lors de la récupération des questions:", error);
      setQuestions([]); 
  }
};
useEffect(() => {
  console.log("📌 selectedCompetencies reçu :", selectedCompetencies);

  const fetchCompetencies = async () => {
    try {
      if (!selectedCompetencies || selectedCompetencies.length === 0) {
        throw new Error("Aucune compétence sélectionnée.");
      }

      const competencyIds = selectedCompetencies.map(comp => comp.id).join(',');

      console.log("📌 Envoi requête API :", `http://localhost:8087/competences?ids=${competencyIds}`);

      const response = await axios.get(`http://localhost:8087/competences?ids=${competencyIds}`);

      console.log("📌 Réponse reçue :", response.data);

      if (!response.data || response.data.length === 0) {
        throw new Error("Aucune compétence trouvée.");
      }

      setCompetencyData(response.data);
    } catch (error) {
      console.error("❌ Erreur lors de la récupération des compétences:", error);
      setCompetencyData([]); 
    }
  };

  fetchCompetencies();
}, [selectedCompetencies]);



  const handleCloseModal = () => {
    setIsModalOpen(false);
  };

  const handleRemoveCandidate = (index) => {
    const newCandidates = candidates.filter((_, i) => i !== index);
    setCandidates(newCandidates);
  };
  

  return (
    <div style={{ display: 'flex', flexDirection: 'column', alignItems: 'center', background: 'linear-gradient(to bottom, #f0f0f0 65%, #232A56 35%)', color: '#000', minHeight: '100vh' }}>
      <Navbar />
      <Typography variant="h1" style={{ marginTop: '25vh', fontSize: '3.5em', fontStyle: 'italic' }}>Créer un test</Typography>

      <Container style={{ backgroundColor: '#fff', padding: 30, borderRadius: 10, boxShadow: '0 2px 4px rgba(0, 0, 0, 0.3)', width: '50%', margin: '3%', height: 'auto' }}>
        <form onSubmit={handleSubmit}>
          <Typography variant="h2" style={{ fontSize: '1.5em', color: 'rgba(35, 42, 86, 0.66)', textAlign: 'center', paddingBottom: '5%' }}>Inviter des candidats</Typography>
          <TextField
            type="text"
            name="testName"
            placeholder="Entrer le nom du test"
            value={testName}
            onChange={(e) => setTestName(e.target.value)}
            variant="outlined"
            style={{ width: '100%', marginBottom: 20, padding: 5, borderRadius: 30, fontSize: '1em' }}
          />
          <div style={{ maxHeight: 130, overflowY: 'auto', width: '100%' }}>
  {candidates.map((candidate, index) => (
    <div key={index} style={{ display: 'flex', alignItems: 'center', justifyContent: 'space-between', marginBottom: 20 }}>
      <TextField
        type="text"
        name="name"
        placeholder="Entrer le nom du candidat"
        value={candidate.name}
        onChange={(e) => handleCandidateChange(index, e)}
        variant="outlined"
        style={{ width: '50%', padding: 5, borderRadius: 30, fontSize: '1em' }}
      />
      <TextField
        type="email"
        name="email"
        placeholder="Entrer l’email du candidat"
        value={candidate.email}
        onChange={(e) => handleCandidateChange(index, e)}
        variant="outlined"
        style={{ width: '50%', padding: 5, borderRadius: 30, fontSize: '1em' }}
      />
      {index > 0 && (
      <Button 
        onClick={() => handleRemoveCandidate(index)}
        style={{ minWidth: 30, minHeight: 30, padding: 5, marginLeft: 10 ,backgroundColor: 'rgba(35, 42, 86, 0.30)',borderRadius: '50%',  transition: 'background-color 0.2s ease-in-out',}}
      >
        <CloseIcon sx={{ color: 'rgba(35, 42, 86, 0.66)' }} />
      </Button>
      )}
    </div>
  ))}
</div>


          <div style={{ display: 'flex', justifyContent: 'space-between', marginBottom: 20 }}>
            <Button type="button" variant="contained" color="primary" sx={{ borderRadius: 30, width: '20%', backgroundColor: 'rgba(35, 42, 86, 0.66)', color: '#fff', cursor: 'pointer', '&:hover': { backgroundColor: '#fff', color: 'rgba(35, 42, 86, 0.66)' } }} onClick={handleAddCandidate}>Ajouter</Button>
          </div>
          <div style={{ display: 'flex', justifyContent: 'space-between' }}>
            <Button type="button" variant="contained" color="primary" sx={{ borderRadius: 30, width: '45%', backgroundColor: '#232A56', color: '#fff', border: 'none', cursor: 'pointer', marginTop: 1, '&:hover': { backgroundColor: grey[400], color: '#232A56' } }} onClick={handleVisualizeTest}>Visualiser le test</Button>
            <Button type="submit" variant="contained" color="primary" disabled={isSubmitDisabled} sx={{ borderRadius: 30, width: '45%', backgroundColor: isSubmitDisabled ? '#C0C0C0' : '#232A56', color: '#fff', border: 'none', cursor: isSubmitDisabled ? 'not-allowed' : 'pointer', marginTop: 1, '&:hover': { backgroundColor: grey[400], color: '#232A56' } }}>Envoyer le test</Button>
          </div>
          {isLoading && <CircularProgress style={{ marginTop: 20 }} />}
          {errorMessage && (
            <Typography
              color="error"
              style={{
                marginTop: 20,
                textAlign: 'center',
                width: '100%',
              }}
            >
              {errorMessage}
            </Typography>
          )}
        </form>
      </Container>

      <Modal open={isModalOpen} onClose={handleCloseModal}>
        <Box sx={{
          position: 'absolute',
          top: '50%',
          left: '50%',
          transform: 'translate(-50%, -50%)',
          width: 600,
          bgcolor: 'background.paper',
          boxShadow: 24,
          p: 4,
          borderRadius: 2,
          maxHeight: '80vh',
          overflowY: 'auto',
        }}>
          <Typography variant="h4" gutterBottom style={{ fontSize: '1.5em', color: 'rgba(35, 42, 86, 0.66)', textAlign: 'center', paddingBottom: '5%' }}>
            Visualiser le test
          </Typography>
          {questions.length > 0 ? (
  questions.map((q, index) => (
    <Box key={index} sx={{ mb: 2, p: 2, border: '1px solid #ccc', borderRadius: 2 }}>
      <Typography variant="h6" style={{ marginBottom: 10, color: '#000' }}>
        {q.questionText}
      </Typography>
      {q.answerChoices && q.answerChoices.length > 0 ? (
        q.answerChoices.map((choice, idx) => (
          <Typography key={idx} variant="body1" style={{ color: '#555' }}>
            {choice.choiceText}
          </Typography>
        ))
      ) : (
        <Typography variant="body2" style={{ color: 'red' }}>Aucune réponse disponible</Typography>
      )}
    </Box>
  ))
) : (
  <Typography style={{ color: '#000' }}>No questions available</Typography>
)}

          <Button variant="contained" color="primary" onClick={handleCloseModal} style={{ borderRadius: 30, backgroundColor: '#232A56', color: '#fff', cursor: 'pointer', marginTop: 10 }}>Fermer</Button>
        </Box>
      </Modal>
    </div>
  );
}

export default Test3;
