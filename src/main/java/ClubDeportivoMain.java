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

		ClubDeportivo club = new ClubDeportivo("Club");
        String[] datosFutbol = {"Fut", "Futbol", "10", "0", "10.0"};
        String[] datosBaloncesto = {"Bal", "Baloncesto", "20", "0", "20.0"};


        //Act: añadimos el grupo al club con los datos del array
        club.anyadirActividad(datosFutbol);
        //club.anyadirActividad(datosBaloncesto);

		System.out.println(club);







		
	}
}
