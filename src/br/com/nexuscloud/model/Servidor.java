
package br.com.nexuscloud.model;

import br.com.nexuscloud.exception.CapacidadeExcedidaException;
import br.com.nexuscloud.exception.ServidorOfflineException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* Servidor virtual da NexusCloud, controla estado, capacidade de memoria e container é identificado pelo IP
   Lista de maneira cronologia os cointainers alocados e verifica o preço por hora para não ser negativo (25004621-2) */
public class Servidor {

    private String ip;
    private double precoPorHora;
    private double ramTotal;
    private boolean statusLigado;

    private List<Container> containers = new ArrayList<>();

    public Servidor(String ip, double ramTotal, double precoPorHora) {
        this.ip = Objects.requireNonNull(ip, "IP não pode ser nulo.");

        if (ramTotal <= 0) {
            throw new IllegalArgumentException(
                "A RAM total deve ser maior que zero."
            );
        }

        if (precoPorHora < 0) {
            throw new IllegalArgumentException(
                "O preço por hora não pode ser negativo."
            );
        }

        this.ramTotal = ramTotal;
        this.precoPorHora = precoPorHora;
        this.statusLigado = false;
    }

    public void ligar() {
        statusLigado = true;
    }

    public void desligar() {
        statusLigado = false;
    }

    /* Aloca container no servidor verificando se ele está ligado e se tem RAM suficiente ativa as exceptions caso um desses fatores se mostre falso (25004621-2) */
    public void alocarContainer(Container c)
            throws CapacidadeExcedidaException {

        if (!statusLigado) {
            throw new ServidorOfflineException(
                "Erro: Não é possível alocar containers em um servidor desligado."
            );
        }

        Objects.requireNonNull(c, "Container não pode ser nulo.");

        if (calcularRamEmUso() + c.getRamNecessaria() > ramTotal) {
            throw new CapacidadeExcedidaException(
                "Falha Crítica: Memória RAM insuficiente no servidor "
                + ip + "."
            );
        }

        containers.add(c);
    }

    /* Calcula o total de RAM usada pelos containers extraindo a RAM de cada container e somando em seguida (25004621-2) */
    public double calcularRamEmUso() {
        return containers.stream()
                .mapToDouble(Container::getRamNecessaria)
                .sum();
    }

    public boolean isStatusLigado() {
    return statusLigado;
}

    public double getPrecoPorHora() {
    return precoPorHora;
}

    /* Compara os servidores pelo IP eles sendo diferentes ou não (25004621-2) */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Servidor)) {
            return false;
        }

        Servidor outro = (Servidor) obj;
        return Objects.equals(ip, outro.ip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ip);
    }
}
