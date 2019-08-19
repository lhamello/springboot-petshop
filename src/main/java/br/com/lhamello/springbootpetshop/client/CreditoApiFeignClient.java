package br.com.lhamello.springbootpetshop.client;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@Qualifier("feign")
@FeignClient(value = "credito-api", url = "${api.credito-api.url}")
public interface CreditoApiFeignClient extends CreditoApiClient {

	@Override
	@GetMapping("/credito/{cpf}")
	CreditoDTO verificarSituacao(@PathVariable("cpf") String cpf);
}