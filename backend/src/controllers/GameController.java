package controllers;

import java.util.ArrayList;
import dao.CardsDAO;
import dao.GameDAO;
import models.Word;
import webserver.WebServerContext;

public class GameController {
    
    public static boolean gameExists(WebServerContext webServerContext){
        try {
            String gameCode = webServerContext.getRequest().getParam("gameCode");
            if(gameCode == null) {
                webServerContext.getResponse().notFound("Missing game code.");
                return false;
            }

            GameDAO gameDAO = new GameDAO();
            if(gameDAO.gameExists(gameCode) == false){
                webServerContext.getResponse().notFound("Game not found.");
                return false;
            }
            return true;
        } catch (Exception e) {
            webServerContext.getResponse().serverError("Failed to find game.");
            return false;
        }
    }

    public static void createGame(WebServerContext webServerContext){
        try {
            String gameCode = webServerContext.getRequest().getParam("gameCode");
            if(gameCode == null) {
                webServerContext.getResponse().notFound("Missing game code.");
                return;
            }

            GameDAO gameDAO = new GameDAO();
            gameDAO.createGame(gameCode);
            webServerContext.getResponse().json(gameDAO.findGameByCode(gameCode));
        } catch (Exception e) {
            webServerContext.getResponse().serverError("Failed to create game.");
        }
    }

    public static int findGameByCode(WebServerContext webServerContext){
        try {
            String gameCode = webServerContext.getRequest().getParam("gameCode");
            if(gameCode == null) {
                webServerContext.getResponse().notFound("Missing game code.");
                return -1;
            }

            GameDAO gameDAO = new GameDAO();
            int gameID = gameDAO.findGameByCode(gameCode);
            webServerContext.getResponse().json(gameID);
            return gameID;
        } catch (Exception e) {
            webServerContext.getResponse().serverError("Failed to find game.");
            return 0;
        }
    }

    public static void deleteGame(WebServerContext webServerContext){
        try {
            String gameCode = webServerContext.getRequest().getParam("gameCode");
            if(gameCode == null) {
                webServerContext.getResponse().notFound("Missing game code.");
                return;
            }

            GameDAO gameDAO = new GameDAO();
            gameDAO.deleteGameByCode(gameCode);
            webServerContext.getResponse().ok("Game deleted.");
        } catch (Exception e) {
            webServerContext.getResponse().serverError("Failed to delete game.");
        }
    }

}
