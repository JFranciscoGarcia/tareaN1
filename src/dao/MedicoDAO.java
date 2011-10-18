package dao;

import java.util.List;

import javax.persistence.NoResultException;

import entidades.Medico;

public class MedicoDAO extends GenericDAO<Medico> {

	
	public Medico traerPorNumColegiado(Integer numColegiado) throws Exception {
		try {
			Medico medico=null;
			begin();
			medico = (Medico) getSession().createQuery(
							"from Medico medico where medico.num = :numColegiado")
					.setParameter("numColegiado", numColegiado).uniqueResult();
			
			commit();
			return medico;
			
		}catch (NoResultException ex){
			return null;
			
		} catch (Exception ex) {
			throw ex;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Medico> traerPorEspecialidad(Integer idEspecialidad) throws Exception {
		try {
			List<Medico> listaMedicos=null;
			begin();
			listaMedicos = (List<Medico>) getSession().createQuery(
							"from Medico medico where medico.especialidad.idEspecialidad = :idEspecialidad")
					.setParameter("idEspecialidad", idEspecialidad).list();
			
			commit();
			return listaMedicos;
			
		}catch (NoResultException ex){
			return null;
			
		} catch (Exception ex) {
			throw ex;
		}
	}

}
