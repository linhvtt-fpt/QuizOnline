/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subject;

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
public class SubjectDAO implements Serializable{
    private List<SubjectDTO> result;
    public List<SubjectDTO> getSubject() throws SQLException, NamingException{
    Connection cn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            cn = DBHelper.makeConnection();
            if(cn!=null){
                String sql = "Select SubjectID, Name, Specialized,Status, NumOfQuestion, TimeQuiz "
                        + "From Subject ";
                stm = cn.prepareStatement(sql);
                rs  = stm.executeQuery();
                while(rs.next()){
                    String subjectID = rs.getString("SubjectID").trim();
                    String name      = rs.getString("Name").trim();
                    String specialized = rs.getString("Specialized").trim();
                    String status    = rs.getString("Status").trim();
                    int numOfQuestion = rs.getInt("NumOfQuestion");
                    int timeQuiz = rs.getInt("TimeQuiz");
                    SubjectDTO dto = new SubjectDTO(subjectID, name, specialized, status, numOfQuestion, timeQuiz);
                    if(this.result==null){
                        this.result = new ArrayList<>();
                    }
                    this.result.add(dto);
                }
            }
        }
         finally {
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
        return this.result;
}
    public SubjectDTO getSubjectByID(String subjectID )throws SQLException, NamingException{
         Connection cn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            cn = DBHelper.makeConnection();
            if(cn!=null){
                String sql = "Select SubjectID, Name, Specialized,Status, NumOfQuestion, TimeQuiz "
                        + "From Subject "
                        + "Where SubjectID = ? ";
                stm = cn.prepareStatement(sql);
                stm.setString(1, subjectID);
                rs  = stm.executeQuery();
                if(rs.next()){
                    String name      = rs.getString("Name").trim();
                    String specialized = rs.getString("Specialized").trim();
                    String status    = rs.getString("Status").trim();
                    int numOfQuestion = rs.getInt("NumOfQuestion");
                    int timeQuiz = rs.getInt("TimeQuiz");
                    SubjectDTO dto = new SubjectDTO(subjectID, name, specialized, status, numOfQuestion, timeQuiz);
                    return dto;
                }
            }
        }
         finally {
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
