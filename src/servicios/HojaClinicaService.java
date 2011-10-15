package servicios;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import dao.AseguradoDAO;
import dao.HojaClinicaDAO;
import dao.MedicoDAO;
import entidades.Asegurado;
import entidades.HojaClinica;
import entidades.Medico;

public class HojaClinicaService {

	public HojaClinicaDAO hojaClinicaDAO = new HojaClinicaDAO();
	public AseguradoDAO aseguradoDAO = new AseguradoDAO();
	public MedicoDAO medicoDAO = new MedicoDAO();

	public  void nuevaHojaClinica(Long numAsegurado, Date fechaIngreso,String sintomas) throws Exception {

		Asegurado asegurado = aseguradoDAO.traerPorNumeroAsegurado(numAsegurado);
		List<Medico> listaMedicos = medicoDAO.list();
		Medico medicoMenorCarga = (Medico)Collections.min(listaMedicos);
		
		HojaClinica nuevaHojaClinica = new HojaClinica();
		nuevaHojaClinica.setFechaIngreso(fechaIngreso);
		nuevaHojaClinica.setSintomas(sintomas);
		nuevaHojaClinica.setAsegurado(asegurado);
		nuevaHojaClinica.setMedico(medicoMenorCarga);
		
		hojaClinicaDAO.insertar(nuevaHojaClinica);
		
	}
	
	public void darAlta(Long idHojaClinica) throws Exception{
		
		HojaClinica hojaClinica= hojaClinicaDAO.traer(idHojaClinica);
		hojaClinica.setAlta(true);
		hojaClinicaDAO.guardar(hojaClinica);
	}
	
public void ingresarDiagnostico(Long idHojaClinica, String diagnostico, String tratamiento, boolean encamar) throws Exception{
		
		HojaClinica hojaClinica= hojaClinicaDAO.traer(idHojaClinica);
		hojaClinica.setDiagnostico(diagnostico);
		hojaClinica.setTratamiento(tratamiento);
		hojaClinica.setEncamado(encamar);
		
		if(encamar){
			hojaClinica.setAlta(false);
		}
		
		hojaClinicaDAO.guardar(hojaClinica);
	}
}
