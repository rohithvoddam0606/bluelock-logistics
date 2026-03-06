import React from 'react';
import { Box, Typography, Button } from '@mui/material';
import { Add, Inbox } from '@mui/icons-material';

const EmptyState = ({ title, message, onAction, actionLabel }) => {
  return (
    <Box
      sx={{
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
        justifyContent: 'center',
        minHeight: '400px',
        textAlign: 'center',
        p: 3,
      }}
    >
      <Inbox sx={{ fontSize: 80, color: 'text.secondary', mb: 2 }} />
      <Typography variant="h5" gutterBottom>
        {title}
      </Typography>
      <Typography variant="body1" color="text.secondary" sx={{ mb: 3 }}>
        {message}
      </Typography>
      {onAction && (
        <Button variant="contained" startIcon={<Add />} onClick={onAction}>
          {actionLabel || 'Add New'}
        </Button>
      )}
    </Box>
  );
};

export default EmptyState;
