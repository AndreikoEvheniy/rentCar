package ua.nure.andreiko.rentCar.util;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Supporting features
 *
 * @author E.Andreiko
 */
public class Util {
    private final Logger LOGGER = Logger.getLogger(Util.class);

    public boolean isNotReSubmitting(HttpServletRequest request) {
        String checkFromRequest = request.getParameter(Constant.RESUBMITTING_PARAMETER);
        String checkFromSession = (String) request.getSession().getAttribute(Constant.RESUBMITTING_PARAMETER);
        request.getSession().setAttribute(Constant.RESUBMITTING_PARAMETER, checkFromRequest);
        return !checkFromRequest.equals(checkFromSession);
    }

    public Timestamp convertStringToTimestamp(String strDate) {
        if (strDate != null) {
            try {
                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date date = formatter.parse(strDate);
                return new Timestamp(date.getTime());
            } catch (ParseException e) {
                LOGGER.error(LoggerUtils.ERR_CANNOT_CONVERT_STRING_TO_TIMESTAMP);
                return null;
            }
        }
        return null;
    }

    public Locale toLocale(String localeStr) {
        return new Locale(localeStr);
    }

    public String getTranslate(Locale locale, String prefix, String key) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(Constant.RESOURCE_BUNDLE, locale);
        return resourceBundle.getString(prefix + "." + key);
    }


}
