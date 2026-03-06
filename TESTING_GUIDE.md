# 🧪 Testing Guide - BlueLock Logistics

## Pre-Testing Checklist

- [ ] MySQL database is running
- [ ] Backend is running on port 8080
- [ ] Frontend is running on port 3000
- [ ] Browser DevTools is open (F12)

## 1. Authentication Testing

### Test Login
1. Navigate to `http://localhost:3000/login`
2. Try invalid credentials
   - Expected: Error toast message
3. Try valid credentials
   - Expected: Redirect to dashboard
4. Check localStorage for token
   - Open DevTools → Application → Local Storage
   - Verify `token` and `user` are stored

### Test Registration
1. Navigate to `http://localhost:3000/register`
2. Fill in all fields
3. Select role (Admin/Customer)
4. Submit form
   - Expected: Success message
   - Expected: Redirect to login
5. Login with new credentials
   - Expected: Successful login

### Test Logout
1. Click user icon in navbar
2. Click Logout
   - Expected: Redirect to login
   - Expected: Token removed from localStorage

### Test Protected Routes
1. Logout
2. Try to access `/dashboard` directly
   - Expected: Redirect to login
3. Login and access dashboard
   - Expected: Dashboard loads

## 2. Dashboard Testing

### Test Statistics Cards
1. Navigate to Dashboard
2. Verify all 6 cards display:
   - Total Orders
   - Active Trucks
   - Total Drivers
   - Total Carriers
   - Delivered Orders
   - Pending Orders
3. Check numbers are accurate
4. Verify icons display correctly

### Test Charts
1. Check Bar Chart displays
   - X-axis: Order status
   - Y-axis: Count
   - Bars render correctly
2. Check Pie Chart displays
   - Segments for each status
   - Labels visible
   - Colors distinct

### Test Responsiveness
1. Resize browser window
2. Check mobile view (< 600px)
   - Sidebar becomes drawer
   - Cards stack vertically
   - Charts remain readable

## 3. Order Management Testing

### Test View Orders
1. Navigate to Orders page
2. Verify table displays with columns:
   - ID, Order Date, Status, Cost, Carrier, Cargo
3. Check action buttons (Edit, Delete)
4. Verify status badges have colors

### Test Create Order
1. Click "Add Order" button
2. Fill in form:
   - Order Date: Select date
   - Status: Select from dropdown
   - Cost: Enter number
   - Carrier: Select from dropdown
   - Cargo: Select from dropdown
3. Click "Create"
   - Expected: Success toast
   - Expected: Order appears in table
4. Verify in database

### Test Update Order
1. Click Edit icon on an order
2. Modify fields
3. Click "Update"
   - Expected: Success toast
   - Expected: Changes reflected in table

### Test Delete Order
1. Click Delete icon
2. Confirm deletion
   - Expected: Confirmation dialog
   - Expected: Success toast
   - Expected: Order removed from table

### Test Form Validation
1. Try to create order with empty fields
   - Expected: Validation errors
2. Try invalid cost (negative number)
   - Expected: Validation error

## 4. Cargo Management Testing

### Test CRUD Operations
1. **Create Cargo**
   - Name: "Electronics"
   - Description: "Fragile items"
   - Weight: 500
   - Count: 10
   - Expected: Success

2. **View Cargo**
   - Verify all fields display
   - Check table formatting

3. **Update Cargo**
   - Change weight to 600
   - Expected: Update successful

4. **Delete Cargo**
   - Confirm deletion
   - Expected: Removed from list

## 5. Truck Management Testing

### Test Create Truck
1. Add new truck:
   - Name: "Truck-001"
   - Number: "MH12AB1234"
   - Capacity: 5000
   - Status: Active
   - Carrier: Select from dropdown
2. Verify creation

### Test Status Update
1. Edit truck
2. Change status to "Maintenance"
3. Verify status badge color changes

### Test Carrier Assignment
1. Edit truck
2. Change carrier
3. Verify carrier updated

## 6. Driver Management Testing

### Test Create Driver
1. Add driver:
   - Name: "John Doe"
   - Contact: "9876543210"
   - Truck: Select from dropdown
   - Carrier: Select from dropdown
2. Verify creation

### Test Truck Assignment
1. Edit driver
2. Assign to different truck
3. Verify assignment

### Test Unassigned Driver
1. Create driver without truck
2. Verify shows "Unassigned"

## 7. Carrier Management Testing

### Test CRUD Operations
1. Create carrier with email validation
2. Update carrier details
3. Delete carrier
4. Verify email format validation

## 8. Address Management Testing

### Test Create Address
1. Add address:
   - Street: "123 Main St"
   - City: "Mumbai"
   - Pincode: "400001"
   - State: "Maharashtra"
2. Verify creation

### Test Pincode Validation
1. Try invalid pincode
2. Verify validation error

## 9. User Management Testing (Admin Only)

### Test Admin Access
1. Login as Admin
2. Verify "Users" menu item visible
3. Access Users page

### Test Customer Access
1. Login as Customer
2. Verify "Users" menu NOT visible
3. Try to access `/users` directly
   - Expected: Redirect to dashboard

### Test User CRUD
1. Create new user
2. Update user role
3. Delete user
4. Verify operations

## 10. API Integration Testing

### Test API Calls
1. Open DevTools → Network tab
2. Perform any CRUD operation
3. Check:
   - Request URL correct
   - Request method (GET/POST/PUT/DELETE)
   - Request headers include Authorization
   - Response status (200, 201, etc.)
   - Response data format

### Test Error Handling
1. Stop backend server
2. Try any operation
   - Expected: Error toast
   - Expected: Graceful error message

### Test Token Expiration
1. Manually expire token in localStorage
2. Try any operation
   - Expected: Redirect to login

## 11. UI/UX Testing

### Test Toast Notifications
1. Perform successful operation
   - Expected: Green success toast
2. Perform failed operation
   - Expected: Red error toast
3. Verify toast auto-dismisses after 3 seconds

### Test Modal Dialogs
1. Open any form dialog
2. Click outside dialog
   - Expected: Dialog closes
3. Click Cancel button
   - Expected: Dialog closes
4. Press Escape key
   - Expected: Dialog closes

### Test Loading States
1. Slow down network (DevTools → Network → Throttling)
2. Perform operation
   - Expected: Loading indicator

### Test Confirmation Dialogs
1. Try to delete any item
   - Expected: Confirmation dialog
2. Click Cancel
   - Expected: No deletion
3. Click OK
   - Expected: Item deleted

## 12. Responsive Design Testing

### Desktop (1920x1080)
- [ ] Sidebar always visible
- [ ] Content area full width
- [ ] Charts display properly
- [ ] Tables not cramped

### Tablet (768x1024)
- [ ] Sidebar collapsible
- [ ] Content adjusts
- [ ] Touch-friendly buttons

### Mobile (375x667)
- [ ] Hamburger menu works
- [ ] Drawer opens/closes
- [ ] Forms stack vertically
- [ ] Tables scroll horizontally
- [ ] Cards stack properly

## 13. Browser Compatibility Testing

Test in:
- [ ] Chrome (latest)
- [ ] Firefox (latest)
- [ ] Edge (latest)
- [ ] Safari (if available)

## 14. Performance Testing

### Load Time
1. Clear cache
2. Reload application
3. Check load time < 3 seconds

### API Response Time
1. Check Network tab
2. Verify API calls < 500ms

### Memory Usage
1. Open DevTools → Performance
2. Record session
3. Check for memory leaks

## 15. Security Testing

### Test XSS Prevention
1. Try to inject script in form fields
   - Expected: Sanitized input

### Test SQL Injection
1. Try SQL commands in inputs
   - Expected: Handled by backend

### Test Authorization
1. Try to access admin routes as customer
   - Expected: Access denied

## 16. Data Validation Testing

### Test Required Fields
- [ ] All required fields marked with *
- [ ] Cannot submit with empty required fields
- [ ] Validation messages clear

### Test Data Types
- [ ] Email format validated
- [ ] Phone number format validated
- [ ] Numbers only accept numeric input
- [ ] Dates use date picker

### Test Data Ranges
- [ ] Negative numbers rejected where applicable
- [ ] Maximum lengths enforced
- [ ] Date ranges valid

## 17. Integration Testing

### Test Order Flow
1. Create carrier
2. Create truck (assign to carrier)
3. Create driver (assign to truck)
4. Create cargo
5. Create order (assign cargo and carrier)
6. Update order status to "In Transit"
7. Update order status to "Delivered"
8. Verify dashboard updates

### Test Relationships
1. Delete carrier with trucks
   - Expected: Handle gracefully
2. Delete truck with driver
   - Expected: Handle gracefully

## 18. Edge Cases Testing

### Test Empty States
1. Fresh database
2. Navigate to each page
   - Expected: Empty state message
   - Expected: "Add New" button

### Test Large Datasets
1. Add 100+ records
2. Check table performance
3. Verify pagination (if implemented)

### Test Special Characters
1. Use special characters in names
2. Use unicode characters
3. Verify proper display

## 19. Accessibility Testing

### Keyboard Navigation
- [ ] Tab through all forms
- [ ] Enter submits forms
- [ ] Escape closes dialogs
- [ ] Arrow keys work in dropdowns

### Screen Reader
- [ ] Labels properly associated
- [ ] Error messages announced
- [ ] Status changes announced

## 20. Final Checklist

- [ ] All CRUD operations work
- [ ] No console errors
- [ ] No network errors
- [ ] All validations work
- [ ] All notifications display
- [ ] Responsive on all devices
- [ ] Fast load times
- [ ] Secure authentication
- [ ] Data persists correctly
- [ ] UI is intuitive

## Bug Reporting Template

```
**Bug Title**: [Brief description]

**Steps to Reproduce**:
1. 
2. 
3. 

**Expected Result**: 

**Actual Result**: 

**Browser**: 
**Device**: 
**Screenshot**: [If applicable]
**Console Errors**: [If any]
```

## Test Results Log

| Test Case | Status | Notes | Date |
|-----------|--------|-------|------|
| Login | ✅ | | |
| Register | ✅ | | |
| Dashboard | ✅ | | |
| Orders CRUD | ✅ | | |
| Cargo CRUD | ✅ | | |
| Trucks CRUD | ✅ | | |
| Drivers CRUD | ✅ | | |
| Carriers CRUD | ✅ | | |
| Addresses CRUD | ✅ | | |
| Users CRUD | ✅ | | |

---

**Happy Testing! 🧪**
