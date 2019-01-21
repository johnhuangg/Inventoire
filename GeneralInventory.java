/**
 * GeneralInventory.java
 * @author BookStore Group
 * General Book Inventory Structure
 */ 

//Imports
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;

class GeneralInventory{
  
  private ArrayList<BookSet> bookSets;  //ArrayList of booksets
  
  /**
   * Constructor for the general inventory
   */
  GeneralInventory(){
    bookSets = new ArrayList<BookSet>();
  }
  
  /**
   * Returns a bookset based on index
   * @param index Index to be returned
   * @return BookSet BookSet at the specified index
   */
  public BookSet getBookSet(int index){
    return bookSets.get(index);  //Returning a bookset
  }
  
  /**
   * Returns a bookset based on the name
   * @param bookName The name of the bookset
   * @return BookSet BookSet with the specified name
   */
  public BookSet getBookSet(String bookName){
    //Finding the bookset with the correct name
    for (int i = 0; i < bookSets.size(); i++){
      if (bookSets.get(i).getName().equals(bookName)){
        return bookSets.get(i);  //Returning a bookset
      }
    }
    return null;  //Return nothing if there is no bookset with that name
  }
  
  /**
   * Returns a book based on the name and ID
   * @param bookName Name of the bookset the book belongs too
   * @param bookID ID of the book
   * @return Book Book with the corresponding information
   */
  public Book getBook(String bookName, String bookID){
    BookSet tempSet = getBookSet(bookName);  //Getting the bookset with the correct name
    //Finding the book with the correct ID
    for (int i = 0; i < tempSet.getSize(); i++){
      if (tempSet.getBook(i).getBookID().equals(bookID)){
        return tempSet.getBook(i);
      }
    }
    return null;  //Returning nothing if the information is invalid
  }
  
  /**
   * Removes a bookset based on name
   * @param name Name of the bookset
   * @return Nothing
   */
  public void removeBookSet(String name){
    //Finding the correct bookset
    for (int i = 0; i < bookSets.size(); i++){
      if ((bookSets.get(i).getName()).equals(name)){
        bookSets.remove(i);  //Removing it from the arraylist
      }
    }
  }
  
  /**
   * Adds a new book to the inventory
   * @param newBook Book to be added
   * @return Nothing
   */
  public void addBook(Book newBook){
    boolean added = false;  //Variable to check if the book has been added
    //Finding a matching bookset
    for (int i = 0; i < bookSets.size(); i++){
      //If the book's name matches the bookset
      if (((bookSets.get(i)).getName()).equals(newBook.getName())){
        (bookSets.get(i)).addBook(newBook);  //Adding the book
        added = true;
      }
    }
    //If there is no matching bookset
    if (added == false){
      bookSets.add(new BookSet(newBook.getName()));  //Adding the book
      (bookSets.get(bookSets.size() - 1)).addBook(newBook);
    }
  }
  
  /**
   * Removes a book with a given name and ID
   * @param bookName Name of the bookset of the book
   * @param bookID ID of the book
   * @return boolean True if the book has been removed and False is there is no corresponding book
   */
  public boolean removeBook(String bookName, String bookID){
    boolean removed = false;  //Variable to check if the book has been removed
    int removingIndex = 0;
    //Finding a bookset of the same name as the book
    for (int i = 0; i < bookSets.size(); i++){
      if (((bookSets.get(i)).getName()).equals(bookName)){
        //Looping through all the books to find the correct ID
        for (int j = 0; j < (bookSets.get(i)).getSize(); j++){
          if ((((bookSets.get(i)).getBook(j)).getBookID()).equals(bookID)){
            (bookSets.get(i)).removeBook((bookSets.get(i)).getBook(j));  //Removing the book
            removed = true;
            removingIndex = i;  //Keeping track of the bookset that was removed from
          }
        }
      }
    }
    //Removing the bookset if it is empty
    if ((bookSets.get(removingIndex)).isEmpty() == true){
      bookSets.remove(removingIndex);  //Removing the bookset
    }
    return removed;
  }
  
  /**
   * Saves inventory to a file
   * @param Nothing
   * @return Nothing
   * @throws IOException
   */
  public void saveInv() throws IOException{
    File file = new File("bookStorage.txt");
    PrintWriter output = new PrintWriter(file);
    //Running through the booksets
    for (int i = 0; i < bookSets.size(); i++){
      bookSets.get(i).sort();  //Sorting the inventory
      for (int j = 0; j < bookSets.get(i).getSize(); j++){
        Book tempBook = bookSets.get(i).getBook(j);  //Temporary book
        //Printing all the information that is guaranteed
        output.print(tempBook.getName() + ",");
        output.print(tempBook.getBookID() + ",");
        output.print(tempBook.getInStockStatus() + ",");
        output.print(tempBook.getRoomNum() + ",");
        //Printing information based on whether it has been added or not
        if (tempBook.getTeacherName() != null){
          //Runs if the book has been checked out to a teacher and student
          output.print(tempBook.getStudentName() + ",");
          output.print(tempBook.getTeacherName() + ",");
          output.println(tempBook.getPeriodNum() + ",");
        } else {
          //Runs if the book is in stock
          output.println("null,null,null,");
        }
      }
    }
    output.close();  //Closing PrintWriter
  }
  
  /**
   * Loads the inventory from a file
   * @param Nothing
   * @return Nothing
   * @throws IOException
   */
  public void loadInv() throws IOException{
    FileReader in = new FileReader("bookStorage.txt");
    BufferedReader input = new BufferedReader(in);
    //Running through the lines in the file
    String line;
    while((line = input.readLine()) != null){
      String tempLine = line;
      String[] data = new String[7];
      //Getting all the book information
      for (int i = 0; i < 7; i++){
        data[i] = tempLine.substring(0, tempLine.indexOf(","));
        tempLine = tempLine.substring(tempLine.indexOf(",") + 1, tempLine.length());
      }
      //Creating the temporary book and setting its variables
      Book temp = new Book(data[0], data[1], Boolean.parseBoolean(data[2]), data[3]);
      if (!data[4].equals("null") && !data[5].equals("null") && !data[6].equals("null")){
        temp.setStudentName(data[4]);
        temp.setTeacherName(data[5]);
        temp.setPeriodNum(Integer.parseInt(data[6]));
      }
      addBook(temp);  //Adding the book to the bookset
    }
    input.close();  //Cloding BufferedReader
  }
  
  /**
   * Returns the number of booksets in the inventory
   * @param Nothing
   * @return int Number of booksets
   */
  public int numBookSets(){
    return bookSets.size();  //Returning the number of booksets
  }
  
}