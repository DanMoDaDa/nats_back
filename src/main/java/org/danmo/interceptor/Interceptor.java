//package org.danmo.interceptor;
//
//import org.danmo.cache.LocalCache;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * 拦截器
// */
//public class Interceptor implements HandlerInterceptor {
//
//    /**
//     * 在请求处理之前进行调用（Controller方法调用之前）
//     */
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
//        //判断是否登录
//        boolean verifyPermissions = verifyPermissions(request);
//        if (verifyPermissions) {
//            return true;
//        }
//        //这里设置拦截以后重定向的页面，一般设置为登陆页面地址
//        try {
//            response.sendRedirect(request.getContextPath() + "/error");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        return true;
//    }
//
//    /**
//     * 验证是否登录
//     *
//     * @param request
//     * @return
//     */
//    public boolean verifyPermissions(HttpServletRequest request) {
//        String token = request.getHeader("token");
//        Object o = LocalCache.get(token);
//        if (o != null) {
//            return true;
//        }
//        return false;
//    }
//}
