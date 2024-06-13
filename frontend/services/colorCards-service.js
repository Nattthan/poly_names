export class ColorCardsService {
    constructor() {
      this.cards = [];
    }
  
    static async findAll(gameCode){
      const response = await fetch(`http://localhost:8080/game/${gameCode}/colorCards`);
  
      if(response.status === 200){
          const data = await response.json();
          return data;
      }
  
      return this.cards;
    }

  }