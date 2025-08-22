package Lazuzu;
 
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
 
public class Formulario extends JFrame {
	
	private JLabel lbDisciplina, lbNomeAluno, lbNota1, lbNota2, lbNota3, lbNota4;
	private JComboBox<String> cbDisciplinas;
	private JTextField txNomeAluno, txNota1, txNota2, txNota3, txNota4;
	private JButton btCalcular;
	
	public Formulario() {
		
		// CONFIGURAÇÃO DA JANELA
		setTitle("Cálculo da Média");
		setSize(400,410);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
		getContentPane().setBackground(Color.LIGHT_GRAY);
		
		// Nome do Aluno
		lbNomeAluno = new JLabel("Nome:");
		lbNomeAluno.setBounds(30, 20, 100, 30);
		add(lbNomeAluno);
		
		//Caixa de texto
		txNomeAluno = new JTextField(); // Não sei
		txNomeAluno.setBounds(120,20,100,30); // Posição da caixa (x, y, w, h)
		add(txNomeAluno);

		
		// DISCIPLINA (agora com dropdown)
		lbDisciplina = new JLabel("Disciplina:");
		lbDisciplina.setBounds(30, 60, 100, 30);
		add(lbDisciplina);
		
		// Opções fixas de matérias
		String[] materias = {"Matemática", "Português", "História", "Geografia", "Física", "Química", "Biologia"};
		cbDisciplinas = new JComboBox<>(materias);
		cbDisciplinas.setBounds(120, 60, 200, 30);
		add(cbDisciplinas);
		
		// NOTA 1
		lbNota1 = new JLabel("Nota 1:");
		lbNota1.setBounds(30, 100, 100, 30);
		add(lbNota1);
		
		txNota1 = new JTextField();
		txNota1.setBounds(120, 100, 100, 30);
		add(txNota1);
		
		// NOTA 2
		lbNota2 = new JLabel("Nota 2:");
		lbNota2.setBounds(30, 140, 100, 30);
		add(lbNota2);
		
		txNota2 = new JTextField();
		txNota2.setBounds(120, 140, 100, 30);
		add(txNota2);
		
		// NOTA 3
		lbNota3 = new JLabel("Nota 3:");
		lbNota3.setBounds(30, 180, 100, 30);
		add(lbNota3);
		
		txNota3 = new JTextField();
		txNota3.setBounds(120, 180, 100, 30);
		add(txNota3);
		
		// NOTA 4
		lbNota4 = new JLabel("Nota 4:");
		lbNota4.setBounds(30, 220, 100, 30);
		add(lbNota4);
		
		txNota4 = new JTextField();
		txNota4.setBounds(120, 220, 100, 30);
		add(txNota4);
		
		// BOTÃO
		btCalcular = new JButton("Calcular Média");
		btCalcular.setBounds(120, 270, 150, 40);
		btCalcular.setBackground(Color.BLACK);
		btCalcular.setForeground(Color.WHITE);
		
		// EVENTO DO BOTÃO
		btCalcular.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String disciplina = cbDisciplinas.getSelectedItem().toString();
					String nome = txNomeAluno.getText();
					double n1 = Double.parseDouble(txNota1.getText());
					double n2 = Double.parseDouble(txNota2.getText());
					double n3 = Double.parseDouble(txNota3.getText());
					double n4 = Double.parseDouble(txNota4.getText());
					
					// Validação das notas (0 a 10)
					if (n1 < 0 || n1 > 10 || n2 < 0 || n2 > 10 ||
						n3 < 0 || n3 > 10 || n4 < 0 || n4 > 10) {
						JOptionPane.showMessageDialog(null, "As notas devem estar entre 0 e 10!");
						return;
					}
					
					double media = (n1 + n2 + n3 + n4) / 4.0;
					
					JOptionPane.showMessageDialog(null,
							"Nome do Aluno: " + nome +
							"\nDisciplina: " + disciplina +
							"\nMédia: " + media);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Digite valores numéricos válidos!");
				}
			}
		});
		
		add(btCalcular);
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Formulario();
	}

}
 