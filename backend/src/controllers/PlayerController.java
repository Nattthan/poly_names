package controllers;
import dao.PlayerDAO;
import dao.GameDAO;
import webserver.WebServerContext;

public class PlayerController {
    public static void createPlayer(WebServerContext webServerContext){
        System.out.println("Creating player...");
        try {
            String Player_UUID = webServerContext.getRequest().getParam("Player_UUID");
            String role = webServerContext.getRequest().getParam("team");
            String gameCode = webServerContext.getRequest().getParam("gameCode");
            GameDAO gameDAO = new GameDAO();
            int gameID = gameDAO.findGameByCode(gameCode);
            if(Player_UUID == null || role == null || gameID == 0) {
                webServerContext.getResponse().notFound("Missing player UUID, role or game ID.");
            }

            PlayerDAO playerDAO = new PlayerDAO();
            playerDAO.createPlayer(Player_UUID, role, gameID);
            webServerContext.getResponse().json(playerDAO.findPlayerByName(Player_UUID, gameID));
        } catch (Exception e) {
            webServerContext.getResponse().serverError("Failed to create player.");
        }
    }

    public static int findPlayerByName(WebServerContext webServerContext){
        try {
            String Player_UUID = webServerContext.getRequest().getParam("Player_UUID");
            if(Player_UUID == null) {
                webServerContext.getResponse().notFound("Missing player UUID.");
                return -1;
            }

            PlayerDAO playerDAO = new PlayerDAO();
            String gameCode = webServerContext.getRequest().getParam("gameCode");
            GameDAO gameDAO = new GameDAO();
            int gameID = gameDAO.findGameByCode(gameCode);
            int playerID = playerDAO.findPlayerByName(Player_UUID, gameID);
            webServerContext.getResponse().json(playerID);
            return playerID;
        } catch (Exception e) {
            webServerContext.getResponse().serverError("Failed to find player.");
            return -1;
        }
    }

    public static boolean playerExists(WebServerContext webServerContext){
        try {
            String role = webServerContext.getRequest().getParam("team");
            String gameCode = webServerContext.getRequest().getParam("gameCode");
            GameDAO gameDAO = new GameDAO();
            int gameID = gameDAO.findGameByCode(gameCode);
            if(role == null) {
                webServerContext.getResponse().notFound("Missing player UUID.");
                return false;
            }
    
            PlayerDAO playerDAO = new PlayerDAO();
            if(playerDAO.playerExists(role, gameID) == false){
                webServerContext.getResponse().notFound("Player not found.");
                return false;
            }
            return true;
        } catch (Exception e) {
            webServerContext.getResponse().serverError("Failed to find game.");
            return false;
        }
    }

    public static boolean isSamePlayerUUID(WebServerContext webServerContext){
        try {
            String Player_UUID = webServerContext.getRequest().getParam("Player_UUID");
            String role = webServerContext.getRequest().getParam("team");
            String gameCode = webServerContext.getRequest().getParam("gameCode");
            GameDAO gameDAO = new GameDAO();
            int gameID = gameDAO.findGameByCode(gameCode);
            if(Player_UUID == null) {
                webServerContext.getResponse().notFound("Missing player UUID.");
                return false;
            }
    
            PlayerDAO playerDAO = new PlayerDAO();
            if(playerDAO.playerHasSameUUID(Player_UUID, gameID, role)){
                webServerContext.getResponse().json(playerDAO.findPlayerByName(Player_UUID, gameID));
                return true;
            }
            else{
                webServerContext.getResponse().notFound("Team is full.");
                return false;
            }
        } catch (Exception e) {
            webServerContext.getResponse().serverError("Failed to find game.");
            return false;
        }
    }
}
