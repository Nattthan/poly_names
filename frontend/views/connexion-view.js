import { CardsService } from "../services/cards-service.js";
import { GameService } from "../services/game-service.js";

export class ConnexionView {
    constructor() {
        this.parent = document.querySelector(".cards");
    }
    async displayConnexionPage(){
        this.parent.innerHTML += '<button class= "createButton">Créer</button>';
        this.parent.innerHTML += '<button class= "joinButton">Rejoindre</button><br>';
        this.parent.innerHTML += '<input type="text" class="gameid">';
        const createButton = this.parent.querySelector(".createButton");
        const joinButton = this.parent.querySelector(".joinButton");
        const gameid = this.parent.querySelector(".gameid");
        createButton.addEventListener("click", async () => {
            localStorage.setItem("state", "2");
            let gameId = gameid.value;
            localStorage.setItem("gameId", gameId);
            CardsService.findAll(gameId);
            location.reload();
        });
        joinButton.addEventListener("click", async () => {
            localStorage.setItem("state", "2");
            let gameId = gameid.value;
            localStorage.setItem("gameId", gameId);
            GameService.findGame(gameId);
            location.reload();
        });
    }
}