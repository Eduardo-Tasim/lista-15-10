
package br.com.nexuscloud.model;

import java.util.Objects;

/* Aplicação que pode ser executada dentro do servidor da NexusCloud com identidade determinada pelo ID 
   Responsavel tambem pela inicialização do container com nome e necessidade de RAM (25004621-2) */
public class Container {

    private String id;
    private String nomeApp;
    private double ramNecessaria;

    public Container(String id, String nomeApp, double ramNecessaria) {
        this.id = Objects.requireNonNull(id, "ID não pode ser nulo.");
        this.nomeApp = Objects.requireNonNull(
            nomeApp, "Nome da aplicação não pode ser nulo."
        );

        if (ramNecessaria <= 0) {
            throw new IllegalArgumentException(
                "A RAM necessária deve ser maior que zero."
            );
        }

        this.ramNecessaria = ramNecessaria;
    }

    public String getId() {
        return id;
    }

    public double getRamNecessaria() {
        return ramNecessaria;
    }

    /* Compara containers usando o ID para identificar caso algum deles seja igual (25004621-2) */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Container)) {
            return false;
        }

        Container outro = (Container) obj;
        return Objects.equals(id, outro.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
