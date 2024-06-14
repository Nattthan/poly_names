export class PlayerService {
    constructor(){

    }

    // create or get player id from local storage depending on if it exists
    static getOrCreatePlayerId(){
        console.log("Getting or creating player id");
        let playerId = localStorage.getItem('playerId');
        if(!playerId){
            playerId = crypto.randomUUID();
            localStorage.setItem('playerId', playerId);
        }
        return playerId;
    }

    // handle player joining team
    static async joinTeam(gameCode, team){
        const playerId = PlayerService.getOrCreatePlayerId();

        const response = await fetch(`http://localhost:8080/game/${gameCode}/${team}/player/${playerId}`, {
            method: 'POST',
        });
        if(response.status === 200){
            return true;
        }
        else if (response.status === 404){
            return false;
        }
    }

}