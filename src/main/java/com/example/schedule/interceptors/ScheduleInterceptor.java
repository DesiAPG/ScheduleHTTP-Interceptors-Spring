package com.example.schedule.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Calendar;

@Component("schedule")
public class ScheduleInterceptor implements HandlerInterceptor {

    @Value("config.schedule.opened")
    private Integer open;
    @Value("config.schedule.closed")
    private Integer close;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Calendar calendar = Calendar.getInstance();
        int time = calendar.get(Calendar.HOUR_OF_DAY);
        if (time >= open && time <= close) {
            StringBuilder message = new StringBuilder("Welcome to the schedule of customer attention");
            message.append(", we attend from ");
            message.append(open);
            message.append("hrs");
            message.append("until");
            message.append(close);
            message.append("hrs");
            message.append(", thanks for your visit");
            request.setAttribute("message", message.toString());
            return true;
        }
        response.sendRedirect(request.getContextPath().concat("/closed"));
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String message = (String) request.getAttribute("message");
        if (modelAndView != null && handler instanceof HandlerMethod) {
            modelAndView.addObject("schedule", message);
        }
    }
}
