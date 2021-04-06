/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StudentEnrollSubject;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import javax.naming.NamingException;
import thuylinhHelper.DBHelper;

/**
 *
 * @author Thuy Linh
 */
public class StudentEnrollSubjectDAO implements Serializable{
    public boolean insertStudentEnroll(StudentEnrollSubjectDTO dto )throws SQLException, NamingException{
        Connection cn = null;
        PreparedStatement stm = null;
        try {
            cn = DBHelper.makeConnection();
            if (cn != null) {
                String sql = "Insert Into StudentEnrollSubject (EnrollID, Email, SubjectID)"
                        + "Values(?,?,?)";
                stm = cn.prepareStatement(sql);
                stm.setString(1, dto.getEnrollID());
                stm.setString(2, dto.getEmail());
                stm.setString(3, dto.getSubjectID());
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
}
