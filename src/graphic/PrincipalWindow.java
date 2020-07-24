package graphic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.SQLException;

public class PrincipalWindow extends JFrame {

    private static final long serialVersionUID = 1L;
    private JLabel lblimagemcurso;
    private JMenuBar menuBar;
    private JMenu menuControle, menuPesquisar, menuAjuda;
    private JMenuItem menuUsuarios, menuProduto, menuSair, menuPesquisa;

    public PrincipalWindow(Connection conn) {
        setSize(1150, 650);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.darkGray);
        setTitle("Localizador de produto em supermercado");
        setIconImage(Toolkit.getDefaultToolkit().getImage("icons/logo.png"));
        lblimagemcurso = new JLabel(new ImageIcon("icons/BackGround.png"));

        add(lblimagemcurso);
        JPanel panel = new JPanel();
        panel.setBackground(Color.DARK_GRAY);
        setLayout(new GridBagLayout());
        lblimagemcurso.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        add(panel);
        setVisible(true);
        setLocationRelativeTo(null);
        setSize(1150, 650);
        getContentPane().add(lblimagemcurso);

        menuBar = new JMenuBar();
        menuControle = new JMenu("Controle");
        menuPesquisar = new JMenu("Pesquisar");
        menuAjuda = new JMenu("Ajuda");

        menuUsuarios = new JMenuItem(new AbstractAction("Cadastro de Usuário") {

            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new AlunoWindow(conn).setVisible(true);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        menuProduto = new JMenuItem(new AbstractAction("Cadastro de Produto") {

            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new AlunoWindow(conn).setVisible(true);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        menuSair = new JMenuItem(new AbstractAction("Sair") {
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);
            }
        });

        menuPesquisa = new JMenuItem(new AbstractAction("Alunos") {

            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new AlunoWindow(conn).setVisible(true);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

            }
        });

        menuControle.add(menuUsuarios);
        menuControle.add(menuProduto);
        menuControle.add(menuSair);
        menuPesquisar.add(menuPesquisa);

        menuBar.add(menuControle);
        menuBar.add(menuPesquisar);
        menuBar.add(menuAjuda);

        setJMenuBar(menuBar);

    }
}
