// src/pages/RegisterPage.jsx
import React, { useState } from 'react';
import {
  Box,
  Button,
  TextField,
  Typography,
  Paper,
  Stack,
  Alert,
} from '@mui/material';
import { register } from '../services/authservice';
import { useNavigate } from 'react-router-dom';

const RegisterPage = () => {
  const [form, setForm] = useState({ name: '', email: '', password: '' });
  const [error, setError] = useState('');
  const [emailError, setEmailError] = useState('');
  const [success, setSuccess] = useState('');
  const navigate = useNavigate();

  const handleEmailChange = (e) => {
    const email = e.target.value;
    setForm({ ...form, email });

    const isValidEmail = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/.test(email);
    setEmailError(isValidEmail ? '' : 'Invalid email format');
    if (error) setError('');
  };

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
    setError('');
    setSuccess('');
  };

  const handleRegister = async (role) => {
    if (emailError || !form.email || !form.password) {
      setError('❌ Please fix error before register');
      setTimeout(() => {
        setError('');
        setForm({ name: '', email: '', password: '' });
      }, 3000);
      return;
    }
    try {
      await register({ ...form, role });
      setSuccess('✅ Registration successful!');
      setError('');

      // Reset after 3 sec and redirect
      setTimeout(() => {
        setForm({ name: '', email: '', password: '' });
        setSuccess('');
        navigate('/login');
      }, 3000);
    } catch (err) {
      setError('❌ Registration failed. Try another email.');
      setSuccess('');

      setTimeout(() => {
        setForm({ name: '', email: '', password: '' });
        setError('');
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
      <Paper elevation={6} sx={{ p: 4, width: 400, backgroundColor: '#1e1e1e' }}>
        <Stack spacing={2}>
          <Typography variant="h5" align="center" color="primary">
            Register
          </Typography>

          {error && <Alert severity="error" sx={{ mb: 1 }}>{error}</Alert>}
          {success && <Alert severity="success" sx={{ mb: 1 }}>{success}</Alert>}

          <TextField
            name="name"
            label="Name"
            value={form.name}
            onChange={handleChange}
            fullWidth
            required
          />
          <TextField
            name="email"
            label="Email"
            value={form.email}
            onChange={handleEmailChange}
            fullWidth
            required
            error={!!emailError}
            helperText={emailError}
          />
          <TextField
            name="password"
            label="Password"
            type="password"
            value={form.password}
            onChange={handleChange}
            fullWidth
            required
          />

          <Stack direction="row" spacing={2} sx={{ mt: 2 }}>
            <Button
              variant="contained"
              color="primary"
              fullWidth
              onClick={() => handleRegister('VIEWER')}
            >
              Register as Viewer
            </Button>
            <Button
              variant="outlined"
              color="secondary"
              fullWidth
              onClick={() => handleRegister('TEAM')}
            >
              Register as Team
            </Button>
          </Stack>
        </Stack>
      </Paper>
    </Box>
  );
};

export default RegisterPage;
