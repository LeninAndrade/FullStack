package com.example.algamoney.api.resource;

import java.net.URI;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.algamoney.api.model.Pessoa;
import com.example.algamoney.api.repository.PessoaRepository;

@RestController
@RequestMapping("/cadastros")
public class PessoaResource {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Pessoa> buscarPeloCodigo(@PathVariable Long codigo) {
		Optional<Pessoa> pessoa = pessoaRepository.findById(codigo);
		if(pessoa.isPresent()) {
			return ResponseEntity.ok(pessoa.get());
		}
		else {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.build();
		}
	
	}
	
	@PostMapping
	public ResponseEntity<Pessoa> cadastrar(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response) {
		Pessoa pessoaCadastrada = pessoaRepository.save(pessoa);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
				   .buildAndExpand(pessoaCadastrada.getCodigo()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(pessoaCadastrada);
	}
 
}
