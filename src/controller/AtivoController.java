package controller;

import model.Ativo;
import facade.AtivoFacade;

import java.util.List;

public class AtivoController {
    private AtivoFacade facade;

    public AtivoController() {
        this.facade = new AtivoFacade();
    }

    public int addAtivo(Ativo ativo){
        return facade.save(ativo);
    }
    public int alterarAtivo(Ativo ativo){
        return facade.update(ativo);
    }
    public int excluirAtivo(Long id){

        return facade.remove(id);
    }
    public List<Ativo> findAtivos(){
        return facade.findAll();
    }
}
