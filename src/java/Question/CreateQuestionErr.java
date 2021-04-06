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
public class CreateQuestionErr implements Serializable{
    String IDLengthErr;
    String IDisExisted;
    String radioNull;
    String answerNull;
    String contentQuestionLengthErr;
    public CreateQuestionErr() {
    }

    public CreateQuestionErr(String IDLengthErr, String IDisExisted, String radioNull, String answerNull, String contentQuestionLengthErr) {
        this.IDLengthErr = IDLengthErr;
        this.IDisExisted = IDisExisted;
        this.radioNull = radioNull;
        this.answerNull = answerNull;
        this.contentQuestionLengthErr = contentQuestionLengthErr;
    }

    public String getContentQuestionLengthErr() {
        return contentQuestionLengthErr;
    }

    public void setContentQuestionLengthErr(String contentQuestionLengthErr) {
        this.contentQuestionLengthErr = contentQuestionLengthErr;
    }

   

    public String getAnswerNull() {
        return answerNull;
    }

    public void setAnswerNull(String answerNull) {
        this.answerNull = answerNull;
    }

    

    public String getIDLengthErr() {
        return IDLengthErr;
    }

    public void setIDLengthErr(String IDLengthErr) {
        this.IDLengthErr = IDLengthErr;
    }

    public String getIDisExisted() {
        return IDisExisted;
    }

    public void setIDisExisted(String IDisExisted) {
        this.IDisExisted = IDisExisted;
    }

    public String getRadioNull() {
        return radioNull;
    }

    public void setRadioNull(String radioNull) {
        this.radioNull = radioNull;
    }
    
}
