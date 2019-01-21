/**
 * InventoryGUI.java
 * @author BookStore Group
 * November 6, 2017
 * The main GUI for inventory
 */ 

//imports
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.NumberFormatException;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
/**
 * InventoryGUI
 * main JFrame
 */
class InventoryGUI extends JFrame{
  //variables for getting list indexes
  private int selectedTeacher = -1;
  private int selectedTeacherRemove;
  private int selectedPeriodRemove;
  private int teacherUser;
  private int userSelectClass;
  //variables for main page and teacherzone
  private JFrame mainFrame;
  private JFrame editFrame;  
  private JLabel instructionsLabel;
  private JButton teacherZone;
  private JButton instructions;
  private JButton backToMainMenuButton;
  private JButton backToMainMenuButton2;
  private JButton editTeacherButton;
  private JButton selectTeacherButton;
  private JButton backToTeachersPeriodButton;
  private JPanel instructionsButtonPanel;
  private JPanel instructionsPagePanel;
  private JPanel teacherZonePanel;
  private JPanel teacherEditPanel;
  private JPanel teacherPeriodPanel;
  private JPanel mainPanel;
  private JPanel mainPanelLeft;
  private JPanel mainPanelRight;
  private ImageIcon logo;
  private JLabel logoLabel;
  //add the variables for lists
  private JList teacherList;
  private JList periodList;
  private JScrollPane teacherListScroll;
  private JScrollPane periodScroll;
  private DefaultListModel teacherListModel;
  private DefaultListModel periodModel;  
  private JScrollPane removeTeachersListScroll;
  private DefaultListModel removeTeachersListModel;
  private JScrollPane teacherPeriodsListScroll;
  private DefaultListModel teacherPeriodsListModel;
  private JList teacherPeriodsList;
  private JList logList;
  private JScrollPane logListScroll;
  private JList userPeriods;
  private JScrollPane userPeriodsScroll;
  private DefaultListModel userPeriodsModel;
  private DefaultListModel logListModel;
  
  //variables for edit teacher frame
  private JFrame addTeachersFrame;
  private JPanel addTeachersPanel;
  private JLabel teachersNameLabel;
  private JTextField teachersNameField;
  private JLabel teachersClassLabel;
  private JTextField teachersClassField;
  private JButton addTeachersButtonAdd;
  private JLabel enterFieldReminder;
  private JLabel dupeTeacher;
  private JLabel periodLabel;
  private JLabel courseCodeLabel;
  private JTextField periodField;
  private JTextField courseCodeField;
  private JFrame removeTeachersFrame;
  private JPanel removeTeachersPanel;
  private JList removeTeachersList;
  private JButton removeTeachersButtonRemove;
  private JButton addClassButton;  
  private JFrame editTeachersFrame;
  private JPanel editTeachersPanel;
  private JList editTeachersList;
  private JPanel editTeachersSubPanel;
  private JLabel editTeachersName;
  private JLabel editTeachersClasses;
  private JButton addPeriodsButton1;
  private JButton removePeriodsButton;
  private JButton addTeachersButtonEdit;
  private JButton removeTeachersButtonEdit;
  private JButton backToTeachersEdit;
  private JButton goToTeacherTabs;
  private JScrollPane editTeachersListScroll;
  private DefaultListModel editTeachersListModel;
  private JFrame tabbedFrame;
  private JTabbedPane tabbedPaneTeachers;
  private JFrame addPeriodFrame;
  
  //General Inventory VARIABLES
  private GeneralInventory bookInv;
  private JPanel generalInventory;
  private JList bookList;
  private JLabel numberOfBooks;
  private JLabel inStock;
  private JLabel numBooksCheckedOut;
  private JButton editBooks;
  private ListSelectionModel listListener;
  private JScrollPane bookListScroll;
  private DefaultListModel bookListModel;
  private JFrame periodFrame;
  private JTable generalInventoryTable;
  private JFrame generalInventoryEditFrame;
  private JPanel generalInventoryEditPanel;
  private JButton removeBook;
  private String selectedBookset;
  private JButton addBook;
  private JFrame addBookFrame;
  private JPanel addBookPanel;
  private JLabel bookNameLabel;
  private JTextField bookNameField;
  private JButton confirmBook;
  private JLabel bookIDLabel;
  private JTextField bookIDField;
  private JLabel bookRoomLabel;
  private JTextField bookRoomField;
  private JPanel listPanel;
  private JPanel setInfoPanel;
  private JPanel functionPanel;
  private JPanel invButtonPanel;
  private JPanel tablePanel;
  private JPanel addBookLabelPanel;
  private JPanel addBookFieldPanel;
  private JButton viewClass;
  private JButton signOutBook;
  private JFrame signOutFrame;
  private JPanel signOutPanel;
  private JPanel signOutInfoPanel;
  private JLabel signOutLabel;
  private JTextField signOutField;
  private JButton signOutConfirm;
  private JTextField signOutNumField;
  private JLabel signOutNumLabel;
  private JTable[] periodInfoTables;
  private JButton backToGeneralInventory;
  
  //variables for student panel
  private JPanel student;
  private JList studentList;
  private JLabel bookBorrowed;
  private JLabel bookId;
  private JLabel overdue;
  private JButton editStudents;
  private JButton backToTeacherFrame;
  
  //variables for tabbed pane
  private JPanel admin;
  private JPanel periodTab;  
  private JLabel log;
  
  //array lists
  private ArrayList<String> teacherNames;
  private ArrayList<Teacher> teachers;
  //variables for screen dimensions
  private Dimension screenSize;
  private int width;
  private int height;
  
  InventoryGUI(){
    //set the name of frame
    super ("BookStore");
    
    //get screensize
    screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    width = (int)screenSize.getWidth();
    height = (int)screenSize.getHeight();  
    
    //set size
    this.setSize(width*2/3,height*2/3);    
    //set close operation
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    
    //Declaring the book inventory
    bookInv = new GeneralInventory();
    try{
      bookInv.loadInv();
    } catch(IOException e){
      System.out.println("Could not load inventory");
    }
    
    //set the image for logo to add to panel
    logo = new ImageIcon("BookStoreLogo.png");
    logoLabel= new JLabel("",logo,JLabel.CENTER);
    
    //declare and set size for each frame
    editFrame  = new JFrame();        
    editFrame.setSize(width*2/3,height*2/3);    
    removeTeachersFrame= new JFrame();
    removeTeachersFrame.setSize(width*2/3/2,height*2/3);    
    addTeachersFrame= new JFrame();
    addTeachersFrame.setSize(width/3,height/6);    
    editTeachersFrame = new JFrame();
    editTeachersFrame.setSize(width/4,height/4);  
    generalInventoryEditFrame = new JFrame();
    generalInventoryEditFrame.setSize(width*2/3,height*2/3);  
    periodFrame = new JFrame();
    periodFrame.setSize(width/3,height/5);  
    addBookFrame = new JFrame();
    addBookFrame.setSize(width/3,height/5);  
    signOutFrame = new JFrame();
    signOutFrame.setSize(width/3,height/5); 
    
    //jtabbedpane
    tabbedPaneTeachers = new JTabbedPane();
    
    //initialize jpanels, and set colour and layout of the panels
    mainPanelLeft = new JPanel(new BorderLayout());
    mainPanelLeft.setBackground(new java.awt.Color(9, 193, 107));
    mainPanelRight = new JPanel(new GridLayout(3,1));   
    mainPanelRight.setBackground(new java.awt.Color(9, 193, 107));
    mainPanel = new JPanel(new GridLayout(1,2));
    instructionsButtonPanel = new JPanel(new BorderLayout());    
    instructionsButtonPanel.setBackground(new java.awt.Color(9, 193, 107));
    instructionsPagePanel= new JPanel();   
    teacherZonePanel=new JPanel(new BorderLayout());    
    teacherEditPanel = new JPanel();
    teacherPeriodPanel = new JPanel();
    removeTeachersPanel = new JPanel(new BorderLayout());
    addTeachersPanel = new JPanel(new BorderLayout());
    editTeachersPanel=new JPanel(new GridLayout(1,1));
    editTeachersSubPanel = new JPanel();
    generalInventory = new JPanel();
    admin= new JPanel();
    periodTab = new JPanel();
    generalInventoryEditPanel = new JPanel(new BorderLayout());
    addBookPanel = new JPanel(new BorderLayout());
    listPanel = new JPanel(new GridLayout(1,1));
    setInfoPanel = new JPanel(new GridLayout(1,3));
    functionPanel = new JPanel(new GridLayout(3, 1));
    invButtonPanel = new JPanel(new GridLayout(1,2));
    tablePanel = new JPanel(new GridLayout(1,1));
    tablePanel.setBackground(new java.awt.Color(9, 193, 107));
    addBookLabelPanel = new JPanel(new GridLayout(3,1));
    addBookFieldPanel = new JPanel(new GridLayout(3,1));
    signOutPanel = new JPanel(new BorderLayout());
    signOutInfoPanel = new JPanel(new GridLayout(2,2));
    JPanel dummyPanel;
    JPanel teacherZoneSubPanel = new JPanel(new BorderLayout());  
    JPanel teacherListButtons = new JPanel(new GridLayout(1,3));
    
    //set the model for the jtable
    periodModel = new DefaultListModel();    
    
    //setting up teacherList and the model for it and the scroll pane
    //set the list model
    teacherListModel = new DefaultListModel();
    //set the jlist
    teacherList= new JList <String>(teacherListModel); 
    //add mouse listener
    teacherList.addMouseListener(new teacherMouseListener());
    //add scroll pane
    teacherListScroll = new JScrollPane(teacherList);
    //set the size of scroll pane
    teacherListScroll.setPreferredSize(new Dimension(350, 250));
    //set the font of list
    teacherList.setFont(new Font("Arial", Font.PLAIN, 18));
    //same comments as teacherList
    removeTeachersListModel = new DefaultListModel();
    removeTeachersList = new JList<String> (removeTeachersListModel);
    removeTeachersList.setFont(new Font("Arial", Font.PLAIN, 18));
    removeTeachersListScroll = new JScrollPane(removeTeachersList);
    removeTeachersListScroll.setPreferredSize(new Dimension(200, 200));
    //same comments as teacherList
    editTeachersListModel = new DefaultListModel();
    editTeachersList = new JList<String> (editTeachersListModel);
    editTeachersList.addListSelectionListener(new editTeacherListSelectionListener());
    editTeachersListScroll = new JScrollPane(editTeachersList);
    editTeachersListScroll.setPreferredSize(new Dimension(200, 200));
    editTeachersList.setFont(new Font("Arial", Font.PLAIN, 18));
    //same comments as teacherList
    teacherPeriodsListModel = new DefaultListModel();
    teacherPeriodsList = new JList<String> (teacherPeriodsListModel);
    teacherPeriodsList.setFont(new Font("Arial", Font.PLAIN, 20));
    teacherPeriodsListScroll = new JScrollPane(teacherPeriodsList);  
    teacherPeriodsListScroll.setPreferredSize(new Dimension(50, 50));
    //same comments as teacherList
    bookListModel = new DefaultListModel();
    bookList = new JList<String> (bookListModel);
    bookList.addMouseListener(new bookMouseListener());    
    bookList.setFont(new Font("Arial", Font.PLAIN, 20));
    bookList.setBorder(new EmptyBorder(10,10, 10, 10));
    bookList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    bookListScroll = new JScrollPane(bookList);
    bookListScroll.setPreferredSize(new Dimension(200, 200));
    //get the selection model of booklist
    listListener = bookList.getSelectionModel();
    listListener.addListSelectionListener(new SharedListSelectionHandler());
    //same comments as teacherList
    userPeriodsModel = new DefaultListModel<Integer>();
    userPeriods = new JList<String>(userPeriodsModel);
    userPeriodsScroll = new JScrollPane(userPeriods);
    userPeriodsScroll.setPreferredSize(new Dimension(200, 200));
    userPeriods.setFont(new Font("Arial", Font.PLAIN, 18));
    //same comments as teacherList
    logListModel = new DefaultListModel();    
    logList = new JList<String> (logListModel);
    logListScroll = new JScrollPane(logList);   
    logListScroll.setPreferredSize(new Dimension(200, 200));
    //declare arraylists
    teacherNames = new ArrayList<String>();
    teachers= new ArrayList<Teacher>(); 
    
    
    //Adding booksets to the list
    for (int i = 0; i < bookInv.numBookSets(); i++){
      bookListModel.addElement(bookInv.getBookSet(i).getName());  //Adding the name of each book list
    }
    
    //declaring jlabels
    instructionsLabel = new JLabel("Instructions", JLabel.CENTER);     
    teachersNameLabel = new JLabel("Teachers Name:   ");
    teachersNameLabel.setFont(new Font("Arial", Font.BOLD, 16));
    teachersClassLabel = new JLabel("Add Class:");    
    editTeachersName = new JLabel("Selected Teacher: No Teacher Selected");
    editTeachersClasses = new JLabel("Classes:");
    numberOfBooks= new JLabel("Number of Books");
    numberOfBooks.setHorizontalAlignment(JLabel.CENTER);
    numberOfBooks.setFont(new Font("Arial", Font.BOLD, 16));
    inStock = new JLabel("In Stock:");
    //set the alignment and font for instock
    inStock.setHorizontalAlignment(JLabel.CENTER);
    inStock.setFont(new Font("Arial", Font.BOLD, 16));
    numBooksCheckedOut=new JLabel("# of Books Checked Out");
    //same comment as inStock
    numBooksCheckedOut.setHorizontalAlignment(JLabel.CENTER);
    numBooksCheckedOut.setFont(new Font("Arial", Font.BOLD, 16));
    log = new JLabel();
    enterFieldReminder= new JLabel("Please Enter a Name!");
    dupeTeacher = new JLabel ("Please enter a new teacher name!");
    periodLabel = new JLabel("Period:");
    bookNameLabel = new JLabel("New Book's Name:    ");
    //setting font for bookNameLabel
    bookNameLabel.setFont(new Font("Arial", Font.BOLD, 15));
    bookIDLabel = new JLabel("New Book's ID:      ");
    //same comment as bookNameLabel
    bookIDLabel.setFont(new Font("Arial", Font.BOLD, 15));
    bookRoomLabel = new JLabel("New Book's Room #:   ");
    //same comment as bookNameLabel
    bookRoomLabel.setFont(new Font("Arial", Font.BOLD, 15));
    signOutLabel = new JLabel("Student Name: ");
    //same comment as bookNameLabel
    signOutLabel.setFont(new Font("Arial", Font.BOLD, 15));
    signOutNumLabel = new JLabel("Period Number");
    //same comment as bookNameLabel
    signOutNumLabel.setFont(new Font("Arial", Font.BOLD, 15));
    
    //declaring jtextfields
    teachersNameField= new JTextField(20);
    teachersClassField = new JTextField(20);
    periodField = new JTextField(15);
    bookNameField = new JTextField(20);
    //setting font for bookNameField
    bookNameField.setFont(new Font("Arial", Font.PLAIN, 20));
    bookIDField = new JTextField(20);
    //same comment as bookNameField
    bookIDField.setFont(new Font("Arial", Font.PLAIN, 20));
    bookRoomField = new JTextField(20);
    //same comment as bookNameField
    bookRoomField.setFont(new Font("Arial", Font.PLAIN, 20));
    signOutField = new JTextField(20);
    //same comment as bookNameField
    signOutField.setFont(new Font("Arial", Font.PLAIN, 20));
    signOutNumField = new JTextField(20);
    //same comment as bookNameField
    signOutNumField.setFont(new Font("Arial", Font.PLAIN, 20));
    
    //declaring buttons
    teacherZone = new JButton("Enter BookStore");
    //set color of button to white
    teacherZone.setBackground(Color.white);
    teacherZone.setFont(new Font("Arial", Font.PLAIN, 15));
    instructions = new JButton("Instructions"); 
    instructions.setFont(new Font("Arial", Font.PLAIN, 15));
    //set color of button to white
    instructions.setBackground(Color.white);
    backToMainMenuButton = new JButton("Back to Main Menu");  
    backToMainMenuButton2 = new JButton("Back to Main Menu");
    //set font of button
    backToMainMenuButton2.setFont(new Font("Arial", Font.PLAIN, 15));
    //set color of button to white
    backToMainMenuButton2.setBackground(Color.white);
    editTeacherButton = new JButton("Edit Teachers");
    //set color of button to white
    editTeacherButton.setBackground(Color.white);
    //set font of button
    editTeacherButton.setFont(new Font("Arial", Font.PLAIN, 15));
    selectTeacherButton = new JButton("Select");
    //set color of button to white
    selectTeacherButton.setBackground(Color.white);
    backToTeachersPeriodButton = new JButton("Back to Teachers");
    //set color of button to white
    backToTeachersPeriodButton.setBackground(Color.white);
    removeTeachersButtonRemove = new JButton("Remove");
    //set color of button to white
    removeTeachersButtonRemove.setBackground(Color.white);
    removeTeachersButtonRemove.setFont(new Font("Arial", Font.PLAIN, 15));
    addClassButton = new JButton("Add Class");
    //set color of button to white
    addClassButton.setBackground(Color.white);
    addTeachersButtonAdd = new JButton("Add Teacher");
    //set color of button to white
    addTeachersButtonAdd.setBackground(Color.white);
    //set font of button
    addTeachersButtonAdd.setFont(new Font("Arial", Font.PLAIN, 15));
    addTeachersButtonEdit = new JButton("Add Teacher");
    //set color of button to white
    addTeachersButtonEdit.setBackground(Color.white);
    //set font of button
    addTeachersButtonEdit.setFont(new Font("Arial", Font.PLAIN, 15));
    addPeriodsButton1= new JButton("Add Period");
    //set color of button to white
    addPeriodsButton1.setBackground(Color.white);
    //set font of button
    addPeriodsButton1.setFont(new Font("Arial", Font.PLAIN, 15));
    removePeriodsButton=new JButton("Remove Period");
    //set color of button to white
    removePeriodsButton.setBackground(Color.white);
    //set font of button
    removePeriodsButton.setFont(new Font("Arial", Font.PLAIN, 15));
    removeTeachersButtonEdit=new JButton("Remove Teacher");
    //set color of button to white
    removeTeachersButtonEdit.setBackground(Color.white);
    //set font of button
    removeTeachersButtonEdit.setFont(new Font("Arial", Font.PLAIN, 15));
    backToTeachersEdit = new JButton("Back To Teachers");
    //set color of button to white
    backToTeachersEdit.setBackground(Color.white);
    //set font of button
    backToTeachersEdit.setFont(new Font("Arial", Font.PLAIN, 15));
    editBooks = new JButton("View Bookset");
    //set font of button
    editBooks.setFont(new Font("Arial", Font.PLAIN, 15));
    //set color of button to white
    editBooks.setBackground(Color.white);
    backToTeacherFrame = new JButton("Back To Teachers");
    //set color of button to white
    backToTeacherFrame.setBackground(Color.white);
    //set font of button
    backToTeacherFrame.setFont(new Font("Arial", Font.PLAIN, 15));
    goToTeacherTabs= new JButton("Select Teacher");
    //set color of button to white
    goToTeacherTabs.setBackground(Color.white);
    //set font of button
    goToTeacherTabs.setFont(new Font("Arial", Font.PLAIN, 15));
    removeBook = new JButton("Remove Selected Book(s)");
    //set color of button to white
    removeBook.setBackground(Color.white);
    //set font of button
    removeBook.setFont(new Font("Arial", Font.PLAIN, 15));
    addBook = new JButton("Add New Book");
    //set font of button
    addBook.setFont(new Font("Arial", Font.PLAIN, 15));
    //set color of button to white
    addBook.setBackground(Color.white);
    confirmBook = new JButton("Add New Book");
    //set font of button
    confirmBook.setFont(new Font("Arial", Font.PLAIN, 15));
    confirmBook.setBackground(Color.white);
    viewClass = new JButton("View Class");
    //set font of button
    viewClass.setFont(new Font("Arial", Font.PLAIN, 15));
    //set color of button to white
    viewClass.setBackground(Color.white);
    signOutBook = new JButton("Sign Out Book");
    //set font of button
    signOutBook.setFont(new Font("Arial", Font.PLAIN, 15));
    //set color of button to white
    signOutBook.setBackground(Color.white);
    signOutConfirm = new JButton("Sign Out Book");
    //set font of button
    signOutConfirm.setFont(new Font("Arial", Font.PLAIN, 15));
    //set color of button to white
    signOutConfirm.setBackground(Color.WHITE);
    //set color of button to white
    signOutBook.setBackground(Color.white);
    backToGeneralInventory= new JButton("Back to General Inventory");
    //set font of button
    backToGeneralInventory.setFont(new Font("Arial", Font.PLAIN, 15));
    backToGeneralInventory.setBackground(Color.white);
    
    //add action listeners to buttons
    teacherZone.addActionListener(new teacherZoneButtonListener());    
    instructions.addActionListener(new instructionsButtonListener());   
    backToMainMenuButton.addActionListener(new backToMainMenuButtonListener());   
    backToMainMenuButton2.addActionListener(new backToMainMenuButton2Listener()); 
    editTeacherButton.addActionListener(new editTeacherListener());    
    selectTeacherButton.addActionListener(new selectTeacherListener());
    backToTeachersPeriodButton.addActionListener(new backToTeachersPeriodListener());
    removeTeachersButtonRemove.addActionListener(new removeTeachersRemoveListener());
    addTeachersButtonAdd.addActionListener(new addTeachersAddListener());
    addTeachersButtonEdit.addActionListener(new addTeacherEditListener());
    addPeriodsButton1.addActionListener(new addPeriodsListener());
    removePeriodsButton.addActionListener(new removePeriodsListener());
    removeTeachersButtonEdit.addActionListener(new removeTeachersEditListener());
    backToTeachersEdit.addActionListener(new backToTeachersEditListener());
    backToTeacherFrame.addActionListener(new backToTeacherFrameListener());
    goToTeacherTabs.addActionListener(new goToTeacherTabsListener());
    editBooks.addActionListener(new editBooksListener());
    removeBook.addActionListener(new removeBookListener());
    addBook.addActionListener(new addBookListener());
    confirmBook.addActionListener(new confirmBookListener());
    signOutBook.addActionListener(new signOutBookListener());
    signOutConfirm.addActionListener(new signOutConfirmListener());
    viewClass.addActionListener (new ViewClassListener());
    backToGeneralInventory.addActionListener(new backToGeneralInventoryListener());
    
    //Loading the period information
    try {
      loadPeriodInfo();
    }catch (IOException E) {
      System.out.println ("ERROR READING 'Period List.txt");  
    }
    
    //add scroll to teacherzonepanel
    teacherZonePanel.add(teacherListScroll, BorderLayout.CENTER);
    //add back to main menu, edit teacher, and select teacher button to teacherlistbutton
    teacherListButtons.add(backToMainMenuButton2);
    teacherListButtons.add(editTeacherButton);
    teacherListButtons.add(goToTeacherTabs);
    //add to teacherzonePanel
    teacherZonePanel.add(teacherListButtons, BorderLayout.PAGE_END);
    
    //add button to teacher period panel
    teacherPeriodPanel.add(backToTeachersPeriodButton);
    
    //add instructions page label to jpanel
    instructionsPagePanel.add(instructionsLabel);
    instructionsPagePanel.add(backToMainMenuButton);
    //add empty jpanel and then instruction button then jpanel to make instruction button look small
    dummyPanel = new JPanel();
    //set color to a green
    dummyPanel.setBackground(new java.awt.Color(9, 193, 107));
    //add to instructions button panel
    instructionsButtonPanel.add(dummyPanel,BorderLayout.NORTH);
    JPanel instructionsMidPanel = new JPanel(new GridLayout(3,1));
    instructionsMidPanel.setBackground(new java.awt.Color(9, 193, 107));
    //add dummy panel and initialize its color to instructions panel
    dummyPanel = new JPanel();
    dummyPanel.setBackground(new java.awt.Color(9, 193, 107));
    //add dummypanel and instructions to instructionsmidpanel
    instructionsMidPanel.add(dummyPanel);
    instructionsMidPanel.add(instructions);
    //add dummy panel and initialize its color to instructions panel
    dummyPanel = new JPanel();
    dummyPanel.setBackground(new java.awt.Color(9, 193, 107));
    //add dummy panel
    instructionsMidPanel.add(dummyPanel);
    //add insturctions midpanel to instructionsbuttonpanel
    instructionsButtonPanel.add(instructionsMidPanel,BorderLayout.CENTER);
    //add dummy panel and initialize its color to instructions panel
    dummyPanel = new JPanel();
    dummyPanel.setBackground(new java.awt.Color(9, 193, 107));
    instructionsButtonPanel.add(dummyPanel,BorderLayout.EAST);   
    //add dummy panel and initialize its color to instructions panel
    dummyPanel = new JPanel();
    dummyPanel.setBackground(new java.awt.Color(9, 193, 107));
    instructionsButtonPanel.add(dummyPanel,BorderLayout.WEST); 
    //add dummy panel and initialize its color to instructions panel
    dummyPanel = new JPanel();
    dummyPanel.setBackground(new java.awt.Color(9, 193, 107));
    instructionsButtonPanel.add(dummyPanel,BorderLayout.SOUTH); 
    
    //add to removeTeachersPanel then to remove teachers Frame
    removeTeachersPanel.add(removeTeachersListScroll, BorderLayout.CENTER);
    removeTeachersPanel.add(removeTeachersButtonRemove, BorderLayout.PAGE_END);
    removeTeachersFrame.add(removeTeachersPanel);
    
    //add to addTeachersPanel then to addTeachersFrame
    JPanel addTeacherInput = new JPanel(new BorderLayout());
    addTeacherInput.setBackground(Color.WHITE);
    addTeacherInput.add(teachersNameLabel, BorderLayout.LINE_START);
    addTeacherInput.add(teachersNameField, BorderLayout.CENTER);   
    addTeachersPanel.add(addTeacherInput, BorderLayout.CENTER);
    addTeachersPanel.add(addTeachersButtonAdd, BorderLayout.PAGE_END);
    addTeachersFrame.add(addTeachersPanel);
    
    //Creating the panel to edit teacher information
    JPanel dividingPanel = new JPanel(new GridLayout(1,2));
    dividingPanel.add(editTeachersListScroll);
    JPanel periodAndButtonsPanel = new JPanel(new GridLayout(2,1));
    periodAndButtonsPanel.add(teacherPeriodsListScroll);
    JPanel mainButtonPanel = new JPanel(new BorderLayout());
    JPanel teacherButtonPanel = new JPanel(new GridLayout(2,2));
    teacherButtonPanel.add(addTeachersButtonEdit);
    teacherButtonPanel.add(addPeriodsButton1);
    teacherButtonPanel.add(removeTeachersButtonEdit);
    teacherButtonPanel.add(removePeriodsButton);
    mainButtonPanel.add(teacherButtonPanel, BorderLayout.CENTER);
    mainButtonPanel.add(backToTeachersEdit, BorderLayout.PAGE_END);
    periodAndButtonsPanel.add(mainButtonPanel);
    dividingPanel.add(periodAndButtonsPanel);
    editTeachersPanel.add(dividingPanel);
    editFrame.add(editTeachersPanel);
    
    //Setting up the generalInventory panel
    JPanel mainSubPanelGI = new JPanel();
    generalInventory.setLayout(new BorderLayout());
    listPanel.add(bookListScroll);
    generalInventory.add(listPanel, BorderLayout.CENTER);
    setInfoPanel.add(numberOfBooks);
    setInfoPanel.add(inStock);
    setInfoPanel.add(numBooksCheckedOut);
    functionPanel.add(setInfoPanel);
    invButtonPanel.add(editBooks);
    invButtonPanel.add(addBook);
    functionPanel.add(invButtonPanel);
    functionPanel.add(backToTeacherFrame);    
    generalInventory.add(functionPanel, BorderLayout.PAGE_END);
    
    //Setting up the sign out window by adding labels and fields to panel, then adding panel to frame
    signOutInfoPanel.add(signOutNumLabel);
    signOutInfoPanel.add(signOutNumField);
    signOutInfoPanel.add(signOutLabel);
    signOutInfoPanel.add(signOutField);
    signOutPanel.add(signOutInfoPanel, BorderLayout.CENTER);
    signOutPanel.add(signOutConfirm, BorderLayout.PAGE_END);
    signOutFrame.add(signOutPanel);
    
    periodTab.add(userPeriodsScroll);
    periodTab.add(viewClass);
    
    //Panel for viewing the bookset
    generalInventoryEditFrame.add(generalInventoryEditPanel);
    
    tabbedPaneTeachers.add("General Inventory",generalInventory);
    
    //Panel for adding a new bookset
    addBookLabelPanel.add(bookNameLabel);
    addBookLabelPanel.add(bookIDLabel);
    addBookLabelPanel.add(bookRoomLabel);
    addBookFieldPanel.add(bookNameField);
    addBookFieldPanel.add(bookIDField);
    addBookFieldPanel.add(bookRoomField);
    addBookPanel.add(addBookLabelPanel, BorderLayout.LINE_START);
    addBookPanel.add(addBookFieldPanel, BorderLayout.CENTER);
    addBookPanel.add(confirmBook, BorderLayout.PAGE_END);
    addBookFieldPanel.setBackground(Color.WHITE);
    addBookLabelPanel.setBackground(Color.WHITE);
    addBookFrame.add(addBookPanel);
    
    //add dummy panel to mainPanelRight
    dummyPanel = new JPanel();
    dummyPanel.setBackground(new java.awt.Color(9, 193, 107));
    mainPanelRight.add(dummyPanel);
    //panel for the teacherzone button
    JPanel teacherButtonZonePanel = new JPanel(new BorderLayout());
    //add dummy panel and teacherzone to teacherzonebuttonpanel
    dummyPanel = new JPanel();
    dummyPanel.setBackground(new java.awt.Color(9, 193, 107));
    teacherButtonZonePanel.add(dummyPanel,BorderLayout.WEST);
    teacherButtonZonePanel.add(teacherZone, BorderLayout.CENTER);
    //set another dummy panel
    dummyPanel = new JPanel();
    dummyPanel.setBackground(new java.awt.Color(9, 193, 107));
    teacherButtonZonePanel.add(dummyPanel,BorderLayout.EAST);
    //add the teacherzone button panel to main panel right
    mainPanelRight.add(teacherButtonZonePanel);    
    //add button mainPanelRight
    mainPanelRight.add(instructionsButtonPanel);
    //add mainPanelLeft
    mainPanelLeft.add(logoLabel, BorderLayout.CENTER);
    //add to main panel
    mainPanel.add(mainPanelLeft);
    mainPanel.add(mainPanelRight);
    
    //add mainPanelRight to frame
    this.add(mainPanel);
    
    //set main frame to visible
    this.setVisible(true);
    
  }
  
  //Load the period info
  public void loadPeriodInfo() throws IOException{
    try {      
      String line;
      FileReader in = new FileReader ("Period List.txt");
      BufferedReader input = new BufferedReader(in); 
      String line1;
      teacherListModel.removeAllElements();
      periodModel.removeAllElements();
      removeTeachersListModel.removeAllElements();
      editTeachersListModel.removeAllElements();
      teachers.clear();
      //Running through each line of code
      while ((line1 = input.readLine()) != null) {
        String tempLine = line1;  
        String[] teacherData = new String [2];
        for (int i = 0; i < 2; i++) {
          teacherData [i] = tempLine.substring (0, tempLine.indexOf(",")); 
          tempLine = tempLine.substring(tempLine.indexOf(",") + 1, tempLine.length());      
        }  
        //Adding the teachers and their data
        Teacher tempTeacher = new Teacher (teacherData[0]);//name
        teacherListModel.addElement(teacherData[0]);      
        periodModel.addElement(teacherData[0]); 
        removeTeachersListModel.addElement(teacherData[0]); 
        editTeachersListModel.addElement (teacherData[0]);
        teachers.add (tempTeacher);
        for (int j = 0; j < teacherData[1].length(); j++) {
          tempTeacher.addPeriod (Integer.parseInt(teacherData[1].substring (j, j + 1))); //period
        }
      }
    }catch (IOException E) {
      System.out.println ("ERROR READING 'Period List.txt");  
    }
  }
  
  /**
   * teacherTabMethod
   * this method opens the tabbed pane when you select a teacher
   */ 
  public void teacherTabMethod(){
    try {
      
      teacherUser = teacherList.getSelectedIndex();
      JPanel periodPanel = new JPanel();
      periodPanel.setBackground(Color.WHITE);
      ArrayList<ArrayList<Book>> periodBooks = new ArrayList<ArrayList<Book>>();  //Keeping track of the books
      int[] periodNums = new int[teachers.get(teacherUser).getPeriodNumbers().size()];
      periodInfoTables = new JTable[teachers.get(teacherUser).getPeriodNumbers().size()];
      //Adding the period numbers
      for (int i = 0; i < periodNums.length; i++){
        periodNums[i] = teachers.get(teacherUser).getPeriodNumbers().get(i);
        periodBooks.add(new ArrayList<Book>());
      }
      //Adding books to the arraylists
      for (int a = 0; a < periodNums.length; a++){
        for (int i = 0; i < bookInv.numBookSets(); i++){
          for (int j = 0; j < bookInv.getBookSet(i).getSize(); j++){
            Book temp = bookInv.getBookSet(i).getBook(j);
            if (!(temp.getTeacherName() == null)){
              if (temp.getTeacherName().equals(teachers.get(teacherUser).getName()) && temp.getPeriodNum() == periodNums[a]){
                periodBooks.get(a).add(temp);
              }
            }
          }
        }
      }
      //Setting the tables
      for (int b = 0; b < periodInfoTables.length; b++){
        String[] headers = {"Book Name", "Book ID", "Student Name"};
        String[][] data = new String[0][3];
        DefaultTableModel tableModel = new DefaultTableModel(data, headers);
        periodInfoTables[b] = new JTable(tableModel){
          //Do not allow cell editing
          public boolean isCellEditable(int row, int column){
            return false;
          };
        };
        periodInfoTables[b].getTableHeader().setReorderingAllowed(false);
        periodInfoTables[b].getTableHeader().setFont(new Font("Arial", Font.BOLD, 15));
        periodInfoTables[b].setFont(new Font("Arial", Font.PLAIN, 13));
        periodInfoTables[b].setBackground(Color.WHITE);
      }
      //Remove teacherzone panel and add tabbed pane
      if (teacherUser != -1) {
        for (int i=0;i<teachers.get(teacherUser).getPeriodNumbers().size();i++){
          userPeriodsModel.addElement(teachers.get(teacherUser).getPeriodNumbers().get(i));  
        }
        remove(teacherZonePanel);
        add(tabbedPaneTeachers);
        //Adding information to each table
        for (int x = 0; x < periodInfoTables.length; x++){
          ArrayList<Book> tempBooks = periodBooks.get(x);
          ArrayList<Book> sortedBooks = new ArrayList<Book>();
          String[] studentNames = new String[tempBooks.size()];
          //Adding the names
          for (int i = 0; i < studentNames.length; i++){
            studentNames[i] = tempBooks.get(i).getStudentName();
          }
          Arrays.sort(studentNames);  //Sorting by student names
          //Sorting the books by name
          for (int j = 0; j < studentNames.length; j++){
            for (int k = 0; k < tempBooks.size(); k++){
              //If the student names match
              if (tempBooks.get(k).getStudentName().equals(studentNames[j])){
                sortedBooks.add(tempBooks.get(k));
                tempBooks.remove(k);
                break;
              }
            }
          }
          //Adding the books to the table
          DefaultTableModel model = (DefaultTableModel)periodInfoTables[x].getModel();
          for (int y = 0; y < sortedBooks.size(); y++){
            Book temp = sortedBooks.get(y);
            model.addRow(new String[]{temp.getName(), temp.getBookID(), temp.getStudentName()});
          }
        }
        //Adding the tabs
        for (int j = 0; j < teachers.get(teacherUser).getPeriodNumbers().size(); j++){
          //Creating the panel for each tab
          JPanel tempPanel = new JPanel(new BorderLayout());
          JPanel periodButtons = new JPanel(new GridLayout(1,1));
          JButton checkInButton = new JButton("Check In Book");
          checkInButton.setBackground(Color.WHITE);
          checkInButton.setFont(new Font("Arial", Font.PLAIN, 14));
          checkInButton.addActionListener(new checkInButtonListener());
          periodButtons.add(checkInButton);
          JScrollPane tempPane = new JScrollPane(periodInfoTables[j]);
          tempPanel.add(tempPane, BorderLayout.CENTER);
          tempPane.getViewport().setBackground(Color.WHITE);
          tempPanel.add(periodButtons, BorderLayout.SOUTH);
          tabbedPaneTeachers.add("Period " + teachers.get(teacherUser).getPeriodNumbers().get(j), tempPanel);
        }
        //refresh page
        tabbedPaneTeachers.setVisible(false);
        tabbedPaneTeachers.setVisible(true);
      }
    } catch (Exception E) {
      JLabel chooseTeacherWarning = new JLabel("Please select a teacher!");
      chooseTeacherWarning.setFont(new Font("Arial", Font.PLAIN, 16));
      JOptionPane.showMessageDialog(new JFrame(), chooseTeacherWarning, "Teacher Error", JOptionPane.ERROR_MESSAGE); 
    }
  }
  /**
   * bookListMethod
   * this method opens the bookset when you select a book from the list
   */   
  public void bookListMethod(){
    //make sure they selected something in the list
    if (bookList.getSelectedIndex() != -1){
      //remove from panels
      generalInventoryEditPanel.removeAll();
      tablePanel.removeAll();
      //initalize string and bookset and string array for the header
      String setName = (String)bookList.getSelectedValue();
      BookSet bookSet = bookInv.getBookSet(setName);
      String[] header = {"Book ID", "Status", "Student Name", "Teacher Name", "Room #"};
      //Creating the table from loaded information
      generalInventoryTable = new JTable(new DefaultTableModel(bookSet.getInfoArray(), header)){
        //Do not allow editing in the table
        public boolean isCellEditable(int row, int column){                
          return false;               
        };
      };
      generalInventoryTable.getTableHeader().setReorderingAllowed(false);
      //Setting fonts of the table
      JPanel mainSubPanelGeneralInventoryEdit = new JPanel(new BorderLayout());
      
      generalInventoryTable.setFont(new Font("Arial", Font.PLAIN, 13));
      generalInventoryTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 15));
      //add to table panel
      tablePanel.add(new JScrollPane(generalInventoryTable));
      //add table panel to sub panel
      mainSubPanelGeneralInventoryEdit.add(tablePanel, BorderLayout.CENTER);
      //initialize panel
      JPanel editButtonPanel = new JPanel(new GridLayout(1,2));
      //add two buttons to panel
      editButtonPanel.add(removeBook);
      editButtonPanel.add(signOutBook);
      //add buttons to the GIeditpanel
      mainSubPanelGeneralInventoryEdit.add(editButtonPanel, BorderLayout.PAGE_END);
      generalInventoryEditPanel.add(mainSubPanelGeneralInventoryEdit,BorderLayout.CENTER);
      generalInventoryEditPanel.add(backToGeneralInventory,BorderLayout.SOUTH);
      //make the frame visible
      generalInventoryEditFrame.setVisible(true);
    }
  } 
  
  
  
  class SharedListSelectionHandler implements ListSelectionListener{
    public void valueChanged(ListSelectionEvent e){
      ListSelectionModel model = (ListSelectionModel)e.getSource();
      int index = model.getMaxSelectionIndex();
      if (index != -1){
        BookSet bookSet = bookInv.getBookSet(index);
        selectedBookset = bookSet.getName();
        numberOfBooks.setText("Number of Books: " + bookSet.getSize());
        inStock.setText("In Stock: " + bookSet.getNumInStock());
        numBooksCheckedOut.setText("# of Books Checked Out: " + (bookSet.getSize() - bookSet.getNumInStock()));
      }
    }
  }
  /**
   * instructionsButtonListener
   * the actionlistener for the instructiosn button
   */ 
  class instructionsButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      if (Desktop.isDesktopSupported()) {
        try {
          File myFile = new File("Manual.pdf");
          Desktop.getDesktop().open(myFile);
        } catch (IOException ex) {
          System.out.println("No file opener");
        }
      }
    }
  }
  /**
   * backToMainMenuButtonListener
   * the actionlistener for the backtomainmenu button
   */ 
  class backToMainMenuButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      //remove main panel
      remove(instructionsPagePanel);
      //add instructions page
      add(mainPanel);
      //refresh page
      mainPanel.setVisible(false);
      mainPanel.setVisible(true);
    }
  }
  /**
   * backToMainMenuButtonListener
   * the actionlistener for the backtomainmenu2 button
   */ 
  class backToMainMenuButton2Listener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      //remove main panel
      remove(teacherZonePanel);
      //load period info from file
       try {
        loadPeriodInfo();
      }catch (IOException E) {
        System.out.println ("ERROR READING 'Period List.txt");  
      }
      //add instructions page
      add(mainPanel);
      //refresh page
      mainPanel.setVisible(false);
      mainPanel.setVisible(true);
    }
  }
  /**
   * teacherZoneButtonListener
   * the actionlistener for the teacherzone button
   */ 
  class teacherZoneButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      //remove main panel
      remove(mainPanel);
      //load Period Info from file
      try {
        loadPeriodInfo();
      }catch (IOException E) {
        System.out.println ("ERROR READING 'Period List.txt");  
      }
      add(teacherZonePanel);
      //refresh page
      teacherZonePanel.setVisible(false);
      teacherZonePanel.setVisible(true);
    }
  }
  /**
   * editTeacherListSelectionListener
   * the list selection listener for the edit teacher jlist
   */
  class editTeacherListSelectionListener implements ListSelectionListener {
    public void valueChanged(ListSelectionEvent e) {
      //check if value on jlist is changing
      selectedTeacher = editTeachersList.getSelectedIndex();
      if (e.getValueIsAdjusting()&&selectedTeacher!=-1){        
        //set value to selected teacher on jlist
        selectedTeacher = editTeachersList.getSelectedIndex();
        //remove the panel to refresh later
        editTeachersPanel.removeAll();
        editTeachersSubPanel.removeAll();
        teacherPeriodsListModel.removeAllElements();
        //add the teachers name to label
        editTeachersName = new JLabel("Selected Teacher: "+ teachers.get(selectedTeacher).getName());        
        //add all the teachers periods to label
        for (int i=0;i<teachers.get(selectedTeacher).getPeriodNumbers().size();i++){          
          teacherPeriodsListModel.addElement(teachers.get(selectedTeacher).getPeriodNumbers().get(i));
        }
        
        //Refreshing the page by adding to the panel again
        JPanel dividingPanel = new JPanel(new GridLayout(1,2));
        dividingPanel.add(editTeachersListScroll);
        JPanel periodAndButtonsPanel = new JPanel(new GridLayout(2,1));
        periodAndButtonsPanel.add(teacherPeriodsListScroll);
        JPanel mainButtonPanel = new JPanel(new BorderLayout());
        JPanel teacherButtonPanel = new JPanel(new GridLayout(2,2));
        teacherButtonPanel.add(addTeachersButtonEdit);
        teacherButtonPanel.add(addPeriodsButton1);
        teacherButtonPanel.add(removeTeachersButtonEdit);
        teacherButtonPanel.add(removePeriodsButton);
        mainButtonPanel.add(teacherButtonPanel, BorderLayout.CENTER);
        mainButtonPanel.add(backToTeachersEdit, BorderLayout.PAGE_END);
        periodAndButtonsPanel.add(mainButtonPanel);
        dividingPanel.add(periodAndButtonsPanel);
        editTeachersPanel.add(dividingPanel);
        editFrame.add(editTeachersPanel);     
        editTeachersPanel.setVisible(false);
        editTeachersPanel.setVisible(true);
      }
    }
  }
  /**
   * teacherMouseListener
   * the mouseListener for the teacher jlist
   */
  class teacherMouseListener extends MouseAdapter{
    public void mouseClicked(MouseEvent event){
      //if they double click
      if (event.getClickCount() == 2) {
        //call teacherTabMethod
        teacherTabMethod();
      }        
    }    
  }
  /**
   * bookMouseListener
   * the mouseListener for the book jlist
   */
  class bookMouseListener extends MouseAdapter{
    public void mouseClicked(MouseEvent event){
      //if they double click
      if (event.getClickCount() == 2) {
        //call bookListMethod
        bookListMethod();
      }
    }    
  }
  /**
   * goToTeacherTabsListener
   * the ActionListener for the select teacher button
   */
  class goToTeacherTabsListener implements ActionListener{
    public void actionPerformed(ActionEvent event) {
      //call the teacher tab method
      teacherTabMethod();
    }
  }
  /**
   * editTeacherListener
   * the ActionListener for the edit teacher button
   */
  class editTeacherListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      //load period info from file
       try {
        loadPeriodInfo();
      }catch (IOException E) {
        System.out.println ("ERROR READING 'Period List.txt");  
      }
      //make the editpanel frame visible     
      editFrame.setVisible(true);
    }
  }
  /**
   * backToTeachersPeriodListener
   * the ActionListener for the back to teachers button
   */
  class backToTeachersPeriodListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      //remove teacherPeriodPanel from main frame
      remove(teacherPeriodPanel);
      //load period info from file
       try {
        loadPeriodInfo();
      }catch (IOException E) {
        System.out.println ("ERROR READING 'Period List.txt");  
      }
      //add teacherzonePanel to main frame
      add(teacherZonePanel);
      //refresh panel
      teacherZonePanel.setVisible(false);
      teacherZonePanel.setVisible(true);
      
    }
  }
  /**
   * backToTeacherFrameListener
   * the ActionListener for the back to teachers button
   */
  class backToTeacherFrameListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      //remove tabbedPaneTeachers from main frame
      remove(tabbedPaneTeachers);
      //load period info from file
       try {
        loadPeriodInfo();
      }catch (IOException E) {
        System.out.println ("ERROR READING 'Period List.txt");  
      }
      //add teacherzonepanel to main frame
      add(teacherZonePanel);
      //for loop to loop through the teacher's period
      for (int j = 0; j < teachers.get(teacherUser).getPeriodNumbers().size(); j++){
        //remove the tabs for each period in the teacher
        String name = "Period " + teachers.get(teacherUser).getPeriodNumbers().get(j);
        tabbedPaneTeachers.remove(tabbedPaneTeachers.indexOfTab(name));
      }
      //refresh page
      teacherZonePanel.setVisible(false);
      teacherZonePanel.setVisible(true);
      
    }
  }
  /**
   * backToTeachersEditListener
   * the ActionListener for the back to teachers button on the edit panel
   */
  class backToTeachersEditListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      //make the editpanel frame visible  
      editFrame.setVisible(false);
      //load period info from file
       try {
        loadPeriodInfo();
      }catch (IOException E) {
        System.out.println ("ERROR READING 'Period List.txt");  
      }
      //refresh page
      teacherZonePanel.setVisible(false);
      teacherZonePanel.setVisible(true);
      
    }
  }
  /**
   * selectTeacherListener
   * the ActionListener for the select teachers button
   */
  class selectTeacherListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      //make the editpanel frame visible  
      remove(teacherZonePanel);
      //load period info from file
       try {
        loadPeriodInfo();
      }catch (IOException E) {
        System.out.println ("ERROR READING 'Period List.txt");  
      }
      add(teacherPeriodPanel);
      //refresh frame
      teacherPeriodPanel.setVisible(false);
      teacherPeriodPanel.setVisible(true);      
    }
  }
  /**
   * addTeacherEditListener
   * the ActionListener for the add teachers on the edit panel
   */
  class addTeacherEditListener implements ActionListener{
    public void actionPerformed (ActionEvent event){
      //make the add teachers frame visible
      addTeachersFrame.setVisible(true);
    }
  }    
  /**
   * removeTeachersRemoveListener
   * the ActionListener for the remove teachers on the edit panel
   */
  class removeTeachersRemoveListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      
      //get the selected element on the removeTeachersList
      selectedTeacherRemove = -1;  //Presetting the variable to -1
      selectedTeacherRemove = removeTeachersList.getSelectedIndex();       
      //clear the selection on teachers list
      editTeachersList.clearSelection();
      //if user selects soemthing on jlist  
      // System.out.println("selectTeacher = " + selectedTeacherRemove);
      if (selectedTeacherRemove!=-1){    
        for (int i = 0; i < bookInv.numBookSets(); i++){
          for (int j = 0; j < bookInv.getBookSet(i).getSize(); j++){
            Book temp = bookInv.getBookSet(i).getBook(j);
            String teacherName = teachers.get(selectedTeacherRemove).getName();
            if (temp.getTeacherName() != null){
              if (temp.getTeacherName().equals(teacherName)){
                bookInv.getBookSet(i).getBook(j).setInStockStatus(true);
                bookInv.getBookSet(i).getBook(j).setStudentName(null);
                bookInv.getBookSet(i).getBook(j).setTeacherName(null);
                bookInv.getBookSet(i).getBook(j).setPeriodNum(-1);
              }
            }
          }
        }
        //Saving inventory
        try{
          bookInv.saveInv();
        }catch(IOException e){}
        
        
        // editTeachersName = new JLabel("Selected Teacher: "+ teachers.get(selectedTeacher).getName());
        teachers.get(selectedTeacherRemove).writePeriodList(true);
        teachers.remove(selectedTeacherRemove);
        //remove element from all the list models
        removeTeachersListModel.remove(selectedTeacherRemove);     
        teacherListModel.remove(selectedTeacherRemove);       
        editTeachersListModel.remove(selectedTeacherRemove);
        //remove all elements from the periods 
        teacherPeriodsListModel.removeAllElements();
        //clsoe the frame
        removeTeachersFrame.setVisible(false);
      }      
    }
  }
  /**
   * addTeachersAddListener
   * the ActionListener for the add teachers button
   */
  class addTeachersAddListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      String teacherName=teachersNameField.getText();
      //if they dont enter a name, remind them to enter a name
      if (teacherName.length()==0){        
        for (int r = 0; r < addTeachersPanel.countComponents(); r++) {       
          if (addTeachersPanel.getComponent(r) ==(dupeTeacher)) {
            addTeachersPanel.remove(dupeTeacher);  
          }
        }        
        addTeachersPanel.add(enterFieldReminder);    
        addTeachersFrame.setVisible(false);
        addTeachersFrame.setVisible(true);
      }else{       
        Boolean alreadyExists = false;
        for (int t = 0; t < teacherListModel.size(); t++) {  
          if (teacherListModel.get(t).equals(teacherName)) {
            alreadyExists = true; 
          }
        }        
        if (!alreadyExists) {
          //create teacher 
          Teacher teacher = new Teacher(teacherName);
          teacher.writePeriodList(false);
          //System.out.println ("Written");
          //add teacher to arraylist of teachers names
          //teacherNames.add(teacherName);
          //add teacher name to teacher list model
          teacherListModel.addElement(teacherName);
          //add teacher name to edit teacher list model
          editTeachersListModel.addElement(teacherName);
          //add teacher name to edit teacher remove model
          removeTeachersListModel.addElement(teacherName);
          //add teacher to array list of teachers
          teachers.add(teacher);
          
          for (int r = 0; r < addTeachersPanel.countComponents(); r++) {       
            if ((addTeachersPanel.getComponent(r) ==(dupeTeacher))||(addTeachersPanel.getComponent(r) ==(enterFieldReminder))) {
              addTeachersPanel.remove(addTeachersPanel.getComponent(r));  
            }
          }                
          addTeachersFrame.setVisible(false);
          
        } else {
          for (int r = 0; r < addTeachersPanel.countComponents(); r++) {       
            if (addTeachersPanel.getComponent(r) ==(enterFieldReminder)) {
              addTeachersPanel.remove(enterFieldReminder);  
            }
          }
          
          addTeachersPanel.add(dupeTeacher);
          addTeachersFrame.setVisible(false);
          addTeachersFrame.setVisible(true);
        }    
        //make the textfield blank
        teachersNameField.setText("");        
      }       
    }
  }
  /**
   * addPeriodsListener
   * the ActionListener for the add periods button
   */
  class addPeriodsListener implements ActionListener{
    public void actionPerformed(ActionEvent event){
      
      if (selectedTeacher!=-1){
        //Creating a frame to add periods
        addPeriodFrame = new JFrame();
        addPeriodFrame.setSize(width/4, height/6);
        JPanel mainAddPanel = new JPanel(new BorderLayout());
        JPanel periodInputPanel = new JPanel(new BorderLayout());
        periodInputPanel.setBackground(Color.WHITE);
        JLabel periodLabel = new JLabel("Period Number:    ");
        periodLabel.setFont(new Font("Arial", Font.BOLD, 16));
        JButton confirmAddPeriodButton = new JButton("Add New Period");
        confirmAddPeriodButton.setBackground(Color.WHITE);
        confirmAddPeriodButton.setFont(new Font("Arial", Font.PLAIN, 15));
        confirmAddPeriodButton.addActionListener(new addPeriods1Listener());
        periodInputPanel.add(periodLabel, BorderLayout.LINE_START);
        periodField.setFont(new Font("Arial", Font.PLAIN, 20));
        periodInputPanel.add(periodField, BorderLayout.CENTER);
        mainAddPanel.add(periodInputPanel, BorderLayout.CENTER);
        mainAddPanel.add(confirmAddPeriodButton, BorderLayout.PAGE_END);
        addPeriodFrame.add(mainAddPanel);
        addPeriodFrame.setVisible(true);
      } else {
        JLabel chooseTeacher = new JLabel( "Please select a teacher to edit!");
        chooseTeacher.setFont(new Font("Arial", Font.PLAIN, 16));
        JOptionPane.showMessageDialog(new JFrame(), chooseTeacher, "Teacher Editor", JOptionPane.ERROR_MESSAGE);   
      }
    }
  }
  /**
   * addPeriods1Listener
   * the ActionListener for the add periods1 button
   */
  class addPeriods1Listener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      //if the jlist has something selected
      
      
      //if the text fields are both filled
      if (!periodField.getText().equals("")){
        
        //get the period
        
        try {//assuming the person enters a number.
          int period = Integer.parseInt(periodField.getText());
          
          //add classroom to teacher class
          if ((period <= 5)&&(period >= 1)&&(teachers.get(selectedTeacher).getPeriodNumbers().size() < 3)&&(teachers.get(selectedTeacher).newPeriodCheck(period))) {
            teachers.get(selectedTeacher).addPeriod(period);
            teachers.get(selectedTeacher).writePeriodList(false);
            teacherPeriodsListModel.removeAllElements();
            for (int t = 0; t < teachers.get(selectedTeacher).getPeriodNumbers().size(); t++) {
              teacherPeriodsListModel.addElement(teachers.get(selectedTeacher).getPeriodNumbers().get(t));
            }
            
            
            
            //remove all from edit panel
            editTeachersPanel.removeAll();
            editTeachersSubPanel.removeAll();          
            //edit the teachers name label to put the teachers name of the item in the jlist
            editTeachersName = new JLabel("Selected Teacher: "+teachers.get(selectedTeacher).getName());      
          } else {
            if ((period > 5)&&(period < 1)) {
              JLabel periodWarning1 = new JLabel( "Duplicate periods; please enter a new period or delete the existing corresponding period.");
              periodWarning1.setFont(new Font("Arial", Font.PLAIN, 16));
              JOptionPane.showMessageDialog(new JFrame(), periodWarning1, "Enter Valid Period", JOptionPane.ERROR_MESSAGE);  
            } else if (teachers.get(selectedTeacher).getPeriodNumbers().size() == 3) {
              JLabel periodWarning2 = new JLabel( "Too many periods; you may not add more than 3 periods.");
              periodWarning2.setFont(new Font("Arial", Font.PLAIN, 16));
              JOptionPane.showMessageDialog(new JFrame(), periodWarning2, "Enter Valid Period", JOptionPane.ERROR_MESSAGE);    
            } else {
              JLabel periodWarning3 = new JLabel("Period out of range; please enter a valid period number between 1 and 5.");
              periodWarning3.setFont(new Font("Arial", Font.PLAIN, 16));
              JOptionPane.showMessageDialog(new JFrame(), periodWarning3, "Enter Valid Period", JOptionPane.ERROR_MESSAGE);    
            }
          } 
          
          
        }catch (Exception E) {
          periodField.setText(""); 
          JLabel enteredWord = new JLabel( "Please enter a number!");
          enteredWord.setFont(new Font("Arial", Font.PLAIN, 16));
          JOptionPane.showMessageDialog(new JFrame(), enteredWord, "Enter Valid Period", JOptionPane.ERROR_MESSAGE);    
        } 
        //set both text fields to blank
        periodField.setText("");    
        
        //Refreshing the page by adding to the panel again
        editTeachersPanel.removeAll();
        JPanel dividingPanel = new JPanel(new GridLayout(1,2));
        dividingPanel.add(editTeachersListScroll);
        JPanel periodAndButtonsPanel = new JPanel(new GridLayout(2,1));
        periodAndButtonsPanel.add(teacherPeriodsListScroll);
        JPanel mainButtonPanel = new JPanel(new BorderLayout());
        JPanel teacherButtonPanel = new JPanel(new GridLayout(2,2));
        teacherButtonPanel.add(addTeachersButtonEdit);
        teacherButtonPanel.add(addPeriodsButton1);
        teacherButtonPanel.add(removeTeachersButtonEdit);
        teacherButtonPanel.add(removePeriodsButton);
        mainButtonPanel.add(teacherButtonPanel, BorderLayout.CENTER);
        mainButtonPanel.add(backToTeachersEdit, BorderLayout.PAGE_END);
        periodAndButtonsPanel.add(mainButtonPanel);
        dividingPanel.add(periodAndButtonsPanel);
        editTeachersPanel.add(dividingPanel);
        editFrame.add(editTeachersPanel);     
        editTeachersPanel.setVisible(false);
        editTeachersPanel.setVisible(true);
        addPeriodFrame.setVisible(false);
      }
    }
    
  }
  /**
   * removePeriodsListener
   * the ActionListener for the remove periods button
   */
  class removePeriodsListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      //get the selected index of both period list and teacher list
      selectedPeriodRemove=teacherPeriodsList.getSelectedIndex();
      selectedTeacherRemove=editTeachersList.getSelectedIndex(); 
      
      //if there is a selected period
      if ((selectedPeriodRemove!=-1)&&(selectedTeacherRemove!=-1)){
        
        //Clearing books with that period and teacher
        for (int i = 0; i < bookInv.numBookSets(); i++){
          for (int j = 0; j < bookInv.getBookSet(i).getSize(); j++){
            Book temp = bookInv.getBookSet(i).getBook(j);
            String teacherName = teachers.get(selectedTeacherRemove).getName();
            if (temp.getTeacherName() != null){
              if (temp.getTeacherName().equals(teacherName)){
                bookInv.getBookSet(i).getBook(j).setInStockStatus(true);
                bookInv.getBookSet(i).getBook(j).setStudentName(null);
                bookInv.getBookSet(i).getBook(j).setTeacherName(null);
                bookInv.getBookSet(i).getBook(j).setPeriodNum(-1);
              }
            }
          }
        }
        //Saving inventory
        try{
          bookInv.saveInv();
        }catch(IOException e){}
        
        teachers.get(selectedTeacherRemove).removeSinglePeriodBooks(selectedPeriodRemove);
        teachers.get(selectedTeacherRemove).removePeriod(selectedPeriodRemove);
        teachers.get(selectedTeacherRemove).writePeriodList (false);        
        
        //remove the period from the list model
        teacherPeriodsListModel.removeElementAt(selectedPeriodRemove);
      } else if (selectedTeacherRemove==-1){
        JLabel chooseTeacher = new JLabel( "Please select a teacher to edit!");
        chooseTeacher.setFont(new Font("Arial", Font.PLAIN, 16));
        JOptionPane.showMessageDialog(new JFrame(), chooseTeacher, "Teacher Editor", JOptionPane.ERROR_MESSAGE);    
      } else {
        JLabel choosePeriod = new JLabel( "Please select a period to remove!");
        choosePeriod.setFont(new Font("Arial", Font.PLAIN, 16));
        JOptionPane.showMessageDialog(new JFrame(), choosePeriod, "Teacher Editor", JOptionPane.ERROR_MESSAGE);    
      }
      teacherPeriodsList.clearSelection();
    }
  }
  /**
   * removeTeachersEditListener
   * the ActionListener for the remove teachers button on the edit panel
   */
  class removeTeachersEditListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      JLabel deleteTeacherWarning = new JLabel( "WARNING: you cannot unremove a teacher once removed. All teacher info will be erased.");
      deleteTeacherWarning.setFont(new Font("Arial", Font.PLAIN, 16));
      JOptionPane.showMessageDialog(new JFrame(), deleteTeacherWarning, "Teacher Warning", JOptionPane.ERROR_MESSAGE);   
      removeTeachersFrame.setVisible(true);
    }
  }
  /**
   * editBooksListener
   * the ActionListener for the edit books button
   */
  class editBooksListener implements ActionListener{
    public void actionPerformed(ActionEvent event) {
      //call bookListMethod
      bookListMethod();
    }
  }
  
  /**
   * removeBookListener
   * the ActionListener for the remove books button
   */
  class removeBookListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      int[] rows = generalInventoryTable.getSelectedRows();  //Selected rows on the table
      String[] selectedIDs = new String[rows.length];
      //Getting the selected book IDs
      for (int i = 0; i < rows.length; i++){
        selectedIDs[i] = (String)generalInventoryTable.getValueAt(rows[i], 0);
      }
      DefaultTableModel model = (DefaultTableModel)generalInventoryTable.getModel();  //Getting the table model
      //Removing the book
      for (int n = (rows.length - 1); n >= 0; n--){
        Book temp = bookInv.getBook(selectedBookset, selectedIDs[n]);
        //If the book is checked out, remove it from the period table and don't if it is in stock
        if (((String)generalInventoryTable.getValueAt(rows[n], 1)).equals("Checked Out")){          
          int periodIndex = teachers.get(teacherUser).getPeriodNumbers().indexOf(temp.getPeriodNum());
          //  System.out.println(periodIndex);
          //Finding the book and removing it
          DefaultTableModel periodModel = (DefaultTableModel)periodInfoTables[periodIndex].getModel();
          for (int m = 0; m < periodModel.getRowCount(); m++){
            if (((String)periodModel.getValueAt(m, 1)).equals(temp.getBookID())){
              periodModel.removeRow(m);  //Removing from the period table
            }
          }
          //Removing
          model.removeRow(rows[n]);
          bookInv.removeBook(selectedBookset, selectedIDs[n]);
        } else {
          //Removing
          bookInv.removeBook(selectedBookset, selectedIDs[n]);
          model.removeRow(rows[n]);
        }
      }
      //Closing the window when there are no more books
      if (generalInventoryTable.getRowCount() < 1){
        //System.out.println(generalInventoryTable.getRowCount());
        bookInv.removeBookSet(selectedBookset);  //Removing the bookset when empty
        //Changing the list 
        bookListModel.removeAllElements();
        for (int i = 0; i < bookInv.numBookSets(); i++){
          bookListModel.addElement(bookInv.getBookSet(i).getName());  //Adding the name of each book list
        }
        generalInventoryEditFrame.dispatchEvent(new WindowEvent(generalInventoryEditFrame, WindowEvent.WINDOW_CLOSING));
      }
      //Saving inventory
      try{
        bookInv.saveInv();
      }catch(IOException e){}
    }
  }
  /**
   * addBookListener
   * the ActionListener for the add books button
   */
  class addBookListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      addBookFrame.setVisible(true);
    }
  }
  /**
   * confirmBookListener
   * the ActionListener for the confirm books button
   */
  class confirmBookListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      boolean numberValid = true;
      //Getting information from the fields
      String name = bookNameField.getText();
      String id = bookIDField.getText();
      String roomNum = bookRoomField.getText();;
      //If the text fields are valid
      if (name.length() > 0 && id.length() > 0 && numberValid == true){
        Book newBook = new Book(name, id, true, roomNum);  //New book to declare
        bookInv.addBook(newBook);  //Adding new book to inventory
      }
      //Resetting the ID button
      bookIDField.setText(null);
      //Changing the list 
      bookListModel.removeAllElements();
      for (int i = 0; i < bookInv.numBookSets(); i++){
        bookListModel.addElement(bookInv.getBookSet(i).getName());  //Adding the name of each book list
      }
      //Saving inventory
      try{
        bookInv.saveInv();
      }catch(IOException e){}
    }
  }
  /**
   * signOutBookListener
   * the ActionListener for the sign out books button
   */
  class signOutBookListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      int index = generalInventoryTable.getSelectedRow();
      
      //Only sign out book if it is currently available        
      
      if (index!=-1){
        if (((String)generalInventoryTable.getValueAt(index, 1)).equals("In Stock")){
          signOutFrame.setVisible(true);
        } else {
          //Warning popup
          JLabel signOutWarning2 = new JLabel("Book is currently signed out.");
          signOutWarning2.setFont(new Font("Arial", Font.PLAIN, 16));
          JOptionPane.showMessageDialog(new JFrame(), signOutWarning2, "Sign Out Error", JOptionPane.ERROR_MESSAGE);
        }
      }
      
    }
  }
  /**
   * signOutConfirmListener
   * the ActionListener for the sign out books confirm button
   */
  class signOutConfirmListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      
      //Variables to keep track of all book data needed
      String name = signOutField.getText();
      try {
        int periodNum = Integer.parseInt(signOutNumField.getText());
        boolean periodExists = false;
        for (int x = 0; x < teachers.get(teacherUser).getPeriodNumbers().size(); x++) {
          if (periodNum == teachers.get(teacherUser).getPeriodNumbers().get(x)) {
            periodExists = true;
          }
        }             
        if (periodExists) { 
          int index = generalInventoryTable.getSelectedRow();
          int teacherIndex = teacherList.getSelectedIndex();
          String selectedID = (String)generalInventoryTable.getValueAt(index, 0);
          //Setting values for the book
          bookInv.getBook(selectedBookset, selectedID).setInStockStatus(false);
          bookInv.getBook(selectedBookset, selectedID).setStudentName(name);
          bookInv.getBook(selectedBookset, selectedID).setTeacherName(teachers.get(teacherIndex).getName());
          bookInv.getBook(selectedBookset, selectedID).setPeriodNum(periodNum);
          //Updating the general table
          generalInventoryTable.getModel().setValueAt("Checked Out", index, 1);
          generalInventoryTable.getModel().setValueAt(name, index, 2);
          generalInventoryTable.getModel().setValueAt(teachers.get(teacherIndex).getName(), index, 3);
          //Updating the period table
          int periodIndex = teachers.get(teacherIndex).getPeriodNumbers().indexOf(periodNum);
          DefaultTableModel model = (DefaultTableModel)periodInfoTables[periodIndex].getModel();
          model.addRow(new String[]{selectedBookset, selectedID, name});
          //Saving inventory
          try{
            bookInv.saveInv();
          }catch(IOException e){}
          //Resetting the text fields
          signOutField.setText("");
          signOutNumField.setText("");
          //Resetting the bookset label values
          BookSet tempBookSet = bookInv.getBookSet(selectedBookset);
          numberOfBooks.setText("Number of Books: " + tempBookSet.getSize());
          inStock.setText("In Stock: " + tempBookSet.getNumInStock());
          numBooksCheckedOut.setText("# of Books Checked Out: " + (tempBookSet.getSize() - tempBookSet.getNumInStock()));
          signOutFrame.setVisible(false);  //Removing the frame
        } else {
          JLabel signOutWarning1 = new JLabel("Please enter a valid period!");
          signOutWarning1.setFont(new Font("Arial", Font.PLAIN, 16));
          JOptionPane.showMessageDialog(new JFrame(), signOutWarning1, "Sign Out Error", JOptionPane.ERROR_MESSAGE);
        }
      } catch (Exception E) {
        JLabel signOutWarning2 = new JLabel("Please enter a valid period!");
        signOutWarning2.setFont(new Font("Arial", Font.PLAIN, 16));
        JOptionPane.showMessageDialog(new JFrame(), signOutWarning2, "Sign Out Error", JOptionPane.ERROR_MESSAGE);
      }
    }
  }
  /**
   * checkInButtonListener
   * the ActionListener for the check in books button
   */
  class checkInButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      
      boolean allCheckedIn = false;  //Check to see if check in has worked
      try {
        //Getting the selected book
        int periodIndex = tabbedPaneTeachers.getSelectedIndex() - 1;
        System.out.println(tabbedPaneTeachers.getSelectedIndex());
        int[] rows = periodInfoTables[periodIndex].getSelectedRows();
        for (int i = rows.length-1; i >= 0; i--){
          String bookset = (String)periodInfoTables[periodIndex].getValueAt(rows[i], 0);
          String id = (String)periodInfoTables[periodIndex].getValueAt(rows[i], 1);
          //Resetting the book's values
          bookInv.getBook(bookset, id).setInStockStatus(true);
          bookInv.getBook(bookset, id).setTeacherName(null);
          bookInv.getBook(bookset, id).setStudentName(null);
          bookInv.getBook(bookset, id).setPeriodNum(-1);
          //Updating the table
          ((DefaultTableModel)(periodInfoTables[periodIndex].getModel())).removeRow(rows[i]);
        }
        allCheckedIn = true;  //Books have been checked in
        //Resetting the bookset label values
        BookSet tempBookSet = bookInv.getBookSet(selectedBookset);
        numberOfBooks.setText("Number of Books: " + tempBookSet.getSize());
        inStock.setText("In Stock: " + tempBookSet.getNumInStock());
        numBooksCheckedOut.setText("# of Books Checked Out: " + (tempBookSet.getSize() - tempBookSet.getNumInStock()));
        //Saving inventory
        try{
          bookInv.saveInv();
        }catch(IOException e){}
      } catch (Exception E) {
        if (allCheckedIn == false){
          JLabel checkInWarning = new JLabel("Please select a book to check in!");
          checkInWarning.setFont(new Font("Arial", Font.PLAIN, 16));
          JOptionPane.showMessageDialog(new JFrame(), checkInWarning, "Check In Error", JOptionPane.ERROR_MESSAGE); 
        }
      }
    }
  }
  /**
   * ViewClassListener
   * the ActionListener for the view class button
   */
  class ViewClassListener implements ActionListener { 
    public void actionPerformed(ActionEvent event) {   
      //get the selected index
      userSelectClass = userPeriods.getSelectedIndex();  
      while (userSelectClass != -1) {
        //teachers.get(teacherUser).getPeriod()[userSelectClass].;
      }
    } 
  }
  /**
   * backToGeneralInventoryListener
   * the ActionListener for backToGeneralInventory button
   */
  class backToGeneralInventoryListener implements ActionListener{
    public void actionPerformed(ActionEvent event) {  
      //makes the edit general inventory frame close
      generalInventoryEditFrame.setVisible(false);
    }
  }
}