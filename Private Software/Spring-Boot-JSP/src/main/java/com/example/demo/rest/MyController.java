package com.example.demo.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

@Controller
public class MyController {
    @Value("${mymessage}")
    private String message;

    @GetMapping("/getMessage")
    public String getMessage(Model model) {

        model.addAttribute("message", message);

        return "show";
    }

    @GetMapping("/getMessage2")
    public ModelAndView getMessage() {

        var mav = new ModelAndView();

        mav.addObject("message", message);
        mav.setViewName("show");

        return mav;
    }

    @GetMapping("/getMessageAndTime")
    public String getMessageAndTime(ModelMap map) {

        var ldt = LocalDateTime.now();

        var fmt = DateTimeFormatter.ofLocalizedDateTime(
                FormatStyle.MEDIUM);

        fmt.withLocale(new Locale("sk", "SK"));
        fmt.withZone(ZoneId.of("CET"));
        String time = fmt.format(ldt);

        map.addAttribute("message", message).addAttribute("time", time);

        return "show";
    }
}
