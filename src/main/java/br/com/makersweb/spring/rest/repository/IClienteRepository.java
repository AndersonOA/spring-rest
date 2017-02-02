/**
 * 
 */
package br.com.makersweb.spring.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.makersweb.spring.rest.domain.Cliente;

/**
 *
 * @author anderson.aristides
 *
 */
public interface IClienteRepository extends JpaRepository<Cliente, Long> {

}
