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
        cardElement.innerHTML = ('<button>' + card.contents + '</button>');
        this.parent.appendChild(cardElement);  
        const button = cardElement.querySelector("button");
        button.addEventListener("click", async () => {
            const updatedCard = await CardsService.checkColor(card.id);
            if(updatedCard){
                this.update(updatedCard);
            }
        });
    }

   update(data) {
   }
}
