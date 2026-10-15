package br.com.nexuscloud.exception;

/* Exceção unchecked usada quando se tenta alocar um container em um servidor desligado (25004621-2) */
public class ServidorOfflineException extends RuntimeException {

    public ServidorOfflineException(String msg) {
        super(msg);
    }
}
