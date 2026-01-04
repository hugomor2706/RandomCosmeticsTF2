package programas;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import datos.DatosCosmeticos;
import datos.DatosCosmeticos.Cosmetico;
import utils.Calidad;
import utils.Clase;

public class RandomCosmeticSelector {

	public static List<Cosmetico> randomCosmeticSelector(Clase clase, Boolean espalda, Boolean pies, Boolean brazos, Boolean halloween) {
		DatosCosmeticos.leeCosmeticos();
		for(int i=0;i<3;i++) {
			DatosCosmeticos.cosmeticos.add(Cosmetico.of(DatosCosmeticos.cosmeticos.size(), "Vacio", Calidad.UNICA, false, false, List.of("all"), List.of("Ninguno")));
		}
		List<Cosmetico> cc = DatosCosmeticos.cosmeticos.stream().filter(x -> (x.clases().contains(clase.toString().toLowerCase())) || x.clases().contains("all")).toList();
		if(!halloween) cc = cc.stream().filter(x -> x.halloween().equals(false)).toList();
		Set<String> regs = new HashSet<>();
		if(espalda) regs.add("Espalda");
		if(pies) regs.add("Pies");
		if(brazos) regs.add("Brazos");
		List<Cosmetico> aux = new ArrayList<>();
		List<Cosmetico> res = new ArrayList<>();
		for(Cosmetico cosmetico:cc) {
			for(int i=0;i<DatosCosmeticos.getPuntuacion(cosmetico.id());i++) {
				aux.add(cosmetico);
			}
		}
		while(res.size()<3) {
			Random random = new Random();
			Integer top = random.nextInt(aux.size());
			Cosmetico cos = aux.get(top);
			if(!regs.stream().anyMatch(cos.regiones()::contains)) {
				res.add(cos);
				if(!cos.regiones().get(0).equals("Ninguno")) {
					regs.addAll(cos.regiones());
					for(String region:cos.regiones()) {
						if(region.equals("Gafas")) {
							regs.add("Cara");
							regs.add("Ojos");
							regs.add("Toda la cabeza");
						}
						if(region.equals("Cara")) {
							regs.add("Gafas");
							regs.add("Toda la cabeza");
						}
						if(region.equals("Ojos")) {
							regs.add("Cara");
						}
						if(region.equals("Toda la cabeza")) {
							regs.add("Sombrero");
							regs.add("Cara");
							regs.add("Gafas");
						}
						if(region.equals("Sombrero")) {
							regs.add("Toda la cabeza");
						}
					}
				}
			}
			aux = aux.stream().filter(x -> !x.id().equals(cos.id())).toList();
		}
		return res;
	}

}
