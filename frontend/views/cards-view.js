import { CardsService } from "../services/cards-service";

export class cardsView {
  constructor() {
    this.parent = document.querySelector(".cards");
  }
  async displayCards() {
    const cards = await ProductsService.findAll(9994);
    cards.forEach((card) => {
        this.displayCard(card);
    });
  }

    displayCard(card) {
        const cardElement = document.createElement("div");
        cardElement.classList.add("card");
        cardElement.innerHTML = <button>${card.contents}</button>;
        this.parent.appendChild(cardElement);  
        const button = cardElement.querySelector("button");
   }
}