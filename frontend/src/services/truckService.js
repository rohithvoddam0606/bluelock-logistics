import api from './api';

const truckService = {
  getAllTrucks: () => api.get('/trucks'),
  getTruckById: (id) => api.get(`/trucks/${id}`),
  createTruck: (truckData) => api.post('/trucks', truckData),
  updateTruck: (id, truckData) => api.put(`/trucks/${id}`, truckData),
  deleteTruck: (id) => api.delete(`/trucks/${id}`),
  getAvailableTrucks: () => api.get('/trucks/available'),
};

export default truckService;
