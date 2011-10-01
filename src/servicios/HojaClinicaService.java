package servicios;

import java.util.Date;

import dao.HojaClinicaDAO;

import entidades.HojaClinica;
import entidades.Hospital;
import entidades.Medico;

public class HojaClinicaService {

	public HojaClinicaDAO hojaClinicaDAO = new HojaClinicaDAO();
	
	public void  nuevaHojaClinica(Hospital hospital, Medico medico, Date fechaIngreso,String sintomas, String diagnostico, String tratamiento, Boolean encamado, Boolean alta, Date fechaAlta){
		
		try{
			
			HojaClinica nuevaHojaClinica= new HojaClinica();
			
			nuevaHojaClinica.setFechaIngreso(fechaIngreso);
			nuevaHojaClinica.setSintomas(sintomas);
			nuevaHojaClinica.setDiagnostico(diagnostico);
			nuevaHojaClinica.setTratamiento(tratamiento);
			nuevaHojaClinica.setEncamado(encamado);
			nuevaHojaClinica.setAlta(alta);
			nuevaHojaClinica.setFechaAlta(fechaAlta);
			
			nuevaHojaClinica.setMedico(medico);
			nuevaHojaClinica.setHospital(hospital);
			
			hojaClinicaDAO.insertar(nuevaHojaClinica);
		
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
