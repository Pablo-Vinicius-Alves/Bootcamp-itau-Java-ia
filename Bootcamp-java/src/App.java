import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);

        System.out.println("Hello, World!");
        System.out.println("Configurando a JDK na versão: " + args[0]);
        System.out.println("Programando em " + args[1]);

        System.out.println("Seu nome:");
        String name = scanner.next();
        System.out.println("Sua idade:");
        int age = scanner.nextInt();
        System.out.printf("Olá %s, sua idade é %s anos", name, age);
    }
}
