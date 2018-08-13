package com.example.police;

import com.example.police.api.NumberGeneratorInt;
import com.example.police.impl.QWGenerator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by estarcev on 06.08.2018.
 */

@RestController
public class NextServlet {

    NumberGeneratorInt generator = new QWGenerator();

    @RequestMapping("/")
    public String index() {
        return "Please use /next or /random";
    }

    @RequestMapping("/next")
    public String next() {
        return generator.getNumber(false);
    }

    @RequestMapping("/random")
    public String random() {
        return generator.getNumber(true);
    }
}
