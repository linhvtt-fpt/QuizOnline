/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Question;

import java.io.Serializable;

/**
 *
 * @author Thuy Linh
 */
public class QuestionDTO implements Serializable{
    String questionID, subjectID, substance, status;

    public QuestionDTO() {
    }

    public QuestionDTO(String questionID, String subjectID, String substance, String status) {
        this.questionID = questionID;
        this.subjectID = subjectID;
        this.substance = substance;
        this.status = status;
    }

    public String getQuestionID() {
        return questionID;
    }

    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public String getSubstance() {
        return substance;
    }

    public void setSubstance(String substance) {
        this.substance = substance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
