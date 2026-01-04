package tests;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import datos.DatosCosmeticos.Cosmetico;
import programas.RandomCosmeticSelector;
import utils.Clase;

public class CosmeticosAleatoriosApp {
	
	@SuppressWarnings("deprecation")
	public CosmeticosAleatoriosApp() {
		JFrame frame = new JFrame();
		
		JCheckBox espalda = new JCheckBox("Arma equipada en la espalda"); //Para armas secundarias del Soldier, del Pyro, del Demoman y del Sniper
		JCheckBox pies = new JCheckBox("Arma equipada en los pies"); //Para armas secundarias del Soldier y del Demoman
		JCheckBox brazos = new JCheckBox("Arma equipada en los brazos"); //Para armas secundarias del Demoman y del Engineer
		JCheckBox halloween = new JCheckBox("Halloween"); //Si se admiten cosméticos de Halloween
		JButton generar = new JButton("Generar"); //Botón para generar cosméticos
		JTextArea cosmeticos = new JTextArea("Cosméticos"); //Cuadro de texto donde se generan los cosméticos
		cosmeticos.setEditable(false); //El usuario no puede escribir
		cosmeticos.setFocusable(false); //El usuario no lo puede seleccionar
		
		JComboBox<Object> clases = new JComboBox<>(); //Lista para seleccionar clase
		List<Clase> clasesAux = List.of(Clase.SCOUT,Clase.SOLDIER,Clase.PYRO,Clase.DEMOMAN,Clase.HEAVY,Clase.ENGINEER,Clase.MEDIC,Clase.SNIPER,Clase.SPY);
		clases.addItem("Selecciona una clase");
		for(int i=0;i<clasesAux.size();i++) {
			clases.addItem(clasesAux.get(i));
		}
		
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
		panel.setLayout(new GridLayout(7,2)); //x filas, y columnas
		panel.add(clases); //ComboBox de las clases
		
		clases.addActionListener(e -> { //Texto por defecto "Selecciona una clase". Cuando se elige una clase, se borra este texto
			Object selected = clases.getSelectedItem();
			if(selected instanceof String && selected.equals("Selecciona una clase")) {
				return;
			}
			clases.removeItem("Selecciona una clase");
			Clase clase = Clase.SCOUT;
			if(selected instanceof Clase) {
				clase = (Clase) selected;
			} else { //Si no se selcciona una clase no pasa nada
				
			}
			switch(clase) { //Siempre que se seleccione una clase, se desmarcan las casillas (menos Halloween) y se cambia el texto dependiendo de la clase
			case SOLDIER:
				cosmeticos.setText("Cosméticos");
				brazos.setSelected(false);
				panel.remove(brazos);
				espalda.setLabel("Equipado Estandarte de Ánimo, Refuerzo del Batallón, Conchistador o Saltador B.A.S.E.");
				espalda.setSelected(false);
				panel.add(espalda);
				pies.setLabel("Equipadas Botas Blindadas o Aplastacabezas");
				pies.setSelected(false);
				panel.add(pies);
				panel.add(halloween);
				panel.add(generar);
				panel.add(cosmeticos);
				generar.addActionListener(g -> {
					cosmeticos.setText("");
					for(Cosmetico c:RandomCosmeticSelector.randomCosmeticSelector(Clase.SOLDIER,espalda.isSelected(),pies.isSelected(),brazos.isSelected(),halloween.isSelected())) {
						if(c.modificado()) {
							cosmeticos.setText(cosmeticos.getText() + c.nombre()+", de Calidad "+ c.calidad()+". Modificado?: SI\r\n");
						} else {
							cosmeticos.setText(cosmeticos.getText() + c.nombre()+", de Calidad "+ c.calidad()+". Modificado?: NO\r\n");
						}
					}
				});
				break;
			case PYRO:
				cosmeticos.setText("Cosméticos");
				pies.setSelected(false);
				panel.remove(pies);
				brazos.setSelected(false);
				panel.remove(brazos);
				espalda.setLabel("Equipado Propulsor Térmico");
				espalda.setSelected(false);
				panel.add(espalda);
				panel.add(halloween);
				panel.add(generar);
				panel.add(cosmeticos);
				generar.addActionListener(g -> {
					cosmeticos.setText("");
					for(Cosmetico c:RandomCosmeticSelector.randomCosmeticSelector(Clase.PYRO,espalda.isSelected(),pies.isSelected(),brazos.isSelected(),halloween.isSelected())) {
						if(c.modificado()) {
							cosmeticos.setText(cosmeticos.getText() + c.nombre()+", de Calidad "+ c.calidad()+". Modificado?: SI\r\n");
						} else {
							cosmeticos.setText(cosmeticos.getText() + c.nombre()+", de Calidad "+ c.calidad()+". Modificado?: NO\r\n");
						}
					}
				});
				break;
			case DEMOMAN:
				cosmeticos.setText("Cosméticos");
				espalda.setLabel("Equipado Saltador B.A.S.E.");
				espalda.setSelected(false);
				panel.add(espalda);
				pies.setLabel("Equipado Patapalo o Babuchas de Alí Babá");
				pies.setSelected(false);
				panel.add(pies);
				brazos.setLabel("Equipado Targe de Carga, Escudo Espléndido o Tuerceolas");
				brazos.setSelected(false);
				panel.add(brazos);
				panel.add(halloween);
				panel.add(generar);
				panel.add(cosmeticos);
				generar.addActionListener(g -> {
					cosmeticos.setText("");
					for(Cosmetico c:RandomCosmeticSelector.randomCosmeticSelector(Clase.DEMOMAN,espalda.isSelected(),pies.isSelected(),brazos.isSelected(),halloween.isSelected())) {
						if(c.modificado()) {
							cosmeticos.setText(cosmeticos.getText() + c.nombre()+", de Calidad "+ c.calidad()+". Modificado?: SI\r\n");
						} else {
							cosmeticos.setText(cosmeticos.getText() + c.nombre()+", de Calidad "+ c.calidad()+". Modificado?: NO\r\n");
						}
					}
				});
				break;
			case ENGINEER:
				cosmeticos.setText("Cosméticos");
				espalda.setSelected(false);
				panel.remove(espalda);
				pies.setSelected(false);
				panel.remove(pies);
				brazos.setLabel("Equipado Forajido");
				brazos.setSelected(false);
				panel.add(brazos);
				panel.add(halloween);
				panel.add(generar);
				panel.add(cosmeticos);
				generar.addActionListener(g -> {
					cosmeticos.setText("");
					for(Cosmetico c:RandomCosmeticSelector.randomCosmeticSelector(Clase.ENGINEER,espalda.isSelected(),pies.isSelected(),brazos.isSelected(),halloween.isSelected())) {
						if(c.modificado()) {
							cosmeticos.setText(cosmeticos.getText() + c.nombre()+", de Calidad "+ c.calidad()+". Modificado?: SI\r\n");
						} else {
							cosmeticos.setText(cosmeticos.getText() + c.nombre()+", de Calidad "+ c.calidad()+". Modificado?: NO\r\n");
						}
					}
				});
				break;
			case SNIPER:
				cosmeticos.setText("Cosméticos");
				brazos.setSelected(false);
				panel.remove(brazos);
				pies.setSelected(false);
				panel.remove(pies);
				espalda.setLabel("Equipado Caparazumbador, Escudo de Darwin o Campista Comodón");
				espalda.setSelected(false);
				panel.add(espalda);
				panel.add(halloween);
				panel.add(generar);
				panel.add(cosmeticos);
				generar.addActionListener(g -> {
					cosmeticos.setText("");
					for(Cosmetico c:RandomCosmeticSelector.randomCosmeticSelector(Clase.SNIPER,espalda.isSelected(),pies.isSelected(),brazos.isSelected(),halloween.isSelected())) {
						if(c.modificado()) {
							cosmeticos.setText(cosmeticos.getText() + c.nombre()+", de Calidad "+ c.calidad()+". Modificado?: SI\r\n");
						} else {
							cosmeticos.setText(cosmeticos.getText() + c.nombre()+", de Calidad "+ c.calidad()+". Modificado?: NO\r\n");
						}
					}
				});
				break;
			default:
				cosmeticos.setText("Cosméticos");
				espalda.setSelected(false);
				panel.remove(espalda);
				pies.setSelected(false);
				panel.remove(pies);
				brazos.setSelected(false);
				panel.remove(brazos);
				panel.add(halloween);
				panel.add(generar);
				panel.add(cosmeticos);
				generar.addActionListener(g -> {
					cosmeticos.setText("");
					for(Cosmetico c:RandomCosmeticSelector.randomCosmeticSelector((Clase) selected,espalda.isSelected(),pies.isSelected(),brazos.isSelected(),halloween.isSelected())) {
						if(c.modificado()) {
							cosmeticos.setText(cosmeticos.getText() + c.nombre()+", de Calidad "+ c.calidad()+". Modificado?: SI\r\n");
						} else {
							cosmeticos.setText(cosmeticos.getText() + c.nombre()+", de Calidad "+ c.calidad()+". Modificado?: NO\r\n");
						}
					}
				});
				break;
			}
			panel.revalidate();
			panel.repaint();
		});
		
		generar.addActionListener(g -> {
		    Object sel = clases.getSelectedItem();
		    if (!(sel instanceof Clase)) return;

		    Clase claseSeleccionada = (Clase) sel;

		    cosmeticos.setText("");
		    for (Cosmetico c : RandomCosmeticSelector.randomCosmeticSelector(
		            claseSeleccionada,
		            espalda.isSelected(),
		            pies.isSelected(),
		            brazos.isSelected(),
		            halloween.isSelected())) {

		        cosmeticos.append(
		            c.nombre() + ", de Calidad " + c.calidad() +
		            ". Modificado?: " + (c.modificado() ? "SI" : "NO") + "\n"
		        );
		    }
		});

				
		frame.add(panel,BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Cosméticos Aleatorios");
		frame.pack();
		frame.setSize(600,400);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new CosmeticosAleatoriosApp();
	}

}
