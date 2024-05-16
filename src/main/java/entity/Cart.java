package entity;

public class Cart {
  private Account account;
  private Product product;
  private int amount;
  private int cartID;

  public Cart(Account account, Product product, int amount, int cartID) {
    this.account = account;
    this.product = product;
    this.amount = amount;
    this.cartID = cartID;
  }

  public Account getAccount() {
    return account;
  }

  public void setAccount(Account account) {
    this.account = account;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public int getCartID() {
    return cartID;
  }

  public void setCartID(int cartID) {
    this.cartID = cartID;
  }

  @Override
  public String toString() {
    return "Cart{" +
      "account=" + account +
      ", product=" + product +
      ", amount=" + amount +
      ", cartID=" + cartID +
      '}';
  }
}
