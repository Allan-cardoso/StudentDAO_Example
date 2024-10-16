package br.dev.joaquim.StudentApp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.dev.joaquim.StudentApp.entities.Curso;

public class H2CursoDAO implements CursoDAO {

  private Connection connection;
  private String url = "jdbc:h2:file:~/data/cursos;";
  private String user = "root";
  private String password = "root";

  public H2CursoDAO() {
    connect();
    createTableIfNotExists();
  }

  private void connect() {
    try {
      this.connection = DriverManager.getConnection(url, user, password);
    } catch (SQLException ex) {
      this.connection = null;
      System.out.println("Problema ao conectar no banco de dados");
      ex.printStackTrace();
    }
  }

  private void createTableIfNotExists() {
    try {
      String sql = "CREATE TABLE IF NOT EXISTS cursos(" +
          "codigo INT, nome VARCHAR(256), PRIMARY KEY (codigo));";
      PreparedStatement stmt = connection.prepareStatement(sql);
      stmt.execute();
    } catch (SQLException ex) {
      System.out.println("Problema ao criar a tabela");
      ex.printStackTrace();
    } catch (NullPointerException ex) {
      System.out.println("Problema ao criar a tabela (sem conexao)");
    }
  }

  @Override
  public boolean create(Curso curso) {
    try {
      String sql = "INSERT INTO cursos VALUES(?,?)";
      PreparedStatement stmt = connection.prepareStatement(sql);
      stmt.setInt(1, curso.getCodigo());
      stmt.setString(2, curso.getNome());
      stmt.executeUpdate();

      return true;

    } catch (SQLException ex) {
      System.out.println("Problema ao criar curso");
      ex.printStackTrace();
    } catch (NullPointerException ex) {
      System.out.println("Problema ao criar curso (sem conexao)");
    }

    return false;
  }

  @Override
  public List<Curso> findAll() {
    try {
      String sql = "SELECT * FROM cursos";
      PreparedStatement stmt = connection.prepareStatement(sql);
      ResultSet rs = stmt.executeQuery();

      List<Curso> cursos = new ArrayList<>();

      while (rs.next()) {
        int codigo = rs.getInt("codigo");
        String nome = rs.getString("nome");
        Curso curso = new Curso(codigo, nome);
        cursos.add(curso);
      }

      return cursos;

    } catch (SQLException ex) {
      System.out.println("Problema ao buscar cursos");
      ex.printStackTrace();
    } catch (NullPointerException ex) {
      System.out.println("Problema ao buscar cursos (sem conexao)");
    }

    return new ArrayList<>();
  }

  @Override
  public Curso findByCodigo(int codigo) {
    try {
      String sql = "SELECT * FROM cursos WHERE codigo = ?";
      PreparedStatement stmt = connection.prepareStatement(sql);
      stmt.setInt(1, codigo);
      ResultSet rs = stmt.executeQuery();

      if (rs.next()) {
        String nome = rs.getString("nome");
        return new Curso(codigo, nome);
      }
    } catch (SQLException ex) {
      System.out.println("Problema ao buscar curso pelo código");
      ex.printStackTrace();
    } catch (NullPointerException ex) {
      System.out.println("Problema ao buscar curso pelo código (sem conexao)");
    }

    return null;
  }

  @Override
  public boolean update(Curso curso) {
    try {
      String sql = "UPDATE cursos SET nome = ? WHERE codigo = ?";
      PreparedStatement stmt = connection.prepareStatement(sql);
      stmt.setString(1, curso.getNome());
      stmt.setInt(2, curso.getCodigo());
      stmt.executeUpdate();

      return true;

    } catch (SQLException ex) {
      System.out.println("Problema ao atualizar curso");
      ex.printStackTrace();
    } catch (NullPointerException ex) {
      System.out.println("Problema ao atualizar curso (sem conexao)");
    }

    return false;
  }

  @Override
  public boolean delete(int codigo) {
    try {
      String sql = "DELETE FROM cursos WHERE codigo = ?";
      PreparedStatement stmt = connection.prepareStatement(sql);
      stmt.setInt(1, codigo);
      stmt.executeUpdate();

      return true;

    } catch (SQLException ex) {
      System.out.println("Problema ao apagar curso");
      ex.printStackTrace();
    } catch (NullPointerException ex) {
      System.out.println("Problema ao apagar curso (sem conexao)");
    }

    return false;
  }
}

