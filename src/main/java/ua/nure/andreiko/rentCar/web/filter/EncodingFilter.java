package ua.nure.andreiko.rentCar.web.filter;

import org.apache.log4j.Logger;
import ua.nure.andreiko.rentCar.util.LoggerUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

/**
 * Encoding filter.
 *
 * @author E.Andreiko
 */
public class EncodingFilter implements Filter {

    private static final Logger LOGGER = Logger.getLogger(EncodingFilter.class);

    private String encoding;

    public void destroy() {
        LOGGER.debug(LoggerUtils.FILTER_DESTROY_START);
        LOGGER.debug(LoggerUtils.FILTER_DESTROY_END);
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        LOGGER.debug(LoggerUtils.FILTER_START);

        HttpServletRequest httpRequest = (HttpServletRequest)request;
        LOGGER.trace(LoggerUtils.FILTER_REQUEST_URI + httpRequest.getRequestURI());

        String requestEncoding = request.getCharacterEncoding();
        if (Objects.isNull(requestEncoding)) {
            LOGGER.trace(LoggerUtils.FILTER_SET_DEFAULT_ENCODING + encoding);
            request.setCharacterEncoding(encoding);
        }

        LOGGER.debug(LoggerUtils.FILTER_END);
        chain.doFilter(request, response);
    }

    public void init(FilterConfig fConfig) throws ServletException {
        LOGGER.debug(LoggerUtils.FILTER_INIT_START);
        encoding = fConfig.getInitParameter("encoding");
        LOGGER.trace(LoggerUtils.FILTER_ENCODING_FROM_XML + encoding);
        LOGGER.debug(LoggerUtils.FILTER_INIT_END);
    }

}
