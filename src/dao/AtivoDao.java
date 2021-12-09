package dao;

import model.Ativo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AtivoDao implements IAtivoDao {


    private static final String SQL_INSERT =  "INSERT INTO carteira (ticker,alocacao, precoentrada) values (?,?,?) ";
    private static final String SQL_UPDATE = "UPDATE carteira SET ticker = ?, alocacao = ?, precoentrada = ? where id = ?";
    private static final String SQL_REMOVE =  "DELETE from carteira where id=?";
    private static final String SQL_FIND_ALL = "SELECT * FROM carteira";



    public int save(Ativo ativo) {
        Connection conn = DBConnection.getConnection();
        PreparedStatement pstm = null;
        int result = 0;
        try {
            pstm = conn.prepareStatement(SQL_INSERT);
            pstm.setString(1, ativo.getTicker());
            pstm.setString(2, ativo.getAlocacao());
            pstm.setString(3, ativo.getPrecoEntrada());
            result = pstm.executeUpdate();
        } catch (SQLException e) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            } finally {
                DBConnection.close(conn, pstm, null);
            }
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int update(Ativo ativo) {
        Connection conn = DBConnection.getConnection();
        PreparedStatement pstm = null;
        int result = 0;
        try {
            pstm = conn.prepareStatement(SQL_UPDATE);
            pstm.setString(1, ativo.getTicker());
            pstm.setString(2, ativo.getAlocacao());
            pstm.setString(3, ativo.getPrecoEntrada());
            pstm.setLong(4,ativo.getId());
            result = pstm.executeUpdate();
        } catch (SQLException e) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            } finally {
                DBConnection.close(conn, pstm, null);
            }
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int remove(Long id) {
        Connection conn = DBConnection.getConnection();
        PreparedStatement pstm = null;
        int result = 0;
        try {
            pstm = conn.prepareStatement(SQL_REMOVE);
            pstm.setLong(1,id);
            result = pstm.executeUpdate();
        } catch (SQLException e) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            } finally {
                DBConnection.close(conn, pstm, null);
            }
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Ativo> findAll() {
        Connection conn = DBConnection.getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<Ativo> ativos = new ArrayList<Ativo>();
        try {
            pstm = conn.prepareStatement(SQL_FIND_ALL);
            rs = pstm.executeQuery();
            while(rs.next()){
                Ativo ativo = new Ativo();
                ativo.setId(rs.getLong("id"));
                ativo.setTicker(rs.getString("ticker"));
                ativo.setAlocacao(rs.getString("alocacao"));
                ativo.setPrecoEntrada(rs.getString("precoentrada"));
                ativos.add(ativo);

            }
        } catch (SQLException e) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            } finally {
                DBConnection.close(conn, pstm, rs);
            }
            e.printStackTrace();
        }
        return ativos;
    }
}
