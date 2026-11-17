package com.rappi.graphql;

import graphql.kickstart.tools.GraphQLMutationResolver;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * Resolvers de las Mutations definidas en schema.graphqls.
 */
@Component
public class PedidoMutation implements GraphQLMutationResolver {

    private static final List<String> ESTADOS = Arrays.asList("PENDIENTE", "EN_CAMINO", "ENTREGADO");

    private final PedidoRepository repo;

    /**
     * Constructor con inyeccion de dependencias.
     * @param repo repositorio de pedidos
     */
    public PedidoMutation(PedidoRepository repo) {
        this.repo = repo;
    }

    /**
     * Mutacion 3: registra un nuevo pedido.
     * @param input datos del pedido
     * @return el pedido guardado
     */
    public Pedido crearPedido(PedidoInput input) {
        if (input.getCedulaCliente() == null || !input.getCedulaCliente().matches("\\d{5,12}")) {
            throw new ErrorNegocio("La cedula debe tener entre 5 y 12 digitos");
        }
        if (input.getTotal() <= 0) {
            throw new ErrorNegocio("El total debe ser mayor a 0");
        }
        Pedido p = new Pedido();
        p.setCedulaCliente(input.getCedulaCliente());
        p.setNombreCliente(input.getNombreCliente());
        p.setDireccionEntrega(input.getDireccionEntrega());
        p.setDescripcion(input.getDescripcion());
        p.setTotal(input.getTotal());
        p.setEstado("PENDIENTE");
        p.setFecha(LocalDate.now());
        p.setHora(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
        return repo.save(p);
    }

    /**
     * Mutacion extra: cambia el estado de un pedido.
     * @param id identificador del pedido
     * @param estado nuevo estado
     * @return el pedido actualizado
     */
    public Pedido cambiarEstado(String id, String estado) {
        if (!ESTADOS.contains(estado)) {
            throw new ErrorNegocio("Estado invalido. Use: " + ESTADOS);
        }
        Pedido p = repo.findById(Long.valueOf(id.trim()))
                .orElseThrow(() -> new ErrorNegocio("No existe el pedido " + id));
        p.setEstado(estado);
        return repo.save(p);
    }
}
