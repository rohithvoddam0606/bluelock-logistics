# BlueLock Logistics - Complete Setup Guide

## Overview

This is a full-stack Logistics and Cargo Management System with:
- **Backend**: Spring Boot REST API (Java)
- **Frontend**: React with Material-UI

## Quick Start

### 1. Start the Backend

```bash
cd bluelock-logistics/backend
./mvnw spring-boot:run
```

Backend will run on: `http://localhost:8080`

### 2. Start the Frontend

```bash
cd bluelock-logistics/frontend
npm start
```

Frontend will run on: `http://localhost:3000`

## System Architecture

### Backend Entities
- **User**: Authentication and authorization
- **Order**: Order management with status tracking
- **Cargo**: Cargo items with weight and count
- **Truck**: Truck fleet management
- **Driver**: Driver assignment to trucks
- **Carrier**: Carrier/logistics companies
- **Address**: Location management
- **Loading/Unloading**: Schedule management

### Frontend Pages
1. **Login/Register**: User authentication
2. **Dashboard**: Analytics and statistics
3. **Orders**: Complete order lifecycle management
4. **Cargo**: Cargo inventory management
5. **Trucks**: Fleet management
6. **Drivers**: Driver assignment and tracking
7. **Carriers**: Carrier management
8. **Addresses**: Location database
9. **Users**: User management (Admin only)

## API Endpoints

### Authentication
- `POST /api/auth/login` - User login
- `POST /api/auth/register` - User registration

### Orders
- `GET /api/orders` - Get all orders
- `GET /api/orders/{id}` - Get order by ID
- `POST /api/orders` - Create order
- `PUT /api/orders/{id}` - Update order
- `DELETE /api/orders/{id}` - Delete order

### Cargo
- `GET /api/cargo` - Get all cargo
- `POST /api/cargo` - Create cargo
- `PUT /api/cargo/{id}` - Update cargo
- `DELETE /api/cargo/{id}` - Delete cargo

### Trucks
- `GET /api/trucks` - Get all trucks
- `POST /api/trucks` - Create truck
- `PUT /api/trucks/{id}` - Update truck
- `DELETE /api/trucks/{id}` - Delete truck

### Drivers
- `GET /api/drivers` - Get all drivers
- `POST /api/drivers` - Create driver
- `PUT /api/drivers/{id}` - Update driver
- `DELETE /api/drivers/{id}` - Delete driver

### Carriers
- `GET /api/carriers` - Get all carriers
- `POST /api/carriers` - Create carrier
- `PUT /api/carriers/{id}` - Update carrier
- `DELETE /api/carriers/{id}` - Delete carrier

### Addresses
- `GET /api/addresses` - Get all addresses
- `POST /api/addresses` - Create address
- `PUT /api/addresses/{id}` - Update address
- `DELETE /api/addresses/{id}` - Delete address

### Users (Admin only)
- `GET /api/users` - Get all users
- `POST /api/users` - Create user
- `PUT /api/users/{id}` - Update user
- `DELETE /api/users/{id}` - Delete user

## Frontend Features

### Authentication
- JWT token-based authentication
- Automatic token refresh
- Protected routes
- Role-based access control

### Dashboard
- Real-time statistics
- Interactive charts (Bar & Pie)
- Key metrics display
- Responsive cards

### Data Management
- CRUD operations for all entities
- Form validation
- Confirmation dialogs
- Toast notifications
- Status badges

### UI/UX
- Material-UI components
- Responsive design
- Mobile-friendly sidebar
- Professional color scheme
- Loading states
- Error handling

## Configuration

### Backend Configuration (application.properties)

```properties
# Server Port
server.port=8080

# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/logistics_db
spring.datasource.username=root
spring.datasource.password=yourpassword

# JPA Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# JWT Configuration
jwt.secret=yourSecretKey
jwt.expiration=86400000
```

### Frontend Configuration (src/services/api.js)

```javascript
const API_BASE_URL = 'http://localhost:8080/api';
```

## CORS Configuration

Add this to your Spring Boot backend:

```java
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
```

## User Roles

### Admin
- Full access to all features
- User management
- System configuration

### Customer
- View and manage own orders
- Create cargo requests
- Track shipments

## Development Workflow

1. **Start Backend**: Ensure database is running and backend starts successfully
2. **Start Frontend**: Run `npm start` in frontend directory
3. **Create Admin User**: Register first user or seed database
4. **Test Features**: Navigate through all pages and test CRUD operations

## Production Deployment

### Backend
```bash
cd backend
./mvnw clean package
java -jar target/bluelock-logistics-0.0.1-SNAPSHOT.jar
```

### Frontend
```bash
cd frontend
npm run build
# Serve the build folder with nginx or any static server
```

## Troubleshooting

### Backend Issues
- Check if MySQL is running
- Verify database credentials
- Check port 8080 is available
- Review application logs

### Frontend Issues
- Clear browser cache
- Check console for errors
- Verify API_BASE_URL in api.js
- Ensure backend is running

### CORS Errors
- Add CORS configuration to backend
- Check allowed origins
- Verify request headers

## Technology Stack

### Backend
- Java 17+
- Spring Boot 3.x
- Spring Data JPA
- MySQL
- Maven

### Frontend
- React 18
- Material-UI 7
- Axios
- React Router 7
- Recharts
- React Toastify

## Future Enhancements

- Real-time tracking with WebSocket
- Email notifications
- PDF report generation
- Advanced analytics
- Mobile app
- Multi-language support
- Dark mode

## Support

For issues or questions, please check:
- Backend logs in console
- Frontend console errors
- Network tab in browser DevTools
- Database connection status

## License

MIT License
