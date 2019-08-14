package br.com.lhamello.springbootpetshop.client;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import br.com.lhamello.springbootpetshop.core.exception.ServiceException;

@Component
public class CreditoApiRestTemplateClient {

	private final RestTemplate restTemplate;
	private static final Logger LOGGER = LoggerFactory.getLogger(CreditoApiRestTemplateClient.class);

	public CreditoApiRestTemplateClient(final RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public CreditoDTO verificarSituacao(final String cpf) {
		final URI url = URI.create("https://imersao-credito-api.herokuapp.com/credito/" + cpf);

		try {
			return restTemplate.getForObject(url, CreditoDTO.class);
		} catch (HttpClientErrorException excecao) {
			LOGGER.info("Erro ao acessar serviço de crédito.", excecao);
			
			if (excecao.getStatusCode().is4xxClientError()) {
				throw new ServiceException("Verifique o CPF enviado. Detalhes: " + excecao.getMessage());
			} else {
				throw new ServiceException("Serviço de consulta ao crédito indisponível.");
			}
		}
	}
}
