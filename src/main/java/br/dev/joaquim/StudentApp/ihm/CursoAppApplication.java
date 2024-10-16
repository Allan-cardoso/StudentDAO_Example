package br.dev.joaquim.StudentApp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.dev.joaquim.StudentApp.dao.H2CursoDAO;
import br.dev.joaquim.StudentApp.ihm.CursoIHM;

@SpringBootApplication
public class CursoAppApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CursoAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("-------------------------");
		System.out.println("APLICACAO INICIALIZADA");
		System.out.println("-------------------------");

		CursoIHM ihm = new CursoIHM(new H2CursoDAO());
		ihm.start();
	}

}
