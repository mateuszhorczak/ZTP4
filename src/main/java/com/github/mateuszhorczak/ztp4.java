package com.github.mateuszhorczak;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

public class ztp4 {

   private static Logger LOGGER = Logger.getLogger(ztp4.class.getName());

    public static void main(String[] args) {
    	try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
        	LOGGER.log(Level.SEVERE, null, ex);
        }

        final Baza baza = new Baza();

        final JFrame frame = new JFrame("Zadanie 4");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JSplitPane splitPane = new JSplitPane();

        final JList<Data> list = new JList<>(baza);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setBorder(BorderFactory.createTitledBorder(" Tablice: "));
        splitPane.setLeftComponent(scrollPane);

        final TableAdapter tableAdapter = new TableAdapter();
        JTable table = new JTable(tableAdapter);
        scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder(" Zawartość: "));
        splitPane.setRightComponent(scrollPane);

        frame.getContentPane().add(splitPane);

        JMenuBar bar = new JMenuBar();
        JButton add = new JButton("Dodaj tablicę");
        JButton del = new JButton("Usuń tablicę");
        JButton cp = new JButton("Kopiuj tablicę");
        bar.add(add);
        bar.add(del);
        bar.add(cp);

        frame.setJMenuBar(bar);

        frame.setSize(600, 450);
        frame.setVisible(true);

        splitPane.setDividerLocation(0.5);

        add.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String value = JOptionPane.showInputDialog(frame,
                        "Podaj rozmiar tablicy",
                        "Dodaj",
                        JOptionPane.INFORMATION_MESSAGE);
                try{
                    int size = Integer.parseInt(value);
                    baza.add(new RealData(size));
                } catch(Exception ex) {
                	//FIXME add exception handling!
                	 /* ... */
                    LOGGER.log(Level.SEVERE, "Błędny format liczby", ex);
                    JOptionPane.showMessageDialog(frame, "Błędny format liczby", "Błąd", JOptionPane.ERROR_MESSAGE);
                };
            }
        });
        del.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                int idx = list.getSelectedIndex();
                try{
                    baza.remove(idx);
                } catch(Exception ex) {
                    LOGGER.log(Level.WARNING, "Próba usunięcia z niepoprawnego indeksu", ex);
                    JOptionPane.showMessageDialog(frame, "Proszę wybrać tablicę do usunięcia", "Błąd", JOptionPane.WARNING_MESSAGE);
                };
            }
        });

        cp.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try{
                    RealData realData = new RealData(list.getSelectedValue().size());
                    for (int i = 0; i < list.getSelectedValue().size(); i++) {
                        realData.set(i, list.getSelectedValue().get(i));
                    }
                    baza.add(realData.copy());
                } catch(Exception ex) {
                    LOGGER.log(Level.WARNING, "Próba usunięcia z niepoprawnego indeksu", ex);
                    JOptionPane.showMessageDialog(frame, "Proszę wybrać tablicę do usunięcia", "Błąd", JOptionPane.WARNING_MESSAGE);
                };
            }
        });

        // zmiana wyboru na liście powoduje odświeżenie tabeli
        list.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                int idx = list.getSelectedIndex();
                if (idx >= 0) {
                    tableAdapter.changeList(list.getSelectedValue());
                }
            }
        });
    }
}
