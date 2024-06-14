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
            for( let i = 0; i < 8; i++){
                if (bluecards[i].contents == card.contents){
                    button.classList.add("blue");
                    button.classList.remove("normalcards");
                }
            }
            for( let i = 0; i < 15; i++){
                if (greycards[i].contents == card.contents){
                    button.classList.add("grey");
                    button.classList.remove("normalcards");
                }
            }
            for( let i = 0; i < 2; i++){
                if (blackcards[i].contents == card.contents){
                    button.classList.add("black");
                    button.classList.remove("normalcards");
                }
            }
        });
    }
}
