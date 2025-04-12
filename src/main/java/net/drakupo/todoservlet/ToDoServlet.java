package net.drakupo.todoservlet;

import java.time.LocalDate;
import java.util.*;
import java.io.*;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet({"/", "/list"})
public class ToDoServlet extends HttpServlet {
    private ToDoAccess access;

    public void init() {
        access = new ToDoAccess();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/new":
                    newTaskForm(request, response);
                    break;
                case "/add":
                    newTask(request, response);
                    break;
                case "/delete":
                    deleteTask(request, response);
                    break;
                case "/edit":
                    editTaskForm(request, response);
                    break;
                case "/update":
                    editTask(request, response);
                    break;
                default:
                    listTasks(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }

    }

    private void listTasks(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        List<ToDoTask> taskList = access.showTasks();
        request.setAttribute("taskList", taskList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("ToDoServlet.jsp");
        dispatcher.forward(request, response);
    }

    private void newTaskForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("newForm.jsp");
        dispatcher.forward(request, response);
    }

    private void editTaskForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        ToDoTask extTask = access.showTask(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("editForm.jsp");
        request.setAttribute("task", extTask);
        dispatcher.forward(request, response);
    }

    private void newTask(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        ToDoTask newTask;
        int id = access.genID();
        String description = request.getParameter("description");
        if (request.getParameter("dueDate").isEmpty())
            newTask = new ToDoTask(id, description);
        else  {
            LocalDate dueDate = LocalDate.parse(request.getParameter("dueDate"));
            if (request.getParameter("startDate").isEmpty())
                newTask = new ToDoTask(id, description, dueDate);
            else {
                    LocalDate startDate = LocalDate.parse(request.getParameter("startDate"));
                    newTask = new ToDoTask(id, description, dueDate, startDate);
            }
        }
        access.addTask(newTask);
        response.sendRedirect("list");
    }

    private void editTask(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        String description = request.getParameter("description");
        LocalDate dueDate = LocalDate.parse(request.getParameter("dueDate"));
        LocalDate startDate = LocalDate.parse(request.getParameter("startDate"));
        boolean done = Boolean.parseBoolean(request.getParameter("done"));
        ToDoTask updateTask = new ToDoTask(id, description, dueDate, startDate);
        updateTask.setDone(done);
        access.updateTask(updateTask);
        response.sendRedirect("list");
    }

    private void deleteTask(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        access.deleteTask(id);
        response.sendRedirect("list");
    }

}