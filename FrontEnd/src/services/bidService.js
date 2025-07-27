// services/bidService.js
import axios from 'axios';
import { getToken } from '../utils/authUtils';

const BID_API = 'http://localhost:8080/auction/bid';

export const placeBid = async (bidReq) => {
  const token = getToken();
  return axios.post(BID_API, bidReq, {
    headers: {
      Authorization: `Bearer ${token}`,
    },
    withCredentials: true,
  });
};

export const getBidHistory = async (playerId) => {
  const token = getToken();
  const res = await axios.get(`${BID_API}/player/${playerId}`, {
    headers: {
      Authorization: `Bearer ${token}`,
    },
    withCredentials: true,
  });
  return res.data;
};
