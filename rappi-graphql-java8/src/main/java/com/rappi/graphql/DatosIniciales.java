package com.rappi.graphql;

import java.time.LocalDate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Carga pedidos de ejemplo al iniciar la aplicacion (base H2 en memoria).
 */
@Configuration
public class DatosIniciales {

    /**
     * Inserta datos de prueba si la tabla esta vacia.
     * @param repo repositorio de pedidos
     * @return runner de arranque
     */
    @Bean
    CommandLineRunner cargar(PedidoRepository repo) {
        return args -> {
            if (repo.count() > 0) {
                return;
            }
            crear(repo, "1001234567", "Ana Torres", "Cra 10 #20-30", "2 hamburguesas y gaseosa", "PENDIENTE", 42000, "09:15");
            crear(repo, "1009876543", "Luis Perez", "Calle 5 #8-12", "Pizza grande", "EN_CAMINO", 55000, "10:40");
            crear(repo, "1001234567", "Ana Torres", "Cra 10 #20-30", "Ensalada y jugo", "ENTREGADO", 23000, "12:05");
            crear(repo, "52345678", "Marta Rojas", "Av 68 #45-10", "Mercado basico", "PENDIENTE", 98000, "13:30");
        };
    }

    private void crear(PedidoRepository repo, String ced, String nom, String dir,
            String desc, String est, double total, String hora) {
        Pedido p = new Pedido();
        p.setCedulaCliente(ced);
        p.setNombreCliente(nom);
        p.setDireccionEntrega(dir);
        p.setDescripcion(desc);
        p.setEstado(est);
        p.setTotal(total);
        p.setFecha(LocalDate.now());
        p.setHora(hora);
        repo.save(p);
    }
}
