import java.io.*;
import java.util.*;
public class Rider implements Comparable
{
   private String name;
   private int level;
   private Horse horse;
   private ArrayList<Integer> times;
   private String notes;
   private boolean isBorrowing;
   private boolean hasLesson;
      
   public Rider(String n, int lev, ArrayList<Integer> t, Horse h, boolean b)      
   {
      name = n;
      level = lev;
      times = t;       
      horse = h;  
      isBorrowing = b; 
      hasLesson = false;
   }   
   
   public Rider()
   {
   }
      
   public String getName()    //rider name
   {
      return name;
   }
   
   public int getLevel()      //rider level
   {
      return level;
   }
   
   public ArrayList<Integer> getTimes()
   {
      return times;
   }
   
   public String getNotes()   //special rider notes
   {
      return notes;
   }
   
   public void editNotes(String n)
   {
      notes = n;
   }
   
   public static String translateTime(int t)
   {
     return (t +":00");   
   }
   
   public void setLesson(boolean b)
   {
      hasLesson = b;
   }
   
   public Horse getHorse()
   {
      return horse;
   }
   
   public boolean hasLesson()
   {
      return hasLesson;
   }
   
   public String toString()
   {
         return name;
   }
   
   public int compareTo(Object o)
   {
      return toString().compareTo(o.toString());
   }
   
   public boolean isBorrowing()
   {
      return isBorrowing;
   }
   
   public void setHorse(Horse h)
   {
      horse = h;
   }
   
   
   
   
   
   
   
   
    //  private void sepTimes(String[] intervals)      //break times for matrix
//    {
//       //figure out input structure
//       //break up strings and put into matrix
//       for(int i = 0; i < intervals.length; i++)
//       {
//          String t = intervals[i];
//          double hours = 0;
//          double min = 0;
//          hours = Integer.parseInt(t.substring(0,2));
//          min = Integer.parseInt(t.substring(3,5)) / 60.0;
//          times[i][0] = hours + min;
//          hours = Integer.parseInt(t.substring(6,8));
//          min = Integer.parseInt(t.substring(9,11)) / 60.0;
//          times[i][1] = hours + min;
//       }
//    }

   
   // public void printTimesArray()
//    {
//       String s = "";
//       for(int r = 0; r < times.length; r++)
//       {
//          s = "";
//          for(int c = 0; c < times[0].length; c++)
//          {
//             s += times[r][c] + " ";
//          }
//          System.out.println(s);
//       }
//    }

   // public void editTimes(String old, String edit) //change available times
//    {
//          //get old start
//          String t = old;
//          double hours = 0;
//          double min = 0;
//          hours = Integer.parseInt(t.substring(0,2));
//          min = Integer.parseInt(t.substring(3,5)) / 60.0;
//          double start = hours + min;
//          
//          //get new interval
//          t = edit;
//          hours = 0;
//          min = 0;
//          hours = Integer.parseInt(t.substring(0,2));
//          min = Integer.parseInt(t.substring(3,5)) / 60.0;
//          double b = hours + min;
//          hours = Integer.parseInt(t.substring(6,8));
//          min = Integer.parseInt(t.substring(9,11)) / 60.0;
//          double e = hours + min;
//          
//          //find old start and replace
//          for(int i = 0; i < times.length; i++)
//          {
//             if(start == times[i][0])
//             {
//                times[i][0] = b;
//                times[i][1] = e;
//             }
//          }         
//          
//    }
   // public double totalTime()
//    {
//       double total = 0;
//       for(int r = 0; r < times.length; r++)
//       {
//          total -= times[r][0];
//       }
//       for(int r = 0; r < times.length; r++)
//       {
//          total += times[r][1];
//       }
//       return total;
//    }
}