package dvdPackage;

import java.io.*;
import java.util.Scanner;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

public class DVDCollection {

	private int numdvds;
	
	private DVD[] dvdArray;
	
	private String sourceName;
	
	private boolean modified;
		
	public DVDCollection() {
		numdvds = 0;
		dvdArray = new DVD[7];
	}
	
	public String toString() {
		
		String alldvds = "";
		alldvds += "numdvds = " + numdvds + "\n";
		alldvds += "dvdArray.length = " + dvdArray.length + "\n";
		for(int i = 0; i < numdvds; i++) {
			alldvds += "dvdArray[" + i + "] = " + dvdArray[i].getTitle() +
					"/" + dvdArray[i].getRating() + "/" + dvdArray[i].getRating() + "\n";
		}
		return alldvds;
		
	}

	public void addOrModifyDVD(String title, String rating, String runningTime) {

		if(numdvds < dvdArray.length) {
			DVD temp = new DVD(title, rating, Integer.parseInt(runningTime));
			this.dvdArray[numdvds] = temp;
			numdvds++;
			modified = true;
		}
		else {
			int newSize = (dvdArray.length * 2);
			DVD[] tempDVDArray = new DVD[newSize];
			for(int i = 0; i < numdvds; i++) {
				tempDVDArray[i] = dvdArray[i];
			}
			DVD temp = new DVD(title, rating, Integer.parseInt(runningTime));
			tempDVDArray[numdvds] = temp;
			numdvds++;
			modified = true;
			dvdArray = tempDVDArray;
		}
		
	}
	
	public void removeDVD(String title) {
		
		int index = -1;
		for(int i = 0; i < numdvds; i++) {
			if(dvdArray[i].getTitle().equals(title)) {
				// System.out.println("IN REMOVE");
				index = i;
				numdvds -= 1;
				modified = true;
			}	
		}
		if(index > -1) {
			for(int j = index; j < numdvds; j++) {
				DVD temp = dvdArray[j+1];
				dvdArray[j] = temp;
			}
		}
		if(index == -1) {
	        String failed = "ERROR";
	        JOptionPane.showMessageDialog(null, "TITLE NOT FOUND", 
	        		failed, JOptionPane.INFORMATION_MESSAGE);
		
		}
		else {
	        String complete = "SUCCESS";
	        JOptionPane.showMessageDialog(null, "TITLE REMOVED", 
	        		complete, JOptionPane.INFORMATION_MESSAGE);
		}

	}
	
	public String getDVDsByRating(String rating) {
		
		String[] titles = new String[numdvds];
		String allTitles = "";
		int numTitles = 0;
		boolean found = false;
		for(int i = 0; i < numdvds; i++) {
			if(dvdArray[i].getRating().equals(rating.toUpperCase())) {
				titles[numTitles] = dvdArray[i].getTitle();
				numTitles += 1;
				found = true;
			}
		}
		for(int i = 0; i < numTitles - 1; i++) {
			allTitles += titles[i] + ", ";
		}
		
		if(found == false) {
			return "No titles found with rating " + rating;
		}
		
		allTitles += titles[numTitles - 1];
        
		return allTitles;

	}

	public int getTotalRunningTime() {
		
		int total = 0;
		for(int i = 0; i < numdvds; i++) {
			total += dvdArray[i].getRunningTime();
		}
		return total;
	}

	public void loadData(String filename) {

		sourceName = filename;
		File file = new File(sourceName);
		if (!file.exists()) {
			// System.out.println("Error! ");
		}
		
		try {
			Scanner inputFile = new Scanner(file);		
			
			while (inputFile.hasNextLine()) {
				// System.out.println("in the file");
				String line = inputFile.nextLine();
				String[] holder = line.split(",", 3);
				String title = holder[0];
				String rating = holder[1];
				String time = holder[2];
				
				addOrModifyDVD(title, rating, time);
			}
            String message = "SUCCESS";
            JOptionPane.showMessageDialog(null, "FILE LOAD COMPLETE", message, JOptionPane.INFORMATION_MESSAGE);
			
			inputFile.close();
		} catch (Exception e) {
			
            String message = "ERROR";
            JOptionPane.showMessageDialog(null, "FILE LOAD UNSUCCESSFULL, NEW FILE WILL BE CREATED", 
            		message, JOptionPane.ERROR_MESSAGE);
			sourceName = filename;
		}

	}
	
	public void save() {
		
		if(modified == true) {
			try {
				
				PrintWriter outputFile = new PrintWriter(sourceName);
				
				for (int i = 0; i < numdvds; i++) {
					String temp = dvdArray[i].getTitle() + "," + dvdArray[i].getRating() +
							"," + dvdArray[i].getRunningTime() + "\n";
					
					outputFile.print(temp);
				}
				
				outputFile.close();
				modified = false;
				
			} catch (Exception e) {
	            String message = "ERROR";
	            JOptionPane.showMessageDialog(null, "SAVE UNSUCCESSFULL", 
	            		message, JOptionPane.ERROR_MESSAGE);			}
		}
	}
	
	public int getNumDVDs() {
		return this.numdvds;
		
	}
	
	public DVD[] getArray() {
		return this.dvdArray;
		
	}
	// Added method so that I can update a DVD instead of adding to the current list
	public void modifyDVD(String title, String rating, String runningTime, int index) {
		DVD temp = new DVD(title, rating, Integer.parseInt(runningTime));
		this.dvdArray[index] = temp;
		modified = true;
		this.dvdArray[index].setGenre("GENERIC");
		
	}
		
}
	
