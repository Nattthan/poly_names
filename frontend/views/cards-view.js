import { CardsService } from "../services/cards-service.js";
import { ColorCardsService } from "../services/colorCards-service.js";
import { GameService } from "../services/game-service.js";
import { HomeView } from "./home-view.js";

export class CardsView {
    constructor() {
        this.parent = document.querySelector(".cards");
    }
    
    async displayCards() {
        const cards = await CardsService.findAll(localStorage.getItem('gameId'));
        const bluecards = await GameService.getBlueCards(localStorage.getItem("gameId")).then();
        const greycards = await GameService.getGreyCards(localStorage.getItem("gameId")).then();
        const blackcards = await GameService.getBlackCards(localStorage.getItem("gameId")).then();
        const score = await GameService.getScore(localStorage.getItem("gameId"));
        localStorage.setItem("guess", "1");
        for (const card of cards){
            await this.displayCard(card, bluecards, greycards, blackcards);
        }
    }

    async displayCard(card, bluecards, greycards, blackcards) {
        const cardElement = document.createElement("div");
        cardElement.classList.add("card");
        cardElement.innerHTML = ('<button class = "normalcards">' + card.contents + '</button>');
        this.parent.appendChild(cardElement);  
        const button = cardElement.querySelector(".normalcards");
        button.addEventListener("click", async () => {
            /*let nbGuess = parseInt(localStorage.getItem("numberOfGuesses"));
            let guess = parseInt(localStorage.getItem("guess"));
            const turn = await GameService.getTurn(localStorage.getItem("gameID")).then();  
            console.log(turn)
            if (turn === "guess"){*/
                for( let i = 0; i < 8; i++){
                    if (bluecards[i].contents == card.contents){
                        button.classList.add("blue");
                        /*let scoreUp = parseInt(localStorage.getItem("score"));
                        if (guess === (nbGuess + 1)){
                            scoreUp += (guess * guess);
                            localStorage.setItem("score", scoreUp.toString());
                            GameService.setScore(localStorage.getItem("gameId"), scoreUp);
                            guess ++ ;
                            localStorage.setItem("guess", guess.toString());
                            GameService.setTurn(localStorage.getItem("gameId"), "input");
                            console.log("inputing");
                        }
                        if (guess < (nbGuess +2)){
                            scoreUp +=  guess;
                            localStorage.setItem("score", scoreUp.toString());
                            GameService.setScore(localStorage.getItem("gameId"), scoreUp);
                            guess ++ ;
                            localStorage.setItem("guess", guess.toString());
                        }*/
                        
                    }
                }
                for( let i = 0; i < 15; i++){
                    if (greycards[i].contents == card.contents){
                        button.classList.add("grey");
                        /*guess === nbGuess +2;
                        GameService.setTurn(localStorage.getItem("gameId"),"input")*/
                    }
                }
                for( let i = 0; i < 2; i++){
                    if (blackcards[i].contents == card.contents){
                        button.classList.add("black");
                        localStorage.setItem("score", "0")
                        let scoreDown = parseInt(localStorage.getItem("score"));
                        GameService.setScore(localStorage.getItem("gameId"), scoreDown)
                    }
                }
            //}
        });
    }
}
