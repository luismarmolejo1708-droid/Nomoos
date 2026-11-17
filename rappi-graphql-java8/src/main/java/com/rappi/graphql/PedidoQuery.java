package com.rappi.graphql;

import graphql.kickstart.tools.GraphQLQueryResolver;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * Resolvers de las Queries definidas en schema.graphqls.
 */
@Component
public class PedidoQuery implements GraphQLQueryResolver {

    private final PedidoRepository repo;
    private final PdfService pdf;

    /**
     * Constructor con inyeccion de dependencias.
     * @param repo repositorio de pedidos
     * @param pdf servicio generador de PDF
     */
    public PedidoQuery(PedidoRepository repo, PdfService pdf) {
        this.repo = repo;
        this.pdf = pdf;
    }

    /**
     * Query 1: todos los pedidos realizados hoy.
     * @return lista de pedidos del dia
     */
    public List<Pedido> pedidosDelDia() {
        return repo.findByFecha(LocalDate.now());
    }

    /**
     * Query 2a: pedidos de un cliente por cedula.
     * @param cedula cedula del cliente
     * @return pedidos del cliente
     */
    public List<Pedido> pedidosPorCedula(String cedula) {
        return repo.findByCedulaCliente(cedula);
    }

    /**
     * Query 2b: un pedido por su ID.
     * @param id identificador del pedido
     * @return el pedido o null si no existe
     */
    public Pedido pedidoPorId(String id) {
        return repo.findById(parseId(id)).orElse(null);
    }

    /**
     * Query 4a: reporte PDF del dia en Base64.
     * @return PDF codificado en Base64
     */
    public String reporteDiarioPdf() {
        return pdf.reporteDiario(repo.findByFecha(LocalDate.now()));
    }

    /**
     * Query 4b: reporte PDF de un pedido en Base64.
     * @param id identificador del pedido
     * @return PDF codificado en Base64
     */
    public String reportePedidoPdf(String id) {
        Pedido p = repo.findById(parseId(id))
                .orElseThrow(() -> new ErrorNegocio("No existe el pedido " + id));
        return pdf.reportePedido(p);
    }

    private Long parseId(String id) {
        try {
            return Long.valueOf(id.trim());
        } catch (Exception e) {
            throw new ErrorNegocio("ID invalido: " + id);
        }
    }
}
