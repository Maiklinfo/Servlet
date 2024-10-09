

import logic.Pet;
import logic.PetModel;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@WebServlet(name = "PetServlet", urlPatterns = {"/createPet", "/getAll", "/getPet", "/deletePet", "/updatePet/*"})
public class PetServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final PetModel petModel = PetModel.getInstance();
    private static final AtomicInteger newId = new AtomicInteger(1);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uri = request.getRequestURI();
        if (uri.equals("/createPet")) {
            String name = request.getParameter("name");
            String type = request.getParameter("type");
            int age = Integer.parseInt(request.getParameter("age"));
            Pet pet = new Pet(name, type, age);
            int id = newId.getAndIncrement();
            petModel.add(pet, id);
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.write("Pet created successfully with ID: " + id);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uri = request.getRequestURI();
        if (uri.equals("/getAll")) {
            Map<Integer, Pet> allPets = petModel.getAll();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.write(allPets.toString());
        } else if (uri.equals("/getPet")) {
            int id = Integer.parseInt(request.getParameter("id"));
            Pet pet = petModel.getFromList(id);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.write(pet.toString());
        }
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uri = request.getRequestURI();
        if (uri.equals("/deletePet")) {
            int id = Integer.parseInt(request.getParameter("id"));
            Pet removedPet = petModel.removeFromList(id);
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            if (removedPet != null) {
                out.write("Pet with ID " + id + " deleted successfully");
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        }
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uri = request.getRequestURI();
        if (uri.startsWith("/updatePet/")) {
            int id = Integer.parseInt(uri.substring(uri.lastIndexOf('/') + 1));
            Pet updatedPet = new Pet(
                    request.getParameter("name"),
                    request.getParameter("type"),
                    Integer.parseInt(request.getParameter("age"))
            );
            if (petModel.getFromList(id) != null) {
                petModel.update(id, updatedPet);
                response.setContentType("text/plain");
                response.setCharacterEncoding("UTF-8");
                PrintWriter out = response.getWriter();
                out.write("Pet with ID " + id + " updated successfully");
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        }
    }
}