package ua.nure.andreiko.rentCar.web.filter;

import org.apache.log4j.Logger;
import ua.nure.andreiko.rentCar.util.Constant;
import ua.nure.andreiko.rentCar.util.LoggerUtils;
import ua.nure.andreiko.rentCar.util.Util;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/**
 * Locale filter.
 *
 * @author E.Andreiko
 */
public class LocaleFilter implements Filter {

    List<String> supportedLanguages;

    private String defaultLanguage;

    private ServletContext servletContext;

    private final Logger LOGGER = Logger.getLogger(Locale.class);

    public void destroy() {
        LOGGER.debug(LoggerUtils.FILTER_DESTROY_START);
        LOGGER.debug(LoggerUtils.FILTER_DESTROY_END);
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws ServletException, IOException {
        LOGGER.debug(LoggerUtils.FILTER_START);
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpSession httpSession = httpRequest.getSession();
        Util util = (Util) servletContext.getAttribute(Constant.UTIL);

        String localeStr = httpRequest.getParameter(Constant.LOCALE_PARAMETER);
        Locale locale = (Locale) httpSession.getAttribute(Constant.LOCALE);
        LOGGER.trace(LoggerUtils.FIlTER_SESSION_LOCALE + locale);
        if (Objects.isNull(locale)) {
            locale = util.toLocale(defaultLanguage);
            httpSession.setAttribute(Constant.LOCALE, locale);
            LOGGER.trace(LoggerUtils.FILTER_SET_DEFAULT_LOCALE + Constant.DEFAULT_LOCALE_TAG);
        }
        if (Objects.nonNull(localeStr)) {
            if (supportedLanguages.contains(localeStr)) {
                httpSession.setAttribute(Constant.LOCALE, util.toLocale(localeStr));
                LOGGER.trace(LoggerUtils.FILTER_SET_LOCALE + localeStr);
            } else {
                httpSession.setAttribute(Constant.LOCALE, util.toLocale(defaultLanguage));
                LOGGER.trace(LoggerUtils.FILTER_SET_DEFAULT_LOCALE + Constant.DEFAULT_LOCALE_TAG);
            }
        }
        servletResponse.setContentType(Constant.CONTENT_TYPE);
        servletResponse.setCharacterEncoding(Constant.UTF_8);
        LOGGER.debug(LoggerUtils.FILTER_END);
        chain.doFilter(servletRequest, servletResponse);
    }

    public void init(FilterConfig config) throws ServletException {
        LOGGER.debug(LoggerUtils.FILTER_INIT_START);
        supportedLanguages = asList(config.getInitParameter(Constant.SUPPORTED_LOCALES));
        defaultLanguage = config.getInitParameter(Constant.DEFAULT_LOCALE);
        servletContext = config.getServletContext();
        LOGGER.debug(LoggerUtils.FILTER_INIT_END);
    }

    private List<String> asList(String str) {
        List<String> list = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(str);
        while (st.hasMoreTokens()) {
            list.add(st.nextToken());
        }
        return list;
    }
}
