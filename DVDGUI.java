package dvdPackage;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;

public class DVDGUI implements DVDUserInterface {
	 
	 private DVDCollection dvdlist;
	 
	 public DVDGUI(DVDCollection dl)
	 {
		 dvdlist = dl;
	 }
	 // Process user selection
	 public void processCommands()
	 {
		 String[] commands = {"View/Modify DVD",
				    "Add DVD",
				 	"Remove DVD",
				 	"Get DVDs By Rating",
				 	"Get Total Running Time",
				 	"Exit and Save"};
		 
		 int choice;
		 
		 do {
			 choice = JOptionPane.showOptionDialog(null,
					 "Select from the following options", 
					 "DVD Collection", 
					 JOptionPane.YES_NO_CANCEL_OPTION, 
					 JOptionPane.QUESTION_MESSAGE, 
					 null, 
					 commands,
					 commands[commands.length - 1]);
		 
			 switch (choice) {
			 	case 0: doViewModify(); break;
			 	case 1: doAddOrModifyDVD(); break;
			 	case 2: doRemoveDVD(); break;
			 	case 3: doGetDVDsByRating(); break;
			 	case 4: doGetTotalRunningTime(); break;
			 	case 5: doSave(); break;
			 	default:
			 }
			 
		 } while (choice != commands.length-1);
		 System.exit(0);
	 }
	 
	 private void doAddOrModifyDVD() {
		 // Request the title
		 String title = JOptionPane.showInputDialog("Enter title");
		 if (title == null) {
			 return;
		 }
		 title = title.toUpperCase();
		
		 // Request the rating
		 String rating = JOptionPane.showInputDialog("Enter rating for " + title);
		 if (rating == null) {
		 	 return;
		 }
		 rating = rating.toUpperCase();
		
		 // Request the running time
		 String time = JOptionPane.showInputDialog("Enter running time for " + title);
		 if (time == null) {
		 }
         // Add or modify the DVD (assuming the rating and time are valid
         dvdlist.addOrModifyDVD(title, rating, time);
         // Display to user that the title was added
         String message = "Success";
         JOptionPane.showMessageDialog(null, "ADDED", message, JOptionPane.INFORMATION_MESSAGE);
         // Display current collection to the console for debugging
         // System.out.println("Adding/Modifying: " + title + "," + rating + "," + time);
         // System.out.println(dvdlist.toString());
	 }
	
	 private void doRemoveDVD() {
		 // Request the title
		 String title = JOptionPane.showInputDialog("Enter title");
		 if (title == null) {
			 return;
		 }
		 title = title.toUpperCase();
		
         // Remove the matching DVD if found
         dvdlist.removeDVD(title);
                
         // Display current collection to the console for debugging
         //System.out.println("Removing: " + title);
         //System.out.println(dvdlist.toString());
                
	 }
	
	 private void doGetDVDsByRating() {
		 // Request the rating
		 String rating = JOptionPane.showInputDialog("Enter rating");
		 if (rating == null) {
		 	 return;
		 }
		 rating = rating.toUpperCase();
		 // Find DVDs with same rating
         String results = dvdlist.getDVDsByRating(rating);
         // System.out.println("DVDs with rating " + rating);
         // System.out.println(results);
         
         // Display DVDs
         String message = rating + " RATINGS";
         JOptionPane.showMessageDialog(null, results, 
         message, JOptionPane.INFORMATION_MESSAGE);
	 }

	 private void doGetTotalRunningTime() {
		 // Get total running time
		 int total = dvdlist.getTotalRunningTime();
         // System.out.println("Total Running Time of DVDs: ");
         // System.out.println(total);
		
		 // Display total running time
         String message = "TOTAL RUNNING TIME";
         JOptionPane.showMessageDialog(null, total + " minutes", 
         message, JOptionPane.INFORMATION_MESSAGE);        
     }

	 private void doSave() {
		 dvdlist.save();
		 // Display to user that save is complete
         String message = "SUCCESS";
         JOptionPane.showMessageDialog(null, "FILE SAVED", 
         message, JOptionPane.INFORMATION_MESSAGE);
	 }
	
	 private void doViewModify() {
		 // Display the list of DVDs 
		 // Allow user to select a DVD for information or
		 // Choose to edit a current DVD
		 int index = -1;
		 int numDVDs = dvdlist.getNumDVDs();
		 DVD[] dvds = dvdlist.getArray();
		 // Set the values options as DVDs
		 Object[] possibleValues = new Object[numDVDs + 1];
		 for(int i = 0; i < numDVDs; i++) {
			 String temp = dvds[i].getTitle();
			 possibleValues[i] = temp;
		 }
		 possibleValues[numDVDs] = "Edit a DVD";
		 // Get the selected option from user from drop-down window
		 Object selectedValue = JOptionPane.showInputDialog(null,
		 "Choose a DVD to view or edit a DVD to edit", "DVDs",
		 JOptionPane.DEFAULT_OPTION, null,
		 possibleValues, possibleValues[numDVDs]);
		 // If user selects quit, go back to main menue
		 if(selectedValue == null) {
			 return;
		 }
		 // If user chooses to edit a DVD...
		 if(selectedValue.equals(possibleValues[numDVDs])) {
			 // System.out.println("WORKING");
			 int indexToChange = 0;
			 // Get the title that the user would like to change
			 String titleToChange = JOptionPane.showInputDialog("Enter title of DVD you would like to change");
			 if (titleToChange == null) {
				 return;
			 }
			 titleToChange = titleToChange.toUpperCase();
			 // Find title index in list
			 for(int i = 0; i < numDVDs; i++) {
				 if(titleToChange.equals(dvds[i].getTitle())) {
					 indexToChange = i;
					 break;
				 }
			 }
			 // Set new title		
			 String title = JOptionPane.showInputDialog("Enter new title");
			 if (title == null) {
				 return;
			 }
			 title = title.toUpperCase();
			
			 // Set the new rating
			 String rating = JOptionPane.showInputDialog("Enter new rating for " + title);
			 if (rating == null) {
				 return;
			 }
			 rating = rating.toUpperCase();
			
			 // Set the new running time
			 String time = JOptionPane.showInputDialog("Enter new unning time for " + title);
			 if (time == null) {
			 }
			 // Update/modify the DVD
	         dvdlist.modifyDVD(title, rating, time, indexToChange);
	         String message = "Success";
	         JOptionPane.showMessageDialog(null, "DVD UPDATED", message, JOptionPane.INFORMATION_MESSAGE);			
			 // Escape to main menu
	         return;
		 }
		 // Display selection
		 for(int i = 0; i < numDVDs; i++) {
			 if(dvds[i].getTitle().equals(selectedValue.toString())) {
				 index = i;
				 break;
			 }
		 }
		 // Set some images for example
		 dvds[0].setGenre("SCARY");
		 dvds[2].setGenre("KIDS");
		 // Set image by DVD genre
		 String genre = dvds[index].getGenre();
         ImageIcon icon = new ImageIcon("/Users/traviscassell/eclipse-workspace_java/Assignment4/images/Generic.png");
         // If genre is not generic, change image path
         if(genre.equals("COMEDY")) {
        	 icon = new ImageIcon("/Users/traviscassell/eclipse-workspace_java/Assignment4/images/Comedy.png");
         }
         else if(genre.equals("KIDS")) {
        	 icon = new ImageIcon("/Users/traviscassell/eclipse-workspace_java/Assignment4/images/Kids.png");
         }
         else if(genre.equals("ROMANCE")) {
        	 icon = new ImageIcon("/Users/traviscassell/eclipse-workspace_java/Assignment4/images/Romance.png");
         }
         else if(genre.equals("SCARY")) {
        	 icon = new ImageIcon("/Users/traviscassell/eclipse-workspace_java/Assignment4/images/Scary.png");
         }
         // Escape to main menu if quit chosen
         if(index == -1) {
        	 return;
         }
         // Display DVD info and image
         JOptionPane.showMessageDialog(
                 null,
                 new JLabel("Rating: " + dvds[index].getRating() + ", Running Time: " + dvds[index].getRunningTime(), icon, JLabel.LEFT),
                 dvds[index].getTitle(), JOptionPane.PLAIN_MESSAGE);
		
	}
		
}