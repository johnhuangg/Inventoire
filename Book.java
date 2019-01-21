/**
 * Book.java
 * @author BookStore group
 * Class for representing a book in the system
 */

class Book{
  
  //Class variables
  private String name;
  private String bookID;
  private Boolean inStock;
  private String roomNum;
  private String studentName;
  private String teacherName;
  private int periodNum;
  
  /**
   * Constructor for a book
   */
  Book(String name, String bookID, boolean inStock, String roomNum){
    this.name = name;
    this.bookID = bookID;
    this.inStock = inStock;
    this.roomNum = roomNum;
    this.periodNum = -1;
  }
  
  /**
   * Returns the name of the book
   * @param Nothing
   * @return String Name of the book
   */
  public String getName(){
    return name;
  }
  
  /**
   * Sets the name of the book
   * @param name Desired name of book
   * @return Nothing
   */
  public void setName(String name){
    this.name = name;
  }
  
  /**
   * Returns the ID of the book
   * @param Nothing
   * @return String ID of the book
   */
  public String getBookID(){
    return bookID;
  }
  
  /**
   * Sets the ID of the book
   * @param bookID Desired ID of book
   * @return Nothing
   */
  public void setBookID(String bookID){
    this.bookID = bookID;
  }
  
  /**
   * Returns the status of the book
   * @param Nothing
   * @return boolean True if the book is in stock and False if the book is checked out
   */
  public boolean getInStockStatus(){
    return inStock;
  }
  
  /**
   * Sets the status of the book
   * @param inStock Desired status of book
   * @return Nothing
   */
  public void setInStockStatus(boolean inStock){
    this.inStock = inStock;
  }
  
  /**
   * Returns the price of the book
   * @param Nothing
   * @return doule Price of the book
   */
  public String getRoomNum(){
    return roomNum;
  }
  
  /**
   * Sets the price of the book
   * @param price Desired price of book
   * @return Nothing
   */
  public void setRoomNum(String roomNum){
    this.roomNum = roomNum;
  }
  
  /**
   * Returns the name of the student associated with the book
   * @param Nothing
   * @return String Name of the student associated with the book
   */
  public String getStudentName(){
    return studentName;
  }
  
  /**
   * Sets the student who holds the book
   * @param studentName Name of the student
   * @return Nothing
   */
  public void setStudentName(String studentName){
    this.studentName = studentName;
  }
  
  /**
   * Returns the name of the teacher associated with the book
   * @param Nothing
   * @return String Name of the teacher associated with the book
   */
  public String getTeacherName(){
    return teacherName;
  }
  
  /**
   * Sets the teacher who holds the book
   * @param teacherName Teacher's name
   * @return Nothing
   */
  public void setTeacherName(String teacherName){
    this.teacherName = teacherName;
  }
  
  /**
   * Returns the period number of the book
   * @param Nothing
   * @return int Period number of the book
   */
  public int getPeriodNum(){
    return periodNum;
  }
  
  /**
   * Sets the period number of the book
   * @param periodNum Desired period number of book
   * @return Nothing
   */
  public void setPeriodNum(int periodNum){
    this.periodNum = periodNum;
  }
  
  /**
   * Outputs all information of the book to console
   * @param Nothing
   * @return Nothing
   */
  public void outputInfo(){
    //Outputting guaranteed variables
    System.out.print(name + ",");
    System.out.print(bookID + ",");
    System.out.print(inStock + ",");
    System.out.print(roomNum + ",");
    if (studentName != null){
      //Runs if the book is checked out
      System.out.print(studentName + ",");
      System.out.print(teacherName + ",");
      System.out.println(periodNum + ",");
    } else {
      //Runs if the book is in stock
      System.out.println("null,null,null,");
    }
  }
  
}