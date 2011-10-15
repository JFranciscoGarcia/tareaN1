package servicios;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import util.Constantes;

import dao.AseguradoDAO;
import dao.HojaClinicaDAO;
import dao.MedicoDAO;
import entidades.Asegurado;
import entidades.HojaClinica;
import entidades.Medico;
import excepciones.LogicaNegocioException;

public class HojaClinicaService {

	public HojaClinicaDAO hojaClinicaDAO = new HojaClinicaDAO();
	public AseguradoDAO aseguradoDAO = new AseguradoDAO();
	public MedicoDAO medicoDAO = new MedicoDAO();

	public Medico nuevaHojaClinica(Long numAsegurado, Date fechaIngreso,String sintomas) throws Exception {

		Asegurado asegurado = aseguradoDAO.traerPorNumeroAsegurado(numAsegurado);
		
		if(asegurado==null){
			throw new LogicaNegocioException(Constantes.EXCEPCION_ASEGURADO_NO_ENCONTRADO);
		}
		
		List<Medico> listaMedicos = medicoDAO.list();
		Medico medicoMenorCarga = (Medico)Collections.min(listaMedicos);
		
		HojaClinica nuevaHojaClinica = new HojaClinica();
		nuevaHojaClinica.setFechaIngreso(fechaIngreso);
		nuevaHojaClinica.setSintomas(sintomas);
		nuevaHojaClinica.setAsegurado(asegurado);
		nuevaHojaClinica.setMedico(medicoMenorCarga);
		
		hojaClinicaDAO.insertar(nuevaHojaClinica);
		
		return medicoMenorCarga;
		
	}
	
	public void darAlta(Long idHojaClinica, Date fechaAlta) throws Exception{
		
		HojaClinica hojaClinica= hojaClinicaDAO.traer(idHojaClinica);
		
		if(hojaClinica==null){
			throw new LogicaNegocioException(Constantes.EXCEPCION_HOJA_CLINICA_NO_ENCONTRADO);
		}
		
		hojaClinica.setAlta(true);
		hojaClinica.setFechaAlta(fechaAlta);
		hojaClinicaDAO.guardar(hojaClinica);
	}
	
	public void ingresarDiagnostico(Long idHojaClinica, String diagnostico, String tratamiento, boolean encamar) throws Exception{
		
		HojaClinica hojaClinica= hojaClinicaDAO.traer(idHojaClinica);
		
		if(hojaClinica==null){
			throw new LogicaNegocioException(Constantes.EXCEPCION_HOJA_CLINICA_NO_ENCONTRADO);
		}
		
		hojaClinica.setDiagnostico(diagnostico);
		hojaClinica.setTratamiento(tratamiento);
		hojaClinica.setEncamado(encamar);
		
		if(encamar){
			hojaClinica.setAlta(false);
		}
		
		hojaClinicaDAO.guardar(hojaClinica);
	}

	public void asignarMedico(Long idMedico, Long idHojaClinica) throws Exception{
			
		HojaClinica hojaClinica= hojaClinicaDAO.traer(idHojaClinica);
		Medico medico= medicoDAO.traer(idMedico);
		
		if(hojaClinica==null){
			throw new LogicaNegocioException(Constantes.EXCEPCION_HOJA_CLINICA_NO_ENCONTRADO);
		}
		
		if(medico==null){
			throw new LogicaNegocioException(Constantes.EXCEPCION_MEDICO_NO_ENCONTRADO);
		}
		hojaClinica.setMedico(medico);
		hojaClinicaDAO.guardar(hojaClinica);
	}
}
