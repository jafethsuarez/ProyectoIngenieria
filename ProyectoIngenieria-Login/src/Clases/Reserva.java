package Clases;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Reserva {
		private int id;
		private String fecha;
		private Time hora;
		private ArrayList<Plato>platos;
		private ArrayList<Menu> menus;
		private ArrayList<Cliente> clientes;
		private String numPersonas;
		
		public Reserva()
		{
			
		}

		public Reserva(int id,String fecha, Time hora, ArrayList<Cliente> clientes,ArrayList<Menu> menus,String numPersonas) {
			
			this.id=id;
			this.fecha = fecha;
			this.hora = hora;
			
			this.clientes = clientes;
			this.menus=menus;
			this.numPersonas=numPersonas;
			
		}

		public String getFecha() {
			return fecha;
		}

		public void setFecha(String fecha) {
			this.fecha = fecha;
		}

		public Time getHora() {
			return hora;
		}

		public void setHora(Time hora) {
			this.hora = hora;
		}

		public ArrayList<Cliente> getClientes() {
			return clientes;
		}

		public void anadircliente(Cliente cliente) {
			clientes.add(cliente);
		}

		public ArrayList<Menu> getMenus() {
			return menus;
		}

		public void setMenus(ArrayList<Menu> menus) {
			this.menus = menus;
		}
		
		
		public String getNumPersonas() {
			return numPersonas;
		}

		public void setNumPersonas(String numPersonas) {
			this.numPersonas = numPersonas;
		}

		public void add(Reserva reservas) {
			// TODO Auto-generated method stub
			
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}
		
		public Vector<Reserva> recuperarReservas(){
			return null;
		}
			
		
}
