package com.rappi.graphql;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

/**
 * Entidad que representa un pedido de domicilio.
 */
@Entity
@Table(name = "pedidos")
public class Pedido {

    /** Identificador unico del pedido. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /** Cedula del cliente. */
    private String cedulaCliente;
    /** Nombre del cliente. */
    private String nombreCliente;
    /** Direccion de entrega. */
    private String direccionEntrega;
    /** Descripcion de los productos pedidos. */
    private String descripcion;
    /** Estado: PENDIENTE, EN_CAMINO o ENTREGADO. */
    private String estado;
    /** Valor total del pedido. */
    private double total;
    /** Fecha en que se realizo el pedido. */
    private LocalDate fecha;
    /** Hora (HH:mm) en que se realizo el pedido. */
    private String hora;

    /** Constructor vacio requerido por JPA. */
    public Pedido() {
    }

    /** @return el id del pedido */
    public Long getId() { return id; }
    /** @param id nuevo id */
    public void setId(Long id) { this.id = id; }
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
    /** @return la descripcion del pedido */
    public String getDescripcion() { return descripcion; }
    /** @param descripcion descripcion del pedido */
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    /** @return el estado del pedido */
    public String getEstado() { return estado; }
    /** @param estado estado del pedido */
    public void setEstado(String estado) { this.estado = estado; }
    /** @return el total del pedido */
    public double getTotal() { return total; }
    /** @param total total del pedido */
    public void setTotal(double total) { this.total = total; }
    /** @return la fecha del pedido */
    public LocalDate getFecha() { return fecha; }
    /** @param fecha fecha del pedido */
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    /** @return la hora del pedido */
    public String getHora() { return hora; }
    /** @param hora hora del pedido */
    public void setHora(String hora) { this.hora = hora; }
}
