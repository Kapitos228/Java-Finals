/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registrationsystem;

/**
 *
 * @author besso
 */
//variables
public class CompletedCourse {
    private Course courseCompleted;
    private int grade;
    //constructor
    public CompletedCourse(Course courseComp, int gr){
        this.courseCompleted = courseComp;
        this.grade = gr;
        //list of getters
    }
    public Course getCourseCompleted() {
        return courseCompleted;
    }
     public int getGrade() {
        return grade;
    }
}
