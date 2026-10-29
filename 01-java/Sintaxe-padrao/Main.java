import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Operadores lógicos
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quanto é 2 + 2?");
        var result = scanner.nextInt();
        var isRight = result == 4;
        System.out.printf("O resultado é 4, você acertou? (%s) \n", isRight);

        // Aritméticos
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

        // Condicionais:
        System.out.println("Informe seu nome:");
        var name = scanner.next();
        System.out.println("Infome sua idade");
        var agee = scanner.nextInt();

        if (agee >= 18) {
        System.out.printf("%s tem %s anos, pode dirigir \n", name, age);
        } else{
        System.out.printf("%s você não pode dirigir \n", name);
        }
        System.out.println("Fim da execução!");

        System.out.println("Informe um número de 1 a 7");
        var option = scanner.nextInt();
        switch (option) {
            // case X -> System.out.println("abcdefg");
            case 1:
                System.out.println("Domingo");
                break;
            case 2:
                System.out.println("Segunda");
                break;
            case 3:
                System.out.println("Terça");
                break;
            case 4:
                System.out.println("Quarta");
                break;
            case 5:
                System.out.println("Quinta");
                break;
            case 6:
                System.out.println("Sexta");
                break;
            case 7:
                System.out.println("Sábado");
                break;
            default: 
                System.out.println("Opção inválida");
        }

        
        //Laços de repetição:
        for(;;){
            System.out.println("Digite um nome:");
            var namee = scanner.next();

            if(name.equalsIgnoreCase("exit")) break;
            System.out.println(namee);
        }

        for(var i = 1; i <= 100; i++){
            System.out.println(i);
        }
        scanner.close();
    }
}
