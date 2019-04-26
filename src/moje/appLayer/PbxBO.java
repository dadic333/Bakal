/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moje.appLayer;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import moje.JpaController.PbxJpaController;
import moje.JpaController.PbxoutputJpaController;
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
  
  
}
