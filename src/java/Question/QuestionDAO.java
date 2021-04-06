/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Question;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.naming.NamingException;
import thuylinhHelper.DBHelper;

/**
 *
 * @author Thuy Linh
 */
public class QuestionDAO implements Serializable {

    private List<QuestionDTO> listQuestion;

    public List<QuestionDTO> searchQuestion(String content, String status, String SubjectID, int offset, int recordOfPage) throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            cn = DBHelper.makeConnection();
            if (cn != null) {
                String sql = null;
                if (status.equals("")) {
                    sql = "Select QuestionID, SubjectID, Substance, Status "
                            + "From Question "
                            + "Where Substance LIKE ? AND Status LIKE ? AND SubjectID = ? "
                            + "ORDER BY Substance "
                            + "OFFSET ? ROWS "
                            + "FETCH NEXT ? ROWS ONLY";
                    stm = cn.prepareStatement(sql);
                    stm.setString(1, "%" + content + "%");
                    stm.setString(2, "%" + status + "%");
                    stm.setString(3, SubjectID);
                    stm.setInt(4, offset);
                    stm.setInt(5, recordOfPage);
                } else {
                    sql = "Select QuestionID, SubjectID, Substance, Status "
                            + "From Question "
                            + "Where Substance LIKE ? AND Status = ? AND SubjectID = ? "
                            + "ORDER BY Substance "
                            + "OFFSET ? ROWS "
                            + "FETCH NEXT ? ROWS ONLY";
                    stm = cn.prepareStatement(sql);
                    stm.setString(1, "%" + content + "%");
                    stm.setString(2, status.trim());
                    stm.setString(3, SubjectID);
                    stm.setInt(4, offset);
                    stm.setInt(5, recordOfPage);
                }
                rs = stm.executeQuery();
                while (rs.next()) {
                    String questionID = rs.getString("QuestionID");
                    String subjectID = rs.getString("SubjectID");
                    String substance = rs.getString("Substance");
                    String Status = rs.getString("Status").trim();
                    QuestionDTO dto = new QuestionDTO(questionID, subjectID, substance, Status);
                    if (this.listQuestion == null) {
                        this.listQuestion = new ArrayList<>();
                    }
                    this.listQuestion.add(dto);
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
        return this.listQuestion;
    }
    int record = 0;

    public int getRecord(String content, String status, String SubjectID) throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            cn = DBHelper.makeConnection();
            if (cn != null) {
                String sql = null;
                if (status.equals("")) {
                    sql = "Select QuestionID, SubjectID, Substance, Status "
                            + "From Question "
                            + "Where Substance LIKE ? AND Status LIKE ? AND SubjectID = ? "
                            + "ORDER BY Substance ";
                    stm = cn.prepareStatement(sql);
                    stm.setString(1, "%" + content + "%");
                    stm.setString(2, "%" + status + "%");
                    stm.setString(3, SubjectID);
                } else {
                    sql = "Select QuestionID, SubjectID, Substance, Status "
                            + "From Question "
                            + "Where Substance LIKE ? AND Status = ? AND SubjectID = ? "
                            + "ORDER BY Substance ";
                    stm = cn.prepareStatement(sql);
                    stm.setString(1, "%" + content + "%");
                    stm.setString(2, status.trim());
                    stm.setString(3, SubjectID);
                }
                rs = stm.executeQuery();
                while (rs.next()) {
                    record++;
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
        return record;
    }

    public boolean insertQuestion(QuestionDTO dto) throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement stm = null;
        try {
            cn = DBHelper.makeConnection();
            if (cn != null) {
                String sql = "Insert Into Question (QuestionID, SubjectID, Substance, Status, CreateDate) "
                        + "Values(?,?,?,?,?)";
                stm = cn.prepareStatement(sql);
                stm.setString(1, dto.getQuestionID());
                stm.setString(2, dto.getSubjectID());
                stm.setString(3, dto.getSubstance());
                stm.setString(4, dto.getStatus());
                Date date = new Date();
                Timestamp createDate = new Timestamp(date.getTime());
                stm.setTimestamp(5, createDate);
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

    public QuestionDTO findQuestionByID(String questionID) throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            cn = DBHelper.makeConnection();
            if (cn != null) {
                String sql = "Select QuestionID, SubjectID, Substance, Status "
                        + "From Question "
                        + "Where QuestionID = ? ";
                stm = cn.prepareStatement(sql);
                stm.setString(1, questionID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String subjectID = rs.getString("SubjectID").trim();
                    String substance = rs.getString("Substance");
                    String Status = rs.getString("Status").trim();
                    QuestionDTO dto = new QuestionDTO(questionID, subjectID, substance, Status);
                    return dto;
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
        return null;
    }

    public boolean updateQuestion(QuestionDTO dto) throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement stm = null;
        try {
            cn = DBHelper.makeConnection();
            if (cn != null) {
                String sql = "Update Question "
                        + "SET  Substance = ? , Status = ?, SubjectID = ? "
                        + "Where QuestionID = ? ";
                stm = cn.prepareStatement(sql);
                stm.setString(1, dto.getSubstance());
                stm.setString(2, dto.getStatus());
                stm.setString(3, dto.getSubjectID());
                stm.setString(4, dto.getQuestionID());
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

    public boolean deleteQuestion(String questionID) throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement stm = null;
        try {
            cn = DBHelper.makeConnection();
            if (cn != null) {
                String sql = "Update Question "
                        + "SET   Status = ? "
                        + "Where QuestionID = ? ";
                stm = cn.prepareStatement(sql);
                stm.setString(1, "DeActive");
                stm.setString(2, questionID);
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
    public List<QuestionDTO> getQuestionInQuiz(int numberOfQuestion, String subjectID) throws SQLException, NamingException{
         Connection cn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            cn = DBHelper.makeConnection();
            if (cn != null) {
                String sql = "Select top (?) QuestionID, SubjectID, Substance, Status "
                        + "From Question "
                        + "Where Status = ? AND SubjectID = ? "
                        + "Order By newid() ";
                stm = cn.prepareStatement(sql);
                stm.setInt(1, numberOfQuestion);
                stm.setString(2, "Active");
                stm.setString(3, subjectID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String QuestionID = rs.getString("QuestionID");
                    String SubjectID = rs.getString("SubjectID");
                    String substance = rs.getString("Substance");
                    String Status = rs.getString("Status").trim();
                    QuestionDTO dto = new QuestionDTO(QuestionID, SubjectID, substance, Status);
                     if (this.listQuestion == null) {
                        this.listQuestion = new ArrayList<>();
                    }
                    this.listQuestion.add(dto);
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
        return this.listQuestion;
    }
}
