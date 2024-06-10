import controllers.GameController;
import controllers.WordsController;
import controllers.CardsController;
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
            // gameDAO.deleteGameByCode("5468");

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

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        
    }
}
