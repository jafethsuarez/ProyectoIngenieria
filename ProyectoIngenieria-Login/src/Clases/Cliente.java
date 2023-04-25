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
	
	
	public Cliente(String usuario, String contrasena, String ubicacion, String nombre, String telefono,
	        ArrayList<Reserva> reservas, String restauranteJson) {
	    super(usuario, contrasena, ubicacion);
	    this.id= ++ultimoId;
	    this.nombre = nombre;
	    this.telefono = telefono;
	    this.restaurantes = cargarRestaurantes(restauranteJson);
	    this.reservas = reservas != null ? reservas : new ArrayList<>();
	}


	public Cliente()
	{
		
	}
	

	
	    // ... el resto de las variables y métodos de la clase

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
	  public ArrayList<Restaurante> cargarRestaurantes(String restauranteJson) {
	        ArrayList<Restaurante> restaurantes = new ArrayList<>();
	        try (Reader reader = new FileReader(restauranteJson)) {
	        	StringBuilder contentBuilder = new StringBuilder();
	        	try (BufferedReader br = new BufferedReader(new FileReader(restauranteJson))) {
	        	    String line;
	        	    while ((line = br.readLine()) != null) {
	        	        contentBuilder.append(line).append("\n");
	        	    }
	        	} catch (IOException e) {
	        	    e.printStackTrace();
	        	}
	        	System.out.println(contentBuilder.toString());

	            Gson gson = new Gson();
	            Type tipoListaRestaurantes = new TypeToken<ArrayList<Restaurante>>() {
	            }.getType();
	            restaurantes = gson.fromJson(reader, tipoListaRestaurantes);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return restaurantes;
	    }



	public int getId() {
	    return id;
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
