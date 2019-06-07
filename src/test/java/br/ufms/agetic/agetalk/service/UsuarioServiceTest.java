package br.ufms.agetic.agetalk.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.ufms.agetic.agetalk.entity.Usuario;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UsuarioServiceTest {
	
	@Autowired
	private UsuarioService usuarioService;

	@Test
	public void deveCriarUsuario() {
		Usuario usuario = new Usuario();
		usuario.setNome("Usuario Teste");
		
		long qtdeUsuariosAntes = usuarioService.count();
		
		usuarioService.save(usuario);
		
		long qtdeUsuariosDepois = usuarioService.count();
		
		assertTrue(qtdeUsuariosDepois == qtdeUsuariosAntes + 1);
	}

	@Test
	public void deveAtualizarUsuario() {
		Usuario usuario = new Usuario();
		usuario.setNome("Usuario Teste");
		
		long qtdeUsuariosAntes = usuarioService.count();
		
		usuario = usuarioService.save(usuario);
		
		long qtdeUsuariosDepois = usuarioService.count();
		
		assertTrue(qtdeUsuariosDepois == qtdeUsuariosAntes + 1);
		
		usuario.setNome("Usuario Nome Modificado");
		
		usuarioService.save(usuario);
		
		Optional<Usuario> usuarioBanco = usuarioService.findById(usuario.getId());
		
		assertEquals("Usuario Nome Modificado", usuarioBanco.get().getNome());
	}
	
	@Test
	public void deveEncontrarUsuarioPorId() {
		assertTrue(usuarioService.findById(1L).isPresent());
	}
	
	@Test
	public void deveEncontrarTodosUsuarios() {
		List<Usuario> usuarios = usuarioService.findAll();
		
		assertFalse(usuarios.isEmpty());
	}
	
	@Test
	public void deveRemoverUsuario() {
		long qtdeUsuariosAntes = usuarioService.count();
		
		usuarioService.deleteById(1L);
		
		long qtdeUsuariosDepois = usuarioService.count();
		
		assertTrue(qtdeUsuariosDepois == qtdeUsuariosAntes - 1);
	}
}
