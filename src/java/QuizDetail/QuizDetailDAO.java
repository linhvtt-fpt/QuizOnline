/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuizDetail;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import thuylinhHelper.DBHelper;

/**
 *
 * @author Thuy Linh
 */
public class QuizDetailDAO implements Serializable {

    public boolean insertQuizDetail(QuizDetailDTO dto) throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement stm = null;
        try {
            cn = DBHelper.makeConnection();
            if (cn != null) {
                String sql = "Insert Into QuizDetail ( QuizDetailID, Email, SubjectID, Score, NumOfCorrectAnswer, StartingTime, EndTime) "
                        + "Values(?,?,?,?,?,?,?)";
                stm = cn.prepareStatement(sql);
                stm.setString(1, dto.getQuizDetailID());
                stm.setString(2, dto.getEmail());
                stm.setString(3, dto.getSubjectID());
                stm.setFloat(4, dto.getScore());
                stm.setInt(5, dto.getNumOfCorrectAns());
                stm.setTimestamp(6, new Timestamp(dto.getStartingTime().getTime()));
                stm.setTimestamp(7, new Timestamp(dto.getEndTime().getTime()));
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return false;
    }
    private List<QuizDetailDTO> list;
    
    public List<QuizDetailDTO> historyQuiz(String email, String subjectID) throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            cn = DBHelper.makeConnection();
            if (cn != null) {
                String sql = "Select QuizDetailID, Score, NumOfCorrectAnswer, StartingTime, EndTime "
                        + "From QuizDetail "
                        + "Where Email = ? AND SubjectID = ? "
                        + "Order by StartingTime DESC";
                stm = cn.prepareStatement(sql);
                stm.setString(1, email.trim());
                stm.setString(2, subjectID.trim());
                rs= stm.executeQuery();
                while (rs.next()) {
                    String quizDetailID = rs.getString("QuizDetailID");
                    float Score = rs.getFloat("Score");
                    int numOfCorrectAns = rs.getInt("NumOfCorrectAnswer");
                    Timestamp start = rs.getTimestamp("StartingTime");
                    Timestamp end   = rs.getTimestamp("EndTime");
                    QuizDetailDTO quizDetail = new QuizDetailDTO(quizDetailID, email, subjectID, numOfCorrectAns, Score, start, end);
                     if(this.list==null){
                        list = new ArrayList<>();
                    }
                    list.add(quizDetail);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return list;
    }
}
