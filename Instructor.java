import java.io.*;
import java.util.*;
import java.awt.*;
public class Instructor
{
   private String name;
   private int level;
   private ArrayList<Integer> times;
   private String notes;
   private Map<Integer,Lesson> lessons;
   
   public Instructor(String n, int l, ArrayList<Integer> t)
   {
      name = n;
      level = l;
      times = t;
      lessons = new TreeMap<Integer,Lesson>();
   }
   
   public String getName()
   {
      return name;
   }
   
   public int getLevel()
   {
      return level;
   }
   
   public ArrayList<Integer> getTimes()
   {
      return times;
   }
   
   public Map<Integer,Lesson> getLessons()
   {
      return lessons;
   }
   
   public void addLesson(Lesson l, int t)
   {
      lessons.put(t,l);
   }
   
   public void addTime(int t)
   {
      times.add(t);
   }
   
   public void editNotes()
   {
      notes = "temp";
   }
   
   public String getNotes()
   {
      return notes;
   }
   
   public boolean isAvailable(int t)
   {
      if(times.contains(t))
         return true;
      return false;
   }
   
   public void addStudent(Rider r, int t, String l)
   {
      lessons.get(t).addRider(r,l);
   }
   
   public void addSoloStudent(Rider r, int t, String l)
   {
      lessons.get(t).addSoloRider(r,l);
   }
   
   public boolean canTeach(Rider r, int t, String type)
   {  
      String s = r.getName();       //NULL LESSON TYPE
      Map<Integer,Lesson> l = lessons;
      if(lessons.get(t) != null)
      {
         if(lessons.get(t).getLessonType() != null && !lessons.get(t).getLessonType().equals(type))
         {
            return false;
         }
         if(lessons.get(t).getLessonSize() > 0)
         {
            if(lessons.get(t).getLevel() != r.getLevel() || lessons.get(t).getLessonGroup().size() == 8) //if wrong level or full
            {
               return false;
            }
         }
         if(lessons.get(t).isSoloLesson())
         {
            return false;
         }
      }
      return true;
      
   }
   
   public void removeStudent(Rider r, int t)
   {
      lessons.get(t).remove(r);
   }
   
   public void removeSoloStudent(int t)
   {
      lessons.get(t).removeSolo();
   }
}