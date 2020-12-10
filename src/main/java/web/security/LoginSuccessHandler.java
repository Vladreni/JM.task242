package web.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

//    @Override
//    public void onAuthenticationSuccess(
//            HttpServletRequest httpServletRequest,
//            HttpServletResponse httpServletResponse,
//            Authentication authentication) throws IOException {
//
//        httpServletResponse.sendRedirect("hello/hello");
//    }

//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
//                                        HttpServletResponse httpServletResponse,
//                                        Authentication authentication)
//                                        throws IOException, ServletException {
//
//        HttpSession httpSession = httpServletRequest.getSession();
//        httpSession.setAttribute("user", authentication.getPrincipal());
//
//        if (authentication.getAuthorities().stream()
//                .anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"))) {
//            httpServletResponse.sendRedirect("/admin");
//        } else {
//            httpServletResponse.sendRedirect("/user");
//        }
//    }

    // Spring Security использует объект Authentication, пользователя авторизованной сессии.
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException {

        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

        if (roles.contains("ROLE_USER")) {
            httpServletResponse.sendRedirect("/");
        } else {
            httpServletResponse.sendRedirect("/admin");
        }
    }

}