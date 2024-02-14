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
        String headerValue = request.getHeader("Authorization");
        System.out.println("Header value: " + headerValue);
        return jwtTokenProvider.parseToken(headerValue);
    }

}
