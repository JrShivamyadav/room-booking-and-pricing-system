package BookingSystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Date;

public class Bookingsystem extends JFrame  implements ActionListener{

    JFrame frame;
    JTextField tfname,tfnumber ,tfcountry,tfdeposit,cbroomnumber;
    JLabel checktime;
    JComboBox cbid  ;

    JRadioButton rbMale,rbFemale;

    JButton add,Cancel, allocateroom ;
    Bookingsystem(){
        frame=new JFrame();
        frame.setBounds(0,0,1550,880);
        frame.setVisible(true);
        Container c= frame.getContentPane();
        c.setBackground(Color.WHITE);
        c.setLayout(null);

        ImageIcon icon =new ImageIcon(ClassLoader.getSystemResource("p.jpg"));
        Image i1= icon.getImage().getScaledInstance(1550,820,Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(i1);
        JLabel image = new JLabel(i2);
        image.setBounds(0,0,1550,820);
        c.add(image);

        JPanel panel=new JPanel();
        panel.setBounds(310,100,950,665);
        panel.setBackground(Color.PINK);
        panel.setVisible(true);
        panel.setLayout(null);
        image.add(panel);




        JLabel lbltitle = new JLabel("Booking System");
        lbltitle.setBounds(300,0,500,60);
        lbltitle.setFont(new Font("Arial",Font.BOLD,30));
        panel.add(lbltitle);

        JLabel lblid = new JLabel("Id");
        lblid .setBounds(100,120,150,30);
        lblid .setFont(new Font("Arial",Font.PLAIN,17));
        panel.add(lblid );
        String str1[]={"Select Option","Aadhar Card","Passport","Driving Licence","Voter-id Card","Ration Card"};
        cbid = new JComboBox(str1);
        cbid .setBounds(250,120,300,30);
        cbid .setBackground(Color.WHITE);
        panel.add( cbid );

        JLabel lblnumber = new JLabel("Id Number");
        lblnumber.setBounds(100,160,100,30);
        lblnumber.setFont(new Font("Arial",Font.PLAIN,17));
        panel.add(lblnumber);
        tfnumber = new JTextField();
        tfnumber.setBounds(250,160,300,30);
        panel.add(tfnumber);

        JLabel lblname = new JLabel("Name");
        lblname.setBounds(100,210,120,30);
        lblname.setFont(new Font("Arial",Font.PLAIN,17));
        panel.add(lblname);
        tfname = new JTextField();
        tfname.setBounds(250,210,300,30);
        panel.add(tfname);

        JLabel lblgender = new JLabel("Gender");
        lblgender.setBounds(100,260,150,30);
        lblgender.setFont(new Font("Arial",Font.PLAIN,17));
        panel.add(lblgender);
        rbMale= new JRadioButton("Male");
        rbMale .setBounds(250,260,130,30);
        rbMale .setBackground(Color.WHITE);
        panel.add( rbMale);

        rbFemale= new JRadioButton("Female");
        rbFemale .setBounds(380,260,170,30);
        rbFemale.setBackground(Color.WHITE);
        panel.add( rbFemale);

        ButtonGroup group =new ButtonGroup();
        group.add(rbMale);
        group.add(rbFemale);

        JLabel lblcuntry = new JLabel("Country");
        lblcuntry .setBounds(100,310,160,30);
        lblcuntry .setFont(new Font("Arial",Font.PLAIN,17));
        panel.add( lblcuntry );
        tfcountry = new JTextField();
        tfcountry.setBounds(250,310,300,30);
        panel.add( tfcountry );

        JLabel lblroomnumber = new JLabel("Room Number");
        lblroomnumber.setBounds(100,360,180,30);
        lblroomnumber.setFont(new Font("Arial",Font.PLAIN,17));
        panel.add(lblroomnumber);
        cbroomnumber = new JTextField();

        cbroomnumber.setBounds(250,360,100,30);
        cbroomnumber.setBackground(Color.WHITE);
        panel.add(cbroomnumber);

        allocateroom =new JButton("Allocate room");
        allocateroom.setBackground(Color.LIGHT_GRAY);
        allocateroom.setForeground(Color.black);
        allocateroom.setBounds(410,360,140,20);
        allocateroom.addActionListener(this);
        panel.add( allocateroom);

        JLabel lbltime = new JLabel("Checkin Time");
        lbltime.setBounds(100,410,180,30);
        lbltime.setFont(new Font("Arial",Font.PLAIN,17));
        panel.add(lbltime);

        Date date =new Date();

        checktime = new JLabel(""+ date);
        checktime.setBounds(250,410,350,30);
        checktime.setFont(new Font("Arial",Font.PLAIN,17));
        panel.add( checktime);



        JLabel lbldeposit = new JLabel("Deposit");
        lbldeposit .setBounds(100,460,100,30);
        lbldeposit.setFont(new Font("Arial",Font.PLAIN,17));
        panel.add(lbldeposit );
        tfdeposit = new JTextField();
        tfdeposit.setBounds(250,460,300,30);
        panel.add( tfdeposit);

        add =new JButton("Book Room");
        add.setBackground(Color.BLACK);
        add.setForeground(Color.WHITE);
        add.setBounds(130,560,180,35);
        add.addActionListener(this);
        panel.add(add);

        Cancel =new JButton("Back");
        Cancel .setBackground(Color.BLACK);
        Cancel .setForeground(Color.WHITE);
        Cancel .setBounds(360,560,180,35);
        Cancel.addActionListener(this);
        panel.add(Cancel );


        ImageIcon icon1 =new ImageIcon(ClassLoader.getSystemResource("kindpng.png"));
        Image i3= icon1.getImage().getScaledInstance(300,450,Image.SCALE_DEFAULT);
        ImageIcon i4 = new ImageIcon(i3);
        JLabel image1 = new JLabel(i4);
        image1.setBounds(630,100,300,450);
        panel.add(image1);


        frame.setResizable(false);
    }




    public static  void main(String[] args){
        new Bookingsystem();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == add) {
            String id = (String) cbid.getSelectedItem();
            String name = tfname.getText();
            String number = tfnumber.getText();
            String country = tfcountry.getText();
            String deposit = tfdeposit.getText();
            String gender = null;

            if (rbFemale.isSelected()) {
                gender = "Female";
            } else if (rbMale.isSelected()) {
                gender = "Male";
            }
            String room = cbroomnumber.getText();
            String  time =checktime.getText();
            try{
                conn c =new conn();
                String query ="insert into customer values('"+id+"','"+name+"','"+number+"','"+gender+"', '"+country+"','"+room+"','"+time+"', '"+deposit+"')";
                String query1 ="update r set availability='Occupied' where roomnumber='"+room+"'";
                c.s.executeUpdate(query);
                c.s.executeUpdate(query1);

                JOptionPane.showMessageDialog(null,"Room are Booked sucessfully");
                frame.setVisible(false);

            }catch (Exception ae){
                ae.printStackTrace();
            }

        } else if (e.getSource()==Cancel) {
            frame.setVisible(false);

        }
         if (e.getSource()==allocateroom) {
            new SearchRoom();

        }
    }
}
