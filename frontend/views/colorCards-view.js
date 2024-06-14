import { ColorCardsService } from "../services/colorCards-service.js";
import { GameService } from "../services/game-service.js";
import { HomeView } from "./home-view.js";

export class ColorCardsView {
    constructor() {
        this.parent = document.querySelector(".cards");
    }
    
    async displayColorCards() {
        const cards = await ColorCardsService.findAll(localStorage.getItem('gameId'));
        const bluecards = await GameService.getBlueCards(localStorage.getItem("gameId")).then();
        const greycards = await GameService.getGreyCards(localStorage.getItem("gameId")).then();
        const blackcards = await GameService.getBlackCards(localStorage.getItem("gameId")).then();
        for (const card of cards){
            this.displayColorCard(card, bluecards, greycards, blackcards);
        }
    }

    displayGuess(){
        document.body.innerHTML += '<div class = "clueDiv"><input type="text" class="clueGuess" placeholder="Indice"></div>';
        document.body.innerHTML += '<div class = "numberDiv"><input type="text" class="numberOfGuesses" placeholder="Nombre de mots en rapport"></div>';
        const numberOfGuesses = document.body.querySelector(".numberOfGuesses");
        const clueGuess = document.body.querySelector(".clueGuess");
        const guessDiv = document.createElement("div");
        guessDiv.classList.add("guess");
        guessDiv.innerHTML = ('<button class = "inputGuess"> Envoyer </button>');
        document.body.appendChild(guessDiv);
        const guessButton = guessDiv.querySelector(".inputGuess");
        guessButton.addEventListener("click", async () => {
            /*const turn = await GameService.getTurn(localStorage.getItem("gameId")).then();
            console.log(turn)
            if (turn === "input"){*/
                let clueGuessValue = clueGuess.value;
                let numberOfGuessesValue = numberOfGuesses.value;
                localStorage.setItem("clueGuess", clueGuessValue);
                localStorage.setItem("numberOfGuesses", numberOfGuessesValue);
                GameService.setTurn(localStorage.getItem("gameId"), "guess");
            //}
        });
    }

    displayColorCard(card, bluecards, greycards, blackcards) {
        const cardElement = document.createElement("div");
        cardElement.classList.add("card");
        cardElement.innerHTML = ('<button class = "coloredcards">' + card.contents + '</button>');
        this.parent.appendChild(cardElement);  
        const button = cardElement.querySelector(".coloredcards");
        for( let i = 0; i < 8; i++){
            if (bluecards[i].contents == card.contents){
                button.classList.add("blue");
            }
        }
        for( let i = 0; i < 15; i++){
            if (greycards[i].contents == card.contents){
                button.classList.add("grey");
            }
        }
        for( let i = 0; i < 2; i++){
            if (blackcards[i].contents == card.contents){
                button.classList.add("black");
            }
        }
    }
}
