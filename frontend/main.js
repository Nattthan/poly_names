import { WordsService } from "./services/words-service.js";
import { CardsService } from "./services/cards-service.js";
import { ColorCardsService } from "./services/colorCards-service.js";
import { GameService } from "./services/game-service.js";

function run() {
  console.log("all words");

  WordsService.findAll().then((data) => {
    console.log(data);
  });

  console.log("all cards");
  CardsService.findAll();
  console.log("Displaying cards");

  console.log("all colored cards");
  ColorCardsService.findAll(5958).then((data) => {
    console.log(data);
  });

}

window.addEventListener("load", run);
