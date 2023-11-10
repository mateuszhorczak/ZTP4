package com.github.mateuszhorczak;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

public class TableAdapter extends AbstractTableModel {
    Data data = null;

    public TableAdapter() {
    }

    @Override
    public int getRowCount() {
        if (data == null) {
            return 0;
        }
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return rowIndex;
        }
        return data.get(rowIndex);
    }

    @Override
    public String getColumnName(int column) {
        if (column == 0) {
            return "index";
        } else if (column == 1) {
            return "value";
        }
        return super.getColumnName(column);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return false;
        } else if (columnIndex == 1) {
            return true;
        }
        return super.isCellEditable(rowIndex, columnIndex);
    }

    @Override
    public void setValueAt(Object Value, int rowIndex, int columnIndex) {
        if (columnIndex == 1) {
            data.set(rowIndex, (Integer) Value);
        }
        super.setValueAt(Value, rowIndex, columnIndex);
        fireTableDataChanged();
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return Integer.class;
    }

    public void changeList(Data data) {
        this.data = data;
        fireTableDataChanged();
    }

}
