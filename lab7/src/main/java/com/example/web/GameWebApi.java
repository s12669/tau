package com.example.web;

import com.example.domain.Game;
import com.example.service.GameManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/api")
public class GameWebApi extends HttpServlet {

    GameManager GameManager = new GameManager();

    private static final long serialVersionUID = 1L;

    public GameWebApi() throws SQLException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");

        PrintWriter out = response.getWriter();

        out.println("{\"data\":[");
        String comma = "";
        for (Game g : GameManager.getAllGames()) {
            out.println(comma+ "{"+
                    "\"id\":" + g.getId() + "," +
                    "\"title\":\"" + g.getTitle() + "\"," +
                    "\"dirn\":\"" + g.getDirectorName() + "\"," +
                    "\"dirsurn\":\"" + g.getDirectorSurname() + "\"," +
                    "\"company\":\"" + g.getCompany() + "\"," +
                    " \"price\":" + g.getPrice() +
                    "}");
            comma = ",";
        }
        out.println("]}");
        out.close();
    }

}