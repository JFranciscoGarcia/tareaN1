package dao;

import java.util.List;

import javax.persistence.NoResultException;

import entidades.Medico;

public class MedicoDAO extends DAO {

	public Medico crear(Medico medico) throws Exception {
		try {
			begin();
			getSession().save(medico);
			commit();
			return medico;
		} catch (Exception e) {
			rollback();
			throw e;
		}
	}

	public Medico traer(Long idMedico) throws Exception {
		try {
			Medico medico = (Medico) getSession()
					.createQuery(
							"from Medico medico where medico.idMedico = :idMedico")
					.setParameter("idMedico", idMedico).uniqueResult();

			return medico;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public Medico traerPorNumColegiado(Integer numColegiado) throws Exception {
		try {
			Medico medico = (Medico) getSession().createQuery(
							"from Medico medico where medico.num = :numColegiado")
					.setParameter("numColegiado", numColegiado).uniqueResult();
			
			return medico;
		}catch (NoResultException ex){
			return null;
			
		} catch (Exception ex) {
			throw ex;
		}
	}

	public void guardar(Medico medico) throws Exception {
		try {
			begin();
			getSession().update(medico);
			commit();
		} catch (Exception e) {
			rollback();
			throw e;
		}

	}

	public void eliminar(Medico medico) throws Exception {
		try {
			begin();
			getSession().delete(medico);
			commit();
		} catch (Exception e) {
			rollback();
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Medico> list() throws Exception {
		try {
			List<Medico> list = getSession().createQuery("from Medico").list();

			return list;
		} catch (Exception e) {
			throw e;
		}
	}
}
