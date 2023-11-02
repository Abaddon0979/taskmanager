package taskmanager.task.api.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletResponse response){
        int statusCode = response.getStatus();

        if (statusCode == HttpStatus.NOT_FOUND.value()){
            return "Page not found!";
        }

        if (statusCode == HttpStatus.UNAUTHORIZED.value()){
            return "Authentication failed! Please check your username and password!";
        }

        if (statusCode == HttpStatus.FORBIDDEN.value()){
            return "You are not allowed to access this resource!";
        }

        if (statusCode == HttpStatus.BAD_REQUEST.value()){
            return "Your request is invalid!";
        }

        if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()){
            return "An unexpected error has occurred!";
        }
        return null;
    }
}