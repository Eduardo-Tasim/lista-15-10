package br.com.nexuscloud.exception;

/* Exeção checked utilizada quando RAM necessaria para um servidor ultrapassa a capacidade do servidor (25004621-2) */
public class CapacidadeExcedidaException extends Exception {

    public CapacidadeExcedidaException(String msg) {
        super(msg);
    }
}
