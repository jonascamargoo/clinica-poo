package View;

import java.util.Scanner;

public class MenuView {
	private static Scanner scn = new Scanner(System.in);

	public int menuPrincipal() {
		System.out.println("\nBem-vindo(a) ao nosso sistema!");
		System.out.println("[1] Logar(a)"); 
		System.out.println("[2] Menu de Usuários");
		System.out.println("[3] Sair");
		System.out.print("Selecione uma opção: ");
		return scn.nextInt();
	}
	
	public int menuUsuario() {
		System.out.println("\nMenu de usuários");
		System.out.println("[1] Cadastrar novo usuário");
		System.out.println("[2] Excluir usuário");
		System.out.println("[3] Alterar usuário");
		System.out.println("[4] Listar usuários");
		System.out.println("[5] Finalizar menu de usuários");
		System.out.print("Selecione uma opção: ");
		return scn.nextInt();
	}
	
	public int menuMedico() {
		System.out.println("\n[1] Adicionar anamnese");
		System.out.println("[2] Buscar anamnese");
		System.out.println("[3] Alterar anamnese");
		System.out.println("[4] Listar anamneses");
		System.out.println("[5] Finalizar menu do médico\n");
		return scn.nextInt();
	}

	public int menuAssistente() {
		System.out.println("\n[1] Adicionar paciente");
		System.out.println("[2] Excluir paciente");
		System.out.println("[3] Alterar paciente");
		System.out.println("[4] Listar pacientes");
		System.out.println("[5] Finalizar menu do assistente\n");
		return scn.nextInt();
	}

	

}
