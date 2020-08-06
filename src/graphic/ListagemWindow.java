package graphic;

import java.sql.Connection;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.beans.PropertyVetoException;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import database.dao.ProdutoDAO;
import database.dao.UsuarioDAO;
import database.model.Produto;
import database.model.Usuario;

public class ListagemWindow extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JButton btnPesquisar;
    private JLabel lblDescricao;
    private JTextField txfDescricao;
    private JPanel painelTabela;
    private JTable table;
    private DefaultTableModel modelo = new DefaultTableModel();
    private ArrayList<Usuario> usuarioDados = new ArrayList<>();
    private ArrayList<Produto> produtosDados = new ArrayList<>();

    public ListagemWindow(final Connection connection) throws SQLException, PropertyVetoException {
        setSize(690, 400);
        setTitle(" Pesquisar");
        setResizable(true);
        setLocationRelativeTo(null);
        setIconImage(Toolkit.getDefaultToolkit().getImage("icons/logo.png"));

        lblDescricao = new JLabel("Digite o nome ou código");
        lblDescricao.setBounds(20, 20, 150, 25);
        getContentPane().add(lblDescricao);

        txfDescricao = new JTextField();
        txfDescricao.setBounds(190, 20, 150, 25);
        getContentPane().add(txfDescricao);

        btnPesquisar = new JButton(new AbstractAction("Listar") {

            /**
             *
             */
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    UsuarioDAO usuarioIO = new UsuarioDAO(connection);
                    ArrayList<Object> usuarios = new ArrayList<>();
                    usuarios = (ArrayList<Object>) usuarioIO.Select(null);

                    ProdutoDAO produtoIO = new ProdutoDAO(connection);
                    ArrayList<Object> produtos = new ArrayList<>();
                    produtos = (ArrayList<Object>) produtoIO.Select(null);

                    LimpaTela();

                    for (int i = 0; i < usuarios.size(); i++) {
                        Usuario usuario = (Usuario) usuarios.get(i);
                        usuarioDados.add(usuario);
                        modelo.insertRow(0,
                                new Object[] { usuario.getID(), usuario.getNome(), "Usuário", usuario.getCriadoEm() });
                    }

                    for (int i = 0; i < produtos.size(); i++) {
                        Produto produto = (Produto) produtos.get(i);
                        produtosDados.add(produto);
                        modelo.insertRow(0,
                                new Object[] { produto.getId(), produto.getNome(), "Produto", produto.getCriadoEm() });
                    }

                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        btnPesquisar.setBounds(360, 20, 100, 25);
        getContentPane().add(btnPesquisar);

        modelo.addColumn("ID");
        modelo.addColumn("Nome");
        modelo.addColumn("Tipo");
        modelo.addColumn("Criado em");

        // Data to be displayed in the JTable
        // String[][] data = { { "Kundan Kumar Jha", "4031", "CSE" }, { "Anand Jha",
        // "6014", "IT" } };
        // String[][] data = {};

        painelTabela = new JPanel();
        painelTabela.setBounds(20, 300, 500, 400);

        // Column Names
        // String[] columnNames = { "ID", "Nome", "Criado em" };

        // Initializing the JTable
        table = new JTable(modelo);
        table.setBounds(30, 50, 300, 200);

        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(30, 100, 200, 300);
        painelTabela.add(sp);

        table.setAlignmentY(300);
        table.setVisible(true);

        getContentPane().add(painelTabela);

    }

    public static void main(String[] args) throws SQLException, PropertyVetoException {
        new ListagemWindow(null).setVisible(true);
    }

    public void LimpaTela() {
        // txfDescricao.setText("");
        for (int i = modelo.getRowCount() - 1; i >= 0; i--) {
            modelo.removeRow(i);
        }
    }
}