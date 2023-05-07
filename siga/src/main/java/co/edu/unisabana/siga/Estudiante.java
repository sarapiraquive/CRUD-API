package co.edu.unisabana.siga;

public class Estudiante {
    private String nombre;
    private int codigo;
    private int semestre;
    private String genero;

    public Estudiante (String nombre, int codigo, int semestre)
    {
        this.nombre=nombre;
        this.codigo=codigo;
        this.semestre=semestre;
        this.genero=genero;
    }
    public Estudiante(){}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
