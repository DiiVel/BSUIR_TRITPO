package client.model;

import globalmodels.Cargo;
import utils.Event;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class SimpleTableModel extends AbstractTableModel {
    public Event wrongDataEvent = new Event();

    public List<Cargo> getItems() {
        return items;
    }

    List<Cargo> items = new ArrayList<>();
    private boolean isLocked;

    @Override
    public int getRowCount() {
        return items.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (isLocked) {
            return false;
        }
        return true;
    }

    public void clearTable(){
        items.clear();
        fireTableDataChanged();
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Тип груза";
            case 1:
                return "Масса (кг)";
            default:
                return "No column";
        }
    }

    @Override
    public Object getValueAt(int row, int column) {
        var item = items.get(row);
        switch (column) {
            case 0:
                return item.getType();
            case 1:
                return item.getWeight();
            default:
                return "Not column number";
        }
    }

    @Override
    public void setValueAt(Object o, int row, int column) {
        var item = items.get(row);
        switch (column) {
            case 0:
                item.setType(o.toString());
                break;
            case 1:
                var data = o.toString();
                if (data.matches("-?\\d+.?\\d*")) {
                    var parsed = Double.valueOf(data);
                    if (!item.setWeight(parsed)) {
                        wrongDataEvent.notifyObservers();
                    }
                } else {
                    wrongDataEvent.notifyObservers();
                }
                break;
        }
    }

    public void addRow() {
        if (isLocked) {
            return;
        }
        Cargo tableItem = new Cargo();
        items.add(tableItem);
        fireTableDataChanged();
    }

    public void lock() {
        isLocked = true;
    }
    public void unlock() {
        isLocked = false;
    }
}
