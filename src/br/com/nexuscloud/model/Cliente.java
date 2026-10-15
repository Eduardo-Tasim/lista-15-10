
package br.com.nexuscloud.model;

import java.util.Objects;
/* Classe responsavel pela identificação e criaçao do cliente (25004621-2)  */
public class Cliente {

    private String cpf;
    private String nome;

    public Cliente(String cpf, String nome) {
        this.cpf = Objects.requireNonNull(cpf, "CPF não pode ser nulo.");
        this.nome = Objects.requireNonNull(nome, "Nome não pode ser nulo.");
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    /* Comparação de dois clientes usando CPF (25004621-2) */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Cliente)) {
            return false;
        }

        Cliente outro = (Cliente) obj;
        return Objects.equals(cpf, outro.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }
}
