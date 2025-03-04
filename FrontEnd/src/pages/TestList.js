import React, { useState, useEffect } from "react";
import Navbar from "../components/Navbar";
import axios from "axios";
import Swal from "sweetalert2";
import {
  Typography,
  List,
  ListItem,
  ListItemText,
  Button,
  Box,
  Modal,
  TextField,
} from "@mui/material";
import SettingsIcon from "@mui/icons-material/Settings";
import PersonIcon from "@mui/icons-material/Person";
import AddIcon from "@mui/icons-material/Add";
import SearchIcon from "@mui/icons-material/Search";
import InputAdornment from "@mui/material/InputAdornment";

function TestList() {
  const [tests, setTests] = useState([]);
  const [selectedTest, setSelectedTest] = useState(null);
  const [isParamsModalOpen, setIsParamsModalOpen] = useState(false);
  const [isCandidatesModalOpen, setIsCandidatesModalOpen] = useState(false);
  const [isInviteModalOpen, setIsInviteModalOpen] = useState(false);
  const [testParams, setTestParams] = useState({});
  const [candidates, setCandidates] = useState([]);
  const [questions, setQuestions] = useState([]);
  const [candidateEmail, setCandidateEmail] = useState("");
  const [candidateName, setCandidateName] = useState("");
  const [searchQuery, setSearchQuery] = useState("");
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchTests = async () => {
      try {
        const response = await fetch("http://localhost:8087/tests");
        if (!response.ok) {
          throw new Error(`HTTP error! status: ${response.status}`);
        }
        const data = await response.json();
        console.log("✅ Tests récupérés :", data);
        setTests(data);
      } catch (error) {
        console.error("❌ Erreur récupération tests :", error);
      }
    };
    fetchTests();
  }, []);

  const handleSearchChange = (event) => {
    setSearchQuery(event.target.value);
  };

  const filteredCandidates = candidates.filter((candidate) =>
    candidate.name.toLowerCase().includes(searchQuery.toLowerCase())
  );

  const handleTestClick = async (test) => {
    setSelectedTest(test);
    console.log(`🔎 Test sélectionné : ${test.name}`);

    try {
      const response = await fetch(`http://localhost:8087/tests/${test.id}`);
      const data = await response.json();
      console.log("📌 Détails du test :", data);
      setTestParams(data);
    } catch (error) {
      console.error("❌ Erreur récupération détails test :", error);
    }

    try {
      const response = await fetch(`http://localhost:8087/question/questAnswer/${test.id}`);
      const data = await response.json();
      console.log("✅ Questions et réponses récupérées :", data);
      setQuestions(data);
    } catch (error) {
      console.error("❌ Erreur récupération questions :", error);
    }
  };

  return (
    <div style={{ display: "flex", minHeight: "100vh" }}>
      <Navbar />
      <div
        style={{
          display: "flex",
          width: "100%",
          marginTop: "64px",
          backgroundColor: "#D9D9D9",
        }}
      >
        <Box sx={{ width: "20%", padding: 2, backgroundColor: "#232A56", color: "#fff" }}>
          <Typography variant="h6" sx={{ textDecoration: "underline", marginBottom: "5%", fontSize: "2em" }}>
            Tests
          </Typography>
          {error && <p>{error}</p>}
          <List>
            {tests.map((test) => (
              <ListItem
                button
                key={test.id}
                onClick={() => handleTestClick(test)}
                sx={{
                  backgroundColor: selectedTest && selectedTest.id === test.id ? "rgba(255, 255, 255, 0.2)" : "transparent",
                  borderRadius: "8px",
                  "&:before": {
                    content: selectedTest && selectedTest.id === test.id ? '"▶"' : '""',
                    marginRight: "8px",
                  },
                }}
              >
                <ListItemText primary={test.name} />
              </ListItem>
            ))}
          </List>
        </Box>
        <Box sx={{ width: "80%", padding: "20px", alignItems: "center", justifyContent: "center" }}>
          {selectedTest ? (
            <>
              <Typography variant="h5" style={{ textAlign: "center", padding: "20px 0", fontSize: "2em" }}>
                {selectedTest.name}
              </Typography>
              <div style={{ textAlign: "right", margin: "2%" }}>
                <Button type="button" variant="contained" color="primary" sx={{ borderRadius: 30, width: "5%", backgroundColor: "#232A56", color: "#fff", cursor: "pointer", margin: "1%" }}>
                  <SettingsIcon />
                </Button>
              </div>
              <Typography variant="h6" style={{ fontSize: "27px", color: "rgba(35, 42, 86, 0.66)", textAlign: "center", paddingBottom: "3%" }}>
                Questions et Réponses
              </Typography>
              <ul>
  {Array.isArray(questions) && questions.length > 0 ? (
    questions.map((question, index) => (
      <Box key={index} 
        sx={{ 
          border: '2px solid #555', 
          borderRadius: '8px', 
          padding: '16px', 
          marginBottom: '10px', 
          backgroundColor: '#f8f8f8'
        }}>
        <Typography variant="body1" style={{ marginBottom: '10px', fontWeight: 'bold', color: '#333' }}>
          {question.questionText}
        </Typography>
        <ul style={{ paddingLeft: '20px' }}>
          {Array.isArray(question.answerChoices) && question.answerChoices.length > 0 ? (
            question.answerChoices.map((choice, idx) => (
              <li key={idx}>
                <Typography variant="body1" style={{ color: '#555' }}>
                  {choice.choiceText}
                </Typography>
              </li>
            ))
          ) : (
            <Typography variant="body1" style={{ color: 'red' }}>Aucune réponse disponible</Typography>
          )}
        </ul>
      </Box>
    ))
  ) : (
    <Typography variant="body1" style={{ color: 'red', textAlign: 'center' }}>
      Aucune question disponible pour ce test.
    </Typography>
  )}
</ul>

            </>
          ) : (
            <div style={{ display: "flex", justifyContent: "center", alignItems: "center", marginTop: "64px", color: "#888888" }}>
              <Typography variant="h4">Aucun test sélectionné</Typography>
            </div>
          )}
        </Box>
      </div>
    </div>
  );
}

export default TestList;
