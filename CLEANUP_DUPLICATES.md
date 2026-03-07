# 🧹 Database Cleanup Guide

## Issue: Duplicate Email Addresses

Your database has duplicate users with the same email `customer@bluelock.com`:
- User ID 2: John Doe (original)
- User ID 3: Sahithya Reddy (duplicate)
- User ID 4: rohith (duplicate)

## ✅ Fixes Applied

1. **Added unique constraint** on email field in User entity
2. **Added email validation** in UserService to prevent duplicates
3. **Added DuplicateEmailException** for better error handling
4. **Updated GlobalExceptionHandler** to handle duplicate email errors

## 🔧 How to Clean Up

### Option 1: Restart Backend (Recommended)

The easiest way is to delete the H2 database file and restart:

```bash
# Stop the backend (Ctrl+C in the terminal)
# Delete the database file
Remove-Item "bluelock-logistics/backend/bluelockdb.mv.db" -Force
Remove-Item "bluelock-logistics/backend/bluelockdb.trace.db" -Force -ErrorAction SilentlyContinue

# Restart the backend
cd bluelock-logistics/backend
java -jar target/bluelock_logistics-0.0.1-SNAPSHOT.jar
```

This will recreate the database with only the default users:
- admin@bluelock.com / admin123
- customer@bluelock.com / customer123

### Option 2: Delete Manually via API

Delete the duplicate users using these commands:

```powershell
# Delete user ID 3 (Sahithya Reddy)
Invoke-WebRequest -Uri "http://localhost:8080/api/users/3" -Method DELETE

# Delete user ID 4 (rohith)
Invoke-WebRequest -Uri "http://localhost:8080/api/users/4" -Method DELETE
```

### Option 3: Use H2 Console

1. Access H2 Console: http://localhost:8080/h2-console
2. JDBC URL: `jdbc:h2:file:./bluelockdb`
3. Username: `sa`
4. Password: (leave empty)
5. Run this SQL:
```sql
DELETE FROM users WHERE id IN (3, 4);
```

## 🎯 After Cleanup

You'll have only these users:
- **Admin**: admin@bluelock.com / admin123
- **Customer**: customer@bluelock.com / customer123

## 🛡️ Prevention

The fixes I applied will now:
- Prevent duplicate emails during registration
- Show error message: "Email already exists"
- Return HTTP 400 status code for duplicates

## 🔄 Rebuild Backend (If Needed)

If you want to compile the changes:

```bash
cd bluelock-logistics/backend
./mvnw.cmd clean package -DskipTests
java -jar target/bluelock_logistics-0.0.1-SNAPSHOT.jar
```

## ✨ What's Fixed

1. ✅ Email uniqueness constraint in database
2. ✅ Duplicate email validation in registration
3. ✅ Proper error messages for users
4. ✅ Better error handling in frontend
5. ✅ Login error messages now visible

## 📝 Test Registration

After cleanup, try registering with an existing email:
- Email: admin@bluelock.com
- You should see: "Email already exists"
