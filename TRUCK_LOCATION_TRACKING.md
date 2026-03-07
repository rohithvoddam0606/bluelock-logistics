# 🚚 Truck Location Tracking Feature

## ✨ What's New

Added real-time location tracking for trucks in the logistics management system.

## 🎯 Features Added

### Backend Changes:

1. **Truck Entity** - Added `currentLocation` field
   - New field: `@ManyToOne Address currentLocation`
   - Tracks the current location of each truck
   - Can be null if location is not set

2. **TruckService** - Added full update method
   - New method: `updateTruck(int id, Truck updatedTruck)`
   - Handles updating all truck fields including location
   - Validates carrier and address existence

3. **TruckController** - Added REST endpoints
   - `PUT /api/trucks/{id}` - Full truck update
   - `DELETE /api/trucks/{id}` - Delete truck by ID

4. **DataInitializer** - Updated sample data
   - All sample trucks now have initial locations
   - Locations distributed across different cities

### Frontend Changes:

1. **Trucks Page** - Enhanced UI
   - New column: "Current Location" showing city and state
   - Location dropdown in create/edit form
   - Fetches all addresses for selection
   - Displays location as "City, State" format

2. **Form Improvements**
   - Added address selection dropdown
   - Shows full address details in dropdown
   - Option to set "None" for no location
   - Properly handles location updates

## 📊 How It Works

### Viewing Truck Locations:
1. Go to "Truck Management" page
2. See "Current Location" column showing each truck's location
3. Location displays as: "City, State"
4. Shows "Not Set" if no location assigned

### Setting/Updating Location:
1. Click "Add Truck" or "Edit" on existing truck
2. Select location from "Current Location" dropdown
3. Dropdown shows: "Street, City, State - Pincode"
4. Select "None" to clear location
5. Save changes

### Sample Data:
- Truck Alpha → New York, New York
- Truck Beta → Los Angeles, California
- Truck Gamma → Chicago, Illinois
- Truck Delta → New York, New York
- Truck Epsilon → Los Angeles, California

## 🔧 Technical Details

### Database Schema:
```sql
ALTER TABLE truck ADD COLUMN current_location_id INTEGER;
ALTER TABLE truck ADD FOREIGN KEY (current_location_id) REFERENCES address(id);
```

### API Request Format:
```json
{
  "id": 1,
  "name": "Truck Alpha",
  "number": 1001,
  "capacity": 5000,
  "status": "Available",
  "carrier": { "id": 1 },
  "currentLocation": { "id": 1 }
}
```

### API Response Format:
```json
{
  "statuscode": 200,
  "message": "Truck updated successfully",
  "data": {
    "id": 1,
    "name": "Truck Alpha",
    "number": 1001,
    "capacity": 5000,
    "status": "Available",
    "carrier": {
      "id": 1,
      "name": "FastShip Logistics"
    },
    "currentLocation": {
      "id": 1,
      "street": "123 Main Street",
      "city": "New York",
      "state": "New York",
      "pincode": 10001
    }
  }
}
```

## 🚀 Next Steps to Apply Changes

### Option 1: Restart with Fresh Database (Recommended)
```bash
# Stop backend
# Delete database files
Remove-Item "bluelock-logistics/backend/bluelockdb.mv.db" -Force
Remove-Item "bluelock-logistics/backend/bluelockdb.trace.db" -Force -ErrorAction SilentlyContinue

# Restart backend
cd bluelock-logistics/backend
java -jar target/bluelock_logistics-0.0.1-SNAPSHOT.jar
```

### Option 2: Rebuild Backend
```bash
cd bluelock-logistics/backend
./mvnw.cmd clean package -DskipTests
java -jar target/bluelock_logistics-0.0.1-SNAPSHOT.jar
```

### Frontend:
The frontend changes are already saved. Just refresh your browser!

## 📝 Files Modified

### Backend:
1. `entity/Truck.java` - Added currentLocation field
2. `service/TruckService.java` - Added updateTruck method
3. `controller/TruckController.java` - Added PUT and DELETE endpoints
4. `config/DataInitializer.java` - Added locations to sample trucks

### Frontend:
1. `pages/Trucks.js` - Added location column and form field
2. `services/truckService.js` - Already had update method

## 💡 Usage Examples

### Track Truck Movement:
1. Truck starts at New York
2. Edit truck and change location to Chicago
3. Later update to Los Angeles
4. View location history in the table

### Filter by Location (Future Enhancement):
- Add search/filter by city
- Show all trucks in a specific state
- Map view of truck locations

## 🎨 UI Improvements

- Location column width: 250px
- Shows full "City, State" format
- "Not Set" placeholder for empty locations
- Dropdown shows complete address details
- Clean, readable format

## ✅ Testing

Test the feature:
1. Create a new truck with a location
2. Edit existing truck to change location
3. Set location to "None"
4. Verify location displays correctly in table
5. Check that location persists after refresh

## 🔮 Future Enhancements

- [ ] Location history tracking
- [ ] Map view of truck locations
- [ ] Route planning between locations
- [ ] Geofencing alerts
- [ ] Real-time GPS integration
- [ ] Distance calculations
- [ ] Estimated arrival times
- [ ] Location-based notifications

## 📞 Support

If you encounter issues:
1. Check browser console for errors
2. Verify backend is running
3. Ensure database has address data
4. Check that addresses are loaded in dropdown
5. Verify API responses in Network tab
