/**
 * 
 */
package br.com.makersweb.spring.rest.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.makersweb.spring.rest.domain.Cliente;
import br.com.makersweb.spring.rest.service.IClienteService;
import br.com.makersweb.spring.rest.service.exception.BusinessException;
import br.com.makersweb.spring.rest.util.ConstantesUtils;
import br.com.makersweb.spring.rest.util.DefaultResponse;

/**
 *
 * @author anderson.aristides
 *
 */
@RestController
@RequestMapping("${makersweb.api}")
public class ClienteResource {
	
	@Autowired
	private IClienteService clienteService;
	
	@RequestMapping(value = "/cliente", method = RequestMethod.GET)
	public ResponseEntity<?> listar() {
		DefaultResponse response = new DefaultResponse();
		
		try {
			return ResponseEntity.ok(clienteService.listar());
		} catch (BusinessException e) {
			response.setError(true);
			response.setTypeError(ConstantesUtils.E_USER_WARNING);
			response.setMessage(e.getMessage());
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@RequestMapping(value = "/cliente/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> buscar(@PathVariable("id") Long id) {
		DefaultResponse response = new DefaultResponse();
		
		try {
			return ResponseEntity.ok(clienteService.buscar(id));
		} catch (BusinessException e) {
			response.setError(true);
			response.setTypeError(ConstantesUtils.E_USER_WARNING);
			response.setMessage(e.getMessage());
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@RequestMapping(value = "/cliente", method = RequestMethod.POST)
	public ResponseEntity<?> salvar(@RequestBody Cliente cliente) {
		DefaultResponse response = new DefaultResponse();
		
		try {
			return ResponseEntity.ok(clienteService.salvar(cliente));
		} catch (BusinessException e) {
			response.setError(true);
			response.setTypeError(ConstantesUtils.E_USER_WARNING);
			response.setMessage(e.getMessage());
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

}
