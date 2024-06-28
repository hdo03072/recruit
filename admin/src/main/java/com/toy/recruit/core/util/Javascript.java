package com.toy.recruit.core.util;

import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.io.PrintWriter;

@NoArgsConstructor
public class Javascript {

    private static class LazyHolder {
        private static final Javascript INSTANCE = new Javascript();
    }

    public static Javascript getInstance() {
        return LazyHolder.INSTANCE;
    }

    public void exceptionMsg(HttpServletResponse response, String msg) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.println("<script type='text/javascript'>");
        writer.println("alert('"+msg+"');");
        writer.println("history.back();");
        writer.println("</script>");
        writer.flush();
    }
}
