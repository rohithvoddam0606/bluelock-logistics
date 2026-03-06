# BlueLock Logistics - System Architecture

## 🏗️ Application Architecture

```
┌─────────────────────────────────────────────────────────────┐
│                        FRONTEND (React)                      │
│                     http://localhost:3000                    │
├─────────────────────────────────────────────────────────────┤
│                                                               │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐      │
│  │   Login/     │  │   Dashboard  │  │   Orders     │      │
│  │   Register   │  │   Analytics  │  │   Management │      │
│  └──────────────┘  └──────────────┘  └──────────────┘      │
│                                                               │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐      │
│  │   Cargo      │  │   Trucks     │  │   Drivers    │      │
│  │   Management │  │   Management │  │   Management │      │
│  └──────────────┘  └──────────────┘  └──────────────┘      │
│                                                               │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐      │
│  │   Carriers   │  │   Addresses  │  │   Users      │      │
│  │   Management │  │   Management │  │   (Admin)    │      │
│  └──────────────┘  └──────────────┘  └──────────────┘      │
│                                                               │
└───────────────────────────┬─────────────────────────────────┘
                            │
                            │ HTTP/REST API
                            │ (Axios)
                            │
┌───────────────────────────▼─────────────────────────────────┐
│                    BACKEND (Spring Boot)                     │
│                     http://localhost:8080                    │
├─────────────────────────────────────────────────────────────┤
│                                                               │
│  ┌────────────────────────────────────────────────────┐     │
│  │              REST API Controllers                   │     │
│  │  /api/auth  /api/orders  /api/cargo  /api/trucks  │     │
│  │  /api/drivers  /api/carriers  /api/addresses      │     │
│  └────────────────────┬───────────────────────────────┘     │
│                       │                                      │
│  ┌────────────────────▼───────────────────────────────┐     │
│  │              Service Layer                          │     │
│  │  Business Logic & Validation                       │     │
│  └────────────────────┬───────────────────────────────┘     │
│                       │                                      │
│  ┌────────────────────▼───────────────────────────────┐     │
│  │              Repository Layer                       │     │
│  │  Spring Data JPA                                   │     │
│  └────────────────────┬───────────────────────────────┘     │
│                       │                                      │
└───────────────────────┼─────────────────────────────────────┘
                        │
                        │ JDBC
                        │
┌───────────────────────▼─────────────────────────────────────┐
│                    DATABASE (MySQL)                          │
│                                                               │
│  ┌──────────┐  ┌──────────┐  ┌──────────┐  ┌──────────┐   │
│  │  users   │  │  orders  │  │  cargo   │  │  trucks  │   │
│  └──────────┘  └──────────┘  └──────────┘  └──────────┘   │
│                                                               │
│  ┌──────────┐  ┌──────────┐  ┌──────────┐  ┌──────────┐   │
│  │ drivers  │  │ carriers │  │addresses │  │ loading  │   │
│  └──────────┘  └──────────┘  └──────────┘  └──────────┘   │
│                                                               │
└─────────────────────────────────────────────────────────────┘
```

## 🔄 Data Flow

### Authentication Flow
```
User Input (Login)
    ↓
Login Component
    ↓
authService.login()
    ↓
Axios POST /api/auth/login
    ↓
Backend Authentication
    ↓
JWT Token Generated
    ↓
Token Stored in LocalStorage
    ↓
Redirect to Dashboard
    ↓
Token Auto-attached to All Requests
```

### CRUD Operation Flow
```
User Action (Create/Update/Delete)
    ↓
Page Component (e.g., Orders.js)
    ↓
Service Method (e.g., orderService.createOrder())
    ↓
Axios HTTP Request
    ↓
Backend Controller
    ↓
Service Layer (Business Logic)
    ↓
Repository Layer (Database)
    ↓
Response Back to Frontend
    ↓
Update UI State
    ↓
Show Toast Notification
```

## 📦 Component Hierarchy

```
App.js
├── Router
│   ├── Login (Public)
│   ├── Register (Public)
│   └── Protected Routes
│       ├── Layout
│       │   ├── Navbar
│       │   ├── Sidebar
│       │   └── Main Content
│       │       ├── Dashboard
│       │       │   ├── DashboardCard (x6)
│       │       │   ├── BarChart
│       │       │   └── PieChart
│       │       ├── Orders
│       │       │   ├── DataTable
│       │       │   └── Dialog Form
│       │       ├── Cargo
│       │       │   ├── DataTable
│       │       │   └── Dialog Form
│       │       ├── Trucks
│       │       │   ├── DataTable
│       │       │   └── Dialog Form
│       │       ├── Drivers
│       │       │   ├── DataTable
│       │       │   └── Dialog Form
│       │       ├── Carriers
│       │       │   ├── DataTable
│       │       │   └── Dialog Form
│       │       ├── Addresses
│       │       │   ├── DataTable
│       │       │   └── Dialog Form
│       │       └── Users (Admin Only)
│       │           ├── DataTable
│       │           └── Dialog Form
│       └── ToastContainer
```

## 🔐 Security Architecture

```
┌─────────────────────────────────────────────┐
│           Frontend Security                  │
├─────────────────────────────────────────────┤
│  • JWT Token in LocalStorage                │
│  • Protected Routes                         │
│  • Role-based Access Control                │
│  • Auto Token Expiration Handling           │
│  • Secure API Calls                         │
└──────────────────┬──────────────────────────┘
                   │
                   │ HTTPS (Production)
                   │
┌──────────────────▼──────────────────────────┐
│           Backend Security                   │
├─────────────────────────────────────────────┤
│  • JWT Token Validation                     │
│  • CORS Configuration                       │
│  • Password Encryption                      │
│  • Input Validation                         │
│  • SQL Injection Prevention (JPA)           │
│  • XSS Protection                           │
└─────────────────────────────────────────────┘
```

## 📊 Database Schema

```
┌──────────────┐         ┌──────────────┐
│    users     │         │   carriers   │
├──────────────┤         ├──────────────┤
│ id (PK)      │         │ id (PK)      │
│ name         │         │ name         │
│ email        │         │ email        │
│ password     │         │ contact      │
│ role         │         └──────┬───────┘
└──────────────┘                │
                                │
                         ┌──────▼───────┐
                         │    trucks    │
                         ├──────────────┤
                         │ id (PK)      │
                         │ name         │
                         │ number       │
                         │ capacity     │
                         │ status       │
                         │ carrier_id   │
                         └──────┬───────┘
                                │
                         ┌──────▼───────┐
                         │   drivers    │
                         ├──────────────┤
                         │ id (PK)      │
                         │ name         │
                         │ contact      │
                         │ truck_id     │
                         │ carrier_id   │
                         └──────────────┘

┌──────────────┐         ┌──────────────┐
│   orders     │         │    cargo     │
├──────────────┤         ├──────────────┤
│ id (PK)      │         │ id (PK)      │
│ orderDate    │         │ name         │
│ status       │         │ description  │
│ cost         │         │ weight       │
│ carrier_id   │◄────────┤ count        │
│ cargo_id     │         └──────────────┘
│ loading_id   │
│ unloading_id │
└──────┬───────┘
       │
       │
┌──────▼───────┐         ┌──────────────┐
│   loading    │         │  addresses   │
├──────────────┤         ├──────────────┤
│ id (PK)      │         │ id (PK)      │
│ date         │         │ street       │
│ time         │         │ city         │
│ address_id   │◄────────┤ pincode      │
└──────────────┘         │ state        │
                         └──────────────┘
```

## 🎯 API Endpoints Structure

```
/api
├── /auth
│   ├── POST /login
│   └── POST /register
├── /orders
│   ├── GET    /
│   ├── GET    /{id}
│   ├── POST   /
│   ├── PUT    /{id}
│   └── DELETE /{id}
├── /cargo
│   ├── GET    /
│   ├── GET    /{id}
│   ├── POST   /
│   ├── PUT    /{id}
│   └── DELETE /{id}
├── /trucks
│   ├── GET    /
│   ├── GET    /{id}
│   ├── POST   /
│   ├── PUT    /{id}
│   └── DELETE /{id}
├── /drivers
│   ├── GET    /
│   ├── GET    /{id}
│   ├── POST   /
│   ├── PUT    /{id}
│   └── DELETE /{id}
├── /carriers
│   ├── GET    /
│   ├── GET    /{id}
│   ├── POST   /
│   ├── PUT    /{id}
│   └── DELETE /{id}
├── /addresses
│   ├── GET    /
│   ├── GET    /{id}
│   ├── POST   /
│   ├── PUT    /{id}
│   └── DELETE /{id}
└── /users
    ├── GET    /
    ├── GET    /{id}
    ├── POST   /
    ├── PUT    /{id}
    └── DELETE /{id}
```

## 🔄 State Management

```
Component State (useState)
    ↓
Local State for Forms & UI
    ↓
API Calls on Mount (useEffect)
    ↓
Update State with Response
    ↓
Re-render Component
    ↓
User Interaction
    ↓
Update State
    ↓
API Call (Create/Update/Delete)
    ↓
Refresh Data
```

## 📱 Responsive Design Breakpoints

```
Mobile (xs)     0px - 600px     Drawer Navigation
Tablet (sm)     600px - 960px   Collapsible Sidebar
Desktop (md)    960px - 1280px  Full Sidebar
Large (lg)      1280px - 1920px Full Layout
XLarge (xl)     1920px+         Full Layout
```

## 🎨 Theme Structure

```
Theme
├── Palette
│   ├── Primary: #1976d2
│   ├── Secondary: #dc004e
│   ├── Success: #4caf50
│   ├── Warning: #ff9800
│   └── Error: #f44336
├── Typography
│   ├── Font Family: Roboto
│   └── Variants: h1-h6, body1-2
└── Spacing
    └── Base Unit: 8px
```

## 🚀 Deployment Architecture

```
┌─────────────────────────────────────────┐
│         Production Environment           │
├─────────────────────────────────────────┤
│                                          │
│  ┌────────────────┐  ┌────────────────┐│
│  │   Frontend     │  │   Backend      ││
│  │   (Nginx)      │  │   (Tomcat)     ││
│  │   Port 80/443  │  │   Port 8080    ││
│  └────────┬───────┘  └────────┬───────┘│
│           │                   │         │
│           └───────┬───────────┘         │
│                   │                     │
│           ┌───────▼───────┐            │
│           │   MySQL DB    │            │
│           │   Port 3306   │            │
│           └───────────────┘            │
│                                          │
└─────────────────────────────────────────┘
```

---

**This architecture provides a scalable, maintainable, and professional logistics management system.**
