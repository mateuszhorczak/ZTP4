package com.github.mateuszhorczak;

public class CopyOnWrite implements Data {
    private RealData original, copy;

    public CopyOnWrite(RealData original) {
        this.original = original;
    }

    protected void copy() {
        if (copy != null) {
            return;
        }
        copy = new RealData(original.size());
        for (int i = 0; i < original.size(); i++) {
            copy.set(i, original.get(i));
        }
        original = null;
    }

    @Override
    public int get(int index) {
        if (copy == null) {
            return original.get(index);
        }
        return copy.get(index);
    }

    @Override
    public void set(int index, int value) {
        if (copy == null) {
            original.detachCopy();
        }
        copy.set(index, value);

    }

    @Override
    public int size() {
        if (copy == null) {
            return original.size();
        }
        return copy.size();
    }
}
