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

/**
 *
 * @author Lenovo
 */
public class Pakaian {
    
    private String idPakaian;
    private String Jenis;
    private String Harga;

    public String getIdPakaian() {
        return idPakaian;
    }

    public void setIdPakaian(String idPakaian) {
        this.idPakaian = idPakaian;
    }

    public String getJenis() {
        return Jenis;
    }

    public void setJenis(String Jenis) {
        this.Jenis = Jenis;
    }

    public String getHarga() {
        return Harga;
    }

    public void setHarga(String Harga) {
        this.Harga = Harga;
    }
      
    public boolean SimpanDataPakaian(Pakaian data) throws SQLException{
        PreparedStatement pstm = null;
        Connection conn = (Connection)Connector.configDB();
        
        String sql = "INSERT INTO pakaian (idPakaian, jenisPakaian, harga) VALUES(?,?,?)";
        
        try{
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, data.getIdPakaian());
            pstm.setString(2, getJenis());
            pstm.setString(3, getHarga());
            
            pstm.execute();
            return true;
        }catch(HeadlessException | SQLException e){
            System.err.println(e);
            return false;
        }
    }
    
    public boolean UpdateDataPakaian(Pakaian data) throws SQLException{
        PreparedStatement pstm = null;
        Connection conn = (Connection)Connector.configDB();
        
        String sql = "UPDATE pakaian SET jenisPakaian=?, harga=? WHERE idPakaian=?";
    
        try{
            pstm = conn.prepareStatement(sql);
            pstm.setString(3, data.getIdPakaian());
            pstm.setString(1, getJenis());
            pstm.setString(2, getHarga());
            
      
            pstm.execute();
            return true;
        }catch(HeadlessException | SQLException e){
            System.err.println(e);
            return false;
        }
    }
    
    public boolean HapusDataPakaian(Pakaian data) throws SQLException{
        PreparedStatement pstm = null;
        Connection conn = (Connection)Connector.configDB();
        
        String sql = "DELETE FROM pakaian WHERE idPakaian=?";
        
        try{
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, data.getIdPakaian());
            pstm.execute();
            return true;
        }catch(HeadlessException | SQLException e){
            System.err.println(e);
            return false;
        }
    }
}
