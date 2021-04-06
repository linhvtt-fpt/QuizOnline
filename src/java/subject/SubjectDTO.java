/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subject;

import java.io.Serializable;

/**
 *
 * @author Thuy Linh
 */
public class SubjectDTO implements Serializable{
    String subjectID, name, specialized,status;
    int numOfQuestion, timeQuiz;

    public SubjectDTO() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public SubjectDTO(String subjectID, String name, String specialized, String status, int numOfQuestion, int timeQuiz) {
        this.subjectID = subjectID;
        this.name = name;
        this.specialized = specialized;
        this.status = status;
        this.numOfQuestion = numOfQuestion;
        this.timeQuiz = timeQuiz;
    }

    public int getNumOfQuestion() {
        return numOfQuestion;
    }

    public void setNumOfQuestion(int numOfQuestion) {
        this.numOfQuestion = numOfQuestion;
    }

    public int getTimeQuiz() {
        return timeQuiz;
    }

    public void setTimeQuiz(int timeQuiz) {
        this.timeQuiz = timeQuiz;
    }

    


    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialized() {
        return specialized;
    }

    public void setSpecialized(String specialized) {
        this.specialized = specialized;
    }
    
}
