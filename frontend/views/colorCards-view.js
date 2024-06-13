import { ColorCardsService } from "../services/colorCards-service.js";

export class ColorCardsView {
    constructor() {
        this.parent = document.querySelector(".cards");
    }
    
    async displayColorCards() {
        const cards = await ColorCardsService.findAll(9994);
        for (const card of cards){
            this.displayColorCard(card);
        }
    }

    displayColorCard(card) {
        const cardElement = document.createElement("div");
        cardElement.classList.add("card");
        cardElement.innerHTML = ('<button>' + card.contents + '</button>');
        this.parent.appendChild(cardElement);  
        const button = cardElement.querySelector("button");
        button.addEventListener("click", async () => {
            const updatedCard = await ColorCardsService.checkColor(card.id);
            if(updatedCard){
                this.update(updatedCard);
            }
        });
    }

   update(data) {
   }
}
