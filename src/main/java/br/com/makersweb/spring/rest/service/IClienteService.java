/**
 * 
 */
package br.com.makersweb.spring.rest.service;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;

import br.com.makersweb.spring.rest.domain.Cliente;
import br.com.makersweb.spring.rest.service.exception.BusinessException;

/**
 *
 * @author anderson.aristides
 *
 */
public interface IClienteService {
	
	List<Cliente> listar() throws BusinessException;
	
	Cliente buscar(Long id) throws BusinessException;
	
	Cliente salvar(Cliente cliente) throws BusinessException;
	
	Cliente atualizar(Cliente cliente) throws BusinessException;
	
	void deletar(Long id) throws EmptyResultDataAccessException;
	
	void verificaExistencia(Cliente cliente) throws BusinessException;

}
