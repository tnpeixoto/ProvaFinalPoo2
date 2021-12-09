package facade;

import model.Ativo;
import dao.AtivoDao;
import dao.IAtivoDao;

import java.util.List;

public class AtivoFacade {
    private IAtivoDao dao;

    public AtivoFacade() {
        this.dao = new AtivoDao();
    }
    public int save (Ativo ativo){
        return dao.save(ativo);
    }
    public int update (Ativo ativo){
        return dao.update(ativo);
    }
    public int remove (Long id){
        return dao.remove(id);
    }
    public List<Ativo> findAll(){
        return dao.findAll();
    }
}
