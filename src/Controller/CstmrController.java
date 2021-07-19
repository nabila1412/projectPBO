/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Connector;
import Model.Customer;
import View.dataCustomer;
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

public class CstmrController implements ActionListener, MouseListener{
    private Customer data;
    private dataCustomer frm;
        
    public CstmrController (Customer data, dataCustomer frm){
        this.data = data;
        this.frm = frm;
        this.frm.btnEdit.addActionListener(this);
        this.frm.btnSimpan.addActionListener(this);
        this.frm.btnEdit.addActionListener(this);
        this.frm.btnHapus.addActionListener(this);
        this.frm.tableCustomer.addMouseListener(this);
    }
    
    public void KosongDataCustomer(){
        frm.txtIdCustomer.setText(null); 
        frm.txtNama.setText(null);
        frm.txtNoHp.setText(null);
        frm.txtAlamat.setText(null);
    }
    
     public void TampilDataCustomer(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("No");
        model.addColumn("id Customer");
        model.addColumn("Nama Customer");
        model.addColumn("No Hp");
        model.addColumn("Alamat");
        
        try{
            int no=1;
            String sql = "SELECT * FROM customer";
            java.sql.Connection conn = (Connection)Connector.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            
            while(res.next()){
                model.addRow(new Object[]{
                 no++,
                res.getString(1),
                res.getString(2),
                res.getString(3),
                res.getString(4)
                });
                
            }
                frm.tableCustomer.setModel(model);
            
                  }catch(SQLException e){
                    System.out.println("Error " + e.getMessage());
                } 
        }
    
     @Override
        public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==frm.btnTambah){
            KosongDataCustomer();
        }else if(ae.getSource()== frm.btnSimpan){
            data.setId(frm.txtIdCustomer.getText());
            data.setNama(frm.txtNama.getText());
            data.setNoHp(frm.txtNoHp.getText());
            data.setAlamat(frm.txtAlamat.getText());
            
            try{
                if(data.SimpanCustomer(data)){
                    JOptionPane.showMessageDialog(null, "Simpan Data Baru Berhasil");
                    KosongDataCustomer();
                    TampilDataCustomer();
                }
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex);
            }
        }else if(ae.getSource()==frm.btnEdit){
            data.setId(frm.txtIdCustomer.getText());
            data.setNama(frm.txtNama.getText());
            data.setNoHp(frm.txtNoHp.getText());
            data.setAlamat(frm.txtAlamat.getText());
            
            try{
                if(data.UpdateCustomer(data)){
                    JOptionPane.showMessageDialog(null, "Update Data Baru Berhasil");
                    KosongDataCustomer();
                    TampilDataCustomer();
                }
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex);
            }
        }else{
            data.setId(frm.txtIdCustomer.getText());
            try{
                if(data.HapusCustomer(data)){
                    JOptionPane.showMessageDialog(null, "Hapus Data Mahasiswa Berhasil");
                    KosongDataCustomer();
                    TampilDataCustomer();
                }
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    }
        @Override
        public void mouseClicked(MouseEvent me){
            if(me.getSource()==frm.tableCustomer){
        
                
                int baris = frm.tableCustomer.rowAtPoint(me.getPoint());
                String id = frm.tableCustomer.getValueAt(baris, 1).toString();
                frm.txtIdCustomer.setText(id);
                String nama = frm.tableCustomer.getValueAt(baris, 2).toString();
                frm.txtNama.setText(nama);
                String NoHp = frm.tableCustomer.getValueAt(baris, 3).toString();
                frm.txtNoHp.setText(NoHp);
                String Alamat = frm.tableCustomer.getValueAt(baris, 4).toString();
                frm.txtAlamat.setText(Alamat);
            }
        }
        
        @Override
        public void mousePressed(MouseEvent me){
            
        }
        @Override
        public void mouseReleased(MouseEvent me){
            
        }
        @Override
        public void mouseEntered(MouseEvent me){
            
        }
        @Override
        public void mouseExited(MouseEvent me){                 
        }
}
