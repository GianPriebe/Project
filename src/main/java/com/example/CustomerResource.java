package com.example;

import java.util.Optional;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class CustomerResource {

  @Autowired
  private CustomerRepository repository;
  /*
   * Construtor do CustomerResource, lista de clientes
   */
  public CustomerResource(CustomerRepository repository) {
    this.repository = repository;
  }

  /**
   *GET, para uma lista
   * @return lista de clientes
   */
  @RequestMapping(value = "/clientes/", method = RequestMethod.GET)
  public Iterable<Customer> buscarProdutos() {
    return this.repository.findAll();
  }

  /**
   *GET, para um item
   * @param id identificador de clientes
   * @return item de produto unico
   */
  @RequestMapping(value = "/clientes/{id}", method = RequestMethod.GET)
  public Optional<Customer> buscarCliente(@PathVariable Long id) {
    return this.repository.findById(id);
  }
  
  /**
   *DELETE, para remover um item
   * @param id identificador de clientes
   */
  @RequestMapping(value = "/clientes/{id}", method = RequestMethod.DELETE)
  public void removerCliente(@PathVariable Long id) {
    this.repository.deleteById(id);
  }

  @RequestMapping(value = "/clientes/", 
  method = RequestMethod.POST)
  public Customer criarCliente(@RequestBody Customer Customer) {
    String nome = Customer.getFirstName();
    String sobrenome = Customer.getLastName();
    return this.repository.save(new Customer(nome, sobrenome));
  }

  @RequestMapping(value="/clientes/{id}", 
  method=RequestMethod.PUT)
  public void alterarCliente(@PathVariable Long id,
  @RequestBody Customer produtoParam) {
      Customer cliente = this.repository.findById(id).get();
      cliente.setFirstName(produtoParam.getFirstName());
      cliente.setLastName(produtoParam.getLastName());
  }

}
