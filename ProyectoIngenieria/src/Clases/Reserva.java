package Clases;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;

public class Reserva {
		private Date fecha;
		private Time hora;
		private ArrayList<Plato> Platos;
		private ArrayList<Menu> menus;
		private ArrayList<Cliente> clientes;
		
		public Reserva()
		{
			
		}

		public Reserva(Date fecha, Time hora, ArrayList<Plato> platos, ArrayList<Cliente> clientes,ArrayList<Menu> menus) {
			
			this.fecha = fecha;
			this.hora = hora;
			this.Platos = platos;
			this.clientes = clientes;
			this.menus=menus;
			
		}

		public Date getFecha() {
			return fecha;
		}

		public void setFecha(Date fecha) {
			this.fecha = fecha;
		}

		public Time getHora() {
			return hora;
		}

		public void setHora(Time hora) {
			this.hora = hora;
		}

		public ArrayList<Plato> getPlatos() {
			return Platos;
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
			
		
}
