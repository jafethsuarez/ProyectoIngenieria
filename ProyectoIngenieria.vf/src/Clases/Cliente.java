package Clases;

import java.io.BufferedReader;
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



public class Cliente extends Usuario{
	
	private String nombre;
	private String telefono; 
	private ArrayList<Restaurante> restaurantes;
	private ArrayList<Reserva> reservas;
	private int id;
	private static int ultimoId = obtenerUltimoId();
	private ArrayList<CategoriaRestaurante> categorias;
	
	
	public Cliente(String usuario, String contrasena, String ubicacion, String nombre, String telefono,
	        ArrayList<Reserva> reservas,String categoriasJson) {
	    super(usuario, contrasena, ubicacion);
	    this.id= ++ultimoId;
	    this.nombre = nombre;
	    this.telefono = telefono;
	   // this.restaurantes = cargarRestaurantes(restauranteJson);
	    this.categorias = cargarCategorias(categoriasJson);
	    this.reservas = reservas != null ? reservas : new ArrayList<>();
	}


	public Cliente()
	{
		
	}
	

	
	    // ... el resto de las variables y métodos de la clase

	public void setId(int id) {
		this.id = id;
	}


	private static int obtenerUltimoId() {
	      int maxId = 0;
	        // Leer el archivo JSON y obtener el último ID
	      try (Reader reader = new FileReader("Datos.json")) {
	          Gson gson = new Gson();
	          Type tipoListaClientes = new TypeToken<ArrayList<Cliente>>() {}.getType();
	          ArrayList<Cliente> clientes = gson.fromJson(reader, tipoListaClientes);

	        if (clientes != null) {
	            for (Cliente cliente : clientes) {
	               if (cliente.getId() > maxId) {
	                     maxId = cliente.getId();
	                   }
	               }
	           }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return maxId;
	    }
	
	public ArrayList<CategoriaRestaurante> cargarCategorias(String categoriasJson) {
        ArrayList<CategoriaRestaurante> categorias = new ArrayList<>();
        try (Reader reader = new FileReader(categoriasJson)) {
            Gson gson = new Gson();
            Type tipoListaCategorias = new TypeToken<ArrayList<CategoriaRestaurante>>() {
            }.getType();
            categorias = gson.fromJson(reader, tipoListaCategorias);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return categorias;
    }
	



	public int getId() {
	    return id;
	}

	public ArrayList<CategoriaRestaurante> getCategorias() {
        return categorias;
    }
	
	public ArrayList<Restaurante> getRestaurantes() {
		return restaurantes;
	}

	

	public void setReservas(ArrayList<Reserva> reservas) {
	    this.reservas = reservas;
	}


	public ArrayList<Reserva> getReservas() {
		return reservas;
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

	
	public static void guardarCliente(Vector<Cliente> clientes) {
	    Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();     
	    try(FileWriter writer = new FileWriter("Datos.json", false)){
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
	public void cargarReservas(int idCliente) {
	    String archivoReservas = "ReservasCli_" + idCliente + ".json";
	    try (Reader reader = new FileReader(archivoReservas)) {
	        Gson gson = new Gson();
	        Type tipoListaReservas = new TypeToken<ArrayList<Reserva>>() {}.getType();
	        this.reservas = gson.fromJson(reader, tipoListaReservas);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}


	public void anadirCliente(Cliente nuevoCliente) {
		// TODO Auto-generated method stub
		Vector<Cliente> trabajadores = recuperarCliente();
		trabajadores.add(nuevoCliente);
		
		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();		
		try(FileWriter writer = new FileWriter("Datos.json",false)){
			prettyGson.toJson(trabajadores, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	

}
