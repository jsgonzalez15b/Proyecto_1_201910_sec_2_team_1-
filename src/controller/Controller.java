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
				IQueue<VODaylyStatistic> dailyStatistics = this.getDailyStatistics();
				view.printDailyStatistics(dailyStatistics);
				break;

			case 3:
				view.printMensage("Ingrese el nÃºmero de infracciones a buscar");
				int n = sc.nextInt();

				IStack<VOMovingViolations> violations = this.nLastAccidents(n);
				view.printMovingViolations(violations);
				break;

			case 4:	
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
					movingViolationsStack.push(new VOMovingViolations(Integer.parseInt(linea[0]), linea[2], linea[13], Double.parseDouble(linea[9]), linea[12], linea[15], linea[14], Double.parseDouble(linea[8]) ));
					movingViolationsQueue.enqueue(new VOMovingViolations(Integer.parseInt(linea[0]), linea[2], linea[13], Double.parseDouble(linea[9]), linea[12], linea[15], linea[14], Double.parseDouble(linea[8])));
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
}
