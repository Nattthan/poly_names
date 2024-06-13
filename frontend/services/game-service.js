export class GameService {
    constructor() {
        this.game = {};
    }
    static async findGame(gameCode){
    const response = await fetch(`http://localhost:8080/game/${gameCode}`);
    
    if(response.status === 200){
        const data = await response.json();
        return data;
    }

    return this.cards;
    }

    static async delete(gameCode) {
        const response = await fetch(
            `http://localhost:8080/game/delete/${gameCode}`,
            {
                method: 'DELETE',
            }
        );

        if (response.status === 200) {
            return true;
        }

        return false;
    }

}