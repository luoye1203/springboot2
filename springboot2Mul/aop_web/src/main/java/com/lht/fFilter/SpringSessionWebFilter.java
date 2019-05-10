package com.lht.fFilter;

import com.alibaba.fastjson.JSON;
import com.lht.dModel.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(filterName = "srpingsessionFilter",
//        urlPatterns = {"/filter/*", "/kafka/*"})
        urlPatterns = {"/*"})
public class SpringSessionWebFilter implements Filter {

    @Autowired
    HttpSession httpSession;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Object o = httpSession.getAttribute("springboot");
        if(o == null){
            o = "spring boot 牛逼了!!!有端口"+request.getLocalPort()+"生成";
            httpSession.setAttribute("springboot", o);
        }
        logger.info("sessionid:"+httpSession.getId());
        HttpServletRequest req = (HttpServletRequest) request;
        logger.info(req.getRequestURL().toString());
        chain.doFilter(request,response);

    }

    @Override
    public void destroy() {

    }

    //过滤后，返回非法状态
    private void sendResponse(HttpServletResponse response, BaseResponse data, int status) {

        String content = JSON.toJSONString(data);
        response.setCharacterEncoding("UTF-8");
        response.setStatus(status);
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.append(content);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}
