package dao;

import java.util.List;

import entidades.Asegurado;

public class AseguradoDAO extends DAO {

	public Asegurado insertar(Asegurado asegurado) throws Exception {
		try {

			begin();
			getSession().save(asegurado);
			commit();
			return asegurado;

		} catch (Exception e) {
			rollback();
			throw e;
		}
	}

	public Asegurado traer(Integer idAsegurado) throws Exception {
		try {
			Asegurado asegurado = (Asegurado) getSession()
					.createQuery(
							"from Asegurado asegurado where asegurado.idAsegurado = :idAsegurado")
					.setParameter("idAsegurado", idAsegurado).uniqueResult();

			return asegurado;
		} catch (Exception e) {
			throw e;
		}
	}

	public Asegurado traerPorNumeroAsegurado(Long numAsegurado)
			throws Exception {
		try {
			Asegurado asegurado = (Asegurado) getSession()
					.createQuery(
							"from Asegurado asegurado where asegurado.numSeguro = :numAsegurado")
					.setParameter("numAsegurado", numAsegurado).uniqueResult();

			return asegurado;
		} catch (Exception e) {
			throw e;
		}
	}

	public void guardar(Asegurado asegurado) throws Exception {
		try {
			begin();
			getSession().update(asegurado);
			commit();
		} catch (Exception e) {
			rollback();
			throw e;
		}

	}

	public void eliminar(Asegurado asegurado) throws Exception {
		try {
			begin();
			getSession().delete(asegurado);
			commit();
		} catch (Exception e) {
			rollback();
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Asegurado> list() throws Exception {
		try {
			List<Asegurado> list = getSession().createQuery("from Asegurado")
					.list();

			return list;
		} catch (Exception e) {
			throw e;
		}
	}
}
