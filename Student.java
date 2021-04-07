package registrationsystem;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.ArrayList;





public class Student {
    //variables
    private String firstName, lastName, streetAddress, city, postalCode, courseCode ;
     private LocalDate  registrationDate, dateOfBirth;
     private int studentNumber;
     private boolean standing;
     private int age;
     private ArrayList<CompletedCourse> completedCourses;

    
//constructor
public Student (String firstName, String lastName, String streetAddress
        , String city, String postalCode, String courseCode, int studentNumber, LocalDate regestrationDate,
        LocalDate dateOfBirth) {
this.firstName = firstName;
this.lastName = lastName;
this.streetAddress = streetAddress;
this.city = city;
this.postalCode = postalCode;
this.courseCode = courseCode;
LocalDate now = LocalDate.now();
Period period = Period.between(dateOfBirth, now);
//throw exception if student's age is more than 100 years
if(period.getYears() > 100){
    throw new IllegalArgumentException("Please check the year entered, student cannot be over 100 years old");
}
this.registrationDate = regestrationDate;
this.dateOfBirth = dateOfBirth;
this.studentNumber = studentNumber;
this.standing = true;
this.age = getAge();
this.completedCourses = new ArrayList<>();
}
//new array list for completedCourses
public ArrayList<CompletedCourse> getCollectionOfCompletedCourses(){
    return completedCourses;
}
//using for each loop for each completed course meeting completed course return getCourseCompleted, sting and getGrade and create a substring that will delete the last element of the string at the end so we won't get extra spece at the end
public String getCoursesCompleted(){
    String returnString = "[";
    for(CompletedCourse course: completedCourses){
        returnString += course.getCourseCompleted()+ " grade=" + course.getGrade() + " ";
    }
    returnString = returnString.substring(0, returnString.length() - 1);
    returnString += "]";
    return returnString;
}
//grade validation
public void addCompletedCourse(Course course, int grade){
    if(grade > 100 || grade < 0){
        throw new IllegalArgumentException("grade must be 0-100 inclusive");
    }
    this.completedCourses.add(new CompletedCourse(course, grade));
}
//List of getters
public String getFirstName (){
     return firstName;
 }
public String getLastName (){
     return lastName;
 }

public String getStreetAddress (){
     return streetAddress;
}
public String getCity (){
     return city;
}
public String getPostalCode (){
     return postalCode;
}
public String getCourseCode (){
     return courseCode;
}
public int getStudentNumber (){
     return studentNumber;
}
public LocalDate getRegDate (){
     return registrationDate;
}
public LocalDate getStudentBirthday(){
     return dateOfBirth;
}
public int getStudentAge() {
    LocalDate from = LocalDate.now();
    Period period = Period.between(dateOfBirth, from);
    return period.getYears();
}
//for each CompletedCourse course : completedCourse return true if course completed and grade is >=50 return true else return false
public boolean hasCompleted(String courseToCheck){
    for(CompletedCourse course: completedCourses){
        if(course.getCourseCompleted().getCourseCode().equals(courseToCheck) && course.getGrade() >= 50){
            return true;
        }
    }
    return false;
}
//get string that wull containt streetAddress city and postalCode
public String getStudentAddress(){
    String returnString = "";
    returnString += this.streetAddress + " ";
    returnString += this.city + " ";
    returnString += this.postalCode;
    return returnString;
}
//set streetAddress,city,postalCode
public void changeAddress(String street, String cityName, String postalC){
    this.streetAddress = street;
    this.city = cityName;
    this.postalCode = postalC;
}
//student in a bad standing
public void suspendStudent(){
    this.standing = false;
}
//student in a good standing again(reinstated)
public void reinstateStudent(){
    this.standing = true;
}
//student in a good standing
public boolean studentInGoodStanding(){
    return this.standing;
}
//get year inrolled based on the registrationDate
public int getYearEnrolled(){
    return this.registrationDate.getYear();
}
//get firstName,lastName,studentNumber using to string method
 @Override
    public String toString(){//overriding the toString() method  
  return firstName+" "+lastName+", student number: "+studentNumber;  
 }  
//get Age as int
    public int getAge() {
        LocalDate now = LocalDate.now();
        Period period = Period.between(dateOfBirth, now);
        return period.getYears();
    }
    //set studentNumber to birthday
    public void setBirthday(LocalDate birthday){
        this.dateOfBirth = birthday;
    }
    
  

}