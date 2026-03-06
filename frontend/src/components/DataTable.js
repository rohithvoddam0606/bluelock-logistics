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

const DataTable = ({ columns, data, onEdit, onDelete, onView }) => {
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
          {data.map((row) => (
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
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
};

export default DataTable;
