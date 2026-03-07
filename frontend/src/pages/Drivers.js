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
import driverService from '../services/driverService';
import truckService from '../services/truckService';
import carrierService from '../services/carrierService';

const Drivers = ({ searchQuery = '' }) => {
  const [drivers, setDrivers] = useState([]);
  const [trucks, setTrucks] = useState([]);
  const [carriers, setCarriers] = useState([]);
  const [open, setOpen] = useState(false);
  const [formData, setFormData] = useState({
    name: '',
    contact: '',
    truckId: '',
    carrierId: '',
  });
  const [editMode, setEditMode] = useState(false);

  useEffect(() => {
    fetchDrivers();
    fetchTrucks();
    fetchCarriers();
  }, []);

  const fetchDrivers = async () => {
    try {
      const response = await driverService.getAllDrivers();
      setDrivers(response.data.data || []);
    } catch (error) {
      console.error('Failed to fetch drivers:', error);
      toast.error('Failed to fetch drivers');
    }
  };

  const fetchTrucks = async () => {
    try {
      const response = await truckService.getAllTrucks();
      setTrucks(response.data.data || []);
    } catch (error) {
      console.error('Failed to fetch trucks:', error);
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

  const handleOpen = () => {
    setOpen(true);
    setEditMode(false);
    setFormData({ name: '', contact: '', truckId: '', carrierId: '' });
  };

  const handleClose = () => {
    setOpen(false);
  };

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async () => {
    try {
      if (editMode) {
        await driverService.updateDriver(formData.id, formData);
        toast.success('Driver updated successfully');
      } else {
        await driverService.createDriver(formData);
        toast.success('Driver created successfully');
      }
      fetchDrivers();
      handleClose();
    } catch (error) {
      toast.error(error.response?.data?.message || 'Operation failed');
    }
  };

  const handleEdit = (driver) => {
    setFormData(driver);
    setEditMode(true);
    setOpen(true);
  };

  const handleDelete = async (driver) => {
    if (window.confirm('Are you sure you want to delete this driver?')) {
      try {
        await driverService.deleteDriver(driver.id);
        toast.success('Driver deleted successfully');
        fetchDrivers();
      } catch (error) {
        toast.error('Failed to delete driver');
      }
    }
  };

  const columns = [
    { field: 'id', headerName: 'Driver ID', width: 100 },
    { field: 'name', headerName: 'Driver Name', width: 180 },
    { 
      field: 'contact', 
      headerName: 'Contact Number',
      width: 150,
      renderCell: (row) => row.contact ? `+${row.contact}` : 'N/A'
    },
    { 
      field: 'truck', 
      headerName: 'Assigned Truck',
      width: 180,
      renderCell: (row) => row.truck ? `${row.truck.name} (#${row.truck.number})` : 'Not Assigned'
    },
    { 
      field: 'carrier', 
      headerName: 'Carrier Company',
      width: 200,
      renderCell: (row) => row.carrier?.name || 'Not Assigned'
    },
  ];

  return (
    <Box>
      <Box sx={{ display: 'flex', justifyContent: 'space-between', mb: 3 }}>
        <Typography variant="h4">Driver Management</Typography>
        <Button variant="contained" startIcon={<Add />} onClick={handleOpen}>
          Add Driver
        </Button>
      </Box>
      <DataTable
        columns={columns}
        data={drivers}
        onEdit={handleEdit}
        onDelete={handleDelete}
        searchQuery={searchQuery}
      />
      <Dialog open={open} onClose={handleClose} maxWidth="sm" fullWidth>
        <DialogTitle>{editMode ? 'Edit Driver' : 'Add Driver'}</DialogTitle>
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
            label="Contact"
            name="contact"
            value={formData.contact}
            onChange={handleChange}
            required
          />
          <TextField
            margin="normal"
            fullWidth
            select
            label="Truck"
            name="truckId"
            value={formData.truckId}
            onChange={handleChange}
          >
            <MenuItem value="">Unassigned</MenuItem>
            {trucks.map((truck) => (
              <MenuItem key={truck.id} value={truck.id}>
                {truck.name} - {truck.number}
              </MenuItem>
            ))}
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

export default Drivers;
