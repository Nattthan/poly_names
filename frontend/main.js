import { WordsService } from "./services/words-service.js";
import { CardsService } from "./services/cards-service.js";
import { ColorCardsService } from "./services/colorCards-service.js";
import { CardsView } from "./views/cards-view.js";
import { GameService } from "./services/game-service.js";
import { ConnexionView } from "./views/connexion-view.js";
import { ColorCardsView } from "./views/colorCards-view.js";
import { ChoiceView } from "./views/choice-view.js";
import { HomeView } from "./views/home-view.js";

async function run() {
  console.log("all words");
  WordsService.findAll().then((data) => {
    console.log(data);
  });

  let state = localStorage.getItem("state");

  if (state === "1"){
    const connexionView = new ConnexionView();
    connexionView.displayConnexionPage();

  }

  else if (state === "2"){
    const choiceView = new ChoiceView();
    await choiceView.displayChoicePage();
  }

  else if (state === "3"){
    console.log("all cards");
    const cardsView = new CardsView();
    await cardsView.displayCards();
    
    
  }

  else if (state === "4"){
    console.log("all colored cards");
    const colorCardsView = new ColorCardsView();
    await colorCardsView.displayColorCards();
    colorCardsView.displayGuess();
  }
}


window.addEventListener("load",run);
