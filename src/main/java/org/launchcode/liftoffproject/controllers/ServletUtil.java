package org.launchcode.liftoffproject.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ServletUtil {

    // Method to get the HttpSession from the HttpServletRequest
    public static HttpSession getSession(HttpServletRequest request) {
        return request.getSession();
    }

}
