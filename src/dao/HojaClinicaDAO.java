package dao;

import java.util.List;

import entidades.HojaClinica;
import entidades.Medico;

public class HojaClinicaDAO extends DAO {
	
	public HojaClinica insertar(HojaClinica hojaClinica) throws Exception{
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
	public HojaClinica obtener(Integer idHojaClinica) throws Exception{
		try {
			HojaClinica hojaClinica = (HojaClinica) getSession().createQuery(
					"from HojaClinica hojaClinica where hojaClinica.idHojaClinica = :idhojaClinica")
					.setParameter("idhojaClinica", idHojaClinica)
					.uniqueResult();

			return hojaClinica;
		} catch (Exception e) {			
			throw e;
		}
	}
	public void save(HojaClinica hojaClinica) throws Exception{
		try {
			begin();
			getSession().update(hojaClinica);
			commit();
		} catch (Exception e) {
			rollback();
			throw e;
		}
		
	}
	public void delete(HojaClinica hojaClinica) throws Exception{
		try {
			begin();
			getSession().delete(hojaClinica);
			commit();
		} catch (Exception e) {
			rollback();
			throw	e;
		}
	}
	@SuppressWarnings("unchecked")
	public List<HojaClinica> list() throws Exception{
		try{
			List<HojaClinica> list  =  getSession().createQuery("from HojaClinica").list();
				
			return list; 
		}catch(Exception e){
			throw e;
		}
	}
}
