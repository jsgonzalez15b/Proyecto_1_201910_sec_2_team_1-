package model.data_structures;



import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;
import model.vo.VOMovingViolations;


/**
 * Esta es la clase usada para verificar que los métodos de la clase Queue estén correctamente implementados
 */
public class TestQueue extends TestCase{
	
	// -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Queue para probar enqueue
     */
    private Queue<VOMovingViolations> enqueuer;

    /**
     * Queue para probar dequeue
     */
    private Queue<VOMovingViolations> dequeuer;

    /**
     * Queue para eliminar 1 nodo
     */
    private Queue<VOMovingViolations> dequeuer1;
    
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
     * Elemento 3
     */
    private VOMovingViolations pElemento4;

    /**
     * Recuperación 1
     */
    private VOMovingViolations pRecuperado1;

    /**
     * Recuperación 2
     */
    private VOMovingViolations pRecuperado2;
    
    /**
     * Recuperación 1
     */
    private VOMovingViolations pRecuperado1a;
    
    private Stack<Integer> pila; 

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

   
	@Before
	public void setUp() throws Exception
	{
		//Inicialización de enqueuer, dequeuer y dequeuer1
		enqueuer=new Queue<>();
		dequeuer=new Queue<>();
		dequeuer1=new Queue<>();
		
		//Inicialización de elementos y enqueue
		System.out.println("Codigo de iniciacion");
		pElemento1 = new VOMovingViolations(1, "Bogota", "2018-02-13", 0, "123000", "Licence", "0001", 1453.2);
		pElemento2 = new VOMovingViolations(2, "Bogota", "2018-02-13", 0, "123001", "Drunk", "0002", 1450.2);
		pElemento3 = new VOMovingViolations(3, "Bogota", "2018-02-13", 0, "123002", "Asshole", "9999", 1451.2);
		pElemento4 = new VOMovingViolations(4, "Bogota", "2018-02-14", 0, "123003", "Speed", "0007", 1451.2);
		System.out.println(pElemento1.getAccidentIndicator());
		
		enqueuer.enqueue(pElemento1);
		enqueuer.enqueue(pElemento2);
		enqueuer.enqueue(pElemento3);
		enqueuer.enqueue(pElemento4);

		
		dequeuer.enqueue(pElemento1);
		dequeuer.enqueue(pElemento2);
		dequeuer.enqueue(pElemento3);
		
		dequeuer1.enqueue(pElemento1);
		dequeuer1.enqueue(pElemento2);
		dequeuer1.enqueue(pElemento3);
		
		//Inicialización de recuperados 
		pRecuperado1= dequeuer1.dequeue();
		pRecuperado2= dequeuer1.dequeue();
		
		pRecuperado1a= dequeuer.dequeue();
	}
	
	@Test
	public void test() 
	{
		//IMPRIMO REFERENCIAS Y OBJETOS EN LA COLA... PERO HONESTAMENTE NO SE QUE PUEDE ESTAR MAL...
		System.out.println(pElemento1);
		System.out.println(pElemento2);
		System.out.println(pElemento3);
		
		System.out.println(enqueuer.primero.darElemento().objectId());
		System.out.println(enqueuer.primero.darSiguiente().darElemento().objectId());
		System.out.println(enqueuer.primero.darSiguiente().darAnterior().darElemento().objectId());
		System.out.println(enqueuer.primero.darSiguiente().darSiguiente().darElemento().objectId());

		System.out.println(enqueuer.primero.darSiguiente().darSiguiente().darSiguiente().darElemento().objectId());
		System.out.println(enqueuer.primero.darSiguiente().darSiguiente().darSiguiente().darSiguiente().darElemento().objectId());
		
		System.out.println(enqueuer.ultimo.darElemento().objectId());
		//pruebas de enqueuer
		assertEquals( "El primero no es igual", 1 , enqueuer.primero.darElemento().objectId() );
		assertEquals( "El segundo no es igual o el método dar siguiente no esta bien implementado", 2 , enqueuer.primero.darSiguiente().darElemento().objectId() );
		assertEquals( "El segundo no es igual o el método dar anterior no esta bien implementado", 3 , enqueuer.primero.darAnterior().darElemento().objectId() );
		//pruebas de dequeuer
		assertEquals( "El primero no es igual", 2, dequeuer.primero.darElemento().objectId() );
		assertEquals( "El segundo(último) no es igual", 3 , dequeuer.ultimo.darElemento().objectId() );
		//pruebas de dequeuer1
		assertEquals( "El primero no es este término", 3 , dequeuer1.primero );
		assertEquals( "El ultimo debería ser igual al primero", 3 , dequeuer1.ultimo );

		//pruebas de recuperación de dequeuers
		assertEquals( "El recuperado 1a no es el esperado", pElemento1, pRecuperado1a );
		
		assertEquals( "El recuperado 1 no es el esperado", pElemento1, pRecuperado1 );
		assertEquals( "El recuperado 2 no es el esperado", pElemento2, pRecuperado2 );
		
	}

}
