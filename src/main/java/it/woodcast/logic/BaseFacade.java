package it.woodcast.logic;

import it.woodcast.resources.JwtUser;
import it.woodcast.services.JwtTokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class BaseFacade {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    ModelMapper modelMapper = new ModelMapper();
    public JwtUser getJwtUser() {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String headerValue = "eyJhbGciOiJIUzUxMiJ9.eyJzdXJuYW1lIjoiRnJhc2NhIiwibmFtZSI6Ik1pY2hlbGEiLCJydWxlcyI6WyJQUkFDVElDRV9MRUFERVIiXSwidXNlcklEIjo2LCJ1c2VybmFtZSI6Im1pY2hlbGEuZnJhc2NhIiwiaWF0IjoxNzA4MDEyNDIxLCJleHAiOjE3MDgwOTg4MjF9.anaz6Ug_7virIIVtJcl2uAUL-H9uQkfj4bTFSZSj0oFVoamrN0P9nhEWpUyqYf77-E08wlQujcJSJX7LbPm_LQ";
        //request.getHeader("Authorization");
        return jwtTokenProvider.parseToken(headerValue);
    }

}
