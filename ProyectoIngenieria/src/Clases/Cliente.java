package Clases;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Vector;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import javafx.scene.control.Alert;

public class Cliente extends Usuario{
	private String nombre;
	private String telefono;
	private ArrayList<Restaurante> restaurantes;
	
	
	
	public Cliente(String usuario, String contrasena, String ubicacion, String nombre, String telefono,
			ArrayList<Restaurante> restaurantes) {
		super(usuario, contrasena, ubicacion);
		this.nombre = nombre;
		this.telefono = telefono;
		this.restaurantes = restaurantes;
	}

	public Cliente()
	{
		
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {		
			this.telefono = telefono;		
	}

	public ArrayList<Restaurante> getRestaurantes() {
		return restaurantes;
	}

	public void anadirRestaurante(Restaurante restaurante) {
		restaurantes.add(restaurante);
	}
	//Deserealizar
	public Vector<Cliente> recuperarCliente(){
	 	Vector<Cliente> clientes = new Vector<Cliente>();
		    	
	  	try(Reader reader = new FileReader("Datos.json")){
	   		Gson gson = new Gson();
	   		Type tipoListaClientes = new TypeToken<Vector<Cliente>>() {}.getType();
	   		clientes = gson.fromJson(reader, tipoListaClientes);
	   	} catch(IOException e) {
	   		e.printStackTrace();
	   	}
	   	
	    	return clientes;
	    }
	
	public void anadirCliente(Cliente cliente) {
		Vector<Cliente> clientes = recuperarCliente();
		clientes.add(cliente);
		
		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();		
		try(FileWriter writer = new FileWriter("Datos.json",false)){
			prettyGson.toJson(clientes, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}	
	public void modificarCliente(Vector<Cliente> clientes) {
		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();		
		try(FileWriter writer = new FileWriter("Datos.json",false)){
			prettyGson.toJson(clientes, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
