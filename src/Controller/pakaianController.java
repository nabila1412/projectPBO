/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Connector;
import Model.Customer;
import Model.Pakaian;
import View.dataCustomer;
import View.dataPakaian;
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

public class pakaianController implements ActionListener, MouseListener{
    private Pakaian data;
    private dataPakaian frm;
    
    public pakaianController(Pakaian data, dataPakaian frm){
        this.data = data;
        this.frm = frm;
        this.frm.btnTambah.addActionListener(this);
        this.frm.btnSimpan.addActionListener(this);
        this.frm.btnEdit.addActionListener(this);
        this.frm.btnHapus.addActionListener(this);
        this.frm.tablePakaian.addMouseListener(this);
    }
    
    public void KosongDataPakaian(){
            frm.txtIdPakaian.setText(null);
            frm.txtJenis.setText(null);
            frm.txtHarga.setText(null);
        }
    public void TampilDataPakaian(){
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("No");
            model.addColumn("Id Pakaian");
            model.addColumn("Jenis Pakaian");
            model.addColumn("Harga");
            // Menampilkan data pada database ke dalam tabel
            try{
                int no=1;
                String sql="SELECT * FROM pakaian";
                java.sql.Connection conn=(Connection)Connector.configDB();
                java.sql.Statement stm=conn.createStatement();
                java.sql.ResultSet res=stm.executeQuery(sql);
                
                while(res.next()){
                    model.addRow(new Object[]{
                        no++,
                        res.getString(1),
                        res.getString(2),
                        res.getString(3),
                       });
                }
                frm.tablePakaian.setModel(model);
            }catch(SQLException e){
                System.out.println("Error "+e.getMessage());
            }
        }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==frm.btnTambah){
            KosongDataPakaian();
        }else if(ae.getSource()==frm.btnSimpan){
            data.setIdPakaian(frm.txtIdPakaian.getText());
            data.setJenis(frm.txtJenis.getText());
            data.setHarga(frm.txtHarga.getText());
            
            
            try {
                if(data.SimpanDataPakaian(data)){
                    JOptionPane.showMessageDialog(null, "Data Telah Disimpan");
                    KosongDataPakaian();
                    TampilDataPakaian();
                }
            }catch (SQLException ex){
                JOptionPane.showMessageDialog(null, ex);
            }
        }else if(ae.getSource()==frm.btnEdit){
            data.setIdPakaian(frm.txtIdPakaian.getText());
            data.setJenis(frm.txtJenis.getText());
            data.setHarga(frm.txtHarga.getText());
            
            
            try {
                if(data.UpdateDataPakaian(data)){
                    JOptionPane.showMessageDialog(null, "Update Anda Berhasil");
                    KosongDataPakaian();
                    TampilDataPakaian();
                }
            }catch (SQLException ex){
                JOptionPane.showMessageDialog(null, ex);
            }
        }else {
            data.setIdPakaian(frm.txtIdPakaian.getText());
            
            try {
                if(data.HapusDataPakaian(data)){
                    JOptionPane.showMessageDialog(null, "Berhasil Menghapus");
                    KosongDataPakaian();
                    TampilDataPakaian();
                }
            }catch (SQLException ex){
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if(me.getSource()==frm.tablePakaian){
            frm.txtIdPakaian.setEditable(false);
            
            int baris=frm.tablePakaian.rowAtPoint(me.getPoint());
            String idPakaian=frm.tablePakaian.getValueAt(baris, 1).toString();
            frm.txtIdPakaian.setText(idPakaian);
            String Jenis=frm.tablePakaian.getValueAt(baris, 2).toString();
            frm.txtJenis.setText(Jenis);
            String Harga=frm.tablePakaian.getValueAt(baris, 3).toString();
            frm.txtHarga.setText(Harga);
            
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
