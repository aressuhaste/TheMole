/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.stijn.mole.dao;

import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Stijn Bouchier
 */
public class DataBaseManagerTest {
    
    public DataBaseManagerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getAllPeople method, of class DataBaseManager.
     */
    @Test
    public void testGetAllPeople() {
        System.out.println("getAllPeople");
        DataBaseManager instance = DataBaseManager.getInstance();
        int expResult = 25;
        List result = instance.getAllPeople();
        assertEquals(expResult, result.size());
    }
}
