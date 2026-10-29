public class ContaBancaria {

    private static final double LIMITE_DEPOSITO_FAIXA_BAIXA = 500.00;
    private static final double CHEQUE_ESPECIAL_FAIXA_BAIXA = 50.00;
    private static final double PERCENTUAL_CHEQUE_ESPECIAL = 0.50;
    private static final double TAXA_USO_CHEQUE_ESPECIAL = 0.20;

    private double saldo;
    private final double limiteChequeEspecial;

    private double chequeEspecialRegistrado;
    private double taxaPendente;

    public ContaBancaria(double depositoInicial) {
        if (depositoInicial < 0) {
            throw new IllegalArgumentException("O depósito inicial não pode ser negativo.");
        }
        this.saldo = arredondar(depositoInicial);
        this.limiteChequeEspecial = calcularLimite(depositoInicial);
        this.chequeEspecialRegistrado = 0;
        this.taxaPendente = 0;
    }

    private static double calcularLimite(double depositoInicial) {
        if (depositoInicial <= LIMITE_DEPOSITO_FAIXA_BAIXA) {
            return CHEQUE_ESPECIAL_FAIXA_BAIXA;
        }
        return arredondar(depositoInicial * PERCENTUAL_CHEQUE_ESPECIAL);
    }

    private static double arredondar(double valor) {
        return Math.round(valor * 100.0) / 100.0;
    }

    public double getSaldo() {
        return saldo;
    }

    public double getLimiteChequeEspecial() {
        return limiteChequeEspecial;
    }

    public double getChequeEspecialUtilizado() {
        return saldo < 0 ? arredondar(-saldo) : 0;
    }

    public double getChequeEspecialDisponivel() {
        return arredondar(limiteChequeEspecial - getChequeEspecialUtilizado());
    }

    public double getSaldoDisponivel() {
        return arredondar(saldo + limiteChequeEspecial);
    }

    public boolean isUsandoChequeEspecial() {
        return saldo < 0;
    }

    public double getTaxaPendente() {
        return taxaPendente;
    }

    public double depositar(double valor) {
        validarValor(valor);
        saldo = arredondar(saldo + valor);
        
        chequeEspecialRegistrado = Math.min(chequeEspecialRegistrado, getChequeEspecialUtilizado());
        return cobrarTaxaPendente();
    }

    public void sacar(double valor) {
        debitar(valor);
    }

    public void pagarBoleto(double valor) {
        debitar(valor);
    }

    private void debitar(double valor) {
        validarValor(valor);
        if (valor > getSaldoDisponivel()) {
            throw new IllegalStateException(String.format(
                    "Saldo insuficiente. Disponível (saldo + cheque especial): R$ %.2f", getSaldoDisponivel()));
        }
        saldo = arredondar(saldo - valor);
        registrarUsoChequeEspecial();
    }

    private void registrarUsoChequeEspecial() {
        double utilizado = getChequeEspecialUtilizado();
        if (utilizado > chequeEspecialRegistrado) {
            double novoUso = utilizado - chequeEspecialRegistrado;
            taxaPendente = arredondar(taxaPendente + novoUso * TAXA_USO_CHEQUE_ESPECIAL);
            chequeEspecialRegistrado = utilizado;
        }
    }

    private double cobrarTaxaPendente() {
        if (taxaPendente <= 0) {
            return 0;
        }
        double taxa = taxaPendente;
        saldo = arredondar(saldo - taxa);
        taxaPendente = 0;
        chequeEspecialRegistrado = getChequeEspecialUtilizado();
        return taxa;
    }

    private void validarValor(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("O valor deve ser maior que zero.");
        }
    }
}