package com.github.mateuszhorczak;

import java.util.ArrayList;
import java.util.LinkedList;

class RealData implements Data {

    private int[] realData;
    private LinkedList<CopyOnWrite> copies = new LinkedList<>();

    public Data copy() {
        CopyOnWrite copy = new CopyOnWrite(this);
        copies.addLast(copy);
        return copy;
    }

    protected void detachCopy() {
        for (CopyOnWrite copy : copies) {
            copy.copy();
        }
        copies.clear();
    }

    public RealData(int size) {
        realData = new int[size];
    }

    public int get(int index) {
        return realData[index];
    }

    public void set(int index, int value) {
        if (!copies.isEmpty()) {
            detachCopy();
        }
        realData[index] = value;
    }

    public int size() {
        return realData.length;
    }
}
