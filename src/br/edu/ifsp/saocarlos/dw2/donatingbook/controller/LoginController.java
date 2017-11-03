package br.edu.ifsp.saocarlos.dw2.donatingbook.controller;

import java.security.NoSuchAlgorithmException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;

import br.edu.ifsp.saocarlos.dw2.donatingbook.repository.UsuarioRepository;

@ManagedBean
public class LoginController extends Controller {

	private String email;
	private String senha;
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String logar() throws NoSuchAlgorithmException {
		FacesContext context = FacesContext.getCurrentInstance();
		EntityManager manager = getEntityManager();
		
		UsuarioRepository usuarioRepo = new UsuarioRepository(manager);
		String loginResult = usuarioRepo.login(email, senha);
		if(loginResult.equals("Voluntario")) {
			ExternalContext externalContext = context.getExternalContext();
			HttpSession session = (HttpSession) externalContext.getSession(Boolean.FALSE);
			session.setAttribute("usuario", email);
			// chamar a HOME do voluntario
			System.out.println("HOME DO VOLUNTARIO");
			return "home.xhtml";
		}
		else if(loginResult.equals("Organizacao")) {
			ExternalContext externalContext = context.getExternalContext();
			HttpSession session = (HttpSession) externalContext.getSession(Boolean.FALSE);
			session.setAttribute("usuario", email);
			// chamar a HOME da organizacao
			System.out.println("HOME DA ORGANIZACAO");
			return "home.xhtml";
		}
		else if(loginResult.equals("membro")) {
			ExternalContext externalContext = context.getExternalContext();
			HttpSession session = (HttpSession) externalContext.getSession(Boolean.FALSE);
			session.setAttribute("usuario", email);
			// chamar a HOME do membro
			System.out.println("HOME DO MEMBRO");
			return "home.xhtml";
		}
		else {
			FacesMessage message = new FacesMessage("Senha ou usu�rio incorreto!");
			message.setSeverity(FacesMessage.SEVERITY_INFO);
			
			context.addMessage("form:senha", message);
			// exibir o erro
			return "index.xhtml";
		}
	}
	
}
