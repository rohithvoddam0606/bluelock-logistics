import React from 'react';
import { Box, Typography, IconButton, Tooltip } from '@mui/material';
import { ArrowBack } from '@mui/icons-material';
import { useNavigate } from 'react-router-dom';

const PageHeader = ({ title, showBackButton = true, backPath = '/dashboard', children }) => {
  const navigate = useNavigate();

  const handleBack = () => {
    navigate(backPath);
  };

  return (
    <Box sx={{ display: 'flex', alignItems: 'center', gap: 2, mb: 3 }}>
      {showBackButton && (
        <Tooltip title="Back to Dashboard">
          <IconButton
            onClick={handleBack}
            sx={{
              bgcolor: 'primary.main',
              color: 'white',
              '&:hover': {
                bgcolor: 'primary.dark',
              },
            }}
          >
            <ArrowBack />
          </IconButton>
        </Tooltip>
      )}
      <Typography variant="h4" sx={{ flexGrow: 1 }}>
        {title}
      </Typography>
      {children}
    </Box>
  );
};

export default PageHeader;
