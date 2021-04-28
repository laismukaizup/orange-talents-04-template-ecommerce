package br.com.academy.lais.mercadolivre.Seguranca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenService tokenService;

	@PostMapping
	public ResponseEntity<?> autenticar(@RequestBody @Valid LoginRequest loginRequest) {
		UsernamePasswordAuthenticationToken dadosLogin = loginRequest.converter();

		try {
			Authentication autenthication = authManager.authenticate(dadosLogin);
			String token = tokenService.gerarToken(autenthication);
			return ResponseEntity.ok(new TokenRequest(token, "Bearer"));

		} catch (InsufficientAuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}

	}

}
