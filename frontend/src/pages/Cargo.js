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
import cargoService from '../services/cargoService';

const Cargo = () => {
  const [cargos, setCargos] = useState([]);
  const [open, setOpen] = useState(false);
  const [formData, setFormData] = useState({
    name: '',
    description: '',
    weight: '',
    count: '',
  });
  const [editMode, setEditMode] = useState(false);

  useEffect(() => {
    fetchCargos();
  }, []);

  const fetchCargos = async () => {
    try {
      const response = await cargoService.getAllCargo();
      setCargos(response.data.data || []);
    } catch (error) {
      console.error('Failed to fetch cargo:', error);
      toast.error('Failed to fetch cargo');
    }
  };

  const handleOpen = () => {
    setOpen(true);
    setEditMode(false);
    setFormData({ name: '', description: '', weight: '', count: '' });
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
        await cargoService.updateCargo(formData.id, formData);
        toast.success('Cargo updated successfully');
      } else {
        await cargoService.createCargo(formData);
        toast.success('Cargo created successfully');
      }
      fetchCargos();
      handleClose();
    } catch (error) {
      toast.error(error.response?.data?.message || 'Operation failed');
    }
  };

  const handleEdit = (cargo) => {
    setFormData(cargo);
    setEditMode(true);
    setOpen(true);
  };

  const handleDelete = async (cargo) => {
    if (window.confirm('Are you sure you want to delete this cargo?')) {
      try {
        await cargoService.deleteCargo(cargo.id);
        toast.success('Cargo deleted successfully');
        fetchCargos();
      } catch (error) {
        toast.error('Failed to delete cargo');
      }
    }
  };

  const columns = [
    { field: 'id', headerName: 'Cargo ID', width: 100 },
    { field: 'name', headerName: 'Cargo Name', width: 180 },
    { field: 'description', headerName: 'Description', width: 300 },
    { 
      field: 'weight', 
      headerName: 'Weight (kg)',
      width: 130,
      renderCell: (row) => `${row.weight} kg`
    },
    { 
      field: 'count', 
      headerName: 'Quantity',
      width: 120,
      renderCell: (row) => `${row.count} items`
    },
    {
      field: 'totalWeight',
      headerName: 'Total Weight',
      width: 150,
      renderCell: (row) => `${(row.weight * row.count).toLocaleString()} kg`
    },
  ];

  return (
    <Box>
      <Box sx={{ display: 'flex', justifyContent: 'space-between', mb: 3 }}>
        <Typography variant="h4">Cargo Management</Typography>
        <Button variant="contained" startIcon={<Add />} onClick={handleOpen}>
          Add Cargo
        </Button>
      </Box>
      <DataTable
        columns={columns}
        data={cargos}
        onEdit={handleEdit}
        onDelete={handleDelete}
      />
      <Dialog open={open} onClose={handleClose} maxWidth="sm" fullWidth>
        <DialogTitle>{editMode ? 'Edit Cargo' : 'Add Cargo'}</DialogTitle>
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
            label="Description"
            name="description"
            multiline
            rows={3}
            value={formData.description}
            onChange={handleChange}
          />
          <TextField
            margin="normal"
            fullWidth
            label="Weight (kg)"
            name="weight"
            type="number"
            value={formData.weight}
            onChange={handleChange}
            required
          />
          <TextField
            margin="normal"
            fullWidth
            label="Count"
            name="count"
            type="number"
            value={formData.count}
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

export default Cargo;
