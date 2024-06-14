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

    static async getTurn(gameCode){
        const response = await fetch(`http://localhost:8080/game/${gameCode}/getTurn`);
        
        if(response.status === 200){
            const data = await response.json();
            return data;
        }
    
        return this.cards;
    }
    static async getScore(gameCode){
        const response = await fetch(`http://localhost:8080/game/${gameCode}/getScore`);
        
        if(response.status === 200){
            const data = await response.json();
            return data;
        }
    
        return this.cards;
    } 

    static async setTurn(gameCode, turn){
        const response = await fetch(`http://localhost:8080/game/${gameCode}/setTurn/${turn}`, {method : "POST"});
        if(response.status === 200){
            const data = await response.json();
            console.log("Done this");
            return data;
        }
    
        return this.cards;
    }

    static async setScore(gameCode, score){
        const response = await fetch(`http://localhost:8080/game/${gameCode}/setScore/${score}`, {method : "POST"});
        
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