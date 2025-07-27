import React, { useEffect, useState } from 'react';
import {
  Box, Typography, Paper, Stack, TextField, Button,
  Grid, Avatar, Divider
} from '@mui/material';
import { placeBid } from '../services/bidService';
import { getCurrentPlayer, finalizeBid } from '../services/playerService';
import { getTokenData } from '../utils/authUtils';

const AuctionPage = () => {
  const [player, setPlayer] = useState(null);
  const [bidAmount, setBidAmount] = useState('');
  const [bidHistory, setBidHistory] = useState([]);
  const [error, setError] = useState('');
  const [secondsLeft, setSecondsLeft] = useState(15);

  useEffect(() => {
    loadPlayer();
  }, []);

  useEffect(() => {
    if (!player) return;

    const timer = setInterval(() => {
      setSecondsLeft((prev) => {
        if (prev === 1) {
          clearInterval(timer);
          handleFinalize();
          return 0;
        }
        return prev - 1;
      });
    }, 1000);

    return () => clearInterval(timer);
  }, [player, bidHistory]);

  const loadPlayer = async () => {
    try {
      const data = await getCurrentPlayer();
      setPlayer(data);
      setSecondsLeft(15);
      setBidHistory([]);
    } catch (err) {
      setError('❌ No player is currently in auction');
    }
  };

  const handleBid = async () => {
    if (!bidAmount || isNaN(bidAmount)) {
      setError('❌ Enter a valid amount');
      return;
    }

    const user = getTokenData();
    const bidReq = {
      playerId: player.id,
      teamId: user.id,
      amount: parseInt(bidAmount),
    };

    try {
      await placeBid(bidReq);
      setError('');
      setBidAmount('');
      setSecondsLeft(15);
      setBidHistory((prev) => [...prev, { team: user.name, amount: bidReq.amount }]);
    } catch (e) {
      setError('❌ Failed to place bid');
    }
  };

  const handleFinalize = async () => {
    try {
      await finalizeBid(player.id);
      loadPlayer();
    } catch (err) {
      setError('❌ Finalize failed');
    }
  };

  if (!player) {
    return (
      <Box p={4} sx={{ color: 'white', textAlign: 'center' }}>
        <Typography variant="h5" color="error">
          No player in auction. Please start the auction first.
        </Typography>
      </Box>
    );
  }

  return (
    <Box
      sx={{
        height: '100vh',
        display: 'grid',
        gridTemplateColumns: '1fr 2fr 1fr',
        background: 'linear-gradient(to right, #0f2027, #203a43, #2c5364)',
        gap: 2,
        padding: 2,
        color: 'white',
      }}
    >
      {/* Left (Player Info) */}
      <Paper sx={{ p: 3, backgroundColor: '#1e1e1e', borderRadius: 3 }}>
        <Stack spacing={2}>
          <Grid container spacing={2} alignItems="center">
            <Grid item>
              <Avatar src={player.image} sx={{ width: 70, height: 70 }} />
            </Grid>
            <Grid item>
              <Typography variant="h5">{player.name}</Typography>
              <Typography variant="body2" color="gray">
                {player.battingStyle} | {player.bowlingStyle}
              </Typography>
              <Typography variant="body2" color="lightgray">
                {player.nationality}
              </Typography>
            </Grid>
          </Grid>

          <Divider sx={{ my: 1 }} />

          <Grid container spacing={2}>
            <Grid item xs={6}>
              <Typography variant="subtitle1" color="primary">Batting Stats</Typography>
              <Typography variant="body2">Runs: {player.playerStats?.runs}</Typography>
              <Typography variant="body2">Avg: {player.playerStats?.battingAverage}</Typography>
              <Typography variant="body2">SR: {player.playerStats?.strikeRate}</Typography>
              <Typography variant="body2">50s: {player.playerStats?.fifties}</Typography>
              <Typography variant="body2">100s: {player.playerStats?.centuries}</Typography>
            </Grid>
            <Grid item xs={6}>
              <Typography variant="subtitle1" color="primary">Bowling Stats</Typography>
              <Typography variant="body2">Matches: {player.playerStats?.matches}</Typography>
              <Typography variant="body2">Wickets: {player.playerStats?.wickets}</Typography>
              <Typography variant="body2">Avg: {player.playerStats?.bowlingAverage}</Typography>
              <Typography variant="body2">Eco: {player.playerStats?.economy}</Typography>
              <Typography variant="body2">5WI: {player.playerStats?.fiveWicketHauls}</Typography>
            </Grid>
          </Grid>
        </Stack>
      </Paper>

      {/* Center (Bidding Box) */}
      <Paper sx={{ p: 3, backgroundColor: '#1e1e1e', borderRadius: 3 }}>
        <Typography variant="h6" color="primary">Place Your Bid</Typography>
        <Typography variant="body2" color="gray" mb={2}>
          Auto-finalizing in: <strong>{secondsLeft}s</strong>
        </Typography>

        <Stack direction="row" spacing={2} mb={2}>
          <TextField
            label="Bid Amount"
            variant="outlined"
            fullWidth
            value={bidAmount}
            onChange={(e) => setBidAmount(e.target.value)}
            sx={{ input: { color: 'white' }, label: { color: 'gray' } }}
          />
          <Button variant="contained" color="primary" onClick={handleBid}>
            Bid
          </Button>
        </Stack>
        {error && <Typography color="error">{error}</Typography>}
      </Paper>

      {/* Right (Bid History) */}
      <Paper sx={{ p: 3, backgroundColor: '#1e1e1e', borderRadius: 3 }}>
        <Typography variant="h6" color="primary" gutterBottom>Bid History</Typography>
        <Stack spacing={1}>
          {bidHistory.length === 0 ? (
            <Typography variant="body2" color="gray">No bids yet</Typography>
          ) : (
            bidHistory.map((bid, idx) => (
              <Paper key={idx} sx={{ p: 1, backgroundColor: '#2e2e2e' }}>
                <Typography variant="body2">
                  <strong>{bid.team}</strong> placed bid: ₹{bid.amount.toLocaleString()}
                </Typography>
              </Paper>
            ))
          )}
        </Stack>
      </Paper>
    </Box>
  );
};

export default AuctionPage;
