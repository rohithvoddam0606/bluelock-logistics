import api from './api';

const driverService = {
  getAllDrivers: () => api.get('/drivers'),
  getDriverById: (id) => api.get(`/drivers/${id}`),
  createDriver: (driverData) => api.post('/drivers', driverData),
  updateDriver: (id, driverData) => api.put(`/drivers/${id}`, driverData),
  deleteDriver: (id) => api.delete(`/drivers/${id}`),
  assignDriverToTruck: (driverId, truckId) => api.put(`/drivers/${driverId}/truck/${truckId}`),
};

export default driverService;
