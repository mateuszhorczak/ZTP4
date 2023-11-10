package com.github.mateuszhorczak;

import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.ListSelectionListener;

// baza danych - kolekcja Data
class Baza extends AbstractListModel<Data> {
    private ArrayList<Data> array = new ArrayList<Data>();

    public void add(Data tab) {
        int index = array.size() - 1;
        array.add(tab);
        fireIntervalAdded(this, index, index);
    }

    public void remove(int index) {
        array.remove(index);
        fireIntervalRemoved(this, array.size(), array.size());
    }

    public int getSize() {
        return array.size();
    }

    public Data getElementAt(int index) {
        return array.get(index);
    }
}