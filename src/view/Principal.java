package view;

import java.util.concurrent.Semaphore;

import controller.controleCorrida;

public class Principal {

	public static void main(String[] args) {
		
		Semaphore semaforo = new Semaphore(1);
		
		for(int idPessoa=1; idPessoa<=4; idPessoa++) {
			Thread contCorrida = new controleCorrida(idPessoa, semaforo);
			contCorrida.start();
		}
	}

}
