import model.Turma;
import service.TurmaService;

public class TurmaTest {

	public static void main(String[] args) {
		TurmaService ts = new TurmaService();
		
		for (Turma t : ts.mostrarAno()) {
			System.out.println("Semestre: " + t.getSemestreLetivo());
			System.out.println("Ano: " + t.getAno());
			System.out.println();
		}
	}
}
