package servicios;

import dao.MedicoDAO;
import entidades.Medico;

public class MedicoService {

	MedicoDAO medicoDao=new MedicoDAO();

	public Medico traerMedicoPorNumColegiado(Integer numeroColegiado)
			throws Exception {
		
		Medico medico = medicoDao.traerPorNumColegiado(numeroColegiado);
		
		return medico;
	}
}
