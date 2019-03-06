package view;

import model.data_structures.IQueue;
import model.data_structures.IStack;
import model.vo.VODaylyStatistic;
import model.vo.VOMovingViolations;

public class MovingViolationsManagerView 
{
	public MovingViolationsManagerView() {
		
	}
	
	public void printMenu() {
		System.out.println("---------ISIS 1206 - Estructuras de datos----------");
		System.out.println("---------------------Taller 3----------------------");
		System.out.println("1. Cree una nueva coleccion de infracciones en movimiento");
		System.out.println("2. Verificar que no hay ObjectId repetidos entre las facturas");
		System.out.println("3. Dar infracciones por fecha y hora de inicio");
		System.out.println("4. Dar promedio de FINEAMT por Codigo de violación");
		System.out.println("5. Dar infracciones por address_ID y fecha");
		System.out.println("6. Consultar por rango de FINEAMT");
		System.out.println("7. Consultar infracciones por cantidad pagada");
		System.out.println("8. Consultar infracciones por hora inicial y final (Ordenada ascendentemente por VIOLATIONDESC.)");
		System.out.println("9. Salir");
		System.out.println("Digite el nï¿½mero de opciï¿½n para ejecutar la tarea, luego presione enter: (Ej., 1):");
		
	}
	
//	public void printDailyStatistics(IQueue<VODaylyStatistic> dailyStatistics) {
//		System.out.println("Se encontraron "+ dailyStatistics.size() + " elementos");
//		int vez=0; 
//		for (VODaylyStatistic dayStatistic : dailyStatistics) 
//		{
//			System.out.println(dayStatistic.darFecha()+"- accidentes:"+dayStatistic.darAccidente()+",	infracciones:"	+dayStatistic.darInfracciones()+",	multas totales:"+dayStatistic.darTotalFineAMT() );;
//			vez++; 
//			if(vez==dailyStatistics.size()){
//				break; 
//			}
//		}
//	}
	public void printOBJECTID(IQueue<Integer> lista) {
		System.out.println("Se encontraron "+ lista.size() + " elementos");
		int vez=0; 
		for (Integer Id : lista) 
		{
			System.out.println(Id+"");;
			vez++; 
			if(vez==lista.size()){
				break; 
			}
		}
	}
	
	public void printMovingViolations(IStack<VOMovingViolations> violations) {
		System.out.println("Se encontraron "+ violations.size() + " elementos");
		for (VOMovingViolations violation : violations) 
		{
			System.out.println(violation.objectId() + " " 
								+ violation.getTicketIssueDate() + " " 
								+ violation.getLocation()+ " " 
								+ violation.getViolationDescription());
		}
	}
	
	public void printMensage(String mensaje) {
		System.out.println(mensaje);
	}

	public void printfechaHora(IQueue<VOMovingViolations> lista1) {
		System.out.println("Se encontraron "+ lista1.size() + " elementos");
		int vez=0; 
		for(VOMovingViolations vo: lista1){
			System.out.println(vo.objectId()+","+vo.getTicketIssueDate());
			vez++;
			if(vez==lista1.size()){
				break; 
			}
		}
		
	}

	public void printAddresId(IStack<VOMovingViolations> lista1) {
		System.out.println("Se encontraron "+ lista1.size() + " elementos");
		int vez=0;
		for(VOMovingViolations vo: lista1){
			System.out.println(vo.objectId()+","+vo.getTicketIssueDate()+","+vo.getAdressId());
			vez++;
			if(vez==lista1.size()){
				break;
			}
		}
	}

	public void printTotalPagado(IStack<VOMovingViolations> lista1) {
		System.out.println("Se encontraron "+ lista1.size() + " elementos");
		int vez=0;
		for(VOMovingViolations vo: lista1){
			System.out.println(vo.objectId()+","+vo.getTicketIssueDate()+","+vo.getTotalPaid());
			vez++;
			if(vez==lista1.size()){
				break;
			}
		}		
	}
}
