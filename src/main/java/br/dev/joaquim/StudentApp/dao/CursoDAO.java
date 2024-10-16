package br.dev.joaquim.StudentApp.dao;

import java.util.List;

import br.dev.joaquim.StudentApp.entities.Curso;

public interface CursoDAO {
  public boolean create(Curso curso);

  public List<Curso> findAll();

  public Curso findByCodigo(int codigo);

  public boolean update(Curso curso);

  public boolean delete(int codigo);
}

