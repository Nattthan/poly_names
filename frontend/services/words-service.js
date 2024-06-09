export class WordsService {
  constructor() {
    this.products = [];
  }

  static async findAll(){
    const response = await fetch('http://localhost:8080/words');

    if(response.status === 200){
        const data = await response.json();
        return data;
    }

    return this.products;
  }

}