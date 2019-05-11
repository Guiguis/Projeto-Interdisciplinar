package service;

import java.io.Serializable;
import java.util.ArrayList;

import dao.EntregaDAO;
import model.Entrega;
import model.Grupo;

public class EntregaService implements Serializable{

     EntregaDAO dao;
	
	public EntregaService() {
		dao = new EntregaDAO();
	}
	
	public Entrega loadEntrega(int id) {
		return dao.loadEntrega(id);
	}
	
	public ArrayList<Entrega> loadTodos(int id){
		return dao.loadTodos(id);
	}
	
	public boolean entregaAvalida(int id){
		return dao.entregaAvalida(id);
	}
	
	//Passando uma lista verrifca se a entrega foi avalida, devolvendo uma lista somente com as entregas avalidas
	public ArrayList<Entrega> beenEvaluated(ArrayList<Entrega> listaTodasEntregas){
		ArrayList<Entrega> listaAvaliados = new ArrayList<>();
		
		EntregaService es = new EntregaService();
		
		for(int i = 0; i < listaTodasEntregas.size(); i++) {
			
			//carrega o metodo que verifica no banco se a entrega esta vinculada a uma avaliaxao
			int id = listaTodasEntregas.get(i).getId();
			boolean verificacao = es.entregaAvalida(id);
			
			//se a verificacao retornar true significa que encontrou uma avaliacao vinculada a uma entrega
			//adicionando o item a lista de avaliados e removendo da listaTodasEntregas
			if(verificacao) {
				listaAvaliados.add(listaTodasEntregas.get(i));
				listaTodasEntregas.remove(i);
			}
		}
		return listaAvaliados;
	}
	
	
}
