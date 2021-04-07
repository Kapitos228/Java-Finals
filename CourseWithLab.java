/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registrationsystem;

import java.time.DayOfWeek;
import java.time.LocalTime;

/**
 *
 * @author besso
 */
//variables
public class CourseWithLab extends Course {
    private Instructor labGuy;
    private String labClassroom;
    private DayOfWeek labDay;
    private LocalTime labTime;
    //constructor 1 standart argumants
    public CourseWithLab(Instructor instructor, String courseCode, String courseDescription, String classroom, DayOfWeek courseDay, LocalTime courseTime,
            int maxCapacity,Instructor laborant, String labClass, DayOfWeek labDay, LocalTime labT) {
        super(instructor, courseCode, courseDescription, classroom, courseDay, courseTime, maxCapacity);
        this.labGuy = laborant;
        this.labClassroom = labClass;
        this.labDay = labDay;
        this.labTime = labT;
        //validation of laborant
        if(!validateLabGuy(laborant, courseCode)){
            throw new IllegalArgumentException("The Lab Tech is not qualified to host the lab");
        }
        //validation of lab start time
        if(labT.getHour() < 8 || (labT.getHour() >= 18  || labT.getMinute() > 0)){
            throw new IllegalArgumentException("The lab start time must be between 08:00-18:00");
        }
    }
     //constructor 2 standart arguments and prerequisite
    public CourseWithLab(Instructor instructor, String courseCode, String courseDescription, String classroom, DayOfWeek courseDay, LocalTime courseTime,
            int maxCapacity, String prerequisite,Instructor laborant, String labClass, DayOfWeek labDay, LocalTime labT){
        super(instructor, courseCode, courseDescription, classroom, courseDay, courseTime, maxCapacity);
        this.labGuy = laborant;
        this.labClassroom = labClass;
        this.labDay = labDay;
        this.labTime = labT;
           //validation of laborant
        if(!validateLabGuy(laborant, courseCode)){
            throw new IllegalArgumentException("The Lab Tech is not qualified to host the lab");
        }
        //validation of lab start time
        if(labT.getHour() < 8 || (labT.getHour() >= 18  || labT.getMinute() > 0)){
            throw new IllegalArgumentException("The lab start time must be between 08:00-18:00");
        }
        super.setPrerequisite(prerequisite);
    }
    public Instructor getLabTech(){
        return this.labGuy;
    }
    //getting labClassroom,labDay and labTime
    public String getLabClassAndTime(){
        return "room: " + this.labClassroom + ", " + this.labDay + " starting at " + this.labTime;
    }
    //validation for laborant 
    public boolean validateLabGuy(Instructor laborant, String courseCode){
        for(String course : laborant.getListOfCourses()){
            if(course.equals(courseCode + "-LAB")){
                return true;
            }
        }
        return false;
    }
//using toString method getting superstring toString and adding string " with lab"
    @Override
    public String toString(){//overriding the toString() method  
        return super.toString() + " with lab";
    }   
}
