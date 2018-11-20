package cn.shovi.edu.config;


import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.Map;

public class EduLocaleResolver implements LocaleResolver{

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
            String l = request.getParameter("l");
            Locale locale = Locale.getDefault();
            if(!StringUtils.isEmpty(l)){
                String[] split = l.split("_");
                locale = new Locale(split[0],split[1]);
            }
            return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, @Nullable HttpServletResponse httpServletResponse, @Nullable Locale locale) {

    }
}
