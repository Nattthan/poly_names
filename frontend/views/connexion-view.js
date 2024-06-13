export class ConnexionView {
    constructor() {
        this.parent = document.querySelector(".cards");
    }
    async displayConnexionPage(){
        this.parent.innerHTML += '<button>Créer</button>';
        this.parent.innerHTML += '<button>Rejoindre</button>';
    }
}