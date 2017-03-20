package br.edu.ufcg.computacao.si1.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by nicacio on 14/03/17.
 */
public class JwtTokenFilter extends GenericFilterBean {

    /**
     *  Token Filter
     * @param request
     * @param response
     * @param chain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest srq = (HttpServletRequest) request;

        String header = srq.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            throw new ServletException("Inexistent Token.");
        }

        String token = header.substring(7); //cut Bearer

        // verify token
        try {
            Jwts.parser().setSigningKey("adExtremeKey").parseClaimsJws(token).getBody();
            chain.doFilter(request, response);
        } catch (Exception e) {
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }

    }
}
