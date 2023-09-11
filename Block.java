import java.io.*;
import java.util.*;
public class Block
{
   private int time;
   private ArrayList<Horse> horsesInUse;
   private ArrayList<Instructor> instructors;
   public Block(int t)
   {
      time = t;
      horsesInUse = new ArrayList<Horse>();
      instructors = new ArrayList<Instructor>();
   }
   
   public int getTime()
   {
      return time;
   }
   
   public ArrayList<Horse> getUsedHorses()
   {
      return horsesInUse;
   }
   
   public ArrayList<Instructor> getInstructors()
   {
      return instructors;
   }
   
   public void addInstructor(Instructor i)
   {
      instructors.add(i);
   }
   
   public void addUsedHorse(Horse h)
   {
      horsesInUse.add(h);
   }
   
   
   public static String translateTime(int t)
   {
      return (t +":00");
   }
   
}