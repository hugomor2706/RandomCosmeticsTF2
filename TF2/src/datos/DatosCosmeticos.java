package datos;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import utils.Calidad;

public class DatosCosmeticos {

	public static List<Cosmetico> cosmeticos = new ArrayList<>();

	public static void leeCosmeticos() {
		cosmeticos.clear();
		try (
			InputStream lines = DatosCosmeticos.class.getResourceAsStream("/ficheros/cosmeticos.txt");
			Scanner scanner = new Scanner(lines)){
			if (lines == null) {
	            throw new IllegalArgumentException("El archivo de los cosméticos no se encuentra");
	        }
			Integer id = 0;
			while(scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String s[] = line.split(";");
				String nombre = s[0].strip();
				Calidad calidad = Calidad.valueOf(s[1].strip().toUpperCase());
				Boolean modificado = (s[2].strip().equals("si"))?true:false;
				Boolean halloween = (s[3].strip().equals("halloween"))?true:false;
				List<String> clases = new ArrayList<>();
				for(String c:s[4].strip().split(",")) {
					clases.add(c.toLowerCase());
				}
				List<String> regiones = new ArrayList<>();
				for(String region:s[5].strip().split(",")) {
					regiones.add(region);
				}
				cosmeticos.add(Cosmetico.of(id,nombre, calidad, modificado,halloween,clases,regiones));
				id++;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		//toConsole();
	}

	public static int getPuntuacion(Integer id) {
		int res = 0;
		Calidad calidad = cosmeticos.get(id).calidad();
		int cal = 0;
		switch(calidad) {
		case UNICA:cal=1;break;
		case CLASICA:cal=2;break;
		case GENUINA:cal=3;break;
		case RARA:cal=4;break;
		case INUSUAL:cal=5;break;
		case EMBRUJADA:cal=6;break;
		case COLECCIONISTA:cal=7;break;
		}
		res+=cal;
		if(cosmeticos.get(id).modificado()) {
			res++;
		}
		return res;
	}

	public static void toConsole() {
		System.out.println(cosmeticos);
	}

	public static record Cosmetico(Integer id,String nombre, Calidad calidad, Boolean modificado, Boolean halloween, List<String> clases, List<String> regiones) {
		public static Cosmetico of(Integer id,String nombre, Calidad calidad, Boolean modificado, Boolean halloween, List<String> clases, List<String> regiones) {
			return new Cosmetico(id,nombre,calidad,modificado,halloween,clases,regiones);
		}
	}

	public static void main(String[] args) {
		leeCosmeticos();
		//System.out.println(getPuntuacion(1));
	}
}
