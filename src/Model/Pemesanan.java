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

public class Pemesanan {
    
    private String idPemesanan;
    private String idCustomer;
    private String idPakaian;
    private String Harga;
    private String Ukuran;
    private String Jumlah;

    public String getJumlah() {
        return Jumlah;
    }

    public void setJumlah(String Jumlah) {
        this.Jumlah = Jumlah;
    }

    public String getIdPemesanan() {
        return idPemesanan;
    }

    public void setIdPemesanan(String idPemesanan) {
        this.idPemesanan = idPemesanan;
    }

    public String getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(String idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getIdPakaian() {
        return idPakaian;
    }

    public void setIdPakaian(String idPakaian) {
        this.idPakaian = idPakaian;
    }

    public String getHarga() {
        return Harga;
    }

    public void setHarga(String Harga) {
        this.Harga = Harga;
    }

    public String getUkuran() {
        return Ukuran;
    }

    public void setUkuran(String Ukuran) {
        this.Ukuran = Ukuran;
    }
    
     public boolean SimpanDataPemesanan(Pemesanan data) throws SQLException{
        PreparedStatement pstm = null;
        Connection conn = (Connection)Connector.configDB();
        
        String sql = "INSERT INTO pemesanan (idPemesanan, idCustomer, idPakaian, harga, ukuran, jumlah) VALUES(?,?,?,?,?,?)";
        
        try{
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, data.getIdPemesanan());
            pstm.setString(2, getIdCustomer());
            pstm.setString(3, getIdPakaian());
            pstm.setString(4, getHarga());
            pstm.setString(5, getUkuran());
            pstm.setString(6, getJumlah());
            
            pstm.execute();
            return true;
        }catch(HeadlessException | SQLException e){
            System.err.println(e);
            return false;
        }
    }
    
     public boolean UpdateDataPemesanan(Pemesanan data) throws SQLException{
        PreparedStatement pstm = null;
        Connection conn = (Connection)Connector.configDB();
        
        String sql = "UPDATE pemesanan SET idCustomer=?, idPakaian=?, harga=?, ukuran=?, jumlah=? WHERE idPemesanan=?";
    
        try{
            pstm = conn.prepareStatement(sql);
            pstm.setString(6, data.getIdPemesanan());
            pstm.setString(1, getIdCustomer());
            pstm.setString(2, getIdPakaian());
            pstm.setString(3, getHarga());
            pstm.setString(4, getUkuran());
            pstm.setString(5, getJumlah());            
      
            pstm.execute();
            return true;
        }catch(HeadlessException | SQLException e){
            System.err.println(e);
            return false;
        }
    }
     
     public boolean HapusDataPemesanan(Pemesanan data) throws SQLException{
        PreparedStatement pstm = null;
        Connection conn = (Connection)Connector.configDB();
        
        String sql = "DELETE FROM pemesanan WHERE idPemesanan=?";
        
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
