package com.ktpt.surmoon.service.survey.oauth.token;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

@Component
public class TokenExtractor {

    // String token = tokenExtractor.extract(request, HttpHeaders.AUTHORIZATION, "bearer");
    // request는 HttpServletRequest 타입
    public String extract(HttpServletRequest request, String key, String type) {
        Enumeration<String> headers = request.getHeaders(key);
        while (headers.hasMoreElements()) {
            String element = headers.nextElement();
            if (element.toLowerCase().startsWith(type.toLowerCase())) {
                String headerElement = element.substring(type.length()).trim();
                request.setAttribute(TokenExtractor.class.getSimpleName() + ".ACCESS_TOKEN_TYPE",
                    element.substring(0, type.length()).trim());
                int commaIndex = headerElement.indexOf(",");
                if (commaIndex > 0) {
                    headerElement = headerElement.substring(0, commaIndex);
                }
                return headerElement;
            }
        }
        return "";
    }
}
