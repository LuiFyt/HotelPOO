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
public class ManterCliente extends DAO {
    public void inserirCliente(Cliente c) throws Exception {
        try {
        abrirBanco();
        String query = "INSERT INTO cliente(id_cliente,nome_cliente,CPF,dataDeNascimento) "
            + "values(null,?,?,?)";
        pst=(PreparedStatement) con.prepareStatement(query);
        pst.setString(1, c.getNome());
        pst.setString(2, c.getCPF());
        pst.setString(3, c.getDataNascimento());
        pst.execute();
        fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        }  
    }
		
    public void deletarReserva(Cliente c) throws Exception{
	abrirBanco();
	String query = "delete from cliente where id_cliente=?";
	pst=(PreparedStatement) con.prepareStatement(query);
	pst.setInt(1, c.getCodigo());
	pst.execute();
        JOptionPane.showMessageDialog(null, "Cadastro do cliente retirado do sistema com sucesso!");
	fecharBanco();
    }
    //listar reservas feitas
    public ArrayList<Cliente> PesquisarCliente() throws Exception {
       ArrayList<Cliente> clientes = new ArrayList<Cliente>();
         try{
         abrirBanco();  
         String query = "select id_cliente, nome_cliente, CPF, dataDeNascimento from cliente order by id_cliente;";
         pst = (PreparedStatement) con.prepareStatement(query);
         ResultSet tr = pst.executeQuery();
         Cliente c;
         while (tr.next()){               
           c = new Cliente();
           c.setCodigo(tr.getInt("id_cliente"));
           c.setNome(tr.getString("nome_cliente"));
           c.setCPF(tr.getString("cpf"));
           c.setDataNascimento(tr.getString("dataDeNascimento"));
           clientes.add(c);
         } 
         fecharBanco();
       }catch (Exception e){
           System.out.println("Erro " + e.getMessage());
     } 
       return clientes;
     }
    
    //atualizar a informação de uma reserva
    public void AtualizarReserva(Cliente c) throws Exception {
        try {
            abrirBanco();
            String query = "update cliente set nome_cliente=?, cpf=?, dataDeNascimento=? WHERE id_cliente=?";
            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setString(1, c.getNome());
            pst.setString(2, c.getCPF());
            pst.setString(3, c.getDataNascimento());
            pst.setInt(4, c.getCodigo());
            pst.execute();
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        } 
    }
}
