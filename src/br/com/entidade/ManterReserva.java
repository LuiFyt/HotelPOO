/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.entidade;

import br.com.controle.Cliente;
import br.com.controle.Reserva;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author LuiF
 */
public class ManterReserva extends DAO {
    
    //metodo para inserir reserva
    public void inserirReserva(Reserva r, int cpf) throws Exception {
        try {
        abrirBanco();
        String query = "INSERT INTO reserva(id_reserva,qtdPessoas,tempoDeEstadia,id_quarto,id_cliente) "
            + "values(null,?,?,?,?)";
        pst=(PreparedStatement) con.prepareStatement(query);
        pst.setInt(1, r.getNumeroDePessoas());
        pst.setInt(2, r.getTempoDeEstadia());
        pst.setInt(3, r.getNumeroDoQuarto());
        pst.setInt(4, cpf);
        pst.execute();
        fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }  
    }
    //metodo para deletar reserva		
    public void deletarReserva(Reserva r) throws Exception{
	abrirBanco();
	String query = "delete from reserva where id_reserva=?";
	pst=(PreparedStatement) con.prepareStatement(query);
	pst.setInt(1, r.getCodigo());
	pst.execute();
        JOptionPane.showMessageDialog(null, "Reserva cancelada com sucesso!");
	fecharBanco();
    }
    //listar reservas feitas
    public ArrayList<Reserva> PesquisarReserva() throws Exception {
       ArrayList<Reserva> reservas = new ArrayList<Reserva>();
         try{
         abrirBanco();  
         String query = "select r.id_reserva, c.id_cliente, r.tempoDeEstadia, q.id_quarto, r.qtdPessoas from reserva as r JOIN quarto as q on (r.id_quarto = q.id_quarto)"
                 + "JOIN cliente as c on (r.id_cliente = c.id_cliente) order by id_reserva;";
         pst = (PreparedStatement) con.prepareStatement(query);
         ResultSet tr = pst.executeQuery();
         Reserva r;
         while (tr.next()){               
           r = new Reserva();
           r.setCodigo(tr.getInt("id_reserva"));
           r.setNumeroDePessoas(tr.getInt("qtdPessoas"));
           r.setTempoDeEstadia(tr.getInt("tempoDeEstadia"));
           r.setNumeroDoQuarto(tr.getInt("id_quarto"));
           r.setCodigoDoCliente(tr.getInt("id_cliente"));
           reservas.add(r);
         } 
         fecharBanco();
       }catch (Exception e){
           System.out.println("Erro " + e.getMessage());
     } 
       return reservas;
     }
    
    //atualizar a informação de uma reserva
    public void AtualizarReserva(Reserva r) throws Exception {
        try {
            abrirBanco();
            String query = "update reserva set qtdPessoas=?, tempoDeEstadia=?, id_quarto=?, id_cliente=? WHERE id_reserva=?";
            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setInt(1, r.getNumeroDePessoas());
            pst.setInt(2, r.getTempoDeEstadia());
            pst.setInt(3, r.getNumeroDoQuarto());
            pst.setInt(4, r.getCodigoDoCliente());
            pst.setInt(5, r.getCodigo());
            pst.execute();
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        } 
    }
    
    public int getIdByCPF(String cpf) {
        int id = 0;
        
        try {
            abrirBanco();
            String query = "SELECT id_cliente FROM cliente WHERE cpf = ?";
            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setString(1, cpf);
            ResultSet data = pst.executeQuery();
            
            if (data.next() == false) throw new NullPointerException("NÃO FOI POSSIVEl ENCONTRAR USUARIO OU USUARIO NÃO EXISTE");
			
            id = data.getInt("id_cliente");
            
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }
        
        return id;
    }
 }
