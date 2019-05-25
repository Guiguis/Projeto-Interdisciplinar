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
		
		ArrayList<Grupo> lstGrupoNaoAvaliado = new ArrayList<Grupo>();
		for(int i = 0; i < listaGrupo.size(); i++) {
			Grupo grupo = listaGrupo.get(i);
			Banca banca = pbs.loadBancaGrupo(grupo.getId());
	
			//verifica se existe alguma banca vinculada com esse grupo
			if(!banca.getGrupo()) System.out.println(lstGrupoNaoAvaliado.add(grupo));
		}
		
		return lstGrupoNaoAvaliado;
	}
}
