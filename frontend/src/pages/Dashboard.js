import React, { useState, useEffect } from 'react';
import { Grid, Paper, Typography, Box } from '@mui/material';
import {
  ShoppingCart,
  LocalShipping,
  Person,
  Business,
  CheckCircle,
  HourglassEmpty,
} from '@mui/icons-material';
import { BarChart, Bar, XAxis, YAxis, CartesianGrid, Tooltip, Legend, ResponsiveContainer, PieChart, Pie, Cell } from 'recharts';
import DashboardCard from '../components/DashboardCard';
import orderService from '../services/orderService';
import truckService from '../services/truckService';
import driverService from '../services/driverService';
import carrierService from '../services/carrierService';

const Dashboard = () => {
  const [stats, setStats] = useState({
    totalOrders: 0,
    activeTrucks: 0,
    totalDrivers: 0,
    totalCarriers: 0,
    deliveredOrders: 0,
    pendingOrders: 0,
  });

  useEffect(() => {
    fetchDashboardData();
  }, []);

  const fetchDashboardData = async () => {
    try {
      const [orders, trucks, drivers, carriers] = await Promise.all([
        orderService.getAllOrders(),
        truckService.getAllTrucks(),
        driverService.getAllDrivers(),
        carrierService.getAllCarriers(),
      ]);

      // Backend returns ResponseStructure with data field
      const ordersData = orders.data.data || [];
      const trucksData = trucks.data.data || [];
      const driversData = drivers.data.data || [];
      const carriersData = carriers.data.data || [];

      const delivered = ordersData.filter(o => o.status === 'Delivered').length;
      const pending = ordersData.filter(o => o.status === 'Pending').length;

      setStats({
        totalOrders: ordersData.length,
        activeTrucks: trucksData.filter(t => t.status === 'Available' || t.status === 'In Transit').length,
        totalDrivers: driversData.length,
        totalCarriers: carriersData.length,
        deliveredOrders: delivered,
        pendingOrders: pending,
      });
    } catch (error) {
      console.error('Error fetching dashboard data:', error);
    }
  };

  const orderData = [
    { name: 'Delivered', value: stats.deliveredOrders },
    { name: 'Pending', value: stats.pendingOrders },
    { name: 'In Transit', value: stats.totalOrders - stats.deliveredOrders - stats.pendingOrders },
  ];

  const COLORS = ['#4caf50', '#ff9800', '#2196f3'];

  return (
    <Box>
      <Typography variant="h4" gutterBottom>
        Dashboard
      </Typography>
      <Grid container spacing={3}>
        <Grid item xs={12} sm={6} md={4}>
          <DashboardCard
            title="Total Orders"
            value={stats.totalOrders}
            icon={<ShoppingCart />}
            color="#2196f3"
          />
        </Grid>
        <Grid item xs={12} sm={6} md={4}>
          <DashboardCard
            title="Active Trucks"
            value={stats.activeTrucks}
            icon={<LocalShipping />}
            color="#4caf50"
          />
        </Grid>
        <Grid item xs={12} sm={6} md={4}>
          <DashboardCard
            title="Total Drivers"
            value={stats.totalDrivers}
            icon={<Person />}
            color="#ff9800"
          />
        </Grid>
        <Grid item xs={12} sm={6} md={4}>
          <DashboardCard
            title="Total Carriers"
            value={stats.totalCarriers}
            icon={<Business />}
            color="#9c27b0"
          />
        </Grid>
        <Grid item xs={12} sm={6} md={4}>
          <DashboardCard
            title="Delivered Orders"
            value={stats.deliveredOrders}
            icon={<CheckCircle />}
            color="#4caf50"
          />
        </Grid>
        <Grid item xs={12} sm={6} md={4}>
          <DashboardCard
            title="Pending Orders"
            value={stats.pendingOrders}
            icon={<HourglassEmpty />}
            color="#ff9800"
          />
        </Grid>
        <Grid item xs={12} md={8}>
          <Paper sx={{ p: 3 }}>
            <Typography variant="h6" gutterBottom>
              Order Statistics
            </Typography>
            <ResponsiveContainer width="100%" height={300}>
              <BarChart data={orderData}>
                <CartesianGrid strokeDasharray="3 3" />
                <XAxis dataKey="name" />
                <YAxis />
                <Tooltip />
                <Legend />
                <Bar dataKey="value" fill="#2196f3" />
              </BarChart>
            </ResponsiveContainer>
          </Paper>
        </Grid>
        <Grid item xs={12} md={4}>
          <Paper sx={{ p: 3 }}>
            <Typography variant="h6" gutterBottom>
              Order Distribution
            </Typography>
            <ResponsiveContainer width="100%" height={300}>
              <PieChart>
                <Pie
                  data={orderData}
                  cx="50%"
                  cy="50%"
                  labelLine={false}
                  label={(entry) => entry.name}
                  outerRadius={80}
                  fill="#8884d8"
                  dataKey="value"
                >
                  {orderData.map((entry, index) => (
                    <Cell key={`cell-${index}`} fill={COLORS[index % COLORS.length]} />
                  ))}
                </Pie>
                <Tooltip />
              </PieChart>
            </ResponsiveContainer>
          </Paper>
        </Grid>
      </Grid>
    </Box>
  );
};

export default Dashboard;
