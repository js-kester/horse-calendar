import java.io.*;
import java.util.*;
public class Horse implements Comparable
{
   private String name;
   private int level;
   private int type;
   private String notes;
   private ArrayList<Integer> usedTimes;
      
   public Horse(String n, int lev, int tp)      //Owned or leased construction
   {
      name = n;
      level = lev;
      type = tp;
      notes = "";     
      usedTimes = new ArrayList<Integer>();                             
                                         
   }

   public String getName()  //name of horse
   {
      return name;
   }
   
   public int getLevel()      //horse level
   {
      return level;
   }
   
   public void addUsedTime(int t)
   {
      usedTimes.add(t);
   }
   
   public void setUsedTimes(ArrayList<Integer> a)
   {
      usedTimes = a;
   }
   
   public ArrayList<Integer> getUsedTimes()
   {
      return usedTimes;
   }
   
   public int getType()   //if horse is shared/leased/owned
   {
      return type;
   }
   
   public String getNotes()   //special horse notes
   {
      return notes;
   }
   
   public void editNotes(String n)
   {
      notes = n;
   }
   
   public String toString()
   {
      return name;
   }
   
   public int compareTo(Object o)
   {
      return toString().compareTo(o.toString());
   }
   
   public boolean canUse(Map<String,ArrayList<Integer>> times, int t)
   {
      String s = name;
      int ty = type;
  //     if(times.get(name) == null)
//       {
//          return true;
//       }
      if(type != 1)
      {
         if(times.get(name).size() == 4)
         {
            return false;
         }
         if((times.get(name).contains(t-1) && times.get(name).contains(t-2)) || times.get(name).contains(t))
         {
            return false;
         }
      }
      else
      {
         if(usedTimes.size() == 4)
         {
            return false;
         }
         if((usedTimes.contains(t-1) && usedTimes.contains(t-2)) || usedTimes.contains(t))
         {
            return false;
         }
      }
      return true;
   }

}