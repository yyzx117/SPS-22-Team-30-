package com.google.sps.servlets;


import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import com.google.gson.Gson;
import java.lang.String;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Handles requests sent to the /hello-pj URL. Try running a server and navigating to /hello-pj! */
@WebServlet("/smash")
public class JSONServlet extends HttpServlet {
    String[] smashChar = {"Byleth: Fire Emblem", "Pokemon Trainer: Pokemon", "Fox: StarFox", "Rob: NES", "Sephiroth: Final Fantasy", "Roy: Fire Emblem"};

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //Convert ArrayList as JSON String
        String json = new Gson().toJson(smashChar);

        // Send the JSON as the response
        response.setContentType("application/json;");
        response.getWriter().println(json);
  }

}
