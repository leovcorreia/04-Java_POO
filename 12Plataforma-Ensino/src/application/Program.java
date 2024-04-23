package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Lesson;
import entities.Task;
import entities.Video;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Quantas aulas tem o curso? ");
		int n = sc.nextInt();
		sc.nextLine(); // Limpeza buffer
		
		List<Lesson> list = new ArrayList<>();
		
		for (int i = 0; i < n; i++) {
			System.out.println("\nDados da " + (i+1) + "a aula: ");
			System.out.print("Conteúdo ou tarefa (c/t)? ");
			char type = sc.next().charAt(0);
			sc.nextLine(); // Limpeza buffer
			
			System.out.print("Título: ");
			String title = sc.nextLine();
			
			if (type == 'c') {
				System.out.print("URL do vídeo: ");
				String url = sc.nextLine();
				System.out.print("Duração em segundos: ");
				Integer seconds = sc.nextInt();
				list.add(new Video(title, url, seconds));
			} else if (type == 't') {
				System.out.print("Descrição: ");
				String description = sc.nextLine();
				System.out.print("Quantidade de questões: ");
				Integer questionCount = sc.nextInt();
				list.add(new Task(title, description, questionCount));
			}
			
		}
		
		Integer duration = 0;
		for (Lesson lesson: list) {
			duration += lesson.duration();
		}
		
		System.out.println("\nDURAÇÃO TOTAL DO CURSO = " + duration + " segundos");
		
		sc.close();
	}

}
