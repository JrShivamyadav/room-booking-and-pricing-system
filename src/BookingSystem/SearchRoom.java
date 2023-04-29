package BookingSystem;


import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class SearchRoom extends JFrame implements ActionListener {

    JFrame frame;
    JTable table;
    JButton submit, back;

    JComboBox bedType;
    JCheckBox availble;
    SearchRoom() {
        frame = new JFrame();
        frame.setBounds(300, 200, 1050, 600);
        frame.setVisible(true);
        Container c = frame.getContentPane();
        c.setBackground(Color.WHITE);
        c.setLayout(null);

        JLabel text =new JLabel("Search for Room");
        text.setFont(new Font("Tohma",Font.BOLD,20));
        text.setBounds(400,30,200,20);
        c.add(text);

        JLabel lblbed=new JLabel("Bed Type");
        lblbed.setFont(new Font("Tohma",Font.PLAIN,15));
        lblbed.setBounds(50,100,100,20);
        c.add(lblbed);

        bedType =new JComboBox(new String[]{"Single Bed", "Double Bed"});
        bedType.setBounds(150,100,100,20);
        bedType.setBackground(Color.WHITE);
        c.add(bedType);

        availble=new JCheckBox("only display Available");
        availble.setFont(new Font("Tohma",Font.PLAIN,15));
        availble.setBounds(680,100,200,20);
        availble.setBackground(Color.WHITE);
        c.add(availble);

        JLabel l1 =new JLabel("RoomNumber");
        l1.setBounds(20,160,120,20);
        c.add(l1);

        JLabel l2 =new JLabel("Availaility");
        l2.setBounds(230,160,120,20);
        c.add(l2);

        JLabel l3 =new JLabel("Status");
        l3.setBounds(420,160,120,20);
        c.add(l3);

        JLabel l4 =new JLabel("Price");
        l4.setBounds(620,160,120,20);
        c.add(l4);
        JLabel l5 =new JLabel("Bed Type");
        l5.setBounds(830,160,120,20);
        c.add(l5);


        table =new JTable();
        table.setBounds(20,180,1000,340);
        c.add(table);
        try{
            conn cn = new conn();
            ResultSet rs=cn.s.executeQuery("select * from r ");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        }catch (Exception ee){
            ee.printStackTrace();
        }
        frame.setResizable(false);

        submit= new JButton("Submit");
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.setBounds(320,520,120,30);
        submit.addActionListener(this);
        c.add(submit);

        back= new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(480,520,120,30);
        back.addActionListener(this);
        c.add(back);

    }
    public  static void main(String[] args){
        new SearchRoom();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==submit){
            try{
                String query1= "select * from r where type ='"+bedType.getSelectedItem()+"'";
                String query2="select * from r where availability ='Available' AND type ='"+bedType.getSelectedItem()+"'";
                conn cn = new conn();
                ResultSet r;
                if(availble.isSelected()){
                    r=cn.s.executeQuery(query2);
                }
                else {
                    r=cn.s.executeQuery(query1);
                }
                table.setModel(DbUtils.resultSetToTableModel(r));
            }catch (Exception ee){
                ee.printStackTrace();
            }
        }
        else {
            frame.setVisible(false);

        }
    }

}

