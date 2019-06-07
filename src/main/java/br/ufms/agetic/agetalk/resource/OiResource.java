package br.ufms.agetic.agetalk.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OiResource {

	@GetMapping("oi")
	public String digaOi() {
		return "Ola!";
	}
}
