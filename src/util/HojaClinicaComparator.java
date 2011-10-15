package util;

import java.util.Comparator;

import entidades.HojaClinica;

public class HojaClinicaComparator implements Comparator<HojaClinica> {

	@Override
	public int compare(HojaClinica o1, HojaClinica o2) {

		if(o1.getFechaIngreso().compareTo(o2.getFechaIngreso())>0){
			return 1;
		}
		
		if(o1.getFechaIngreso().compareTo(o2.getFechaIngreso())<0){
			return -1;
		}
		
		return 0;
	}

}
