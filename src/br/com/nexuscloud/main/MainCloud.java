
package br.com.nexuscloud.main;

import br.com.nexuscloud.model.Cliente;
import br.com.nexuscloud.model.Container;
import br.com.nexuscloud.model.Servidor;
import br.com.nexuscloud.model.CloudManager;
import br.com.nexuscloud.exception.CapacidadeExcedidaException;

/* Classe principal da aplicação que executa os testes (25004621-2) */
public class MainCloud {

    public static void main(String[] args) {

        //Cria o gerenciador (25004621-2)
        CloudManager manager = new CloudManager();

        //Cria o cliente (25004621-2)
        Cliente cliente = new Cliente(
            "111.222.333-44",
            "Wayne Enterprises"
        );

        //Cria o sercidor (25004621-2)
        Servidor servidor = new Servidor(
            "192.168.0.1",
            16.0,
            5.50
        );

        //Registra o cliente no banco e associa o servidor ao cliente (25004621-2)
        manager.registrarCliente(cliente);
        manager.provisionarServidor(cliente, servidor);

        //Cria 2 container com necessidade de RAM diferentes (25004621-2)
        Container container1 = new Container(
            "C-01",
            "Aplicacao 1",
            10.0
        );

        Container container2 = new Container(
            "C-02",
            "Aplicacao 2",
            8.0
        );

        // Teste da exceção unchecked, tantando alocar um container a um servidor desligado
        // Descomente para testar e depois comente novamente.
        /* try {
            servidor.alocarContainer(container1);
           } catch (CapacidadeExcedidaException e) {
             } */

        servidor.ligar();

        // Alocação do primeiro container sem erro
        try {
            servidor.alocarContainer(container1);
            System.out.println("Container C-01 alocado com sucesso!");

        } catch (CapacidadeExcedidaException e) {
            System.out.println(e.getMessage());
        }

        // Alocação do segundo container com erro por estar acima do limite de RAM
        try {
            servidor.alocarContainer(container2);
            System.out.println("Container C-02 alocado com sucesso!");

        } catch (CapacidadeExcedidaException e) {
            System.out.println(e.getMessage());
        }

        System.out.printf(
            "RAM em uso: %.2f GB%n",
            servidor.calcularRamEmUso()
        );

        System.out.printf(
            "Fatura ativa: R$ %.2f por hora%n",
            manager.calcularFaturaAtiva(cliente)
        );
    }
}
