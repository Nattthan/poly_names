import { WordsService } from "../services/words-service.js";

export class WordsView {
  constructor() {
    // this.parent = document.querySelector(".words");
  }

  // async displayProducts() {
  //   const products = await ProductsService.findAll();
  //   products.forEach((product) => {
  //     this.displayProduct(product);
  //   });
  // }

  // displayProduct(product) {
  //   const productElement = document.createElement("div");
  //   productElement.classList.add("product");
  //   productElement.innerHTML = `
  //       <p>${product.name}</p>
  //       <p>${product.owner}</p>
  //       <p id="product-${product.id}-bid">${product.bid}€</p>
  //       <button>Enchère</button>
  //     `;
  //   this.parent.appendChild(productElement);

  //   const button = productElement.querySelector("button");
  //   button.addEventListener("click", async () => {
  //     const updatedProduct = await ProductsService.bid(product.id);
  //     if(updatedProduct){
  //       this.updateBid(updatedProduct);
  //     }
  //   });
  // }

  // updateBid(data) {
  //   const productBidElement = document.getElementById(
  //     `product-${data.id}-bid`
  //   );
  //   productBidElement.textContent = `${data.bid}€`;
  // }
}
