package DS;
import java.util.Scanner;

//Exercício 1
public class Exercicio1 {
	    public void coisarOnome() {
	        Scanner scanner = new Scanner(System.in);
	        System.out.print("Digite um nome: ");
	        String nome = scanner.nextLine();
	        
	        if (nome.length() < 3) {
	            System.out.println("Nome inválido");
	        } else {
	            System.out.println("Nome válido");
	        }
	    }
	}