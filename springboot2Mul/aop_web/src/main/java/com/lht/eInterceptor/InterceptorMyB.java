package com.lht.eInterceptor;

import com.alibaba.fastjson.JSON;
import com.lht.dModel.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class InterceptorMyB implements HandlerInterceptor {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info(this.getClass().getName() + " 拦截器开始.....");
        String token = request.getParameter("token");
        if (!StringUtils.isEmpty(token)) {
            return true;
        } else {
            BaseResponse baseresponse = BaseResponse.buildResponse().setCode(HttpStatus.UNAUTHORIZED.value()).setMessage("登录认证失败").build();
            this.sendResponse(response,baseresponse, HttpStatus.UNAUTHORIZED.value());
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

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
