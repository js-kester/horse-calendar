import java.io.*;
import java.util.*;
import java.awt.*;
public class Lesson
{
   private int time;
   private ArrayList<Rider> group;
   private Rider single;
   private String lessonType;
   private int level;
   private boolean isSoloLesson;
   private int lessonSize;
   
   public Lesson(int t, ArrayList<Rider> g, String l) //for group
   {
      time = t;
      group = g;
      lessonType = l;
      isSoloLesson = false;
      lessonSize = 0;
   }
   
   public Lesson(int t, Rider r, String l) //for solo
   {
      time = t;
      single = r;
      lessonType = l;
      isSoloLesson = true;
      level = r.getLevel();
      lessonSize = 1;
   }
   
   public Lesson(int t)
   {
      time = t;
      group = new ArrayList<Rider>();
      lessonSize = 0;
   }
   
   
   public int getLessonTime()
   {
      return time;
   }
   
   public Rider getSoloRider()
   {
      if(isSoloLesson)
         return single;
      else
         return null;
      
   }
   
   public ArrayList<Rider> getLessonGroup()
   {
      return group;
   }
   
   public void addRider(Rider r, String l)
   {
      if(lessonSize == 0)
      {
         level = r.getLevel();
         isSoloLesson = false;
         lessonType = l;
      }
      group.add(r);
      lessonSize++;
   }
   
   public void addSoloRider(Rider r, String l)
   {     
      lessonType = l;
      level = r.getLevel();
      single = r;
      isSoloLesson = true;
      lessonSize = 1;
   }
   
   public int getLevel()
   {
      return level;
   }
   
   public int getLessonSize()
   {
      return lessonSize;
   }
   
   public boolean isSoloLesson()
   {
      return isSoloLesson;
   }
   
   public void setLessonSolo(boolean b)
   {
      isSoloLesson = b;
   }
   
   public String getLessonType()
   {
      return lessonType;
   }
   
   public void remove(Rider r)
   {
      group.remove(r);
      lessonSize--;
      if(lessonSize == 0)
      {
         level = -1;
         isSoloLesson = false;
         lessonType = "";
      }
   }
   
   public void removeSolo()
   {
      lessonType = "";
      level = -1;
      single = null;
      isSoloLesson = false;
      lessonSize = 0;
   }
   
}