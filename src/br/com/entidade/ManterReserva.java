/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.entidade;

import br.com.controle.Quarto;
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
    public void inserirReserva(Reserva r) throws Exception {
        try {
        abrirBanco();
        String query = "INSERT INTO reserva(id_reserva,nome_hospede,CPF,dataDeNascimento,qtdPessoas,tempoDeEstadia,id_quarto) "
            + "values(null,?,?,?,?,?,?)";
        pst=(PreparedStatement) con.prepareStatement(query);
        pst.setString(1, r.getNome());
        pst.setString(2, r.getCPF());
        pst.setString(3, r.getDataNascimento());
        pst.setInt(4, r.getNumeroDePessoas());
        pst.setInt(5, r.getTempoDeEstadia());
        pst.setInt(6, r.getNumeroDoQuarto());
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
         String query = "select r.id_reserva, r.nome_hospede, r.CPF, r.dataDeNascimento, r.qtdPessoas, r.tempoDeEstadia, q.id_quarto from reserva as r JOIN quarto as q "
                 + "on (r.id_quarto = q.id_quarto) order by id_reserva;";
         pst = (PreparedStatement) con.prepareStatement(query);
         ResultSet tr = pst.executeQuery();
         Reserva r;
         while (tr.next()){               
           r = new Reserva();
           r.setCodigo(tr.getInt("id_reserva"));
           r.setNome(tr.getString("nome_hospede"));
           r.setCPF(tr.getString("cpf"));
           r.setDataNascimento(tr.getString("dataDeNascimento"));
           r.setNumeroDePessoas(tr.getInt("qtdPessoas"));
           r.setTempoDeEstadia(tr.getInt("tempoDeEstadia"));
           r.setNumeroDoQuarto(tr.getInt("id_quarto"));
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
            String query = "update reserva set nome_hospede=?, cpf=?, dataDeNascimento=?, qtdPessoas=?, tempoDeEstadia=?, id_quarto=? WHERE id_reserva=?";
            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setString(1, r.getNome());
            pst.setString(2, r.getCPF());
            pst.setString(3, r.getDataNascimento());
            pst.setInt(4, r.getNumeroDePessoas());
            pst.setInt(5, r.getTempoDeEstadia());
            pst.setInt(6, r.getNumeroDoQuarto());
            pst.setInt(7, r.getCodigo());
            pst.execute();
            fecharBanco();
        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage());
        } 
    }
 }
