package marc.dev.Ecommerce.Spring.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import marc.dev.Ecommerce.Spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;


public class VerifyToken extends GenericFilterBean {
    @Autowired
    UserService userService;
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        String authHeader = httpRequest.getHeader("Authorization");
        if(authHeader != null){
            String[] authHeaderArr = authHeader.split("Bearer");
            if(authHeaderArr.length > 1 && authHeaderArr[1] != null){
                String token = authHeaderArr[1];

                try{
                    Claims claims = Jwts.parser().setSigningKey(constants.API_SECRET_KEY)
                            .parseClaimsJws(token).getBody();
//                 System.out.println("Token: " + token);
//               System.out.println("Claims: " + claims);
                    String userId = claims.get("userId", String.class);
                    httpRequest.setAttribute("userId", userId);
//                    System.out.println("userId: " + userId);





                }catch(Exception e){
                    httpResponse.sendError(HttpStatus.FORBIDDEN.value(), "invalid/expired token");
                    return;

                }
            }else{
                httpResponse.sendError(HttpStatus.FORBIDDEN.value(), "Authorization token must be Bearer[token]");
                return;
            }
        }else{
            httpResponse.sendError(HttpStatus.FORBIDDEN.value(), "Authorization token must be provided");
            return;
        }
        filterChain.doFilter(servletRequest,servletResponse);


    }
}
