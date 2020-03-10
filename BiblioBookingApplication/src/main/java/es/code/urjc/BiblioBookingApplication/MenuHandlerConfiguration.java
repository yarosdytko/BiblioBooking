package es.code.urjc.BiblioBookingApplication;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class MenuHandlerConfiguration implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MenuHandlerInterceptor());
    }

}

class MenuHandlerInterceptor extends HandlerInterceptorAdapter {

    @Override
    public void postHandle(final HttpServletRequest request,
                           final HttpServletResponse response, final Object handler,
                           final ModelAndView modelAndView) throws Exception {
    	if(modelAndView != null) {
    		if(request.isUserInRole("ROLE_ADMIN")){
                modelAndView.addObject("alumno",false);
                modelAndView.addObject("usuario",true);
                modelAndView.addObject("invitado",false);
                modelAndView.addObject("admin",true);
                modelAndView.addObject("gestion",false);
                modelAndView.addObject("noInvitado",true);
            } else if(request.isUserInRole("ROLE_ALUMNO")){
                modelAndView.addObject("alumno",true);
                modelAndView.addObject("usuario",true);
                modelAndView.addObject("invitado",false);
                modelAndView.addObject("admin",false);
                modelAndView.addObject("gestion",false);
                modelAndView.addObject("noInvitado",true);
            } else if (request.isUserInRole("ROLE_EMPLEADO")){
                modelAndView.addObject("alumno",false);
                modelAndView.addObject("usuario",true);
                modelAndView.addObject("invitado",false);
                modelAndView.addObject("admin",false);
                modelAndView.addObject("gestion",true);
                modelAndView.addObject("noInvitado",true);
            } else {
                modelAndView.addObject("alumno",false);
                modelAndView.addObject("usuario",false);
                modelAndView.addObject("invitado",true);
                modelAndView.addObject("admin",false);
                modelAndView.addObject("gestion",false);
                modelAndView.addObject("noInvitado",false);
            }
    	}  
    }
    
}