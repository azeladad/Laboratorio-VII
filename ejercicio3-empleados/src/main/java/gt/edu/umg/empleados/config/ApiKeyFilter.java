package gt.edu.umg.empleados.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class ApiKeyFilter extends OncePerRequestFilter {

    private static final String HEADER_API_KEY = "X-API-KEY";

    @Value("${app.api-key}")
    private String apiKeyValida;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String apiKeyRecibida = request.getHeader(HEADER_API_KEY);

        if (apiKeyRecibida == null || !apiKeyRecibida.equals(apiKeyValida)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(
                    "{\"status\":401,\"error\":\"UNAUTHORIZED\",\"mensaje\":\"API Key inválida o ausente\"}");
            return;
        }

        filterChain.doFilter(request, response);
    }
}
