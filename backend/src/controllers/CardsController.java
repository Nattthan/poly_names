package controllers;

import java.util.ArrayList;
import dao.CardsDAO;
import dao.GameDAO;
import models.Word;
import webserver.WebServerContext;

public class CardsController {
    
    public static ArrayList<Word> findBlueCards(WebServerContext webServerContext){
        try {
            String gameCode = webServerContext.getRequest().getParam("gameCode");
            if(gameCode == null) {
                webServerContext.getResponse().notFound("Missing game code.");
                return null;
            }

            GameDAO gameDAO = new GameDAO();
            int game_ID = gameDAO.findGameByCode(gameCode);

            CardsDAO cardsDAO = new CardsDAO();
            webServerContext.getResponse().json(cardsDAO.drawBlueCards(game_ID));
            return null;
        } catch (Exception e) {
            webServerContext.getResponse().serverError("Failed to find blue cards.");
        }
        return null;
    }

    public static ArrayList<Word> findGreyCards(WebServerContext webServerContext){
        try {
            String gameCode = webServerContext.getRequest().getParam("gameCode");
            if(gameCode == null) {
                webServerContext.getResponse().notFound("Missing game code.");
                return null;
            }

            GameDAO gameDAO = new GameDAO();
            int game_ID = gameDAO.findGameByCode(gameCode);

            CardsDAO cardsDAO = new CardsDAO();
            webServerContext.getResponse().json(cardsDAO.drawGreyCards(game_ID));
            return null;
        } catch (Exception e) {
            webServerContext.getResponse().serverError("Failed to find grey cards.");
        }
        return null;
    }

    public static ArrayList<Word> findBlackCards(WebServerContext webServerContext){
        try {
            String gameCode = webServerContext.getRequest().getParam("gameCode");
            if(gameCode == null) {
                webServerContext.getResponse().notFound("Missing game code.");
                return null;
            }

            GameDAO gameDAO = new GameDAO();
            int game_ID = gameDAO.findGameByCode(gameCode);

            CardsDAO cardsDAO = new CardsDAO();
            webServerContext.getResponse().json(cardsDAO.drawBlackCards(game_ID));
            return null;
        } catch (Exception e) {
            webServerContext.getResponse().serverError("Failed to find black cards.");
        }
        return null;
    }

    public static ArrayList<Word> drawAllCards(WebServerContext webServerContext){
        try {
            String gameCode = webServerContext.getRequest().getParam("gameCode");
            if(gameCode == null) {
                webServerContext.getResponse().notFound("Missing game code.");
                return null;
            }

            GameDAO gameDAO = new GameDAO();
            int game_ID = gameDAO.findGameByCode(gameCode);

            CardsDAO cardsDAO = new CardsDAO();
            webServerContext.getResponse().json(cardsDAO.drawAllCards(game_ID));
            return null;
        } catch (Exception e) {
            webServerContext.getResponse().serverError("Failed to find all cards.");
        }
        return null;
    }

    public static ArrayList<Word> drawAllColorCards(WebServerContext webServerContext){
        try {
            String gameCode = webServerContext.getRequest().getParam("gameCode");
            if(gameCode == null) {
                webServerContext.getResponse().notFound("Missing game code.");
                return null;
            }

            GameDAO gameDAO = new GameDAO();
            int game_ID = gameDAO.findGameByCode(gameCode);

            CardsDAO cardsDAO = new CardsDAO();
            webServerContext.getResponse().json(cardsDAO.drawAllColorCards(game_ID));
            return null;
        } catch (Exception e) {
            webServerContext.getResponse().serverError("Failed to find all cards.");
        }
        return null;
    }

    public static void createCards(WebServerContext webServerContext){
        try {
            String gameCode = webServerContext.getRequest().getParam("gameCode");
            if(gameCode == null) {
                webServerContext.getResponse().notFound("Missing game code.");
                return;
            }

            GameDAO gameDAO = new GameDAO();
            int game_ID = gameDAO.findGameByCode(gameCode);

            CardsDAO cardsDAO = new CardsDAO();
            cardsDAO.createBlueCards(game_ID);
            cardsDAO.createGreyCards(game_ID);
            cardsDAO.createBlackCards(game_ID);

            webServerContext.getResponse().ok("Cards created.");
        } catch (Exception e) {
            webServerContext.getResponse().serverError("Failed to create cards.");
        }
    }
}
