package com.rappi.graphql;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio de acceso a datos para {@link Pedido}.
 */
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    /**
     * Busca los pedidos de una fecha.
     * @param fecha fecha a consultar
     * @return lista de pedidos
     */
    List<Pedido> findByFecha(LocalDate fecha);

    /**
     * Busca los pedidos de un cliente.
     * @param cedulaCliente cedula del cliente
     * @return lista de pedidos
     */
    List<Pedido> findByCedulaCliente(String cedulaCliente);
}
