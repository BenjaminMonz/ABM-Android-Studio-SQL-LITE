package entidad;

public class Producto {
    private int id;
    private String nombre;
    private int stock;
    private int categoria;
    private String categoriaDescription;

    public Producto(int id, String nombre, int stock, int categoria) {
        this.id = id;
        this.nombre = nombre;
        this.stock = stock;
        this.categoria = categoria;
    }

    public Producto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public String getCategoriaDescription() {
        return categoriaDescription;
    }

    public void setCategoriaDescription(String categoriaDescription) {
        this.categoriaDescription = categoriaDescription;
    }
}
