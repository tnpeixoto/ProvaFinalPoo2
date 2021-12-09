package dao;

import model.Ativo;
import java.util.List;

public interface IAtivoDao {
  int save (Ativo ativo);
  int update (Ativo ativo);
  int remove (Long id);
  List<Ativo> findAll();





}
