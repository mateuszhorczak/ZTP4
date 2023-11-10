package com.github.mateuszhorczak;

import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.ListSelectionListener;


// dane przechowywane w bazie
interface Data {
    public int get(int idx);
    public void set(int idx, int value);
    public int size();
}
