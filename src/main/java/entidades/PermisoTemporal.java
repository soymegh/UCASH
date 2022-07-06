package entidades;

import java.util.ArrayList;

public class PermisoTemporal {
	public static boolean flag;
	public static boolean temporalFlag = true; 
	
	public boolean checkPermisson(int companyValue, int accountingPeriod, int currency) {
		ArrayList<Integer> permissionsFlags = new ArrayList<Integer>();
		
		permissionsFlags.add(companyValue);
		permissionsFlags.add(accountingPeriod);
		permissionsFlags.add(currency);
		
		for(Integer flag: permissionsFlags) {
			if(flag > 0 && flag != null) {
				PermisoTemporal.flag = true; 
			}else {
				PermisoTemporal.flag = false;
				break; 
			}
		}
		
		return PermisoTemporal.flag; 
	}
}
