package Controller;

import java.lang.reflect.Type;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import Clases.CategoriaRestaurante;

public class PlatoManager {
    private static final String PLATO_JSON_PATH = "Categoria.json";
    private Gson gson;

    public PlatoManager() {
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public List<CategoriaRestaurante> leerCategorias() {
        try (FileReader reader = new FileReader(PLATO_JSON_PATH)) {
            Type categoriaListType = new TypeToken<List<CategoriaRestaurante>>() {}.getType();
            return gson.fromJson(reader, categoriaListType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void escribirCategorias(List<CategoriaRestaurante> categorias) {
        try (FileWriter writer = new FileWriter(PLATO_JSON_PATH)) {
            gson.toJson(categorias, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
