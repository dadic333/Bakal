/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
  
  
}
