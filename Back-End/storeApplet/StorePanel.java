// Karen Lopez
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*; //To use borders.

import java.util.*;

public class StorePanel extends JPanel
 {
   //Instance Variables are created.
   private Vector<Computer> compList;
   private PurchasePanel purchasePanel;
   private JButton button1;
   private JLabel computerPrompt;
   private JLabel brandName;
   private JLabel priceLabel;
   private JLabel memory;
   private JLabel cpuType;
   private JLabel cpuSpeed;
   private JTextArea computerNames;
   private JTextField brandNameTextField;
   private JTextField priceTextField;
   private JTextField memoryTextField;
   private JTextField cpuTypeTextField;
   private JTextField cpuSpeedTextField;
   private boolean error;

   //Step #1: Within the constructor, initialize all instance variable and set up the layout properly
 public StorePanel(Vector<Computer> compList, PurchasePanel pPanel)
  {
    this.compList = compList;
    this.purchasePanel = pPanel;
    computerPrompt = new JLabel("", SwingConstants.CENTER); 
    brandName = new JLabel("Brand Name");
    priceLabel = new JLabel("Price");
    memory = new JLabel("Memory");
    cpuType = new JLabel("CPU Type");
    cpuSpeed = new JLabel("CPU Speed");
    computerNames = new JTextArea("No Computer");
    computerNames.setBorder(BorderFactory.createEtchedBorder()); //Borders are created to the textFields.
    brandNameTextField = new JTextField();
    brandNameTextField.setBorder(BorderFactory.createEtchedBorder());
    priceTextField = new JTextField();
    priceTextField.setBorder(BorderFactory.createEtchedBorder());
    memoryTextField = new JTextField();
    memoryTextField.setBorder(BorderFactory.createEtchedBorder());
    cpuTypeTextField = new JTextField();
    cpuTypeTextField.setBorder(BorderFactory.createEtchedBorder());
    cpuSpeedTextField = new JTextField();
    cpuSpeedTextField.setBorder(BorderFactory.createEtchedBorder());
    computerNames.setEditable(false);
    
    // orgranize components here
    // here is an example

   //Step #3: Register the button with the listerner
    button1 = new JButton("Store");
    button1.addActionListener(new ButtonListener());
    
    //Left Panel with all the texfields and labels.
    JPanel cp0 = new JPanel(new GridLayout(5,2,3,3));
    cp0.add(brandName);
    cp0.add(brandNameTextField);
    cp0.add(priceLabel);
    cp0.add(priceTextField);
    cp0.add(memory);
    cp0.add(memoryTextField);
    cp0.add(cpuType);
    cp0.add(cpuTypeTextField);
    cp0.add(cpuSpeed);
    cp0.add(cpuSpeedTextField);
    
    //Finalizing left panel. Adds the button and label. 
    JPanel cp1 = new JPanel(new BorderLayout());
    cp1.add(computerPrompt, BorderLayout.NORTH);
    cp1.add(cp0, BorderLayout.CENTER);
    cp1.add(button1, BorderLayout.SOUTH);
    
    //set up layout.
    setLayout(new GridLayout(1,2,5,0));
    add(cp1);
    add(computerNames);
  }


  private class ButtonListener implements ActionListener
   {
	public void actionPerformed(ActionEvent event)
	 {
			 error = false; 
			 //If any of the textFields are empty it shows an error.
			 if(brandNameTextField.getText().equals("") || priceTextField.getText().equals("") || memoryTextField.getText().equals("") || cpuTypeTextField.getText().equals("") || cpuSpeedTextField.getText().equals(""))
			 {
				 error = true;
				 computerPrompt.setForeground(Color.red);
				 computerPrompt.setText("Please enter all fields");
				 brandNameTextField.setText("");
				 priceTextField.setText("");
				 memoryTextField.setText("");
				 cpuTypeTextField.setText("");
				 cpuSpeedTextField.setText("");
			 }
			 else
			 {
				 try
				 {
				//Gets info from textFields.
				 String brandNameText = brandNameTextField.getText();
				 double priceInt = Double.parseDouble(priceTextField.getText());
				 int memoryInt = Integer.parseInt(memoryTextField.getText());
				 String cpuTypeText = cpuTypeTextField.getText();
				 int cpuSpeedInt = Integer.parseInt(cpuSpeedTextField.getText());
				 
				 //Creating object for compList.
				 Computer newComp = new Computer();
				 newComp.setBrandName(brandNameText);
				 newComp.setPrice(priceInt);
				 newComp.setMemory(memoryInt);
				 newComp.setCPU(cpuTypeText,cpuSpeedInt);
				 
				 //Adds the object newComp with all the information to the list.
				 compList.add(newComp);
				 
				 //Updating the label.
				 computerPrompt.setForeground(Color.red);
				 computerPrompt.setText("Computer added.");
					 
				 //Displays the list on the textArea.
				 computerNames.append(newComp.toString());
				 brandNameTextField.setText("");
				 priceTextField.setText("");
				 memoryTextField.setText("");
				 cpuTypeTextField.setText("");
				 cpuSpeedTextField.setText("");
				 
				 purchasePanel.updateCompList(compList);	
				 }
				 //if the price, memory or speed don't have numbers it displays an error.
				 catch(NumberFormatException e)
				 {
					 computerPrompt.setForeground(Color.red);
					 computerPrompt.setText("Enter a number for price, memory, or speed");
					 brandNameTextField.setText("");
					 priceTextField.setText("");
					 memoryTextField.setText("");
					 cpuTypeTextField.setText("");
					 cpuSpeedTextField.setText("");
				 }
			 }
		 // if there is no error, add a computer to computer list
		 // otherwise, show an error message

     }
  }
}