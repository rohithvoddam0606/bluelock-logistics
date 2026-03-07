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
  IconButton,
  Chip,
  Grid,
  Paper,
} from '@mui/material';
import { Add, Close, LocalShipping, LocationOn } from '@mui/icons-material';
import { toast } from 'react-toastify';
import DataTable from '../components/DataTable';
import orderService from '../services/orderService';
import carrierService from '../services/carrierService';
import cargoService from '../services/cargoService';

const Orders = ({ searchQuery = '' }) => {
  const [orders, setOrders] = useState([]);
  const [carriers, setCarriers] = useState([]);
  const [cargos, setCargos] = useState([]);
  const [open, setOpen] = useState(false);
  const [trackingOpen, setTrackingOpen] = useState(false);
  const [selectedOrder, setSelectedOrder] = useState(null);
  const [formData, setFormData] = useState({
    orderDate: '',
    status: 'Pending',
    cost: '',
    carrierId: '',
    cargoId: '',
  });
  const [editMode, setEditMode] = useState(false);

  useEffect(() => {
    fetchOrders();
    fetchCarriers();
    fetchCargos();
  }, []);

  const fetchOrders = async () => {
    try {
      console.log('Fetching orders...');
      const response = await orderService.getAllOrders();
      console.log('Orders response:', response);
      console.log('Orders data:', response.data);
      console.log('Orders array:', response.data.data);
      setOrders(response.data.data || []);
    } catch (error) {
      console.error('Failed to fetch orders:', error);
      toast.error('Failed to fetch orders');
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

  const fetchCargos = async () => {
    try {
      const response = await cargoService.getAllCargo();
      setCargos(response.data.data || []);
    } catch (error) {
      console.error('Failed to fetch cargos:', error);
    }
  };

  const handleOpen = () => {
    setOpen(true);
    setEditMode(false);
    setFormData({
      orderDate: new Date().toISOString().split('T')[0],
      status: 'Pending',
      cost: '',
      carrierId: '',
      cargoId: '',
    });
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
        await orderService.updateOrder(formData.id, formData);
        toast.success('Order updated successfully');
      } else {
        await orderService.createOrder(formData);
        toast.success('Order created successfully');
      }
      fetchOrders();
      handleClose();
    } catch (error) {
      toast.error(error.response?.data?.message || 'Operation failed');
    }
  };

  const handleEdit = (order) => {
    setFormData(order);
    setEditMode(true);
    setOpen(true);
  };

  const handleDelete = async (order) => {
    if (window.confirm('Are you sure you want to delete this order?')) {
      try {
        await orderService.deleteOrder(order.id);
        toast.success('Order deleted successfully');
        fetchOrders();
      } catch (error) {
        toast.error('Failed to delete order');
      }
    }
  };

  const handleTrack = (order) => {
    setSelectedOrder(order);
    setTrackingOpen(true);
  };

  const handleCloseTracking = () => {
    setTrackingOpen(false);
    setSelectedOrder(null);
  };

  const columns = [
    { field: 'id', headerName: 'Order ID', width: 100 },
    { 
      field: 'orderdate', 
      headerName: 'Order Date',
      width: 150,
      renderCell: (row) => row.orderdate ? new Date(row.orderdate).toLocaleDateString() : 'N/A'
    },
    { 
      field: 'status', 
      headerName: 'Status',
      width: 130,
      renderCell: (row) => (
        <span style={{
          padding: '4px 12px',
          borderRadius: '12px',
          fontSize: '12px',
          fontWeight: 'bold',
          backgroundColor: 
            row.status === 'Delivered' ? '#4caf50' :
            row.status === 'In Transit' ? '#2196f3' :
            row.status === 'Pending' ? '#ff9800' : '#f44336',
          color: 'white'
        }}>
          {row.status}
        </span>
      )
    },
    { 
      field: 'cost', 
      headerName: 'Cost',
      width: 120,
      renderCell: (row) => `$${row.cost?.toFixed(2) || '0.00'}`
    },
    { 
      field: 'carrier', 
      headerName: 'Carrier',
      width: 180,
      renderCell: (row) => row.carrier?.name || 'Not Assigned'
    },
    { 
      field: 'cargo', 
      headerName: 'Cargo',
      width: 150,
      renderCell: (row) => row.cargo?.name || 'N/A'
    },
    {
      field: 'loading',
      headerName: 'Loading Address',
      width: 180,
      renderCell: (row) => row.loading?.address ? 
        `${row.loading.address.city}, ${row.loading.address.state}` : 'N/A'
    },
    {
      field: 'unloading',
      headerName: 'Unloading Address',
      width: 180,
      renderCell: (row) => row.unloading?.address ? 
        `${row.unloading.address.city}, ${row.unloading.address.state}` : 'N/A'
    },
    {
      field: 'track',
      headerName: 'Track',
      width: 120,
      renderCell: (row) => (
        (row.status === 'Pending' || row.status === 'In Transit') ? (
          <Button
            size="small"
            variant="outlined"
            startIcon={<LocalShipping />}
            onClick={() => handleTrack(row)}
          >
            Track
          </Button>
        ) : null
      )
    },
  ];

  return (
    <Box>
      <Box sx={{ display: 'flex', justifyContent: 'space-between', mb: 3 }}>
        <Typography variant="h4">Orders</Typography>
        <Button variant="contained" startIcon={<Add />} onClick={handleOpen}>
          Add Order
        </Button>
      </Box>
      <DataTable
        columns={columns}
        data={orders}
        onEdit={handleEdit}
        onDelete={handleDelete}
        searchQuery={searchQuery}
      />
      <Dialog open={open} onClose={handleClose} maxWidth="sm" fullWidth>
        <DialogTitle>{editMode ? 'Edit Order' : 'Add Order'}</DialogTitle>
        <DialogContent>
          <TextField
            margin="normal"
            fullWidth
            label="Order Date"
            name="orderDate"
            type="date"
            value={formData.orderDate}
            onChange={handleChange}
            InputLabelProps={{ shrink: true }}
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
            <MenuItem value="Pending">Pending</MenuItem>
            <MenuItem value="In Transit">In Transit</MenuItem>
            <MenuItem value="Delivered">Delivered</MenuItem>
            <MenuItem value="Cancelled">Cancelled</MenuItem>
          </TextField>
          <TextField
            margin="normal"
            fullWidth
            label="Cost"
            name="cost"
            type="number"
            value={formData.cost}
            onChange={handleChange}
          />
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
            label="Cargo"
            name="cargoId"
            value={formData.cargoId}
            onChange={handleChange}
          >
            {cargos.map((cargo) => (
              <MenuItem key={cargo.id} value={cargo.id}>
                {cargo.name}
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

      {/* Tracking Dialog */}
      <Dialog open={trackingOpen} onClose={handleCloseTracking} maxWidth="md" fullWidth>
        <DialogTitle>
          <Box sx={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
            <Box sx={{ display: 'flex', alignItems: 'center', gap: 1 }}>
              <LocalShipping color="primary" />
              <Typography variant="h6">Order Tracking - #{selectedOrder?.id}</Typography>
            </Box>
            <IconButton onClick={handleCloseTracking}>
              <Close />
            </IconButton>
          </Box>
        </DialogTitle>
        <DialogContent>
          {selectedOrder && (
            <Box>
              {/* Order Status */}
              <Paper elevation={2} sx={{ p: 2, mb: 3, bgcolor: '#f5f5f5' }}>
                <Grid container spacing={2}>
                  <Grid item xs={12} md={6}>
                    <Typography variant="subtitle2" color="textSecondary">Order Status</Typography>
                    <Chip 
                      label={selectedOrder.status}
                      color={selectedOrder.status === 'In Transit' ? 'primary' : 'warning'}
                      sx={{ mt: 1 }}
                    />
                  </Grid>
                  <Grid item xs={12} md={6}>
                    <Typography variant="subtitle2" color="textSecondary">Carrier</Typography>
                    <Typography variant="body1" sx={{ mt: 1 }}>
                      {selectedOrder.carrier?.name || 'Not Assigned'}
                    </Typography>
                  </Grid>
                  <Grid item xs={12} md={6}>
                    <Typography variant="subtitle2" color="textSecondary">Cargo</Typography>
                    <Typography variant="body1" sx={{ mt: 1 }}>
                      {selectedOrder.cargo?.name || 'N/A'}
                    </Typography>
                  </Grid>
                  <Grid item xs={12} md={6}>
                    <Typography variant="subtitle2" color="textSecondary">Order Date</Typography>
                    <Typography variant="body1" sx={{ mt: 1 }}>
                      {selectedOrder.orderdate ? new Date(selectedOrder.orderdate).toLocaleDateString() : 'N/A'}
                    </Typography>
                  </Grid>
                </Grid>
              </Paper>

              {/* Loading Address */}
              <Paper elevation={2} sx={{ p: 2, mb: 2, border: '2px solid #4caf50' }}>
                <Box sx={{ display: 'flex', alignItems: 'center', gap: 1, mb: 2 }}>
                  <LocationOn sx={{ color: '#4caf50' }} />
                  <Typography variant="h6" color="#4caf50">Loading Point (Origin)</Typography>
                </Box>
                {selectedOrder.loading?.address ? (
                  <Box>
                    <Typography variant="body1" sx={{ fontWeight: 'bold' }}>
                      {selectedOrder.loading.address.street}
                    </Typography>
                    <Typography variant="body2" color="textSecondary">
                      {selectedOrder.loading.address.city}, {selectedOrder.loading.address.state} - {selectedOrder.loading.address.pincode}
                    </Typography>
                    {selectedOrder.loading.date && (
                      <Typography variant="body2" sx={{ mt: 1 }}>
                        <strong>Loading Date:</strong> {new Date(selectedOrder.loading.date).toLocaleDateString()}
                        {selectedOrder.loading.time && ` at ${selectedOrder.loading.time}`}
                      </Typography>
                    )}
                  </Box>
                ) : (
                  <Typography color="textSecondary">No loading address available</Typography>
                )}
              </Paper>

              {/* Route Indicator */}
              <Box sx={{ display: 'flex', justifyContent: 'center', my: 2 }}>
                <Box sx={{ 
                  width: 4, 
                  height: 60, 
                  bgcolor: '#2196f3',
                  position: 'relative',
                  '&::before': {
                    content: '""',
                    position: 'absolute',
                    top: '50%',
                    left: '50%',
                    transform: 'translate(-50%, -50%)',
                    width: 0,
                    height: 0,
                    borderLeft: '8px solid transparent',
                    borderRight: '8px solid transparent',
                    borderTop: '12px solid #2196f3',
                  }
                }} />
              </Box>

              {/* Unloading Address */}
              <Paper elevation={2} sx={{ p: 2, border: '2px solid #f44336' }}>
                <Box sx={{ display: 'flex', alignItems: 'center', gap: 1, mb: 2 }}>
                  <LocationOn sx={{ color: '#f44336' }} />
                  <Typography variant="h6" color="#f44336">Unloading Point (Destination)</Typography>
                </Box>
                {selectedOrder.unloading?.address ? (
                  <Box>
                    <Typography variant="body1" sx={{ fontWeight: 'bold' }}>
                      {selectedOrder.unloading.address.street}
                    </Typography>
                    <Typography variant="body2" color="textSecondary">
                      {selectedOrder.unloading.address.city}, {selectedOrder.unloading.address.state} - {selectedOrder.unloading.address.pincode}
                    </Typography>
                    {selectedOrder.unloading.date && (
                      <Typography variant="body2" sx={{ mt: 1 }}>
                        <strong>Expected Delivery:</strong> {new Date(selectedOrder.unloading.date).toLocaleDateString()}
                        {selectedOrder.unloading.time && ` at ${selectedOrder.unloading.time}`}
                      </Typography>
                    )}
                  </Box>
                ) : (
                  <Typography color="textSecondary">No unloading address available</Typography>
                )}
              </Paper>

              {/* Additional Info */}
              <Paper elevation={1} sx={{ p: 2, mt: 2, bgcolor: '#e3f2fd' }}>
                <Typography variant="body2" color="textSecondary">
                  <strong>Estimated Distance:</strong> Calculating route...
                </Typography>
                <Typography variant="body2" color="textSecondary" sx={{ mt: 1 }}>
                  <strong>Total Cost:</strong> ${selectedOrder.cost?.toFixed(2) || '0.00'}
                </Typography>
              </Paper>
            </Box>
          )}
        </DialogContent>
        <DialogActions>
          <Button onClick={handleCloseTracking}>Close</Button>
        </DialogActions>
      </Dialog>
    </Box>
  );
};

export default Orders;
