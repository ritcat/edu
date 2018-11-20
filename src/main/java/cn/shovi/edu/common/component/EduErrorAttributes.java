package cn.shovi.edu.common.component;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@Component
public class EduErrorAttributes extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
//        return super.getErrorAttributes(webRequest, includeStackTrace);s
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest,includeStackTrace);
        errorAttributes.put("project","education");
        Map<String,Object> map=(Map<String,Object>)webRequest.getAttribute("ext", 0);
        errorAttributes.put("ext",map);
        return errorAttributes;
    }
}
