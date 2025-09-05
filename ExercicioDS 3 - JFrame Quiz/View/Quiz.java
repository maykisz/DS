package View;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// Tela Inicial
class TelaInicial extends JFrame {
    private JTextField tfNome, tfEmail, tfSerie;
    private JButton btIniciar;

    public TelaInicial() {
        setTitle("Tela Inicial - Quiz");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Painel principal
        JPanel painel = new JPanel();
        painel.setLayout(null);
        painel.setBackground(new Color(240, 240, 240));
        add(painel);

        JLabel lbTitulo = new JLabel("Bem-vindo ao Quiz!", JLabel.CENTER);
        lbTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lbTitulo.setBounds(100, 20, 300, 40);
        painel.add(lbTitulo);

        JLabel lbNome = new JLabel("Nome:");
        lbNome.setFont(new Font("Arial", Font.PLAIN, 16));
        lbNome.setBounds(80, 90, 100, 30);
        painel.add(lbNome);

        tfNome = new JTextField();
        tfNome.setBounds(180, 90, 220, 30);
        painel.add(tfNome);

        JLabel lbEmail = new JLabel("Email:");
        lbEmail.setFont(new Font("Arial", Font.PLAIN, 16));
        lbEmail.setBounds(80, 140, 100, 30);
        painel.add(lbEmail);

        tfEmail = new JTextField();
        tfEmail.setBounds(180, 140, 220, 30);
        painel.add(tfEmail);

        JLabel lbSerie = new JLabel("Série:");
        lbSerie.setFont(new Font("Arial", Font.PLAIN, 16));
        lbSerie.setBounds(80, 190, 100, 30);
        painel.add(lbSerie);

        tfSerie = new JTextField();
        tfSerie.setBounds(180, 190, 220, 30);
        painel.add(tfSerie);

        btIniciar = new JButton("Iniciar Quiz");
        btIniciar.setBounds(160, 260, 180, 40);
        btIniciar.setBackground(new Color(255, 20, 147));
        btIniciar.setForeground(Color.WHITE);
        btIniciar.setFont(new Font("Arial", Font.BOLD, 16));
        painel.add(btIniciar);

        // Evento do botão
        btIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = tfNome.getText().trim();
                String email = tfEmail.getText().trim();
                String serie = tfSerie.getText().trim();

                if (nome.isEmpty() || email.isEmpty() || serie.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
                } else {
                    dispose(); // Fecha a tela inicial
                    new Quiz(nome, email, serie); // Abre o quiz passando os dados
                }
            }
        });

        setVisible(true);
    }
}

// Classe Quiz
public class Quiz extends JFrame {
    private JLabel lbPergunta;
    private JRadioButton rbOp1, rbOp2, rbOp3, rbOp4, rbOp5;
    private ButtonGroup bgRespostas;
    private JButton btProxima;

    private int perguntaAtual = 0;
    private int score = 0;

    private String nome, email, serie;

    private String[][] perguntas = {
        {"Qual é a capital do Brasil?", "Brasília", "São Paulo", "Rio de Janeiro", "Belo Horizonte", "Curitiba", "Brasília"},
        {"Quantos continentes existem?", "5", "6", "7", "8", "9", "7"},
        {"Qual é o maior planeta do Sistema Solar?", "Terra", "Júpiter", "Saturno", "Vênus", "Netuno", "Júpiter"},
        {"Quem pintou a Mona Lisa?", "Vincent van Gogh", "Pablo Picasso", "Leonardo da Vinci", "Claude Monet", "Rembrandt", "Leonardo da Vinci"},
        {"Qual é a fórmula da água?", "H2O", "CO2", "O2", "N2", "CH4", "H2O"}
    };

    public Quiz(String nome, String email, String serie) {
        this.nome = nome;
        this.email = email;
        this.serie = serie;

        // Configurações da janela
        setTitle("Quiz de Mandrake");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel painel = new JPanel();
        painel.setLayout(null);
        painel.setBackground(Color.WHITE);
        add(painel);

        // Label para exibir a pergunta
        lbPergunta = new JLabel("", JLabel.CENTER);
        lbPergunta.setFont(new Font("Arial", Font.BOLD, 20));
        lbPergunta.setBounds(50, 50, 700, 40);
        painel.add(lbPergunta);

        // Opções de respostas
        rbOp1 = criarRadioButton(painel, 150);
        rbOp2 = criarRadioButton(painel, 200);
        rbOp3 = criarRadioButton(painel, 250);
        rbOp4 = criarRadioButton(painel, 300);
        rbOp5 = criarRadioButton(painel, 350);

        // Agrupando as opções
        bgRespostas = new ButtonGroup();
        bgRespostas.add(rbOp1);
        bgRespostas.add(rbOp2);
        bgRespostas.add(rbOp3);
        bgRespostas.add(rbOp4);
        bgRespostas.add(rbOp5);

        // Botão para próxima pergunta
        btProxima = new JButton("Próxima");
        btProxima.setBounds(325, 450, 150, 40);
        btProxima.setForeground(Color.WHITE);
        btProxima.setBackground (new Color(255, 20, 147));
        btProxima.setFont(new Font("Arial", Font.BOLD, 16));
        painel.add(btProxima);

        // Evento do botão
        btProxima.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String respostaCorreta = perguntas[perguntaAtual][6];
                String respostaSelecionada = null;

                if (rbOp1.isSelected()) respostaSelecionada = rbOp1.getText();
                else if (rbOp2.isSelected()) respostaSelecionada = rbOp2.getText();
                else if (rbOp3.isSelected()) respostaSelecionada = rbOp3.getText();
                else if (rbOp4.isSelected()) respostaSelecionada = rbOp4.getText();
                else if (rbOp5.isSelected()) respostaSelecionada = rbOp5.getText();

                if (respostaCorreta.equals(respostaSelecionada)) {
                    score++;
                }

                perguntaAtual++;

                if (perguntaAtual < perguntas.length) {
                    exibirPergunta();
                } else {
                    // Sistema de notas
                    String nota;
                    if (score < 2) {
                        nota = "I";
                    } else if (score == 3) {
                        nota = "R";
                    } else if (score == 4) {
                        nota = "B";
                    } else if (score == 5) {
                        nota = "MB";
                    } else {
                        nota = "-";
                    }

                    // Exibe o resultado final com nota
                    JOptionPane.showMessageDialog(null,
                        "Quiz terminado!\n\nNome: " + nome +
                        "\nEmail: " + email +
                        "\nSérie: " + serie +
                        "\n\nSua pontuação: " + score + " de " + perguntas.length +
                        "\nNota: " + nota,
                        "Resultado Final",
                        JOptionPane.INFORMATION_MESSAGE);

                    System.exit(0);
                }
            }
        });

        exibirPergunta(); // Exibe a primeira
        setVisible(true);
    }

    // Método para criar os radio buttons
    private JRadioButton criarRadioButton(JPanel painel, int y) {
        JRadioButton rb = new JRadioButton();
        rb.setBounds(200, y, 400, 30);
        rb.setBackground(Color.WHITE);
        rb.setFont(new Font("Arial", Font.PLAIN, 16));
        painel.add(rb);
        return rb;
    }

    // Exibir pergunta atual
    private void exibirPergunta() {
        String[] pergunta = perguntas[perguntaAtual];
        lbPergunta.setText(pergunta[0]);
        rbOp1.setText(pergunta[1]);
        rbOp2.setText(pergunta[2]);
        rbOp3.setText(pergunta[3]);
        rbOp4.setText(pergunta[4]);
        rbOp5.setText(pergunta[5]);
        bgRespostas.clearSelection();
    }

    public static void main(String[] args) {
        new TelaInicial(); // Começa na tela inicial
    }
}
