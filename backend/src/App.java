import controllers.WordsController;
// import dao.GameDAO;
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

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        
    }
}
