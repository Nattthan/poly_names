import { WordsService } from "./services/words-service.js";
import { CardsService } from "./services/cards-service.js";
import { ColorCardsService } from "./services/colorCards-service.js";
import { CardsView } from "./views/cards-view.js";
import { GameService } from "./services/game-service.js";
import { ConnexionView } from "./views/connexion-view.js";
import { ColorCardsView } from "./views/colorCards-view.js";
import { ChoiceView } from "./views/choice-view.js";

async function run() {
  console.log("all words");

  WordsService.findAll().then((data) => {
    console.log(data);
  });
  let state = 2;
  if (state ===1){
    const connexionView = new ConnexionView();
    connexionView.displayConnexionPage();
  }
  if (state ===2){
    const choiceView = new ChoiceView();
    choiceView.displayChoicePage();
  }
  if (state === 3){
    console.log("all cards");
    const cardsService = new CardsService();
    const cardsView = new CardsView();
    // Correct usage if keeping findAll as a static method
    await cardsView.displayCards();
  }
  else if (state === 4){
    console.log("all colored cards");
    const cardsService = new ColorCardsService();
    const colorCardsView = new ColorCardsView();
    await colorCardsView.displayColorCards();
  }

  

}

window.addEventListener("load", run);
