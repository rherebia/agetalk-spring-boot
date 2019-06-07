package br.ufms.agetic.agetalk.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufms.agetic.agetalk.entity.Usuario;

@Repository
public interface UsuarioService extends JpaRepository<Usuario, Long> {

}
