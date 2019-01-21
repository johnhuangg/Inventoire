/**
 * BookSet.java
 * @author BookStore group
 * Class to represent a book set
 */

//Imports
import java.util.ArrayList;
import java.util.Collections;

class BookSet{
  
  //Class variables
  private String name;
  private ArrayList<Book> books;
  
  /**
   * Constructor for a bookset
   */
  BookSet(String name){
    this.name = name;
    books = new ArrayList<Book>();
  }
  
  /**
   * Returns the name of the bookset
   * @param Nothing
   * @return String Name of the bookset
   */
  public String getName(){
    return name;
  }
  
  /**
   * Sets the name of the bookset
   * @param name Desired name of the bookset
   * @return Nothing
   */
  public void setName(String name){
    this.name = name;
  }
  
  /**
   * Returns the books in the bookset
   * @param Nothing
   * @return ArrayList<Book> List of books
   */
  public ArrayList<Book> getBooks(){
    return books;
  }
  
  /**
   * Sets the books in the bookset
   * @param books List of books
   * @return Nothing
   */
  public void setBooks(ArrayList<Book> books){
    this.books = books;
  }
  
  /**
   * Adds a book to the bookset
   * @param newBook Book to be added
   * @return Nothing
   */
  public void addBook(Book newBook){
    books.add(newBook);
  }
  
  /**
   * Removes a book from the bookset
   * @param removingBook Book to be removed
   * @return Nothing
   */
  public void removeBook(Book removingBook){
    books.remove(removingBook);
  }
  
  /**
   * Returns a book at the specified index
   * @param index Index of the book to be removed
   * @return Book Book at the index
   */
  public Book getBook(int index){
    return books.get(index);
  }
  
  /**
   * Returns the number of books in the bookset
   * @param Nothing
   * @return int Number of books
   */
  public int getSize(){
    return books.size();
  }
  
  /**
   * Checks if the bookset is empty
   * @param Nothing
   * @return boolean True if it is empty and False if it isn't
   */
  public boolean isEmpty(){
    //Checking if the book list is empty or not
    if (books.size() > 0){
      return false;
    } else {
      return true;
    }
  }
  
  /**
   * Returns the number of books in stock
   * @param Nothing
   * @return int Number of books in stock
   */
  public int getNumInStock(){
    int num = 0;
    //Finding all the books in the set that are available
    for (int i = 0; i < books.size(); i++){
      if (books.get(i).getInStockStatus() == true){
        num++;
      }
    }
    return num;  //Returning the number of available books
  }
  
  /**
   * Sorts the books in the bookset by room # and ID
   * @param Nothing
   * @return Nothing
   */
  public void sort(){
    ArrayList<String> roomNumbers = new ArrayList<String>();  //Creating a list of room numbers
    ArrayList<String> bookIDs = new ArrayList<String>();  //Creating a list of IDs
    ArrayList<Book> newBooks = new ArrayList<Book>();  //List to sort by ID
    ArrayList<Book> finalBooks = new ArrayList<Book>();  //List for final sort
    //Adding to the list
    for (int i = 0; i < books.size(); i++){
      roomNumbers.add(books.get(i).getRoomNum());
      bookIDs.add(books.get(i).getBookID());
    }
    Collections.sort(bookIDs);  //Sorting the list of book IDs
    //Sorting the main book list by ID
    for (int i = 0; i < books.size(); i++){
      for (int j = 0; j < books.size(); j++){
        //If the IDs match
        if (books.get(j).getBookID().equals(bookIDs.get(i))){
          newBooks.add(books.get(j));  //Adding to the new set of books
          break;  //Breaking the loop
        }
      }
    }
    Collections.sort(roomNumbers);  //Sorting the list of room numbers
    //Sorting the main book list
    for (int i = 0; i < roomNumbers.size(); i++){
      for (int j = 0; j < newBooks.size(); j++){
        //If the ID and room number match
        if (newBooks.get(j).getRoomNum().equals(roomNumbers.get(i))){
          finalBooks.add(newBooks.get(j));
          //Remove the book from the list to continue sorting
          newBooks.remove(j);
          break;
        }
      }
    }
    this.books = finalBooks;  //Resetting the books
  }
  
  /**
   * Returns a 2D array of all the books and their associated information
   * @param Nothing
   * @return String[][] 2D array of all books and their information
   */
  public String[][] getInfoArray(){
    String[][] data = new String[books.size()][5];  //2D array to output
    //Getting the bookset information
    for (int x = 0; x < books.size(); x++){
      data[x][0] = books.get(x).getBookID();
      //Getting the in stock status
      if (books.get(x).getInStockStatus() == true){
        data[x][1] = "In Stock";
      } else {
        data[x][1] = "Checked Out";
      }
      //Getting the checked out or in stock specific information
      if (books.get(x).getStudentName() != null){
        data[x][2] = books.get(x).getStudentName();
        data[x][3] = books.get(x).getTeacherName();
      } else {
        data[x][2] = "N/A";
        data[x][3] = "N/A";
      }
      //Getting the price
      data[x][4] = books.get(x).getRoomNum();
    }
    return data;  //Returning the 2D array
  }
  
}