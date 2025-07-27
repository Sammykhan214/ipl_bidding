// src/utils/authUtils.js
import {jwt_decode, jwtDecode} from 'jwt-decode';

export const getTokenData = () => {
  const token = localStorage.getItem('token');
  if (!token) return null;
  return jwtDecode(token); // returns object with id, name, email, role etc.
};
export const saveToken = (token) => {
  localStorage.setItem('token', token);
};

export const getToken = () => {
  return localStorage.getItem('token');
};

export const removeToken = () => {
  localStorage.removeItem('token');
};
