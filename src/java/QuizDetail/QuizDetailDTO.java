/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuizDetail;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Thuy Linh
 */
public class QuizDetailDTO implements Serializable{
   private String quizDetailID, email, subjectID;
   private int numOfCorrectAns;
   private float score;
   private Date startingTime, endTime;

    public QuizDetailDTO() {
    }

    public QuizDetailDTO(String quizDetailID, String email, String subjectID, int numOfCorrectAns, float score, Date startingTime, Date endTime) {
        this.quizDetailID = quizDetailID;
        this.email = email;
        this.subjectID = subjectID;
        this.numOfCorrectAns = numOfCorrectAns;
        this.score = score;
        this.startingTime = startingTime;
        this.endTime = endTime;
    }

    public String getQuizDetailID() {
        return quizDetailID;
    }

    public void setQuizDetailID(String quizDetailID) {
        this.quizDetailID = quizDetailID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public int getNumOfCorrectAns() {
        return numOfCorrectAns;
    }

    public void setNumOfCorrectAns(int numOfCorrectAns) {
        this.numOfCorrectAns = numOfCorrectAns;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public Date getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(Date startingTime) {
        this.startingTime = startingTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

   
   


}
