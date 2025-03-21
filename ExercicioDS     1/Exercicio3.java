package DS;
import java.util.Scanner;

public class Exercicio3{
	//Exercicio 3
    public void cepCOISADO() {
    	
    	//Scanner
        Scanner scanner = new Scanner(System.in);
        
        //Inserção de Dados
        System.out.print("Digite o CEP (sem hífen): ");
        String cep = scanner.next();
        
        //Sim e Não
        if (cep.length() == 8) {
            System.out.println("CEP com a inserção do hífen: " + cep.substring(0, 5) + "-" + cep.substring(5));
        } else {
            System.out.println("CEP inválido");
        }
    }
    	
}
