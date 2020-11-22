package com.example.demo.jwt;

import com.example.demo.services.UserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthTokenFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String jwt = parseJwt(request);//obteniendose el jwt del request propiciado por el HttpServletRequest
            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
                //en este caso se establece condicional sobre el jwt traido en el request y su validez, en donde de
                //ser valido
                String username = jwtUtils.getUserNameFromJwtToken(jwt);//se obtendria el nombre del usuarioa
                                                                        //a traves de su token

                UserDetails userDetails = userDetailsService.loadUserByUsername(username);//se accederia  al metodo
                //del user userDetailsImpl para que con el nombre extraido del token se proceda a crear un
                //nuevo objeto con los datos del usuario loggeaod

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                //Estableciendose los detalles del usuario en cuestion

                SecurityContextHolder.getContext().setAuthentication(authentication);
                //Estableciendose los detalles del usuario en cuestion asigando a la variable authentication
                //En el security context holder para su uso en los diferentes en los diferentes controllers
                //o implementaciones
            }
        } catch (Exception e) {
            logger.error("Cannot set user authentication: {}", e);
        }//retorno de la excepcion en caso de que nno se cumpla lo anterior

        filterChain.doFilter(request, response);
    }

    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7, headerAuth.length());
        }

        return null;
    }


}
