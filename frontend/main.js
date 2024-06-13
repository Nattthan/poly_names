import { WordsService } from "./services/words-service.js";
import { CardsService } from "./services/cards-service.js";
import { ColorCardsService } from "./services/colorCards-service.js";
import { cardsView } from "./views/cards-view.js";

async function run() {
  console.log("all words");
  const wordsService = new WordsService();
  // Correct usage if keeping findAll as a static method
  WordsService.findAll().then((data) => {
    console.log(data);
  });

  console.log("all cards");
  const cardsService = new CardsService();
  // Correct usage if keeping findAll as a static method
  await cardsView.displayCards();

  console.log("all colored cards");
  const colorCardsService = new ColorCardsService();
  // Correct usage if keeping findAll as a static method
  ColorCardsService.findAll(9994).then((data) => {
    console.log(data);
  });
}

window.addEventListener("load", run);
