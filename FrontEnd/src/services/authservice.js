import React from 'react'
import axios from 'axios';
const BASE_URL='http://localhost:8080/auction/auth';
export const login = async ({email, password}) => {

  const response = await axios.post(`${BASE_URL}/login`, {
    email,
    password,
  });
  return response.data; // { token, role, username, ... }
};

export const register = async ({ name, email, password, role }) => {
  const response = await axios.post(`${BASE_URL}/register`, {
    name,
    email,
    password,
    role, // 👈 important: viewer/team
  });
  return response.data;
};