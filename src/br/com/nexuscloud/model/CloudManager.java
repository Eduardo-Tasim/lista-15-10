
package br.com.nexuscloud.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Collections;
import java.util.Objects;

/* Gerencia o cadastro dos clientes e a associação deles com o seus servidores e calcula o custo por hora dos servidores ativos
   Evita duplicações com o HashMap, impede substituição de servidores para clientes ja cadastrados (25004621-2) */
public class CloudManager {

    private Map<Cliente, Set<Servidor>> bancoDeDados = new HashMap<>();

    public CloudManager() {
    }

    public void registrarCliente(Cliente c) {
        Objects.requireNonNull(c, "Cliente não pode ser nulo.");

        bancoDeDados.putIfAbsent(c, new HashSet<>());
    }

    public void provisionarServidor(Cliente c, Servidor s) {
        Objects.requireNonNull(c, "Cliente não pode ser nulo.");
        Objects.requireNonNull(s, "Servidor não pode ser nulo.");

        bancoDeDados.putIfAbsent(c, new HashSet<>());
        bancoDeDados.get(c).add(s);
    }

    public double calcularFaturaAtiva(Cliente c) {
        return bancoDeDados.getOrDefault(
                    c, Collections.emptySet()
                )
                .stream()
                .filter(Servidor::isStatusLigado)
                .mapToDouble(Servidor::getPrecoPorHora)
                .sum();
    }
}
