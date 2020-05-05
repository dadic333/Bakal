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

  public static void deleteHwPosition(Hwposition hwposition) {
    deleteHwPosition(hwposition.getId());
  }
  
  public static void deleteHwPosition(int id) {
    HwpositionJpaController cont = new HwpositionJpaController(emf);
        Hwposition hwPosition = HwPositionBO.findHwPositionById(id);
        Integer telExchangeID = hwPosition.getTelechangeId().getId();
    try {

      cont.destroy(id);

    } catch (NonexistentEntityException ex) {
      Logger.getLogger(HwPositionBO.class.getName()).log(Level.SEVERE, null, ex);
    }
    Telexchange telEx = TelExchangeBO.getTelExchangeByID(telExchangeID);
    telEx.setOutputcount(telEx.getHwpositionList().size()); //přípočet výstupu TelExchange.outputcount
    telEx = TelExchangeBO.editTelExchangeByTelExchange(telEx); //provedení úpravy TelExchange.outputcount v databázi
  }

  public static List<Hwposition> getAllHwPositions() {
    HwpositionJpaController cont = new HwpositionJpaController(emf);
    return cont.findHwpositionEntities();
  }

  public static void addTelExchangeHwPositions(int telExchangeID, int count) {
    HwpositionJpaController cont = new HwpositionJpaController(emf);
    Telexchange telEx = TelExchangeBO.getTelExchangeByID(telExchangeID);
    for (int i = 0; i < count; i++) {
      Hwposition newHwPosition = new Hwposition();
      //newHwPosition.setName("0"); // není potřeba
      newHwPosition.setTelexchangeoutput(i+1);
      //newHwPosition.setPhonenumber(0); // není potřeba
      newHwPosition.setNote("neuvedeno");
      newHwPosition.setTechnologytype('n');
      newHwPosition.setTelechangeId(telEx);
      cont.create(newHwPosition);
    }
    telEx = null;
    telEx = TelExchangeBO.getTelExchangeByID(telExchangeID);
    telEx.setOutputcount(telEx.getHwpositionList().size()); //přípočet výstupu TelExchange.outputcount
    telEx = TelExchangeBO.editTelExchangeByTelExchange(telEx); //provedení úpravy TelExchange.outputcount v databázi
  }

  public static Hwposition editHwPosition(int id, String name, Integer output, Integer phoneNumber, String technologyType, String note) {
    HwpositionJpaController cont = new HwpositionJpaController(emf);
    Hwposition newHwPosition = new Hwposition();
    Hwposition oldHwPosition = cont.findHwposition(id);
    newHwPosition.setId(oldHwPosition.getId());
    newHwPosition.setTelexchangeoutput(oldHwPosition.getTelexchangeoutput());
    newHwPosition.setTelechangeId(oldHwPosition.getTelechangeId());
    technologyType  = technologyType.toLowerCase();

/*    if(name==null){                               // Pokud nebudu chtít akceptovat "null" vstupy   - toto odzávorkuji
      newHwPosition.setName(oldHwPosition.getName());
    } else { newHwPosition.setName(name);}
*/
    newHwPosition.setName(name);                    // Pokud nebudu chtít akceptovat "null" vstupy  - toto vymažu
    
    if(output==null){        //  - Při testu toto zakrýt
      newHwPosition.setTelexchangeoutput(oldHwPosition.getTelexchangeoutput());
    } else { newHwPosition.setTelexchangeoutput(output);}  
    
/*    if(phoneNumber==null){                        // Pokud nebudu chtít akceptovat "null" vstupy   - toto odzávorkuji
      newHwPosition.setPhonenumber(oldHwPosition.getPhonenumber());
    } else { newHwPosition.setPhonenumber(phoneNumber);}
*/
    newHwPosition.setPhonenumber(phoneNumber);      // Pokud nebudu chtít akceptovat "null" vstupy  - toto vymažu
    
    if(technologyType==null){
      newHwPosition.setTechnologytype(oldHwPosition.getTechnologytype());
    } else { newHwPosition.setTechnologytype(technologyType.toUpperCase().trim().charAt(0));}
    
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

    public static List<Hwposition> findPhoneNumber(Integer phoneNumber) {
        HwpositionJpaController cont = new HwpositionJpaController(emf);
        List<Hwposition> ret = cont.findAllHwOutputsByPhoneNumber(phoneNumber);
        return ret;
    }

    public static Hwposition findHwPositionById(int id) {
        HwpositionJpaController cont = new HwpositionJpaController(emf);
        Hwposition ret = cont.findHwposition(id);
        return ret;
    }
  
  
}
