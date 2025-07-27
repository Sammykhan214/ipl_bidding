// src/theme.js
import { createTheme } from '@mui/material/styles';

const theme = createTheme({
  palette: {
    mode: 'dark',
    primary: {
      main: '#2563eb', // Blue (matches your UI)
    },
    secondary: {
      main: '#f59e0b', // Amber for accents (optional)
    },
    background: {
      default: '#0f172a',  // Deep dark background
      paper: '#1e293b',    // Card background
    },
    text: {
      primary: '#ffffff',
      secondary: '#cbd5e1',
    },
  },
  typography: {
    fontFamily: 'Poppins, Roboto, sans-serif',
  },
  shape: {
    borderRadius: 12,
  },
});

export default theme;
