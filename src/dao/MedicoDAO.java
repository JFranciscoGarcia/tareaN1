package dao;

import java.util.List;

import entidades.Medico;

public class MedicoDAO extends DAO{

	public Medico crear(Medico medico) throws Exception{
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
	public Medico get(Long idMedico) throws Exception{
		try {
			Medico medico = (Medico) getSession().createQuery(
					"from Medico medico where medico.idMedico = :idMedico")
					.setParameter("idMedico", idMedico)
					.uniqueResult();

			return medico;
		} catch (Exception e) {			
			throw e;
		}
	}
	public void save(Medico medico) throws Exception{
		try {
			begin();
			getSession().update(medico);
			commit();
		} catch (Exception e) {
			rollback();
			throw e;
		}
		
	}
	public void delete(Medico medico) throws Exception{
		try {
			begin();
			getSession().delete(medico);
			commit();
		} catch (Exception e) {
			rollback();
			throw	e;
		}
	}
	@SuppressWarnings("unchecked")
	public List<Medico> list() throws Exception{
		try{
			List<Medico> list  =  getSession().createQuery("from Medico").list();
				
			return list; 
		}catch(Exception e){
			throw e;
		}
	}
}
