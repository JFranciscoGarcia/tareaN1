package dao;

import java.util.List;

import entidades.Especialidad;

public class EspecialidadDAO extends DAO {

	public Especialidad crear(Especialidad especialidad) throws Exception {
		try {
			begin();
			getSession().save(especialidad);
			commit();
			return especialidad;
		} catch (Exception e) {
			rollback();
			throw e;
		}
	}

	public Especialidad traer(Long idEspecialidad) throws Exception {
		try {
			Especialidad Especialidad = (Especialidad) getSession()
					.createQuery(
							"from Especialidad especialidad where especialidad.idEspecialidad = :idEspecialidad")
					.setParameter("idEspecialidad", idEspecialidad).uniqueResult();

			return Especialidad;
		} catch (Exception e) {
			throw e;
		}
	}

	public void guardar(Especialidad especialidad) throws Exception {
		try {
			begin();
			getSession().update(especialidad);
			commit();
		} catch (Exception e) {
			rollback();
			throw e;
		}

	}

	public void eliminar(Especialidad especialidad) throws Exception {
		try {
			begin();
			getSession().delete(especialidad);
			commit();
		} catch (Exception e) {
			rollback();
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Especialidad> list() throws Exception {
		try {
			List<Especialidad> list = getSession().createQuery("from Especialidad").list();

			return list;
		} catch (Exception e) {
			throw e;
		}
	}
}
