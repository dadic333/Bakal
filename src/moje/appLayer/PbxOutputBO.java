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
import moje.JpaController.PbxoutputJpaController;
import moje.JpaController.exceptions.NonexistentEntityException;
import moje.entity.Pbxoutput;

/**
 *
 * @author Honza
 */
public class PbxOutputBO {
  
  private static EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("a33PU");
  
  
  static void deletePbxOutput(Pbxoutput output) {
    deletePbxOutput(output.getId());
  }

  static void deletePbxOutput(Integer id) {
    PbxoutputJpaController cont = new PbxoutputJpaController(emf);
    try {
      cont.destroy(id);
    } catch (NonexistentEntityException ex) {
      Logger.getLogger(PbxOutputBO.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public static List<Pbxoutput> getAllPbxOutputs() {
    PbxoutputJpaController cont = new PbxoutputJpaController(emf);
    return cont.findPbxoutputEntities();
  }

  public static Pbxoutput editPbxOutput(int id, String technologyType, String note, int phoneNumber) {
    PbxoutputJpaController cont = new PbxoutputJpaController(emf);
    Pbxoutput newPbxOut = new Pbxoutput();
    Pbxoutput oldPbxOut = cont.findPbxoutput(id);
    newPbxOut.setId(oldPbxOut.getId());
    newPbxOut.setPbxout(oldPbxOut.getPbxout());
    newPbxOut.setPbxId(oldPbxOut.getPbxId());
    //TEST PRÁZDNÝCH HODNOT Z FORMULÁŘE
//    note=null;                      // součást testu
//    Integer pomPhoneNumber=null;    // součást testu
//    technologyType=null;
    if(note==null){
      newPbxOut.setNote(oldPbxOut.getNote());
    } else{ newPbxOut.setNote(note);}
//    if(pomPhoneNumber==null){       // součást testu
    if((Integer)phoneNumber==null){  
      newPbxOut.setPhonenumber(oldPbxOut.getPhonenumber());
    } else { newPbxOut.setPhonenumber(phoneNumber);}
    technologyType = technologyType.toLowerCase();
    if(technologyType==null){
      newPbxOut.setTechnologytype(oldPbxOut.getTechnologytype());
    } else { newPbxOut.setTechnologytype(technologyType.trim().charAt(0));}
        try {
          cont.edit(newPbxOut);
        } catch (Exception ex) {
          Logger.getLogger(CabHeadOutputBO.class.getName()).log(Level.SEVERE, null, ex);
        }
    return newPbxOut;
  }
  
  
  
}
