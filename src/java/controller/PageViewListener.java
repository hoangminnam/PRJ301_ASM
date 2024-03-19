/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package controller;

/**
 *
 * @author hoang
 */
import dal.PageViewDBContext;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class PageViewListener implements ServletContextListener, ServletRequestListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        PageViewDBContext pDB = new PageViewDBContext();
        int views = pDB.getViews();
        context.setAttribute("viewCount", views);
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        ServletContext context = sre.getServletContext();
        Integer viewCount = (Integer) context.getAttribute("viewCount");
        if (viewCount != null) {
            viewCount++;
            PageViewDBContext pDB = new PageViewDBContext();
            pDB.updateViews(viewCount);
            context.setAttribute("viewCount", viewCount);
        }
    }
}
