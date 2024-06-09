import { WordsService } from "./services/words-service.js";

function run() {
  console.log("all words");
  const wordsService = new WordsService();
  // Correct usage if keeping findAll as a static method
  WordsService.findAll().then((data) => {
    console.log(data);
  });
}

window.addEventListener("load", run);
