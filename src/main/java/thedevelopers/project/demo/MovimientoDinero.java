package thedevelopers.project.demo;

public class MovimientoDinero {

    private int monto;
    //booleano para manejar el monto: True positivo, False negativo
    private boolean esPositivo;
    private String concepto;
    private int documentoUsuario;

    public MovimientoDinero(int monto, boolean esPositivo, String concepto, int documentoUsuario) {
        this.monto = monto;
        this.esPositivo = esPositivo;
        this.concepto = concepto;
        this.documentoUsuario = documentoUsuario;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public boolean isEsPositivo() {
        return esPositivo;
    }

    public void setEsPositivo(boolean esPositivo) {
        this.esPositivo = esPositivo;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public int getDocumentoUsuario() {
        return documentoUsuario;
    }

    public void setDocumentoUsuario(int documentoUsuario) {
        this.documentoUsuario = documentoUsuario;
    }

    @Override
    public String toString() {
        return "MovimientoDinero{" +
                "monto=" + monto +
                ", esPositivo=" + esPositivo +
                ", concepto='" + concepto + '\'' +
                ", documentoUsuario=" + documentoUsuario +
                '}';
    }
}
