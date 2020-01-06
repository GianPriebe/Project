package com.example;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Cart {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;
  @ManyToOne
  private Product product;
  @ManyToOne
  private Customer customer;


  protected Cart() {}

  public Cart(Product product, Customer customer) {
    this.product = product;
    this.customer = customer;
  }

  @Override
  public String toString() {
    return String.format(
        "[id=%d, customer='%s', product='%s']",
        id, customer, product);
  }

  public Long getId() {
    return id;
  }

  /**
   * @return o customer
   */
  public Customer getCustomer() {
    return customer;
  }

  /**
   * @return o product
   */
  public Product getProduct() {
    return product;
  }

  /**
   * @param customer para set
   */
  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  /**
   * @param product para set
   */
  public void setProduct(Product product) {
    this.product = product;
  }
}