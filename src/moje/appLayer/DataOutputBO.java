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

  public static Dataoutput editDataOutput(int id, String note, int phoneNumber, String mac) {
    DataoutputJpaController cont = new DataoutputJpaController(emf);
    Dataoutput newDataOutput = new Dataoutput();
    Dataoutput oldDataOutput = cont.findDataoutput(id);
    newDataOutput.setId(oldDataOutput.getId());
    newDataOutput.setDatadevout(oldDataOutput.getDatadevout());
    newDataOutput.setDeviceId(oldDataOutput.getDeviceId());
    //TEST PRÁZDNÝCH HODNOT Z FORMULÁŘE
//    note=null;                      // součást testu
//    Integer pomPhoneNumber=null;    // součást testu
//    mac=null;                       // součást testu
    if(note==null){
      newDataOutput.setNote(oldDataOutput.getNote());
    } else{ newDataOutput.setNote(note);}
//    if(pomPhoneNumber==null){       // součást testu
    if((Integer)phoneNumber==null){  
      newDataOutput.setPhonenumber(oldDataOutput.getPhonenumber());
    } else { newDataOutput.setPhonenumber(phoneNumber);}
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
  
}
