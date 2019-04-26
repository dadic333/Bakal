/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moje.appLayer;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import moje.JpaController.PbxJpaController;
import moje.JpaController.PbxoutputJpaController;
import moje.JpaController.exceptions.NonexistentEntityException;
import moje.entity.Pbx;
import moje.entity.Pbxoutput;

/**
 *
 * @author Honza
 */
public class PbxBO {
  
  private static EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("a33PU");

  public static Pbx createNewPbxAndOutputs(String name, String building, String note, int outputCount) {
    PbxJpaController cont = new PbxJpaController(emf);
    PbxoutputJpaController outscont = new PbxoutputJpaController(emf);
    Pbx ret = new Pbx();
    ret.setName(name);
    ret.setBuilding(building);
    ret.setNote(note);
    ret.setOutputcount(outputCount);
    cont.create(ret);
    //  create and fill PbxOutputList
    List<Pbxoutput> pbxOutputList = new ArrayList<Pbxoutput>();
    for(int i=1; i<=outputCount; i++){
      Pbxoutput newOutput = new Pbxoutput();
      newOutput.setPbxout(i);
      newOutput.setPhonenumber(0);
      newOutput.setNote("");
      newOutput.setPbxId(ret);
      outscont.create(newOutput);
    }
    return ret;
  }

  public static Pbx getPbxByID(Integer id) {
    PbxJpaController cont = new PbxJpaController(emf);
    Pbx ret = cont.findPbx(id);
    return ret;
  }

  public static List<Pbx> getAllPbx() {
    PbxJpaController cont = new PbxJpaController(emf);
    List<Pbx> ret = cont.findPbxEntities();
    return ret;
  }
  
  public static void deletePbxAndOutputs(Pbx pbx) {
    deletePbxAndOutputs(pbx.getId());
  }

  public static void deletePbxAndOutputs(Integer id) {
    PbxJpaController cont = new PbxJpaController(emf);
    Pbx pbx = cont.findPbx(id);
    List<Pbxoutput> pbxList = pbx.getPbxoutputList();
    for (Pbxoutput pbxoutput : pbxList) {
      PbxOutputBO.deletePbxOutput(pbxoutput);
    }
    try {
      cont.destroy(pbx.getId());
    } catch (NonexistentEntityException ex) {
      Logger.getLogger(PbxBO.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public static Pbx editPbx(int id, String name, String building, String note) {
    PbxJpaController cont = new PbxJpaController(emf);
    Pbx newPbx = new Pbx();
    Pbx oldPbx = cont.findPbx(id);
    newPbx.setId(oldPbx.getId());
    //TEST PRÁZDNÝCH HODNOT Z FORMULÁŘE
//    name=null;      // součást testu
//    building=null;  // součást testu
//    note=null;      // součást testu
    if(name==null){
      newPbx.setName(oldPbx.getName());
    } else { newPbx.setName(name);}
    if(building==null){
      newPbx.setBuilding(oldPbx.getBuilding());
    } else { newPbx.setBuilding(building);}
    if(note==null){
      newPbx.setNote(oldPbx.getNote());
    } else { newPbx.setNote(note);}
    newPbx.setOutputcount(oldPbx.getOutputcount());
    newPbx.setPbxoutputList(oldPbx.getPbxoutputList());
      try {
        cont.edit(newPbx);
      } catch (Exception ex) {
        Logger.getLogger(CableHeadBO.class.getName()).log(Level.SEVERE, null, ex);
      }
    return newPbx;
  }
  
  
}
