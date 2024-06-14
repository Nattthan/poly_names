import { PlayerService } from "../services/player-service.js";

export class ChoiceView {
    constructor() {
        this.parent = document.querySelector(".cards");
    }
    async displayChoicePage(){
        this.parent.innerHTML += '<div class="wrapper"></div>';
        const wrapper = this.parent.querySelector(".wrapper");
        wrapper.innerHTML += '<button class ="wordMaster">Maître des Mots</button>';
        wrapper.innerHTML += '<div class="description">As word master, your task is to guess your team s words with the help of the question master. Be careful to not find the black words or you lose !</div>'

        this.parent.innerHTML += '<div class="wrapper2"></div>';
        const wrapper2 = this.parent.querySelector(".wrapper2");
        wrapper2.innerHTML += '<button class ="intMaster">Maître des Indices</button>';
        wrapper2.innerHTML += '<div class="description">As Question Master, your task is to help the Word Master find all the words of your team! Be clever and find clues that could match as many words as you can !</div>'
        const wordMaster = this.parent.querySelector(".wordMaster");
        const intMaster = this.parent.querySelector(".intMaster");
        wordMaster.addEventListener("click", async () => {
            const joined = await PlayerService.joinTeam(localStorage.getItem('gameId'), 'wordMaster', localStorage.getItem('playerId'));
            if (!joined) {
                alert("The team is full. Please join the other team.");
            }
            else {
                localStorage.setItem("state", "4");
                location.reload();
            }
        });
        intMaster.addEventListener("click", async () => {
            const joined = await PlayerService.joinTeam(localStorage.getItem('gameId'), 'intMaster', localStorage.getItem('playerId'));
            if (!joined) {
                alert("The team is full. Please join the other team.");
            }
            else {
                localStorage.setItem("state", "3");
                location.reload();
            }
        });
    }
}