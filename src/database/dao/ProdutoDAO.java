package database.dao;

import database.model.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO extends MasterDAO {
    private Connection connection;
    private final String select = "select * from tb_produtos";
    private final String insert = "insert into tb_produtos(nm_produto, num_creditos, valor_creditos) VALUES (?, ?, ?)";
    private final String update = "update tb_produtos SET nm_produto=?, num_creditos=?, valor_creditos=? WHERE id_produto = ?";
    private final String delete = "delete from tb_produtos WHERE id_produto = ?";
    private final PreparedStatement pstSelect, pstInsert, pstDelete, pstUpdate;

    public ProdutoDAO(final Connection connection) throws SQLException {
        this.connection = connection;
        pstSelect = connection.prepareStatement(select);
        pstInsert = connection.prepareStatement(insert);
        pstUpdate = connection.prepareStatement(update);
        pstDelete = connection.prepareStatement(delete);
    }

    @Override
    public List<Object> Select(Object as_parameter) throws SQLException {

        pstSelect.clearParameters();

        ArrayList<Object> arlDados = new ArrayList<Object>();
        ResultSet resultado = pstSelect.executeQuery();

        while (resultado.next()) {
            Produto produto = new Produto();
            produto.setId(resultado.getInt("id_produto"));
            produto.setNome(resultado.getString("nm_produto"));
            produto.setCodigoBarras(resultado.getDouble("num_creditos"));
            produto.setDescricao(resultado.getString("valor_creditos"));
            produto.setLocalizacao(resultado.getString("valor_creditos"));

            arlDados.add(produto);

        }
        resultado.close();
        return arlDados;
    }

    @Override
    public void Insert(Object ao_object) throws SQLException {

        pstInsert.clearParameters();

        Produto produto = (Produto) ao_object;

        Set(pstInsert, 1, produto.getNome());
        Set(pstInsert, 2, produto.getCodigoBarras());
        Set(pstInsert, 3, produto.getDescricao());
        Set(pstInsert, 4, produto.getId());
        Set(pstInsert, 5, produto.getLocalizacao());

        pstInsert.execute();

    }

    @Override
    public void Update(Object ao_object) throws SQLException {

        pstUpdate.clearParameters();

        Produto produto = (Produto) ao_object;

        Set(pstUpdate, 1, produto.getNome());
        Set(pstUpdate, 2, produto.getCodigoBarras());
        Set(pstUpdate, 3, produto.getDescricao());
        Set(pstUpdate, 4, produto.getId());
        Set(pstUpdate, 5, produto.getId());

        pstUpdate.execute();

    }

    @Override
    public void Delete(Object ao_object) throws SQLException {

        pstDelete.clearParameters();

        Produto produto = (Produto) ao_object;

        Set(pstDelete, 1, produto.getId());

        pstDelete.execute();

    }
}
