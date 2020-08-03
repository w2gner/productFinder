package graphic;

import java.sql.Connection;
import java.awt.*;
import java.sql.SQLException;
import java.beans.PropertyVetoException;
import javax.swing.JButton;
import javax.swing.JFrame;

public class ListagemWindow extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JButton bntListar;

    public ListagemWindow(final Connection connection) throws SQLException, PropertyVetoException {

        setSize(690, 400);
        setLocationRelativeTo(null);
        setTitle(" Cadastro de Usuário");
        setVisible(true);
        setIconImage(Toolkit.getDefaultToolkit().getImage("icons/logo.png"));

        bntListar = new JButton("Listar");
        bntListar.setBounds(50, 110, 300, 25);
        getContentPane().add(bntListar);

    }
}