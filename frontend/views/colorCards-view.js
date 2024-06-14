import { ColorCardsService } from "../services/colorCards-service.js";

export class ColorCardsView {
    constructor() {
        this.parent = document.querySelector(".cards");
    }
    
    async displayColorCards() {
        const cards = await ColorCardsService.findAll(localStorage.getItem('gameId'));
        for (const card of cards){
            this.displayColorCard(card);
        }
    }

    displayColorCard(card) {
        const cardElement = document.createElement("div");
        cardElement.classList.add("card");
        cardElement.innerHTML = ('<button class = "coloredcards">' + card.contents + '</button>');
        this.parent.appendChild(cardElement);  
        const button = cardElement.querySelector(".coloredcards");
        button.addEventListener("click", async () => {
            
        });
    }

   update(data) {
   }
}
