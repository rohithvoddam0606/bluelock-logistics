import React, { useState, useEffect } from 'react';
import {
  Box,
  Button,
  Dialog,
  DialogTitle,
  DialogContent,
  DialogActions,
  TextField,
  Typography,
  MenuItem,
} from '@mui/material';
import { Add } from '@mui/icons-material';
import { toast } from 'react-toastify';
import DataTable from '../components/DataTable';
import truckService from '../services/truckService';
import carrierService from '../services/carrierService';

const Trucks = ({ searchQuery = '' }) => {
  const [trucks, setTrucks] = useState([]);
  const [carriers, setCarriers] = useState([]);
  const [addresses, setAddresses] = useState([]);
  const [open, setOpen] = useState(false);
  const [formData, setFormData] = useState({
    name: '',
    number: '',
    capacity: '',
    status: 'Active',
    carrierId: '',
    currentLocationId: '',
  });
  const [editMode, setEditMode] = useState(false);

  useEffect(() => {
    fetchTrucks();
    fetchCarriers();
    fetchAddresses();
  }, []);

  const fetchTrucks = async () => {
    try {
      console.log('Fetching trucks...');
      const response = await truckService.getAllTrucks();
      console.log('Trucks response:', response);
      console.log('Trucks data:', response.data);
      console.log('Trucks array:', response.data.data);
      setTrucks(response.data.data || []);
    } catch (error) {
      console.error('Failed to fetch trucks:', error);
      toast.error('Failed to fetch trucks');
    }
  };

  const fetchCarriers = async () => {
    try {
      const response = await carrierService.getAllCarriers();
      setCarriers(response.data.data || []);
    } catch (error) {
      console.error('Failed to fetch carriers:', error);
    }
  };

  const fetchAddresses = async () => {
    try {
      const response = await fetch('http://localhost:8080/api/addresses');
      const data = await response.json();
      setAddresses(data.data || []);
    } catch (error) {
      console.error('Failed to fetch addresses:', error);
    }
  };

  const handleOpen = () => {
    setOpen(true);
    setEditMode(false);
    setFormData({ name: '', number: '', capacity: '', status: 'Available', carrierId: '', currentLocationId: '' });
  };

  const handleClose = () => {
    setOpen(false);
  };

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async () => {
    try {
      const truckData = {
        ...formData,
        carrier: formData.carrierId ? { id: formData.carrierId } : null,
        currentLocation: formData.currentLocationId ? { id: formData.currentLocationId } : null,
      };
      
      if (editMode) {
        await truckService.updateTruck(formData.id, truckData);
        toast.success('Truck updated successfully');
      } else {
        await truckService.createTruck(truckData);
        toast.success('Truck created successfully');
      }
      fetchTrucks();
      handleClose();
    } catch (error) {
      toast.error(error.response?.data?.message || 'Operation failed');
    }
  };

  const handleEdit = (truck) => {
    setFormData({
      ...truck,
      carrierId: truck.carrier?.id || '',
      currentLocationId: truck.currentLocation?.id || '',
    });
    setEditMode(true);
    setOpen(true);
  };

  const handleDelete = async (truck) => {
    if (window.confirm('Are you sure you want to delete this truck?')) {
      try {
        await truckService.deleteTruck(truck.id);
        toast.success('Truck deleted successfully');
        fetchTrucks();
      } catch (error) {
        toast.error('Failed to delete truck');
      }
    }
  };

  const columns = [
    { field: 'id', headerName: 'Truck ID', width: 100 },
    { field: 'name', headerName: 'Truck Name', width: 150 },
    { 
      field: 'number', 
      headerName: 'Truck Number',
      width: 140,
      renderCell: (row) => `#${row.number}`
    },
    { 
      field: 'capacity', 
      headerName: 'Capacity',
      width: 130,
      renderCell: (row) => `${row.capacity.toLocaleString()} kg`
    },
    { 
      field: 'status', 
      headerName: 'Status',
      width: 140,
      renderCell: (row) => (
        <span style={{
          padding: '4px 12px',
          borderRadius: '12px',
          fontSize: '12px',
          fontWeight: 'bold',
          backgroundColor: 
            row.status === 'Available' ? '#4caf50' :
            row.status === 'In Transit' ? '#2196f3' :
            row.status === 'Maintenance' ? '#ff9800' : '#9e9e9e',
          color: 'white'
        }}>
          {row.status}
        </span>
      )
    },
    { 
      field: 'carrier', 
      headerName: 'Carrier Company',
      width: 200,
      renderCell: (row) => row.carrier?.name || 'Not Assigned'
    },
    { 
      field: 'currentLocation', 
      headerName: 'Current Location',
      width: 250,
      renderCell: (row) => row.currentLocation 
        ? `${row.currentLocation.city}, ${row.currentLocation.state}` 
        : 'Not Set'
    },
  ];

  return (
    <Box>
      <Box sx={{ display: 'flex', justifyContent: 'space-between', mb: 3 }}>
        <Typography variant="h4">Truck Management</Typography>
        <Button variant="contained" startIcon={<Add />} onClick={handleOpen}>
          Add Truck
        </Button>
      </Box>
      <DataTable
        columns={columns}
        data={trucks}
        onEdit={handleEdit}
        onDelete={handleDelete}
        searchQuery={searchQuery}
      />
      <Dialog open={open} onClose={handleClose} maxWidth="sm" fullWidth>
        <DialogTitle>{editMode ? 'Edit Truck' : 'Add Truck'}</DialogTitle>
        <DialogContent>
          <TextField
            margin="normal"
            fullWidth
            label="Name"
            name="name"
            value={formData.name}
            onChange={handleChange}
            required
          />
          <TextField
            margin="normal"
            fullWidth
            label="Number"
            name="number"
            value={formData.number}
            onChange={handleChange}
            required
          />
          <TextField
            margin="normal"
            fullWidth
            label="Capacity (kg)"
            name="capacity"
            type="number"
            value={formData.capacity}
            onChange={handleChange}
            required
          />
          <TextField
            margin="normal"
            fullWidth
            select
            label="Status"
            name="status"
            value={formData.status}
            onChange={handleChange}
          >
            <MenuItem value="Available">Available</MenuItem>
            <MenuItem value="In Transit">In Transit</MenuItem>
            <MenuItem value="Maintenance">Maintenance</MenuItem>
            <MenuItem value="Inactive">Inactive</MenuItem>
          </TextField>
          <TextField
            margin="normal"
            fullWidth
            select
            label="Carrier"
            name="carrierId"
            value={formData.carrierId}
            onChange={handleChange}
          >
            {carriers.map((carrier) => (
              <MenuItem key={carrier.id} value={carrier.id}>
                {carrier.name}
              </MenuItem>
            ))}
          </TextField>
          <TextField
            margin="normal"
            fullWidth
            select
            label="Current Location"
            name="currentLocationId"
            value={formData.currentLocationId}
            onChange={handleChange}
          >
            <MenuItem value="">None</MenuItem>
            {addresses.map((address) => (
              <MenuItem key={address.id} value={address.id}>
                {address.street}, {address.city}, {address.state} - {address.pincode}
              </MenuItem>
            ))}
          </TextField>
        </DialogContent>
        <DialogActions>
          <Button onClick={handleClose}>Cancel</Button>
          <Button onClick={handleSubmit} variant="contained">
            {editMode ? 'Update' : 'Create'}
          </Button>
        </DialogActions>
      </Dialog>
    </Box>
  );
};

export default Trucks;
