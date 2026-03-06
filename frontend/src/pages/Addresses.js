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
} from '@mui/material';
import { Add } from '@mui/icons-material';
import { toast } from 'react-toastify';
import DataTable from '../components/DataTable';
import addressService from '../services/addressService';

const Addresses = () => {
  const [addresses, setAddresses] = useState([]);
  const [open, setOpen] = useState(false);
  const [formData, setFormData] = useState({
    street: '',
    city: '',
    pincode: '',
    state: '',
  });
  const [editMode, setEditMode] = useState(false);

  useEffect(() => {
    fetchAddresses();
  }, []);

  const fetchAddresses = async () => {
    try {
      const response = await addressService.getAllAddresses();
      setAddresses(response.data.data || []);
    } catch (error) {
      console.error('Failed to fetch addresses:', error);
      toast.error('Failed to fetch addresses');
    }
  };

  const handleOpen = () => {
    setOpen(true);
    setEditMode(false);
    setFormData({ street: '', city: '', pincode: '', state: '' });
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
        await addressService.updateAddress(formData.id, formData);
        toast.success('Address updated successfully');
      } else {
        await addressService.createAddress(formData);
        toast.success('Address created successfully');
      }
      fetchAddresses();
      handleClose();
    } catch (error) {
      toast.error(error.response?.data?.message || 'Operation failed');
    }
  };

  const handleEdit = (address) => {
    setFormData(address);
    setEditMode(true);
    setOpen(true);
  };

  const handleDelete = async (address) => {
    if (window.confirm('Are you sure you want to delete this address?')) {
      try {
        await addressService.deleteAddress(address.id);
        toast.success('Address deleted successfully');
        fetchAddresses();
      } catch (error) {
        toast.error('Failed to delete address');
      }
    }
  };

  const columns = [
    { field: 'id', headerName: 'Address ID', width: 100 },
    { field: 'street', headerName: 'Street Address', width: 250 },
    { field: 'city', headerName: 'City', width: 150 },
    { 
      field: 'pincode', 
      headerName: 'Pincode',
      width: 120
    },
    { field: 'state', headerName: 'State', width: 150 },
    {
      field: 'fullAddress',
      headerName: 'Full Address',
      width: 350,
      renderCell: (row) => `${row.street}, ${row.city}, ${row.state} - ${row.pincode}`
    },
  ];

  return (
    <Box>
      <Box sx={{ display: 'flex', justifyContent: 'space-between', mb: 3 }}>
        <Typography variant="h4">Address Management</Typography>
        <Button variant="contained" startIcon={<Add />} onClick={handleOpen}>
          Add Address
        </Button>
      </Box>
      <DataTable
        columns={columns}
        data={addresses}
        onEdit={handleEdit}
        onDelete={handleDelete}
      />
      <Dialog open={open} onClose={handleClose} maxWidth="sm" fullWidth>
        <DialogTitle>{editMode ? 'Edit Address' : 'Add Address'}</DialogTitle>
        <DialogContent>
          <TextField
            margin="normal"
            fullWidth
            label="Street"
            name="street"
            value={formData.street}
            onChange={handleChange}
            required
          />
          <TextField
            margin="normal"
            fullWidth
            label="City"
            name="city"
            value={formData.city}
            onChange={handleChange}
            required
          />
          <TextField
            margin="normal"
            fullWidth
            label="Pincode"
            name="pincode"
            value={formData.pincode}
            onChange={handleChange}
            required
          />
          <TextField
            margin="normal"
            fullWidth
            label="State"
            name="state"
            value={formData.state}
            onChange={handleChange}
            required
          />
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

export default Addresses;
