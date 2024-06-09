package controllers;

import java.util.ArrayList;
import dao.WordsDAO;
import models.Word;
import webserver.WebServerContext;

public class WordsController {
    
    public static ArrayList<Word> findAll(WebServerContext webServerContext){
        try {
            WordsDAO wordsDAO = new WordsDAO();
            webServerContext.getResponse().ok("Tous les mots");
            webServerContext.getResponse().json(wordsDAO.findAll());
            return null;
        } catch (Exception e) {
            webServerContext.getResponse().serverError("Failed to find all words.");
        }
        return null;
    }

}
