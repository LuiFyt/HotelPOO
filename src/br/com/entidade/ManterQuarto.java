/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.entidade;

import br.com.controle.Quarto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author LuiF
 */
public class ManterQuarto extends DAO {
    
    public void inserirQuarto(Quarto q) throws Exception {
        try {
        abrirBanco();
        String query = "INSERT INTO quarto(id_quarto,tipo,qtdCamas,valor,reservado) "
            + "values(null,?,?,?,?)";
        pst=(PreparedStatement) con.prepareStatement(query);
        pst.setString(1, q.getTipo());
        pst.setString(2, String.valueOf(q.getQtdCama()));
        pst.setDouble(3, Double.valueOf(q.getSoma()));
        pst.setBoolean(4, q.isReservado());
        pst.execute();
        fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }  
    }
        
    public void deletarQuarto(Quarto q) throws Exception{
	abrirBanco();
	String query = "delete from quarto where id_quarto=?";
	pst=(PreparedStatement) con.prepareStatement(query);
	pst.setInt(1, q.getCodigo());
	pst.execute();
        JOptionPane.showMessageDialog(null, "Quarto retirado do sistema!");
	fecharBanco();
    }
    
    public ArrayList<Quarto> PesquisarQuarto() throws Exception {
       ArrayList<Quarto> quartos = new ArrayList<Quarto>();
         try{
         abrirBanco();  
         String query = "select * FROM quarto order by id_quarto";
         pst = (PreparedStatement) con.prepareStatement(query);
         ResultSet tr = pst.executeQuery();
         Quarto q;
         while (tr.next()){               
           q = new Quarto();
           q.setCodigo(tr.getInt("id_quarto"));
           q.setTipo(tr.getString("tipo"));
           q.setQtdCama(tr.getInt("qtdCamas"));
           q.setSoma(tr.getDouble("valor"));
           q.setReservado(tr.getBoolean("reservado"));
           quartos.add(q);
         } 
         fecharBanco();
       }catch (Exception e){
           System.out.println("Erro " + e.getMessage());
     } 
       return quartos;
     }
    
    public void AtualizarQuarto(Quarto q) throws Exception {
        try {
            abrirBanco();
            String query = "update quarto set tipo=?, qtdCamas=?, valor=? WHERE id_quarto=?";
            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setString(1, q.getTipo());
            pst.setInt(2, q.getQtdCama());
            pst.setDouble(3, q.getSoma());
            pst.setInt(4, q.getCodigo());
            pst.execute();
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        } 
    }
    
        public ArrayList<Quarto> PesquisarQuartoVazio() throws Exception {
       ArrayList<Quarto> quartos = new ArrayList<Quarto>();
         try{
         abrirBanco();  
         String query = "select * FROM quarto WHERE reservado is false order by tipo";
         pst = (PreparedStatement) con.prepareStatement(query);
         ResultSet tr = pst.executeQuery();
         Quarto q;
         while (tr.next()){               
           q = new Quarto();
           q.setCodigo(tr.getInt("id_quarto"));
           q.setTipo(tr.getString("tipo"));
           q.setQtdCama(tr.getInt("qtdCamas"));
           q.setSoma(tr.getDouble("valor"));
           q.setReservado(tr.getBoolean("reservado"));
           quartos.add(q);
         } 
         fecharBanco();
       }catch (Exception e){
           System.out.println("Erro " + e.getMessage());
     } 
       return quartos;
     }
        
    public void reservado(Quarto q) throws Exception {
        try {
            abrirBanco();
            String query = "update quarto set reservado=true WHERE id_quarto=?";
            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setInt(1, q.getCodigo());
            pst.execute();
            System.out.println(query);
            fecharBanco();   
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
    }
}
