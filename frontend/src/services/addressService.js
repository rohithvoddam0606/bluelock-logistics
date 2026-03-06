import api from './api';

const addressService = {
  getAllAddresses: () => api.get('/addresses'),
  getAddressById: (id) => api.get(`/addresses/${id}`),
  createAddress: (addressData) => api.post('/addresses', addressData),
  updateAddress: (id, addressData) => api.put(`/addresses/${id}`, addressData),
  deleteAddress: (id) => api.delete(`/addresses/${id}`),
};

export default addressService;
