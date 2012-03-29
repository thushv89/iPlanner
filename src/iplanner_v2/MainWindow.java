package iplanner_v2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ruchira
 *
 */
public class MainWindow extends DefaultTableCellRenderer {

    private JPanel mainPanel = new JPanel();
    private JPanel calenderPanel = new JPanel();
    private JPanel calenderPanelHolder = new JPanel();
    private JPanel dateSelectionPanel = new JPanel();
    private JPanel inputPanel = new JPanel();
    private JPanel buttonPanel = new JPanel();
    private JPanel lablePanel = new JPanel();
    private JButton exitBtn = new JButton("  Exit  ");
    private JButton updateBtn = new JButton(" Update ");
    private JButton showData = new JButton(" Train ");
    private JButton newBtn = new JButton("New Meeting");
    private JButton showTasksBtn = new JButton(" Show tasks ");
    private JFrame frame = new JFrame("iPlanner");
    private JFrame newMeeting;
    private JTable table = new JTable();
    private PrintCalendar printCalendar = new PrintCalendar();
    private JLabel heading = new JLabel("iPlanner");
    private JLabel task = new JLabel("Task");
    private JLabel date = new JLabel("Date");
    private JLabel time = new JLabel("Time");
    private JLabel month = new JLabel("Month");
    private JLabel year = new JLabel("Year");
    private JComboBox monthCmb = new JComboBox();
    private JComboBox yearCmb = new JComboBox();
    private JLabel meetingwith = new JLabel("Meeting with");
    private JTextField taskTXTField = new JTextField();
    private JTextField dateTXTField = new JTextField();
    private JTextField timeTXTField = new JTextField();
    private JTextField meetingWithTXTField = new JTextField();
    private Traindata tranTraindata;
    private String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    private DefaultTableModel tableModel = null;
    private Object[][] data;
    private int monthInt;
    private MeetingList meetingList;
    private Meeting meeting;

    public MainWindow() {
        initMonth();
        meetingList = IOHandler.readObject("object.dat");
        System.out.println(">> " + meetingList.getAl().size());
        for (int i = 0; i < meetingList.getAl().size(); i++) {
            System.out.println(">> " + meetingList.getAl().get(i).getDay());
        }
    }

    private void initMonth() throws NumberFormatException {
        Date date = new Date();
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat("MM");
        System.out.println("Month " + sdf.format(date));
        monthInt = Integer.parseInt(sdf.format(date)) - 1;
        System.out.println(monthInt);
    }

    public void setPanel() throws UnsupportedLookAndFeelException {

        tranTraindata = new Traindata();
        //  newMeeting=new NewMeeting();
        newMeeting = new NewMeeting();
        // Set BorderLayout with horizontal gap 5 and vertical gap 10
        mainPanel.setLayout(new BorderLayout(5, 2));

        // Set the label
        heading.setFont(new java.awt.Font("Andalus", 0, 18)); // NOI18N
        heading.setForeground(new java.awt.Color(102, 102, 102));
        heading.setText("iPlanner");
        lablePanel.add(heading);


        // set the calendar
        tableModel = printCalendar.printMonthBodyTable(2012, monthInt);
        table.setColumnSelectionAllowed(true);
    
      
       table.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);


        for (int i = 2012; i < 2050; i++) {
            yearCmb.addItem(i);
        }
        for (int i = 0; i < months.length; i++) {
            monthCmb.addItem(months[i]);
        }



        monthCmb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox) e.getSource();
                int selectedIndex = cb.getSelectedIndex();
                tableModel = printCalendar.printMonthBodyTable((yearCmb.getSelectedIndex() + 2012), (selectedIndex + 1));
                setTablePropertiesOnUpdate();
            }
        });

        yearCmb.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox) e.getSource();
                // int selectedIndex = cb.getSelectedIndex();
                tableModel = printCalendar.printMonthBodyTable((yearCmb.getSelectedIndex() + 2012), (monthCmb.getSelectedIndex() + 1));
                setTablePropertiesOnUpdate();
            }
        });

        dateSelectionPanel.add(year);
        dateSelectionPanel.add(yearCmb);
        dateSelectionPanel.add(month);
        dateSelectionPanel.add(monthCmb);
        setTableProperties();

        // add buttons
        buttonPanel.add(newBtn);
        buttonPanel.add(showData);
        buttonPanel.add(showTasksBtn);
        buttonPanel.add(exitBtn);
        buttonPanel.setBorder(new TitledBorder("Operations"));

        // set the input panel
        inputPanel.setLayout(new GridLayout(4, 2, 5, 5));
        inputPanel.add(date);
        inputPanel.add(dateTXTField);
        inputPanel.add(time);
        inputPanel.add(timeTXTField);
        inputPanel.add(task);
        inputPanel.add(taskTXTField);
        inputPanel.add(meetingwith);
        inputPanel.add(meetingWithTXTField);
        inputPanel.setBorder(new TitledBorder(" Input "));
        buttonPanel.setSize(200, 50);

        // Add panels to the frame
        mainPanel.add(calenderPanelHolder, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        mainPanel.add(lablePanel, BorderLayout.NORTH);

        // set the frame
        frame.add(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(480, 315);
        frame.setLocationRelativeTo(null);

        exitBtn.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        newBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                newMeeting.setVisible(true);
            }
        });
        
        showData.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tranTraindata.setVisible(true);
            }
        });

        showTasksBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 String value =table.getModel().getValueAt(table.getSelectedRow(), table.getSelectedColumn()).toString();
                 System.out.println(value);
                 String tasksStr = new String();
                 meetingList = IOHandler.readObject("object.dat");
                 
                 for(int i=0;i<meetingList.getAl().size();i++){
                     if(meetingList.getAl().get(i).getDay()== table.getSelectedColumn()){
                         tasksStr += meetingList.getAl().get(i).getTask()+" with "+meetingList.getAl().get(i).getMeetingWith()+" at "+meetingList.getAl().get(i).getLocation()+" on "+CommonUtil.intTotimeSlot(meetingList.getAl().get(i).getTimeSlot())+"\n";
                     }
                 }
                 JOptionPane.showMessageDialog(null,tasksStr);
            }
        });
    }

   
    public void setTableProperties() {
        // set the scrollPane
        table.setModel(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setSize(100, 50);
        calenderPanelHolder.setLayout(new FlowLayout());
        calenderPanelHolder.add(dateSelectionPanel);
        calenderPanelHolder.add(scrollPane);
        calenderPanelHolder.setOpaque(true);
    }

    public void setTablePropertiesOnUpdate() {
        table.setModel(tableModel);

    }

    public void actionPerformed(ActionEvent e) {
        monthCmb = (JComboBox) e.getSource();
        System.out.println(">> " + e.getActionCommand());
        System.out.println("key event occured");
        // String selectedMonth = (String)monthCmb.getSelectedItem();
        tableModel = printCalendar.printMonthBodyTable((yearCmb.getSelectedIndex() + 2012), (monthCmb.getSelectedIndex() + 1));
        setTablePropertiesOnUpdate();
    }

    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        try {
            UIManager.setLookAndFeel(new com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        MainWindow view = new MainWindow();
        view.setPanel();

        CommonUtil.tr_keywords.clear();
        CommonUtil.tr_priorities.clear();
        IOHandler.readObject("object.dat");
        IOHandler.readTrainingSet("training_data.tr");

        ArrayList<Meeting> meetings = IOHandler.getTrainingData().getAl();
        for (Meeting m : meetings) {
            CommonUtil.tr_keywords.add(m.getMeetingWithNum());
            CommonUtil.tr_priorities.add(m.getPriority());
        }
    }
}
