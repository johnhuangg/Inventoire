/**
 * Teacher.java
 * This class represents a teacher.
 * @author BookStore group
 */
import java.util.ArrayList; 
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.BufferedReader;

class Teacher{
  
  //Class variables
  private String name;
  private ArrayList<Integer> periodNumber;
  private ArrayList<Period> periodBooks;
  private File teacherList;  
  private File periodList;
  private Scanner reader;
  private PrintWriter writer;
  
  Teacher(String name){
    this.name = name;
    this.periodNumber = new ArrayList<Integer>();
    this.periodBooks = new ArrayList<Period>();
    
    try {
      this.teacherList = new File ("Teacher List.txt");
      this.periodList = new File ("Period List.txt");
      this.reader = reader;
      this.writer = writer;
    } catch (Exception E){
      System.out.println ("ERROR LOADING 'Teacher List.txt' ");  
    }
    
  }
  
  /**
   * writePeriodList
   * Writes the teacher's periods to 'Period Info.txt'.
   * @param boolean; true if removing this teacher, false if not.
   */
  public void writePeriodList (boolean remove) {
    String[] temp = new String [2];
    Boolean change = false;
    try {
      ArrayList<String[]> periods = this.readPeriodList();
      temp [0] = this.name;
      temp [1] = "";
      for (int g = 0; g < this.periodNumber.size(); g++) {
        temp [1] += this.periodNumber.get(g);  
      }    
      for (int h = 0; h < periods.size(); h++) { 
        //System.out.println (this.name + " " + periods.get(h)[0]);
        if (periods.get(h)[0].equals(this.name)) {   
          if (remove) {
            periods.remove(h);          
          } else {
            periods.set (h, temp);
            h = periods.size();  
          }
          change = true;
        }
      }
      
      if (!change) {
        periods.add (temp);  
      }
      
      //writes the teacherList
      writer = new PrintWriter (periodList);        
      for (int t = 0; t < periods.size(); t++) { 
        this.writer.println(periods.get(t)[0] + "," + periods.get(t)[1] + ",");
      }
      
      writer.close();
    } catch (Exception E){
      System.out.println ("ERROR WRITING TO 'Period List.txt' ");  
    }  
  }
  
  /**
   * readPeriodList
   * Reads the teacher's information from 'Teacher List.txt'.
   * @return ArrayList<String[]> that contains the info on the teachers and their periods.
   */
  public ArrayList<String[]> readPeriodList (){
    ArrayList<String[]> periods = new ArrayList<String[]>();
    try {
      String line;
      FileReader in = new FileReader ("Period List.txt");
      BufferedReader input = new BufferedReader(in); 
      String line1;
      
      while ((line1 = input.readLine()) != null) {
        String tempLine = line1;  
        String[] teacherData = new String [2];
        for (int i = 0; i < 2; i++) {
          teacherData [i] = tempLine.substring (0, tempLine.indexOf(",")); 
          tempLine = tempLine.substring(tempLine.indexOf(",") + 1, tempLine.length());      
        }
        periods.add(teacherData);
      } 
    }catch (Exception E) {
      System.out.println ("ERROR READING 'Period List.txt");  
    }    
    return periods;    
  }
  
  /**
   * getName
   * Gets the name of the teacher.
   * @return String that represents the teacher's name.
   */
  public String getName(){
    return this.name;
  }
  
  /**
   * setName
   * Sets the name of the teacher.
   * @param String that represents the teacher's name.
   */
  public void setName(String name){
    this.name = name;
  }
  
  /**
   * getPeriods
   * Gets the teacher's periodNumber.
   * @return ArrayList<Integer> that represents the teacher's periodNumber.
   */
  public ArrayList<Integer> getPeriodNumbers(){
    return this.periodNumber;
  }
  
  /**
   * setAllPeriods
   * Sets all of the teacher's periodNumber.
   * @param ArrayList<Integer> that represents the period to be changed.
   */
  public void setAllPeriods(ArrayList<Integer> periodNumber){
    this.periodNumber = periodNumber;
  }
  
  /**
   * clearAllPeriods
   * Clears all of the teacher's periodNumber.
   */
  public void clearAllPeriods(){
    this.periodNumber.clear();
  }
  
  /**
   * addPeriod
   * Sets one of the teacher's periodNumbers.
   * @param int that represents the index of the period to be changed
   * @param int that represents the new period number
   */
  public void addPeriod(int newPeriod){
    this.periodNumber.add(newPeriod);
    this.periodBooks.add(new Period());
    this.sortPeriods();
  }

  /**
   * removePeriod
   * Removes one of the teacher's periodNumber.
   * @param int that represents the period to be removed.
   */
  public void removePeriod(int period){
    this.periodNumber.remove(period);
  }
  
  /**
   * getPeriodBooks
   * Gets the books being used for a period.
   * return ArrayList<Period> that represents the books being used in that period.
   */
  public ArrayList<Period> getPeriodBooks(){
    return this.periodBooks;
  }
  
  /**
   * setPeriodBooks
   * Sets the books being used for a period.
   * @param ArrayList<Period> that represents the books being used in that period.
   */
  public void setPeriodBooks(ArrayList<Period> periodBooks){
    this.periodBooks = periodBooks;
  }

  
   /**
   * removeSinglePeriodBooks
   * Removes a single period.
   * @param int that represents the index of the period to be removed.
   */
  public void removeSinglePeriodBooks(int index){
    this.periodBooks.remove (index);
  }
  
  /**
   * newPeriodBooks
   * Checks to see if a period number already exists.
   * @param int that represents the period to check.
   * @return boolean; true if the period does not exist, and false if it does.
   */
  public boolean newPeriodCheck (int newPeriod) {
    boolean test = true; 
    for (int r = 0; r < this.periodNumber.size(); r++) {
      if (this.periodNumber.get(r) == newPeriod) {
        test = false;  
        r = this.periodNumber.size();
      }
    } 
    return test;
  }
  
  /**
   * sortPeriods
   * Sorts the periods in numerical order.
   */
  public void sortPeriods () {
    int numberPH;
    Period periodPH;
    boolean sorted = false;
    
    while (!sorted) {
      sorted = true;

      for (int r = 0; r < this.periodNumber.size() - 1; r++) {
        if (this.periodNumber.get(r) > this.periodNumber.get(r + 1)) {
          //sorts period numbers
          numberPH = this.periodNumber.get(r);
          this.periodNumber.set(r, this.periodNumber.get(r + 1));        
          this.periodNumber.set(r + 1, numberPH);          
         // System.out.println (this.periodNumber.get(r) + " " + this.periodNumber.get(r + 1));
          //sorts periods
          periodPH = this.periodBooks.get(r);
          this.periodBooks.set(r, this.periodBooks.get(r + 1));
          this.periodBooks.set(r + 1, periodPH);        
          
          sorted = false;
        }
      }   
    }   
  }
    
}