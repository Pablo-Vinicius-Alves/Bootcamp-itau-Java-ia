import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== Criação de conta ===");
        double depositoInicial = lerValor("Informe o valor do depósito inicial: R$ ", true);
        ContaBancaria conta = new ContaBancaria(depositoInicial);
        System.out.printf("Conta criada. Cheque especial: R$ %.2f%n", conta.getLimiteChequeEspecial());

        int opcao;
        do {
            exibirMenu();
            opcao = (int) lerValor("Escolha uma opção: ", true);
            executar(opcao, conta);
        } while (opcao != 0);

        System.out.println("Encerrando. Até logo!");
        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println();
        System.out.println("===== MENU =====");
        System.out.println("1 - Consultar saldo");
        System.out.println("2 - Consultar cheque especial");
        System.out.println("3 - Depositar dinheiro");
        System.out.println("4 - Sacar dinheiro");
        System.out.println("5 - Pagar boleto");
        System.out.println("6 - Verificar uso do cheque especial");
        System.out.println("0 - Sair");
    }

    private static void executar(int opcao, ContaBancaria conta) {
        try {
            switch (opcao) {
                case 1 -> System.out.printf("Saldo: R$ %.2f%n", conta.getSaldo());
                case 2 -> {
                    System.out.printf("Limite do cheque especial: R$ %.2f%n", conta.getLimiteChequeEspecial());
                    System.out.printf("Utilizado: R$ %.2f%n", conta.getChequeEspecialUtilizado());
                    System.out.printf("Disponível: R$ %.2f%n", conta.getChequeEspecialDisponivel());
                }
                case 3 -> {
                    double taxa = conta.depositar(lerValor("Valor do depósito: R$ ", false));
                    System.out.println("Depósito realizado.");
                    if (taxa > 0) {
                        System.out.printf("Taxa de uso do cheque especial cobrada: R$ %.2f%n", taxa);
                    }
                    System.out.printf("Saldo atual: R$ %.2f%n", conta.getSaldo());
                }
                case 4 -> {
                    conta.sacar(lerValor("Valor do saque: R$ ", false));
                    System.out.println("Saque realizado.");
                    System.out.printf("Saldo atual: R$ %.2f%n", conta.getSaldo());
                }
                case 5 -> {
                    conta.pagarBoleto(lerValor("Valor do boleto: R$ ", false));
                    System.out.println("Boleto pago.");
                    System.out.printf("Saldo atual: R$ %.2f%n", conta.getSaldo());
                }
                case 6 -> {
                    if (conta.isUsandoChequeEspecial()) {
                        System.out.printf("A conta ESTÁ usando o cheque especial (R$ %.2f).%n",
                                conta.getChequeEspecialUtilizado());
                        System.out.printf("Taxa pendente (20%%): R$ %.2f%n", conta.getTaxaPendente());
                    } else {
                        System.out.println("A conta NÃO está usando o cheque especial.");
                    }
                }
                case 0 -> { }
                default -> System.out.println("Opção inválida.");
            }
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static double lerValor(String mensagem, boolean aceitaZero) {
        while (true) {
            System.out.print(mensagem);
            String entrada = scanner.nextLine().trim().replace(",", ".");
            try {
                double valor = Double.parseDouble(entrada);
                if (valor < 0 || (!aceitaZero && valor == 0)) {
                    System.out.println("Informe um valor válido.");
                    continue;
                }
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número.");
            }
        }
    }
}