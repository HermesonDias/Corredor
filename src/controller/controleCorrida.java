package controller;

import java.util.concurrent.Semaphore;

public class controleCorrida extends Thread {

	private int idPessoa;
	private Semaphore semaforo;
	private static int posicao;
	
	public controleCorrida(int idPessoa, Semaphore semaforo) {
		this.idPessoa = idPessoa;
		this.semaforo = semaforo;
	}
	
	@Override
	public void run() {
		corrida();
		try {
			semaforo.acquire();
			porta();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
		}
	}

	private void corrida() {
		int percurso = 200;
		int total=0;
		
		while(total<percurso) {
			try {
				sleep(1000);
				int andou = (int)(Math.random()*3)+4;
				total+=andou;
				System.out.println("O candidato #"+idPessoa+" andou "+andou+" ms, já acumulou "+total+" ms");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void porta() {
		System.out.println("O candidato #"+idPessoa+" chegou na porta");
		int abrePorta = (int)(Math.random()*2000)+1000;
		
		try {
			sleep(abrePorta);
			posicao++;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("O candidato #"+idPessoa+" chegou em "+posicao+"o lugar");
	}
}
