package controller;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import com.opencsv.CSVReader;
import com.sun.corba.se.impl.orbutil.graph.Node;

import model.data_structures.IQueue;
import model.data_structures.IStack;
import model.data_structures.Iterador;
import model.data_structures.Nodo;
import model.data_structures.Queue;
import model.data_structures.Stack;
import model.vo.VODaylyStatistic;
import model.vo.VOMovingViolations;
import view.MovingViolationsManagerView;

public class Controller {

	private MovingViolationsManagerView view;

	/**
	 * Cola donde se van a cargar los datos de los archivos
	 */
	private IQueue<VOMovingViolations> movingViolationsQueue;

	/**
	 * Pila donde se van a cargar los datos de los archivos
	 */
	private IStack<VOMovingViolations> movingViolationsStack;


	public Controller() {
		view = new MovingViolationsManagerView();

		//TODO, inicializar la pila y la cola
		movingViolationsQueue = null;
		movingViolationsStack = null;
	}

	public void run() {
		Scanner sc = new Scanner(System.in);
		boolean fin = false;

		while(!fin)
		{
			view.printMenu();

			int option = sc.nextInt();

			switch(option)
			{
			case 1:
				view.printMensage("Ingrese el número del cuatrimestre que desea cargar");
				int num=sc.nextInt();
				this.loadMovingViolations(num);
				System.out.println("Hay "+movingViolationsQueue.size()+" elementos en cola y "+""+movingViolationsStack.size()+" en pila");
				break;

			case 2:
				IStack<Integer> lista= verificarObjectId(); 
				if(lista.isEmpty()){
					view.printMensage("No hay OBJECTID repetidos");
					break;
				}else{

					break;
				}
			case 3:
				view.printMensage("Ingrese el dia y la fecha en formato AAAA-MM-DD,HH:MM:SS.000Z");;
				String ingreso=sc.next();
				String fecha=ingreso.split(",")[0];
				String hora=ingreso.split(",")[1];
				IQueue<VOMovingViolations> lista1=consultarPorFechaYHora(fecha,hora);
				view.printfechaHora(lista1);
				break; 
			case 4:
				view.printMensage("Ingrese el código de violación");
				String codigo=sc.next();
				double[] arreglo=fineAmtPromedio(codigo);
				String mensaje="El promedio con accidentalidad es: "+arreglo[0]+" y sin accidentalidad es: "+ arreglo[1] ;
				view.printMensage(mensaje);
				break;
			case 5: 
				view.printMensage("Ingrese el ADDRESS_ID y la fecha en formato: ADDRESS_ID, AAAA-MM-DD, AAAA-MM-DD");
				String linea=sc.next();
				int addresid=Integer.parseInt(linea.split(",")[0]);
				String inicio=linea.split(",")[1];
				String fina=linea.split(",")[2];
				view.printAddresId(consultarPorDireccion(addresid, inicio, fina));
				break;
			case 6: 
				view.printMensage("Ingrese el promedio en formato: val1,val2");
				String entrada=sc.next();
				int in=Integer.parseInt(entrada.split(",")[0]);
				int fin2=Integer.parseInt(entrada.split(",")[1]);
				
				break; 
			case 7:
				view.printMensage("Ingrese el rango del valor total pagado en formato: val1,val2");
				String linea1=sc.next();
				int val1=Integer.parseInt(linea1.split(",")[0]);
				int val2=Integer.parseInt(linea1.split(",")[1]);
				view.printTotalPagado(consultarPorTotalPagado(val1, val2));
				break; 
			case 8: 
				view.printMensage("Ingrese la hora inicial y final en formato: HH:MM:SS.000Z,HH:MM:SS.000Z");
				String linea11=sc.next();
				String horainicio=linea11.split(",")[0];
				String horafinal=linea11.split(",")[1];
				
				break; 
			case 9:	
				fin=true;
				sc.close();
				break;
			}
		}
	}



	public void loadMovingViolations(int num) {
		movingViolationsQueue=new Queue<VOMovingViolations>();
		movingViolationsStack= new Stack<VOMovingViolations>();
		String[] nombresArchivos=new String[12];
		nombresArchivos[0]="."+File.separator+"data"+File.separator+"Moving_Violations_Issued_in_January_2018_ordered.csv";
		nombresArchivos[1]="."+File.separator+"data"+File.separator+"Moving_Violations_Issued_in_February_2018_ordered.csv";
		nombresArchivos[2]="."+File.separator+"data"+File.separator+"Moving_Violations_Issued_in_March_2018.csv";
		nombresArchivos[3]="."+File.separator+"data"+File.separator+"Moving_Violations_Issued_in_April_2018.csv";
		nombresArchivos[4]="."+File.separator+"data"+File.separator+"Moving_Violations_Issued_in_May_2018.csv";
		nombresArchivos[5]="."+File.separator+"data"+File.separator+"Moving_Violations_Issued_in_June_2018.csv";
		nombresArchivos[6]="."+File.separator+"data"+File.separator+"Moving_Violations_Issued_in_July_2018.csv";
		nombresArchivos[7]="."+File.separator+"data"+File.separator+"Moving_Violations_Issued_in_August_2018.csv";
		nombresArchivos[8]="."+File.separator+"data"+File.separator+"Moving_Violations_Issued_in_September_2018.csv";
		nombresArchivos[9]="."+File.separator+"data"+File.separator+"Moving_Violations_Issued_in_October_2018.csv";
		nombresArchivos[10]="."+File.separator+"data"+File.separator+"Moving_Violations_Issued_in_November_2018.csv";
		nombresArchivos[11]="."+File.separator+"data"+File.separator+"Moving_Violations_Issued_in_December_2018.csv";
		CSVReader reader=null;
		int inicio=-1; 
		if(num==1){
			inicio=0; 
		}else if(num==2){
			inicio=4;
		}else{
			inicio=8; 
		}
		for(int i=inicio; i<inicio+4;i++){
			try{
				reader=new CSVReader(new FileReader(nombresArchivos[i]));
				String[] linea=reader.readNext();
				linea=reader.readNext();
				while(linea!=null){
					movingViolationsStack.push(new VOMovingViolations(Integer.parseInt(linea[0]), linea[2], linea[13], Double.parseDouble(linea[9]), linea[12], linea[15], linea[14], Double.parseDouble(linea[8]),Integer.parseInt(linea[3])));
					movingViolationsQueue.enqueue(new VOMovingViolations(Integer.parseInt(linea[0]), linea[2], linea[13], Double.parseDouble(linea[9]), linea[12], linea[15], linea[14], Double.parseDouble(linea[8]),Integer.parseInt(linea[3])));
					linea=reader.readNext();
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				if(reader!=null){
					try{
						reader.close();
					}catch(IOException e){
						e.printStackTrace();	
					}
				}
			}
		}
	}

	public IQueue <VODaylyStatistic> getDailyStatistics () {
		IQueue<VODaylyStatistic> lista= new Queue<VODaylyStatistic>();
		Iterador<VOMovingViolations> iter=(Iterador<VOMovingViolations>) movingViolationsQueue.iterator();
		if(iter.hasNext()) {
			VOMovingViolations actual=(VOMovingViolations)iter.next();
			String fecha=actual.getTicketIssueDate().split("T")[0];
			int numInfracciones=0;
			int numAccidentes=0;
			double numafintotal=0;
			int vez=0; 
			while(vez<movingViolationsQueue.size()) {
				while(actual.getTicketIssueDate().split("T")[0].equals(fecha)) {
					numafintotal+=actual.getFINEAMT();
					numInfracciones++;
					if(actual.getAccidentIndicator().equals("Yes")) {
						numAccidentes++;
					}
					vez++; 
					actual=iter.next();
				}
				lista.enqueue(new VODaylyStatistic(fecha, numAccidentes, numInfracciones, numafintotal));
				numAccidentes=0;
				numafintotal=0;
				numInfracciones=0;
				fecha=actual.getTicketIssueDate().split("T")[0];
			}
			return lista; 
		}
		return lista;
	}

	public IStack <VOMovingViolations> nLastAccidents(int n) {
		IStack<VOMovingViolations> lista=new Stack<>();
		Iterador<VOMovingViolations> iter=(Iterador<VOMovingViolations>) movingViolationsStack.iterator();
		VOMovingViolations actual=iter.next();
		while(n>0&&iter.hasNext()){
			if(actual.getAccidentIndicator().equals("Yes")){
				lista.push(actual);
				n--;
			}
			actual=iter.next();
		}

		return lista;
	}

	public IStack<Integer> verificarObjectId (){
		IStack<Integer> retornar= new Stack<>();
		Iterador<VOMovingViolations> iter=(Iterador<VOMovingViolations>)movingViolationsStack.iterator();
		VOMovingViolations actual=iter.next();
		Nodo<VOMovingViolations> primero=movingViolationsStack.darPrimero();
		int objectid=actual.objectId();
		while(iter.hasNext()){
			boolean repetido=false; 
			while(primero.darSiguiente()!=null&&!repetido){
				if(primero.darSiguiente().darElemento().objectId()==objectid){
					retornar.push(objectid);
					repetido=true; 
				}
				primero=primero.darSiguiente();
			}
			objectid=iter.next().objectId();
		}
		return retornar; 
	}

	public IQueue<VOMovingViolations> consultarPorFechaYHora(String pFecha, String pHora){
		IQueue<VOMovingViolations> retornar= new Queue<>();
		Iterador<VOMovingViolations> iter=(Iterador<VOMovingViolations>) movingViolationsQueue.iterator();
		VOMovingViolations actual=iter.next();
		int contador=0; 
		String fecha=actual.getTicketIssueDate().split("T")[0];
		String hora=actual.getTicketIssueDate().split("T")[1];
		while(contador<movingViolationsQueue.size()){
			if(fecha.equals(pFecha)&&hora.equals(pHora)){
				retornar.enqueue(actual);
			}
			actual=iter.next();
			fecha=actual.getTicketIssueDate().split("T")[0];
			hora=actual.getTicketIssueDate().split("T")[1];
			contador++;
		}
		return retornar;
	}

	public double[] fineAmtPromedio(String pViolation){
		double[] retornar= new double[2];
		Iterador<VOMovingViolations> iter= (Iterador<VOMovingViolations>) movingViolationsStack.iterator();
		VOMovingViolations actual=iter.next();
		int sumyes=0;
		int sumNo=0; 
		int cantYes=0; 
		int cantNo=0;
		while(iter.hasNext()){
			if(actual.getViolationCode().equals(pViolation)){
				if(actual.getAccidentIndicator().equals("Yes")){
					sumyes+=actual.getFINEAMT();
					cantYes++; 
				}else{
					sumNo+=actual.getFINEAMT();
					cantNo++; 
				}
			}
			actual=iter.next();
		}
		retornar[0]=(sumyes/cantYes);
		retornar[1]=(sumNo/cantNo);
		return retornar; 
	}

	public IStack <VOMovingViolations> consultarPorDireccion(int pDirección,String FechaIn, String FechaFin){
		IStack <VOMovingViolations> retornar= new Stack<>();
		Iterador<VOMovingViolations> iter= (Iterador<VOMovingViolations>) movingViolationsStack.iterator();
		VOMovingViolations actual=iter.next();
		while(iter.hasNext()){
			if(actual.getAdressId()==pDirección){
				{
					if(actual.getTicketIssueDate().split("T")[0].compareTo(FechaIn)>0&&actual.getTicketIssueDate().split("T")[0].compareTo(FechaFin)<0){
						retornar.push(actual);
					}
				}
			}
		}
		return retornar; 
	}
	
	public IQueue<VOMovingViolations> consultarPorPromedioFINEAMT(int val1, int val2){
		IQueue<VOMovingViolations> retornar= new Queue<>();
		
		return retornar; 
	}
	
	public  IStack <VOMovingViolations> consultarPorTotalPagado(int val1, int val2){
		 IStack <VOMovingViolations> retornar= new Stack <VOMovingViolations>();
		 Iterador<VOMovingViolations> iter= (Iterador<VOMovingViolations>) movingViolationsStack.iterator();
		 VOMovingViolations actual= iter.next();
		 while(iter.hasNext()){
			 if(actual.getTotalPaid()>=val1 && actual.getTotalPaid()<=val2){
				 retornar.push(actual);
			 }
			 actual=iter.next();
		 }
		 return retornar; 
	}
}
