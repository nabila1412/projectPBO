/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Connector;
import Model.Customer;
import Model.Pakaian;
import Model.Pemesanan;
import View.dataCustomer;
import View.dataPakaian;
import View.dataPemesanan;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class pemesananController implements ActionListener, MouseListener{
    private Pemesanan data;
    private dataPemesanan frm;
    
    public pemesananController(Pemesanan data, dataPemesanan frm){
        this.data = data;
        this.frm = frm;
        this.frm.btnTambah.addActionListener(this);
        this.frm.btnSimpan.addActionListener(this);
        this.frm.btnEdit.addActionListener(this);
        this.frm.btnHapus.addActionListener(this);
        this.frm.tablePemesanan.addMouseListener(this);
    }
    
    public void KosongDataPemesanan(){
            frm.txtIdPemesanan.setText(null);
            frm.idCustomer.setSelectedItem(null);
            frm.idPakaian.setSelectedItem(null);
            frm.txtHarga.setText(null);
            frm.ukuran.setSelectedItem(null);
            frm.jumlah.setText(null);
            
        }
    
    public void TampilDataPemesanan(){
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("No");
            model.addColumn("Id Pemesanan");
            model.addColumn("Id Pakaian");
            model.addColumn("Id Customer");
            model.addColumn("Harga");
            model.addColumn("Ukuran");
            model.addColumn("Jumlah");
            // Menampilkan data pada database ke dalam tabel
            try{
                int no=1;
                String sql="SELECT * FROM pemesanan";
                java.sql.Connection conn=(Connection)Connector.configDB();
                java.sql.Statement stm=conn.createStatement();
                java.sql.ResultSet res=stm.executeQuery(sql);
                
                while(res.next()){
                    model.addRow(new Object[]{
                        no++,
                        res.getString(1),
                        res.getString(2),
                        res.getString(3),
                        res.getString(4),
                        res.getString(5),
                        res.getString(6),
                       });
                }
                frm.tablePemesanan.setModel(model);
            }catch(SQLException e){
                System.out.println("Error "+e.getMessage());
            }
        }
    
    public void pemesanan() {
        String sql = "Select id FROM customer";
        
        try {
            java.sql.Connection conn=(Connection)Connector.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            
            while (res.next()) {
                this.frm.idCustomer.addItem(res.getString(1));    
            }
        }
        catch(SQLException e){
            
        }
    }
    
    public void pakaian() {
        String sql = "Select idPakaian FROM pakaian";
        
        try {
            java.sql.Connection conn=(Connection)Connector.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            
            while (res.next()) {
                this.frm.idPakaian.addItem(res.getString(1));    
            }
        }
        catch(SQLException e){
            
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==frm.btnTambah){
            KosongDataPemesanan();
        }else if(ae.getSource()==frm.btnSimpan){
            data.setIdPemesanan(frm.txtIdPemesanan.getText());
            data.setIdCustomer((String) frm.idCustomer.getSelectedItem());
            data.setIdPakaian((String) frm.idPakaian.getSelectedItem());
            data.setHarga(frm.txtHarga.getText());
            data.setUkuran((String) frm.ukuran.getSelectedItem());
            data.setJumlah(frm.jumlah.getText());
            
            
            try {
                if(data.SimpanDataPemesanan(data)){
                    JOptionPane.showMessageDialog(null, "Data Telah Disimpan");
                    KosongDataPemesanan();
                    TampilDataPemesanan();
                }
            }catch (SQLException ex){
                JOptionPane.showMessageDialog(null, ex);
            }
        }else if(ae.getSource()==frm.btnEdit){
            data.setIdPemesanan(frm.txtIdPemesanan.getText());
            data.setIdCustomer((String) frm.idCustomer.getSelectedItem());
            data.setIdPakaian((String) frm.idPakaian.getSelectedItem());
            data.setHarga(frm.txtHarga.getText());
            data.setUkuran((String) frm.ukuran.getSelectedItem());
            data.setJumlah(frm.jumlah.getText());
            
            
            try {
                if(data.UpdateDataPemesanan(data)){
                    JOptionPane.showMessageDialog(null, "Update Anda Berhasil");
                    KosongDataPemesanan();
                    TampilDataPemesanan();
                }
            }catch (SQLException ex){
                JOptionPane.showMessageDialog(null, ex);
            }
        }else {
            data.setIdPemesanan(frm.txtIdPemesanan.getText());
            
            try {
                if(data.HapusDataPemesanan(data)){
                    JOptionPane.showMessageDialog(null, "Berhasil Menghapus");
                    KosongDataPemesanan();
                    TampilDataPemesanan();
                }
            }catch (SQLException ex){
                JOptionPane.showMessageDialog(null, ex);
            }
        }      
    }
    @Override
    public void mouseClicked(MouseEvent me) {
        if(me.getSource()==frm.tablePemesanan){
            frm.txtIdPemesanan.setEditable(false);
            
            int baris=frm.tablePemesanan.rowAtPoint(me.getPoint());
            String idPemesanan = frm.tablePemesanan.getValueAt(baris, 1).toString();
            frm.txtIdPemesanan.setText(idPemesanan);
            
            String IdCustomer=frm.tablePemesanan.getValueAt(baris, 2).toString();
            frm.idCustomer.setSelectedItem(IdCustomer);
            
            String IdPakaian=frm.tablePemesanan.getValueAt(baris, 3).toString();
            frm.idPakaian.setSelectedItem(IdPakaian);
            
            String Harga = frm.tablePemesanan.getValueAt(baris, 4).toString();
            frm.txtHarga.setText(Harga);
            
            String Ukuran=frm.tablePemesanan.getValueAt(baris, 5).toString();
            frm.ukuran.setSelectedItem(Ukuran);                    
            
            String Jumlah=frm.tablePemesanan.getValueAt(baris, 6).toString();
            frm.jumlah.setText(Jumlah);
            
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
