package service;

import java.io.Serializable;
import java.util.ArrayList;

import dao.ProfessorBancaDAO;
import model.Aluno;
import model.Banca;
import model.Grupo;
import model.Professor;
import model.ProfessorBanca;

public class ProfessorBancaService implements Serializable{

	ProfessorBancaDAO dao;
	
	public ProfessorBancaService() {
		dao = new ProfessorBancaDAO();
	}
	
	public void createProfessorBanca(ArrayList<ProfessorBanca> listaProfessorBanca) {
		for(int i = 0; i < listaProfessorBanca.size(); i++) {
			dao.createBanca(listaProfessorBanca.get(i));
		}
	}
	
	public void updateProfessorBanca(ProfessorBanca professorBanca) {
		dao.updateProfessorBanca(professorBanca);
	}
	
	public void deleteProfessorBanca(ProfessorBanca professorBanca) {
		dao.deleteBanca(professorBanca);
	}
	
	public ProfessorBanca loadProfessorBanca(int id) {
		return dao.loadProfessorBanca(id);
	}
	
	//----
	public String loadTemaGrupo(int idGrupo) {
		return dao.loadTemaGrupo(idGrupo);
	}
	
	//--
	public ArrayList<Aluno> loadAlunosGrupoBanca(int idGrupo) {
		return dao.loadAlunosGrupoBanca(idGrupo);
	}
	
	//--
	public Professor loadOrientadorGrupo(int idGrupo) {
		return dao.loadOrientadorGrupo(idGrupo);
	}
	
	public Banca loadBancaGrupo(int idGrupo) {
		return dao.loadBancaGrupo(idGrupo);
	}
	
	public ArrayList<Professor> loadProfessoresBanca(int idBanca) {
		return dao.loadProfessoresBanca(idBanca);
	}
	
	//Retorna uma lista com os grupos que o usuario logado participou da banca
	
	public ArrayList<Grupo> gruposParticipados(ArrayList<Grupo> lstGrupos, Professor professorLogado){
		ArrayList<Grupo> listaGrupoParticipados = new ArrayList<>();
		
		for(int i = 0; i < lstGrupos.size(); i++) {
			
			//Verifica se o professor é o orientador do grupo
			if(lstGrupos.get(i).getOrientador().getId() == professorLogado.getId()) {
				listaGrupoParticipados.add(lstGrupos.get(i));
			}
			else {
				int idGrupo = lstGrupos.get(i).getId();
				ProfessorBancaService pbs = new ProfessorBancaService();
				Banca banca = pbs.loadBancaGrupo(idGrupo);
				ArrayList<Professor> listaProfessores = pbs.loadProfessoresBanca(banca.getId());
				
				//percore a lista de professores que participaram da banca e verifica se um deles é o professor logadp
				for(int j = 0; j < listaProfessores.size(); j++) {
					if(listaProfessores.get(j).getId() == professorLogado.getId()) {
						listaGrupoParticipados.add(lstGrupos.get(i));
					}
				}
				
			}
		}
		
		
		return listaGrupoParticipados;
	}
	
	
}
