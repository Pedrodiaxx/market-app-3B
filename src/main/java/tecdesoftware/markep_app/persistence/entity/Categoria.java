package tecdesoftware.markep_app.persistence.entity;

import jakarta.persistence.*;


@Entity
@Table (name="categorias")
public class Categoria {

    @Id //lave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id_categoria")
    private Integer idCategoria;

    private String descripcion;
    private Boolean estado;

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
