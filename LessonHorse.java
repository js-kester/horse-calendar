import java.io.*;
import java.util.*;
import java.awt.*;
public class LessonHorse extends Horse
{
   private String name;
   private int level;
   private int type;
   private String notes;
   private ArrayList<Integer> times;
   private int streak;
   private int uses;
   private boolean inUse;
   private ArrayList<Integer> usedTimes;
   public LessonHorse(String n, int lev, int tp, ArrayList<Integer> t)
   {
      super(n, lev, tp);
      name = n;
      level = lev;
      type = tp;
      times = t;
      streak = 0;
      uses = 0;
      inUse = false;
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
 
   public int getType()   //if horse is shared/leased/owned
   {
      return type;
   }
   public ArrayList<Integer> getTimeArray()
   {
      return times;
   }
   public int getStreak()
   {
      return streak;
   }
   public void addStreak()
   {
      streak++;
   }
   public void resetStreak()
   {
      streak = 0;
   }
   public int getUses()
   {
      return uses;
   }
   public void addUse()
   {
      uses++;
   }
   public void resetUses()
   {
      uses = 0;
   }
   public boolean isInUse()
   {
      return inUse;
   }
   public void inUse()
   {
      inUse = true;
   }
   public void notInUse()
   {
      inUse = false;
   }
   public void addTime(int t)
   {
      usedTimes.add(t);
   }
   public void clearTimes()
   {
      usedTimes = new ArrayList<Integer>();
   }
   public ArrayList<Integer> getUsedTimes()
   {
      return usedTimes;
   }
}