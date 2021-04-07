package registrationsystem;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.util.ArrayList;





public class Course {
    //variables
    private String courseCode, courseDescription, classroom, prerequisite;
     private DayOfWeek  courseDay;
     private LocalTime courseTime;
     private int maxCapacity;
     private Instructor instructor;
     private ArrayList<Student> listOfStudents;
     private int maxCapacityGeneral;
     private double averageStudentAge;
     //contructor 1
public Course (Instructor instructor, String courseCode, String courseDescription
        , String classroom, DayOfWeek courseDay, LocalTime courseTime, int maxCapacity) {
this.courseCode = courseCode;
if(!validateInstructor(instructor)){
    throw new IllegalArgumentException("Professor "+ instructor.toString() + " is not qualified to teach " + courseCode);
}
this.instructor = instructor;
this.courseDescription = courseDescription;
this.classroom = classroom;
this.courseDay = courseDay;
if(courseTime.getHour() < 8 || (courseTime.getHour() >= 18 || courseTime.getMinute() > 0)){
    throw new IllegalArgumentException("Course start time must be between 08:00-18:00");
}
this.courseTime = courseTime;
this.maxCapacity = maxCapacity;
this.listOfStudents = new ArrayList<>();
this.maxCapacityGeneral = 40;
}
//constructor 2
public Course (Instructor instructor, String courseCode, String courseDescription
        , String classroom, DayOfWeek courseDay, LocalTime courseTime, int maxCapacity, String prerequisite){
    this.courseCode = courseCode;
        if(!validateInstructor(instructor)){
            throw new IllegalArgumentException("Professor "+ instructor.toString() + " is not qualified to teach " + courseCode);
        }
        this.instructor = instructor;
        this.courseDescription = courseDescription;
        this.classroom = classroom;
        this.courseDay = courseDay;
        if(courseTime.getHour() < 8 || (courseTime.getHour() >= 18 || courseTime.getMinute() > 0)){
            throw new IllegalArgumentException("Course start time must be between 08:00-18:00");
        }
        this.courseTime = courseTime;
        this.maxCapacity = maxCapacity;
        this.listOfStudents = new ArrayList<>();
        this.maxCapacityGeneral = 40;
        this.prerequisite = prerequisite;
}
//set string prerequisite
public void setPrerequisite(String prerequisite) {
    this.prerequisite = prerequisite;
}
//if prerequisite is not null return prerequisite else return null
public String checkPrerequisite(){
    if(this.prerequisite != null){
        return this.prerequisite;
    }
    return "";
}
//list of getters 
public int getClassSize() {
    return maxCapacity;
}

public Instructor getCourseInstructor(){
     return instructor;
 }
public String getCourseCode (){
     return courseCode;
 }

public String getCourseDescription (){
     return courseDescription;
}
public String getClassRoom (){
     return classroom;
}
public DayOfWeek getCourseDay (){
     return courseDay;
}
public LocalTime getCourseTime (){
     return courseTime;
}
public int getCredits (){
     return maxCapacity;
}
//return cobined string that contains courseDay, string "'s, starting at " and courseTime
public String getCourseDayAndTime(){
    String returnString = "";
    returnString += courseDay + "'s, starting at " + courseTime;
    return returnString;
}
//using for each loop display the list of students in the class
public String displayTheClassList(){
    String returnString = "";
    for(Student student : listOfStudents){
        returnString += student.toString();
    }
    //returnString = returnString.substring(0, returnString.length() - 1);
    return returnString;
}
//if getAverageStudentAge > class is mature, else - not mature
 public boolean matureClass(){
     if(this.getAverageStudentAge() > 25){
         return true;
     }
     return false;
 }
 //add a student to the class if prerequisite != null and course.getCourseCompleted().getCourseCode().equals(this.prerequisite) else if prerequisite is null throw a message, if in a bad standing throw a message, if course is full throw a message
public String addStudent(Student student){
    boolean found = false;
    if(this.prerequisite != null){
        for(CompletedCourse course: student.getCollectionOfCompletedCourses()){
            if(course.getCourseCompleted().getCourseCode().equals(this.prerequisite)){
                found = true;
            } 
        }
        //if found is true
        if(!found){
                return "Student has not completed the prerequisite course: " + this.prerequisite;
        }
    }
    if((this.listOfStudents.size() < this.maxCapacity) && (student.studentInGoodStanding())){
        this.listOfStudents.add(student);
        return "";
    }
    else if(!student.studentInGoodStanding()){
        return "The Student is not in good standing and cannot join the course.";
    }
    return "Student was not added because the course is full";
}
// set maxCapacity of the class
public String setClassSize(int size){
    if(size > maxCapacityGeneral){
        this.maxCapacity = this.maxCapacityGeneral;
        return "Max class size = 40, it has been set to 40";
    }
    this.maxCapacity = size;
    return "";
}
//get instructor
public Instructor getInstructorToTeach(){
    return this.instructor;
}
//validation for instructor
public boolean validateInstructor(Instructor instructor) {
    for(String courseCode : instructor.getListOfCourses()){
        if(courseCode.equals(this.courseCode)){
            return true;
        }
    }
    return false;
}

//using to string method return courseCode and courseDescription
 @Override
    public String toString(){//overriding the toString() method  
  return courseCode+"-"+courseDescription;
 }  
    //get average student's age using for each loop
 public double getAverageStudentAge(){
     double returnAge = 0;
     for(Student student: listOfStudents){
         returnAge += student.getAge();
     }
     return returnAge/listOfStudents.size();
 }
}