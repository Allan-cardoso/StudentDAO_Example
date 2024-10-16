package br.dev.joaquim.StudentApp.entities;

public class Curso {
  private int codigo;
  private String nome;

  public Curso() {
  }

  public Curso(int codigo, String nome) {
    this.codigo = codigo;
    this.nome = nome;
  }

  public int getCodigo() {
    return codigo;
  }

  public String getNome() {
    return nome;
  }

  public void setCodigo(int codigo) {
    this.codigo = codigo;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  @Override
  public String toString() {
    return "Curso " + getNome() + " [" + getCodigo() + "]";
  }
}
