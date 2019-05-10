package moje.appLayer;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import moje.JpaController.CabheadoutputJpaController;
import moje.JpaController.exceptions.NonexistentEntityException;
import moje.entity.Cabheadoutput;

/**
 *
 * @author Honza
 */
public class CabHeadOutputBO {
  
      private static EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("a33PU");

  public static void deleteOutputsWithoutCableHeadID() {
    CabheadoutputJpaController cont = new CabheadoutputJpaController(emf);
    List<Cabheadoutput> outL = cont.findAllCabHeadOutputsWithoutCableHeadID();
    for (Cabheadoutput cabheadoutput : outL) {
      try {
        cont.destroy(cabheadoutput.getId());
      } catch (NonexistentEntityException ex) {
        Logger.getLogger(CabHeadOutputBO.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }

  public static List<Cabheadoutput> findAllCabHeadOutputsWithoutCableHeadID() {
    CabheadoutputJpaController cont = new CabheadoutputJpaController(emf);
    List<Cabheadoutput> ret = cont.findAllCabHeadOutputsWithoutCableHeadID();
    return ret;
  }
  
  public static List<Cabheadoutput> findAllCabHeadOutputsByParameter(String attribute , String value){
    CabheadoutputJpaController cont = new CabheadoutputJpaController(emf);
    List<Cabheadoutput>ret = cont.findAllCabHeadOutputsByParameter(attribute, value);
    return ret;
  }

  public static List<Cabheadoutput> getAllCabHeadsOutputs() {
    CabheadoutputJpaController cont = new CabheadoutputJpaController(emf);
    return cont.findCabheadoutputEntities();
  }

  public static Cabheadoutput editCabHeadOutput(int id, String note, int phoneNumber) {
    CabheadoutputJpaController cont = new CabheadoutputJpaController(emf);
    Cabheadoutput newCabHeadOut = new Cabheadoutput();
    Cabheadoutput oldCabHeadOut = cont.findCabheadoutput(id);
    newCabHeadOut.setId(oldCabHeadOut.getId());
    newCabHeadOut.setCabheadout(oldCabHeadOut.getCabheadout());
    newCabHeadOut.setCableheadId(oldCabHeadOut.getCableheadId());
    //TEST PRÁZDNÝCH HODNOT Z FORMULÁŘE
//    note=null;                      // součást testu
//    Integer pomPhoneNumber=null;    // součást testu
    if(note==null){
      newCabHeadOut.setNote(oldCabHeadOut.getNote());
    } else{ newCabHeadOut.setNote(note);}
//    if(pomPhoneNumber==null){       // součást testu
    if((Integer)phoneNumber==null){  
      newCabHeadOut.setPhonenumber(oldCabHeadOut.getPhonenumber());
    } else { newCabHeadOut.setPhonenumber(phoneNumber);}
        try {
          cont.edit(newCabHeadOut);
        } catch (Exception ex) {
          Logger.getLogger(CabHeadOutputBO.class.getName()).log(Level.SEVERE, null, ex);
        }
    return newCabHeadOut;
  }
  
}
