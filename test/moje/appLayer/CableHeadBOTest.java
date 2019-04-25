/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moje.appLayer;

import java.util.List;
import moje.entity.Cablehead;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Honza
 */
public class CableHeadBOTest {
  
  public CableHeadBOTest() {
  }
  
  @BeforeClass
  public static void setUpClass() {
    
  }
  
  @AfterClass
  public static void tearDownClass() {
  }
  
  @Before
  public void setUp() {
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of createNewCaleHeadAndCHOutputs method, of class CableHeadBO.
   */
  @Test
  public void testCreateNewCaleHeadAndCHOutputs() {
    System.out.println("createNewCaleHeadAndCHOutputs");
    String name = "";
    String building = "";
    String note = "";
    int outputCout = 0;
    Cablehead expResult = null;
    Cablehead result = CableHeadBO.createNewCaleHeadAndCHOutputs(name, building, note, outputCout);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getCableheadByID method, of class CableHeadBO.
   */
  @Test
  public void testGetCableheadByID() {
    System.out.println("getCableheadByID");
    int id = 0;
    Cablehead expResult = null;
    Cablehead result = CableHeadBO.getCableheadByID(id);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getAllCableHeads method, of class CableHeadBO.
   */
  @Test
  public void testGetAllCableHeads() {
    System.out.println("getAllCableHeads");
    List<Cablehead> expResult = null;
    List<Cablehead> result = CableHeadBO.getAllCableHeads();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of deleteCableHeadAndOutpustByCableHeadID method, of class CableHeadBO.
   */
  @Test
  public void testDeleteCableHeadAndOutpustByCableHeadID() {
    System.out.println("deleteCableHeadAndOutpustByCableHeadID");
    int id = 0;
    CableHeadBO.deleteCableHeadAndOutpustByCableHeadID(id);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }
  
}
