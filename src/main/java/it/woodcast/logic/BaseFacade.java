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
        String headerValue = "eyJhbGciOiJIUzUxMiJ9.eyJzdXJuYW1lIjoiRnJhc2NhIiwibmFtZSI6Ik1pY2hlbGEiLCJydWxlcyI6WyJQUkFDVElDRV9MRUFERVIiXSwidXNlcklEIjo0LCJ1c2VybmFtZSI6Im1pY2hlbGEuZnJhc2NhIiwiaWF0IjoxNzA3OTE3NjY0LCJleHAiOjE3MDgwMDQwNjR9.R6GXF5ZLWF9TZ-Wi59dyfl1tfHgeGE-2lDRbbmIvg3P-xOfeI0eqLT9ZdU0i64JlHT7px3wyAWJXLeyq02dfIw";
        /*request.getHeader("Authorization");*/
        return jwtTokenProvider.parseToken(headerValue);
    }

}
