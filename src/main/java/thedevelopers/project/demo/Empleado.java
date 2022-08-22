package thedevelopers.project.demo;

public class Empleado {

    private String nombre;
    private String correo;
    private String nombreEmpresa;
    private String rol;

    public Empleado(String nombre, String correo, String nombreEmpresa, String rol) {
        this.nombre = nombre;
        this.correo = correo;
        this.nombreEmpresa = nombreEmpresa;
        this.rol = rol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                ", nombreEmpresa='" + nombreEmpresa + '\'' +
                ", rol='" + rol + '\'' +
                '}';
    }
}
