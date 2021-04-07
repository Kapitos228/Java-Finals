/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registrationsystem;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

/**
 *
 * @author besso
 */
//variables
public class Instructor {
    private String firstName, lastName, streetAddress, city, postalCode;
    private LocalDate  startWorkingDate, dateOfBirth;
    private int instructorNumber;
    private ArrayList<String> listOfCourses;

    
    //constructor
    public Instructor (String firstName, String lastName, int instructorNumber, String streetAddress
        , String city, String postalCode, LocalDate startWorkingDate,
        LocalDate dateOfBirth) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.streetAddress = streetAddress;
            this.city = city;
            this.postalCode = postalCode;
            LocalDate now = LocalDate.now();
            Period periodWork = Period.between(startWorkingDate, now);
            if(periodWork.getYears() > 80){
                throw new IllegalArgumentException(startWorkingDate + " as a hire date would mean Anita started working over 80 years ago");
            }
            this.startWorkingDate = startWorkingDate;
            Period periodBirth = Period.between(dateOfBirth, now);
            if(periodBirth.getYears() > 100){
                throw new IllegalArgumentException("Please check the year entered, instructor cannot be over 100 years old");
            }
            this.dateOfBirth = dateOfBirth;
            this.instructorNumber = instructorNumber;
            this.listOfCourses = new ArrayList<>();
    }
    //new array list for listOfCourses
    public ArrayList<String> getListOfCourses() {
        return listOfCourses;
    }
    //using toString method get firstName and lastName
    @Override
    public String toString(){//overriding the toString() method  
        return firstName+" "+lastName;  
    }
    //get get instructor name in number of years 
    public int getAgeInYears(){
        LocalDate from = LocalDate.now();
        Period period = Period.between(dateOfBirth, from);
        return period.getYears();
    }
    //get nomber of years in college
    public int noOfYearsAtCollege(){
        LocalDate from = LocalDate.now();
        Period period = Period.between(this.startWorkingDate, from);
        return period.getYears();
    }
    
    //get a combined street that contains streetAddress,city,postalCode
    public String getInstructorAddress(){
        String returnString = "";
        returnString += this.streetAddress + ", ";
        returnString += this.city + ", ";
        returnString += this.postalCode;
        return returnString;
    }
    // set addrress 
    public void changeAddress(String street, String cityName, String postalC){
        this.streetAddress = street;
        this.city = cityName;
        this.postalCode = postalC;
    }   
    //using for each loop we are getting the list of subjects that instructor is certified to teach and taking extra space and comma at the end
    public String listOfSubjectsCertifiedToTeach(){
        if(this.listOfCourses.isEmpty()){
            return "not qualified to teach courses yet.";
        }
        String returnString = "[";
        for(String courseName: this.listOfCourses){
            returnString += courseName + ", ";
        }
        returnString = returnString.substring(0, returnString.length() - 2);
        returnString += "]";
        return returnString;
    }
    // method in which if instructor can't teach course ad a course to the listOfCourses
    public void addCourseToInstructorAbilities(String course){
        if(!instructorCanTeach(course)){
            this.listOfCourses.add(course);
        }
    }
    //instructor can teach if courseName.equals course else he can't
    public boolean instructorCanTeach(String course){
        for(String courseName: this.listOfCourses){
            if(courseName.equals(course)){
                return true;
            }
        }
        return false;
    }   
}
