/*
 *  EDIT:THIS IS NOT THE MAIN VERSION OF OUR PROJECT ANYMORE, REFER TO login4.java.  FEEL FREE TO 
 *       TEST THIS OUT THOUGH, SEARCH FUNCTION HAS SOME TROUBLE
 *
 * PlayStation.java 
 * 
 * PlayStation version 2 
 * 
 * This program will generate  a table of PS4 games
 * that the user currently want. 
 * 
 * @group:		LulzSec
 * 
 * @author:		Quynh-Tram Nguyen, Spencer Bishop, Andy Omori
 * 
 * @course:		ICS 491
 * 
 * @date:		August 9, 2015
 *  
 * 
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import javax.swing.table.DefaultTableModel;

public class PlayStation implements ActionListener {

    //Modifiers
    private int size;
    private Vector tableHeaders, gameData;
    private ArrayList<Object> gameDevelopers, gameGenres, gamePublisher;
    private JTable tableContents;
    private JScrollPane gameTable;
    private JPanel playStation;
    private JLabel databaseTitle, searchLabel, nameLabel, genreLabel, developerLabel, publisherLabel;
    private JComboBox<Object>  developerBox, genreBox, publisherBox;
    private JTextField searchBox;
    private JButton searchButton; 
    private DefaultTableModel tableLayout;
    private SpringLayout layout;
	
    public PlayStation() throws BiffException {
        layout = new SpringLayout();
	size = 0;
		
	//The PlayStation 4 Gui
	playStation = new JPanel();
	playStation.setPreferredSize(new Dimension(900,480));
	playStation.setVisible(true);
		
	//The Database Title
	databaseTitle = new JLabel("PlayStation 4 Games Database (North America Only)");
	databaseTitle.setFont(new Font(databaseTitle.getName(), Font.TRUETYPE_FONT, 19));
	playStation.add(databaseTitle);
		
	//The table that will show the output
	fillTableFromExcel("PlayStation.xls");
	tableLayout = new DefaultTableModel(gameData, tableHeaders);
	tableContents = new JTable(tableLayout);
	tableContents.setAutoCreateRowSorter(true);
	gameTable = new JScrollPane(tableContents);
	gameTable.setPreferredSize(new Dimension(800,200));
	gameTable.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
	gameTable.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	playStation.add(gameTable);
	
	//Fills the instance variable of the array of genres and developers with unique names of developers and genres.
	getDeveloperPublisherGenre();
	
	//Searching label
	searchLabel = new JLabel("Search for Specific Games");
	searchLabel.setFont(new Font(searchLabel.getName(), Font.TRUETYPE_FONT, 16));
	playStation.add(searchLabel);
		
	//Game title search label
	nameLabel = new JLabel("Find by Title");
	playStation.add(nameLabel);
		
	//Game search box
	searchBox = new JTextField();
	searchBox.setPreferredSize(new Dimension(200,25));
	playStation.add(searchBox);
		
	//Genre Label
	genreLabel = new JLabel("Genre(s):");
	playStation.add(genreLabel);
		
	//GenreBox
	genreBox = new JComboBox<Object>(gameGenres.toArray());
	playStation.add(genreBox);

	//Developer Label
	developerLabel = new JLabel("Developer(s):");
	playStation.add(developerLabel);
		
	//Developer Box
	developerBox = new JComboBox<Object>(gameDevelopers.toArray());
	playStation.add(developerBox);
		
	//Publisher Label
	publisherLabel = new JLabel("Publisher(s):");
	playStation.add(publisherLabel);
				
	//Publisher Box
	publisherBox = new JComboBox<Object>(gamePublisher.toArray());
	playStation.add(publisherBox);
		
	//Search Button
	searchButton = new JButton("Search");
	searchButton.addActionListener(this);
	playStation.add(searchButton);
		
	/*
	 * Constraints for the Gui
	 */
	//Database Title Label Constraints
	layout.putConstraint(SpringLayout.NORTH, databaseTitle, 25, SpringLayout.NORTH, playStation);
	layout.putConstraint(SpringLayout.WEST, databaseTitle, 80, SpringLayout.WEST, playStation);
		
	//Game Table Constraints
	layout.putConstraint(SpringLayout.NORTH, gameTable, 60, SpringLayout.NORTH, playStation);
	layout.putConstraint(SpringLayout.WEST, gameTable, 50, SpringLayout.WEST, playStation);
	
	//Search For Label Constraint
	layout.putConstraint(SpringLayout.NORTH, searchLabel, 265, SpringLayout.NORTH, playStation);
	layout.putConstraint(SpringLayout.WEST, searchLabel, 80, SpringLayout.WEST, playStation);
	
	//Search box constraints
	layout.putConstraint(SpringLayout.NORTH, searchBox, 315, SpringLayout.NORTH, playStation);
	layout.putConstraint(SpringLayout.WEST, searchBox, 110, SpringLayout.WEST, playStation);
		
	//Name Label
	layout.putConstraint(SpringLayout.NORTH, nameLabel, 295, SpringLayout.NORTH, playStation);
	layout.putConstraint(SpringLayout.WEST, nameLabel, 110, SpringLayout.WEST, playStation);
		
	//Genre Label
	layout.putConstraint(SpringLayout.NORTH, genreLabel, 350, SpringLayout.NORTH, playStation);
	layout.putConstraint(SpringLayout.WEST, genreLabel, 110, SpringLayout.WEST, playStation);
	
	//Genre Box
	layout.putConstraint(SpringLayout.NORTH, genreBox, 370, SpringLayout.NORTH, playStation);
	layout.putConstraint(SpringLayout.WEST, genreBox, 110, SpringLayout.WEST, playStation);
	
	//Developer Label
	layout.putConstraint(SpringLayout.NORTH, developerLabel, 295, SpringLayout.NORTH, playStation);
	layout.putConstraint(SpringLayout.WEST, developerLabel, 450, SpringLayout.WEST, playStation);
				
	//Developer Box
	layout.putConstraint(SpringLayout.NORTH, developerBox, 315, SpringLayout.NORTH, playStation);
	layout.putConstraint(SpringLayout.WEST, developerBox, 525, SpringLayout.WEST, playStation);
		
	//Publisher Label
	layout.putConstraint(SpringLayout.NORTH, publisherLabel, 350, SpringLayout.NORTH, playStation);
	layout.putConstraint(SpringLayout.WEST, publisherLabel, 450, SpringLayout.WEST, playStation);
		
	//Publisher Box
	layout.putConstraint(SpringLayout.NORTH, publisherBox, 370, SpringLayout.NORTH, playStation);
	layout.putConstraint(SpringLayout.WEST, publisherBox, 525, SpringLayout.WEST, playStation);
	
	//search button constraints
	layout.putConstraint(SpringLayout.NORTH, searchButton, 420, SpringLayout.NORTH, playStation);
	layout.putConstraint(SpringLayout.WEST, searchButton, 410, SpringLayout.WEST, playStation);
		
	playStation.setLayout(layout);
   }
   
   //Search button action
   @Override
   public void actionPerformed(ActionEvent ae) {
	Object source = ae.getSource();
	boolean[] fieldSelected;
	if (source == searchButton) {
	    if ( checkSelected(searchBox.getText(),  genreBox.getSelectedItem(), developerBox.getSelectedItem(), publisherBox.getSelectedItem() ) ) {
	    	//Something was selected
	    	JFrame searchFrame = new JFrame("Results");
	    	searchFrame.setPreferredSize(new Dimension(700,400));
	    	searchFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	      	searchFrame.pack();
	      	searchFrame.setVisible(true);
	            
	      	//finds what was selected
	      	fieldSelected = fieldCheck(searchBox.getText(),genreBox.getSelectedItem(),developerBox.getSelectedItem(),publisherBox.getSelectedItem());
	      	//does the fields that were filled
	      	for (int i = 0; i < fieldSelected.length; i++) {
	      	     if(fieldSelected[i] && i == 0) {
	      		//Name field
	      	     }
	      	     if(fieldSelected[i] && i == 1) {
	      		//genre
	      	     }
		     if(fieldSelected[i] && i == 2) {
			//developer
		     }
		     if(fieldSelected[i] && i == 3){
			//publisher
		     }
		}
	            
	    } else {
		    //Something was not selected
		    String errorMessage = "No Search Parameters were selected!\n\n" 
		    + "Please enter a search parameter to find a list of games.";
	            JOptionPane.showMessageDialog(null, errorMessage);
	      }
	}
   }
   
   //Assigns values in the selection boxes
   public boolean checkSelected(String name, Object genre, Object developer, Object publisher) {
		
	if( name.equals("") && genre.equals("Choose Genre") && 
	   developer.equals("Choose Developer") && publisher.equals("Choose Publisher") ) {
           return false;
	}
	return true;
   }
   
   //Checks the field in the selection boxes
   public boolean[] fieldCheck(String name, Object genre, Object developer, Object publisher) {
		
	boolean[] fieldChecked = new boolean[4];
	for (int i = 1; i < 4; i++) {
	     fieldChecked[i] = false;
	}
	//name check
	if ( !(name.equals("")) ) {
	   fieldChecked[0] = true;
	}
	//Genre check
	if (!(genre.equals("Choose Genre"))) {
	    fieldChecked[1] = true;
	}
	//Developer check
	if (!(developer.equals("Choose Developer"))) {
	    fieldChecked[2] = true;
	}
	//Publisher check
	if (!(publisher.equals("Choose Publisher"))) {
	   fieldChecked[3] = true;
	}
	return fieldChecked;
   }
	
   //Excel sheet database method
   public void fillTableFromExcel(String file) throws BiffException {
	tableHeaders = new Vector();
	gameData = new Vector();
	
	//xls method	
	try {
	     File filename = new File(file);
	     Workbook workbook = Workbook.getWorkbook(filename);
	     Sheet sheet = workbook.getSheet(0);
	     tableHeaders.clear();
             for (int i = 0; i < sheet.getColumns(); i++) {
                  Cell cellOne = sheet.getCell(i,0);
                  tableHeaders.add(cellOne.getContents());
             }
             gameData.clear();
             for (int i = 1; i < sheet.getRows(); i++) {
                  Vector temp = new Vector();
        	  for (int j = 0; j < sheet.getColumns(); j++) {
                       Cell cell = sheet.getCell(j, i);
                       temp.add(cell.getContents());
                  }
                  temp.add("\n");
		  gameData.add(temp);
		  size++;
             }	 
	} catch (IOException e) {
				 System.out.println("Error: " + e);
          }
   }

   //Method that grabs categories from xls file to file choice boxes		
   public void getDeveloperPublisherGenre() {
	int rows = tableContents.getRowCount();
	gameDevelopers = new ArrayList<Object>();
	gameGenres = new ArrayList<Object>();
	gamePublisher = new ArrayList<Object>();
		
	//Grabs genre from row in database
	gameGenres.add("Choose Genre");
	for (int i = 0; i < rows; i++) {
	     if (tableContents.getModel().getValueAt(i, 1).equals("")) {
		 continue;
	     }
	     if (!(gameGenres.contains(tableContents.getModel().getValueAt(i, 1)))) {
		 gameGenres.add(tableContents.getModel().getValueAt(i, 1));
	     }
	}
		
	//Grabs developer from row in database
	gameDevelopers.add("Choose Developer");
	for (int i = 0; i < rows; i++) {
	     if(!(gameDevelopers.contains(tableContents.getModel().getValueAt(i, 2)))) {
		gameDevelopers.add(tableContents.getModel().getValueAt(i, 2));
	     }
	}
	    
	//Grabs publisher from row in database
	gamePublisher.add("Choose Publisher");
	for(int i = 0; i < rows; i++) {
	    if(!(gamePublisher.contains(tableContents.getModel().getValueAt(i, 3)))) {
	       gamePublisher.add(tableContents.getModel().getValueAt(i, 3));
	    }
	}	
   }
	
   public static void main(String[] args) throws BiffException {
	JFrame window = new JFrame("PS4 Game Database");
	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	window.setResizable(false);
	PlayStation gui = new PlayStation();
	window.setContentPane(gui.playStation);
	window.pack();
	window.setVisible(true);
   }
}
