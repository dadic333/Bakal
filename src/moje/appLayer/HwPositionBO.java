package moje.appLayer;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import moje.JpaController.HwpositionJpaController;
import moje.JpaController.exceptions.NonexistentEntityException;
import moje.entity.Hwposition;
import moje.entity.Telexchange;

/**
 *
 * @author Honza
 */
public class HwPositionBO {
  
  private static EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("a33PU");

  static void deleteHwPosition(Hwposition hwposition) {
    deleteHwPosition(hwposition.getId());
  }
  
  static void deleteHwPosition(int id) {
    HwpositionJpaController cont = new HwpositionJpaController(emf);
    try {
      cont.destroy(id);
    } catch (NonexistentEntityException ex) {
      Logger.getLogger(HwPositionBO.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public static List<Hwposition> getAllHwPositions() {
    HwpositionJpaController cont = new HwpositionJpaController(emf);
    return cont.findHwpositionEntities();
  }

  public static void addTelExchangeHwPositions(int telExchangeID, int count) {
    HwpositionJpaController cont = new HwpositionJpaController(emf);
    Telexchange telEx = TelExchangeBO.getTelexchangeByID(telExchangeID);
    List<Hwposition> oldHwList = telEx.getHwpositionList();
    for (int i = 0; i < count; i++) {
      Hwposition newHwPosition = new Hwposition();
      newHwPosition.setName("0");
      newHwPosition.setTelexchangeoutput(0);
      newHwPosition.setPhonenumber(0);
      newHwPosition.setNote("");
      newHwPosition.setPhonenumber(0);
      newHwPosition.setTechnologytype('n');
      newHwPosition.setTelechangeId(telEx);
      cont.create(newHwPosition);
    }
  }

  public static Hwposition editHwPosition(int id, String name, int output, int phoneNumber, String technologyType, String note) {
    HwpositionJpaController cont = new HwpositionJpaController(emf);
    Hwposition newHwPosition = new Hwposition();
    Hwposition oldHwPosition = cont.findHwposition(id);
    newHwPosition.setId(oldHwPosition.getId());
    newHwPosition.setTelexchangeoutput(oldHwPosition.getTelexchangeoutput());
    newHwPosition.setTelechangeId(oldHwPosition.getTelechangeId());
    //TEST PRÁZDNÝCH HODNOT Z FORMULÁŘE
//        name=null;                      // součást testu
//        Integer pomOutput = null;       // součást testu
//        Integer pomPhoneNumber=null;    // součást testu
//        technologyType=null;            // součást testu
//        note=null;                      // součást testu
    if(name==null){
      newHwPosition.setName(oldHwPosition.getName());
    } else { newHwPosition.setName(name);}
//        if(pomOutput==null){            // součást testu - Při testu toto odkrýt
    if((Integer)output==null){        //  - Při testu toto zakrýt
      newHwPosition.setTelexchangeoutput(oldHwPosition.getTelexchangeoutput());
    } else { newHwPosition.setTelexchangeoutput(output);}  
//        if(pomPhoneNumber==null){       // součást testu
    if((Integer)phoneNumber==null){   //  - Při testu toto zakrýt
      newHwPosition.setPhonenumber(oldHwPosition.getPhonenumber());
    } else { newHwPosition.setPhonenumber(phoneNumber);}
    if(technologyType==null){
      newHwPosition.setTechnologytype(oldHwPosition.getTechnologytype());
    } else { newHwPosition.setTechnologytype(technologyType.trim().charAt(0));}
    if(note==null){
      newHwPosition.setNote(oldHwPosition.getNote());
    } else{ newHwPosition.setNote(note);}
        try {
          cont.edit(newHwPosition);
        } catch (Exception ex) {
          Logger.getLogger(CabHeadOutputBO.class.getName()).log(Level.SEVERE, null, ex);
        }
    return newHwPosition;
  }
  
  
}
