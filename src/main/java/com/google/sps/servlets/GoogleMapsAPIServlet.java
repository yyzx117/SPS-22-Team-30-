package com.google.sps.servlets;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Handles requests sent to the /hello-pj URL. Try running a server and navigating to /hello-pj! */
@WebServlet("/google-maps-api")
public class GoogleMapsAPIServlet extends HttpServlet {

  private int pageViews = 0;

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    pageViews++;
    response.setContentType("text/html;");
    response.getWriter().println("<h1>Hello PJ!</h1>");
    response.getWriter().println("<h1>This is where the server code should be</h1>");
    response.getWriter().println("<p>This page has been viewed " + pageViews + " times.</p>");
  }

}
