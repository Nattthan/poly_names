import { CardsService } from "../services/cards-service.js";

export class CardsView {
    constructor() {
        this.parent = document.querySelector(".cards");
    }
    
    async displayCards() {
        const cards = await CardsService.findAll(9994);
        for (const card of cards){
            this.displayCard(card);
        }
    }

    displayCard(card) {
        const cardElement = document.createElement("div");
        cardElement.classList.add("card");
        cardElement.innerHTML = ('<button class = "normalcards">' + card.contents + '</button>');
        this.parent.appendChild(cardElement);  
        const button = cardElement.querySelector(".normalcards");
        button.addEventListener("click", async () => {
            
        });
    }

   update(data) {
   }
}
