import React, { useState, useEffect } from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate, useLocation } from 'react-router-dom';
import { Box, Toolbar, CssBaseline, ThemeProvider, createTheme } from '@mui/material';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

import Navbar from './components/Navbar';
import Sidebar from './components/Sidebar';
import Login from './pages/Login';
import Register from './pages/Register';
import Dashboard from './pages/Dashboard';
import Orders from './pages/Orders';
import Cargo from './pages/Cargo';
import Trucks from './pages/Trucks';
import Drivers from './pages/Drivers';
import Carriers from './pages/Carriers';
import Addresses from './pages/Addresses';
import Users from './pages/Users';
import authService from './services/authService';

const theme = createTheme({
  palette: {
    primary: {
      main: '#1976d2',
    },
    secondary: {
      main: '#dc004e',
    },
  },
});

const PrivateRoute = ({ children }) => {
  return authService.isAuthenticated() ? children : <Navigate to="/login" />;
};

const AppContent = () => {
  const [mobileOpen, setMobileOpen] = useState(false);
  const [searchQuery, setSearchQuery] = useState('');
  const location = useLocation();

  // Clear search when navigating to a different page
  useEffect(() => {
    setSearchQuery('');
  }, [location.pathname]);

  const handleDrawerToggle = () => {
    setMobileOpen(!mobileOpen);
  };

  const handleSearch = (query) => {
    setSearchQuery(query);
  };

  return (
    <>
      <Routes>
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route
          path="/*"
          element={
            <PrivateRoute>
              <Box sx={{ display: 'flex' }}>
                <Navbar onMenuClick={handleDrawerToggle} onSearch={handleSearch} searchQuery={searchQuery} />
                <Sidebar mobileOpen={mobileOpen} onDrawerToggle={handleDrawerToggle} />
                <Box
                  component="main"
                  sx={{
                    flexGrow: 1,
                    p: 3,
                    width: { sm: `calc(100% - 240px)` },
                  }}
                >
                  <Toolbar />
                  <Routes>
                    <Route path="/" element={<Navigate to="/dashboard" />} />
                    <Route path="/dashboard" element={<Dashboard searchQuery={searchQuery} />} />
                    <Route path="/orders" element={<Orders searchQuery={searchQuery} />} />
                    <Route path="/cargo" element={<Cargo searchQuery={searchQuery} />} />
                    <Route path="/trucks" element={<Trucks searchQuery={searchQuery} />} />
                    <Route path="/drivers" element={<Drivers searchQuery={searchQuery} />} />
                    <Route path="/carriers" element={<Carriers searchQuery={searchQuery} />} />
                    <Route path="/addresses" element={<Addresses searchQuery={searchQuery} />} />
                    <Route path="/users" element={<Users searchQuery={searchQuery} />} />
                  </Routes>
                </Box>
              </Box>
            </PrivateRoute>
          }
        />
      </Routes>
      <ToastContainer
        position="top-right"
        autoClose={3000}
        hideProgressBar={false}
        newestOnTop
        closeOnClick
        rtl={false}
        pauseOnFocusLoss
        draggable
        pauseOnHover
      />
    </>
  );
};

function App() {
  return (
    <ThemeProvider theme={theme}>
      <Router>
        <CssBaseline />
        <AppContent />
      </Router>
    </ThemeProvider>
  );
}

export default App;
