package DS;

import java.util.Scanner;

public class Exercicio4 {
	 public void exDados() {
		 //Scanner
	     Scanner scanner = new Scanner(System.in);
	     
	     //Inserção de Dados
	     System.out.print("Digite o nome: ");
	     String nome = scanner.nextLine();
	     
	     System.out.print("Digite a idade: ");
	     int idade = scanner.nextInt();
	     
	     scanner.nextLine();  //Para capturar a quebra de linha após o próximoInt
	     
	     System.out.print("Digite o e-mail: ");
	     String email = scanner.nextLine();
	     
	     System.out.print("Digite o peso: ");
	     double peso = scanner.nextDouble();
	     
		 System.out.println("-------------------------------------------");
	     
	     //Exibição dos dados
	     System.out.println("Nome: " + nome.toUpperCase());
	     System.out.println("Idade: " + idade);
	     System.out.println("E-mail: " + email.toLowerCase());
	     System.out.printf("Peso: %.2f kg\n", peso);
	     
	     scanner.close();
	 }
}

