package tecdesoftware.markep_app.persistence.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name= "compras")
public class Compra {

    @Id //lave primaria
    //Hace el Id autoincrementar
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id_compra")
    private Integer idCompra;

    @Column(name = "id_cliente")
    private Integer idCliente;

    private LocalDateTime fecha;

    @Column (name = "medio_pago")
    private String madioPago;

    private String comentario;

    private Boolean estado;

    //Relación con la entidad cliente: Muchas compras a un cliente
    @ManyToOne
    //no quiero que se modifique la entidad cliente, solo relacionarla
    @JoinColumn (name="id_cliente", insertable = false, updatable = false)
    private Cliente cliente;

    //Relacion con la entidad CompraProducto
    @OneToMany (mappedBy = "compra")
    private List< CompraProducto> productos;

    public Integer getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Integer idCompra) {
        this.idCompra = idCompra;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getMadioPago() {
        return madioPago;
    }

    public void setMadioPago(String madioPago) {
        this.madioPago = madioPago;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
