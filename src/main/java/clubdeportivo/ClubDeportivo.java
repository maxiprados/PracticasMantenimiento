package clubdeportivo;

import java.util.StringJoiner;

public class ClubDeportivo {
	private String nombre;
	private int ngrupos;
	private Grupo[] grupos;
	private static final int TAM = 10;

	public ClubDeportivo(String nombre) throws ClubException {
		this(nombre, TAM);
	}

	public ClubDeportivo(String nombre, int n) throws ClubException {
		if (n <= 0) {
			throw new ClubException("ERROR: el club no puede crearse con un número de grupos 0 o negativo");
		}
		this.nombre = nombre;
		grupos = new Grupo[n];
	}

	private int buscar(Grupo g) {
		int i = 0;
		while (i < ngrupos && !g.equals(grupos[i])) {
			i++;
		}
		if (i == ngrupos) {
			i = -1;
		}
		return i;
	}

	public void anyadirActividad(String[] datos) throws ClubException {
		//No se comprueba que el array pasado por parametro tenga la longitud correcta -> FALLO
		if (datos.length < 5) {
			throw new ClubException("ERROR: el array de datos no tiene la longitud correcta");
		}
		
		
		
		try {
			int plazas = Integer.parseInt(datos[2]);
			int matriculados = Integer.parseInt(datos[3]);
			double tarifa = Double.parseDouble(datos[4]);
			Grupo g = new Grupo(datos[0], datos[1], plazas, matriculados, tarifa);
			anyadirActividad(g);
		} catch (NumberFormatException e) {
			throw new ClubException("ERROR: formato de número incorrecto");
		}
	}

	public void anyadirActividad(Grupo g) throws ClubException {
		//Aqui si metes mas grupos de los que has definido en el club hay un out of bounds
		if (g==null){ // ADDME: anaydido para comprobar los grupos nulos
			throw new ClubException("ERROR: el grupo es nulo");
		}
		int pos = buscar(g);
		//Si metes mas grupos de los que se han delimitado al principio en el constructor da IndexOutOfBounds-> FALLO
		if (pos==-1 && ngrupos == grupos.length){
			throw new ClubException("ERROR: tamaño maximo de grupos ha sido completado");
		}
		else if (pos == -1 && ngrupos <= grupos.length) { // El grupo es nuevo
			grupos[ngrupos] = g;
			ngrupos++;
		}else { // El grupo ya existe --> modificamos las plazas
			grupos[pos].actualizarPlazas(g.getPlazas());
		}
	}

	public int plazasLibres(String actividad) {
		int p = 0;
		int i = 0;
		while (i < ngrupos) {
			if (grupos[i].getActividad().equals(actividad)) {
				p += grupos[i].plazasLibres();
			}
			i++;
		}
		return p;
	}

	public void matricular(String actividad, int npersonas) throws ClubException {
		int plazas = plazasLibres(actividad);
		if (plazas < npersonas) {
			throw new ClubException("ERROR: no hay suficientes plazas libres para esa actividad en el club.");
		}
		int i = 0;
		while (i < ngrupos && npersonas > 0) {
			if (actividad.equals(grupos[i].getActividad())) {
				int plazasGrupo = grupos[i].plazasLibres();
				if (npersonas >= plazasGrupo) {
					grupos[i].matricular(plazasGrupo);
					npersonas -= plazasGrupo;
				} else {
					grupos[i].matricular(npersonas);
					//Habría que decrementar npersonas a 0 para salir del bucle y no seguir añadiendo personas "ficticias" -> FALLO
					npersonas = 0;
				}
			}
			i++;
		}
	}

	public double ingresos() {
		double cantidad = 0.0;
		int i = 0;
		while (i < ngrupos) {
			cantidad += grupos[i].getTarifa() * grupos[i].getMatriculados();
			i++;
		}
		return cantidad;
	}

	public String toString() {
		StringJoiner sj = new StringJoiner(", ", "[ ", " ]");
		int i = 0;
		while (i < ngrupos) {
			sj.add(grupos[i].toString());
			i++;
		}
		return nombre + " --> " + sj.toString();
	}
}
