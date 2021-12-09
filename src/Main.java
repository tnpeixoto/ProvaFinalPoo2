import dao.DBConnection;
import form.AtivoForm;

public class Main {
    public static void main(String[] args){
        DBConnection.createTable();
        new AtivoForm();

    }
}
