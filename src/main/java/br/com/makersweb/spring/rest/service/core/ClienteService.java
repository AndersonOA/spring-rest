/**
 * 
 */
package br.com.makersweb.spring.rest.service.core;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import br.com.makersweb.spring.rest.domain.Cliente;
import br.com.makersweb.spring.rest.message.MakersMessage;
import br.com.makersweb.spring.rest.repository.IClienteRepository;
import br.com.makersweb.spring.rest.service.IClienteService;
import br.com.makersweb.spring.rest.service.exception.BusinessException;

/**
 *
 * @author anderson.aristides
 *
 */
@Service
@EnableTransactionManagement
public class ClienteService implements IClienteService {

	private static final Logger _logger = LogManager.getLogger(ClienteService.class);

	@Autowired
	private IClienteRepository clienteRepository;

	@Autowired
	private MessageSource messageSource;

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public List<Cliente> listar() throws BusinessException {
		_logger.info("Iniciou método listar cliente...");

		List<Cliente> clientes = clienteRepository.findAll();

		if (CollectionUtils.isEmpty(clientes)) {
			throw new BusinessException(new MakersMessage(messageSource)
					.message("br.com.makersweb.spring.rest.text.nao.encontrado", "Cliente"));
		}

		return clientes;
	}

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public Cliente buscar(Long id) throws BusinessException {
		_logger.info("Iniciou método buscar cliente...");

		Cliente cliente = clienteRepository.findOne(id);

		if (ObjectUtils.isEmpty(cliente)) {
			throw new BusinessException(new MakersMessage(messageSource)
					.message("br.com.makersweb.spring.rest.text.nao.encontrado", "Cliente"));
		}

		return cliente;
	}

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public Cliente salvar(Cliente cliente) throws BusinessException {
		_logger.info("Iniciou método salvar cliente...");

		if (ObjectUtils.isEmpty(cliente)) {
			throw new BusinessException(
					new MakersMessage(messageSource).message("br.com.makersweb.spring.rest.text.campos.obrigatorio"));
		} else {
			if (StringUtils.isEmpty(cliente.getNome()) || StringUtils.isEmpty(cliente.getEmail())
					|| StringUtils.isEmpty(cliente.getTelefone())) {
				throw new BusinessException(
						new MakersMessage(messageSource).message("br.com.makersweb.spring.rest.text.campos.obrigatorio.cliente"));
			}
		}

		return clienteRepository.save(cliente);
	}

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public Cliente atualizar(Cliente cliente) throws BusinessException {
		_logger.info("Iniciou método atualizar cliente...");
		verificaExistencia(cliente);
		
		return clienteRepository.save(cliente);
	}

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public void deletar(Long id) throws EmptyResultDataAccessException {
		_logger.info("Iniciou método deletar cliente...");
		
		try {
			clienteRepository.delete(id);
		} catch (EmptyResultDataAccessException e) {
			throw new BusinessException(new MakersMessage(messageSource)
					.message("br.com.makersweb.spring.rest.text.nao.encontrado", "Cliente"));
		}
	}

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public void verificaExistencia(Cliente cliente) throws BusinessException {
		buscar(cliente.getId());
	}

}
