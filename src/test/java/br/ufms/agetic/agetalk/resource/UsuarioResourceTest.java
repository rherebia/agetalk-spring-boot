package br.ufms.agetic.agetalk.resource;

import java.util.Arrays;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.ufms.agetic.agetalk.entity.Usuario;
import br.ufms.agetic.agetalk.service.UsuarioService;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.mockito.BDDMockito.*;

@RunWith(SpringRunner.class)
@WebMvcTest(UsuarioResource.class)
public class UsuarioResourceTest {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private UsuarioService service;
	
	@Test
	public void devePegarTodosUsuarios() throws Exception {
		Usuario usuario1 = new Usuario();
		usuario1.setId(1L);
		usuario1.setNome("Usuario Teste 1");
		
		Usuario usuario2 = new Usuario();
		usuario2.setId(2L);
		usuario2.setNome("Usuario Teste 2");
		
		given(service.findAll()).willReturn(Arrays.asList(usuario1, usuario2));
		
		mvc.perform(get("/usuarios").accept(MediaType.APPLICATION_JSON))
			.andExpect(status().is(HttpStatus.OK.value()))
			.andExpect(content().string(containsString("Usuario Teste 1")))
			.andExpect(content().string(containsString("Usuario Teste 2")));
	}
	
	@Test
	public void devePegarUsuarioPorId() throws Exception {
		Usuario usuario = new Usuario();
		usuario.setId(1L);
		usuario.setNome("Usuario Teste 1");
		
		given(service.findById(anyLong())).willReturn(Optional.of(usuario));
		
		mvc.perform(get("/usuarios/1").accept(MediaType.APPLICATION_JSON))
			.andExpect(status().is(HttpStatus.OK.value()))
			.andExpect(content().string(containsString("Usuario Teste 1")));
	}
	
	@Test
	public void deveAtualizarUsuario() throws Exception {
		Usuario usuario = new Usuario();
		usuario.setId(1L);
		usuario.setNome("Usuario Teste 1");
		
		given(service.findById(anyLong())).willReturn(Optional.of(usuario));
		given(service.save(any())).willReturn(usuario);
		
		mvc.perform(put("/usuarios/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"id\":1,\"nome\":\"Usuario Teste 2\"}"))
			.andExpect(status().is(HttpStatus.OK.value()));
	}
	
	@Test
	public void deveCriarUsuario() throws Exception {
		given(service.save(any())).willReturn(any());
		
		Usuario usuario = new Usuario();
		usuario.setId(1L);
		usuario.setNome("Usuario Teste 1");
		
		mvc.perform(post("/usuarios")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"id\":1,\"nome\":\"Usuario Teste 2\"}"))
			.andExpect(status().is(HttpStatus.OK.value()));
	}
	
	@Test
	public void deveRemoverUsuario() throws Exception {
		doNothing().when(service).deleteById(anyLong());
		
		mvc.perform(delete("/usuarios/1"))
			.andExpect(status().is(HttpStatus.OK.value()));
	}
}
