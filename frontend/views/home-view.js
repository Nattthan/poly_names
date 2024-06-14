import { GameService } from "../services/game-service.js";

export class HomeView {
    constructor() {
        this.parent = document.querySelector(".cards");
    }

    async displayHomeView() {
        this.parent.innerHTML += '<br><button class= "homeBtn">Home</button>';
        const homeBtn = this.parent.querySelector(".homeBtn");
        homeBtn.addEventListener("click", async () => {
            localStorage.setItem("state", "1");
            location.reload();
            GameService.setTurn(9994, "input")
        });
    }
}
