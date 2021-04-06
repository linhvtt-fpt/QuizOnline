/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Answer;

import java.io.Serializable;

/**
 *
 * @author Thuy Linh
 */
public class AnswerDTO implements Serializable{
    String answerID, questionID,substance;
    boolean status;

    public AnswerDTO() {
    }

    public AnswerDTO(String answerID, String questionID, String substance, boolean status) {
        this.answerID = answerID;
        this.questionID = questionID;
        this.substance = substance;
        this.status = status;
    }

    public String getAnswerID() {
        return answerID;
    }

    public void setAnswerID(String answerID) {
        this.answerID = answerID;
    }

    public String getQuestionID() {
        return questionID;
    }

    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }

    public String getSubstance() {
        return substance;
    }

    public void setSubstance(String substance) {
        this.substance = substance;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
}
