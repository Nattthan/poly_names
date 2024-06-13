export class ChoiceView {
    constructor() {
        this.parent = document.querySelector(".cards");
    }
    async displayChoicePage(){
        this.parent.innerHTML += '<button class ="wordMaster">Maître des Mots</button>';
        this.parent.innerHTML += '<button class = "intMaster">Maître des Intuitions</button>';
        const wordMaster = this.parent.querySelector(".wordMaster");
        const intMaster = this.parent.querySelector(".intMaster");
        wordMaster.addEventListener("click", async () => {
            localStorage.setItem("state", "4");
            location.reload();
        });
        intMaster.addEventListener("click", async () => {
            localStorage.setItem("state", "3");
            location.reload();
        });
    }
}