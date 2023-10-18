package com.example.springdemo.controller;
import com.example.springdemo.model.personModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.ArrayList;
import java.util.List;

@Controller
public class personController {
    // return a string from a test
    @GetMapping("/test1")
    @ResponseBody
    public String printHello() {
        // return this text
        return "Hello there!";
    }

    @GetMapping("/test2")
    public String showHelloPage(Model model) {
        model.addAttribute("message", "Have a great day");
        model.addAttribute("rating", 99);
        model.addAttribute("report", "Another great report");
        // return the helloPage.html in templates
        return "helloPage";
    }

}
