import { GameService } from "../services/game-service.js";

export class HomeView {
    constructor() {
        this.parent = document.querySelector(".cards");
    }

    displayHomeView() {
        document.body.innerHTML += '<br><button class= "homeBtn">Home</button>';
        const homeBtn = document.body.querySelector(".homeBtn");
        homeBtn.addEventListener("click", async () => {
            localStorage.setItem("state", "1");
            location.reload();
            GameService.setTurn(1918, "input");
            GameService.setScore(1918, "8");
            console.log(GameService.getTurn(1918));
        });
    }
}
