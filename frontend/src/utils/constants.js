// Application Constants

export const ORDER_STATUS = {
  PENDING: 'Pending',
  IN_TRANSIT: 'In Transit',
  DELIVERED: 'Delivered',
  CANCELLED: 'Cancelled',
};

export const TRUCK_STATUS = {
  ACTIVE: 'Active',
  INACTIVE: 'Inactive',
  MAINTENANCE: 'Maintenance',
};

export const USER_ROLES = {
  ADMIN: 'Admin',
  CUSTOMER: 'Customer',
};

export const STATUS_COLORS = {
  Pending: 'info',
  'In Transit': 'warning',
  Delivered: 'success',
  Cancelled: 'error',
  Active: 'success',
  Inactive: 'default',
  Maintenance: 'warning',
};

export const ROUTES = {
  LOGIN: '/login',
  REGISTER: '/register',
  DASHBOARD: '/dashboard',
  ORDERS: '/orders',
  CARGO: '/cargo',
  TRUCKS: '/trucks',
  DRIVERS: '/drivers',
  CARRIERS: '/carriers',
  ADDRESSES: '/addresses',
  USERS: '/users',
};
