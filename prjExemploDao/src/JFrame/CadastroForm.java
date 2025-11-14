package JFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroForm {
    public static void main(String[] args) {
        // Configuração do JFrame
        JFrame frame = new JFrame("Cadastro de Usuário");
        frame.setSize(450, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Centraliza a janela na tela
        frame.setLayout(new BorderLayout());

        // Painel principal com borda e layout
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.setBackground(new Color(245, 245, 245));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        // Título do formulário
        JLabel titulo = new JLabel("Cadastro de Usuário");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setForeground(new Color(55, 55, 55));

        // Campos de entrada de dados
        JPanel panelCampos = new JPanel(new GridLayout(4, 2, 10, 10));
        panelCampos.setBackground(new Color(245, 245, 245));

        // Definindo o campo de Nome
        JLabel labelNome = new JLabel("Nome:");
        JTextField textNome = new JTextField();
        estilizarCampo(textNome);

        // Definindo o campo de CPF
        JLabel labelCPF = new JLabel("CPF:");
        JTextField textCPF = new JTextField();
        estilizarCampo(textCPF);

        // Definindo o campo de Telefone
        JLabel labelTelefone = new JLabel("Telefone:");
        JTextField textTelefone = new JTextField();
        estilizarCampo(textTelefone);

        // Definindo o campo de Data de Nascimento
        JLabel labelDataNascimento = new JLabel("Data de Nascimento:");
        JTextField textDataNascimento = new JTextField();
        estilizarCampo(textDataNascimento);

        // Adicionando os campos ao painel
        panelCampos.add(labelNome);
        panelCampos.add(textNome);
        panelCampos.add(labelCPF);
        panelCampos.add(textCPF);
        panelCampos.add(labelTelefone);
        panelCampos.add(textTelefone);
        panelCampos.add(labelDataNascimento);
        panelCampos.add(textDataNascimento);

        // Botão de Salvar
        JButton botaoSalvar = new JButton("Salvar");
        botaoSalvar.setFont(new Font("Arial", Font.BOLD, 16));
        botaoSalvar.setForeground(Color.WHITE);
        botaoSalvar.setBackground(new Color(0, 123, 255));
        botaoSalvar.setFocusPainted(false);
        botaoSalvar.setAlignmentX(Component.CENTER_ALIGNMENT);
        botaoSalvar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Ação do botão "Salvar"
        botaoSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = textNome.getText();
                String cpf = textCPF.getText();
                String telefone = textTelefone.getText();
                String dataNascimento = textDataNascimento.getText();

                // Exibir os dados inseridos
                JOptionPane.showMessageDialog(frame, 
                        "Dados Salvos:\n" +
                        "Nome: " + nome + "\n" +
                        "CPF: " + cpf + "\n" +
                        "Telefone: " + telefone + "\n" +
                        "Data de Nascimento: " + dataNascimento);
            }
        });

        // Adicionando componentes ao painel principal
        panelPrincipal.add(titulo);
        panelPrincipal.add(Box.createVerticalStrut(20));
        panelPrincipal.add(panelCampos);
        panelPrincipal.add(Box.createVerticalStrut(20));
        panelPrincipal.add(botaoSalvar);

        // Adicionando o painel principal ao frame
        frame.add(panelPrincipal, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    // Método para estilizar os campos de texto
    private static void estilizarCampo(JTextField campo) {
        campo.setFont(new Font("Arial", Font.PLAIN, 14));
        campo.setForeground(Color.DARK_GRAY);
        campo.setBackground(Color.WHITE);
        campo.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1));
        campo.setPreferredSize(new Dimension(300, 30));
    }
}
