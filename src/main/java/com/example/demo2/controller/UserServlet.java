package com.example.demo2.controller;

import com.example.demo2.model.User;
import com.example.demo2.service.UserService;
import com.example.demo2.service.impl.UserServiceImpl;

import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/")
public class UserServlet extends HttpServlet {

    private UserService userService;

    public void init() {
        userService = new UserServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("hello do post");
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("hello do get");
        String action = req.getRequestURI();
        System.out.println(action);
        switch (action) {
            case "/new": {
                showNewForm(req, resp);
                break;
            }

            case "/insert": {
                insertUser(req, resp);
                break;
            }
            case "/delete": {
                deleteUser(req, resp);
                break;
            }
            case "/edit": {
                showEditForm(req, resp);
                break;
            }

            case "/update": {
                updateUser(req, resp);
                break;
            }
            default: {
                listUser(req, resp);
                break;
            }
        }

    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        User existingUser = userService.getUserById(id);
        req.setAttribute("user",existingUser);
        req.getRequestDispatcher("user-form.jsp").forward(req,resp);
    }

    private void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        userService.deleteUser(id);
        resp.sendRedirect("list");
    }

    private void insertUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String address = req.getParameter("address");
        User newUser = new User(name,email,address);
        userService.insertOrUpdateUser(newUser);
        resp.sendRedirect("list");

    }

    private void updateUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String address = req.getParameter("address");

        User user = new User( id, name, email, address);
        userService.insertOrUpdateUser(user);
        resp.sendRedirect("list");
    }

    private void listUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("listUser",this.userService.listUser());
        RequestDispatcher dispatcher = req.getRequestDispatcher("user-list.jsp");
        dispatcher.forward(req,resp);
    }

    private void showNewForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("user-form.jsp");
        dispatcher.forward(req,resp);
    }
}