package com.example.demo.rest;

import com.example.demo.dto.EmployeeImuTable;
import com.example.demo.service.RoleServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
//@CrossOrigin
@Slf4j
public class TestController {

    @Autowired
    private RoleServiceImpl roleService;

    @Autowired
    private EmployeeImuTable employeeImuTable;

    //PreAuthorize verify permission before the method is executed
    //PostAuthorize verify permission after the method is executed
    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping("/ping-service")
    public ResponseEntity<String> test(HttpServletRequest request, HttpServletResponse response) {
        CsrfToken csrf = (CsrfToken) request.getAttribute("_csrf");
        log.info("url=/ping-service {} == {} == {}", csrf.getHeaderName(), csrf.getParameterName(), csrf.getToken());
        return new ResponseEntity<>("Ping Ok", HttpStatus.ACCEPTED);
    }

    @RequestMapping("/logout-api")
    public ResponseEntity logout(HttpServletRequest request, HttpServletResponse response) {
//        Cookie[] cookie = request.getCookies();
//        if (Objects.nonNull(cookie)) {
//            Arrays.stream(cookie).forEach(item -> {
//                if(item.getName().equals("JSESSIONID")){
//                    item.setMaxAge(0);
//                    response.addCookie(item);
//                }
//            });
//        }
//        response.sendRedirect("/login");
        boolean isSecure = false;
        String contextPath = null;
        if (request != null) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            isSecure = request.isSecure();
            contextPath = request.getContextPath();
        }
        SecurityContext context = SecurityContextHolder.getContext();
        SecurityContextHolder.clearContext();
        context.setAuthentication(null);
        if (response != null) {
            Cookie cookie = new Cookie("JSESSIONID", null);
            String cookiePath = StringUtils.hasText(contextPath) ? contextPath : "/";
            cookie.setPath(cookiePath);
            cookie.setMaxAge(0);
            cookie.setSecure(isSecure);
            response.addCookie(cookie);
        }
        return new ResponseEntity<>("Logout Ok", HttpStatus.RESET_CONTENT);
    }

    @RequestMapping("/public/welcome")
    public ResponseEntity<String> welcome(HttpServletRequest request, HttpServletResponse response) {
        return new ResponseEntity<>("Welcome My Website =="+ employeeImuTable.getPancardNumber(), HttpStatus.OK);
    }
}
