import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
  

class temp extends JFrame
{
    JTextField tempinput, tempoutput;
    ButtonListener buttonListener;
    JLabel inputlabel,convertlabel,outputlabel,imglabel,Type;
    JComboBox comboBox1, comboBox2;


    public temp() 
    {
        Container c = getContentPane();
        c.setLayout(null);
        c.setBackground(Color.white);
        setTitle("TEMPERATURE  CONVERTER");
        setSize(400,500);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel=new JPanel();
        panel.setLayout(null);
        JLabel label=new JLabel("TEMPERATURE CONVERTER");
        label.setBounds(25,0,400,100);
        label.setFont(new Font("Algerian",Font.BOLD,25));
        panel.add(label);
        panel.setBackground(Color.cyan);
        panel.setBounds(0,0,500,100);
        c.add(panel); 

        inputlabel=new JLabel("Degrees");
        inputlabel.setFont(new Font("Comic Sans MS",Font.BOLD,18));
        inputlabel.setSize(270,20);
        inputlabel.setLocation(32,120);

        Type=new JLabel("Type");
        Type.setFont(new Font("Comic Sans MS",Font.BOLD,18));
        Type.setSize(270,20);
        Type.setLocation(210,120);

        String s1[] = {"Celsius", "Fahrenheit", "Kelvin"};
        comboBox1 = new JComboBox(s1);
        comboBox1.setFont(new Font("Book Antiqua",Font.BOLD,15));
        comboBox1.setBounds(210,155,150,30);
        add(comboBox1);

        tempinput = new JTextField(10);
        tempinput.setSize(100,30);
        tempinput.setLocation(30,155);
        tempinput.setFont(new Font("Book Antiqua",Font.BOLD,15));


        String s2[] = {"Celsius", "Fahrenheit", "Kelvin"};
        comboBox2 = new JComboBox(s2);
        comboBox2.setFont(new Font("Book Antiqua",Font.BOLD,15));
        comboBox2.setBounds(210,210,150,30);
        add(comboBox2);

        convertlabel = new JLabel("Convert To");
        convertlabel.setFont(new Font("Comic Sans MS",Font.BOLD,18));
        convertlabel.setSize(270,20);
        convertlabel.setLocation(30,210);

        tempoutput = new JTextField(10);
        tempoutput.setSize(150,30);
        tempoutput.setLocation(210,270);
        tempoutput.setBackground(Color.white);
        tempoutput.setFont(new Font("Book Antiqua",Font.BOLD,15));

        outputlabel = new JLabel("Result");
        outputlabel.setFont(new Font("Comic Sans MS",Font.BOLD,18));
        outputlabel.setSize(270,20);
        outputlabel.setLocation(40,270);

        JButton button =new JButton("Convert");
        button.setSize(335,40);
        button.setLocation(25,320);
        button.setFont(new Font("Comic Sans MS",Font.BOLD,20));
        button.setBackground(Color.lightGray);
        buttonListener = new ButtonListener();
        button.addActionListener(buttonListener);

        c.add(outputlabel);
        c.add(Type);
        c.add(convertlabel);
        c.add(inputlabel);
        c.add(tempinput);
        c.add(tempoutput);
        c.add(button);
        c.add(comboBox1);
        c.add(comboBox2);   
        tempoutput.setEditable(false);
    }


    private class ButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String temp = (String) comboBox1.getSelectedItem();
            String tempConvert = (String) comboBox2.getSelectedItem();


            if(temp.equals("Celsius") && tempConvert.equals("Fahrenheit")){
                    double c = Double.parseDouble(tempinput.getText());
                    double f = (double) (c*1.8+32);
                    tempoutput.setText(String.valueOf(f));
            }
           else if (temp.equals("Celsius") && tempConvert.equals("Kelvin")) {
                    double c = Double.parseDouble(tempinput.getText());
                    double k = (double) (c+273);
                    tempoutput.setText(String.valueOf(k));
            }
           else if (temp.equals("Celsius") && tempConvert.equals("Celsius")) {
                    double c = Double.parseDouble(tempinput.getText());
                    tempoutput.setText(String.valueOf(c));
            }


            if(temp.equals("Fahrenheit") && tempConvert.equals("Celsius")) {
                double f = Double.parseDouble(tempinput.getText());
                double c = (double)((f - 32)*5/9);
                tempoutput.setText(String.valueOf(c));
            }
            else if(temp.equals("Fahrenheit") && tempConvert.equals("Kelvin")) {
                double f = Double.parseDouble(tempinput.getText());
                double k = (double)((f - 32)*5/9 + 273.15);
                tempoutput.setText(String.valueOf(k));
            }
            else if(temp.equals("Fahrenheit") && tempConvert.equals("Fahrenheit")) {
                double f = Double.parseDouble(tempinput.getText());
                tempoutput.setText(String.valueOf(f));
            }


            if(temp.equals("Kelvin") && tempConvert.equals("Fahrenheit")) {
                double k = Double.parseDouble(tempinput.getText());
                double f = (double) ((k*(9/5))-459.67);
                tempoutput.setText(String.valueOf(f));
            }
           else if (temp.equals("Kelvin") && tempConvert.equals("Kelvin")) {
                double k = Double.parseDouble(tempinput.getText());
                tempoutput.setText(String.valueOf(k));
            }
           else if (temp.equals("Kelvin") && tempConvert.equals("Celcius")) {
                double k = Double.parseDouble(tempinput.getText());
                double c = (double) (k-273);
                tempoutput.setText(String.valueOf(c));
                }
            }
    }


    public static void main(String[] args)
    {
        new temp();
    }
}