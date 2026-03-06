# 🚚 BlueLock Logistics - Complete Management System

A professional full-stack Logistics and Cargo Management System built with React and Spring Boot.

![Version](https://img.shields.io/badge/version-1.0.0-blue)
![React](https://img.shields.io/badge/React-18.2.0-61dafb)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-green)
![License](https://img.shields.io/badge/license-MIT-green)

## 📋 Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Quick Start](#quick-start)
- [Documentation](#documentation)
- [Project Structure](#project-structure)
- [Screenshots](#screenshots)
- [API Documentation](#api-documentation)
- [Contributing](#contributing)
- [License](#license)

## 🎯 Overview

BlueLock Logistics is an enterprise-grade logistics and cargo management system designed to streamline operations for logistics companies. It provides comprehensive tools for managing orders, cargo, trucks, drivers, carriers, and addresses with real-time analytics and reporting.

### Key Highlights

- ✅ Complete CRUD operations for all entities
- ✅ Real-time dashboard with analytics
- ✅ Role-based access control (Admin/Customer)
- ✅ JWT authentication
- ✅ Responsive Material-UI design
- ✅ RESTful API architecture
- ✅ Production-ready deployment

## ✨ Features

### Authentication & Authorization
- Secure JWT-based authentication
- User registration and login
- Role-based access control
- Protected routes
- Session management

### Dashboard
- Real-time statistics cards
- Interactive charts (Bar & Pie)
- Order analytics
- Fleet status overview
- Delivery tracking

### Order Management
- Create and track orders
- Update order status (Pending, In Transit, Delivered, Cancelled)
- Assign cargo and carriers
- Cost calculation
- Order history

### Cargo Management
- Add cargo items
- Track weight and count
- Manage descriptions
- Cargo inventory

### Fleet Management
- Truck registration
- Status tracking (Active, Inactive, Maintenance)
- Capacity management
- Carrier assignment

### Driver Management
- Driver registration
- Truck assignment
- Contact management
- Carrier association

### Carrier Management
- Carrier registration
- Contact information
- Email management

### Address Management
- Location database
- Street, city, state, pincode
- Address book

### User Management (Admin)
- User CRUD operations
- Role assignment
- Password management

## 🛠️ Tech Stack

### Frontend
- **Framework**: React 18.2.0
- **UI Library**: Material-UI 7.3.9
- **Routing**: React Router 7.13.1
- **HTTP Client**: Axios 1.13.6
- **Charts**: Recharts 3.7.0
- **Notifications**: React Toastify 11.0.5
- **State Management**: React Hooks

### Backend
- **Framework**: Spring Boot 3.x
- **Database**: MySQL 8.0
- **ORM**: Spring Data JPA
- **Security**: JWT Authentication
- **Build Tool**: Maven

## 🚀 Quick Start

### Prerequisites
- Node.js 16+ and npm
- Java 17+
- MySQL 8+
- Maven 3.6+

### Installation

1. **Clone the repository**
```bash
git clone https://github.com/rohithvoddam0606/bluelock-logistics.git
cd bluelock-logistics
```

2. **Setup Database**
```sql
CREATE DATABASE logistics_db;
```

3. **Configure Backend**
Edit `backend/src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/logistics_db
spring.datasource.username=your_username
spring.datasource.password=your_password
```

4. **Start Backend**
```bash
cd backend
./mvnw spring-boot:run
```
Backend runs on: `http://localhost:8080`

5. **Install Frontend Dependencies**
```bash
cd frontend
npm install
```

6. **Start Frontend**
```bash
npm start
```
Frontend runs on: `http://localhost:3000`

### Quick Start Scripts (Windows)
- Double-click `start-backend.bat` to start backend
- Double-click `start-frontend.bat` to start frontend

## 📚 Documentation

Comprehensive documentation is available in the following files:

| Document | Description |
|----------|-------------|
| [QUICK_START.md](QUICK_START.md) | 5-minute setup guide |
| [COMPLETE_GUIDE.md](COMPLETE_GUIDE.md) | Full implementation guide |
| [ARCHITECTURE.md](ARCHITECTURE.md) | System architecture & diagrams |
| [PROJECT_SUMMARY.md](PROJECT_SUMMARY.md) | Complete feature list |
| [FRONTEND_SETUP.md](FRONTEND_SETUP.md) | Detailed frontend setup |
| [TESTING_GUIDE.md](TESTING_GUIDE.md) | Comprehensive testing guide |
| [DEPLOYMENT_GUIDE.md](DEPLOYMENT_GUIDE.md) | Production deployment guide |
| [frontend/README.md](frontend/README.md) | Frontend documentation |

## 📁 Project Structure

```
bluelock-logistics/
├── backend/                    # Spring Boot Backend
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/alpha/bluelock_logistics/
│   │   │   │   ├── controller/      # REST Controllers
│   │   │   │   ├── entity/          # JPA Entities
│   │   │   │   ├── repository/      # Data Repositories
│   │   │   │   ├── service/         # Business Logic
│   │   │   │   ├── dto/             # Data Transfer Objects
│   │   │   │   └── exceptions/      # Custom Exceptions
│   │   │   └── resources/
│   │   │       └── application.properties
│   │   └── test/
│   └── pom.xml
│
└── frontend/                   # React Frontend
    ├── public/
    ├── src/
    │   ├── components/         # Reusable Components
    │   │   ├── Navbar.js
    │   │   ├── Sidebar.js
    │   │   ├── DashboardCard.js
    │   │   ├── DataTable.js
    │   │   ├── Loading.js
    │   │   ├── EmptyState.js
    │   │   └── ProtectedRoute.js
    │   ├── pages/              # Page Components
    │   │   ├── Login.js
    │   │   ├── Register.js
    │   │   ├── Dashboard.js
    │   │   ├── Orders.js
    │   │   ├── Cargo.js
    │   │   ├── Trucks.js
    │   │   ├── Drivers.js
    │   │   ├── Carriers.js
    │   │   ├── Addresses.js
    │   │   └── Users.js
    │   ├── services/           # API Services
    │   │   ├── api.js
    │   │   ├── authService.js
    │   │   └── [entity]Service.js
    │   ├── utils/              # Utilities
    │   │   ├── constants.js
    │   │   └── validators.js
    │   ├── App.js
    │   └── index.js
    └── package.json
```

## 📸 Screenshots

### Dashboard
![Dashboard](docs/screenshots/dashboard.png)
*Real-time analytics and statistics*

### Order Management
![Orders](docs/screenshots/orders.png)
*Complete order lifecycle management*

### Fleet Management
![Trucks](docs/screenshots/trucks.png)
*Truck and driver management*

## 🔌 API Documentation

### Base URL
```
http://localhost:8080/api
```

### Authentication Endpoints
```
POST   /auth/login       - User login
POST   /auth/register    - User registration
```

### Entity Endpoints
All entities follow RESTful conventions:
```
GET    /[entity]         - Get all
GET    /[entity]/{id}    - Get by ID
POST   /[entity]         - Create new
PUT    /[entity]/{id}    - Update
DELETE /[entity]/{id}    - Delete
```

Entities: `orders`, `cargo`, `trucks`, `drivers`, `carriers`, `addresses`, `users`

### Example Request
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email":"user@example.com","password":"password123"}'
```

### Example Response
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "user": {
    "id": 1,
    "name": "John Doe",
    "email": "user@example.com",
    "role": "Admin"
  }
}
```

## 🧪 Testing

Run the comprehensive test suite:

```bash
# Backend tests
cd backend
./mvnw test

# Frontend tests
cd frontend
npm test
```

See [TESTING_GUIDE.md](TESTING_GUIDE.md) for detailed testing procedures.

## 🚀 Deployment

### Production Build

**Backend:**
```bash
cd backend
./mvnw clean package
java -jar target/bluelock-logistics-0.0.1-SNAPSHOT.jar
```

**Frontend:**
```bash
cd frontend
npm run build
# Deploy build folder to hosting service
```

See [DEPLOYMENT_GUIDE.md](DEPLOYMENT_GUIDE.md) for detailed deployment instructions.

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👥 Authors

- **Rohith Voddam** - [rohithvoddam0606](https://github.com/rohithvoddam0606)

## 🙏 Acknowledgments

- Material-UI for the excellent component library
- Spring Boot team for the robust framework
- React team for the amazing frontend library
- All contributors and users of this project

## 📞 Support

For support, email support@bluelocklogistics.com or open an issue in the repository.

## 🔗 Links

- [Live Demo](#) (Coming soon)
- [Documentation](COMPLETE_GUIDE.md)
- [API Docs](#)
- [Report Bug](https://github.com/rohithvoddam0606/bluelock-logistics/issues)
- [Request Feature](https://github.com/rohithvoddam0606/bluelock-logistics/issues)

## 📊 Project Status

- ✅ Core features complete
- ✅ Frontend implemented
- ✅ Backend integrated
- ✅ Documentation complete
- 🚧 Testing in progress
- 🚧 Deployment pending

## 🎯 Roadmap

- [ ] Real-time tracking with WebSocket
- [ ] Email notifications
- [ ] PDF report generation
- [ ] Advanced search and filters
- [ ] Export to Excel
- [ ] Dark mode theme
- [ ] Multi-language support
- [ ] Mobile app version
- [ ] Advanced analytics
- [ ] Route optimization

---

**Built with ❤️ for BlueLock Logistics**

*Making logistics management simple and efficient*
