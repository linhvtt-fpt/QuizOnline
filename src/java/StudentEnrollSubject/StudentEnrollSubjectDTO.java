/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StudentEnrollSubject;

import java.io.Serializable;

/**
 *
 * @author Thuy Linh
 */
public class StudentEnrollSubjectDTO implements Serializable{
    String enrollID,email, subjectID;

    public StudentEnrollSubjectDTO(String enrollID, String email, String subjectID) {
        this.enrollID = enrollID;
        this.email = email;
        this.subjectID = subjectID;
    }

    public StudentEnrollSubjectDTO() {
    }

    public String getEnrollID() {
        return enrollID;
    }

    public void setEnrollID(String enrollID) {
        this.enrollID = enrollID;
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
    
}
