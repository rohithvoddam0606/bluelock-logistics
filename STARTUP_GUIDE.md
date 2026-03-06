# 🚀 BlueLock Logistics - Startup Guide

## Current Status

✅ **Frontend**: Running successfully on `http://localhost:3000`
✅ **Java 17**: Installed and configured
✅ **H2 Database**: Configured
❌ **Backend**: Needs to be started

## Quick Start (3 Steps)

### Step 1: Open PowerShell in Backend Directory
```powershell
cd "C:\Users\sahit\OneDrive\ドキュメント\Desktop\Logistics and cargomanagement system\bluelock-logistics\backend"
```

### Step 2: Build the Backend
```powershell
./mvnw.cmd clean package -DskipTests
```

### Step 3: Start the Backend
```powershell
java -jar target/bluelock_logistics-0.0.1-SNAPSHOT.jar
```

## Access the Application

Once both servers are running:
- **Frontend**: `http://localhost:3000`
- **Backend**: `http://localhost:8080`

## Login Credentials

Since authentication is not fully implemented, you have two options:

### Option A: Register a New User
1. Go to `http://localhost:3000/register`
2. Fill in the form
3. Click "Sign Up"
4. Login with your credentials

### Option B: Use API Directly
The backend doesn't have `/api/auth/login` endpoint yet, so the frontend login won't work until we implement it.

## Troubleshooting

### Problem: "Could not find or load main class"
**Solution**: The backend needs to be compiled first
```powershell
cd bluelock-logistics/backend
./mvnw.cmd clean install -DskipTests
```

### Problem: "Port 8080 already in use"
**Solution**: Kill the process using port 8080
```powershell
netstat -ano | findstr :8080
taskkill /PID <PID_NUMBER> /F
```

### Problem: "npm start" error
**Solution**: Make sure you're in the frontend directory
```powershell
cd bluelock-logistics/frontend
npm start
```

### Problem: Frontend shows "Connection Refused"
**Solution**: Backend is not running. Start it first.

## What's Working vs What's Not

### ✅ Working
- Frontend UI (all pages)
- Frontend routing
- Material-UI design
- Charts and graphs
- Form validation
- Responsive design

### ❌ Not Working Yet
- User login (no auth endpoint)
- User registration (no auth endpoint)
- Data operations (backend not running)
- API calls (backend not running)

## Next Steps

1. **Start the backend** using the commands above
2. **Implement authentication** endpoints in backend
3. **Test the application** end-to-end

## Quick Commands Reference

### Start Frontend
```powershell
cd bluelock-logistics/frontend
npm start
```

### Start Backend
```powershell
cd bluelock-logistics/backend
java -jar target/bluelock_logistics-0.0.1-SNAPSHOT.jar
```

### Build Backend
```powershell
cd bluelock-logistics/backend
./mvnw.cmd clean package -DskipTests
```

### Check Running Processes
```powershell
# Check port 3000 (Frontend)
netstat -ano | findstr :3000

# Check port 8080 (Backend)
netstat -ano | findstr :8080
```

## Important Notes

1. **Always start backend before frontend** for best results
2. **Frontend is already running** on port 3000
3. **Backend needs to be built** before it can run
4. **Authentication is not implemented** in the original backend

## Support

If you encounter issues:
1. Check if Java is in PATH: `java -version`
2. Check if Node is in PATH: `node --version`
3. Verify you're in the correct directory
4. Check console logs for errors

---

**Current Status**: Frontend running, Backend needs to be started
