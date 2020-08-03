package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import database.model.Usuario;

public class UsuarioDAO extends MasterDAO {

    private Connection connection;
    private final String select = "select * from admin";
    private final String insert = "insert into admin (id, full_name, username, password, created_at) VALUES (DEFAULT, ?, ?, ?, ?)";
    private final String update = "update admin SET  full_name=?, username=?, password=? WHERE id = ?";
    private final String delete = "delete from admin where id = ?";
    private final PreparedStatement pstSelect, pstInsert, pstDelete, pstUpdate;

    public UsuarioDAO(final Connection connection) throws SQLException {
        this.connection = connection;
        pstSelect = this.connection.prepareStatement(select);
        pstInsert = this.connection.prepareStatement(insert);
        pstUpdate = this.connection.prepareStatement(update);
        pstDelete = this.connection.prepareStatement(delete);
    }

    @Override
    public List<Object> Select(Object as_parameter) throws SQLException {

        pstSelect.clearParameters();

        ArrayList<Object> usuarios = new ArrayList<Object>();

        ResultSet resultado = pstSelect.executeQuery();

        while (resultado.next()) {
            Usuario user = new Usuario();
            user.setID(resultado.getInt("id"));
            user.setNome(resultado.getString("full_name"));
            user.setSenha(resultado.getString("password"));
            usuarios.add(user);
        }

        pstSelect.close();

        return usuarios;
    }

    @Override
    public void Insert(Object ao_object) throws SQLException {

        pstInsert.clearParameters();

        Usuario ioUsuario = (Usuario) ao_object;

        Set(pstInsert, 1, ioUsuario.getNome());
        Set(pstInsert, 2, "");
        Set(pstInsert, 3, ioUsuario.getSenha());
        pstInsert.setObject(4, LocalDate.now());

        pstInsert.execute();

    }

    @Override
    public void Update(Object ao_object) throws SQLException {

        pstUpdate.clearParameters();

        Usuario ioUsuario = (Usuario) ao_object;

        Set(pstUpdate, 1, ioUsuario.getNome());
        Set(pstUpdate, 2, "");
        Set(pstUpdate, 3, ioUsuario.getSenha());
        Set(pstUpdate, 4, ioUsuario.getID());

        pstUpdate.execute();

    }

    @Override
    public void Delete(Object ao_object) throws SQLException {

        pstDelete.clearParameters();

        Usuario ioUsuario = (Usuario) ao_object;

        Set(pstDelete, 1, ioUsuario.getID());

        pstDelete.execute();

    }
}