import api from './api';

const carrierService = {
  getAllCarriers: () => api.get('/carriers'),
  getCarrierById: (id) => api.get(`/carriers/${id}`),
  createCarrier: (carrierData) => api.post('/carriers', carrierData),
  updateCarrier: (id, carrierData) => api.put(`/carriers/${id}`, carrierData),
  deleteCarrier: (id) => api.delete(`/carriers/${id}`),
};

export default carrierService;
