package br.edu.ifsp.saocarlos.dw2.donatingbook.controller;

import java.security.NoSuchAlgorithmException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;

import br.edu.ifsp.saocarlos.dw2.donatingbook.repository.MembroRepository;
import br.edu.ifsp.saocarlos.dw2.donatingbook.repository.OrganizacaoRepository;
import br.edu.ifsp.saocarlos.dw2.donatingbook.repository.UsuarioRepository;
import br.edu.ifsp.saocarlos.dw2.donatingbook.repository.VoluntarioRepository;

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
		int loginIdResult = usuarioRepo.loginId(email, senha);
		String loginResult = usuarioRepo.login(email, senha);
		if(loginResult.equals("Voluntario")) {
			VoluntarioRepository voluntarioRepository = new VoluntarioRepository(manager);
			int voluntarioStatus = voluntarioRepository.status(loginIdResult);
			if(voluntarioStatus == 0) {
				System.out.println("Volunt�rio desativado! Por favor, fale com o Administrador do Sistema!");
				return "Volunt�rio desativado! Por favor, fale com o Administrador do Sistema!";
			}else if(voluntarioStatus == 1) {
				ExternalContext externalContext = context.getExternalContext();
				HttpSession session = (HttpSession) externalContext.getSession(Boolean.FALSE);
				session.setAttribute("usuario", email);
				session.setAttribute("id", loginIdResult);
				System.out.println("HOME DO VOLUNTARIO");
				return "/client/doador/home_doador.xhtml";
			}
			return "Login Volunt�rio";
		}
		else if(loginResult.equals("Organizacao")) {
			OrganizacaoRepository ongRepo = new OrganizacaoRepository(manager);
			int ongStatus = ongRepo.status(loginIdResult);
			if(ongStatus == 0) {
				System.out.println("Aguarde aprova��o do Administrador para poder acessar o sistema!");
				return "Aguarde aprova��o do Administrador para poder acessar o sistema!";
			}else if(ongStatus == 1) {
				System.out.println("Seja Bem vindo ao sistema Donating Book");
				ExternalContext externalContext = context.getExternalContext();
				HttpSession session = (HttpSession) externalContext.getSession(Boolean.FALSE);
				session.setAttribute("usuario", email);
				session.setAttribute("id", loginIdResult);
				System.out.println("HOME DA ORGANIZACAO");
				return "/client/organizacao/home_ong.xhtml";
			}else if(ongStatus == 2) {
				System.out.println("Organiza��o desativada! Por favor, fale com o Administrador do Sistema!");
				return "Organiza��o desativada! Por favor, fale com o Administrador do Sistema!";
			}
			return "Login Organizacao";
		}
		else if(loginResult.equals("membro")) {
			MembroRepository membroRepository = new MembroRepository(manager);
			int membroStatus = membroRepository.status(loginIdResult);
			if(membroStatus == 0) {
				System.out.println("Membro desativado");
				return "Usu�rio desativado";
			}else if(membroStatus == 1) {
				ExternalContext externalContext = context.getExternalContext();
				HttpSession session = (HttpSession) externalContext.getSession(Boolean.FALSE);
				session.setAttribute("usuario", email);
				session.setAttribute("id", loginIdResult);
				System.out.println("HOME DO MEMBRO");
				return "/client/membro/home_membro.xhtml";
			}
			return "Login Membro";
		}
		else if(loginResult.equals("Administrador")) {
			ExternalContext externalContext = context.getExternalContext();
			HttpSession session = (HttpSession) externalContext.getSession(Boolean.FALSE);
			session.setAttribute("usuario", email);
			session.setAttribute("id", loginIdResult);
			System.out.println("HOME DO ADMINISTRADOR");
			return "/client/admin/home_admin";
		} else {
			FacesMessage message = new FacesMessage("Senha ou usu�rio incorreto!");
			message.setSeverity(FacesMessage.SEVERITY_INFO);
			
			context.addMessage("form:senha", message);
			// exibir o erro
			return "index.xhtml";
		}
	}
	
}
