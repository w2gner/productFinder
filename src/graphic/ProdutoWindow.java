package graphic;

// import database.dao.AlunoDAO;
// import database.model.Aluno;
// import lib.MLFDataTextField;
// import lib.Observer;

import javax.swing.*;

import database.dao.ProdutoDAO;
import database.model.Produto;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.SQLException;
// import java.util.ArrayList;
// import java.util.List;

public class ProdutoWindow extends JFrame {

    private static final long serialVersionUID = 1L;
    // private Connection connection;
    private JLabel lblNome;
    private JTextField txfNome;
    private JLabel lblDescricao;
    private JTextField txfDescricao;
    private JLabel lblLocalizacao;
    private JTextField txfLocalização;
    private JLabel lblCodigo;
    private JLabel lblimagemcurso;
    private JTextField txfCodigo;
    private JButton btnSalvar;
    private JButton btnCancelar;
    // private AlunoDAO io_aluno_dao;
    private final ImageIcon alertIcon = new ImageIcon("icons/alerta.png");

    public ProdutoWindow(final Connection connection) throws SQLException {
        // this.connection = connection;
        setLayout(null);
        setSize(690, 400);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle(" Cadastro de Produto");
        setIconImage(Toolkit.getDefaultToolkit().getImage("icons/logo.png"));

        lblimagemcurso = new JLabel(new ImageIcon("icons/cadastro.png"));
        lblimagemcurso.setBounds(400, 60, 200, 200);
        getContentPane().add(lblimagemcurso);

        lblNome = new JLabel("Nome do produto");
        lblNome.setBounds(50, 35, 150, 25);
        getContentPane().add(lblNome);

        txfNome = new JTextField();
        txfNome.setBounds(50, 55, 300, 25);
        getContentPane().add(txfNome);

        lblDescricao = new JLabel("Descrição do produto");
        lblDescricao.setBounds(50, 90, 150, 25);
        getContentPane().add(lblDescricao);

        txfDescricao = new JTextField();
        txfDescricao.setBounds(50, 110, 300, 25);
        getContentPane().add(txfDescricao);

        lblLocalizacao = new JLabel("Localização do produto");
        lblLocalizacao.setBounds(50, 145, 150, 25);
        getContentPane().add(lblLocalizacao);

        txfLocalização = new JTextField();
        txfLocalização.setBounds(50, 165, 300, 25);
        getContentPane().add(txfLocalização);

        lblCodigo = new JLabel("Código de Barras");
        lblCodigo.setBounds(50, 205, 150, 25);
        getContentPane().add(lblCodigo);

        txfCodigo = new JTextField();
        txfCodigo.setBounds(50, 225, 300, 25);
        getContentPane().add(txfCodigo);

        btnSalvar = new JButton(new AbstractAction("Salvar") {

            private static final long serialVersionUID = 1L;
            // private AlunoDAO alunoIO = new AlunoDAO(connection);
            // private List<Object> alunos = new ArrayList<Object>();
            // private Boolean isUpdate = false;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (txfCodigo.getText().isBlank() || txfDescricao.getText().isBlank()
                        || txfLocalização.getText().isBlank() || txfNome.getText().isBlank()) {
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos", "Aviso",
                            JOptionPane.WARNING_MESSAGE, alertIcon);
                } else {
                    try {
                        ProdutoDAO produtoIO = new ProdutoDAO(connection);
                        Produto produto = new Produto();

                        produto.setCodigoBarras(Integer.parseInt(txfCodigo.getText()));
                        produto.setDescricao(txfDescricao.getText());
                        produto.setLocalizacao(txfLocalização.getText());
                        produto.setNome(txfNome.getText());

                        produtoIO.Insert(produto);

                        LimpaTela();
                        JOptionPane.showMessageDialog(null, "Produto salvo com sucesso!", "Aviso",
                                JOptionPane.WARNING_MESSAGE, alertIcon);
                    } catch (SQLException e1) {
                        JOptionPane.showMessageDialog(null, "Ocorreu um erro ao salvar o produto!", "Aviso",
                                JOptionPane.WARNING_MESSAGE, alertIcon);
                        e1.printStackTrace();
                    }
                }
            }
        });
        btnSalvar.setBounds(190, 310, 115, 25);
        getContentPane().add(btnSalvar);

        btnCancelar = new JButton(new AbstractAction("Cancelar") {

            private static final long serialVersionUID = 1L;
            // private AlunoDAO alunoIO = new AlunoDAO(connection);
            // private List<Object> alunos = new ArrayList<Object>();
            // private Boolean isUpdate = false;

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnCancelar.setBounds(325, 310, 115, 25);
        getContentPane().add(btnCancelar);

    }

    public void LimpaTela() {
        txfNome.setText("");
        txfCodigo.setText("");
        txfDescricao.setText("");
        txfLocalização.setText("");
    }

}
