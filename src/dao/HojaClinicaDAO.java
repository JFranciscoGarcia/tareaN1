package dao;

import java.util.List;

import entidades.HojaClinica;

public class HojaClinicaDAO extends DAO {

	public HojaClinica insertar(HojaClinica hojaClinica) throws Exception {
		try {
			begin();
			getSession().save(hojaClinica);
			commit();
			return hojaClinica;
		} catch (Exception e) {
			rollback();
			throw e;
		}
	}

	public HojaClinica traer(Long idHojaClinica) throws Exception {
		try {
			HojaClinica hojaClinica = (HojaClinica) getSession()
					.createQuery(
							"from HojaClinica hojaClinica where hojaClinica.idHojaClinica = :idhojaClinica order by hojaClinica.fechaIngreso desc")
					.setParameter("idhojaClinica", idHojaClinica)
					.uniqueResult();

			return hojaClinica;
		} catch (Exception e) {
			throw e;
		}
	}

	public void guardar(HojaClinica hojaClinica) throws Exception {
		try {
			begin();
			getSession().update(hojaClinica);
			commit();
		} catch (Exception e) {
			rollback();
			throw e;
		}

	}

	public void eliminar(HojaClinica hojaClinica) throws Exception {
		try {
			begin();
			getSession().delete(hojaClinica);
			commit();
		} catch (Exception e) {
			rollback();
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	public List<HojaClinica> list() throws Exception {
		try {
			List<HojaClinica> list = getSession().createQuery(
					"from HojaClinica").list();

			return list;
		} catch (Exception e) {
			throw e;
		}
	}
}
