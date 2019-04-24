import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// class CalcFrame for creating a calculator frame and added windolistener to
// close the calculator

class CalcFrame extends Frame
{
    CalcFrame(String str)
    {
        // call to superclass
        super(str);
        // to close the calculator(Frame)
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing (WindowEvent we)
            {
                System.exit(0);
            }
        });
    }
}

// main class Calculator implemnets two interfaces ActionListener
//  and ItemListener

public class Calculator implements ActionListener, ItemListener
{
    // creating instances of objects
    CalcFrame fr;
    MenuBar mb;
    Menu view, font, about;
    MenuItem bold, regular, author;
    CheckboxMenuItem basic, scientific, IMC, Conversor, Quad_Formula;
    CheckboxGroup cbg;
    Checkbox radians, degrees;
    TextField display;
    Button key[] = new Button[20]; // creates a button object array of 20
    Button clearAll, clearEntry, round;
    Button scientificKey[] = new Button[10]; // creates a button array of 8
    // declaring variables
    boolean addButtonPressed, subtractButtonPressed, multiplyButtonPressed;
    boolean divideButtonPressed, decimalPointPressed, powerButtonPressed;
    boolean roundButtonPressed = false;
    double initialNumber;      // the first number for the two number operation
    double currentNumber = 0;  // the number shown in the screen while it is being pressed
    int decimalPlaces = 0;

    // main function
    public static void main ()
    {
      // constructor
           Calculator calc = new Calculator();
           calc.makeCalculator();
    }

    public void makeCalculator()
    {
      // size of the button
      final int BWIDTH = 30;
      final int BHEIGHT = 25;
      int count =1;
      // create frame for the calculator
      fr = new CalcFrame("Basic Calculator");
      // set the size
      fr.setSize(500,500);
      fr.setResizable(false);
      fr.setBackground(Color.gray);
      // create a menubar for the frame
      mb = new MenuBar();
      // add menu the menubar
      // create instance of object for View menu
      basic = new CheckboxMenuItem("Basic",true);
      // add a listener to receive item events when the state of an item changes
      basic.addItemListener(this);
      scientific = new CheckboxMenuItem("Scientific");
      // add a listener to receive item events when the state of an item changes
      scientific.addItemListener(this);
      IMC = new CheckboxMenuItem("IMC");
      // add a listener to receive item events when the state of an item changes
      IMC.addItemListener(this);
      Conversor = new CheckboxMenuItem("Conversor");
      // add a listener to receive item events when the state of an item changes
      Conversor.addItemListener(this);
      Quad_Formula = new CheckboxMenuItem("Quad Formula");
      // add a listener to receive item events when the state of an item changes
      Quad_Formula.addItemListener(this);
      
      view = new Menu("View");
      font = new Menu ("Font");
      about = new Menu ("About");
      

      // create instance of object for font menu
      bold = new MenuItem("Arial Bold");
      bold.addActionListener(this);

      regular = new MenuItem("Arial Regular");
      regular.addActionListener(this);

      // add the items in the menu

      author = new MenuItem("Codigo por Geovanni Dzul y Saulo Bosquez");
      about.addActionListener(this);
      
      font.add(bold);
      font.add(regular);
      
      view.add(basic);
      view.add(scientific);
      view.add(IMC);
      view.add(Conversor);
      view.add(Quad_Formula);
      view.add(about);
      about.add(author);

      // add the menus in the menubar

      mb.add(view);
      mb.add(font);
      mb.add(about);
      
      // add menubar to the frame
      fr.setMenuBar(mb);

      // override the layout manager

      fr.setLayout(null);

      // set the initial numbers that is 1 to 9
      for (int row = 0; row < 3; ++row)
      {
        for (int col = 0; col < 3; ++col)
        {
          // this will set the key from 1 to 9
            key[count] = new Button(Integer.toString(count));
            key[count].addActionListener(this);
            // set the boundry for the keys
            key[count].setBounds(25*(col + 1), 25*(row + 3),25,BHEIGHT);
            key[count].setBackground(Color.white);
            // add to the frame
            fr.add(key[count++]);
        }
      }


      // Now create, addlistener and add to frame all other keys




      //0
      key[0] = new Button("0");
      key[0].addActionListener(this);
      key[0].setBounds(25,150,BWIDTH - 5,BHEIGHT);
      key[0].setBackground(Color.white);
      fr.add(key[0]);

      //decimal
      key[10] = new Button(".");
      key[10].addActionListener(this);
      key[10].setBounds(50,150,BWIDTH - 5,BHEIGHT);
      key[10].setBackground(Color.white);
      fr.add(key[10]);

      //equals to
      key[11] = new Button("=");
      key[11].addActionListener(this);
      key[11].setBounds(75,150,BWIDTH - 5,BHEIGHT);
      key[11].setBackground(Color.white);
      fr.add(key[11]);

      //multiply
      key[12] = new Button("*");
      key[12].addActionListener(this);
      key[12].setBounds(100,75,BWIDTH - 5,BHEIGHT);
      key[12].setBackground(Color.white);
      fr.add(key[12]);

      //divide
      key[13] = new Button("/");
      key[13].addActionListener(this);
      key[13].setBounds(100,100,BWIDTH - 5,BHEIGHT);
      key[13].setBackground(Color.white);
      fr.add(key[13]);

      //addition
      key[14] = new Button("+");
      key[14].addActionListener(this);
      key[14].setBounds(100,125,BWIDTH - 5,BHEIGHT);
      key[14].setBackground(Color.white);
      fr.add(key[14]);

      //subtract
      key[15] = new Button("-");
      key[15].addActionListener(this);
      key[15].setBounds(100,150,BWIDTH - 5,BHEIGHT);
      key[15].setBackground(Color.white);
      fr.add(key[15]);

      //reciprocal
      key[16] = new Button("1/x");
      key[16].addActionListener(this);
      key[16].setBounds(125,75,BWIDTH,BHEIGHT);
      key[16].setBackground(Color.white);
      fr.add(key[16]);

      //power
      key[17] = new Button("x^n");
      key[17].addActionListener(this);
      key[17].setBounds(125,100,BWIDTH,BHEIGHT);
      key[17].setBackground(Color.white);
      fr.add(key[17]);

      //change sign
      key[18] = new Button("+/-");
      key[18].addActionListener(this);
      key[18].setBounds(125,125,BWIDTH,BHEIGHT);
      key[18].setBackground(Color.white);
      fr.add(key[18]);

      //factorial
      key[19] = new Button("x!");
      key[19].addActionListener(this);
      key[19].setBounds(125,150,BWIDTH,BHEIGHT);
      key[19].setBackground(Color.white);
      fr.add(key[19]);

      // CA
      clearAll = new Button("CA");
      clearAll.addActionListener(this);
      clearAll.setBounds(18, 175, BWIDTH+20, BHEIGHT);
      clearAll.setBackground(Color.gray);
      fr.add(clearAll);

      // CE
      clearEntry = new Button("CE");
      clearEntry.addActionListener(this);
      clearEntry.setBounds(65, 175, BWIDTH+20, BHEIGHT);
      clearEntry.setBackground(Color.gray);
      fr.add(clearEntry);

      // round
      round = new Button("~");
      round.addActionListener(this);
      round.setBounds(112, 175, BWIDTH+15, BHEIGHT);
      round.setBackground(Color.gray);
      fr.add(round);

      // set display area

      display = new TextField("0");
      display.setBounds(10,40,150,20);
      display.setBackground(Color.white);

      // key for scientific calculator

      // Sine
      scientificKey[0] = new Button("Sin");
      scientificKey[0].addActionListener(this);
      scientificKey[0].setBounds(150, 72, 50, 28);
      scientificKey[0].setVisible(true);
      scientificKey[0].setBackground(Color.gray);
      fr.add(scientificKey[0]);

      // cosine
      scientificKey[1] = new Button("Cos");
      scientificKey[1].addActionListener(this);
      scientificKey[1].setBounds(150, 100, 50, 30);
      scientificKey[1].setBackground(Color.gray);
      scientificKey[1].setVisible(true);
      fr.add(scientificKey[1]);


      // Tan
      scientificKey[2] = new Button("Tan");
      scientificKey[2].addActionListener(this);
      scientificKey[2].setBounds(150, 130, 50, 30);
      scientificKey[2].setBackground(Color.gray);
      scientificKey[2].setVisible(true);
      fr.add(scientificKey[2]);

      // PI
      scientificKey[3] = new Button("Pi");
      scientificKey[3].addActionListener(this);
      scientificKey[3].setBounds(150, 160, 50, 30);
      scientificKey[3].setBackground(Color.gray);
      scientificKey[3].setVisible(true);
      fr.add(scientificKey[3]);

      // aSine
      scientificKey[4] = new Button("aSin");
      scientificKey[4].addActionListener(this);
      scientificKey[4].setBounds(195, 72, 60, 28);
      scientificKey[4].setBackground(Color.gray);
      scientificKey[4].setVisible(true);
      fr.add(scientificKey[4]);

      // aCos
      scientificKey[5] = new Button("aCos");
      scientificKey[5].addActionListener(this);
      scientificKey[5].setBounds(195, 100, 60, 30);
      scientificKey[5].setBackground(Color.gray);
      scientificKey[5].setVisible(true);
      fr.add(scientificKey[5]);


      // aTan
      scientificKey[6] = new Button("aTan");
      scientificKey[6].addActionListener(this);
      scientificKey[6].setBounds(195, 130, 60, 30);
      scientificKey[6].setBackground(Color.gray);
      scientificKey[6].setVisible(true);
      fr.add(scientificKey[6]);


      // E
      scientificKey[7] = new Button("E");
      scientificKey[7].addActionListener(this);
      scientificKey[7].setBounds(195, 160, 60, 30);
      scientificKey[7].setBackground(Color.gray);
      scientificKey[7].setVisible(true);
      fr.add(scientificKey[7]);

      // to degrees
      scientificKey[8] = new Button("to deg");
      scientificKey[8].addActionListener(this);
      scientificKey[8].setBounds(18, 200, 68, 30);
      scientificKey[8].setBackground(Color.gray);
      scientificKey[8].setVisible(true);
      fr.add(scientificKey[8]);

      // to radians
      scientificKey[9] = new Button("to rad");
      scientificKey[9].addActionListener(this);
      scientificKey[9].setBounds(90, 200, 68, 30);
      scientificKey[9].setBackground(Color.gray);
      scientificKey[9].setVisible(true);
      fr.add(scientificKey[9]);
      
      

      cbg = new CheckboxGroup();
      degrees = new Checkbox("Degrees", cbg, true);
      radians = new Checkbox("Radians", cbg, false);
      degrees.addItemListener(this);
      radians.addItemListener(this);

      degrees.setBounds(155, 20, 3 * BWIDTH, BHEIGHT);

      radians.setBounds(155, 45, 3 * BWIDTH, BHEIGHT);

      degrees.setVisible(false);
      radians.setVisible(false);
      fr.add(degrees);
      fr.add(radians);
      fr.add(display);
      fr.setVisible(true);
    } // end of makeCalculator


    public void actionPerformed(ActionEvent ae)
    {


      String buttonText = ae.getActionCommand();
      double displayNumber = Double.valueOf(display.getText()).doubleValue();

      // if the button pressed text is 0 to 9

      if((buttonText.charAt(0) >= '0') & (buttonText.charAt(0) <= '9'))
      {
        if(decimalPointPressed)
        {
           for (int i=1;i <=decimalPlaces; ++i)
           currentNumber *= 10;
           currentNumber +=(int)buttonText.charAt(0)- (int)'0';
           for (int i=1;i <=decimalPlaces; ++i)
           {
            currentNumber /=10;
           }
           ++decimalPlaces;
           display.setText(Double.toString(currentNumber));
        }
        else if (roundButtonPressed)
        {
          int decPlaces = (int)buttonText.charAt(0) - (int)'0';
          for (int i=0; i< decPlaces; ++i)
            displayNumber *=10;
            displayNumber = Math.round(displayNumber);

          for (int i = 0; i < decPlaces; ++i)
          {
            displayNumber /=10;

          }

          display.setText(Double.toString(displayNumber));
          roundButtonPressed = false;
        }
        else
        {
            currentNumber = currentNumber * 10 + (int)buttonText.charAt(0)-(int)'0';
            display.setText(Integer.toString((int)currentNumber));
        }
      }

      // if button pressed is addition
      if(buttonText == "+")
      {
        addButtonPressed = true;
        initialNumber = displayNumber;
        currentNumber = 0;
        decimalPointPressed = false;
      }

      // if button pressed is subtract

      if (buttonText == "-")
      {
        subtractButtonPressed = true;
        initialNumber = displayNumber;
        currentNumber = 0;
        decimalPointPressed = false;
      }

      // if button pressed is divide
      if (buttonText == "/")
      {
        divideButtonPressed = true;
        initialNumber = displayNumber;
        currentNumber = 0;
        decimalPointPressed = false;
      }

      // if button pressed is multiply
      if (buttonText == "*")
      {
        multiplyButtonPressed = true;
        initialNumber = displayNumber;
        currentNumber = 0;
        decimalPointPressed = false;
      }

      // if button pressed is reciprocal
      if (buttonText == "1/x")
      {
        // call reciprocal method
        display.setText(reciprocal(displayNumber));
        currentNumber = 0;
        decimalPointPressed = false;
      }

      // if button is pressed to change a sign
      if (buttonText == "+/-")
      {
        // call changesign method to change the sign
        display.setText(changeSign(displayNumber));
        currentNumber = 0;
        decimalPointPressed = false;
      }

      // factorial button
      if (buttonText == "x!")
      {
        display.setText(factorial(displayNumber));
        currentNumber = 0;
        decimalPointPressed = false;
      }
      // power button
      if (buttonText == "x^n")
      {
        powerButtonPressed = true;
        initialNumber = displayNumber;
        currentNumber = 0;
        decimalPointPressed = false;
      }

      // now for scientific buttons

      if (buttonText == "Sin")
      {
        if (degrees.getState())
        {
            display.setText(Double.toString(Math.sin(Math.PI * displayNumber/180)));
        }
        else
        {
            display.setText(Double.toString(Math.sin(displayNumber)));
            currentNumber = 0;
            decimalPointPressed = false;
        }
      }


      if (buttonText == "Cos")
      {
        if (degrees.getState())
        {
            display.setText(Double.toString(Math.cos(Math.PI * displayNumber/180)));
        }
        else
        {
            display.setText(Double.toString(Math.cos(displayNumber)));
            currentNumber = 0;
            decimalPointPressed = false;
        }
      }


      if (buttonText == "Tan")
      {
        if (degrees.getState())
        {
            display.setText(Double.toString(Math.tan(Math.PI * displayNumber/180)));
        }
        else
        {
            display.setText(Double.toString(Math.tan(displayNumber)));
            currentNumber = 0;
            decimalPointPressed = false;
        }
      }

      if (buttonText == "aSin")
      {
        if (degrees.getState())
        {
            display.setText(Double.toString(Math.asin(displayNumber)* 180/Math.PI ));
        }
        else
        {
            display.setText(Double.toString(Math.asin(displayNumber)));
            currentNumber = 0;
            decimalPointPressed = false;
        }
      }


      if (buttonText == "aCos")
      {
        if (degrees.getState())
        {
            display.setText(Double.toString(Math.acos(displayNumber)* 180/Math.PI ));
        }
        else
        {
            display.setText(Double.toString(Math.acos(displayNumber)));
            currentNumber = 0;
            decimalPointPressed = false;
        }
      }


      if (buttonText == "aTan")
      {
        if (degrees.getState())
        {
            display.setText(Double.toString(Math.atan(displayNumber)* 180/Math.PI ));
        }
        else
        {
            display.setText(Double.toString(Math.atan(displayNumber)));
            currentNumber = 0;
            decimalPointPressed = false;
        }
      }

      // this will convert the numbers displayed to degrees
      if (buttonText == "todeg")
      {
         display.setText(Double.toString(Math.toDegrees(displayNumber)));
      }
       // this will convert the numbers displayed to radians
      if (buttonText == "torad")
      {
         display.setText(Double.toString(Math.toRadians(displayNumber)));
      }

      if (buttonText == "Pi")
      {
       display.setText(Double.toString(Math.PI));
       currentNumber =0;
       decimalPointPressed = false;
      }

      if (buttonText == "Round")
      {
        roundButtonPressed = true;
      }

      // check if decimal point is pressed
     if (buttonText == ".")
     {
        String displayedNumber = display.getText();
        boolean decimalPointFound = false;
        int i;

        decimalPointPressed = true;
        // check for decimal point
        for (i =0; i < displayedNumber.length(); ++i)
        {
          if(displayedNumber.charAt(i) == '.')
          {
             decimalPointFound = true;
              continue;
          }
        }
        if (!decimalPointFound)
        {
            decimalPlaces = 1;
        }
     }

       if(buttonText == "CA")
       {
         // set all buttons to false
         resetAllButtons();
         display.setText("0");
         currentNumber = 0;
       }

     if (buttonText == "CE")
     {
        display.setText("0");
        currentNumber = 0;
        decimalPointPressed = false;
     }
     if (buttonText == "E")
     {
        display.setText(Double.toString(Math.E));
        currentNumber = 0;
        decimalPointPressed = false;
     }


     // the main action

     if (buttonText == "=")
     {
        currentNumber = 0;
        // if add button is pressed
        if(addButtonPressed)
        {
            display.setText(Double.toString(initialNumber + displayNumber));
        }
        // if subtract button is pressed
        if(subtractButtonPressed)
        {
            display.setText(Double.toString(initialNumber - displayNumber));
        }
        // if divide button is pressed
        if (divideButtonPressed)
        {
        // check if the divisor is zero
        if(displayNumber == 0) 
        {
            MessageBox mb = new MessageBox ( fr, "Error ", true, "Cannot divide by zero.");
            mb.show();
        }
        else
        {
            display.setText(Double.toString(initialNumber/displayNumber));
        }
        }

      // if multiply button is pressed
        if(multiplyButtonPressed)
        {
            display.setText(Double.toString(initialNumber * displayNumber));
        }
      // if power button is pressed
        if (powerButtonPressed)
        {
            display.setText(power(initialNumber, displayNumber));
        }

        // set all the buttons to false
        resetAllButtons();
      }

     if (buttonText == "Arial Regular")
     {
        for (int i =0; i < 10; ++i)
         key[i].setFont(new Font("Arial", Font.PLAIN, 12));
     }

     if (buttonText == "Arial Bold")
     {
        for (int i =0; i < 10; ++i)
            key[i].setFont(new Font("Arial", Font.BOLD, 12));
     }

     if (buttonText == "Author")
     {
            MessageBox mb = new MessageBox ( fr, "Calculator ver 1.0 beta ", true, "Author: Anon.");
            mb.show();
     }
   } // end of action events

    public void itemStateChanged(ItemEvent ie)
    {
        if (ie.getItem() == "Basic")
        {
            basic.setState(true);
            scientific.setState(false);
            fr.setTitle("Basic Calculator");
            fr.setSize(200,270);
            // check if the scientific keys are visible. if true hide them
            if (scientificKey[0].isVisible())
            {
                for (int i=0; i < 8; ++i)
                {
                    scientificKey[i].setVisible(false);
                    radians.setVisible(false);
                    degrees.setVisible(false);
                    scientificKey[8].setVisible(false);
                    scientificKey[9].setVisible(false);
                }
                for (int i=0; i < 20; ++i)
                {
                    key[i].setVisible(true);
                }
                clearAll.setVisible(true);
                clearEntry.setVisible(true);
                round.setVisible(true);
            }
        }

        if (ie.getItem() == "Scientific")
        {
            basic.setState(false);
            scientific.setState(true);
            fr.setTitle("Scientific Calculator");
            fr.setSize(270,270);
            // check if the scientific keys are visible. if true display them
            if (!scientificKey[0].isVisible())
            {
                for (int i=0; i < 10; ++i)
                {
                    scientificKey[i].setVisible(true);
                    radians.setVisible(true);
                    degrees.setVisible(true);
                }
            }
        }


        if (ie.getItem() == "IMC")
        {
            basic.setState(false);
            scientific.setState(false);
            fr.setTitle("IMC");
            if (scientificKey[0].isVisible())
            {
                for (int i=0; i < 8; ++i)
                {
                    scientificKey[i].setVisible(false);
                    radians.setVisible(false);
                    degrees.setVisible(false);
                    scientificKey[8].setVisible(false);
                    scientificKey[9].setVisible(false);
                }
                for (int i=0; i < 20; ++i)
                {
                    key[i].setVisible(false);
                }
                clearAll.setVisible(false);
                clearEntry.setVisible(false);
                round.setVisible(false);
            
                class BMI
                {
                    public void main (String[] args)
                    {
                    int WIDTH = 500;
                    int HEIGHT = 300;
                    JFrame frame = new JFrame("IMC");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    BMIGUI panel = new BMIGUI();
                    frame.getContentPane().add(panel);
                    frame.pack();
                    frame.setVisible(true);
                    frame.setSize(300, 200);
                    frame.setResizable(false);
                    }
                }
                class BMIGUI extends JPanel
                {
                    private JLabel heightLabel, weightLabel, BMILabel, resultLabel;
                    private JTextField height, weight;
                    private JButton calculate;

                    public BMIGUI()
                    {
                    setLayout(null);
                    heightLabel = new JLabel ("Altura metros: ");
                    heightLabel.setBounds(10,10,100,30);
                    weightLabel = new JLabel ("Peso KG: ");
                    weightLabel.setBounds(10,35,100,30);
                    BMILabel = new JLabel ("Tu IMC: ");
                    BMILabel.setBounds(10,55,100,30);
                    resultLabel = new JLabel ("Resultado: ");
                    resultLabel.setBounds(10,75,100,30);
                    height = new JTextField ();
                    height.setBounds(120,10,150,20);
                    weight = new JTextField ();
                    weight.setBounds(120,35,150,20);
                    calculate = new JButton ("Calcular");
                    calculate.setBounds(10, 100, 100, 30);
                    BMIListener Listener = new BMIListener();
                    calculate.addActionListener(Listener); 
      
                    // OK apply this to this JPanel
                    setPreferredSize (new Dimension(WIDTH, HEIGHT));
                    setBackground (Color.yellow);
     
                    // you add stuff to the Panel which BMIGUI is not to the JFframe
                    add(heightLabel);
                    add(height);
                    add(weightLabel);
                    add(weight);
                    add(calculate);
                    add(BMILabel);
                    add(resultLabel);
                    }
   
                    class BMIListener implements ActionListener
                    {
                    public void actionPerformed (ActionEvent event)
                    {
                        String heightText, weightText;
                        double heightVal, weightVal;
                        double bmi;

                        // heightText is a String just declared 2 lines above,
                        // it is null and the getText() method does not
                        // apply to String...you probably refer to your JTextField
         
                        String respon1 = height.getText();
                        double t = Double.parseDouble(respon1);
         
                        String respon2 = weight.getText();
                        double v = Double.parseDouble(respon2);
         
                        heightVal = Double.parseDouble(respon1);
                        weightVal = Double.parseDouble(respon2);
     

                        bmi = Math.round(weightVal / (heightVal * heightVal));

                        String str = String.valueOf(bmi);
                        resultLabel.setText(str);
                    }
                    }
                }
            }
        }
        
    } // end of itemState


    // this method will reset all the buttonPressed property to false
    public void resetAllButtons()
    {
        addButtonPressed = false;
        subtractButtonPressed = false;
        multiplyButtonPressed = false;
        divideButtonPressed = false;
        decimalPointPressed = false;
        powerButtonPressed = false;
        roundButtonPressed = false;
    }

    public String factorial(double num)
    {
        int theNum = (int)num;
        if (theNum < 1)
        {
            MessageBox mb = new MessageBox (fr, "Facorial Error", true,
                                "Cannot find the factorial of numbers less than 1.");
            mb.show();
            return ("0");
        }
        else
        {
            for (int i=(theNum -1); i > 1; --i)
                theNum *= i;
                return Integer.toString(theNum);
        }
    }


    public String reciprocal(double num)
    {
        if (num ==0)
        {
            MessageBox mb = new MessageBox(fr,"Reciprocal Error", true,
                            "Cannot find the reciprocal of 0");
            mb.show();
        }
        else
            num = 1/num;
            return Double.toString(num);
    }

    public String power (double base, double index) {
        return Double.toString(Math.pow(base, index));
    }

    public String changeSign(double num) {
        return Double.toString(-num);
    }

}


class MessageBox extends Dialog implements ActionListener {
        Button ok;
        MessageBox(Frame f, String title, boolean mode, String message) {
            super(f, title, mode);

            Panel centrePanel = new Panel();
            Label lbl = new Label(message);
            centrePanel.add(lbl);
            add(centrePanel, "Center");
            Panel southPanel = new Panel();
            ok = new Button ("OK");
            ok.addActionListener(this);
            southPanel.add(ok);
            add(southPanel, "South");
            pack();
            addWindowListener (new WindowAdapter(){
                public void windowClosing (WindowEvent we)
                {
                    System.exit(0);
                }
            });
     }
     public void actionPerformed(ActionEvent ae)
     {
            dispose();
     }
}