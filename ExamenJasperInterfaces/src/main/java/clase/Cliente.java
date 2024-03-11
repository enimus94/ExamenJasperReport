package clase;

public class Cliente {
    private Long id;
    private String nombre;
    private String sexo;
    private Long peso;
    private Long edad;
    private Long talla;
    private String tipoActividad;
    private String observaciones;

    public Cliente(){

    }

    public Cliente(String nombre, String sexo, Long peso, Long edad, Long talla, String tipoActividad, String observaciones) {
        this.nombre = nombre;
        this.sexo = sexo;
        this.peso = peso;
        this.edad = edad;
        this.talla = talla;
        this.tipoActividad = tipoActividad;
        this.observaciones = observaciones;
    }

    public Cliente(Long id, String nombre, String sexo, Long peso, Long edad, Long talla, String tipoActividad, String observaciones) {
        this.id = id;
        this.nombre = nombre;
        this.sexo = sexo;
        this.peso = peso;
        this.edad = edad;
        this.talla = talla;
        this.tipoActividad = tipoActividad;
        this.observaciones = observaciones;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Long getPeso() {
        return peso;
    }

    public void setPeso(Long peso) {
        this.peso = peso;
    }

    public Long getEdad() {
        return edad;
    }

    public void setEdad(Long edad) {
        this.edad = edad;
    }

    public Long getTalla() {
        return talla;
    }

    public void setTalla(Long talla) {
        this.talla = talla;
    }

    public String getTipoActividad() {
        return tipoActividad;
    }

    public void setTipoActividad(String tipoActividad) {
        this.tipoActividad = tipoActividad;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
