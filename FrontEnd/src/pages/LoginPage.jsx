import React, { useEffect, useState } from 'react';
import {
  Box,
  Button,
  TextField,
  Typography,
  Paper,
  Stack,
  Alert,
} from '@mui/material';
import { login } from '../services/authservice';
import { saveToken } from '../utils/authUtils';
import { Link, useNavigate } from 'react-router-dom';

const LoginPage = () => {
  const [form, setForm] = useState({ email: '', password: '' });
  const [emailError, setEmailError] = useState('');
  const [errorMsg, setErrorMsg] = useState('');
  const navigate = useNavigate();

 const handleEmailChange = (e) => {
    const email = e.target.value;
    setForm({ ...form, email });

    const isValidEmail = /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email);
    setEmailError(isValidEmail ? '' : 'Invalid email format');
    if (errorMsg) setErrorMsg('');
  };

   const handlePasswordChange = (e) => {
    setForm({ ...form, password: e.target.value });
    if (errorMsg) setErrorMsg('');
  };

  const handleLogin = async () => {
      if (emailError || !form.email || !form.password) {
      setErrorMsg('❌ Please fix email before login');
      setTimeout(() => {
        setErrorMsg('');
        setForm({ email: '', password: '' });
      }, 3000);
      return;
    }
    try {
      const data = await login(form);
      setTimeout(() => {
        setForm({ email: '', password: '' }); // Reset
        saveToken(data.token);
        navigate('/auction');
      }, 3000);
    } catch (err) {
      setErrorMsg('❌ Invalid credentials. Please try again.');
      setTimeout(() => {
        setForm({ email: '', password: '' });
        setErrorMsg('');
      }, 3000);
    }
  };

  return (
    <Box
      sx={{
        height: '100vh',
        background: 'linear-gradient(to right, #0f2027, #203a43, #2c5364)',
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
      }}
    >
      <Paper elevation={6} sx={{ p: 4, width: 350, backgroundColor: '#1e1e1e' }}>
        <Stack spacing={3}>
          <Typography variant="h5" color="primary" align="center">
            IPL Auction Login
          </Typography>

          {errorMsg && <Alert severity="error">{errorMsg}</Alert>}

          <TextField
             variant="outlined"
            label="Email"
            name="email"
            value={form.email}
            onChange={handleEmailChange}
            error={!!emailError}
            helperText={emailError}
            fullWidth
          />

          <TextField
            variant="outlined"
            label="Password"
            name="password"
            type="password"
            value={form.password}
            onChange={handlePasswordChange}
            fullWidth
          />
          <Button variant="contained" color="primary" fullWidth onClick={handleLogin}>
            Login
          </Button>

          <Typography variant="body2" align="center" sx={{ mt: 2 }}>
            <Link to="/register" style={{ marginRight: '1rem' }}>
              Don't have an account? Register
            </Link>
            <Link to="/forgot-password">Forgot Password?</Link>
          </Typography>
        </Stack>
      </Paper>
    </Box>
  );
};

export default LoginPage;
