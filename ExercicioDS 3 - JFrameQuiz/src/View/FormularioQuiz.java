package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import javax.swing.*;

public class FormularioQuiz extends JFrame {
    
    // Definindo as perguntas, alternativas e as respostas corretas
    private String[] perguntas = {
        "1) Qual é a capital do Brasil?",
        "2) Qual é o maior planeta do Sistema Solar?",
        "3) Quem pintou a Mona Lisa?",
        "4) Qual a fórmula química da água?",
        "5) Em que ano o Brasil foi descoberto?"
    };
    
    private String[][] alternativas = {
        {"São Paulo", "Brasília", "Rio de Janeiro", "Salvador", "Belo Horizonte"},
        {"Terra", "Marte", "Júpiter", "Saturno", "Vênus"},
        {"Leonardo da Vinci", "Pablo Picasso", "Michelangelo", "Raphael", "Donatello"},
        {"H2O", "CO2", "NaCl", "O2", "CH4"},
        {"1492", "1500", "1822", "1889", "1600"}
    };
    
    private int[] respostasCorretas = {1, 2, 0, 0, 1}; // Índices das alternativas corretas
    
    private ButtonGroup[] grupos = new ButtonGroup[5]; // Array de ButtonGroups para cada pergunta
    
    public FormularioQuiz() {
        setTitle("Teste Sua Sabedoria!");
        setSize(600, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Painel principal com rolagem
        JPanel painelConteudo = new JPanel();
        painelConteudo.setLayout(new BoxLayout(painelConteudo, BoxLayout.Y_AXIS));

        // Adicionando perguntas e alternativas dinamicamente
        grupos = new ButtonGroup[5];
        for (int i = 0; i < perguntas.length; i++) {
            JPanel blocoPergunta = new JPanel();
            blocoPergunta.setLayout(new BoxLayout(blocoPergunta, BoxLayout.Y_AXIS));

            // Criando e adicionando as perguntas
            JLabel lblPergunta = new JLabel(perguntas[i]);
            lblPergunta.setFont(new Font("Arial", Font.BOLD, 14));
            blocoPergunta.add(lblPergunta);

            // Criando um novo ButtonGroup para cada pergunta
            ButtonGroup grupo = new ButtonGroup();
            for (int j = 0; j < alternativas[i].length; j++) {
                JRadioButton rb = new JRadioButton(alternativas[i][j]);
                grupo.add(rb); // Adicionando a alternativa ao ButtonGroup
                blocoPergunta.add(rb); // Adicionando a alternativa ao painel da pergunta
            }

            grupos[i] = grupo; // Armazenando o ButtonGroup no array
            painelConteudo.add(blocoPergunta); // Adicionando o painel de perguntas ao painel principal
        }

        // Botão para verificar as respostas
        JButton btVerificar = new JButton("Verificar Respostas");
        btVerificar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btVerificar.setBackground(new Color(0, 10, 20));
        btVerificar.setForeground(Color.WHITE);
        btVerificar.setFont(new Font("Arial", Font.BOLD, 16));
        btVerificar.setFocusPainted(false);
        btVerificar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Evento do botão para verificar as respostas
        btVerificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int acertos = 0;

                // Verificando se todas as perguntas foram respondidas
                for (int i = 0; i < grupos.length; i++) {
                    ButtonGroup grupo = grupos[i];
                    Enumeration<AbstractButton> botoes = grupo.getElements();
                    int index = 0;
                    boolean respondeu = false;
                    
                    // Iterando pelas alternativas de cada pergunta
                    while (botoes.hasMoreElements()) {
                        JRadioButton botao = (JRadioButton) botoes.nextElement();
                        if (botao.isSelected()) {
                            respondeu = true;
                            if (index == respostasCorretas[i]) { // Verificando se a resposta está correta
                                acertos++;
                            }
                        }
                        index++;
                    }

                    if (!respondeu) {
                        JOptionPane.showMessageDialog(null, "Responda todas as perguntas!");
                        return; // Se alguma pergunta não for respondida, interrompe a verificação
                    }
                }

                // Exibindo a quantidade de respostas corretas
                JOptionPane.showMessageDialog(null, "Você acertou " + acertos + " de 5 perguntas.");
            }
        });

        painelConteudo.add(Box.createVerticalStrut(20)); // Espaço entre os elementos
        painelConteudo.add(btVerificar); // Adicionando o botão ao painel
        painelConteudo.add(Box.createVerticalStrut(20)); // Espaço adicional

        // ScrollPane para permitir rolagem
        JScrollPane scrollPane = new JScrollPane(painelConteudo);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane); // Adicionando o ScrollPane à janela

        setVisible(true); // Tornando a janela visível
    }

    public static void main(String[] args) {
        new FormularioQuiz(); // Criando a instância do formulário
    }
}
