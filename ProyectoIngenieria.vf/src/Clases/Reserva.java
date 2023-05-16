package Clases;
import java.util.List;

public class Reserva {
    private int id;
    private Fecha fecha;
    private String hora;
    private int cantidad;
    private List<String> menuSeleccionado;
    private static int nextId = 1;
    private String nombreRest;

    public Reserva() {

    }

    public Reserva(int id) {
        this.id = id;
        nextId = id + 1;
    }

    public Reserva(int id, Fecha fecha, String hora, int cantidad, List<String> menuSeleccionado, String nombreRest) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.cantidad = cantidad;
        this.menuSeleccionado = menuSeleccionado;
        this.nombreRest = nombreRest;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Fecha getFecha() {
        return fecha;
    }

    public void setFecha(Fecha fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public List<String> getMenuSeleccionado() {
        return menuSeleccionado;
    }

    public void setMenuSeleccionado(List<String> menuSeleccionado) {
        this.menuSeleccionado = menuSeleccionado;
    }

    public int getId() {
        return id;
    }

    public String getNombreRest() {
        return nombreRest;
    }

    public void setNombreRest(String nombreRest) {
        this.nombreRest = nombreRest;
    }
}
