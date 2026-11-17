package com.rappi.graphql;

/**
 * Datos de entrada de la mutacion crearPedido (tipo PedidoInput del schema).
 */
public class PedidoInput {

    private String cedulaCliente;
    private String nombreCliente;
    private String direccionEntrega;
    private String descripcion;
    private double total;

    /** Constructor vacio requerido para el mapeo desde GraphQL. */
    public PedidoInput() {
    }

    /** @return la cedula del cliente */
    public String getCedulaCliente() { return cedulaCliente; }
    /** @param cedulaCliente cedula del cliente */
    public void setCedulaCliente(String cedulaCliente) { this.cedulaCliente = cedulaCliente; }
    /** @return el nombre del cliente */
    public String getNombreCliente() { return nombreCliente; }
    /** @param nombreCliente nombre del cliente */
    public void setNombreCliente(String nombreCliente) { this.nombreCliente = nombreCliente; }
    /** @return la direccion de entrega */
    public String getDireccionEntrega() { return direccionEntrega; }
    /** @param direccionEntrega direccion de entrega */
    public void setDireccionEntrega(String direccionEntrega) { this.direccionEntrega = direccionEntrega; }
    /** @return la descripcion */
    public String getDescripcion() { return descripcion; }
    /** @param descripcion descripcion del pedido */
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    /** @return el total */
    public double getTotal() { return total; }
    /** @param total total del pedido */
    public void setTotal(double total) { this.total = total; }
}
