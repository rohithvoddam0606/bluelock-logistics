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
import carrierService from '../services/carrierService';

const Carriers = ({ searchQuery = '' }) => {
  const [carriers, setCarriers] = useState([]);
  const [open, setOpen] = useState(false);
  const [formData, setFormData] = useState({
    name: '',
    email: '',
    contact: '',
  });
  const [editMode, setEditMode] = useState(false);

  useEffect(() => {
    fetchCarriers();
  }, []);

  const fetchCarriers = async () => {
    try {
      const response = await carrierService.getAllCarriers();
      setCarriers(response.data.data || []);
    } catch (error) {
      console.error('Failed to fetch carriers:', error);
      toast.error('Failed to fetch carriers');
    }
  };

  const handleOpen = () => {
    setOpen(true);
    setEditMode(false);
    setFormData({ name: '', email: '', contact: '' });
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
        await carrierService.updateCarrier(formData.id, formData);
        toast.success('Carrier updated successfully');
      } else {
        await carrierService.createCarrier(formData);
        toast.success('Carrier created successfully');
      }
      fetchCarriers();
      handleClose();
    } catch (error) {
      toast.error(error.response?.data?.message || 'Operation failed');
    }
  };

  const handleEdit = (carrier) => {
    setFormData(carrier);
    setEditMode(true);
    setOpen(true);
  };

  const handleDelete = async (carrier) => {
    if (window.confirm('Are you sure you want to delete this carrier?')) {
      try {
        await carrierService.deleteCarrier(carrier.id);
        toast.success('Carrier deleted successfully');
        fetchCarriers();
      } catch (error) {
        toast.error('Failed to delete carrier');
      }
    }
  };

  const columns = [
    { field: 'id', headerName: 'Carrier ID', width: 100 },
    { field: 'name', headerName: 'Company Name', width: 220 },
    { 
      field: 'email', 
      headerName: 'Email Address',
      width: 250
    },
    { 
      field: 'contact', 
      headerName: 'Contact Number',
      width: 180,
      renderCell: (row) => row.contact ? `+${row.contact}` : 'N/A'
    },
  ];

  return (
    <Box>
      <Box sx={{ display: 'flex', justifyContent: 'space-between', mb: 3 }}>
        <Typography variant="h4">Carrier Management</Typography>
        <Button variant="contained" startIcon={<Add />} onClick={handleOpen}>
          Add Carrier
        </Button>
      </Box>
      <DataTable
        columns={columns}
        data={carriers}
        onEdit={handleEdit}
        onDelete={handleDelete}
        searchQuery={searchQuery}
      />
      <Dialog open={open} onClose={handleClose} maxWidth="sm" fullWidth>
        <DialogTitle>{editMode ? 'Edit Carrier' : 'Add Carrier'}</DialogTitle>
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
            label="Email"
            name="email"
            type="email"
            value={formData.email}
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

export default Carriers;
