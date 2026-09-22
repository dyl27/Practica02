package dao;


import model.Book;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    private List<Book> libros = new ArrayList<>();

    public BookDAO() {
        libros.add(new Book("Cien años de soledad", "Gabriel García Márquez", 250.00));
        libros.add(new Book("El Principito", "Antoine de Saint-Exupéry", 120.50));
        libros.add(new Book("1984", "George Orwell", 180.00));
        libros.add(new Book("Rayuela", "Julio Cortázar", 210.75));
        libros.add(new Book("Pedro Páramo", "Juan Rulfo", 150.00));
        libros.add(new Book("Don Quijote de la Mancha", "Miguel de Cervantes", 320.00));
        libros.add(new Book("La casa de los espíritus", "Isabel Allende", 195.00));
        libros.add(new Book("Ficciones", "Jorge Luis Borges", 175.00));
        libros.add(new Book("El túnel", "Ernesto Sabato", 160.00));
        libros.add(new Book("Como agua para chocolate", "Laura Esquivel", 165.00));
        libros.add(new Book("Crónica de una muerte anunciada", "Gabriel García Márquez", 145.00));
        libros.add(new Book("El amor en los tiempos del cólera", "Gabriel García Márquez", 220.00));
        libros.add(new Book("La sombra del viento", "Carlos Ruiz Zafón", 240.00));
        libros.add(new Book("Fahrenheit 451", "Ray Bradbury", 170.00));
        libros.add(new Book("Un mundo feliz", "Aldous Huxley", 185.00));
        libros.add(new Book("Matar a un ruiseñor", "Harper Lee", 200.00));
        libros.add(new Book("Orgullo y prejuicio", "Jane Austen", 190.00));
        libros.add(new Book("Crimen y castigo", "Fiódor Dostoyevski", 280.00));
        libros.add(new Book("Guerra y paz", "León Tolstói", 350.00));
        libros.add(new Book("Ana Karenina", "León Tolstói", 310.00));
        libros.add(new Book("El retrato de Dorian Gray", "Oscar Wilde", 155.00));
        libros.add(new Book("Drácula", "Bram Stoker", 175.00));
        libros.add(new Book("Frankenstein", "Mary Shelley", 165.00));
        libros.add(new Book("El extranjero", "Albert Camus", 140.00));
        libros.add(new Book("La metamorfosis", "Franz Kafka", 130.00));
        libros.add(new Book("El proceso", "Franz Kafka", 190.00));
        libros.add(new Book("Sapiens", "Yuval Noah Harari", 289.00));
        libros.add(new Book("Homo Deus", "Yuval Noah Harari", 299.00));
        libros.add(new Book("El nombre del viento", "Patrick Rothfuss", 260.00));
        libros.add(new Book("Harry Potter y la piedra filosofal", "J.K. Rowling", 230.00));
        libros.add(new Book("El señor de los anillos", "J.R.R. Tolkien", 380.00));
        libros.add(new Book("El hobbit", "J.R.R. Tolkien", 210.00));
        libros.add(new Book("Juego de tronos", "George R.R. Martin", 340.00));
        libros.add(new Book("Cien años de soledad (ed. especial)", "Gabriel García Márquez", 400.00));
        libros.add(new Book("Rebelión en la granja", "George Orwell", 135.00));
        libros.add(new Book("El gran Gatsby", "F. Scott Fitzgerald", 160.00));
        libros.add(new Book("Moby Dick", "Herman Melville", 220.00));
        libros.add(new Book("Los miserables", "Victor Hugo", 360.00));
        libros.add(new Book("El conde de Montecristo", "Alexandre Dumas", 330.00));
        libros.add(new Book("Los tres mosqueteros", "Alexandre Dumas", 250.00));
        libros.add(new Book("La divina comedia", "Dante Alighieri", 275.00));
        libros.add(new Book("Hamlet", "William Shakespeare", 150.00));
        libros.add(new Book("Macbeth", "William Shakespeare", 145.00));
        libros.add(new Book("Romeo y Julieta", "William Shakespeare", 140.00));
        libros.add(new Book("El alquimista", "Paulo Coelho", 180.00));
        libros.add(new Book("Veronika decide morir", "Paulo Coelho", 175.00));
        libros.add(new Book("La sutil ciencia de las pociones", "J.K. Rowling", 210.00));
        libros.add(new Book("Los detectives salvajes", "Roberto Bolaño", 260.00));
        libros.add(new Book("2666", "Roberto Bolaño", 390.00));
        libros.add(new Book("La ciudad y los perros", "Mario Vargas Llosa", 230.00));
        libros.add(new Book("La casa verde", "Mario Vargas Llosa", 220.00));
    }

    public List<Book> listar() {
        return libros;
    }

    public void agregar(Book libro) {
        libros.add(libro);
    }

    public List<Book> buscar(String texto) {
        List<Book> resultado = new ArrayList<>();
        for (Book libro : libros) {
            if (libro.getNombre().toLowerCase().contains(texto.toLowerCase())
                    || libro.getAutor().toLowerCase().contains(texto.toLowerCase())) {
                resultado.add(libro);
            }
        }
        return resultado;
    }
    
    public List<Book> filtrar(String titulo, String autor, String rangoPrecio) {
        String t = normalizar(titulo);
        String a = normalizar(autor);

        double min = 0;
        double max = Double.MAX_VALUE;
        if (rangoPrecio != null && rangoPrecio.matches("\\d+(\\.\\d+)?-\\d+(\\.\\d+)?")) {
            String[] partes = rangoPrecio.split("-");
            min = Double.parseDouble(partes[0]);
            max = Double.parseDouble(partes[1]);
        }

        List<Book> resultado = new ArrayList<>();
        for (Book libro : libros) {
            boolean coincideTitulo = normalizar(libro.getNombre()).contains(t);
            boolean coincideAutor = normalizar(libro.getAutor()).contains(a);
            boolean coincidePrecio = libro.getPrecio() >= min && libro.getPrecio() < max;

            if (coincideTitulo && coincideAutor && coincidePrecio) {
                resultado.add(libro);
            }
        }
        return resultado;
    }

    private String normalizar(String texto) {
        if (texto == null) {
            return "";
        }
        return Normalizer.normalize(texto, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")
                .toLowerCase()
                .trim();
    }    
    
}