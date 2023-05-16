package Clases;

import java.util.ArrayList;
import java.util.List;

public class CategoriaRestaurante {
    String idCategoria;
    String nombreCategoria;
    List<CategoriaRestaurante> categorias;

    public CategoriaRestaurante(String idCategoria, String nombreCategoria) {
        super();
        this.idCategoria = idCategoria;
        this.nombreCategoria = nombreCategoria;
        this.categorias = new ArrayList<>();
    }

    public String getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(String idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public void agregarCategoria(CategoriaRestaurante categoriaRestaurante) {
        categorias.add(categoriaRestaurante);
    }

    public List<CategoriaRestaurante> getCategorias() {
        return categorias;
    }
}
	

