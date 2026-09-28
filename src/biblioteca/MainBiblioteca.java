package biblioteca;

public class MainBiblioteca {
    public static void main(String[] args) {

        // --- Alta de libros en el catálogo ---
        // Constructor corto: arranca con 1 copia y $15000.0.
        // new Libro(); // no compila: al declarar constructores propios,
        //               // el constructor sin argumentos que regalaba el compilador ya no existe.
        Libro libro1 = new Libro("Clean Code", "Robert C. Martin", "9780132350884");

        // Constructor principal: control total de los valores.
        Libro libro2 = new Libro("Efectivo con Java", "Ana Restrepo",
                "9781234567897", 3, 22000.0);

        Libro libro3 = new Libro("Cien Años de Soledad", "Gabriel García Márquez",
                "9780307474728", 2, 18500.0);

        // --- Caso 1: dato inválido al construir ---
        // El constructor no rompe: reemplaza y sigue.
        Libro libroInvalido = new Libro("", "Anónimo", "0000000000", 2, 10000.0);
        System.out.println("Título guardado (debería ser \"Sin título\"): "
                + libroInvalido.getTitulo());
        System.out.println();

        // --- Caso 2: setter que rechaza un precio inválido ---
        // El boolean avisa si el cambio se aplicó; el precio queda intacto.
        boolean aceptado = libro1.setPrecioReposicion(-100.0);
        System.out.println("¿Se aceptó el precio -100.0? " + aceptado
                + " (se mantiene el precio anterior: $"
                + libro1.getPrecioReposicion() + ")");
        System.out.println();

        // --- Fichas de todos los libros ---
        libro1.mostrarFicha();
        libro2.mostrarFicha();
        libro3.mostrarFicha();

        // --- Agotar copias y comprobar que el contador nunca baja de 0 ---
        System.out.println("\n--- Prestando libro1 hasta agotar ---");
        System.out.println("¿Préstamo aceptado? " + libro1.prestar()); // 1 → 0
        System.out.println("¿Préstamo aceptado? " + libro1.prestar()); // false, sin cambios

        System.out.println("Copias de libro1 (debe ser 0, nunca negativo): "
                + libro1.getCopiasDisponibles());

        // --- Devolución: el contador sube otra vez ---
        System.out.println("\n--- Devolviendo libro1 ---");
        libro1.devolver(); // 0 → 1

        // --- Setter con un valor válido ---
        System.out.println();
        boolean aceptadoOk = libro1.setPrecioReposicion(18000.0);
        System.out.println("¿Se aceptó el precio 18000.0? " + aceptadoOk);
        System.out.println("Precio de reposición actualizado de \"Clean Code\": $15000.0 -> $"
                + libro1.getPrecioReposicion());
    }
}
