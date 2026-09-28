package biblioteca;

public class Libro {

    // Estado protegido: nada de esto se toca desde afuera.
    // título, autor e isbn se fijan una sola vez → final.
    private final String titulo;
    private final String autor;
    private final String isbn;
    private int copiasDisponibles;
    private double precioReposicion;

    // Constructor principal: acá vive TODA la validación.
    // Si un dato viene mal, no se aborta la creación: se
    // reemplaza por un valor por defecto seguro y se avisa.
    public Libro(String titulo, String autor, String isbn,
                 int copiasDisponibles, double precioReposicion) {

        if (titulo == null || titulo.isBlank()) {
            System.out.println("Título inválido, se usó \"Sin título\" por defecto.");
            this.titulo = "Sin título";
        } else {
            this.titulo = titulo;
        }

        if (autor == null || autor.isBlank()) {
            System.out.println("Autor inválido, se usó \"Autor desconocido\" por defecto.");
            this.autor = "Autor desconocido";
        } else {
            this.autor = autor;
        }

        if (isbn == null || isbn.isBlank()) {
            System.out.println("ISBN inválido, se usó \"ISBN pendiente\" por defecto.");
            this.isbn = "ISBN pendiente";
        } else {
            this.isbn = isbn;
        }

        if (copiasDisponibles < 0) {
            System.out.println("Copias inválidas (" + copiasDisponibles
                    + "), se usó 0 por defecto.");
            this.copiasDisponibles = 0;
        } else {
            this.copiasDisponibles = copiasDisponibles;
        }

        // Se reutiliza el setter validado para no repetir la regla (> 0).
        if (!setPrecioReposicion(precioReposicion)) {
            System.out.println("Precio de reposición inválido, se usó $15000.0 por defecto.");
            this.precioReposicion = 15000.0;
        }
    }

    // Constructor corto: delega todo con this(...).
    // Sin repetir validaciones: el principal ya se encarga.
    public Libro(String titulo, String autor, String isbn) {
        this(titulo, autor, isbn, 1, 15000.0);
    }

    // Getters: única vía de lectura del estado.
    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getCopiasDisponibles() {
        return copiasDisponibles;
    }

    public double getPrecioReposicion() {
        return precioReposicion;
    }

    // Setter con validación: true si acepta, false si rechaza
    // (y en ese caso no toca el precio anterior).
    public boolean setPrecioReposicion(double precio) {
        if (precio > 0) {
            this.precioReposicion = precio;
            return true;
        }
        return false;
    }

    // Método de dominio: prestar solo baja si hay copias.
    // Si no hay, no modifica nada y devuelve false.
    public boolean prestar() {
        if (copiasDisponibles > 0) {
            copiasDisponibles--;
            System.out.println("Préstamo registrado: \"" + titulo
                    + "\". Copias disponibles: " + copiasDisponibles);
            return true;
        } else {
            System.out.println("Error: no hay copias disponibles de \""
                    + titulo + "\" para prestar.");
            return false;
        }
    }

    // Complemento de prestar(): sube el contador e informa.
    public void devolver() {
        copiasDisponibles++;
        System.out.println("Devolución registrada: \"" + titulo
                + "\". Copias disponibles: " + copiasDisponibles);
    }

    // Salida formateada para inspección rápida del objeto.
    public void mostrarFicha() {
        System.out.println("=== Ficha de libro ===");
        System.out.println("Título:  " + titulo);
        System.out.println("Autor:   " + autor);
        System.out.println("ISBN:    " + isbn);
        System.out.println("Copias disponibles: " + copiasDisponibles);
        System.out.println("Precio de reposición: $" + precioReposicion);
        System.out.println("=======================");
    }
}
