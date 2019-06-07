package br.ufms.agetic.agetalk.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufms.agetic.agetalk.entity.Usuario;
import br.ufms.agetic.agetalk.service.UsuarioService;

@RestController
@RequestMapping("usuarios")
public class UsuarioResource {

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping("/{id}")
	public Usuario get(@PathVariable("id") Long id) {
		return usuarioService.findById(id).orElse(null);
	}

	@GetMapping
	public List<Usuario> getAll() {
		return usuarioService.findAll();
	}

	@PostMapping
	public void save(@RequestBody Usuario usuario) {
		usuarioService.save(usuario);
	}

	@PutMapping("{id}")
	public void update(@PathVariable("id") Long id, 
			@RequestBody Usuario usuario) {
		if (usuarioService.existsById(id)) {
			usuario.setId(id);
			
			usuarioService.save(usuario);
		}
	}

	@DeleteMapping("{id}")
	public void remove(@PathVariable("id") Long id) {
		if (usuarioService.existsById(id)) {
			usuarioService.deleteById(id);
		}
	}
}
