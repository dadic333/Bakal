/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moje.appLayer;

import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import moje.entity.Telexchange;
import moje.JpaController.TelexchangeJpaController;
import moje.JpaController.exceptions.NonexistentEntityException;
import moje.entity.Hwposition;

/**
 *
 * @author Honza
 */
public class TelExchangeBO {
  
  private static EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("a33PU");

  public static Telexchange createNewTelExchange(String name, String building, String note) {
    TelexchangeJpaController cont = new TelexchangeJpaController(emf);
    Telexchange ret = new Telexchange();
    ret.setName(name);
    ret.setBuilding(building);
    ret.setNote(note);
    ret.setOutputcount(0);
    ret.setHwpositionList(Collections.EMPTY_LIST);
    cont.create(ret);
    return ret;
  }

  public static Telexchange getTelexchangeByID(Integer id) {
    TelexchangeJpaController cont = new TelexchangeJpaController(emf);
    return cont.findTelexchange(id);
  }

  public static List<Telexchange> getAllTelExchanges() {
    TelexchangeJpaController cont = new TelexchangeJpaController(emf);
    return cont.findTelexchangeEntities();
  }
  
  public static void deleteTelExchange(Telexchange telExchange) {
    deleteTelExchange(telExchange.getId());
  }  

  public static void deleteTelExchange(int id) {
    TelexchangeJpaController cont = new TelexchangeJpaController(emf);
    Telexchange telExchange = cont.findTelexchange(id);
    List<Hwposition> hwPositionsList = telExchange.getHwpositionList();
    for (Hwposition hwposition : hwPositionsList) {
      HwPositionBO.deleteHwPosition(hwposition);
    }
    try {
      cont.destroy(telExchange.getId());
    } catch (NonexistentEntityException ex) {
      Logger.getLogger(TelExchangeBO.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
}
