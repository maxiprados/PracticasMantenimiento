import clubdeportivo.ClubDeportivo;
import clubdeportivo.ClubException;
import clubdeportivo.Grupo;

public class ClubDeportivoMain {
	public static void main(String[] args) throws ClubException {
		/*String [] grupo1 = {"123A","Kizomba","10","10","25.0"};
		
		try {
			ClubDeportivo club = new ClubDeportivo("UMA",1);
			Grupo pilates = new Grupo("456B","Pilates",8,5,50.0);
			club.anyadirActividad(grupo1);
			club.anyadirActividad(pilates);
			System.out.println(club);			
			System.out.println("Ingresos: " + club.ingresos());
			
		} catch (ClubException e) {
			System.out.println(e.getMessage());
		}*/
		String name="Club";
        ClubDeportivo club = new ClubDeportivo(name);
        String codigo = "Futbol";
        String actividad = "Deporte";
        int nplazas = 10;
        int matriculados = 0;
        double tarifa = 10.0;
        Grupo grupo = new Grupo(codigo, actividad, nplazas, matriculados, tarifa);

		

        //Act: Añadimos el grupo
        club.anyadirActividad(grupo);
		System.out.println(club);

		//Assert: Comprobamos que la insercion se ha ejecutado correctamente (empleando el toString que re)






		
	}
}
