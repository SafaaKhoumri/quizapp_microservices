import React from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import { UserProvider } from './UserContext';
import Login from './pages/Login';
import Test1 from './pages/Test1';
import Test2 from './pages/Test2';
import Test3 from './pages/Test3';
import Condidatverification from './pages/Condidatverification';
import TestList from './pages/TestList';
import TakeTest from './pages/TakeTest'; 
import TestIntroduction from './pages/TestIntroduction'; 
import TestResultsPage from './pages/TestResultsPage';


function App() {
  return (
    <UserProvider>
      <div className="App">
        <Router>
          <Routes>
            <Route path="/" element={<Navigate to="/Login" />} />
            <Route path="/Login" element={<Login />} />
            <Route path="/Test1" element={<Test1 />} />
            <Route path="/Test2" element={<Test2 />} />
            <Route path="/Test3" element={<Test3 />} />
            <Route path="/Condidatverification" element={<Condidatverification />} />
            <Route path="/TestList" element={<TestList />} />
            <Route path="/TakeTest/:id" element={<TakeTest />} />
            <Route path="/TestIntroduction" element={<TestIntroduction />} />
            <Route path="/TestResultsPage" element={<TestResultsPage />} />
          </Routes>
        </Router>
      </div>
    </UserProvider>
  );
}

export default App;
