# BlueLock Logistics - Frontend

A professional ReactJS frontend for the Logistics and Cargo Management System.

## Features

- Modern, responsive dashboard UI
- Complete CRUD operations for all entities
- Role-based access control (Admin/Customer)
- Real-time data visualization with charts
- Toast notifications for user feedback
- Material-UI components for professional look
- Axios integration with Spring Boot backend

## Tech Stack

- React 18
- React Router v6
- Material-UI (MUI)
- Axios
- Recharts
- React Toastify

## Project Structure

```
src/
├── components/
│   ├── Navbar.js          # Top navigation bar
│   ├── Sidebar.js         # Left sidebar menu
│   ├── DashboardCard.js   # Reusable stat cards
│   └── DataTable.js       # Reusable data table
├── pages/
│   ├── Login.js           # Login page
│   ├── Register.js        # Registration page
│   ├── Dashboard.js       # Main dashboard
│   ├── Orders.js          # Order management
│   ├── Cargo.js           # Cargo management
│   ├── Trucks.js          # Truck management
│   ├── Drivers.js         # Driver management
│   ├── Carriers.js        # Carrier management
│   ├── Addresses.js       # Address management
│   └── Users.js           # User management (Admin only)
├── services/
│   ├── api.js             # Axios configuration
│   ├── authService.js     # Authentication service
│   ├── orderService.js    # Order API calls
│   ├── cargoService.js    # Cargo API calls
│   ├── truckService.js    # Truck API calls
│   ├── driverService.js   # Driver API calls
│   ├── carrierService.js  # Carrier API calls
│   ├── addressService.js  # Address API calls
│   └── userService.js     # User API calls
├── App.js                 # Main app component
└── index.js               # Entry point
```

## Installation

1. Install dependencies:
```bash
npm install
```

2. Configure API endpoint:
   - Open `src/services/api.js`
   - Update `API_BASE_URL` if your backend runs on a different port

## Running the Application

Start the development server:
```bash
npm start
```

The app will open at [http://localhost:3000](http://localhost:3000)

## Backend Configuration

Make sure your Spring Boot backend is running on `http://localhost:8080`

The frontend expects the following API endpoints:
- `/api/auth/login` - User login
- `/api/auth/register` - User registration
- `/api/orders` - Order management
- `/api/cargo` - Cargo management
- `/api/trucks` - Truck management
- `/api/drivers` - Driver management
- `/api/carriers` - Carrier management
- `/api/addresses` - Address management
- `/api/users` - User management

## Default Credentials

After setting up the backend, you can create users through the registration page or use admin credentials if seeded in the database.

## Features by Page

### Dashboard
- Total orders, trucks, drivers, carriers
- Delivered and pending order counts
- Bar chart for order statistics
- Pie chart for order distribution

### Orders
- View all orders
- Create new orders
- Update order status
- Assign cargo and carrier
- Delete orders

### Cargo
- Add cargo items
- View cargo list
- Update cargo details
- Delete cargo

### Trucks
- Add trucks
- View truck list
- Update truck status
- Assign to carriers

### Drivers
- Add drivers
- View driver list
- Assign to trucks and carriers
- Update driver info

### Carriers
- Add carriers
- View carrier list
- Update carrier details

### Addresses
- Add addresses
- View address list
- Update addresses

### Users (Admin Only)
- View all users
- Create users
- Update user roles
- Delete users

## Build for Production

```bash
npm run build
```

This creates an optimized production build in the `build` folder.

## Troubleshooting

### CORS Issues
If you encounter CORS errors, ensure your Spring Boot backend has CORS configured:

```java
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("GET", "POST", "PUT", "DELETE");
    }
}
```

### API Connection Issues
- Verify backend is running on port 8080
- Check `src/services/api.js` for correct API_BASE_URL
- Ensure all backend endpoints are accessible

## License

MIT
