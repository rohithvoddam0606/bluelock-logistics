# 🔍 Global Search Feature

## ✨ What's New

Added a global search bar in the navigation bar that searches across all pages in real-time!

## 🎯 Features

### Search Bar Location:
- Located in the top navigation bar
- Always visible on every page
- Searches the current page's data

### What You Can Search:
- **Trucks**: Name, number, status, carrier, location
- **Orders**: Order ID, status, cost, cargo, carrier
- **Drivers**: Name, contact, carrier, truck
- **Cargo**: Name, description, weight
- **Addresses**: Street, city, state, pincode
- **Users**: Name, email, role
- **Carriers**: Name, email, contact

### How It Works:
1. Type in the search bar at the top
2. Results filter instantly as you type
3. Searches all visible columns in the current table
4. Shows "No results found" if nothing matches
5. Clear the search to see all data again

## 🎨 Visual Design

### Search Bar:
- Clean, modern design with search icon
- Placeholder text: "Search trucks, orders, drivers..."
- White text on semi-transparent background
- Smooth hover effect
- Responsive width (expands on larger screens)

### Search Results:
- Instant filtering (no button needed)
- Highlights matching rows
- Shows count of results
- Empty state message when no matches

## 📱 Responsive Design

- **Desktop**: Full-width search bar (40 characters)
- **Tablet**: Medium-width search bar
- **Mobile**: Compact search bar

## 🔧 Technical Details

### Components Updated:
1. **Navbar.js** - Added search bar with Material-UI styled components
2. **DataTable.js** - Added search filtering logic
3. **App.js** - Added search state management
4. **All Pages** - Added searchQuery prop support

### Search Algorithm:
- Case-insensitive search
- Searches all columns in the table
- Handles nested objects (like carrier.name)
- Real-time filtering (no delay)
- Efficient with React.useMemo

### Code Example:
```javascript
// In DataTable.js
const filteredData = React.useMemo(() => {
  if (!searchQuery) return data;
  
  const query = searchQuery.toLowerCase();
  return data.filter(row => {
    return columns.some(column => {
      const value = row[column.field];
      // Search logic...
    });
  });
}, [data, searchQuery, columns]);
```

## 🚀 How to Use

### Basic Search:
1. Go to any page (Trucks, Orders, etc.)
2. Type in the search bar at the top
3. See results filter instantly

### Search Examples:

**On Trucks Page:**
- Type "Alpha" → Shows Truck Alpha
- Type "New York" → Shows trucks in New York
- Type "Available" → Shows available trucks
- Type "FastShip" → Shows trucks from FastShip carrier

**On Orders Page:**
- Type "Delivered" → Shows delivered orders
- Type "15000" → Shows orders with that cost
- Type "Electronics" → Shows orders with electronics cargo

**On Drivers Page:**
- Type "Michael" → Shows driver Michael Johnson
- Type "9123" → Shows drivers with that contact number
- Type "FastShip" → Shows drivers from FastShip

**On Addresses Page:**
- Type "New York" → Shows New York addresses
- Type "10001" → Shows addresses with that pincode
- Type "Main Street" → Shows addresses on Main Street

## ✅ Features

- ✅ Real-time search (instant results)
- ✅ Case-insensitive matching
- ✅ Searches all columns
- ✅ Works on all pages
- ✅ Handles nested data (carrier names, etc.)
- ✅ Shows "No results found" message
- ✅ Clears easily
- ✅ Responsive design
- ✅ Clean UI with search icon

## 🎯 Benefits

1. **Fast Data Access**: Find what you need instantly
2. **Better UX**: No need to scroll through long lists
3. **Efficient**: Search across multiple fields at once
4. **User-Friendly**: Simple, intuitive interface
5. **Consistent**: Same search experience on all pages

## 💡 Usage Tips

### Quick Tips:
- Search is case-insensitive (type "alpha" or "ALPHA")
- Partial matches work (type "Fast" to find "FastShip")
- Search multiple words (type "New York")
- Clear search to see all data again

### Best Practices:
- Use specific terms for better results
- Try different keywords if no results
- Search by status, name, or location
- Use numbers to find IDs or contacts

## 🔮 Future Enhancements

Possible improvements:
- [ ] Search history dropdown
- [ ] Advanced filters (date range, status, etc.)
- [ ] Search suggestions/autocomplete
- [ ] Highlight matching text in results
- [ ] Export search results
- [ ] Save favorite searches
- [ ] Search across all pages simultaneously
- [ ] Voice search
- [ ] Keyboard shortcuts (Ctrl+K)

## 🐛 Troubleshooting

**Search not working?**
- Refresh the browser (Ctrl+F5)
- Make sure you're on a page with data
- Check that data has loaded

**No results showing?**
- Try different search terms
- Check spelling
- Clear search and try again
- Verify data exists in the table

**Search bar not visible?**
- Check if you're logged in
- Refresh the page
- Clear browser cache

## 📝 Files Modified

### Frontend:
1. `components/Navbar.js` - Added search bar UI
2. `components/DataTable.js` - Added search filtering
3. `App.js` - Added search state management
4. `pages/Trucks.js` - Added searchQuery prop
5. `pages/Orders.js` - Added searchQuery prop
6. `pages/Drivers.js` - Added searchQuery prop
7. `pages/Cargo.js` - Added searchQuery prop
8. `pages/Addresses.js` - Added searchQuery prop
9. `pages/Users.js` - Added searchQuery prop
10. `pages/Carriers.js` - Added searchQuery prop

## 🎉 Summary

Your application now has a powerful global search feature that makes finding data quick and easy. Just type in the search bar and watch the results filter in real-time!

---

**Status**: ✅ READY TO USE

Refresh your browser and start searching! 🔍
