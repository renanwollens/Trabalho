package Console;

import java.util.Scanner;

import acessoAosDados.Aluno;
import acessoAosDados.AlunoDAO;

public class App {
	
	public static void main(String[] args) {
		AlunoDAO dao = new AlunoDAO();
		Scanner s = new Scanner(System.in);
		String command = "";
		while (!command.equals("5")){
			menu();
			
			command = s.nextLine();
			switch (command) {
			case "1":
				criar(dao, s);
				break;
			case "2":
				atualizar(dao, s);				
				break;
			case "3":
				listar(dao);
				break;
			case "4":
				deletar(dao, s);				
				break;
			case "5":				
				break;
			default:
				System.out.println("commando não identificado");
				break;
			}
		}
	}

	private static void listar(AlunoDAO dao) {
		for (Aluno a : dao.getList()) {
			System.out.println(a.getId() + " - " + a.getNome() + ", notas : " +
					a.getNota1() +" e " + a.getNota2());
		}
	}

	private static void criar(AlunoDAO dao, Scanner s) {
		System.out.println("Digite o nome do aluno");
		String nome = s.nextLine();
		dao.CriarAluno(nome);
	}
	private static void deletar(AlunoDAO dao, Scanner s) {
		System.out.println("Digite o id do aluno");
		int id = Integer.valueOf(s.nextLine());
		dao.RemoverAluno(id);
	}
	private static void atualizar(AlunoDAO dao, Scanner s) {
		System.out.println("Digite o id do aluno");
		int id = Integer.valueOf(s.nextLine());
		Aluno a = dao.getAluno(id);
		if(a == null){
			System.err.println("Aluno não encontrado");
		}else {
			System.out.println("Digite as duas notas de "+ a.getNome());
			float nota1 = Float.valueOf(s.nextLine());
			float nota2 = Float.valueOf(s.nextLine());
			a.setNota1(nota1);
			a.setNota2(nota2);
			dao.AtualizarAluno(a);
		}
	}

	private static void menu() {
		System.out.println("O que deseja fazer?");
		System.out.println("1 - Adicionar um aluno");
		System.out.println("2 - Adicionar notas de um aluno");
		System.out.println("3 - Listar alunos");
		System.out.println("4 - Remover aluno");
		System.out.println("5 - sair");
	}
}
