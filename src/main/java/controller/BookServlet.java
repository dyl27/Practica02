package controller;

import dao.BookDAO;
import model.Book;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/BookServlet")
public class BookServlet extends HttpServlet {

    private BookDAO dao = new BookDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if ("buscar".equals(accion)) {
            List<Book> resultado = dao.filtrar(
                    request.getParameter("buscarTitulo"),
                    request.getParameter("buscarAutor"),
                    request.getParameter("rangoPrecio"));
            request.setAttribute("libros", resultado);
        } else {
            request.setAttribute("libros", dao.listar());
        }

        request.getRequestDispatcher("libreria.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String nombre = request.getParameter("nombre");
        String autor = request.getParameter("autor");
        double precio = Double.parseDouble(request.getParameter("precio"));

        dao.agregar(new Book(nombre, autor, precio));

        request.setAttribute("libros", dao.listar());
        request.getRequestDispatcher("libreria.jsp").forward(request, response);
    }
}