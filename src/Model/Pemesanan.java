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
    private String NamaCustomer;
    private String idPakaian;
    private String Harga;
    private String Ukuran;
    private String Jumlah;

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

    public String getNamaCustomer() {
        return NamaCustomer;
    }

    public void setNamaCustomer(String NamaCustomer) {
        this.NamaCustomer = NamaCustomer;
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

    public String getJumlah() {
        return Jumlah;
    }

    public void setJumlah(String Jumlah) {
        this.Jumlah = Jumlah;
    }

    
    
     public boolean SimpanDataPemesanan(Pemesanan data) throws SQLException{
        PreparedStatement pstm = null;
        Connection conn = (Connection)Connector.configDB();
        
        String sql = "INSERT INTO pemesanan (idPemesanan, idCustomer, namaCustomer, idPakaian, harga, ukuran, jumlah) VALUES(?,?,?,?,?,?,?)";
        
        try{
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, data.getIdPemesanan());
            pstm.setString(2, data.getIdCustomer());
            pstm.setString(3, data.getNamaCustomer());
            pstm.setString(4, data.getIdPakaian());
            pstm.setString(5, data.getHarga());
            pstm.setString(6, data.getUkuran());
            pstm.setString(7, data.getJumlah());
            
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
        
        String sql = "UPDATE pemesanan SET idCustomer=?, namaCustomer=?, idPakaian=?, harga=?, ukuran=?, jumlah=? WHERE idPemesanan=?";
    
        try{
            pstm = conn.prepareStatement(sql);
            pstm.setString(7, data.getIdPemesanan());
            pstm.setString(1, data.getIdCustomer());
            pstm.setString(2, data.getNamaCustomer());
            pstm.setString(3, data.getIdPakaian());
            pstm.setString(4, data.getHarga());
            pstm.setString(5, data.getUkuran());
            pstm.setString(6, data.getJumlah());            
      
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
            pstm.setString(1, data.getIdPemesanan());
            pstm.execute();
            return true;
        }catch(HeadlessException | SQLException e){
            System.err.println(e);
            return false;
        }
    }
}
