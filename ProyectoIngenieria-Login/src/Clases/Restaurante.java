package Clases;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.channels.NonWritableChannelException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class Restaurante extends Usuario{
	private String nombreRestaurante;
    private Menu menu;
    private ArrayList<Reserva> reservas;
    private CategoriaRestaurante categoriaRestaurante;
    private int mesa;
    private int aforo;

    public Restaurante(String usuario, String contrasena, String ubicacion, String nombreRestaurante, Menu menus,
                       ArrayList<Reserva> reservas, int mesa, int aforo, CategoriaRestaurante categoriaRestaurante) {
        super(usuario, contrasena, ubicacion);
        this.nombreRestaurante = nombreRestaurante;
        this.reservas = reservas;
        this.menu = menus;
        this.mesa = mesa;
        this.aforo = aforo;
        this.categoriaRestaurante = categoriaRestaurante;
    }

	public ArrayList<Reserva> cargarReservas() {
		ArrayList<Reserva> reservas = new ArrayList<>();
	    try(Reader reader = new FileReader("Reserva.json")){
	        Gson gson = new Gson();
	        Type tipoListaReserva = new TypeToken<ArrayList<Reserva>>() {}.getType();
	        reservas = gson.fromJson(reader, tipoListaReserva);
	    } catch(IOException e) {
	        e.printStackTrace();
	    }
	    return reservas;
    }

	
	

	private void guardarMenu() {
	    try (FileWriter writer = new FileWriter("Datos.json")) {
	        Gson gson = new GsonBuilder().setPrettyPrinting().create();
	        gson.toJson(this, Restaurante.class, writer);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	
	public String AsignarCliente() {
		int i=0;
		Cliente cliente = new Cliente();
		Vector<Cliente> clientes = cliente.recuperarCliente();
		return clientes.get(i).getNombre();
		
	}
	
	
	public Restaurante()
	{
		
	}
	
	
	public int getMesa() {
		return mesa;
	}


	public void setMesa(int mesa) {
		this.mesa = mesa;
	}


	public int getAforo() {
		return aforo;
	}


	public void setAforo(int aforo) {
		this.aforo = aforo;
	}


	public String getNombreRestaurante() {
		return nombreRestaurante;
	}
	public void setNombreRestaurante(String nombreRestaurante) {
		this.nombreRestaurante = nombreRestaurante;
	}
	
	
	public void addReserva(Reserva reserva) {
	        reservas.add(reserva);
	    }

	public void setReservas(ArrayList<Reserva> reservas) {
	        this.reservas = reservas;
	    }
	
	
	public Vector<Restaurante> recuperarRestaurantes(){
	 	Vector<Restaurante> restaurantes = new Vector<Restaurante>();
		    	
	  	try(Reader reader = new FileReader("Restaurantes.json")){
	   		Gson gson = new Gson();
	   		Type tipoListaRestaurantes = new TypeToken<Vector<Restaurante>>() {}.getType();
	   		restaurantes = gson.fromJson(reader, tipoListaRestaurantes);
	   	} catch(IOException e) {
	   		e.printStackTrace();
	   	}
	   	
	    	return restaurantes;
	    }
	public CategoriaRestaurante getCategoriaRestaurante() {
        return categoriaRestaurante;
    }

    public void setCategoriaRestaurante(CategoriaRestaurante categoriaRestaurante) {
        this.categoriaRestaurante = categoriaRestaurante;
    }


	public void add(Restaurante restaurantes) {
		// TODO Auto-generated method stub
		
	}
	

}

	
