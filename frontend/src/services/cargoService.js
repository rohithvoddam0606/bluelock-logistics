import api from './api';

const cargoService = {
  getAllCargo: () => api.get('/cargo'),
  getCargoById: (id) => api.get(`/cargo/${id}`),
  createCargo: (cargoData) => api.post('/cargo', cargoData),
  updateCargo: (id, cargoData) => api.put(`/cargo/${id}`, cargoData),
  deleteCargo: (id) => api.delete(`/cargo/${id}`),
};

export default cargoService;
