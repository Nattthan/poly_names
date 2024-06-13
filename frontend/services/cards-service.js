export class CardsService {
  constructor() {
    this.cards = [];
  }

  static async findAll(gameCode) {
    const response = await fetch(
      `http://localhost:8080/game/${gameCode}/cards`
    );

    if (response.status === 200) {
      const data = await response.json();
      return data;
    }

    return this.cards;
  }
}
