package Clases;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Vector;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class CategoriaRestaurante {
   
    String nombreCategoria;
    ArrayList<Restaurante> restaurantes;

    public CategoriaRestaurante(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
        this.restaurantes = new ArrayList<>();
    }

   

	public CategoriaRestaurante() {
		// TODO Auto-generated constructor stub
	}

	public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public ArrayList<Restaurante> getRestaurantes() {
        return restaurantes;
    }

    public void agregarRestaurante(Restaurante restaurante) {
        this.restaurantes.add(restaurante);
    }
    public ArrayList<CategoriaRestaurante> recuperarCategorias() {
        ArrayList<CategoriaRestaurante> categorias = new ArrayList<>();

        try (Reader reader = new FileReader("Categoria.json")) {
            Gson gson = new Gson();
            Type tipoListaCategorias = new TypeToken<ArrayList<CategoriaRestaurante>>() {}.getType();
            categorias = gson.fromJson(reader, tipoListaCategorias);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return categorias;
    }
    
    
}
