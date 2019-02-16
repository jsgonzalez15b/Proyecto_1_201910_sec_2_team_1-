package model.data_structures;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;
import model.vo.VOMovingViolations;


/**
 * Esta es la clase usada para verificar que los mÃ©todos de la clase Queue estÃ©n correctamente implementados
 */
public class TestStack extends TestCase{
	
	// -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Stack para probar pop
     */
    private Stack<VOMovingViolations> poper;

    /**
     * Stack para probar push
     */
    private Stack<VOMovingViolations> pusher;

    /**
     * Stack para probar isEmpty()
     */
    private Stack<VOMovingViolations> vaciio;
    
    /**
     * Elemento 1
     */
    private VOMovingViolations pElemento1;

    /**
     * Elemento 2
     */
    private VOMovingViolations pElemento2;

    /**
     * Elemento 3
     */
    private VOMovingViolations pElemento3;
    
    /**
     * Elemento 4
     */
    private VOMovingViolations pElemento4;
    
    /**
     * Elemento 5
     */
    private VOMovingViolations pElemento5;

    /**
     * Recuperacion 1
     */
    private VOMovingViolations pRecuperado1;

    /**
     * Recuperacion 2
     */
    private VOMovingViolations pRecuperado2;
   
    // -----------------------------------------------------------------
    // MÃ©todos
    // -----------------------------------------------------------------

   
	@Before
	public void setUpStack() throws Exception
	{
		//Inicializacion de poper, pusher y vaciio
		poper=new Stack<>();
		pusher=new Stack<>();
		vaciio=new Stack<>();
		
		//Inicializacion de elementos 
		System.out.println("Codigo de iniciacion");
		pElemento1 = new VOMovingViolations(1, "Bogota", "2018-02-13", 0, "123000", "Licence", "0001", 1453.2);
		pElemento2 = new VOMovingViolations(2, "Bogota", "2018-02-13", 0, "123001", "Drunk", "0002", 1450.2);
		pElemento3 = new VOMovingViolations(3, "Bogota", "2018-02-13", 0, "123002", "Asshole", "9999", 1451.2);
		pElemento4 = new VOMovingViolations(4, "Bogota", "2018-02-14", 0, "123003", "Speed", "0007", 1451.2);
		pElemento5 = new VOMovingViolations(5, "Bogota", "2018-02-14", 0, "123004", "Licence", "0001", 1451.2);
		System.out.println(pElemento1.getAccidentIndicator());
		
		poper.push(pElemento1);
		poper.push(pElemento2);
		poper.push(pElemento3);
		poper.push(pElemento4);
		poper.push(pElemento5);

		
		pusher.push(pElemento1);
		pusher.push(pElemento2);
		pusher.push(pElemento3);
		
		//Inicializacion de recuperados 
		pRecuperado1= poper.pop();
		pRecuperado2= poper.pop();

	}
	
	@Test
	public void test() 
	{
		try {
			setUpStack();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//OPCIONES PARA IMPRIMIR EN PANTALLA CADA OBJETO
//		System.out.println(pElemento1);
//		System.out.println(pElemento2);
//		System.out.println(pElemento3);
//		
//		System.out.println(poper.primero.darElemento().objectId());
//		System.out.println(poper.primero.darSiguiente().darElemento().objectId());
//		System.out.println(poper.primero.darSiguiente().darSiguiente().darElemento().objectId());
		
	System.out.println(pusher);
//		System.out.println(pusher.primero.darSiguiente().darElemento().objectId());
//		System.out.println(pusher.primero.darSiguiente().darSiguiente().darElemento().objectId());
//
//		System.out.println(poper.primero.darSiguiente().darSiguiente().darSiguiente().darElemento().objectId());
//		System.out.println(poper.primero.darSiguiente().darSiguiente().darSiguiente().darSiguiente().darElemento().objectId());
//		
//		System.out.println(poper.ultimo.darElemento().objectId());
		
		//Pruebas para identificador OBJECTID
		//pruebas inicializacion de pusher
//		assertEquals( "El tamaño no es el esperado", 3 , pusher.size() );
		assertEquals( "El primero no es igual", 3 , pusher.primero.darElemento().objectId() );
		assertEquals( "El segundo no es igual o el metodo dar siguiente no esta bien implementado", 2 , pusher.primero.darSiguiente().darElemento().objectId() );
		assertEquals( "El tercero no es igual o el metodo dar siguiente no esta bien implementado", 1 , pusher.primero.darSiguiente().darSiguiente().darElemento().objectId() );
		//pruebas inicializacion poper
		assertEquals( "El primero no es igual", 3, poper.primero.darElemento().objectId() );
		assertEquals( "El segundo no es igual", 2 , poper.primero.darSiguiente().darElemento().objectId() );
		assertEquals( "El ultimo no es igual", 1 , poper.primero.darSiguiente().darSiguiente().darElemento().objectId() );
		//prueba del método isEmpty() - vaciio, pusher, poper
		assertEquals("La lista no fue evaluada como vacia",true,vaciio.isEmpty());
		assertEquals("La lista fue evaluada como vacia",false,pusher.isEmpty());
		assertEquals("La lista no fue evaluada como vacia",false,poper.isEmpty());

		//pruebas de recuperacion poper
		assertEquals( "El recuperado 1 no es el esperado", pElemento5.objectId(), pRecuperado1.objectId() );
		assertEquals( "El recuperado 2 no es el esperado", pElemento4.objectId(), pRecuperado2.objectId() );
		
		//pop completo, isEmpty() y push nuevamente
		VOMovingViolations tercerElemento = poper.pop();
		VOMovingViolations segundoElemento = poper.pop();
		VOMovingViolations primerElemento = poper.pop();
		assertEquals( "El tercer recuperado no es el tercer elemento", pElemento3.objectId(), tercerElemento.objectId() );
		assertEquals( "El cuarto recuperado no es el segundo elemento", pElemento2.objectId(), segundoElemento.objectId() );
		assertEquals( "El quinto recuperado no es el primer elemento", pElemento1.objectId(), primerElemento.objectId() );
		assertEquals( "La lista no fue evaluada como vacia",true,poper.isEmpty());
		poper.push(pElemento3);
		assertEquals( "El metodo push no funciono despues de vaciar el poper", 3 , poper.primero.darElemento().objectId() );
		
		
	}

}
