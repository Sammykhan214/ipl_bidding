import React from 'react'
import { Routes, Route, Navigate } from 'react-router-dom';

import LoginPage from './pages/LoginPage';
import AuctionPage from './pages/AuctionPage';
import SummaryPage from './pages/SummaryPage';
import LeaderboardPage from './pages/LeaderBoardPage';
import RegisterPage from './pages/RegisterPage';
const App = () => {
  return (
    <div>
      <Routes>
        <Route path="/" element={<Navigate to="/login"/>}/>
          <Route path="/register" element={<RegisterPage />} />
        <Route path="/login" element={<LoginPage />} />
      <Route path="/auction" element={<AuctionPage />} />
      <Route path="/summary" element={<SummaryPage />} />
      <Route path="/leaderboard" element={<LeaderboardPage />} />
      </Routes>
    </div>
  )
}

export default App
