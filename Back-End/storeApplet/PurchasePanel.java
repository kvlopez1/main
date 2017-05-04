// Karen Lopez

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*; //To use borders.

import java.util.*;
import java.text.NumberFormat; //To format total.

public class PurchasePanel extends JPanel
 {
	//Instance variables are created.
   private Vector<Computer> compList;
   private JCheckBox[] checkBoxArray;
   private JPanel checkBoxPanel;
   private JLabel currentTotal;
   private JTextArea totalPurchase;
   private double total;

   //Step #1: Within the constructor, initialize all instance variables and set up the layout properly
   public PurchasePanel(Vector<Computer> compList)
    {
	  this.compList = compList;
	  	 currentTotal = new JLabel("Current Total Purchase");
	  	 total = 0.0;
	  	 totalPurchase = new JTextArea("$0.00");
	  	 totalPurchase.setBorder(BorderFactory.createEtchedBorder()); //Borders are created for the textarea.

	  	 //creates a new panel to display the checkbox array.
	  	 checkBoxPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); //Arranges the components to the left of the panel.
	  	 checkBoxPanel.setBorder(BorderFactory.createEtchedBorder());

		 
		 //creates a panel to organize the components of the applet.
		 	JPanel sp0 = new JPanel(new BorderLayout());
		 	sp0.add(currentTotal, BorderLayout.NORTH);
		 	sp0.add(totalPurchase, BorderLayout.CENTER);
			
			this.setLayout(new BorderLayout());
			//adds the panel to the applet
		 	add(checkBoxPanel, BorderLayout.CENTER);
		 	add(sp0, BorderLayout.EAST);
		 	
	  // organize components for purchase panel
		 	

    }
   
   //This method updates the vector and created a checkbox for every item.
   public void updateCompList(Vector<Computer> newCompList) 
   {
	   checkBoxPanel.removeAll(); //Removes everything from the panel
	   
	   //Step #3: Register the JCheckBox object with the listener.
	   CheckBoxListener listener = new CheckBoxListener();
	   checkBoxArray = new JCheckBox[newCompList.size()];

	   for (int i = 0; i < newCompList.size(); i++)
	   {
		   	checkBoxArray[i] = new JCheckBox(newCompList.get(i).toString());
	        checkBoxArray[i].addItemListener(listener);
	        checkBoxPanel.add(checkBoxArray[i]); //Adds entire array. 
		}
	   
   }
   
 //Step #2:  CheckBoxListener class represents the listener for check boxes.
 private class CheckBoxListener implements ItemListener
  {
	   public void itemStateChanged(ItemEvent event)
	    {
		   NumberFormat fmt = NumberFormat.getCurrencyInstance();
		   for (int i = 0; i < checkBoxArray.length; i++)
		   {
			   if (checkBoxArray[i].isSelected())
			   {
					total += compList.get(i).getPrice();
			   }
		   }
		   //update the label
		   totalPurchase.setText(fmt.format(total));

		   //re-set the instance variable to 0, otherwise, total will keep increasing
		   total = 0.00;
		   
		   // compute the total purchase amount when a check box is
			// checked or unchecked.
		}
  }

}
