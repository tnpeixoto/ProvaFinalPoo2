
/*
União Metropolitana de Educação e Cultura
Bacharelado em Sistemas de Informação
Programação Orientada a Objetos II
Prof. Pablo Ricardo Roxo Silva
Tiago Nogueira Peixoto
*/
import dao.DBConnection;
import form.AtivoForm;

public class Main {
    public static void main(String[] args){
        DBConnection.createTable();
        new AtivoForm();

    }
}
