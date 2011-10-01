package dao;

import entidades.Hospital;

public class HospitalDAO extends DAO {

	public Hospital get(Long idHospital) throws Exception{
		try {
			Hospital hospital = (Hospital) getSession().createQuery(
					"from Hospital hospital where hospital.idHospital = :idHospital")
					.setParameter("idHospital", idHospital)
					.uniqueResult();

			return hospital;
		} catch (Exception e) {			
			throw e;
		}
	}
}
