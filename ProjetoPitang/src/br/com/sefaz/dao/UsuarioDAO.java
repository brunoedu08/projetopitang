package br.com.sefaz.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import br.com.sefaz.model.Usuario;

public class UsuarioDAO<obj> {

	List<Usuario> listusuario = new ArrayList<Usuario>();

	public void adicionar(Usuario usuario) {

		usuario.setNome(usuario.getNome());
		usuario.setEmail(usuario.getEmail());
		usuario.setSenha(usuario.getSenha());

		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		session.beginTransaction();

		session.save(usuario);
		saveMessage();
		session.getTransaction().commit();
		session.close();

	}

	@SuppressWarnings("unchecked")
	public void lista() {
		try {

			SessionFactory factory = new Configuration().configure().buildSessionFactory();
			Session session = factory.openSession();
			session.beginTransaction();

			List<Usuario> listUsuarios = new ArrayList<>();

			listUsuarios = session.createQuery("from Usuario").list();

			int tamanho = listUsuarios.size();

			for (int i = 0; i < tamanho; i++) {
				Usuario usuario = listUsuarios.get(i);

				System.out.println(usuario.getId() + " - " + usuario.getNome());
			}

			session.close();
			factory.close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public void alterar(Usuario usuario) {
		
		usuario.setId(usuario.getId());
		usuario.setNome(usuario.getNome());
		usuario.setEmail(usuario.getEmail());
		usuario.setSenha(usuario.getSenha());

		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		session.beginTransaction();

		session.update(usuario);
		editMessage();
		session.getTransaction().commit();
		session.close();

	}

	public void remover(Usuario usuario) {

		usuario.setEmail(usuario.getEmail());

		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		session.beginTransaction();

		session.delete(usuario);

		session.getTransaction().commit();
		session.close();

	}

	public String verificarLogin(Usuario user) {
		try {
			SessionFactory factory = new Configuration().configure().buildSessionFactory();
			Session session = factory.openSession();
			session.beginTransaction();

			List<Usuario> listPessoas = new ArrayList<Usuario>();

			listPessoas = session.createQuery(
					"from Usuario where email = '" + user.getEmail() + "' and senha = '" + user.getSenha() + "'")
					.list();

			int tamanho = listPessoas.size();

			if (tamanho == 0) {
				System.out.println("Usuario Invalido");
				msgIncorreta();
			} else {
				System.out.println("Usuario Logado");
				PagePesquisarButton();
			}

			session.close();
			factory.close();
		} catch (IOException e) {
			System.out.println(e);
		}
		return null;
	}

	public void PagePesquisarButton() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().redirect("index.html");
	}

	public void msgIncorreta() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Erro", "Usuario ou Senha Incorreta"));
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> gerarTabela() {

		// conexão com o banco
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		// fim da conexão

		// cria a lsta com os atributos carros
		listusuario = session.createQuery("from Usuario").list(); // faz a query e lista

		session.close();
		factory.close();

		return listusuario; // retorna a listcarros com a query
	}

	public void editMessage() {
		FacesContext context = FacesContext.getCurrentInstance();

		context.addMessage(null, new FacesMessage("Successful", "Foi modificado com sucesso "));
	}

	public void saveMessage() {
		FacesContext context = FacesContext.getCurrentInstance();

		context.addMessage(null, new FacesMessage("Successful", "Foi gravado com sucesso "));
	}

}
