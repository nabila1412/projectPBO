/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Customer {
    
    private String id;
    private String nama;
    private String NoHp;
    private String Alamat;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNoHp() {
        return NoHp;
    }

    public void setNoHp(String NoHp) {
        this.NoHp = NoHp;
    }

    public String getAlamat() {
        return Alamat;
    }

    public void setAlamat(String Alamat) {
        this.Alamat = Alamat;
    }
    
    public boolean SimpanCustomer(Customer data) throws SQLException{
        PreparedStatement pstm = null;
        Connection conn = (Connection)Connector.configDB();
        
        String sql = "INSERT INTO customer(id, nama, no_hp, alamat) VALUES(?,?,?,?)";
        
        try{
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, data.getId());
            pstm.setString(2, data.getNama());
            pstm.setString(3, data.getNoHp());
            pstm.setString(4, data.getAlamat());
            pstm.execute();
            return true;
        }catch(HeadlessException | SQLException e){
            System.out.println(data.getId());
            return false;
        }
    }
    
//    public boolean SimpanCustomer() throws SQLException {
//        Connection con = (Connection)Connector.configDB();
//        
//        
//        String sql = "insert into customer values (?,?,?, ?)";
//        PreparedStatement ps = con.prepareStatement(sql);
//        ps.setInt(1, this.id);
//        ps.setString(2, this.nama);
//        ps.setString(3, this.NoHp);
//        ps.setString(4, this.Alamat);
//        ps.execute();
// 
//return true;
//}
    public boolean UpdateCustomer(Customer data) throws SQLException{
        PreparedStatement pstm = null;
        Connection conn = (Connection)Connector.configDB();
        
         String sql = "UPDATE customer SET nama=?, no_hp=?, alamat=? WHERE id=?";
    
        try{
            pstm = conn.prepareStatement(sql);
            pstm.setString(4, data.getId());
            pstm.setString(1, data.getNama());
            pstm.setString(2, data.getNoHp());
            pstm.setString(3, data.getAlamat());
            
      
            pstm.execute();
            return true;
        }catch(HeadlessException | SQLException e){
            System.err.println(e);
            return false;
        }
    }

    
    public boolean HapusCustomer(Customer data) throws SQLException{
        PreparedStatement pstm = null;
        Connection conn = (Connection)Connector.configDB();
        
        String sql = "DELETE FROM customer WHERE id=?";
        
        try{
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, data.getId());
            pstm.execute();
            return true;
        }catch(HeadlessException | SQLException e){
            System.err.println(e);
            return false;
        }
    }
}
