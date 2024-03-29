package br.com.lhamello.springbootpetshop.client;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import br.com.lhamello.springbootpetshop.core.exception.ServiceException;

@Component
@Qualifier("restTemplate")
public class CreditoApiRestTemplateClient implements CreditoApiClient {

	private static final Logger LOGGER = LoggerFactory.getLogger(CreditoApiRestTemplateClient.class);
	private final RestTemplate restTemplate;
	private final String creditoApiUrl;

	public CreditoApiRestTemplateClient(final RestTemplate restTemplate, @Value("${api.credito-api.url}") final String creditoApiUrl) {
		this.restTemplate = restTemplate;
		this.creditoApiUrl = creditoApiUrl;
	}

	public CreditoDTO verificarSituacao(final String cpf) {
		final URI url = URI.create(String.format("%s/credito/%s", creditoApiUrl, cpf));

		try {
			return restTemplate.getForObject(url, CreditoDTO.class);
		} catch (HttpClientErrorException excecao) {
			LOGGER.info("Erro ao acessar serviço de crédito.", excecao);
			
			if (excecao.getStatusCode().is4xxClientError()) {
				throw new ServiceException("Verifique o CPF enviado. Detalhes: " + excecao.getMessage());
			} else {
				throw new ServiceException("Serviço de consulta ao crédito indisponível.");
			}
		} catch (Exception excecao) {
			LOGGER.info("Erro ao acessar o serviço de crédito.", excecao);
			throw new ServiceException("Serviço de consulta ao Crédito indisponível.");
		}
	}
}
