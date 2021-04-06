/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import thuylinhHelper.DBHelper;


/**
 *
 * @author Thuy Linh
 */
public class LoginDAO {
    public boolean checkLogin(String email, String password) throws SQLException, NamingException{
        Connection cn = null;
        PreparedStatement stm =null;
        ResultSet rs = null;
        try {
            cn = DBHelper.makeConnection();
            if(cn!=null){
                String sql = "Select Name, Email "
                        + "From Login "
                        + "Where Email = ? AND Password = ? ";
                stm =cn.prepareStatement(sql);
                stm.setString(1, email);
                stm.setString(2, password);
                rs= stm.executeQuery();
                if(rs.next()){
                    return true;
                }
            }
        }
        finally{
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
        return false;
    }
    public boolean SignUp(LoginDTO dto) throws  SQLException, NamingException{
        Connection cn = null;
        PreparedStatement stm = null;
        try {
            cn = DBHelper.makeConnection();
            if(cn!=null){
                String sql ="Insert Into Login(Email,Name,Password,Role,Status) "
                        + "Values(?,?,?,?,?) ";
                stm = cn.prepareStatement(sql);
                stm.setString(1, dto.getEmail());
                stm.setString(2, dto.getName());
                stm.setString(3, dto.getPassword());
                stm.setString(4, dto.getRole());
                stm.setString(5, dto.getStatus());
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
    public boolean checkAdmin(String email) throws SQLException, NamingException{
        Connection cn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            cn = DBHelper.makeConnection();
            if(cn!=null){
                String sql = "Select Email "
                        + "From Login "
                        + "Where Email = ? AND Role = ? ";
                stm = cn.prepareStatement(sql);
                stm.setString(1, email);
                stm.setString(2, "Admin");
                rs= stm.executeQuery();
                if(rs.next()){
                    return true;
                }
            }
        } finally {
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
        return false;
    }
    public String  getFullName(String email) throws SQLException, NamingException{
         Connection cn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            cn = DBHelper.makeConnection();
            if(cn!=null){
                String sql = "Select Name "
                        + "From Login "
                        + "Where Email = ?";
                stm = cn.prepareStatement(sql);
                stm.setString(1, email);
                rs= stm.executeQuery();
                if(rs.next()){
                    String fullname = rs.getString("Name");
                    return fullname;
                }
            }
        } finally {
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
