import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quanto é 2 + 2?");
        var result = scanner.nextInt();
        var isRight = result == 4;
        System.out.printf("O resultado é 4, você acertou? (%s) \n", isRight);

        System.out.println("Quantos anos você tem?");
        var age = scanner.nextInt();
        System.out.println("Tem carteira?");
        var visa = scanner.nextBoolean(); 
        var canDrive = age >= 18 && visa == true;
        System.out.printf("\nVocê pode dirigir? (%s)", canDrive);

        System.out.println("Informe o primeiro número:");
        var value1 = scanner.nextInt();
        System.out.println("Informe o segundo número:");
        var value2 = scanner.nextInt();
        System.out.printf("%s + %s = %s\n", value1, value2, value1 + value2 );
    }
}
