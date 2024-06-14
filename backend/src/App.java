import controllers.GameController;
import controllers.WordsController;
import controllers.CardsController;
import controllers.PlayerController;
import dao.GameDAO;
// import dao.WordsDAO;
import webserver.WebServer;
import webserver.WebServerContext;

public class App {
    public static void main(String[] args) throws Exception {
        try {
            // WordsDAO wordsDAO = new WordsDAO();
            WebServer webserver = new WebServer();
            webserver.listen(8080);

            // GameDAO gameDAO = new GameDAO();
            // gameDAO.createGame("5468");
            // int id = gameDAO.findGameByCode("5468");
            // wordsDAO.selectBlueCards(id);
            // wordsDAO.selectGreyCards(id);
            // wordsDAO.selectBlackCards(id);

            // wordsDAO.deleteCards(gameDAO.findGameByCode("5468"));
            // gameDAO.deleteGameByCode("1234");

            
            webserver.getRouter().get("/words", (WebServerContext context) -> {
                WordsController.findAll(context);
            });

            // create a route to the game with the code as a parameter
            webserver.getRouter().get("/game/:gameCode", (WebServerContext context) -> {
                if (GameController.gameExists(context)) {
                    GameController.findGameByCode(context);
                }   
                else {
                    GameController.createGame(context);
                    CardsController.createCards(context);
                }

            });

            // create a route to return the cards of the game with the code as a parameter
            webserver.getRouter().get("/game/:gameCode/cards", (WebServerContext context) -> {
                if (GameController.gameExists(context)) {
                    CardsController.drawAllCards(context);
                }   
                else {
                    GameController.createGame(context);
                    CardsController.createCards(context);
                }
            });

            // create a route to return the cards of the game with the code as a parameter
            webserver.getRouter().get("/game/:gameCode/colorCards", (WebServerContext context) -> {
                if (GameController.gameExists(context)) {
                    CardsController.drawAllColorCards(context);
                }   
                else {
                    GameController.createGame(context);
                    CardsController.createCards(context);
                }
            });

            // create a route to delete the game with the code as a parameter
            webserver.getRouter().delete("/game/delete/:gameCode", (WebServerContext context) -> {
                GameController.deleteGame(context);
            });

            // create a route to send the player UUID, role and game ID then create the player
            webserver.getRouter().post("/game/:gameCode/:team/player/:Player_UUID", (WebServerContext context) -> {
                if(PlayerController.playerExists(context)){
                    PlayerController.isSamePlayerUUID(context);
                }
                else if (PlayerController.playerExists(context) == false){
                    PlayerController.createPlayer(context);
                }
            });

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        
    }
}
