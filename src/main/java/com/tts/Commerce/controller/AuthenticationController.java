package com.tts.Commerce.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tts.Commerce.Services.UserService;
import com.tts.Commerce.model.User;

@Controller
public class AuthenticationController
{
    @Autowired
    private UserService userService;
    
    @GetMapping("/signin")
    public String login()
    {
        return "signin";
    }
    
    @PostMapping("/signin")
    public  String signup(@Valid User user,
            @RequestParam String submit,
            BindingResult bindingResult,
            HttpServletRequest request) throws ServletException
            {
                String password = user.getPassword();
                if(submit.equals("up"))
                {
//                    if(userService.testPassword(password) == false)
//                    {
//                        bindingResult.rejectValue("password", "error.user", "Password must contain a capital letter and one lowercase letter a number and cannot contain symbols");
//                        return "signin";
//                    }
                    if(userService.findByUsername(user.getUsername()) == null)
                    {
                        userService.saveNew(user);                   
                    }
                    else
                    {
                        bindingResult.rejectValue("username", "error.user", "Username is already taken.");
                        return "signin";
                    }
                }               
                request.login(user.getUsername(), password);
                return "redirect:/";
            }     

    
}
