package JFrame;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import Model.InformacoesCadastro;
import DAO.InformacoesDao;

/**
 * Tela de Cadastro de Cliente - Versão Corrigida
 * Campo data com máscara, validação robusta e conversão para Date no banco.
 */
public class TelaCadastro extends JFrame {

    // Constantes para design consistente
    private static final Color PRIMARY_COLOR = new Color(52, 152, 219);
    private static final Color SUCCESS_COLOR = new Color(46, 204, 113);
    private static final Color ERROR_COLOR = new Color(231, 76, 60);
    private static final Color WARNING_COLOR = new Color(241, 196, 15);
    private static final Color BACKGROUND_COLOR = new Color(245, 245, 245);
    private static final Color CARD_BACKGROUND = Color.WHITE;
    private static final Font LABEL_FONT = new Font("Segoe UI", Font.BOLD, 14);
    private static final Font FIELD_FONT = new Font("Segoe UI", Font.PLAIN, 14);
    private static final Font BUTTON_FONT = new Font("Segoe UI", Font.BOLD, 16);
    private static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 20);

    // Componentes da UI
    private JTextField campoCPF;
    private JTextField campoNome;
    private JTextField campoTelefone;
    private JFormattedTextField campoDataNascimento;
    private JButton botaoCadastrar;
    private JButton botaoLimpar;
    private JProgressBar progressBar;
    private JLabel statusLabel;

    public TelaCadastro() {
        inicializarUI();
        configurarEventos();
    }

    /**
     * Inicializa a interface do usuário.
     */
    private void inicializarUI() {
        // Configurações da janela
        setTitle("Cadastro de Cliente");
        setSize(650, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setUndecorated(true);
        setShape(new RoundRectangle2D.Float(0, 0, 650, 550, 20, 20));

        // Suporte para arrastar a janela
        addMouseListener(new MouseAdapter() {
            private Point initialClick;
            @Override
            public void mousePressed(MouseEvent e) {
                initialClick = e.getPoint();
            }
        });

        // Painel principal
        JPanel painelPrincipal = new JPanel(new BorderLayout());
        painelPrincipal.setBackground(BACKGROUND_COLOR);

        // Cabeçalho
        JPanel painelCabecalho = new JPanel(new BorderLayout());
        painelCabecalho.setBackground(PRIMARY_COLOR);
        painelCabecalho.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Cadastro de Cliente", SwingConstants.CENTER);
        titulo.setFont(TITLE_FONT);
        titulo.setForeground(Color.WHITE);
        painelCabecalho.add(titulo, BorderLayout.CENTER);

        JButton btnFechar = new JButton("✕");
        btnFechar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnFechar.setForeground(Color.WHITE);
        btnFechar.setBackground(PRIMARY_COLOR);
        btnFechar.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        btnFechar.setFocusPainted(false);
        btnFechar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnFechar.addActionListener(e -> System.exit(0));
        painelCabecalho.add(btnFechar, BorderLayout.EAST);

        painelPrincipal.add(painelCabecalho, BorderLayout.NORTH);

        // Corpo do formulário
        JPanel painelCorpo = new RoundedPanel(15);
        painelCorpo.setBackground(CARD_BACKGROUND);
        painelCorpo.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        painelCorpo.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(12, 12, 12, 12);
        c.fill = GridBagConstraints.HORIZONTAL;

        // CPF
        c.gridx = 0; c.gridy = 0;
        painelCorpo.add(criarLabel("CPF (somente números):"), c);

        campoCPF = criarCampo();
        campoCPF.setToolTipText("Digite apenas números do CPF (11 dígitos)");
        c.gridx = 1; c.gridy = 0;
        painelCorpo.add(campoCPF, c);

        // Nome
        c.gridx = 0; c.gridy = 1;
        painelCorpo.add(criarLabel("Nome Completo:"), c);

        campoNome = criarCampo();
        campoNome.setToolTipText("Digite o nome completo (mín. 3 caracteres)");
        c.gridx = 1; c.gridy = 1;
        painelCorpo.add(campoNome, c);

        // Telefone
        c.gridx = 0; c.gridy = 2;
        painelCorpo.add(criarLabel("Telefone:"), c);

        campoTelefone = criarCampo();
        campoTelefone.setToolTipText("Digite o telefone (10-11 dígitos)");
        c.gridx = 1; c.gridy = 2;
        painelCorpo.add(campoTelefone, c);

        // Data de Nascimento
        c.gridx = 0; c.gridy = 3;
        painelCorpo.add(criarLabel("Data de Nascimento (dd/mm/aaaa):"), c);

        campoDataNascimento = criarCampoData();
        campoDataNascimento.setToolTipText("Digite a data no formato dd/mm/aaaa");
        c.gridx = 1; c.gridy = 3;
        painelCorpo.add(campoDataNascimento, c);

        // Status e Progress
        statusLabel = new JLabel("", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        c.gridwidth = 2;
        c.gridx = 0; c.gridy = 4;
        painelCorpo.add(statusLabel, c);

        progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);
        progressBar.setVisible(false);
        progressBar.setPreferredSize(new Dimension(350, 25));
        progressBar.setStringPainted(true);
        progressBar.setString("Salvando...");
        c.gridy = 5;
        painelCorpo.add(progressBar, c);

        painelPrincipal.add(painelCorpo, BorderLayout.CENTER);

        // Rodapé com botões
        JPanel painelRodape = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 0));
        painelRodape.setBackground(BACKGROUND_COLOR);
        painelRodape.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        botaoCadastrar = criarBotao("Cadastrar", PRIMARY_COLOR);
        botaoLimpar = criarBotao("Limpar", SUCCESS_COLOR);

        painelRodape.add(botaoCadastrar);
        painelRodape.add(botaoLimpar);
        painelPrincipal.add(painelRodape, BorderLayout.SOUTH);

        add(painelPrincipal);

        // Foco inicial
        campoNome.requestFocusInWindow();
    }

    /**
     * Configura os eventos dos componentes.
     */
    private void configurarEventos() {
        botaoCadastrar.addActionListener(e -> cadastrar());
        botaoLimpar.addActionListener(e -> limparCampos());

        // Atalho Enter para cadastrar
        getRootPane().setDefaultButton(botaoCadastrar);
    }

    /**
     * Cria um label padronizado.
     */
    private JLabel criarLabel(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(LABEL_FONT);
        label.setForeground(Color.DARK_GRAY);
        return label;
    }

    /**
     * Cria um campo de texto padronizado.
     */
    private JTextField criarCampo() {
        JTextField campo = new JTextField();
        campo.setFont(FIELD_FONT);
        campo.setPreferredSize(new Dimension(280, 40));
        campo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        return campo;
    }

    /**
     * Cria um campo formatado para data com máscara.
     */
    private JFormattedTextField criarCampoData() {
        try {
            MaskFormatter dataMask = new MaskFormatter("##/##/####");
            dataMask.setPlaceholderCharacter('_');
            JFormattedTextField campo = new JFormattedTextField(dataMask);
            campo.setFont(FIELD_FONT);
            campo.setPreferredSize(new Dimension(280, 40));
            campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
            ));
            return campo;
        } catch (ParseException e) {
            return new JFormattedTextField();
        }
    }

    /**
     * Cria um botão padronizado com efeitos de hover.
     */
    private JButton criarBotao(String texto, Color cor) {
        JButton botao = new JButton(texto);
        botao.setFont(BUTTON_FONT);
        botao.setBackground(cor);
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);
        botao.setBorder(BorderFactory.createEmptyBorder(12, 30, 12, 30));
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));

        botao.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                botao.setBackground(cor.darker());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botao.setBackground(cor);
            }
        });

        return botao;
    }

    /**
     * Lógica de cadastro com validação e feedback ao usuário.
     */                     
    private void cadastrar() {
        // Validações dos campos
        String nome = campoNome.getText().trim();
        // Nome deve ter pelo menos 3 caracteres
        if (nome.length() < 3) {
            mostrarErro("O nome deve ter pelo menos 3 caracteres!");
            campoNome.requestFocus();
            return;
        }

        // CPF deve ter exatamente 11 dígitos numéricos
        String cpfTexto = campoCPF.getText().trim();
        if (cpfTexto.isEmpty() || !cpfTexto.matches("\\d{11}")) {
            mostrarErro("CPF inválido! Digite apenas números.");
            campoCPF.requestFocus();
            return;
        }

        // Telefone deve ter entre 9 e 11 dígitos numéricos
        String telefone = campoTelefone.getText().trim();
        if (telefone.isEmpty() || !telefone.matches("\\d{9,11}")) {
            mostrarErro("Telefone inválido!");
            campoTelefone.requestFocus();
            return;
        }

        // Data de Nascimento deve estar completa
        String dataTexto = campoDataNascimento.getText().trim();
        if (dataTexto.contains("_")) {
            mostrarErro("Data incompleta! Preencha todos os campos.");
            campoDataNascimento.requestFocus();
            return;
        }

        // Inicia processamento assíncrono
        progressBar.setVisible(true);
        statusLabel.setText("Processando...");
        statusLabel.setForeground(WARNING_COLOR);
        botaoCadastrar.setEnabled(false);

        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                // Simula tempo de processamento
                Thread.sleep(1500);
                // Prepara dados para cadastro
                InformacoesCadastro info = new InformacoesCadastro();
                info.setIdcpf((cpfTexto));
                info.setNome(nome);
                info.setTelefone(telefone);

                // Conversão e validação da data
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                sdf.setLenient(false);
                Date dataNascimento = sdf.parse(dataTexto);
                if (dataNascimento.after(new Date())) {
                    throw new IllegalArgumentException("Data de nascimento não pode ser futura!");
                }
                info.setDataNascimento(new java.sql.Date(dataNascimento.getTime()));

                InformacoesDao dao = new InformacoesDao();
                dao.adcionarCadastro(info);
                return null;
            }

            @Override
            protected void done() {
                progressBar.setVisible(false);
                botaoCadastrar.setEnabled(true);
                try {
                    get();
                    statusLabel.setText("Cadastro realizado com sucesso!");
                    statusLabel.setForeground(SUCCESS_COLOR);
                    limparCampos();
                } catch (Exception ex) {
                    mostrarErro(ex.getMessage());
                }
            }
        };
        worker.execute();
    }

    /**
     * Limpa os campos do formulário.
     */
    private void limparCampos() {
        campoCPF.setText("");
        campoNome.setText("");
        campoTelefone.setText("");
        campoDataNascimento.setText("");
        statusLabel.setText("");
        campoNome.requestFocus();
    }

    /**
     * Mostra uma mensagem de erro.
     */
    private void mostrarErro(String mensagem) {
        statusLabel.setText(mensagem);
        statusLabel.setForeground(ERROR_COLOR);
    }

    /**
     * Painel arredondado para o formulário.
     */
    private static class RoundedPanel extends JPanel {
        private int cornerRadius;

        public RoundedPanel(int cornerRadius) {
            this.cornerRadius = cornerRadius;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius));
            g2.dispose();
            super.paintComponent(g);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getLookAndFeel());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new TelaCadastro().setVisible(true);
        });
    }
}
