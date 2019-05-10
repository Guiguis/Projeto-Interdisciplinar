package service;

import java.util.ArrayList;

import dao.GrupoDAO;
import model.Banca;
import model.Grupo;
import model.Turma;

public class GrupoService {

	GrupoDAO dao;
	
	public GrupoService() {
		dao = new GrupoDAO();
	}
	
	public Grupo load(int id) { 
		return dao.load(id);
	}
	
	public ArrayList<Grupo> loadGrupoByTurma(int idTurma) {
		return dao.loadGrupoByTurma(idTurma);
	}
	
	public ArrayList<Grupo> carrega() {
		return dao.carrega();
	}
	
	//Verifica se o grupo ja foi avaliado pela banca
	public ArrayList<Grupo> verificaAvaliado(ArrayList<Grupo> listaGrupo){
		
		ProfessorBancaService pbs = new ProfessorBancaService();
		
		for(int i = 0; i < listaGrupo.size(); i++) {
			int idGrupo = listaGrupo.get(i).getId();
			Banca banca = pbs.loadBancaGrupo(idGrupo);
			//verifica se existe alguma banca vinculada com esse grupo
			if(banca.getId() > 0) {
				listaGrupo.remove(i);
			}
		}
		return listaGrupo;
	}
	
	
}
