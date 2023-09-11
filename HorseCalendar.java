import java.io.*;
import java.util.*;
import java.awt.*;
public class HorseCalendar
{
   private String name;
   private Day[][] month;
   
   public HorseCalendar(String n, Day[] b)
   {
      Day[] days = b;
      name = n;
      month = new Day[4][7];
      int spot = 0;
      for(int w = 0; w < 4; w++)
      {
         for(int d = 0; d < 6; d++)
         {
            if(spot < days.length && d == days[spot].getDOW())
            {
               month[w][d] = days[spot];
               spot++;
            }
            else
            {
               month[w][d] = null;
               
            }
         }
      }
   }


   public static void main(String[] args) throws FileNotFoundException
   {        
      System.out.println("Compiling...");
      Scanner kb = new Scanner(System.in);
      Scanner file = new Scanner(new File("TestList.csv"));
      ArrayList<Rider> students = new ArrayList<>();
      file.nextLine();
      file.nextLine();
      
      int open = 8;
      int close = 18;
      ArrayList<Integer> hours = new ArrayList<>();
       for(int i = open; i < close; i++)
       {
         hours.add(i);
       }
/*********************************************************************************/
//Owned horses
      Map<Rider,ArrayList<String>> reasons = new TreeMap<Rider,ArrayList<String>>();
      String lowTime = "Low time availability";
      String oddLessonType = "Lesson type is uncommon";
      
      int totalRiders = 0;
      
      
      Map<Rider,Boolean> soloMap = new TreeMap<Rider,Boolean>();
      Map<Rider,String> LTMap = new TreeMap<Rider,String>();         //Lesson Type Map
      Map<String,Integer> HTMap = new TreeMap<String,Integer>();     //Horse Type Map
      System.out.println("Pulling owned horses...");
      while(file.hasNextLine())
      {
         String[] info = file.nextLine().split(",");
         if(info.length > 0 && info[0].equals("Leased"))
               break;
         ArrayList<Integer> tList = new ArrayList<>();
         String[] tInts = info[3].split(";");
         for(String s : tInts)
         {
            if(s.charAt(0) == '"')
            {
               s = s.substring(1,s.length());
            }
            if(s.indexOf('-') != -1)
            {
               for(int i = Integer.parseInt(s.substring(0,s.indexOf('-'))); i <= Integer.parseInt(s.substring(s.indexOf('-')+1,s.length())); i++)
               {
                  tList.add(i);
               }
            }
            else
            {
               tList.add(Integer.parseInt(s.substring(0,s.length())));
            }
               
         }
         Rider r = new Rider(info[0], Integer.parseInt(info[1]), tList, new Horse(info[2], Integer.parseInt(info[1]), 1), false);
         totalRiders++;
         students.add(r);
         reasons.put(r,new ArrayList<String>());
         LTMap.put(r,info[4]);
         HTMap.put(r.getHorse().getName(), r.getHorse().getType());
         if(info.length > 5)
         {
            soloMap.put(r,true);
         }
         else
         {
            soloMap.put(r,false);
         }
      }
/*******************************************************************************************************/
//leased horses
      System.out.println("Pulling leased horses...");
      String[] leasedHorses = file.nextLine().split(",");
      Horse[] horses = new Horse[leasedHorses.length];
      for(int i = 0; i < horses.length; i++)
      {
         horses[i] = new Horse(leasedHorses[i], 3, 2);
      }
      Map<String, ArrayList<Integer>> usedTimes = new TreeMap<>();
      for(Horse h : horses)
      {
         usedTimes.put(h.getName(), new ArrayList<Integer>());
         HTMap.put(h.getName(), 2);
      }
      while(file.hasNextLine())
      {
         String[] info = file.nextLine().split(",");
         if(info.length > 0 && info[0].equals("Lesson"))
               break;
         ArrayList<Integer> tList = new ArrayList<>();
         String[] tInts = info[3].split(";");
         for(String s : tInts)
         {
            if(s.charAt(0) == '"')
            {
               s = s.substring(1,s.length());
            }
            if(s.indexOf('-') != -1)
            {
               for(int i = Integer.parseInt(s.substring(0,s.indexOf('-'))); i <= Integer.parseInt(s.substring(s.indexOf('-')+1,s.length())); i++)
               {
                  tList.add(i);
               }
            }
            else
            {
               tList.add(Integer.parseInt(s.substring(0,s.length())));
            }
               
         }
         Horse tempHorse = new Horse("broke", 0, 0);
         for(Horse h : horses)
         {
            if(h.getName().equals(info[2]))
            {
               tempHorse = h;
            }
         }
         
         if(tempHorse.getName().equals("broke"))
         {
            System.out.println(info[2]);
         }
         
         Rider r = new Rider(info[0], Integer.parseInt(info[1]), tList, tempHorse, true);
         totalRiders++;
         students.add(r);
         LTMap.put(r,info[4]);
         reasons.put(r,new ArrayList<String>());
         if(info.length > 5)
         {
            soloMap.put(r,true);
         }
         else
         {
            soloMap.put(r,false);
         }
      }
 /******************************************************************************************/
//lessonHorses
      String[] lessonHorses = file.nextLine().split(",");
      Horse[] horses2 = new Horse[lessonHorses.length];
      for(int i = 0; i < horses2.length; i++)
      {
         horses2[i] = new Horse(lessonHorses[i], 3, 3);
      }
      
      for(Horse h : horses2)
      {
         usedTimes.put(h.getName(), new ArrayList<Integer>());
         HTMap.put(h.getName(), 3);
      }
      while(file.hasNextLine())
      {
         String[] info = file.nextLine().split(",");
         ArrayList<Integer> tList = new ArrayList<>();
         String[] tInts = info[3].split(";");
         for(String s : tInts)
         {
            if(s.charAt(0) == '"')
            {
               s = s.substring(1,s.length());
            }
            if(s.indexOf('-') != -1)
            {
               for(int i = Integer.parseInt(s.substring(0,s.indexOf('-'))); i <= Integer.parseInt(s.substring(s.indexOf('-')+1,s.length())); i++)
               {
                  tList.add(i);
               }
            }
            else
            {
               tList.add(Integer.parseInt(s.substring(0,s.length())));
            }
               
         }
         Horse tempHorse = new Horse("broke", 0, 0);
         for(Horse h : horses2)
         {
            if(h.getName().equals(info[2]))
            {
               tempHorse = h;
            }
         }
         
         if(tempHorse.getName().equals("broke"))
         {
            System.out.println(info[2]);
         }
         
         Rider r = new Rider(info[0], Integer.parseInt(info[1]), tList, tempHorse, true);
         totalRiders++;
         students.add(r);
         LTMap.put(r,info[4]);
         reasons.put(r,new ArrayList<String>());
         HTMap.put(r.getHorse().getName(), r.getHorse().getType());
         if(info.length > 5)
         {
            soloMap.put(r,true);
         }
         else
         {
            soloMap.put(r,false);
         }
      }      
      

  /***************************************************************************/     
       
       ArrayList<Instructor> instructors = new ArrayList<>();
       ArrayList<Integer> hours4 = new ArrayList<>();
       ArrayList<Integer> hours2 = new ArrayList<>();
        hours2.add(15);
        hours2.add(16);
        hours2.add(17);
        ArrayList<Integer> hours3 = new ArrayList<>();
        hours3.add(11);
        hours3.add(16);
        for(Integer i : hours)
        {
         hours4.add(i);
        }
        
       instructors.add(new Instructor("Taylor", 1,hours3));
       instructors.add(new Instructor("Darragh", 1,hours2));
       instructors.add(new Instructor("Siobhan", 10,hours));
       instructors.add(new Instructor("Shelley", 10,hours4));                               //create instructor objects
       
       ArrayList<Rider> allStudents = students;
       ArrayList<Rider> lowChoice = new ArrayList<>();
       Day day = new Day(1, 6);
       for(int t = open; t < close; t++)  //add instructors to blocks when available
         {
            Block b = new Block(t);
            for(Instructor i : instructors)
            {
               if(i.isAvailable(t))
               {
                  b.addInstructor(i);
                  i.addLesson(new Lesson(t), t);
               }
                  
            }
            day.addBlock(b,t);
         }
         
         
         Map<String,Integer> LTCount = new TreeMap<>();  //Lesson Type reason map
         int LTTotal = 0;
         for(String s : LTMap.values())
         {
            if(LTCount.get(s) == null)
            {
               LTCount.put(s,0);
            }
            LTCount.put(s,LTCount.get(s) + 1);
            LTTotal++;
         }
         
         for(Rider r : students)
         {
            String type = LTMap.get(r);
            if(LTCount.get(type) * 1.0 / LTTotal < .4)   //percentage of lesson type
               reasons.get(r).add(oddLessonType);
            
         }
         
         for(Rider r : students)       //lowTime reason add
         {
            if(r.getTimes().size() < 4)
            {
               reasons.get(r).add(lowTime);
            }
         }
         
        //  for(Block b : day.getBlocks())                                 //test blocks' instructor lists
//          {
//             System.out.print(b.getTime() + " ");
//             for(Instructor i : b.getInstructors())
//             {
//                System.out.print(i.getName() + " ");
//             }
//             System.out.println();
//             
//          }
         System.out.println("(1) for level sort \n(2) for time sort");  //choose how to sort riders
         String choice = "";
         while(!choice.equals("1") && !choice.equals("2"))
         {
            choice = kb.nextLine();
            if(choice.equals("1"))
               students = sortByLevel(students);
            else if(choice.equals("2"))
               students = sortByTime(students);
            else
               System.out.println("try again...");
         }
         System.out.print("Sorted by ");
         if(choice.equals("1"))
            System.out.println("level");
         if(choice.equals("2"))
            System.out.println("time");
         
         
         String iName = "";
         String rName = "";
         for(Rider r : students)       //low available riders
         {
            if(r.getTimes().size() == 1)
            {
               rName =r.getName();
               if(r.getTimes().get(0) >= open && r.getTimes().get(0) < close)
               for(Instructor i : day.getBlocks().get(r.getTimes().get(0)).getInstructors())
               {
                  iName = i.getName();
                //  System.out.println("loop");
                  if(i.canTeach(r,r.getTimes().get(0), LTMap.get(r)) && r.getHorse().canUse(usedTimes, r.getTimes().get(0)))
                  {
                     if(!soloMap.get(r))
                     {
                      //  System.out.println("adding...");
                        i.addStudent(r,r.getTimes().get(0),LTMap.get(r));
                        if(r.isBorrowing())
                        {
                           usedTimes.get(r.getHorse().getName()).add(r.getTimes().get(0));
                        }
                        else
                        {
                           r.getHorse().addUsedTime(r.getTimes().get(0));
                        }
                        
                        r.setLesson(true);
                        break;
                     }
                     else
                     {
                      //  System.out.println("adding...");
                        i.addSoloStudent(r,r.getTimes().get(0),LTMap.get(r));
                        if(r.isBorrowing())
                        {
                           usedTimes.get(r.getHorse().getName()).add(r.getTimes().get(0));
                        }
                        else
                        {
                           r.getHorse().addUsedTime(r.getTimes().get(0));
                        }
                        r.setLesson(true);
                        break;
                     }
                  }
               }
            }
         }
         
         day.printSchedule();
         
         ArrayList<Rider> withLessons = new ArrayList<>();
         for(int i = students.size()-1; i >=0; i--)         //remove students with lessons
         {
            if(students.get(i).hasLesson())
            {
               withLessons.add(students.remove(i));
            }
         }
         
         
         
         for(Rider r : students)       //main addition to schedule
         {
            for(Integer t : r.getTimes())
            {
               rName =r.getName();
               if(t >= open && t < close)
                  for(Instructor i : day.getBlocks().get(t).getInstructors())
                  {
                     iName = i.getName();
                   //  System.out.println("loop");
                     if(i.canTeach(r,t,LTMap.get(r)) && r.getHorse().canUse(usedTimes,t))
                     {
                        if(!soloMap.get(r))
                        {
                         //  System.out.println("adding...");
                           i.addStudent(r,t,LTMap.get(r));
                           if(r.isBorrowing())
                           {
                              usedTimes.get(r.getHorse().getName()).add(t);
                           }
                           else
                           {
                              r.getHorse().addUsedTime(t);
                           }
                           r.setLesson(true);
                           break;
                           
                        }
                        else
                        {
                         //  System.out.println("adding...");
                           i.addSoloStudent(r,t,LTMap.get(r));
                           if(r.isBorrowing())
                           {
                              usedTimes.get(r.getHorse().getName()).add(t);
                           }
                           else
                           {
                              r.getHorse().addUsedTime(t);
                           }
                           r.setLesson(true);
                           break;
                           
                        }
                     }
                  }
                  if(r.hasLesson())
                  {
                     break;
                  }
               
            }
         }
         
         
         
         for(int i = students.size()-1; i >=0; i--)         //remove students with lessons
         {
            if(students.get(i).hasLesson())
            {
               withLessons.add(students.remove(i));
            }
         }
 
         System.out.println();
         day.printSchedule();
 
         for(Rider r : students)
         {
            System.out.println(r.getName() + " didn't fit");
         }
      
/**********************************************************************************************/

   //Start tweaking to fit last few
      for(Rider r : students)       //different lesson horses
         {
            String hName = "";
            Horse save = r.getHorse();
            Horse temp = new Horse("broke", 0, 0);
            ArrayList<String> badHorses = new ArrayList<>();
            badHorses.add(r.getHorse().getName());
            if(r.isBorrowing())
            {
               for(Integer t : r.getTimes())
               {
                  rName =r.getName();
                  if(t >= open && t < close)
                     for(Instructor i : day.getBlocks().get(t).getInstructors())
                     {
                        iName = i.getName();
                      //  System.out.println("loop");
                        temp = new Horse(getBestHorse(usedTimes, HTMap, r.getHorse().getType(), badHorses), r.getLevel(), r.getHorse().getType());
                        hName = temp.getName();
                        if(temp.getName().equals("broke"))
                        {
                           break;
                        }
                        else
                        {
                           badHorses.add(temp.getName());
                           r.setHorse(temp);
                        }
                        if(i.canTeach(r,t,LTMap.get(r)) && r.getHorse().canUse(usedTimes,t))
                        {
                           if(!soloMap.get(r))
                           {
                            //  System.out.println("adding...");
                              i.addStudent(r,t,LTMap.get(r));
                              if(r.isBorrowing())
                              {
                                 usedTimes.get(r.getHorse().getName()).add(t);
                              }
                              else
                              {
                                 r.getHorse().addUsedTime(t);
                              }
                              r.setLesson(true);
                              break;
                              
                           }
                           else
                           {
                            //  System.out.println("adding...");
                              i.addSoloStudent(r,t,LTMap.get(r));
                              if(r.isBorrowing())
                              {
                                 usedTimes.get(r.getHorse().getName()).add(t);
                              }
                              else
                              {
                                 r.getHorse().addUsedTime(t);
                              }
                              r.setLesson(true);
                              break;
                              
                           }
                        }
                     }
                     if(r.hasLesson())
                     {
                        break;
                     }
                     else
                     {
                        r.setHorse(save);
                     }
                  
               }
            }
         }
      
         for(int i = students.size()-1; i >=0; i--)         //remove students with lessons
         {
            if(students.get(i).hasLesson())
            {
               withLessons.add(students.remove(i));
            }
         }
 
         System.out.println();
         day.printSchedule();
 
         for(Rider r : students)
         {
            System.out.println(r.getName() + " didn't fit");
         }
 
 
 
         /* Things to try:
         1. Go to lower level to try to fit
         2. See if can swap with somebody
         3. See if Swap, then can fit somebody with lower level
         */
         
         
         
         
        // for(Rider r : students)       //trying to do a one degree swap
//          {
//             for(Integer t : r.getTimes())
//             {
//                rName =r.getName();
//                if(t >= open && t < close)
//                   for(Instructor i : day.getBlocks().get(t).getInstructors())
//                   {
//                      iName = i.getName();
//                    //  System.out.println("loop");
//                      if(i.canTeach(r,t,LTMap.get(r)) && r.getHorse().canUse(usedTimes,t))
//                      {
//                         if(!soloMap.get(r))
//                         {
//                          //  System.out.println("adding...");
//                            i.addStudent(r,t,LTMap.get(r));
//                            if(r.isBorrowing())
//                            {
//                               usedTimes.get(r.getHorse().getName()).add(t);
//                            }
//                            else
//                            {
//                               r.getHorse().addUsedTime(t);
//                            }
//                            r.setLesson(true);
//                            break;
//                            
//                         }
//                         else
//                         {
//                          //  System.out.println("adding...");
//                            i.addSoloStudent(r,t,LTMap.get(r));
//                            if(r.isBorrowing())
//                            {
//                               usedTimes.get(r.getHorse().getName()).add(t);
//                            }
//                            else
//                            {
//                               r.getHorse().addUsedTime(t);
//                            }
//                            r.setLesson(true);
//                            break;
//                            
//                         }
//                      }
//                   }
//                   if(r.hasLesson())
//                   {
//                      break;
//                   }
//                
//             }
//          } 
//          
//          
//          
//          
//          
//         for(int i = students.size()-1; i >=0; i--)         //remove students with lessons
//          {
//             if(students.get(i).hasLesson())
//             {
//                withLessons.add(students.remove(i));
//             }
//          }
//  
//          System.out.println();
//          day.printSchedule();
//  
//          for(Rider r : students)
//          {
//             System.out.println(r.getName() + " didn't fit");
//          } 
         
         
         
         
         
         
         
         
         for(Rider r : students)       //trying with lower level
         {
            r = new Rider(r.getName(), r.getLevel()-1, r.getTimes(), r.getHorse(), r.isBorrowing());
            for(Integer t : r.getTimes())
            {
               rName =r.getName();
               if(t >= open && t < close)
                  for(Instructor i : day.getBlocks().get(t).getInstructors())
                  {
                     iName = i.getName();
                   //  System.out.println("loop");
                     if(i.canTeach(r,t,LTMap.get(r)) && r.getHorse().canUse(usedTimes,t))
                     {
                        if(!soloMap.get(r))
                        {
                         //  System.out.println("adding...");
                           i.addStudent(r,t,LTMap.get(r));
                           if(r.isBorrowing())
                           {
                              usedTimes.get(r.getHorse().getName()).add(t);
                           }
                           else
                           {
                              r.getHorse().addUsedTime(t);
                           }
                           r.setLesson(true);
                           break;
                           
                        }
                        else
                        {
                         //  System.out.println("adding...");
                           i.addSoloStudent(r,t,LTMap.get(r));
                           if(r.isBorrowing())
                           {
                              usedTimes.get(r.getHorse().getName()).add(t);
                           }
                           else
                           {
                              r.getHorse().addUsedTime(t);
                           }
                           r.setLesson(true);
                           break;
                           
                        }
                     }
                  }
                  if(r.hasLesson())
                  {
                     break;
                  }
               
            }
         }
         
         
         
         for(int i = students.size()-1; i >=0; i--)         //remove students with lessons
         {
            if(students.get(i).hasLesson())
            {
               withLessons.add(students.remove(i));
            }
         }
 
         System.out.println();
         day.printSchedule();
         
 
         for(Rider r : students)
         {
            System.out.print(r.getName() + " didn't fit because: ");
            printReasons(reasons.get(r));
         }
         
         
         
         for(Rider r : students)    //ask if want to put lesson outside normal hours
         {
            ArrayList<Integer> outTimes = new ArrayList<>();
            boolean firstTime = true;
            boolean possIns = false;
            for(Integer t : r.getTimes())
            {
               if(t < open || t >= close)
               {
                  if(firstTime)
                  {
                     System.out.print(r.getName() + " is available outside of usual hours at: ");
                     firstTime = false;
                  }
                  System.out.print(t + ":00 ");
                  outTimes.add(t);
               }
            }
            if(firstTime == false)
            {
               System.out.println();
               System.out.println("Would you like to place " + r.getName() + " in one of these times?");
               System.out.println("(1) Yes\n(2) No");
               int choose = 0;
               choice = "";
               while(!choice.equals("1") && !choice.equals("2"))
               {
                  choice = kb.nextLine();
                  if(choice.equals("1"))
                     choose = 1;
                  else if(choice.equals("2"))
                     choose = 2;
                  else
                     System.out.println("try again...");
               }
               if(choose == 1)
               {
                  System.out.print("Please select a time: ");
                  for(Integer i : outTimes)
                  {
                     if(r.getHorse().canUse(usedTimes, i))
                     {
                        System.out.print(i + " ");
                     }
                  }
                  System.out.println();
                  
                  choice = "";
                  boolean gotNum = false;
                  while(!gotNum)
                  {
                     choice = kb.nextLine();
                     try
                     {
                        choose = Integer.parseInt(choice);
                        if(outTimes.contains(choose))
                        {
                           gotNum = true;
                        }
                        else
                        {
                           System.out.println("try again...");
                        }
                     }
                     catch(Exception E)
                     {
                        System.out.println("try again...");
                     }
                  }
                     
                        
                  
                  
                  if(day.getBlocks().get(choose) == null)
                  {
                     day.addBlock(new Block(choose), choose);
                  }
                  
                  for(Instructor i : instructors)
                  {
                     if(i.getTimes().contains(choose) && i.canTeach(r,choose, LTMap.get(r)))
                     {
                        possIns = true;
                     }
                  }
                  if(possIns == false)
                  {
                     int yn = 0;
                     System.out.println("No instructors are marked as available");
                     System.out.println("Please select an instructor for " + r.getName());
                     Map<Integer, Instructor> iMap = new TreeMap<>();
                     int num = 1;
                     for(Instructor i : instructors)
                     {
                        if(i.getLessons().get(choose) == null || !i.getLessons().get(choose).isSoloLesson())
                        {
                           System.out.println("(" + num +") " + i.getName());
                           iMap.put(num,i);
                           num++;
                        }
                     }
                     
                     choice = "";
                     gotNum = false;
                     while(!gotNum)
                     {
                        choice = kb.nextLine();
                        try
                        {
                           yn = Integer.parseInt(choice);
                           if(outTimes.contains(choose))
                           {
                              gotNum = true;
                           }
                        }
                        catch(Exception E)
                        {
                           System.out.println("try again");
                        }
                     }
                     
                     iMap.get(yn).addTime(choose);
                     iMap.get(yn).addLesson(new Lesson(choose), choose);
                     if(!day.getBlocks().get(choose).getInstructors().contains(iMap.get(yn)))
                     {
                        day.getBlocks().get(choose).addInstructor(iMap.get(yn));
                     }
                     System.out.println("Instructor Assigned");
                  }
                  for(Instructor i : day.getBlocks().get(choose).getInstructors())
                  {
                     iName = i.getName();
                   //  System.out.println("loop");
                     if(i.canTeach(r,choose, LTMap.get(r)) && r.getHorse().canUse(usedTimes, choose))
                     {
                        if(!soloMap.get(r))
                        {
                         //  System.out.println("adding...");
                           i.addStudent(r,choose,LTMap.get(r));
                           System.out.println("Student Added");
                           if(r.isBorrowing())
                           {
                              usedTimes.get(r.getHorse().getName()).add(choose);
                           }
                           else
                           {
                              r.getHorse().addUsedTime(choose);
                           }
                           
                           r.setLesson(true);
                           break;
                        }
                        else
                        {
                         //  System.out.println("adding...");
                           i.addSoloStudent(r,choose,LTMap.get(r));
                           i.getLessons().get(choose).setLessonSolo(true);
                           System.out.println("Student Added");
                           if(r.isBorrowing())
                           {
                              usedTimes.get(r.getHorse().getName()).add(choose);
                           }
                           else
                           {
                              r.getHorse().addUsedTime(choose);
                           }
                           r.setLesson(true);
                           break;
                        }
                     }
                  }
                  
                  /****************************************/
                  if(!r.hasLesson())   //trying new lesson horse if borrowing
                  {
                     String hName = "";
                  Horse save = r.getHorse();
                  Horse temp = new Horse("broke", 0, 0);
                  ArrayList<String> badHorses = new ArrayList<>();
                  badHorses.add(r.getHorse().getName());
                  int t = choose;
                  if(r.isBorrowing())
                  {
                     
                        rName =r.getName();
                        
                           for(Instructor i : day.getBlocks().get(choose).getInstructors())
                           {
                              iName = i.getName();
                            //  System.out.println("loop");
                              temp = new Horse(getBestHorse(usedTimes, HTMap, r.getHorse().getType(), badHorses), r.getLevel(), r.getHorse().getType());
                              hName = temp.getName();
                              if(temp.getName().equals("broke"))
                              {
                                 break;
                              }
                              else
                              {
                                 badHorses.add(temp.getName());
                                 r.setHorse(temp);
                              }
                              if(i.canTeach(r,t,LTMap.get(r)) && r.getHorse().canUse(usedTimes,t))
                              {
                                 if(!soloMap.get(r))
                                 {
                                  //  System.out.println("adding...");
                                    i.addStudent(r,choose,LTMap.get(r));
                                    if(r.isBorrowing())
                                    {
                                       usedTimes.get(r.getHorse().getName()).add(choose);
                                    }
                                    else
                                    {
                                       r.getHorse().addUsedTime(choose);
                                    }
                                    r.setLesson(true);
                                    break;
                                    
                                 }
                                 else
                                 {
                                  //  System.out.println("adding...");
                                    i.addSoloStudent(r,t,LTMap.get(r));
                                    if(r.isBorrowing())
                                    {
                                       usedTimes.get(r.getHorse().getName()).add(t);
                                    }
                                    else
                                    {
                                       r.getHorse().addUsedTime(t);
                                    }
                                    r.setLesson(true);
                                    break;
                                    
                                 }
                              }
                           }
                           if(r.hasLesson())
                           {
                              break;
                           }
                           else
                           {
                              r.setHorse(save);
                           }
                        
                     
                  }
                  }
                  /**********************************/
                  
               }
            }
            
         }
         
         
         for(int i = students.size()-1; i >=0; i--)         //remove students with lessons
         {
            if(students.get(i).hasLesson())
            {
               withLessons.add(students.remove(i));
            }
         }
 
         System.out.println();
         day.printSchedule();
         
 
         for(Rider r : students)
         {
            System.out.print(r.getName() + " didn't fit because: ");
            printReasons(reasons.get(r));
         }
         
         
         
         
         //recursive swaps until everybody has a spot
         
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
      day.createSchedule();
 
 
 
 
 
 
 
 
 
 
 
 
  // /************************************************************************************/  
//       
//       System.out.println("Determining Priority...");
//       for(int i = allStudents.size()-1; i >= 0; i--)  //if high level
//       {
//          if(allStudents.get(i).getLevel() > 3)
//          {
//             lowChoice.add(allStudents.get(i));
//             allStudents.remove(i);
//          }
//       }
//       for(int i = allStudents.size()-1; i >= 0; i--)  //if small time window
//       {
//          if(allStudents.get(i).getTimeArray().size() < 3)
//          {
//             lowChoice.add(allStudents.get(i));
//             allStudents.remove(i);
//          }
//       }
//       
//       System.out.println("Finding possible permutations...");
//       ArrayList<ArrayList<Rider>> allLists = new ArrayList<>();
//       makeLists(students, new ArrayList<Rider>(), allLists);
//       for(int i = 0; i < allLists.size(); i++)                    //add priority to front of permutations
//       {
//          ArrayList<Rider> fList = new ArrayList<>();
//          fList.addAll(lowChoice);
//          fList.addAll(allLists.get(i));
//          allLists.remove(i);
//          allLists.add(i,fList);
//       }
//  //      for(ArrayList<Rider> a : allLists)     //print every perm (priority is constant)
// //       {
// //          for(Rider r : a)
// //          {
// //             System.out.print(r + " ");
// //          }
// //          System.out.println();
// //       }
//        
//       System.out.println("Calculated Schedule...");
//       
//       for(ArrayList<Rider> currList : allLists)
//       {
//       students = currList;
//  //      students = sList;
//       Map<Integer, Map<Rider,Map<Integer,Integer>>> lessonBlocks = new TreeMap<>();  // Map<TIME, Map<INSTRUCTOR, Map<LEVEL, GROUPSIZE>>>
//       Map<Integer,ArrayList<Block>> blockMap = new TreeMap<>();   //Map<Time, Blocks>
//        for(int i = 0; i <= 2; i++)
//        {
//          for(int time = open; time < close; time++)  //for every hour of business
//          {
//             Rider temp = instructors.get(instructors.size()-1);
//             instructors.set(instructors.size()-1,instructors.get(instructors.size()-2));
//             instructors.set(instructors.size()-2,temp);
// 
//             ArrayList<Block> blockList = new ArrayList<>();
//             ArrayList<Rider> possInstructors = getPossIns(instructors, time);
//             for(Rider student : students)                //for every student that needs a lesson
//             {
//                ArrayList<Integer> possStudTimes = student.getTimeArray();
//                int studentLevel = student.getLevel() - i;
//                if(i > 0 && studentLevel <= 1)
//                   studentLevel++;
//                Rider finalInstructor = null;
//                ArrayList<Rider> availableInstructors = new ArrayList<>();
//                boolean found = false;
//                boolean typeFound = false;
//                int inSpot = 0;
//                int LSize = 0;
//                String studName = student.getName();   //optional
//                String insName;   //optional
//                int horseType; //optional
//                if(!hasLesson(student, blockMap))  // if Student doesnt already has lesson time
//                {
//                   if(possStudTimes.contains(time))
//                   {
//                      finalInstructor = getBestInstructor(possInstructors, time, studentLevel, lessonBlocks);
//                      
//                      if(finalInstructor != null)
//                      {
//                         insName = finalInstructor.getName();
//                         if(blockMap.get(time) != null)
//                            {
//                               typeFound = blockMap.get(time).get(0).getLessonType().equals(LTypes.get(student));
//                            }
//                            horseType = student.getHorse().getType();
//                         if(!hasLesson(student, blockMap) && student.getHorse().getType() == 1 && typeFound)   //if does not need lesson horse       
//                         {
//                            blockList.add(new Block(student, student.getHorse(), LTypes.get(student), time, finalInstructor));
//                            LSize = lessonBlocks.get(time).get(finalInstructor).get(studentLevel);
//                            lessonBlocks.get(time).get(finalInstructor).put(studentLevel,LSize + 1);
//                         }
//                         else if(!hasLesson(student, blockMap) && student.getHorse().getType() == 2 && typeFound) //if needs leased horse
//                         {
//                               if(canRide(student.getLeasedHorse(), time, leasedMap))
//                               {
//                                  blockList.add(new Block(student, student.getHorse(), LTypes.get(student), time, finalInstructor));
//                                  LSize = lessonBlocks.get(time).get(finalInstructor).get(studentLevel);
//                                  lessonBlocks.get(time).get(finalInstructor).put(studentLevel,LSize + 1);
//                                  for(LessonHorse h : leasedMap.keySet())
//                                  {
//                                     if(h.getName().equals(student.getLeasedHorse().getName()))
//                                     {
//                                        h.addTime(time);
//                                     }
//                                  }
//                               }
//                         }
//                         else if(!hasLesson(student, blockMap) && typeFound) //lesson horse
//                         {
//           //                  while(!found)
// //                            {
//                                  if(canRide(student.getLeasedHorse(), time, priority))
//                                  {
//                                     blockList.add(new Block(student, student.getHorse(), LTypes.get(student), time, finalInstructor));
//                                     LSize = lessonBlocks.get(time).get(finalInstructor).get(studentLevel);
//                                     lessonBlocks.get(time).get(finalInstructor).put(studentLevel,LSize + 1);
//                                     for(LessonHorse h : priority.keySet())
//                                     {
//                                        if(h.getName().equals(student.getLeasedHorse().getName()))
//                                        {
//                                           h.addTime(time);
//                                        }
//                                     }
//                                  }
// //                                  else
// //                                  {
// //                                     ArrayList<LessonHorse> bestChoices = getNextBest(priority, lessonHorses, student.getLeasedHorse());
// //                                     priority.get(student.getLeasedHorse()).remove(student);
// //                                     student.setHorse(bestChoices.get(0));
// //                                  }
// //                                  
// //                            }
//                         }
//                      }
//                      else
//                      {
//                         availableInstructors = getAvailableInstructors(possInstructors, time, studentLevel, lessonBlocks);
//                         if(availableInstructors.size() == 0)
//                         {
//                            System.out.println("No instructors available for " + student + " at " + time);
//                         }
//                         else
//                         {
//                            while(!found && inSpot < availableInstructors.size())
//                            {
//                               insName = availableInstructors.get(inSpot).getName();
//                               if(lessonBlocks.get(time) != null)
//                               {
//                                  if(lessonBlocks.get(time).get(availableInstructors.get(inSpot)) == null)
//                                  {
//                                     horseType = student.getHorse().getType();
//                                     if(!hasLesson(student, blockMap) && student.getHorse().getType() == 1)   //if does not need lesson horse
//                                     {
//                                        blockList.add(new Block(student, student.getHorse(), LTypes.get(student), time, availableInstructors.get(inSpot)));
//                                        found = true;
//                                        lessonBlocks.get(time).put(availableInstructors.get(inSpot), new TreeMap<Integer,Integer>());
//                                        lessonBlocks.get(time).get(availableInstructors.get(inSpot)).put(studentLevel,1);
//                                     }
//                                     else if(!hasLesson(student, blockMap) && student.getHorse().getType() == 2) //if needs leased horse
//                                     {
//                                        if(canRide(student.getLeasedHorse(), time, leasedMap))
//                                        {                                       
//                                           blockList.add(new Block(student, student.getHorse(), LTypes.get(student), time, availableInstructors.get(inSpot)));
//                                           found = true;
//                                           lessonBlocks.get(time).put(availableInstructors.get(inSpot), new TreeMap<Integer,Integer>());
//                                           lessonBlocks.get(time).get(availableInstructors.get(inSpot)).put(studentLevel,1);
//                                           for(LessonHorse h : priority.keySet())
//                                           {
//                                              if(h.getName().equals(student.getLeasedHorse().getName()))
//                                              {
//                                                 h.addTime(time);
//                                              }
//                                           }
//                                        }
//                                    }
//                                    else if(!hasLesson(student, blockMap) && typeFound) //lesson horse
//                                    {
//                                      //   while(!found)
// //                                        {
//                                           if(canRide(student.getLeasedHorse(), time, priority))
//                                           {                                       
//                                              blockList.add(new Block(student, student.getHorse(), LTypes.get(student), time, availableInstructors.get(inSpot)));
//                                              found = true;
//                                              lessonBlocks.get(time).put(availableInstructors.get(inSpot), new TreeMap<Integer,Integer>());
//                                              lessonBlocks.get(time).get(availableInstructors.get(inSpot)).put(studentLevel,1);
//                                              for(LessonHorse h : priority.keySet())
//                                              {
//                                                 if(h.getName().equals(student.getLeasedHorse().getName()))
//                                                 {
//                                                    h.addTime(time);
//                                                 }
//                                              }
//                                           }
// //                                           else
// //                                           {  
// //                                                 System.out.println("Finding alt lesson horse...");
// //                                                 ArrayList<LessonHorse> bestChoices = getNextBest(priority, lessonHorses, student.getLeasedHorse());
// //                                                 priority.get(student.getLeasedHorse()).remove(student);
// //                                                 student.setHorse(bestChoices.get(0));
// //                                           }
//                                           
// //                                        }
//                                        
//                                    }
//                                  }
//                               }
//                               else
//                               {
//                                  lessonBlocks.put(time, new TreeMap<Rider,Map<Integer,Integer>>());
//                                  horseType = student.getHorse().getType();
//                                  if(!hasLesson(student, blockMap) && student.getHorse().getType() == 1)   //if does not need lesson horse
//                                     {
//                                        blockList.add(new Block(student, student.getHorse(), LTypes.get(student), time, availableInstructors.get(inSpot)));
//                                        found = true;
//                                        lessonBlocks.get(time).put(availableInstructors.get(inSpot), new TreeMap<Integer,Integer>());
//                                        lessonBlocks.get(time).get(availableInstructors.get(inSpot)).put(studentLevel,1);
//                                     }
//                                     else if(!hasLesson(student, blockMap) && student.getHorse().getType() == 2) //if needs leased horse
//                                     {
//                                           if(canRide(student.getLeasedHorse(), time, leasedMap))
//                                           {
//                                              blockList.add(new Block(student, student.getHorse(), LTypes.get(student), time, availableInstructors.get(inSpot)));
//                                              lessonBlocks.get(time).put(availableInstructors.get(inSpot), new TreeMap<Integer,Integer>());
//                                              lessonBlocks.get(time).get(availableInstructors.get(inSpot)).put(studentLevel,1);
//                                              for(LessonHorse h : leasedMap.keySet())
//                                              {
//                                                 if(h.getName().equals(student.getLeasedHorse().getName()))
//                                                 {
//                                                    h.addTime(time);
//                                                 }
//                                              }
//                                              found = true;
//                                           }
//                                    }
//                                    else if(!hasLesson(student, blockMap) && typeFound) //lesson horse
//                                    {
// //                                        while(!found)
// //                                        {
//                                           if(canRide(student.getLeasedHorse(), time, priority))
//                                              {
//                                                 blockList.add(new Block(student, student.getHorse(), LTypes.get(student), time, availableInstructors.get(inSpot)));
//                                                 lessonBlocks.get(time).put(availableInstructors.get(inSpot), new TreeMap<Integer,Integer>());
//                                                 lessonBlocks.get(time).get(availableInstructors.get(inSpot)).put(studentLevel,1);
//                                                 for(LessonHorse h : priority.keySet())
//                                                 {
//                                                    if(h.getName().equals(student.getLeasedHorse().getName()))
//                                                    {
//                                                       h.addTime(time);
//                                                    }
//                                                 }
//                                                 found = true;
//                                              }
//                                              else
//                                              {
//                                                 System.out.println("Finding alt lesson horse...");
//                                                 ArrayList<LessonHorse> bestChoices = getNextBest(priority, lessonHorses, student.getLeasedHorse());
//                                                 priority.get(student.getLeasedHorse()).remove(student);
//                                                 student.setHorse(bestChoices.get(0));
//                                                 
//                                              }
//                           //             }
//                                           
//                                    }
//                               }
//                               inSpot++;
//                            }
//                         }
//                         
//                      }
//                    }
//                 }
//                 
//             }
//             if(blockMap.get(time) == null)
//                blockMap.put(time,blockList);
//             else
//             {
//                for(Block b : blockList)
//                {
//                   blockMap.get(time).add(b);
//                }
//             }
//          }
//          for(int a = students.size()-1; a >= 0; a--)
//          {
//             if(hasLesson(students.get(a), blockMap))
//               students.remove(a);
//          }
//       }
//       
//     
//       
//       Day d1 = new Day(3,16,blockMap);
//        for(int i = open; i < close; i++)
//          System.out.println(blockMap.get(i));     
//        if(students.isEmpty())
//        {
//          System.out.println("full schedule found");
//          break;
//        }
//          for(Rider r : students)
//          {
//             System.out.println(r.getName() + " did not fit into the schedule");
//          }
// }
// 
// 
// 
// 
// 
// 
// 
//       //for every person that still doesn't have a lesson, remove from schedule and redo sort in a new permutations until fixed. (Save best version)
//     //   boolean triedEvery = false;
// //       ArrayList<Rider> duds = students;
// //       while(!duds.isEmpty() && !triedEvery)
// //       {
// //          for(Rider test : duds)
// //          {
// //             
// //          }
// //          
// //          switch()
// //          
// //       }      
//       
//       
//       
//         
// //  /*********************************************************************************************************************************************/
// //          
// // 
//          
//          
//          
//          
//          
//          
//    //       
// //       blockMap = new TreeMap<>();
// //       ArrayList<Day> possSchedules = getPossSchedules(allStudents, instructors, blockMap);   
// //          
//          
//          
//          
//          
//          
//          
//          
//          
//          
//          
//          
//          
//          
//          
//          
//          
//          
//              
//    }
//    
//    // static void printPermutn(String str, String ans) //For reference
// //     { 
// //   
// //         // If string is empty 
// //         if (str.length() == 0) { 
// //             System.out.print(ans + " "); 
// //             return; 
// //         } 
// //   
// //         for (int i = 0; i < str.length(); i++) { 
// //   
// //             // ith character of str 
// //             char ch = str.charAt(i); 
// //   
// //             // Rest of the string after excluding  
// //             // the ith character 
// //             String ros = str.substring(0, i) +  
// //                          str.substring(i + 1); 
// //   
// //             // Recurvise call 
// //             printPermutn(ros, ans + ch); 
// //         } 
// //     }
//    
// //    public static ArrayList<Block> getPossSchedules(ArrayList<Rider> students, Rider[] instructors)
// //    {
// //       if(students.size() == 0)
// //       {
// //          return;
// //       }
// //       
// //       for(int i = 8; i <= 18; i++)
// //       {
// //          for(Rider r : students)
// //          {
// //             
// //          }
// //       }
// //    }
//    
//    
//    public static void makeLists(ArrayList<Rider> riders, ArrayList<Rider> endList, ArrayList<ArrayList<Rider>> fullList)
//    {
//       ArrayList<Rider> localRiders = new ArrayList<>();
//       for(Rider r : riders)
//       {
//          localRiders.add(r);
//       }
//       ArrayList<Rider> temp = new ArrayList<>();
//       for(Rider r : localRiders)
//       {
//          temp.add(r);
//       }
//       ArrayList<Rider> baseList = new ArrayList<>();
//       for(Rider r : endList)
//       {
//          baseList.add(r);
//       }
//       if(riders.size() == 0)
//       {
//          fullList.add(endList);
//         //  for(Rider r : endList)
// //          {
// //             System.out.print(r + " ");
// //          }
// //          System.out.println();
//          return;
//       }
//       for(int i = 0; i < localRiders.size(); i++)
//       {
//          endList = baseList;
//          temp = localRiders;
//          endList.add(temp.remove(i));
//          makeLists(temp,endList,fullList);
//       }
//    }
//       
//    public static ArrayList<Rider> getPossIns(ArrayList<Rider> instructors, int time)
//    {
//       ArrayList<Rider> possIns = new ArrayList<>();
//       for(Rider i : instructors)//for every instructor
//                {
//                      for(int t : i.getTimeArray()) 
//                      { 
//                         if(t == time)    //if instructor is available at specific time
//                         {
//                            possIns.add(i);
//                         }
//                      }
//                    
//                }
//                return possIns;
//    }
//    
//    public static Rider getBestInstructor(ArrayList<Rider> ins, int time, int level, Map<Integer,Map<Rider,Map<Integer,Integer>>> blockMap)
//    {
//       Rider list = null;
//       for(Rider instructor : ins)
//       {
//          if(blockMap.get(time) != null)
//          {
//             if(blockMap.get(time).get(instructor) != null)
//             {
//                if(blockMap.get(time).get(instructor).get(level) != null && blockMap.get(time).get(instructor).get(level) < 8)
//                {
//                   list = instructor;
//                }
//                else if(blockMap.get(time).get(instructor).get(level) != null && blockMap.get(time).get(instructor).get(level) == 8)
//                {
//                   System.out.println("Level " + level + " lesson with " + instructor + " at " + time + ":00 is full");
//                }
//             }
//          }
//       }
//       return list;
//    }
//    
//    public static ArrayList<Rider> getAvailableInstructors(ArrayList<Rider> ins, int time, int level, Map<Integer,Map<Rider,Map<Integer,Integer>>> blockMap)
//    {
//       ArrayList<Rider> list = new ArrayList<>();
//       for(Rider instructor : ins)
//       {
//          if(blockMap.get(time) != null)
//          {
//             if(blockMap.get(time).get(instructor) != null)
//             {
//                if(blockMap.get(time).get(instructor).get(level) != null && blockMap.get(time).get(instructor).get(level) == 8)         //THIS METHOD IS NOT FUNCTIONING RIGHT
//                {
//                   System.out.println("Level " + level + " lesson with " + instructor + " at " + time + ":00 is full");
//                }
//                else
//                {
//                   if(blockMap.get(time).get(instructor).get(level) != null && blockMap.get(time).get(instructor).get(level) < 8)
//                   {
//                      list.add(instructor);
//                   }
//                }
//                
//             }
//             else
//             {
//                if(instructor.getLevel() >= level)
//                {
//                   list.add(instructor);
//                }
//             }
//          }
//          else
//          {
//             if(instructor.getLevel() >= level)
//             {
//                list.add(instructor);
//             }
//          }
//       }
//       return list;
//    }
//    
//    public static boolean canRide(LessonHorse test, int time, Map<LessonHorse, ArrayList<Rider>> horseMap)
//    {
//       ArrayList<Integer> times = new ArrayList<>();
//       for(LessonHorse h : horseMap.keySet())
//       {
//          if(h.getName().equals(test.getName()))
//          {
//             times = h.getUsedTimes();
//          }
//       }
//       if(times.size() < 2)
//       {
//          return true;
//       }
//       if(times.size() == 2)
//       {
//          if(times.get(1) - times.get(0) > 1 || time - times.get(1) > 1)
//          {
//             return true;
//          }
//       }
//       if(times.size() == 3)
//       {
//          if(times.get(2) - times.get(1) > 1 || time - times.get(2) > 1)
//          {
//             return true;
//          }
//       }
//       return false;
//    }
//    
//    public static boolean hasLesson(Rider r, Map<Integer,ArrayList<Block>> map)
//    {
//       for(ArrayList<Block> a : map.values())
//       {
//          for(Block b : a)
//          {
//             if(b.getRider().compareTo(r) == 0)
//             {
//                return true;
//             }
//          }
//       }
//       return false;
//    }
//    
//    public static ArrayList<LessonHorse> getNextBest(Map<LessonHorse, ArrayList<Rider>> priority, LessonHorse[] lessonHorses, LessonHorse original)
//    {
//       ArrayList<LessonHorse> poss = new ArrayList<>();
//       int size = Integer.MAX_VALUE;
//       for(LessonHorse h : lessonHorses)
//       {
//          if(!priority.containsKey(h))
//             poss.add(h);
//       }
//       if(!poss.isEmpty())
//          return poss;
//       for(LessonHorse h : priority.keySet())
//       {
//          if(priority.get(h).size() < size && !h.getName().equals(original.getName()))
//          {
//             poss = new ArrayList<>();
//             size = priority.get(h).size();
//             poss.add(h);
//          }
//          else if(priority.get(h).size() == size && !h.getName().equals(original.getName()))
//          {
//             size = priority.get(h).size();
//             poss.add(h);
//          }
//       }
//       return poss;
//    }

}
   public static String translateTime(double t)
   {
      return (t +":00");
   }
   
   public static String getBestHorse(Map<String, ArrayList<Integer>> horseMap, Map<String, Integer> typeMap, int type, ArrayList<String> usedHorses)
   {
      String answer = "broke";
      boolean found = false;
      
      
      for(int i = 0; i < 4; i++)
      {
         for(String s : horseMap.keySet())
         {
            String name = s;
            if(typeMap.get(s) == type && horseMap.get(s).size() == i && !usedHorses.contains(s))
            {
                  answer = s;
                  found = true;
                  break; 
               
            }
         }
         if(found)
            break;
      }
      return answer;
   }
   
   public static boolean couldReplace(Lesson l, ArrayList<Integer> times, String lt, int level)
   {
      if(l.getLessonSize() == 1)
      {
         return true;
      }
      else if(times.contains(l.getLessonTime()) && lt.equals(l.getLessonType()) && level == l.getLevel())
      {
         return true;
      }
      
      return false;
      
   }
    
   public static void printReasons(ArrayList<String> reasons)
   {
      if(reasons.size() == 0)
      {
         System.out.println("unknown reason");
         return;
      }
      for(int i = 0; i < reasons.size()-1; i++)
      {
         System.out.print(reasons.get(i) + " and ");
      }
      System.out.println(reasons.get(reasons.size()-1));
   }
   
   public static ArrayList<Rider> sortByLevel(ArrayList<Rider> list)
   {
      Rider[] riderArray = new Rider[list.size()];
      int[] levelArray = new int[list.size()];
      for(int i = 0; i < list.size(); i++)
      {
         riderArray[i] = list.get(i);
         levelArray[i] = list.get(i).getLevel();
      }
      
      int n = riderArray.length; 
        for (int i = 1; i < n; ++i) 
        { 
            int key = levelArray[i]; 
            Rider rKey = riderArray[i];
            int j = i - 1; 
  
            /* Move elements of arr[0..i-1], that are 
               greater than key, to one position ahead 
               of their current position */
            while (j >= 0 && levelArray[j] > key) 
            {  
                levelArray[j+1] = levelArray[j];
                riderArray[j+1] = riderArray[j];
                j = j - 1; 
            } 
            levelArray[j + 1] = key; 
            riderArray[j+1] = rKey;
        }
        ArrayList<Rider> out = new ArrayList<>();
        for(Rider r : riderArray)
        {
            out.add(r);
        }
        Collections.reverse(out);
        return out;
   }
   
   public static ArrayList<Rider> sortByTime(ArrayList<Rider> list)
   {
      Rider[] riderArray = new Rider[list.size()];
      int[] timeArray = new int[list.size()];
      for(int i = 0; i < list.size(); i++)
      {
         riderArray[i] = list.get(i);
         timeArray[i] = list.get(i).getTimes().size();
      }
      
      int n = riderArray.length; 
        for (int i = 1; i < n; ++i) 
        { 
            int key = timeArray[i]; 
            Rider rKey = riderArray[i];
            int j = i - 1; 
  
            /* Move elements of arr[0..i-1], that are 
               greater than key, to one position ahead 
               of their current position */
            while (j >= 0 && timeArray[j] > key) 
            {  
                timeArray[j+1] = timeArray[j];
                riderArray[j+1] = riderArray[j];
                j = j - 1; 
            } 
            timeArray[j + 1] = key; 
            riderArray[j+1] = rKey;
        }
        ArrayList<Rider> out = new ArrayList<>();
        for(Rider r : riderArray)
        {
            out.add(r);
        }
        return out;
   }
   
   
   
   
   
   
}