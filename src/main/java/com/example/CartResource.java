package com.example;

import java.util.Optional;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
// import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class CartResource {

  @Autowired
  private CartRepository repository;
  /*
   * Construtor do CartResource, preparando uma lista de compras
   */
  public CartResource(CartRepository repository) {
    this.repository = repository;
  }

  /**
   * GET, para uma lista
   * @return lista de compras
   */
  @RequestMapping(value = "/compras/", method = RequestMethod.GET)
  public Iterable<Cart> buscarCompras() {
    return this.repository.findAll();
  }

  /**
   * GET, para um item
   * @param id identificador da colecao das compras
   * @return item de produto unico, sendo procurado na lista pelo seu id
   */
  @RequestMapping(value = "/compras/{id}", method = RequestMethod.GET)
  public Optional<Cart> buscarCompra(@PathVariable Long id) {
    return this.repository.findById(id);
  }
  
  /**
   * DELETE, para remover um item
   * @param id identificador da colecao das compras
   */
  @RequestMapping(value = "/compras/{id}", method = RequestMethod.DELETE)
  public void removerCompra(@PathVariable Long id) {
    this.repository.deleteById(id);
  }

  @RequestMapping(value = "/compras/", 
  method = RequestMethod.POST)
  public Cart adicionarCompra(@RequestBody Cart Cart) {
    Product produto = Cart.getProduct();
    Customer cliente = Cart.getCustomer();
    return this.repository.save(new Cart(produto, cliente));
  }

  @RequestMapping(value="/compras/{id}", 
  method=RequestMethod.PUT)
  public void alterarCompra(@PathVariable Long id,
  @RequestBody Cart produtoParam) {
      Cart cliente = this.repository.findById(id).get();
      cliente.setProduct(produtoParam.getProduct());
      cliente.setCustomer(produtoParam.getCustomer());
  }

}
