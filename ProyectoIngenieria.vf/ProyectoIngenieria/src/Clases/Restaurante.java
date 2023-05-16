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
	private ArrayList<Reserva>reservas;
	private CategoriaRestaurante categoriaRestaurante;
	private String cliente;
	private int mesa;
	private int aforo;
	
	
	
	public Restaurante(String usuario, String contrasena, String ubicacion, String nombreRestaurante, Menu menu,
			ArrayList<Reserva> reservas, String cliente, int mesa, int aforo,CategoriaRestaurante categoriaRestaurante) {
		super(usuario, contrasena, ubicacion);
		this.nombreRestaurante = nombreRestaurante;
		this.menu = menu;
		this.reservas = reservas;
		this.mesa = mesa;
		this.aforo = aforo;
		this.categoriaRestaurante= categoriaRestaurante;
		cliente = AsignarCliente();
		
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
	public Menu getMenu() {
		return menu;
	}
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	
	public ArrayList<Reserva> getReservas() {
		return reservas;
	}
	public void setReservas(Reserva reserva) {
		 reservas.add(reserva);
	}
	public void AgregarCategoria(CategoriaRestaurante categoriaRestaurante) {
		categoriaRestaurante.agregarCategoria(categoriaRestaurante);
	}
	public void agregarPlatoAlMenu(Plato plato) {
        this.menu.agregarPlato(plato);
    }
	/*
    public void mostrarInformacion(Restaurante restaurante) {
        restaurante.getNombreRestaurante();
        categoriaRestaurante.getCategorias();
        StringBuilder menuStringBuilder = new StringBuilder("Menú:\n");
        for (Plato plato : restaurante.getMenu().agregarPlato(plato)) {
            menuStringBuilder.append("- ").append(plato.toString()).append("\n");
        }
        menuLabel.setText(menuStringBuilder.toString());

        StringBuilder reservasStringBuilder = new StringBuilder("Reservas:\n");
        for (Reserva reserva : restaurante.getReservas()) {
            reservasStringBuilder.append("Fecha: ").append(reserva.getFecha()).append(", Hora: ").append(reserva.getHora()).append("\n");
        }
        reservasLabel.setText(reservasStringBuilder.toString());
    }*/

}

	
