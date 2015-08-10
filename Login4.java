/*
 * Login4.java 
 * 
 * Login version 4 -Final version
 * 
 * This program will generate a login window and display a table of PlayStation 4 games
 * that the user own. 
 * 
 * @group:		LulzSec
 * 
 * @author:		Spencer Bishop, Quynh-Tram Nguyen, Andy Omori
 * 
 * @course:		ICS 491
 * 
 * @date:		August 9, 2015
 *  
 */

import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;


//This class display a user login window to access the PS4 game database that the user own. 
@SuppressWarnings("serial")
public class Login4 extends JFrame 
{
        //GUI components for login window
        private JLabel 			usernameLabel = new JLabel("Username");
        private JLabel 			passwordLabel = new JLabel("Password");
        private JButton 		login = new JButton("Login");
        private JPanel 			panel = new JPanel();
        private JTextField 		user = new JTextField(15);
	private JPasswordField 	pass = new JPasswordField(15);
	
	
	//User variables for login window
	String dbUsername = null;   //User name in database
	String dbPassword = null;   //Password in database
	String puname = null;      //Entered user name
	String ppaswd = null;      //Entered password
	
	//Variables for PlayStation 4 database window
	private int size;
	private Vector tableHeaders, gameData;
	private ArrayList<Object> gameDevelopers, gameGenres, gamePublisher;
	private JLabel databaseTitle;
	private JTable tableContents;
	private JScrollPane gameTable;
	private JPanel playStation;
	private DefaultTableModel tableLayout;
	private SpringLayout layout;

	/*
	 * This method displays the GUI for the login window.
	 */
	Login4()
	{
		//Create window structure
		super("Login");
		setSize(400,300);
		setLocation(500,280);
		panel.setLayout (null); 
		
		//Create login button layout
		login.setBounds(160,120,80,20);
		panel.add(login);

                //Create user name button and textbox layout
		usernameLabel.setBounds(60,50,80,20);
		user.setBounds(160,50,150,20);
		panel.add(user);
		panel.add(usernameLabel);

		//Create password button and textbox layoutlayout
		passwordLabel.setBounds(60,85,80,20);
		pass.setBounds(160,85,150,20);
		panel.add(pass);
		panel.add(passwordLabel);

		getContentPane().add(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		//When the login button is click, it will call the checkLogin method
		//to verify the user login information that user have entered
		login.addActionListener(new ActionListener() 
		{	 
	        public void actionPerformed(ActionEvent ae) 
	        {
	        	try 
	        	{
				checkLogin();  //Begin login verification
	        	} 
	        	catch (BiffException e) 
	        	{
				e.printStackTrace(); 
			}
	        }
	    });
	}
	
	/*
	 * This method will verify whether or not the login information is correction.
	 */
	
	public void checkLogin() throws BiffException
	{
		//Stores user login input to authenticate with login information stored in login database
		puname = user.getText().trim();
		ppaswd = new String(pass.getPassword()).trim();
		
		try {	
			//Connect to login database that is store locally
			Connection newDBConnection = new DBConnection3().connectLoginDatabase();
			
			//For testing: Display user login input
			//System.err.print("User entered username >> '"+puname +"'; password: '"+ppaswd+"'\n");
		
			//Process SQL Statement to verify login information
			String sql = "select usrname, psswd from user where usrname=? and psswd=?";
			PreparedStatement preparedStmt = newDBConnection.prepareStatement(sql);
			preparedStmt.setString(1, puname);
			preparedStmt.setString(2, ppaswd);
		
			//Execute SQL query
			ResultSet rs = preparedStmt.executeQuery();
			
			
			/* EXTRACT DATA FROM DATABASE */
			if(rs.next()) 
			{
				//If login information is correct, login window will disappear,
				//a welcome window will appear, and shows the PlayStation 4 database window.
				JOptionPane.showMessageDialog(null,"Welcome "+ puname);  //for testing
				//user.setText("");
				//pass.setText("");
				dispose(); //login window disappears
				showData(); //Call method to display PlayStation 4 database window and its data from Excel file.
			}	
				
			else
			{
				//If login information is incorrect, an error message window will appear
				//to notify the user that the username or password that was entered is incorrect.
				JOptionPane.showMessageDialog(null,"Access Denied\nInvalid Username or Password");
				user.setText(""); //Clear username fieldbox so user can re-enter username.
				pass.setText(""); //Clear password fieldbox so user can re-enter password.	
			}
			
			//Close disconnection to database
			newDBConnection.close();
		}
		catch(SQLException e)
		{
			System.out.print(e.getMessage() + "\n");  //Display compiling error message on console if SQL syntax is in correct 
		}
	}
	
	/*
	 * This method will display a window with a table of PlayStation 4 games owned by user,
	 * which has been  imported from an Excel file.
	 */
	public void showData() throws BiffException 
	{
		
		//Create a new window and its layout for the PlayStation 4 game database
        	JFrame window = new JFrame("PS4 Game Database");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(900, 500);
		window.setResizable(false);
		window.setVisible(true);
		layout = new SpringLayout();
		//size = 0;
		
		//Create a PlayStation 4 GUI
		playStation = new JPanel();
		playStation.setPreferredSize(new Dimension(1000,480));
		playStation.setVisible(true);
		window.add(playStation);
		
		//Create a Database Title for PlayStation 4 database window
		databaseTitle = new JLabel("PlayStation 4 Games Database (North America Only)");
		databaseTitle.setFont(new Font(databaseTitle.getName(), Font.TRUETYPE_FONT, 19));
		layout.putConstraint(SpringLayout.NORTH, databaseTitle, 25, SpringLayout.NORTH, playStation);
		layout.putConstraint(SpringLayout.WEST, databaseTitle, 80, SpringLayout.WEST, playStation);
		playStation.add(databaseTitle);
		window.add(playStation);
		
		//Create a table that will show the PlayStion 4 database which is impported from Excel file, "PS4.xls"
		fillTableFromExcel("PS4.xls");
		tableLayout = new DefaultTableModel(gameData, tableHeaders);
		tableContents = new JTable(tableLayout);
		tableContents.setAutoCreateRowSorter(true);
		
		//Create layout of the PlayStation 4 table
		gameTable = new JScrollPane(tableContents);
		gameTable.setPreferredSize(new Dimension(800,200));
		gameTable.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		gameTable.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		layout.putConstraint(SpringLayout.NORTH, gameTable, 60, SpringLayout.NORTH, playStation);
		layout.putConstraint(SpringLayout.WEST, gameTable, 50, SpringLayout.WEST, playStation);
		playStation.add(gameTable);
		
		/*
		 * FOR FUTURE IMPLEMENATION - Implement search feature
		 * These features are to be used as part of future improvement on our program but it is optional if you want
		 * to implement it.
		/*
		//Create "Search for Specific Games" label
		searchLabel = new JLabel("Search for Specific Games");
		searchLabel.setFont(new Font(searchLabel.getName(), Font.TRUETYPE_FONT, 16));
		layout.putConstraint(SpringLayout.NORTH, searchLabel, 265, SpringLayout.NORTH, playStation);
		layout.putConstraint(SpringLayout.WEST, searchLabel, 80, SpringLayout.WEST, playStation);
		playStation.add(searchLabel);
		
		//Create game title search label
		nameLabel = new JLabel("Find by Title");
		layout.putConstraint(SpringLayout.NORTH, nameLabel, 295, SpringLayout.NORTH, playStation);
		layout.putConstraint(SpringLayout.WEST, nameLabel, 110, SpringLayout.WEST, playStation);
		playStation.add(nameLabel);
		
		//Create game search box
		searchBox = new JTextField();
		searchBox.setPreferredSize(new Dimension(200,25));
		layout.putConstraint(SpringLayout.NORTH, searchBox, 315, SpringLayout.NORTH, playStation);
		layout.putConstraint(SpringLayout.WEST, searchBox, 110, SpringLayout.WEST, playStation);
		playStation.add(searchBox);
		
		//Create genre label
		genreLabel = new JLabel("Genre(s):");
		layout.putConstraint(SpringLayout.NORTH, genreLabel, 350, SpringLayout.NORTH, playStation);
		layout.putConstraint(SpringLayout.WEST, genreLabel, 110, SpringLayout.WEST, playStation);
		playStation.add(genreLabel);
		
		//Create genre selection box
		genreBox = new JComboBox<String>();
		layout.putConstraint(SpringLayout.NORTH, genreBox, 370, SpringLayout.NORTH, playStation);
		layout.putConstraint(SpringLayout.WEST, genreBox, 110, SpringLayout.WEST, playStation);
		playStation.add(genreBox);
		
		//Create developer Label
		developerLabel = new JLabel("Developer(s):");
		layout.putConstraint(SpringLayout.NORTH, developerLabel, 295, SpringLayout.NORTH, playStation);
		layout.putConstraint(SpringLayout.WEST, developerLabel, 450, SpringLayout.WEST, playStation);
		playStation.add(developerLabel);
				
		//Create developer selection box
		developerBox = new JComboBox<String>();
		layout.putConstraint(SpringLayout.NORTH, developerBox, 315, SpringLayout.NORTH, playStation);
		layout.putConstraint(SpringLayout.WEST, developerBox, 525, SpringLayout.WEST, playStation);
		playStation.add(developerBox);
		
		//Create publisher label
		publisherLabel = new JLabel("Publisher(s):");
		layout.putConstraint(SpringLayout.NORTH, publisherLabel, 350, SpringLayout.NORTH, playStation);
		layout.putConstraint(SpringLayout.WEST, publisherLabel, 450, SpringLayout.WEST, playStation);
		playStation.add(publisherLabel);
						
		//Create publisher selection box
		publisherBox = new JComboBox<String>();
		layout.putConstraint(SpringLayout.NORTH, publisherBox, 370, SpringLayout.NORTH, playStation);
		layout.putConstraint(SpringLayout.WEST, publisherBox, 525, SpringLayout.WEST, playStation);
		playStation.add(publisherBox);
		*/
		playStation.setLayout(layout); //Update layout of PlayStation 4 database window
	}
	
	//This method will fill a table with data from an imported Excel file. 
	public void fillTableFromExcel(String file) throws BiffException
	{
		//Create column names and rows in table  
		tableHeaders = new Vector();
		gameData = new Vector();
       
		try 
		{
			//Create a worksheet in table to display data from Excel file
			 File filename = new File(file);
			 Workbook workbook = Workbook.getWorkbook(filename);
			 Sheet sheet = workbook.getSheet(0);
			 
			 tableHeaders.clear();
             
            		//Get the column names in the Excel file and store as table header
            		for(int i = 0; i < sheet.getColumns(); i++)
            		{
                 		Cell cellOne = sheet.getCell(i,0);
                 	tableHeaders.add(cellOne.getContents());
            		}
            
            		gameData.clear();
        
        		//Get the data (by rows) from Excel file and display them in the table (by rows)
            		for(int i = 1; i < sheet.getRows(); i++)
            		{
                		Vector temp = new Vector();
                
	        		for(int j = 0; j < sheet.getColumns(); j++)
	        		{
                			Cell cell = sheet.getCell(j, i);
                			temp.add(cell.getContents());
                		}
                		temp.add("\n");
				gameData.add(temp);
				size++;
            		}	 
		}   
		catch(IOException e) //Checking for reading infile error
		{
			System.out.println("Error: " + e); //Display on consolse any error for reading in Excel file
		}
	}
	
	//Start program and display a user login window
	public static void main(String[] args) 
	{
		new Login4();
	}
}
