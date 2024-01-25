package pqt_biblioteca;

/**
 *
 * @author Diego Cuadrado
 */
public class Libro {
    
    private int id;
    private String tematica;
    private String editorial;
    private String FPublicacion;
    private String idioma;
    private String titulo;
    private String autor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTematica() {
        return tematica;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getFPublicacion() {
        return FPublicacion;
    }

    public void setFPublicacion(String FPublicacion) {
        this.FPublicacion = FPublicacion;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "Libro{" + "id=" + id + ", tematica=" + tematica + ", editorial=" + editorial + ", FPublicacion=" + FPublicacion + ", idioma=" + idioma + ", titulo=" + titulo + ", autor=" + autor + '}';
    }
    
    
}
