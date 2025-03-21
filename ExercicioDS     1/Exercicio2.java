package DS;
import java.util.Scanner;

public class Exercicio2 {
    public void coisaIDADE() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite sua idade: ");
        int idade = scanner.nextInt();
        
        if (idade > 17) {
            System.out.println("Maior de idade");
        } else {
            System.out.println("Menor de idade");
        }
    }
}