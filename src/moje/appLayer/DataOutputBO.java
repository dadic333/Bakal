package moje.appLayer;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import moje.JpaController.DataoutputJpaController;
import moje.JpaController.exceptions.NonexistentEntityException;
import moje.entity.Dataoutput;

/**
 *
 * @author Honza
 */
public class DataOutputBO {
  
    private static EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("a33PU");

  static void createDataOutput(Dataoutput out) {
    DataoutputJpaController cont = new DataoutputJpaController(emf);
    cont.create(out);
  }
  
  static void deleteDataOutput(Dataoutput output) {
    deleteDataOutput(output.getId());
  }

  static void deleteDataOutput(int id) {
    DataoutputJpaController cont = new DataoutputJpaController(emf);
      try {
        cont.destroy(id);
      } catch (NonexistentEntityException ex) {
        Logger.getLogger(DataOutputBO.class.getName()).log(Level.SEVERE, null, ex);
      }
  }

  public static List<Dataoutput> getAllDataOutputs() {
    DataoutputJpaController cont = new DataoutputJpaController(emf);
    List<Dataoutput> ret = cont.findDataoutputEntities();
    return ret;
  }

  public static Dataoutput editDataOutput(int id, String note, Integer phoneNumber, String mac) {
    DataoutputJpaController cont = new DataoutputJpaController(emf);
    Dataoutput newDataOutput = new Dataoutput();
    Dataoutput oldDataOutput = cont.findDataoutput(id);
    newDataOutput.setId(oldDataOutput.getId());
    newDataOutput.setDatadevout(oldDataOutput.getDatadevout());
    newDataOutput.setDeviceId(oldDataOutput.getDeviceId());
    
    if(note==null){
      newDataOutput.setNote(oldDataOutput.getNote());
    } else{ newDataOutput.setNote(note);}

/*    if((Integer)phoneNumber==null){               // Pokud nebudu chtít akceptovat "null" vstupy   - toto odzávorkuji
      newDataOutput.setPhonenumber(oldDataOutput.getPhonenumber());
    } else { newDataOutput.setPhonenumber(phoneNumber);}
*/
    newDataOutput.setPhonenumber(phoneNumber);      // Pokud nebudu chtít akceptovat "null" vstupy  - toto vymažu
    
    if(mac==null){
      newDataOutput.setMac(oldDataOutput.getMac());
    } else { newDataOutput.setMac(mac);}
    
      try {
        cont.edit(newDataOutput);
      } catch (Exception ex) {
        Logger.getLogger(DataOutputBO.class.getName()).log(Level.SEVERE, null, ex);
      }
    return newDataOutput;
  }

    public static List<Dataoutput> findPhoneNumber(Integer phoneNumber) {
        DataoutputJpaController cont = new DataoutputJpaController(emf);
        List<Dataoutput> ret = cont.findAllDataOutputsByPhoneNumber(phoneNumber);
        return ret; 
    }
  
}
