// services/playerService.js
import axios from 'axios';
import { getToken } from '../utils/authUtils';

const BASE_URL = 'http://localhost:8080/auction';

export const getCurrentPlayer = async () => {
  const token = getToken();
  const res = await axios.get(`${BASE_URL}/current-player`, {
    headers: {
      Authorization: `Bearer ${token}`,
    },
    withCredentials: true,
  });
  return res.data;
};

// optional: only use this if admin manually starts auction
// export const startAuction = async (playerId) => {
//   const token = getToken();
//   return axios.post(
//     `${BASE_URL}/startAuction`,
//     { startPlayerId: playerId },
//     {
//       headers: {
//         Authorization: `Bearer ${token}`,
//       },
//       withCredentials: true,
//     }
//   );
// };

export const finalizeBid = async (playerId) => {
  const token = getToken();
  return axios.post(`${BASE_URL}/finalize/${playerId}`, null, {
    headers: {
      Authorization: `Bearer ${token}`,
    },
    withCredentials: true,
  });
};
