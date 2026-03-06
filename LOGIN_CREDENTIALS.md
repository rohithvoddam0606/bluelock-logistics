# 🔐 BlueLock Logistics - Login Credentials

## ⚠️ IMPORTANT: Backend Authentication Not Implemented

The backend from GitHub **does not have authentication endpoints** implemented yet. 

## 🔧 Current Situation

The backend has:
- ✅ User entity and database table
- ✅ UserController for CRUD operations
- ❌ No `/api/auth/login` endpoint
- ❌ No `/api/auth/register` endpoint
- ❌ No authentication logic

## 🎯 Two Solutions

### Solution 1: Use Frontend Only (UI Testing)
You can explore the frontend UI without backend:
1. Open `http://localhost:3000`
2. View the login/register pages
3. See the dashboard design
4. Explore all pages (but no data operations will work)

### Solution 2: Implement Authentication (Recommended)

I've created the authentication files for you:
- `AuthController.java` - Login and register endpoints
- `DataInitializer.java` - Creates default users on startup
- `WebConfig.java` - CORS configuration

**But the backend needs to be recompiled with these new files.**

## 📝 Default Credentials (Once Backend is Fixed)

After implementing authentication, these users will be created automatically:

### Admin User
```
Email: admin@bluelock.com
Password: admin123
Role: Admin
```

### Customer User
```
Email: customer@bluelock.com
Password: customer123
Role: Customer
```

## 🚀 Quick Fix Steps

### Step 1: Navigate to Backend Directory
```bash
cd "bluelock-logistics/backend"
```

### Step 2: Clean and Rebuild
```bash
./mvnw.cmd clean install -DskipTests
```

### Step 3: Start Backend
```bash
java -jar target/bluelock_logistics-0.0.1-SNAPSHOT.jar
```

### Step 4: Start Frontend (New Terminal)
```bash
cd "bluelock-logistics/frontend"
npm start
```

### Step 5: Access Application
Open: `http://localhost:3000`

## 🐛 Current Issues

1. **Backend won't compile** - The new authentication files need proper compilation
2. **JAR file missing** - Because compilation failed
3. **No authentication** - Original backend doesn't have auth endpoints

## ✅ What's Working

- ✅ Frontend is running on port 3000
- ✅ Frontend UI is complete and beautiful
- ✅ All pages are accessible
- ✅ Java 17 is installed
- ✅ H2 Database is configured

## 🔄 Alternative: Use Existing Backend As-Is

If you want to use the application without authentication:

1. **Remove authentication** from frontend
2. **Access APIs directly** without login
3. **Use Postman** to test backend APIs

### Direct API Testing (No Auth)
```bash
# Create a user directly
POST http://localhost:8080/api/users
Body: {
  "name": "Test User",
  "email": "test@example.com",
  "password": "test123",
  "role": "Admin"
}
```

## 📞 Need Help?

The main issue is that the backend needs to be properly compiled with the new authentication code. The files are created but not compiled into the JAR.

**Recommended Action:**
1. Manually compile the backend
2. Or use the backend without authentication
3. Or implement authentication in a separate branch

---

**Status**: Backend authentication is partially implemented but not compiled yet.
