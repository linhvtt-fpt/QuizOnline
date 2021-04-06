/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Answer;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import thuylinhHelper.DBHelper;

/**
 *
 * @author Thuy Linh
 */
public class AnswerDAO implements Serializable{
    private List<AnswerDTO> listAnswer;
    public  List<AnswerDTO> getAnswerByQuestionID(String questionID) throws SQLException, NamingException{
        Connection cn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            cn = DBHelper.makeConnection();
            if(cn!=null){
                String sql = "Select AnswerID, QuestionID , Substance, Status "
                        + "From Answer "
                        + "Where QuestionID = ? ";
                stm = cn.prepareStatement(sql);
                stm.setString(1, questionID);
                rs = stm.executeQuery();
                while(rs.next()){
                    String QuestionID = rs.getString("QuestionID");
                    String answerID  = rs.getString("AnswerID");
                    String substance  = rs.getString("Substance");
                    boolean status     = rs.getBoolean("Status");
                    AnswerDTO dto = new AnswerDTO(answerID, QuestionID, substance, status);
                    if(this.listAnswer==null){
                        this.listAnswer = new ArrayList<>();
                    }
                    this.listAnswer.add(dto);
                }
            }
        } finally{
            if(rs!=null){
                rs.close();
            }
            if(stm!=null){
                stm.close();
            }
            if(cn!=null){
                cn.close();
            }
        }
        return this.listAnswer;
    }
    public  List<AnswerDTO> getAnswer() throws SQLException, NamingException{
        Connection cn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            cn = DBHelper.makeConnection();
            if(cn!=null){
                String sql = "Select AnswerID, QuestionID , Substance, Status "
                        + "From Answer ";
                stm = cn.prepareStatement(sql);
                rs = stm.executeQuery();
                while(rs.next()){
                    String QuestionID = rs.getString("QuestionID");
                    String answerID  = rs.getString("AnswerID");
                    String substance  = rs.getString("Substance");
                    boolean status     = rs.getBoolean("Status");
                    AnswerDTO dto = new AnswerDTO(answerID, QuestionID, substance, status);
                    if(this.listAnswer==null){
                        this.listAnswer = new ArrayList<>();
                    }
                    this.listAnswer.add(dto);
                }
            }
        } finally{
            if(rs!=null){
                rs.close();
            }
            if(stm!=null){
                stm.close();
            }
            if(cn!=null){
                cn.close();
            }
        }
        return this.listAnswer;
    }
    public  boolean insertAns(AnswerDTO dto) throws SQLException, NamingException{
        Connection cn = null;
        PreparedStatement stm = null;
        try {
            cn = DBHelper.makeConnection();
            if(cn!=null){
                String sql = "Insert Into Answer (AnswerID, QuestionID, Status, Substance) "
                        + "Values(?,?,?,?) ";
                stm = cn.prepareStatement(sql);
                stm.setString(1, dto.getAnswerID());
                stm.setString(2, dto.getQuestionID());
                stm.setBoolean(3, dto.getStatus());
                stm.setString(4, dto.getSubstance());
                int row = stm.executeUpdate();
                if(row>0){
                   return true;
                }
            }
        } finally{
            
            if(stm!=null){
                stm.close();
            }
            if(cn!=null){
                cn.close();
            }
        }
       return false;
    }
    public  AnswerDTO findAnswerByID(String ansID)throws SQLException, NamingException{
        Connection cn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            cn = DBHelper.makeConnection();
            if(cn!=null){
                String sql = "Select AnswerID, QuestionID , Substance, Status "
                        + "From Answer "
                        + "Where AnswerID = ? ";
                stm = cn.prepareStatement(sql);
                stm.setString(1, ansID);
                rs = stm.executeQuery();
                if(rs.next()){
                    String QuestionID = rs.getString("QuestionID");
                    String answerID  = rs.getString("AnswerID");
                    String substance  = rs.getString("Substance");
                    boolean status     = rs.getBoolean("Status");
                    AnswerDTO dto = new AnswerDTO(answerID, QuestionID, substance, status);
                    return dto;
                }
            }
        } finally{
            if(rs!=null){
                rs.close();
            }
            if(stm!=null){
                stm.close();
            }
            if(cn!=null){
                cn.close();
            }
        }
        return null;
    
    }
    public boolean updateAnswer (AnswerDTO dto) throws SQLException, NamingException{
        Connection cn = null;
        PreparedStatement stm = null;
        try {
            cn = DBHelper.makeConnection();
            if(cn!=null){
                String sql = "Update Answer "
                        + "SET  Substance = ? , Status = ? "
                        + "Where AnswerID = ? ";
                stm = cn.prepareStatement(sql);
                stm.setString(1, dto.getSubstance());
                stm.setBoolean(2, dto.getStatus());
                stm.setString(3, dto.getAnswerID());
               int row = stm.executeUpdate();
                if(row > 0){
                    return true;
                }
            }
        } finally{
            
            if(stm!=null){
                stm.close();
            }
            if(cn!=null){
                cn.close();
            }
        }
        return false;
   }
    public  String answerCorrectByQuestionID(String questionID ) throws SQLException, NamingException{
         Connection cn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            cn = DBHelper.makeConnection();
            if(cn!=null){
                String sql = "Select AnswerID "
                        + "From Answer "
                        + "Where QuestionID = ? AND Status = 1";
                stm = cn.prepareStatement(sql);
                stm.setString(1, questionID);
                rs = stm.executeQuery();
                if(rs.next()){
                    String answerID  = rs.getString("AnswerID");
                    return answerID;
                }
            }
        } finally{
            if(rs!=null){
                rs.close();
            }
            if(stm!=null){
                stm.close();
            }
            if(cn!=null){
                cn.close();
            }
        }
        return null;
    }
}
