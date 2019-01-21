/**
 * Period.java
 * This class will represent a single period for a teacher.
 * @author BookStore group
 */


//Imports
import java.util.ArrayList;
import java.io.File;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;

class Period{
  
  //Class variables
  ArrayList<Book> books;
  File file;
  
  /**
   * Constructor for the period class.
   */
  Period(){
    books = new ArrayList<Book>();
    file = new File ("Books in Each Period.txt");
  }
  
  //Saving the inventory to a file
  public void writeInv(String name, int period) throws Exception{
    PrintWriter writer = new PrintWriter(file);
    ArrayList<String[]> info = this.readInv();
    Boolean change = false;
    int correctTeachIndex = 0;
    Boolean found = false;
    try {
      String periodStr = String.valueOf(period);
      while (!found) {
        if (info.get(correctTeachIndex).length == 2) {
          if (info.get(correctTeachIndex)[0].equals(name)&&(info.get(correctTeachIndex)[1].equals(periodStr))){ 
            while(info.get(correctTeachIndex + 1).length != 2){
              info.remove (correctTeachIndex); //removes it to rewrite it  
            }
            found = true;
          } else {           
            correctTeachIndex++;    
          }
        } else {         
          correctTeachIndex++;  
        }       
      }
      
      //writes the teacherList
      
      for (int t = 0; t < info.size(); t++) { 
        for (int y = 0; y < info.get(t).length; y++) {
          writer.print(info.get(t)[y] + ","); 
        }
        writer.println("");  
      }
      
//name, ID, stock, price, student, teacher, period
      
      writer.println(name + "," + periodStr + ",");
      int index = 0;
      while (index != this.books.size()) {
        writer.println (this.books.get(index).getName() + "," + this.books.get(index).getBookID() + "," + 
                        String.valueOf(this.books.get(index).getInStockStatus()) + "," + 
                        String.valueOf(this.books.get(index).getRoomNum()) + "," + 
                        this.books.get(index).getStudentName() + "," + this.books.get(index).getTeacherName() + ","
                          + String.valueOf(this.books.get(index).getPeriodNum() + ","));  
      }
      
      writer.close();
    } catch (Exception E){
      System.out.println ("ERROR WRITING TO 'Books in each Period.txt' ");  
    }  
    
    writer.close();  //Closing PrintWriter
  }
  
  //Loading the inventory from a file
  public ArrayList<String[]> readInv() throws Exception{
    ArrayList<String[]> info = new ArrayList<String[]>();
    FileReader in = new FileReader("Books in Each Period.txt");
    BufferedReader input = new BufferedReader(in);
    //Running through the lines in the file
    String line;
    while((line = input.readLine()) != null){
      int count = 0;
      for (int l = 0; l < line.length(); l++) { 
        if (line.charAt(l) == ',') {
          count++; 
        }
      }          
      String tempLine = line;
      String[] data = new String[count];
      //Getting all the book information
      for (int i = 0; i < count; i++){
        data[i] = tempLine.substring(0, tempLine.indexOf(","));
        tempLine = tempLine.substring(tempLine.indexOf(",") + 1, tempLine.length());
      }      
      info.add (data);
    }
    input.close();  //Cloding BufferedReader
    return info;
  }
  
  /**
   * getBooks
   * Gets all of the books being used for a period.
   * @return Arraylist<Book> that represents all the books being used for the period.
   */
  public ArrayList<Book> getBooks(){
    return this.books;
  }
  
  /**
   * setBooks
   * Sets all of the books being used for a period.
   * @param Arraylist<Book> that represents all the books being used for the period.
   */
  public void setBooks(ArrayList<Book> books){
    this.books = books;
  }
  
  /**
   * addSingleBook
   * Adds a single book being used for a period.
   * @param Book that represents the new book.
   */
  public void addSingleBook(Book book) {
    this.books.add(book);     
  }
  
  /**
   * removeSingleBook
   * Removes a single book being used for a period.
   * @param Book that represents the book being removed.
   */
  public void removeSingleBook(Book book) {
    this.books.remove(book);     
  }
  
//Saving the inventory to a file
  public void saveFile(Book newBook, String teacher, int period) throws Exception{
    PrintWriter output = new PrintWriter(file);
    ArrayList<String[]> info = this.readFile();
    String [] teacherPeriod = {teacher, String.valueOf(period)};     
    int index = info.indexOf(teacherPeriod);
    String [] bookInfo = new String[]{newBook.getName(), newBook.getBookID(), 
      String.valueOf(newBook.getInStockStatus()), String.valueOf(newBook.getRoomNum()), 
      newBook.getStudentName(), newBook.getTeacherName(), String.valueOf(newBook.getPeriodNum())};
    info.add (index + 1, bookInfo);       
    
    for (int y = 0; y < info.size(); y++) {
      for (int z = 0; z < info.get(y).length; z++) {
        output.print (info.get(y)[z] + ",");
      }
      output.println("");
      
      if (info.get(y + 1).length == 2){
        output.println ("!");  
      }
    }
    
    
    output.close();  //Closing PrintWriter
  }
  
  //Name, ID, instock, price, student, teacher, period 
  //Loading the inventory from a file
  public ArrayList<String[]> readFile() throws Exception{
    ArrayList<String[]> info = new ArrayList<String[]>();
    FileReader in = new FileReader(file);
    BufferedReader input = new BufferedReader(in);
    //Running through the lines in the file
    String teacher, period, line, tempLine;
    String [] bookData = new String [7];
    String [] teacherPeriod = new String [2];
    
    while ((line = input.readLine()) != null){
      tempLine = line;
      teacherPeriod = new String [7];
      for (int i = 0; i < 2; i++){
        teacherPeriod [i] = tempLine.substring(0, tempLine.indexOf(","));
        tempLine = tempLine.substring(tempLine.indexOf(",") + 1, tempLine.length());
      }
      while ((line = input.readLine()) != "!") {
        //Getting all the book information,
        for (int i = 0; i < 7; i++){
          bookData[i] = tempLine.substring(0, tempLine.indexOf(","));
          tempLine = tempLine.substring(tempLine.indexOf(",") + 1, tempLine.length());
        }
        info.add(bookData);
      }
    }
    input.close();  //Closing BufferedReader
    
    return info;
  } 
  
  
}





