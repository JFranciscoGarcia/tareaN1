package dao;

import entidades.Asegurado;

public class AseguradoDAO extends GenericDAO<Asegurado> {


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
}
