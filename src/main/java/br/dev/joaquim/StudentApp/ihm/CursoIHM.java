package br.dev.joaquim.StudentApp.ihm;

import java.util.Scanner;

import br.dev.joaquim.StudentApp.dao.CursoDAO;
import br.dev.joaquim.StudentApp.entities.Curso;

public class CursoIHM {

    private CursoDAO cursoDAO;

    public CursoIHM(CursoDAO cursoDAO) {
        this.cursoDAO = cursoDAO;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        int option = -1;

        while (option != 0) {
            System.out.println("=== Course Management Menu ===");
            System.out.println("1. Add Course");
            System.out.println("2. View All Courses");
            System.out.println("3. Update Course");
            System.out.println("4. Delete Course");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    addCourse(scanner);
                    break;
                case 2:
                    viewAllCourses();
                    break;
                case 3:
                    updateCourse(scanner);
                    break;
                case 4:
                    deleteCourse(scanner);
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }

        scanner.close();
    }

    private void addCourse(Scanner scanner) {
        System.out.print("Enter course name: ");
        String nome = scanner.nextLine();
        System.out.print("Enter course code: ");
        int codigo = scanner.nextInt();

        Curso curso = new Curso();
        curso.setNome(nome);
        curso.setCodigo(codigo);

        cursoDAO.create(curso);
        System.out.println("Course added successfully.");
    }

    private void viewAllCourses() {
        System.out.println("=== List of Courses ===");
        for (Curso curso : cursoDAO.findAll()) {
            System.out.println(curso);
        }
    }

    private void updateCourse(Scanner scanner) {
        System.out.print("Enter course code to update: ");
        int codigo = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Curso curso = cursoDAO.findByCodigo(codigo);
        if (curso == null) {
            System.out.println("Course not found.");
            return;
        }

        System.out.print("Enter new name: ");
        String nome = scanner.nextLine();

        curso.setNome(nome);

        cursoDAO.update(curso);
        System.out.println("Course updated successfully.");
    }

    private void deleteCourse(Scanner scanner) {
        System.out.print("Enter course code to delete: ");
        int codigo = scanner.nextInt();
        cursoDAO.delete(codigo);
        System.out.println("Course deleted successfully.");
    }
}
