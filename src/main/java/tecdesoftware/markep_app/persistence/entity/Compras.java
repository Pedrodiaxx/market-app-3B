package tecdesoftware.markep_app.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name= "compras")
public class Compras {

    @Id //lave primaria
    //Hace el Id autoincrementar
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id_compra")
    private Integer idCompra;

    @Column(name = "id_cliente")
    private Integer idCliente;

    private String fecha;

    @Column (name = "medio_pago")
    private String madioPago;

    private String comentario;

    private Boolean estado;

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getMadioPago() {
        return madioPago;
    }

    public void setMadioPago(String madioPago) {
        this.madioPago = madioPago;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
