package com.BankSystem_Project;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class AccountFrame extends JFrame{
    JLabel accnNoLBL, ownerLBL, balanceLBL, cityLBL, genderLBL, amountLBL;
    JTextField accNoTXT, ownerTXT, balanceTXT, amountTXT;
    JComboBox<City> citiesCMB;

    JButton newBTN, saveBTN, showBTN, quitBTN, depositBTN, withdrawBTN;
    JRadioButton maleRDB, femaleRDB;
    ButtonGroup genderBTNGRP;

    JList<Account> accountList;
    JPanel p1, p2, p3, p4, p5;

    Set<Account> accountSet = new TreeSet<>();
    Account acc, x;
    boolean newRec = true;

    // ComboBox Data
    DefaultComboBoxModel<City> citiesCMBMDL;
    DefaultListModel<Account> accountLSTMDL;

    // Table
    JTable table;
    DefaultTableModel tableModel;
    ArrayList<Transaction> translist = new ArrayList<>();

    public AccountFrame(){
        super("Account Operation");
        setLayout(null);
        setSize(800, 600);

        // Adding components to the Frame
        // 1 - Labels
        accnNoLBL = new JLabel("Account No");
        ownerLBL = new JLabel("Owner");
        balanceLBL = new JLabel("Balance");
        cityLBL = new JLabel("City");
        genderLBL = new JLabel("Gender");
        amountLBL = new JLabel("Amount");

        // 2 - Text Field
        accNoTXT = new JTextField(); accNoTXT.setEnabled(false);
        ownerTXT = new JTextField();
        balanceTXT = new JTextField(); balanceTXT.setEnabled(false);
        amountTXT = new JTextField();
        amountTXT.setPreferredSize(new Dimension(150, 25));

        // 3 - Combo Box D
        citiesCMBMDL = new DefaultComboBoxModel<>();
        citiesCMBMDL.addElement(null);
        citiesCMBMDL.addElement(new City("Brooklyn", "New York"));
        citiesCMBMDL.addElement(new City("Kosovo", "Mitrovice"));
        citiesCMBMDL.addElement(new City("Panama", "Florida"));
        citiesCMBMDL.addElement(new City("Miami", "Florida"));

        // Adding data to JCOMBOBOX
        citiesCMB = new JComboBox<City>(citiesCMBMDL);

        // 4 - Radio Buttons
        maleRDB = new JRadioButton("Male", true);
        femaleRDB = new JRadioButton("Female");
        genderBTNGRP = new ButtonGroup();
        genderBTNGRP.add(maleRDB);
        genderBTNGRP.add(femaleRDB);

        // 5 - Button
        newBTN = new JButton("New");
        saveBTN = new JButton("Save");
        showBTN = new JButton("Show");
        quitBTN = new JButton("Quit");
        depositBTN = new JButton("Deposit");
        withdrawBTN = new JButton("WithDraw");

        // 6 - Table
        accountLSTMDL = new DefaultListModel<>();
        accountList = new JList<>(accountLSTMDL);

        // 7 - Panel
        p1 = new JPanel();
        p1.setBounds(5, 5, 300, 150);
        p1.setLayout(new GridLayout(5, 2));

        p2 = new JPanel();
        p2.setBounds(5, 155, 300, 40);
        p2.setLayout(new FlowLayout());

        p3 = new JPanel();
        p3.setBounds(5, 195, 600, 40);
        p3.setLayout(new FlowLayout());

        p4 = new JPanel();
        p4.setBounds(305, 5, 300, 190);
        p4.setLayout(new BorderLayout());

        p5 = new JPanel();
        p5.setBounds(5, 240, 580, 120);
        p5.setLayout(new BorderLayout());

        // Adding components to Panel
        p1.add(accnNoLBL);
        p1.add(accNoTXT);
        p1.add(ownerLBL);
        p1.add(ownerTXT);
        p1.add(balanceLBL);
        p1.add(balanceTXT);
        p1.add(cityLBL);
        p1.add(citiesCMB);
        p1.add(maleRDB);
        p1.add(femaleRDB);

        p2.add(newBTN);
        p2.add(saveBTN);
        p2.add(showBTN);
        p2.add(quitBTN);

        p3.add(amountLBL);
        p3.add(amountTXT);
        p3.add(depositBTN);
        p3.add(withdrawBTN);

        p4.add(accountList);


        // Adding Panels to Frame
        add(p1);
        add(p2);
        add(p3);
        add(p4);
        add(p5);


        // Table creation
        tableModel = new DefaultTableModel();

        table = new JTable(tableModel);
        tableModel.addColumn("TrsNo");
        tableModel.addColumn("TrsDate");
        tableModel.addColumn("TrsType");
        tableModel.addColumn("TrsAmount");

        JScrollPane scrollpane = new JScrollPane(table);


        p5.add(scrollpane);


        /****************** Methods ******************/
        newBTN.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                accNoTXT.setText("");
                ownerTXT.setText("");
                citiesCMB.setSelectedIndex(0);
                maleRDB.setSelected(true);
                balanceTXT.setText("");
                amountTXT.setText("");
                newRec = true;
            }
        });

        saveBTN.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // -Saving new Data
                if(newRec){
                    // Insertion
                    if(ownerTXT.getText().length() !=0){
                        acc = new Account(
                                ownerTXT.getText(),
                                (City) citiesCMB.getSelectedItem(),
                                maleRDB.isSelected()? 'M':'F');

                        accNoTXT.setText(String.valueOf(acc.accNo));
                        accountSet.add(acc);
                        accountLSTMDL.addElement(acc);
                        newRec = false;
                        // ---
                    }else{
                        JOptionPane.showMessageDialog(null, "Please Fill All Fields");
                    }// if it is not a new record , this is for new update |>
                }else{ // Updating
                    accountSet.remove(acc);
                    int a = Integer.parseInt(accNoTXT.getText());
                    String o = ownerTXT.getText();
                    City c =(City) citiesCMB.getSelectedItem();

                    char g = maleRDB.isSelected()?'M':'F';
                    double b = Double.parseDouble(balanceTXT.getText());
                    acc = new Account(a,o,c,g,b);
                    accountSet.add(acc);
                    accountLSTMDL.setElementAt(acc, accountList.getSelectedIndex());
                    newRec = false;
                }

            }
        });
        showBTN.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String s = "";
                Iterator<Account> it = accountSet.iterator();
                while(it.hasNext()){
                    s += it.next().toString() + "\n";
                    JOptionPane.showMessageDialog(null, s);
                }

            }
        });
        depositBTN.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(!newRec && amountTXT.getText().length() !=0){
                    //Adding Transaction to table
                    Transaction t = new Transaction(acc, LocalDate.now(),
                            'D', Double.parseDouble(amountTXT.getText()));
                    DisplayTransactionInTable(t);

                    //Perform deposit from account
                    acc.deposit(Double.parseDouble(amountTXT.getText()));
                    balanceTXT.setText(String.valueOf(acc.balance));
                }

            }
        });
        withdrawBTN.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                if(!newRec && amountTXT.getText().length() != 0){
                    //Adding Transaction to table
                    Transaction t = new Transaction(acc, LocalDate.now(),
                            'W', Double.parseDouble(amountTXT.getText()));

                    DisplayTransactionInTable(t);
                    // Perform withdrawal on account
                    acc.withdraw(Double.parseDouble(amountTXT.getText()));
                    balanceTXT.setText(String.valueOf(acc.balance));
                }

            }
        });
        accountList.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                acc = x = accountList.getSelectedValue();

                accNoTXT.setText(String.valueOf(acc.accNo));
                ownerTXT.setText(acc.owner);
                citiesCMB.setSelectedItem(acc.city);

                if(acc.gender == 'M') maleRDB.setSelected(true);
                else femaleRDB.setSelected(false);

                balanceTXT.setText(String.valueOf(acc.balance));
                amountTXT.setEnabled(true);
                depositBTN.setEnabled(true);
                newRec = false;

                // Clear Table
                for(int i = tableModel.getRowCount() -1; i>=0; i--){
                    tableModel.removeRow(i);
                }
                // Get Transactions to select Account
                SearchTransactionList(acc.accNo);
            }
        });

    }



    private void SearchTransactionList(int accNo) {
        List<Transaction> filteredList = new ArrayList<>();

        // Iterate thought the list
        for(Transaction t : translist){
            //Filter values that contain trsNo
            if(t.getAcc().accNo == accNo){
                filteredList.add(t);
            }
        }
        //Display the filtered list
        for(int i = 0; i < filteredList.size(); i++){
            //Display data into table
            tableModel.addRow(new Object[]{
                    filteredList.get(i).getTrsNo(),
                    filteredList.get(i).getDate(),
                    filteredList.get(i).getOperation(),
                    filteredList.get(i).getAmount()
            });

        }
    }

    private void DisplayTransactionInTable(Transaction t) {

        // Display data into table
        tableModel.addRow(new Object[]{
                t.getTrsNo(),
                t.getDate(),
                t.getOperation(),
                t.getAmount()
        });
        // Adding object to array list
        translist.add(t);

    }



    public static void main(String[] args){
        AccountFrame af = new AccountFrame();
        af.setVisible(true);
        af.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }
}
