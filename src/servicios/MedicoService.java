package servicios;

import dao.MedicoDAO;
import entidades.Medico;

public class MedicoService {

	MedicoDAO medicoDao;

	public Medico traerMedicoPorNumColegiado(Integer numeroColegiado)
			throws Exception {
		
		MedicoDAO medicoDao = new MedicoDAO();
		Medico medico = medicoDao.traerPorNumColegiado(numeroColegiado);

		return medico;
	}
}
