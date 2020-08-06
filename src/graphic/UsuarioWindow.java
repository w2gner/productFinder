package graphic;

import javax.swing.*;
import database.dao.UsuarioDAO;
import database.model.Usuario;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioWindow extends JFrame {

    private static final long serialVersionUID = 1L;
    private Connection connection;
    private JLabel lblNome;
    private JTextField txfNome;
    private JLabel lblSenha;
    private JPasswordField pwfSenha;
    private JLabel lbConfirmarSenha;
    private JPasswordField pwfConfirmarSenha;
    private JLabel lblimagemcurso;
    private JButton btnSalvar;
    private JButton btnCancelar;
    private final ImageIcon alertIcon = new ImageIcon("icons/alerta.png");

    public UsuarioWindow(final Connection connection) throws SQLException {
        this.connection = connection;
        setLayout(null);
        setSize(690, 400);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle(" Cadastro de Usuário");
        setIconImage(Toolkit.getDefaultToolkit().getImage("icons/logo.png"));

        lblimagemcurso = new JLabel(new ImageIcon("icons/cadastro.png"));
        lblimagemcurso.setBounds(400, 60, 200, 200);
        getContentPane().add(lblimagemcurso);

        lblNome = new JLabel("Nome do Usuário");
        lblNome.setBounds(50, 35, 150, 25);
        getContentPane().add(lblNome);

        txfNome = new JTextField();
        txfNome.setBounds(50, 55, 300, 25);
        getContentPane().add(txfNome);

        lblSenha = new JLabel("Senha");
        lblSenha.setBounds(50, 90, 150, 25);
        getContentPane().add(lblSenha);

        pwfSenha = new JPasswordField();
        pwfSenha.setBounds(50, 110, 300, 25);
        getContentPane().add(pwfSenha);

        lbConfirmarSenha = new JLabel("Confirmar senha");
        lbConfirmarSenha.setBounds(50, 145, 150, 25);
        getContentPane().add(lbConfirmarSenha);

        pwfConfirmarSenha = new JPasswordField();
        pwfConfirmarSenha.setBounds(50, 165, 300, 25);
        getContentPane().add(pwfConfirmarSenha);

        btnSalvar = new JButton(new AbstractAction("Salvar") {

            private static final long serialVersionUID = 1L;
            private UsuarioDAO usuarioIO = new UsuarioDAO(connection);
            private List<Object> usuarios = new ArrayList<Object>();
            Usuario usuarioNovo = new Usuario();

            @Override
            @SuppressWarnings("deprecation")
            public void actionPerformed(ActionEvent e) {

                if (pwfSenha.getPassword().length == 0 || pwfConfirmarSenha.getPassword().length == 0
                        || txfNome.getText().isBlank()) {
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos", "Aviso",
                            JOptionPane.WARNING_MESSAGE, alertIcon);
                } else if (!pwfSenha.getText().equals(pwfConfirmarSenha.getText())) {
                    JOptionPane.showMessageDialog(null, "As senhas devem ser iguais", "Aviso",
                            JOptionPane.WARNING_MESSAGE, alertIcon);
                } else {

                    try {
                        usuarios = usuarioIO.Select(null);
                    } catch (SQLException e2) {
                        e2.printStackTrace();
                    }

                    for (int i = 0; i < usuarios.size(); i++) {
                        Usuario teste = (Usuario) usuarios.get(i);
                        if (teste.getNome().equals(txfNome.getText()) && teste.getSenha().equals(pwfSenha.getText())) {
                            JOptionPane.showMessageDialog(null, "Esse usuário já foi criado", "Aviso",
                                    JOptionPane.WARNING_MESSAGE, alertIcon);
                            break;
                        } else {
                            try {
                                usuarioNovo.setNome(txfNome.getText());
                                usuarioNovo.setSenha(pwfSenha.getText());

                                usuarioIO.Insert(usuarioNovo);
                                LimpaTela();
                                JOptionPane.showMessageDialog(null, "Usuário criado com sucesso", "Aviso",
                                        JOptionPane.WARNING_MESSAGE, alertIcon);
                            } catch (SQLException e1) {
                                JOptionPane.showMessageDialog(null, "Ocorreu um erro ao salvar o produto!", "Aviso",
                                        JOptionPane.WARNING_MESSAGE, alertIcon);
                                e1.printStackTrace();
                            }
                        }
                    }
                }
            }
        });
        btnSalvar.setBounds(190, 310, 115, 25);
        getContentPane().add(btnSalvar);

        btnCancelar = new JButton(new AbstractAction("Cancelar") {

            /**
             *
             */
            private static final long serialVersionUID = 1L;

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
        pwfSenha.setText("");
        pwfConfirmarSenha.setText("");
    }

}
