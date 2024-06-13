export class ChoiceView {
    constructor() {
        this.parent = document.querySelector(".cards");
    }
    async displayChoicePage(){
        this.parent.innerHTML += '<button>Maître des Mots</button>';
        this.parent.innerHTML += '<button>Maître des Intuitions</button>';
    }
}