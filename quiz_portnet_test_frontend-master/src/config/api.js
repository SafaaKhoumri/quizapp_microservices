import axios from 'axios';

// URL de l'API Gateway
const API_GATEWAY_URL = "http://localhost:8087";

// Récupérer le token JWT depuis le localStorage
const getAuthToken = () => localStorage.getItem("token");

// Axios instance avec configuration de base
const api = axios.create({
    baseURL: API_GATEWAY_URL,
    headers: {
        "Content-Type": "application/json"
    }
});

// Intercepteur pour ajouter le token JWT automatiquement dans les requêtes
api.interceptors.request.use(
    (config) => {
        const token = getAuthToken();
        if (token) {
            config.headers.Authorization = `Bearer ${token}`;
        }
        return config;
    },
    (error) => Promise.reject(error)
);

// 🔹 AUTHENTIFICATION
export const login = async (credentials) => {
    return api.post("/api/auth/login", credentials)
        .then(response => {
            localStorage.setItem("token", response.data.token); // Stocker le JWT
            return response.data;
        });
};

export const register = async (userData) => {
    return api.post("/api/auth/register", userData);
};

// 🔹 CANDIDATS
export const getCandidats = async () => api.get("/candidat/");
export const getCandidatById = async (id) => api.get(`/candidat/${id}`);
export const createCandidat = async (data) => api.post("/candidat/", data);
export const updateCandidat = async (id, data) => api.put(`/candidat/${id}`, data);
export const deleteCandidat = async (id) => api.delete(`/candidat/${id}`);

// 🔹 TESTS
export const getTests = async () => api.get("/tests/");
export const getTestById = async (id) => api.get(`/tests/${id}`);
export const createTest = async (data) => api.post("/tests/", data);
export const updateTest = async (id, data) => api.put(`/tests/${id}`, data);
export const deleteTest = async (id) => api.delete(`/tests/${id}`);

// 🔹 LEVELS
export const getLevels = async () => api.get("/level/");
export const getLevelById = async (id) => api.get(`/level/${id}`);
export const createLevel = async (data) => api.post("/level/", data);
export const updateLevel = async (id, data) => api.put(`/level/${id}`, data);
export const deleteLevel = async (id) => api.delete(`/level/${id}`);

// 🔹 ROLES
export const getRoles = async () => api.get("/role/");
export const getRoleById = async (id) => api.get(`/role/${id}`);
export const createRole = async (data) => api.post("/role/", data);
export const updateRole = async (id, data) => api.put(`/role/${id}`, data);
export const deleteRole = async (id) => api.delete(`/role/${id}`);

// 🔹 THEMES
// 🔹 Récupérer tous les thèmes
export const getAllThemes = async () => {
    return api.get("/theme/themes").then(response => response.data);
};
export const getThemes = async () => api.get("/theme/");
export const getThemeById = async (id) => api.get(`/theme/${id}`);
export const createTheme = async (data) => api.post("/theme/", data);
export const updateTheme = async (id, data) => api.put(`/theme/${id}`, data);
export const deleteTheme = async (id) => api.delete(`/theme/${id}`);

// 🔹 SCORES
export const getScores = async () => api.get("/score/");
export const getScoreById = async (id) => api.get(`/score/${id}`);
export const createScore = async (data) => api.post("/score/", data);
export const updateScore = async (id, data) => api.put(`/score/${id}`, data);
export const deleteScore = async (id) => api.delete(`/score/${id}`);

// 🔹 COMPÉTENCES
export const getCompetences = async () => api.get("/competences/");
export const getCompetenceById = async (id) => api.get(`/competences/${id}`);
export const createCompetence = async (data) => api.post("/competences/", data);
export const updateCompetence = async (id, data) => api.put(`/competences/${id}`, data);
export const deleteCompetence = async (id) => api.delete(`/competences/${id}`);

// 🔹 QUESTIONS
export const getQuestions = async () => api.get("/question/");
export const getQuestionById = async (id) => api.get(`/question/${id}`);
export const createQuestion = async (data) => api.post("/question/", data);
export const updateQuestion = async (id, data) => api.put(`/question/${id}`, data);
export const deleteQuestion = async (id) => api.delete(`/question/${id}`);

// 🔹 RÉPONSES
export const getAnswers = async () => api.get("/answer/");
export const getAnswerById = async (id) => api.get(`/answer/${id}`);
export const createAnswer = async (data) => api.post("/answer/", data);
export const updateAnswer = async (id, data) => api.put(`/answer/${id}`, data);
export const deleteAnswer = async (id) => api.delete(`/answer/${id}`);

// 🔹 EMAILING
export const sendEmail = async (emailData) => api.post("/api/email/send", emailData);

// Exportation de l'instance API pour une utilisation globale
export default api;
