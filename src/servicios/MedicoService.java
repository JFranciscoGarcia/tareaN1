package servicios;

import java.util.List;

import dao.MedicoDAO;
import entidades.Medico;

public class MedicoService  {

	MedicoDAO medicoDao;

	public List<Medico> traerATodosLosGiles() {
		try{
			//return medicoDao.list();
			
			MedicoDAO a = new MedicoDAO ();
			return a.list();
		
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
	
	
}
