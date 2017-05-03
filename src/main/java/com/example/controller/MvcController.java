package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by AjmalCholassery on 5/2/17.
 */

@Controller
public class MvcController {

    @RequestMapping("/partials/{page}")
    String partialHandler(@PathVariable("page") final String page) {
        return page;
    }

    @RequestMapping("/home")
    String homeHandler(ModelMap modelMap) {
        modelMap.addAttribute("title", "My App");
        return "index";
    }
}
