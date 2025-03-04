import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { Button, Box, Typography, Paper, FormControl, FormControlLabel, Radio, RadioGroup, Dialog, DialogActions, DialogContent, DialogTitle } from '@mui/material';
import { grey, common } from '@mui/material/colors';
import { styled } from '@mui/system';
import TestIntroduction from './TestIntroduction';

const GradientBackground = styled('div')(({ theme }) => ({
  display: 'flex',
  flexDirection: 'column',
  alignItems: 'center',
  justifyContent: 'center',
  minHeight: '100vh',
  background: 'linear-gradient(to bottom, #232A56, #f5f5f5)',
}));

const TestBox = styled(Paper)(({ theme }) => ({
  padding: '2rem',
  width: '100%',
  maxWidth: '600px',
  borderRadius: '8px',
  boxShadow: '0px 0px 10px rgba(0, 0, 0, 0.1)',
}));

const TakeTest = () => {
  const { id, candidatId } = useParams(); // Assume the candidate ID is passed as a route param
  const [currentQuestion, setCurrentQuestion] = useState(0);
  const [questions, setQuestions] = useState([]);
  const [answers, setAnswers] = useState([]);
  const [openDialog, setOpenDialog] = useState(false);
  const [submitted, setSubmitted] = useState(false);
  const [started, setStarted] = useState(false);
  const email = new URLSearchParams(window.location.search).get('email');
  
  /*useEffect(() => {
    // Fetch the questions for the test ID
    fetch(`localhost:8087/question/byCandidat/email/${email}`)
      .then(response => response.json())
      .then(data => {
        console.log('Fetched questions:', data); // Debug line
        setQuestions(data);
        setAnswers(new Array(data.length).fill(''));
      })
      .catch(error => console.error('Error fetching questions:', error));
  }, [id]);
  */
  useEffect(() => {
    if (!email) {
      console.warn("Email is missing, cannot fetch questions");
      return;
    }
  
    console.log("Fetching questions from API...");
    fetch(`http://localhost:8087/question/byCandidat/email/${email}`)
      .then(response => {
        if (!response.ok) {
          throw new Error(`HTTP error! Status: ${response.status}`);
        }
        return response.json();
      })
      .then(data => {
        console.log("Fetched questions:", data);
  
        // Ensure choices are correctly mapped from answerChoices
        const validatedQuestions = data.map(question => ({
          ...question,
          choices: question.answerChoices || [], // Map answerChoices to choices
        }));
  
        setQuestions(validatedQuestions);
        setAnswers(new Array(validatedQuestions.length).fill(''));
      })
      .catch(error => console.error("Error fetching questions:", error));
  }, [id, email]);
  
  
  const handleChange = (event) => {
    const newAnswers = [...answers];
    newAnswers[currentQuestion] = event.target.value;
    setAnswers(newAnswers);
  };

  const handleNext = () => {
    if (currentQuestion < questions.length - 1) {
      setCurrentQuestion(currentQuestion + 1);
    }
  };

  const handlePrevious = () => {
    if (currentQuestion > 0) {
      setCurrentQuestion(currentQuestion - 1);
    }
  };



  const handleSubmit = () => {
    setOpenDialog(true);
  };
  
const handleCloseDialog = (confirm) => {
    setOpenDialog(false);
    if (confirm) {
        setSubmitted(true);

        const answerRequests = questions.map((question, index) => {
            const selectedChoice = question.choices.find(choice => choice.choiceText === answers[index]);
            console.log("Selected Choice for question " + (index + 1) + " : ", selectedChoice); // Log for debugging
            const isCorrect = selectedChoice ? selectedChoice.correct : false; // Corrected property name
            console.log("Is Correct for question " + (index + 1) + " : ", isCorrect); // Log for debugging
            return {
                questionId: question.id,
                texteReponse: answers[index],
                estCorrecte: isCorrect
            };
        });

        console.log("Answer Requests: ", answerRequests); // Log for debugging

        const email = new URLSearchParams(window.location.search).get('email');
        console.log("Email: ", email); // Log for debugging

        fetch(`http://localhost:8087/tests/${id}/submit?email=${encodeURIComponent(email)}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(answerRequests)
        }).then(response => {
            if (response.ok) {
                alert('Test submitted successfully!');
            } else {
                alert('Failed to submit test.');
            }
        }).catch(error => console.error('Error submitting test:', error));
    }
};





  const handleStart = () => {
    setStarted(true);
  };

  return (
    <>
      {!started ? (
        <TestIntroduction onStart={handleStart} />
      ) : (
        <GradientBackground>
          {submitted ? (
            <Box sx={{ display: 'flex', flexDirection: 'column', alignItems: 'center', justifyContent: 'center', minHeight: '100vh' }}>
              <Typography variant="h4" component="h1" gutterBottom style={{ textAlign: 'center' }}>
                Test Soumis!
              </Typography>
              <Typography variant="h6" component="h2" gutterBottom style={{ textAlign: 'center' }}>
                Merci d'avoir pris le test. Vos réponses ont été enregistrées.
              </Typography>
            </Box>
          ) : (
            <>
              <Typography variant="h2" component="h1" style={{ marginBottom: '5%', color: common.white }}>
                Prendre le Test
              </Typography>
              <TestBox>
                <Typography variant="h6" component="h2" gutterBottom style={{ marginBottom: '5%', marginLeft: '40%' }}>
                  Question {currentQuestion + 1} / {questions.length}
                </Typography>
                <Typography variant="h6" component="h2" gutterBottom>
                  {questions[currentQuestion]?.questionText}
                </Typography>
                <FormControl component="fieldset">
                  <RadioGroup value={answers[currentQuestion]} onChange={handleChange}>
                    {questions[currentQuestion]?.answerChoices?.map((choice, index) => (
                      <FormControlLabel key={index} value={choice.choiceText} control={<Radio />} label={choice.choiceText} />
                    )) || <Typography>Aucune option disponible</Typography>}
                  </RadioGroup>
                </FormControl>
                <Box sx={{ display: 'flex', flexDirection: 'column', alignItems: 'center', marginTop: '2rem' }}>
                  <Box sx={{ display: 'flex', justifyContent: currentQuestion === 0 ? 'flex-end' : 'space-between', width: '100%' }}>
                    {currentQuestion > 0 && (
                      <Button
                        variant="contained"
                        color="primary"
                        onClick={handlePrevious}
                        sx={{ backgroundColor: '#232A56', '&:hover': { backgroundColor: grey[400], color: '#232A56' } }}
                      >
                        Précédent
                      </Button>
                    )}
                    {currentQuestion < questions.length - 1 ? (
                      <Button
                        variant="contained"
                        color="primary"
                        onClick={handleNext}
                        sx={{ backgroundColor: '#232A56', '&:hover': { backgroundColor: grey[400], color: '#232A56' } }}
                      >
                        Suivant
                      </Button>
                    ) : (
                      <Button
                        variant="contained"
                        color="primary"
                        onClick={handleSubmit}
                        sx={{ backgroundColor: '#232A56', '&:hover': { backgroundColor: grey[400], color: '#232A56' } }}
                      >
                        Soumettre
                      </Button>
                    )}
                  </Box>
                </Box>
              </TestBox>
              <Dialog
                open={openDialog}
                onClose={() => setOpenDialog(false)}
                aria-labelledby="confirmation-dialog-title"
                aria-describedby="confirmation-dialog-description"
              >
                <DialogTitle id="confirmation-dialog-title">{"Confirmer la soumission"}</DialogTitle>
                <DialogContent>
                  <Typography variant="body1" id="confirmation-dialog-description">
                    Êtes-vous sûr de vouloir soumettre le test ?
                  </Typography>
                </DialogContent>
                <DialogActions>
                  <Button onClick={() => handleCloseDialog(false)} color="primary">
                    Annuler
                  </Button>
                  <Button onClick={() => handleCloseDialog(true)} color="primary">
                    Soumettre
                  </Button>
                </DialogActions>
              </Dialog>
            </>
          )}
        </GradientBackground>
      )}
    </>
  );
};

export default TakeTest;
