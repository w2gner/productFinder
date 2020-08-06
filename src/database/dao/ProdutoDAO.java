package database.dao;

import database.model.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO extends MasterDAO {
    private Connection connection;
    private final String select = "select * from products";
    private final String insert = "insert into products(id, product_name, description, barcode, position, status, created_at) VALUES (DEFAULT, ?, ?, ?, ?, ? ,?)";
    private final String update = "update products SET product_name=?, description=?, barcode=?, position=?, status=?, created_at=? WHERE id = ?";
    private final String delete = "delete from products WHERE id = ?";
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
            produto.setId(resultado.getInt("id"));
            produto.setNome(resultado.getString("product_name"));
            produto.setCodigoBarras(resultado.getInt("barcode"));
            produto.setDescricao(resultado.getString("description"));
            produto.setLocalizacao(resultado.getString("position"));
            produto.setCriadoEm(resultado.getString("created_at"));

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
        Set(pstInsert, 2, produto.getDescricao());
        Set(pstInsert, 3, produto.getCodigoBarras());
        Set(pstInsert, 4, produto.getLocalizacao());
        Set(pstInsert, 5, null);
        pstInsert.setObject(6, LocalDate.now());

        pstInsert.execute();

    }

    @Override
    public void Update(Object ao_object) throws SQLException {

        pstUpdate.clearParameters();

        Produto produto = (Produto) ao_object;

        Set(pstUpdate, 1, produto.getNome());
        Set(pstUpdate, 2, produto.getDescricao());
        Set(pstUpdate, 3, produto.getCodigoBarras());
        Set(pstUpdate, 4, produto.getLocalizacao());
        Set(pstUpdate, 5, null);
        pstUpdate.setObject(6, LocalDate.now());

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
