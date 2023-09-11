import java.io.*;
import java.util.*;
public class Day
{
   private int DOW;
   private int date;
   private Map<Integer, Block> blocks;
   private String notes;
   public Day(int dow, int d)
   {
      DOW = dow;
      date = d;
      blocks = new TreeMap<Integer, Block>();
   }
   
   public int getDOW()
   {
      return DOW;
   }
   
   public String strDOW()
   {
      if(DOW == 0)
         return "Sunday";
      if(DOW == 1)
         return "Monday";
      if(DOW == 2)
         return "Tuesday";
      if(DOW == 3)
         return "Wednesday";
      if(DOW == 4)
         return "Thursday";
      if(DOW == 5)
         return "Friday";
      if(DOW == 6)
         return "Saturday";
      return "error";
   }
   
   public int getDate()
   {
      return date;
   }
  
   public Map<Integer, Block> getBlocks()
   {
      return blocks;
   }
   
   public void addBlock(Block b, int t)
   {
      blocks.put(t,b);
   }
   
   public String getNotes()
   {
      return notes;
   }
   
   public void printSchedule()
   {
      for(Integer t : blocks.keySet())
      {
         for(Instructor i : blocks.get(t).getInstructors())
         {
            if(i.getLessons().get(t).getLessonSize() > 0)
            {
               System.out.print(i.getName() + " is doing " + i.getLessons().get(t).getLessonType() + " with ");
               if(!i.getLessons().get(t).isSoloLesson())  //if not solo rider
               {
                  for(Rider r : i.getLessons().get(t).getLessonGroup())
                  {
                     System.out.print(r.getName() + " ");
                  }
               }
               else
               {
                  System.out.print(i.getLessons().get(t).getSoloRider().getName() + " " + "solo ");
               }
               System.out.println("at " + t + ":00 ");
            }
         }
      }
   }
   
   public void createSchedule()
   {
      try
      {
         File file = new File("TestSchedule.csv");
         FileWriter writer = new FileWriter(file);
         writer.write(toString() + ",\n");
         String time = "";
         String ins = "";
         String type = "";
         String rid = "";
         boolean newTime;
         for(Integer t : blocks.keySet())
         {
            newTime = true;
            for(Instructor i : blocks.get(t).getInstructors())
            {
               rid = "";
               ins = "Ins: " + i.getName() + ",";
               if(i.getLessons().get(t).getLessonSize() > 0)
               {
                  if(newTime)
                  {
                     writer.write(t + ":00,\n");
                     newTime = false;
                  }
                  type = "Type: " + i.getLessons().get(t).getLessonType() + ",\n";
                  if(!i.getLessons().get(t).isSoloLesson())  //if not solo rider
                  {
                     for(Rider r : i.getLessons().get(t).getLessonGroup())
                     {
                       rid += r.getName() + "," + r.getHorse() + ",\n";
                     }
                     
                  }
                  else
                  {
                     rid = i.getLessons().get(t).getSoloRider().getName() + "," + i.getLessons().get(t).getSoloRider().getHorse() + ",\n";
                  }
                  
                  writer.write(ins + type + rid + "\n");
               }
            }
         }
         writer.flush();
         writer.close();
      }
      catch(Exception E)
      {
         System.out.println(E);
      }
   }
   
   public String toString()
   {
      String out = "";
      if(date == 1)
         out = "1st";
      else if(date == 2)
         out = "2nd";
      else if(date == 3)
         out = "3rd";
      else
         out = date + "th";
      return strDOW() + " the " + out;
   }
}