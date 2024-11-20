/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.controle;

/**
 *
 * @author LuiF
 */
public class Quarto {
    
    private int codigo;
    private String tipo;
    private int qtdCama;
    private double valorTipo = 0.0;
    private double valorCama = 0.0;
    private double soma = 0.0;
    private boolean reservado;


    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getQtdCama() {
        return qtdCama;
    }

    public void setQtdCama(int qtdCama) {
        this.qtdCama = qtdCama;
    }

    public double getValorTipo() {
        return valorTipo;
    }

    public void setValorTipo(double valorTipo) {
        this.valorTipo = valorTipo;
    }

    public double getValorCama() {
        return valorCama;
    }

    public void setValorCama(double valorCama) {
        this.valorCama = valorCama;
    }

    public boolean isReservado() {
        return reservado;
    }

    public void setReservado(boolean reservado) {
        this.reservado = reservado;
    }

    public double getSoma() {
        return soma;
    }

    public void setSoma(double soma) {
        this.soma = soma;
    }
    
    public double somaValor() {
        soma = valorTipo + valorCama;
        return soma;
    }
}
