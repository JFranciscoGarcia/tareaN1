import java.util.Date;

import dao.HospitalDAO;
import dao.MedicoDAO;
import entidades.Hospital;
import entidades.Medico;

import servicios.HojaClinicaService;
import servicios.MedicoService;



public class Main {

	
	
	public static void main(String argv[]){
		
		
		try{
		HojaClinicaService hojaClinicaService = new HojaClinicaService();
		
		MedicoDAO medicoDAO = new MedicoDAO();
		HospitalDAO hospitalDAO= new HospitalDAO();
		
		
		//Busco a un medico 
		Medico medico= medicoDAO.get(new Long(1));
		//Busco un hospital
		Hospital hospital= hospitalDAO.get(new Long(1));
		
		//Creo una nueva hoja clinica
		hojaClinicaService.nuevaHojaClinica(hospital, medico,new Date(),"enfermo","toy re bien!!!","reposo", true, false, new Date());
		
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
}
