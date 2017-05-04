import javax.swing.*;
import java.util.*;

public class storeAppletMain extends JApplet
 {

   private int APPLET_WIDTH = 650, APPLET_HEIGHT = 350;

   private JTabbedPane tPane;
   private StorePanel storePanel;
   private PurchasePanel purchasePanel;
   private Vector<Computer> computerList;

   //The method init initializes the Applet with a Pane with two tabs
   public void init()
    {
	 //list of computers to be used in every panel
	 computerList = new Vector<Computer>();

	 //customer purchase panel uses the list of computers
	 purchasePanel = new PurchasePanel(computerList);

	 //store inventory panel uses the list of computers and also
	 //established a connection with purchasePanel
     storePanel = new StorePanel(computerList, purchasePanel);


     //create a tabbed pane with two tabs
     tPane = new JTabbedPane();
     tPane.addTab("Store Inventory", storePanel);
     tPane.addTab("Customer Purchase", purchasePanel);

     getContentPane().add(tPane);
     setSize (APPLET_WIDTH, APPLET_HEIGHT); //set Applet size
    }
}
