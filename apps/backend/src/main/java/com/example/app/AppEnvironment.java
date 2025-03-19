package com.example.app;

import org.springframework.stereotype.Component;
import io.github.cdimascio.dotenv.Dotenv;

@Component
public class AppEnvironment {

    private static final Dotenv dotenv = Dotenv.load();
    public static final String SECRET_KEY = dotenv.get("SECRET_KEY");
    public static final String FRONTEND_URL = dotenv.get("FRONTEND_URL");

}