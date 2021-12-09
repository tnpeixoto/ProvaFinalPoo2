package table;

import model.Ativo;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class AtivoTableModel extends AbstractTableModel {
    private static final int COL_id =0;
    private static final int COL_ticker =1;
    private static final int COL_alocacao =2;
    private static final int COL_precoentrada =3;

    private List<Ativo> valores;

    public AtivoTableModel(List<Ativo> valores) {
        this.valores = valores;
    }

    @Override
    public int getRowCount() {
        return valores.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Ativo ativo = valores.get(rowIndex);
        if(columnIndex == COL_id){
            return ativo.getId();
        }else if (columnIndex == COL_ticker){
            return ativo.getTicker();
        }else if (columnIndex == COL_alocacao) {
            return ativo.getAlocacao();
        }else if (columnIndex == COL_precoentrada){
            return ativo.getPrecoEntrada();
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        String coluna = "";
        switch (column){
            case COL_id:
                coluna = "Código";
                break;
            case COL_ticker:
                coluna = "Ticker";
                break;
            case COL_alocacao:
                coluna = "Alocação";
                break;
            case COL_precoentrada:
                coluna = "Valor de Entrada";
                break;
            default:
                throw new IllegalArgumentException("Coluna Inválida !");
        }

        return coluna;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if(columnIndex == COL_id){
            return int.class;
        }else if (columnIndex == COL_ticker){
            return String.class;
        }else if (columnIndex == COL_alocacao) {
            return String.class;
        }else if (columnIndex == COL_precoentrada){
            return String.class;
        }
        return null;
    }
    public Ativo get(int row){
        return valores.get(row);
    }
}
