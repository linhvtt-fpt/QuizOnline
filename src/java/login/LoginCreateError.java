/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import java.io.Serializable;

/**
 *
 * @author Thuy Linh
 */
public class LoginCreateError implements Serializable{
    private String emailFormatError;
    private String emailLengError;
    private String emailIsExisted;
    private String passwordLengError;
    private String confirmNotMatched;
    private String nameLengError;
    

    public LoginCreateError() {
    }

    public LoginCreateError(String emailFormatError, String emailLengError, String emailIsExisted, String passwordLengError, String confirmNotMatched, String nameLengError) {
        this.emailFormatError = emailFormatError;
        this.emailLengError = emailLengError;
        this.emailIsExisted = emailIsExisted;
        this.passwordLengError = passwordLengError;
        this.confirmNotMatched = confirmNotMatched;
        this.nameLengError = nameLengError;
    }

    

   

    public String getEmailFormatError() {
        return emailFormatError;
    }

    public void setEmailFormatError(String emailFormatError) {
        this.emailFormatError = emailFormatError;
    }

    public String getEmailLengError() {
        return emailLengError;
    }

    public void setEmailLengError(String emailLengError) {
        this.emailLengError = emailLengError;
    }

    public String getEmailIsExisted() {
        return emailIsExisted;
    }

    public void setEmailIsExisted(String emailIsExisted) {
        this.emailIsExisted = emailIsExisted;
    }

    public String getPasswordLengError() {
        return passwordLengError;
    }

    public void setPasswordLengError(String passwordLengError) {
        this.passwordLengError = passwordLengError;
    }

    public String getConfirmNotMatched() {
        return confirmNotMatched;
    }

    public void setConfirmNotMatched(String confirmNotMatched) {
        this.confirmNotMatched = confirmNotMatched;
    }

    public String getNameLengError() {
        return nameLengError;
    }

    public void setNameLengError(String nameLengError) {
        this.nameLengError = nameLengError;
    }
    
}
