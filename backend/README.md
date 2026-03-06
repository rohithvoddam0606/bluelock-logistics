# Bluelock Logistics - Backend API

A comprehensive logistics management system built with Spring Boot, providing RESTful APIs for managing carriers, trucks, drivers, orders, and shipments.

## 🚀 Features

- **User Management**: Customer and Admin role-based access
- **Carrier Management**: Manage logistics companies
- **Fleet Management**: Track trucks and drivers
- **Order Management**: Complete order lifecycle from creation to delivery
- **Address Management**: Handle loading and unloading locations
- **Cargo Tracking**: Monitor goods being transported

## 🏗️ Architecture

### Technology Stack
- **Framework**: Spring Boot 3.5.5
- **Language**: Java 17
- **Database**: PostgreSQL
- **ORM**: Hibernate/JPA
- **Build Tool**: Maven
- **API Style**: RESTful

### Project Structure
```
bluelock_logistics/
├── src/main/java/com/alpha/bluelock_logistics/
│   ├── controller/      # REST API Controllers
│   ├── service/         # Business Logic Layer
│   ├── repository/      # Data Access Layer
│   ├── entity/          # JPA Entities
│   ├── dto/             # Data Transfer Objects
│   └── exceptions/      # Custom Exception Handlers
├── src/main/resources/
│   └── application.properties
└── pom.xml
```

## 📊 Database Schema

### Entities
- **User**: Customer and Admin users
- **Carrier**: Logistics companies
- **Truck**: Transport vehicles
- **Driver**: Truck operators
- **Address**: Loading/Unloading locations
- **Cargo**: Goods being transported
- **Loading**: Loading point information
- **Unloading**: Unloading point information
- **Orders**: Shipment orders

### Entity Relationships
- Truck → Carrier (ManyToOne)
- Driver → Carrier (ManyToOne)
- Driver → Truck (ManyToOne)
- Orders → Carrier (ManyToOne)
- Orders → Cargo (ManyToOne)
- Orders → Loading (ManyToOne)
- Orders → Unloading (ManyToOne)
- Loading → Address (ManyToOne)
- Unloading → Address (ManyToOne)

## 🛠️ Setup Instructions

### Prerequisites
- Java 17 or higher
- PostgreSQL 12 or higher
- Maven 3.6 or higher

### Database Setup
1. Install PostgreSQL
2. Create a database:
```sql
CREATE DATABASE bluelock_logistics;
```

3. Update `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/bluelock_logistics
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### Running the Application

#### Using Maven Wrapper (Recommended)
```bash
./mvnw spring-boot:run
```

#### Using Maven
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## 📡 API Endpoints

### User Management
- `POST /users` - Create user
- `GET /users` - Get all users
- `GET /users/{id}` - Get user by ID
- `PUT /users/{id}` - Update user
- `DELETE /users/{id}` - Delete user

### Carrier Management
- `POST /addcarrier` - Create carrier
- `GET /findcarrier/{id}` - Get carrier by ID
- `DELETE /deletecarrier/{id}` - Delete carrier

### Truck Management
- `POST /addtruck` - Create truck
- `GET /findtruck/{id}` - Get truck by ID
- `PUT /updatetruck/{truckid}/assigncarrier/{carrierid}` - Assign carrier
- `DELETE /deletetruck/{id}` - Delete truck

### Driver Management
- `POST /adddriver` - Create driver
- `GET /finddriver/{id}` - Get driver by ID
- `PUT /updatedriver/{driverid}/assigntruck/{truckid}/assigncarrier/{carrierid}` - Assign truck & carrier
- `DELETE /deletedriver/{id}` - Delete driver

### Address Management
- `POST /addaddress` - Create address
- `GET /findaddress/{id}` - Get address by ID
- `DELETE /deleteaddress/{id}` - Delete address

### Cargo Management
- `POST /addcargo` - Create cargo

### Order Management
- `POST /addorder` - Create order
- `GET /fetchorder/{orderid}` - Get order by ID
- `PUT /updateorder/{orderid}/assigncarrier/{truckid}` - Assign truck
- `PUT /updateorder/{orderid}/updateloading` - Mark loading complete
- `PUT /updateorder/{orderid}/updateunloading` - Mark unloading complete
- `GET /getallcancelledorders` - Get cancelled orders

## 📝 API Usage Examples

### Create Carrier
```bash
POST http://localhost:8080/addcarrier
Content-Type: application/json

{
    "id": 1,
    "name": "Express Logistics",
    "email": "info@expresslogistics.com",
    "contact": 5551234567
}
```

### Create Order
```bash
POST http://localhost:8080/addorder
Content-Type: application/json

{
    "orderdate": "2026-03-06",
    "status": "PENDING",
    "cost": 5000.00,
    "carrierid": 1,
    "loadingaddressid": 1,
    "unloadingaddressid": 2,
    "cargoname": "Electronics",
    "cargodescription": "Laptops and computers",
    "cargoweight": 500,
    "cargocount": 10
}
```

## 🧪 Testing

### Using Postman
1. Import the provided Postman collection: `Bluelock_Logistics_Postman_Collection.json`
2. Follow the testing guide in `POSTMAN_TESTING_GUIDE.md`

### Testing Workflow
1. Create Carrier
2. Create Addresses (loading and unloading)
3. Create Truck
4. Create Driver
5. Create Order
6. Update order status (assign truck, loading, unloading)

## 📦 Response Format

All API responses follow this structure:
```json
{
    "statuscode": 200,
    "message": "Success message",
    "data": { ... }
}
```

## 🔒 Security Notes

⚠️ **Important**: This is a development version. Before production deployment:
- Implement Spring Security
- Add JWT authentication
- Encrypt passwords (BCrypt)
- Add role-based access control
- Validate all inputs
- Add rate limiting

## 🐛 Error Handling

The application includes custom exception handlers for:
- `AddressWithIdNotFoundException`
- `CarrierWithIdNotFoundException`
- `DriverWithIdNotFoundException`
- `OrderWithIdNotFoundException`
- `TruckWithIdNotFoundException`
- `TruckCapacityFullException`

## 📄 License

This project is for educational/development purposes.

## 👥 Contributors

- Your Name

## 📞 Contact

For questions or support, please contact: [your-email@example.com]

---

## 🚀 Quick Start

```bash
# Clone the repository
git clone https://github.com/yourusername/bluelock-logistics.git

# Navigate to project directory
cd bluelock-logistics/bluelock_logistics

# Run the application
./mvnw spring-boot:run
```

The API will be available at `http://localhost:8080`

---

**Built with ❤️ using Spring Boot**
