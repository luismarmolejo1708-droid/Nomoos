package com.rappi.graphql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal de la aplicacion GraphQL de pedidos tipo RAPPI.
 * Ejecutar esta clase desde NetBeans (Run Project) para iniciar el servidor.
 */
@SpringBootApplication
public class RappiGraphqlApplication {

    /**
     * Punto de entrada de la aplicacion.
     * @param args argumentos de linea de comandos
     */
    public static void main(String[] args) {
        SpringApplication.run(RappiGraphqlApplication.class, args);
    }
}
