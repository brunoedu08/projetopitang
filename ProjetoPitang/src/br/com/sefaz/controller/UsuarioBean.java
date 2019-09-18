package br.com.sefaz.controller;


import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import br.com.sefaz.dao.UsuarioDAO;
import br.com.sefaz.model.Usuario;

@ManagedBean(name = "usuarioMB")
public class UsuarioBean {
	
	private Usuario usuario = new Usuario();
	private UsuarioDAO<Usuario> usuarioDao = new UsuarioDAO<Usuario>();
	private List<Usuario> usuariolista = new ArrayList<Usuario>();
	
	public void logar(){
		usuarioDao.verificarLogin(usuario);
	}
	
	public List<Usuario>gerarTabela() {
		return usuarioDao.gerarTabela();
	}
	
	public void apagar(Usuario user){
		usuarioDao.remover(user);
	}
	
	public void editar(Usuario user){
		usuarioDao.alterar(user);
	}
	
	public void adicionar(Usuario user) {
		usuarioDao.adicionar(user);
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public UsuarioDAO<Usuario> getUsuarioDao() {
		return usuarioDao;
	}
	public void setUsuarioDao(UsuarioDAO<Usuario> usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

	public List<Usuario> getUsuariolista() {
		return usuariolista;
	}

	public void setUsuariolista(List<Usuario> usuariolista) {
		this.usuariolista = usuariolista;
	}

}
