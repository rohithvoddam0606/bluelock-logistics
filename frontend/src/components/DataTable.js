import React from 'react';
import {
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Paper,
  IconButton,
  Chip,
} from '@mui/material';
import { Edit, Delete, Visibility } from '@mui/icons-material';

const DataTable = ({ columns, data = [], onEdit, onDelete, onView, searchQuery = '' }) => {
  // Filter data based on search query
  const filteredData = React.useMemo(() => {
    // Ensure data is an array
    const dataArray = Array.isArray(data) ? data : [];
    
    if (!searchQuery || searchQuery.trim() === '') return dataArray;
    
    const query = searchQuery.toLowerCase().trim();
    return dataArray.filter(row => {
      return columns.some(column => {
        const value = row[column.field];
        if (value === null || value === undefined) return false;
        
        // Handle nested objects (like carrier.name)
        if (typeof value === 'object' && value !== null) {
          return Object.values(value).some(v => 
            v !== null && v !== undefined && String(v).toLowerCase().includes(query)
          );
        }
        
        return String(value).toLowerCase().includes(query);
      });
    });
  }, [data, searchQuery, columns]);

  const getStatusColor = (status) => {
    switch (status?.toLowerCase()) {
      case 'delivered':
        return 'success';
      case 'in transit':
        return 'warning';
      case 'pending':
        return 'info';
      case 'cancelled':
        return 'error';
      default:
        return 'default';
    }
  };

  const renderCell = (row, column) => {
    const value = row[column.field];
    
    if (column.field === 'status') {
      return <Chip label={value} color={getStatusColor(value)} size="small" />;
    }
    
    if (column.renderCell) {
      return column.renderCell(row);
    }
    
    return value;
  };

  return (
    <TableContainer component={Paper}>
      <Table>
        <TableHead>
          <TableRow>
            {columns.map((column) => (
              <TableCell key={column.field} sx={{ fontWeight: 'bold' }}>
                {column.headerName}
              </TableCell>
            ))}
            <TableCell sx={{ fontWeight: 'bold' }}>Actions</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {!Array.isArray(data) ? (
            <TableRow>
              <TableCell colSpan={columns.length + 1} align="center">
                Loading data...
              </TableCell>
            </TableRow>
          ) : filteredData.length === 0 ? (
            <TableRow>
              <TableCell colSpan={columns.length + 1} align="center">
                {searchQuery && searchQuery.trim() !== '' ? 'No results found for your search' : 'No data available'}
              </TableCell>
            </TableRow>
          ) : (
            filteredData.map((row) => (
            <TableRow key={row.id} hover>
              {columns.map((column) => (
                <TableCell key={column.field}>
                  {renderCell(row, column)}
                </TableCell>
              ))}
              <TableCell>
                {onView && (
                  <IconButton color="info" size="small" onClick={() => onView(row)}>
                    <Visibility />
                  </IconButton>
                )}
                {onEdit && (
                  <IconButton color="primary" size="small" onClick={() => onEdit(row)}>
                    <Edit />
                  </IconButton>
                )}
                {onDelete && (
                  <IconButton color="error" size="small" onClick={() => onDelete(row)}>
                    <Delete />
                  </IconButton>
                )}
              </TableCell>
            </TableRow>
          ))
          )}
        </TableBody>
      </Table>
    </TableContainer>
  );
};

export default DataTable;
