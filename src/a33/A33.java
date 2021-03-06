package a33;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import moje.appLayer.CabHeadOutputBO;
import moje.appLayer.CableHeadBO;
import moje.appLayer.DataDeviceBO;
import moje.appLayer.DataOutputBO;
import moje.appLayer.HwPositionBO;
import moje.appLayer.PbxBO;
import moje.appLayer.PbxOutputBO;
import moje.appLayer.TelExchangeBO;
import moje.entity.Cabheadoutput;
import moje.entity.Cablehead;
import moje.entity.Datadevice;
import moje.entity.Dataoutput;
import moje.entity.Hwposition;
import moje.entity.Pbx;
import moje.entity.Pbxoutput;
import moje.entity.Telexchange;

/**
 *
 * @author Honza
 */
public class A33 {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    
//    findPhoneNumber(123);
//    getAllCableHeads();
//    createNewCaleHeadAndCHOutputs("147", "Českobratrská 147", "5.NP, dveře 147", 20);
//    readCableHeadAllParam(2);
//    deleteCableHeadAndOutpustByID(446);
//    findAllCabHeadOutputsWithoutCableHeadID();
//    findCableHeadOutputById(121);
//    deleteOutputsWithoutCableHeadID();
////    getAllCabHeadsOutputs();
////    editCableHead(218,"mame 4","zmenena budova a umisteni 4","zmenena poznamka 4");
//    editCableHeadOutput(121,"NOTE ZMENA 7", 1234567897);
    
    createDataDeviceAndOutputs("DD11", "Uliční 25", "1.NP, dveře 101", 10);
//    getAllDataDevices();
//    readDataDeviceAllParam(1);
//    deleteDataDeviceAndOutpus(2);
//    getAllDataOutputs();
//    editDataDevice(1,"name 3","building 3","note 3");
//    editDataOutput(2,"note 2", 123222, "d2:222:222:22");

//    createNewPbxAndOutputs("pokus4", "Českobratrská 4", "4.NP, dveře 44", 50);
//    getAllPbx();
//    deletePbxAndOutputs(1);
//    getAllPbxOutputs();
//    readPbxAllParam(2);
//    editPbx(2,"name 1","building 1","note 1");
//    editPbxOutput(21, "a", "nový note", 22222);

//    creatTelExchange("Telex 1", "building 4", "poznámka 4");
//    getAllTelExchange();

//    deleteTelExchange(2);
//    getAllHwPositions();
//    addTelExchangeHwPositions(616, 5);
//    readTelExchangeAllParam(616);
//    editTelExchange(3, "name 2", "building 2", "note 2");
//    editHwPosition(10, "name 2", 222, 22222, "d", "note 2" );
//    deleteHwPosition(9);
    
  }
  
  private static void createNewCaleHeadAndCHOutputs(String name, String building, String note, int outputCout) {
    Cablehead newCH = CableHeadBO.createNewCaleHeadAndCHOutputs(name, building, note, outputCout);
    System.out.println("NEW CableHead:");
    readCableHeadAllParam(newCH.getId());
  }
  
  public static void readCableHeadAllParam(int id) {
  Cablehead newCableHead = CableHeadBO.getCableheadByID(id);
  System.out.println(".....Read Cablehead all Parameters......");
  System.out.println("Výpis kabelové hlavy s ID= "+id);   // cable reading
  System.out.println("id= "+newCableHead.getId()+"; name= "+newCableHead.getName()
          +"; building= "+newCableHead.getBuilding()+"; note= "
          + newCableHead.getNote()+"; outputCount= "+newCableHead.getOutputcount()
          +"; getCabheadoutputList().size()"+newCableHead.getCabheadoutputList().size());
  System.out.println("Má výstupy:");
  // cableHeadList reading
  readCabHeadOutputList(newCableHead.getCabheadoutputList());
  }
    
  private static void readCabHeadOutputList (List<Cabheadoutput> cableHeadOutputList){
  if (cableHeadOutputList.isEmpty()){
    System.out.println("NO CableHeadOutputs!!!");
  }else {
    System.out.println("___________________________-All CableHeads Outputs-______________________________");   
    for (Cabheadoutput ch : cableHeadOutputList) {
      System.out.println("CableHeadOutput id= "+ch.getId()+"; output= "+ch.getCabheadout()
              +"; note= "+ch.getNote()+"; phoneNumber= "+ch.getPhonenumber()
              +"; owner cableHead ID= "+ch.getCableheadId());
      }
    System.out.println("END__________________________________________________________________________________END");
    }
  }
    
  private static void getAllCableHeads() {
    List<Cablehead> ret = CableHeadBO.getAllCableHeads();
    System.out.println("________________________-All Cable Heads-___________________________");
    for (Cablehead cabHead : ret) {
      System.out.println("ID= "+cabHead.getId()+"; name= "+cabHead.getName()
              +"; building= "+cabHead.getBuilding()+"; note= "+cabHead.getNote()
              +"; outputs cout= "+cabHead.getOutputcount()
              +"; output List size= "+cabHead.getCabheadoutputList().size());
    }
    System.out.println("END__________________________________________________________________________________END");
  }

  private static void deleteCableHeadAndOutpustByCableHeadID(int id) {
     CableHeadBO.deleteCableHeadAndOutpustByCableHead(id);
  }

  private static void findAllCabHeadOutputsWithoutCableHeadID() {
    List<Cabheadoutput> retL = CabHeadOutputBO.findAllCabHeadOutputsWithoutCableHeadID();
    readCabHeadOutputList(retL);
  }

  private static void deleteOutputsWithoutCableHeadID() {
    CabHeadOutputBO.deleteOutputsWithoutCableHeadID();
  }

  private static void deleteCableHeadAndOutpustByID(int id) {
    Cablehead cableHead = CableHeadBO.getCableheadByID(id);
    System.out.println("_______________VÝPIS CABLEHEAD PŘED VYMAZÁNÍM_____________________");
    readCableHeadAllParam(cableHead.getId());
    CableHeadBO.deleteCableHeadAndOutpustByCableHead(id);    
    System.out.println("_______________VÝPIS CABLEHEAD PO VYMAZÁNÍM_____________________");
    if(CableHeadBO.getCableheadByID(id)==null){
      System.out.println("-CABLEHEAD ID: '"+cableHead.getId()+"' BYL VYMAZÁN-");
    } else { 
      readCableHeadAllParam(CableHeadBO.getCableheadByID(id).getId());
    }
  }

  private static void findAllCabHeadOutputsByParameter(String attribute, String value) {
    List<Cabheadoutput> retL = CabHeadOutputBO.findAllCabHeadOutputsByParameter(attribute, value);
    System.out.println("______________________-CableHeads by CableHead ID-__________________________");
    for (Cabheadoutput cabheadoutput : retL) {
      System.out.println("ID= "+cabheadoutput.getId()+"; out= "+cabheadoutput.getCabheadout()
          +"; note= "+cabheadoutput.getNote()+"; phone number= "+cabheadoutput.getPhonenumber()
          +"; CableHead ID= "+cabheadoutput.getCableheadId());
    }
    System.out.println("END__________________________________________________________________________________END");
  }

  private static void getAllCabHeadsOutputs() {
    List<Cabheadoutput> ret = CabHeadOutputBO.getAllCabHeadsOutputs();
    System.out.println("_____________________-All CableHeads Outputs-________________________");
    for (Cabheadoutput outs : ret) {
      System.out.println("ID= "+outs.getId()+"; name= "+outs.getCabheadout()
              +"; note= "+outs.getNote()+"; Phone Number= "+outs.getPhonenumber()
              +"; getCableheadId= "+outs.getCableheadId());
    }
    System.out.println("END__________________________________________________________________________________END");
  }

  private static void createDataDeviceAndOutputs(String name, String building, String note, int outputCout) {
    Datadevice newdataDevice = DataDeviceBO.createDadtaDeviceAndOutputs(name, building, note, outputCout);
    System.out.println("NEW DataDevice:");
    readDataDeviceAllParam(newdataDevice.getId());
  }

  private static void readDataDeviceAllParam(Integer id) {
    Datadevice newDataDevice = DataDeviceBO.getDataDeviceByID(id);
    System.out.println(".....Read DataDevice all Parameters......");
    System.out.println("Výpis datového prvku s ID= "+id);   // cable reading
    System.out.println("id= "+newDataDevice.getId()+"; name= "+newDataDevice.getName()
            +"; building= "+newDataDevice.getBuilding()+"; note= "+ newDataDevice.getNote()
            +"; outputCount= "+newDataDevice.getOutputcount()
            +"; getCabheadoutputList().size()"+newDataDevice.getDataoutputList().size());
    System.out.println("Má výstupy:");
    readDataDeviceOutputList(newDataDevice.getDataoutputList());
  }

  private static void readDataDeviceOutputList(List<Dataoutput> dataoutputList) {
  if (dataoutputList.isEmpty()){
    System.out.println("NO DataOutputs!!!");
  }else { 
    System.out.println("________________________-All DataOutputs-___________________________");
    for (Dataoutput out : dataoutputList) {
      System.out.println("Output id= "+out.getId()+"; output number= "+out.getDatadevout()
              +"; note= "+out.getNote()+"; phoneNumber= "+out.getPhonenumber()
              +"; MAC= "+out.getMac()+"; OWNER DataDevice ID= "+out.getDeviceId());
      }
    System.out.println("END__________________________________________________________________________________END");
    }
  }

  private static void getAllDataDevices() {
    List<Datadevice> ret = DataDeviceBO.getAllDataDevices();
    System.out.println("______________________-All DataDevices-________________________");
    for (Datadevice dev : ret) {
      System.out.println("ID= "+dev.getId()+"; name= "+dev.getName()
              +"; building= "+dev.getBuilding()+"; note= "+dev.getNote()
              +"; outputs cout= "+dev.getOutputcount()
              +"; output List size= "+dev.getDataoutputList().size());
    }
    System.out.println("END__________________________________________________________________________________END");
  }
  
  private static void deleteDataDeviceAndOutpus(int id) {
    Datadevice dataDevice = DataDeviceBO.getDataDeviceByID(id);
    System.out.println("_______________VÝPIS DATADEVICE PŘED VYMAZÁNÍM_____________________");
    readDataDeviceAllParam(dataDevice.getId());
    DataDeviceBO.deleteDataDeviceAndOutpust(id);
    System.out.println("_______________VÝPIS DATADEVICE PO VYMAZÁNÍM_____________________");
    if(DataDeviceBO.getDataDeviceByID(id)==null){
      System.out.println("-DATADEVICE ID: '"+dataDevice.getId()+"' BYL VYMAZÁN-");
    } else { 
      readDataDeviceAllParam(DataDeviceBO.getDataDeviceByID(id).getId());
    }
  }

  private static void getAllDataOutputs() {
    List<Dataoutput> ret = DataOutputBO.getAllDataOutputs();
    readDataDeviceOutputList(ret);
  }

  private static void editCableHead(int id, String name, String building, String note) {
    System.out.println("_______________editCableHead - VÝPIS PŘED ZMĚNOU___________________");
    readCableHeadAllParam(id);
    Cablehead newCableHead = CableHeadBO.editCableHead(id,name,building,note);
    System.out.println("_______________editCableHead - VÝPIS PO ZMĚNĚ___________________");
    readCableHeadAllParam(newCableHead.getId());
  }

  private static void editCableHeadOutput(int id, String note, int phoneNumber) {
    System.out.println("_______________editCableHeadOutput - VÝPIS PŘED ZMĚNOU___________________");
    getAllCabHeadsOutputs();
    Cabheadoutput newCabHeadOUtput = CabHeadOutputBO.editCabHeadOutput(id,note,phoneNumber);
    System.out.println("_______________editCableHeadOutput - VÝPIS PO ZMĚNĚ___________________");
    System.out.println("VÝPIS NA CABLEHEADOUTPUT S ID: "+newCabHeadOUtput.getId()
            +"; output number= "+newCabHeadOUtput.getCabheadout()+"; note= "+newCabHeadOUtput.getNote()
            +"; phone number= "+newCabHeadOUtput.getPhonenumber()+"; OWNER cableHead= "+newCabHeadOUtput.getCableheadId());
  }

  private static void editDataDevice(int id, String name, String building, String note) {
    System.out.println("_______________editDataDevice - VÝPIS PŘED ZMĚNOU___________________");
    readDataDeviceAllParam(id);
    Datadevice newDataDevice = DataDeviceBO.editdataDevice(id,name,building,note);
    System.out.println("_______________editDataDevice - VÝPIS PO ZMĚNĚ___________________");
    readDataDeviceAllParam(newDataDevice.getId());
  }

  private static void editDataOutput(int id, String note, int phoneNumber, String mac) {
    System.out.println("_______________editDataOutput - VÝPIS PŘED ZMĚNOU___________________");
    getAllDataOutputs();
    Dataoutput newDataOutput = DataOutputBO.editDataOutput(id,note,phoneNumber,mac);
    System.out.println("_______________editDataOutput - VÝPIS PO ZMĚNĚ___________________");
    System.out.println("VÝPIS NA DataDevice S ID: "+newDataOutput.getId()
            +"; output number= "+newDataOutput.getDatadevout()+"; note= "+newDataOutput.getNote()
            +"; phone number= "+newDataOutput.getPhonenumber()+"; MAC= "+newDataOutput.getMac()
            +"; OWNER DataDevice= "+newDataOutput.getDeviceId());
  }

  private static void createNewPbxAndOutputs(String name, String building, String note, int outputCount) {
    Pbx newPbx = PbxBO.createNewPbxAndOutputs(name, building, note, outputCount);
    System.out.println("NEW PBX:");
    readPbxAllParam(newPbx.getId());
  }

  private static void readPbxAllParam(Integer id) {
    Pbx newPbx = PbxBO.getPbxByID(id);
    System.out.println(".....Read PBX all Parameters......");
    System.out.println("Výpis PBX s ID= "+id);   // cable reading
    System.out.println("id= "+newPbx.getId()+"; name= "+newPbx.getName()
            +"; building= "+newPbx.getBuilding()+"; note= "
            + newPbx.getNote()+"; outputCount= "+newPbx.getOutputcount()
            +"; getCabheadoutputList().size()"+newPbx.getPbxoutputList().size());
    System.out.println("Má výstupy:");
    // cableHeadList reading
    readPbxOutputList(newPbx.getPbxoutputList());
  }

  private static void readPbxOutputList(List<Pbxoutput> pbxoutputList) {
  if (pbxoutputList.isEmpty()){
    System.out.println("NO PBX Outputs!!!");
  }else {
    System.out.println("___________________________-All PBX Outputs-______________________________");   
    for (Pbxoutput pbxout : pbxoutputList) {
      System.out.println("PbxOutput id= "+pbxout.getId()+"; output= "+pbxout.getPbxout()
              +"; note= "+pbxout.getNote()+"; phoneNumber= "+pbxout.getPhonenumber()
              +"; technology type= "+pbxout.getTechnologytype()+"; owner cableHead ID= "+pbxout.getPbxId());
      }
    System.out.println("END__________________________________________________________________________________END");
    }
  }

  private static void getAllPbx() {
    List<Pbx>newList = PbxBO.getAllPbx();
    System.out.println("________________________-All Cable Heads-___________________________");
    for (Pbx pbx : newList) {
      System.out.println("ID= "+pbx.getId()+"; name= "+pbx.getName()
              +"; building= "+pbx.getBuilding()+"; note= "+pbx.getNote()
              +"; outputs cout= "+pbx.getOutputcount()
              +"; output List size= "+pbx.getPbxoutputList().size());
    }
    System.out.println("END__________________________________________________________________________________END");
  }

  private static void deletePbxAndOutputs(int id) {
    Pbx pbx = PbxBO.getPbxByID(id);
    System.out.println("_______________VÝPIS PBX PŘED VYMAZÁNÍM_____________________");
    readPbxAllParam(pbx.getId());
    PbxBO.deletePbxAndOutputs(pbx);
    System.out.println("_______________VÝPIS PBX PO VYMAZÁNÍM_____________________");
    if(PbxBO.getPbxByID(id)==null) {
      System.out.println("-PBX ID: '"+pbx.getId()+"' BYL VYMAZÁN-");
    } else { 
      readPbxAllParam(PbxBO.getPbxByID(id).getId());
    }
  }

  private static void getAllPbxOutputs() {
    List<Pbxoutput> ret = PbxOutputBO.getAllPbxOutputs();
    System.out.println("_____________________-All PBX Outputs-________________________");
    for (Pbxoutput outs : ret) {
      System.out.println("ID= "+outs.getId()+"; name= "+outs.getPbxout()
              +"; note= "+outs.getNote()+"; Phone Number= "+outs.getPhonenumber()
              +"; technollogy type= "+outs.getTechnologytype()+"; getPbxId= "+outs.getPbxId());
    }
    System.out.println("END__________________________________________________________________________________END");
  }

  private static void editPbx(int id, String name, String building, String note) {
    System.out.println("_______________editPBX - VÝPIS PŘED ZMĚNOU___________________");
    readPbxAllParam(id);
    Pbx newPbx = PbxBO.editPbx(id,name,building,note);
    System.out.println("_______________editPBX - VÝPIS PO ZMĚNĚ___________________");
    readPbxAllParam(newPbx.getId());
  }

  private static void editPbxOutput(int id, String technologyType, String note, int phoneNumber) {
    System.out.println("_______________editPbxOutput - VÝPIS PŘED ZMĚNOU___________________");
    getAllPbxOutputs();
    Pbxoutput newPbxOutput = PbxOutputBO.editPbxOutput(id, technologyType, note, phoneNumber);
    System.out.println("_______________editPbxOutput - VÝPIS PO ZMĚNĚ___________________");
    System.out.println("VÝPIS NA PBXOUTPUT S ID: "+newPbxOutput.getId()
            +"; output number= "+newPbxOutput.getPbxout()+"; note= "+newPbxOutput.getNote()
            +"; phone number= "+newPbxOutput.getPhonenumber()+"; technology type= "+newPbxOutput.getTechnologytype()
            +"; OWNER PBX= "+newPbxOutput.getPbxId());
  }

  private static void creatTelExchange(String name, String building, String note) {
    Telexchange newTE = TelExchangeBO.createNewTelExchange(name, building, note);
    System.out.println("NEW TelExchange: ");
    readTelExchangeAllParam(newTE.getId());
  }

  private static void readTelExchangeAllParam(Integer id) {
    Telexchange newTE = TelExchangeBO.getTelExchangeByID(id);
    System.out.println(".....Read TelExchange all Parameters......");
    System.out.println("Výpis TelExchange s ID= "+id);   // cable reading
    System.out.println("id= "+newTE.getId()+"; name= "+newTE.getName()
            +"; building= "+newTE.getBuilding()+"; note= "+ newTE.getNote()
            +"; outputCount= "+newTE.getOutputcount()
            +"; getHwpositionList().size() "+newTE.getHwpositionList().size());
    System.out.println("Má výstupy: ");
    readTelExchangeHwPositionsList(newTE.getHwpositionList());
  }

  private static void getAllTelExchange() {
    List<Telexchange>newList = TelExchangeBO.getAllTelExchanges();
    System.out.println("________________________-All TelExchanges-___________________________");
    for (Telexchange telExchange : newList) {
      System.out.println("ID= "+telExchange.getId()+"; name= "+telExchange.getName()
              +"; building= "+telExchange.getBuilding()+"; note= "+telExchange.getNote()
              +"; outputs cout= "+telExchange.getOutputcount()+"; getHWCount= "+telExchange.getHWCount()
              +"; HwPosition List size= "+telExchange.getHwpositionList().size());
    }
    System.out.println("END__________________________________________________________________________________END");
  }

  private static void readTelExchangeHwPositionsList(List<Hwposition> hwpositionList) {
    if (hwpositionList.isEmpty()){
      System.out.println("NO HW positions!!!");
    }else {
      System.out.println("___________________________-All HW positions-______________________________");   
      for (Hwposition hwPositions : hwpositionList) {
        System.out.println("PbxOutput id= "+hwPositions.getId()+"; WH position= "+hwPositions.getName()
                +"; output= "+hwPositions.getTelexchangeoutput()+"; phoneNumber= "+hwPositions.getPhonenumber()
                +"; technology type= "+hwPositions.getTechnologytype()+"; note= "+hwPositions.getNote()+
                "; OWNER TelExchange ID="+hwPositions.getTelechangeId());
        }
      System.out.println("END__________________________________________________________________________________END");
    }
  }

  private static void deleteTelExchange(int id) {
    Telexchange telExchange = TelExchangeBO.getTelExchangeByID(id);
    System.out.println("_______________VÝPIS TelExchange PŘED VYMAZÁNÍM_____________________");
    readTelExchangeAllParam(telExchange.getId());
    TelExchangeBO.deleteTelExchange(telExchange);
    System.out.println("_______________VÝPIS PBX PO VYMAZÁNÍM_____________________");
    if(TelExchangeBO.getTelExchangeByID(id)==null) {
      System.out.println("-TelExchange ID: '"+telExchange.getId()+"' BYL VYMAZÁN-");
    } else { 
      readPbxAllParam(TelExchangeBO.getTelExchangeByID(id).getId());
    }
  }

  private static void getAllHwPositions() {
    List<Hwposition> ret = HwPositionBO.getAllHwPositions();
    System.out.println("_____________________-All HW positions-________________________");
    for (Hwposition outs : ret) {
      System.out.println("ID= "+outs.getId()+"; name= "+outs.getName()
              +"; telEchange number= "+outs.getTelexchangeoutput()+"; Phone Number= "+outs.getPhonenumber()
              +"; technology type= "+outs.getTechnologytype()+"; note= "+outs.getNote()
              +"; OWNER TelExchange ID= "+outs.getTelechangeId());
    }
    System.out.println("END__________________________________________________________________________________END");
  }

  private static void addTelExchangeHwPositions(int telExchangeID, int count) {
    HwPositionBO.addTelExchangeHwPositions(telExchangeID, count);
    Telexchange x = TelExchangeBO.getTelExchangeByID(telExchangeID);
    readTelExchangeAllParam(x.getId());
  }

  private static void editTelExchange(int id, String name, String building, String note) {
    System.out.println("_______________editTelExchange - VÝPIS PŘED ZMĚNOU___________________");
    readTelExchangeAllParam(id);
    Telexchange telEx = TelExchangeBO.editTelExchange(id,name,building,note);
    System.out.println("_______________editTelExchange - VÝPIS PO ZMĚNĚ______________________");
    readTelExchangeAllParam(telEx.getId());
  }

  private static void editHwPosition(int id, String name, int output, int phoneNumber, String technologyType, String note) {
    System.out.println("_______________editHwPosition - VÝPIS PŘED ZMĚNOU___________________");
    getAllHwPositions();
    Hwposition hwPosition = HwPositionBO.editHwPosition(id, name, output, phoneNumber, technologyType, note);
    System.out.println("_______________editHwPosition - VÝPIS PO ZMĚNĚ_______________________");
    System.out.println("ID= "+hwPosition.getId()+"; name= "+hwPosition.getName()
        +"; telEchange number= "+hwPosition.getTelexchangeoutput()+"; Phone Number= "+hwPosition.getPhonenumber()
        +"; technology type= "+hwPosition.getTechnologytype()+"; note= "+hwPosition.getNote()
        +"; OWNER TelExchange ID= "+hwPosition.getTelechangeId());
    System.out.println("END__________________________________________________________________________________END");      
  }

    private static void findCableHeadOutputById(int id) {
        Cabheadoutput out = CabHeadOutputBO.findCableHeadOutputById(id);
        int chid = out.getCableheadId().getId();
        System.out.println("______________________-CabHead Output by Output ID-________________________");
        System.out.println("CableHeadOutput name: "+out.getId()+"; note: "+out.getNote()
                +"; phone numer: "+out.getPhonenumber()+"; CableHead name: "+moje.appLayer.CableHeadBO.getCableheadByID(chid));
        System.out.println("END__________________________________________________________________________________END");      
    }

    private static void findPhoneNumber(Integer phoneNumber) {
        List<Cabheadoutput> cabHeadOutput = CabHeadOutputBO.findPhoneNumber(phoneNumber);
        List<Dataoutput> dataDeviceOutput = DataOutputBO.findPhoneNumber(phoneNumber);
        List<Pbxoutput> pbxOutput = PbxOutputBO.findPhoneNumber(phoneNumber);
        List<Hwposition> hwPosition = HwPositionBO.findPhoneNumber(phoneNumber);
        readCabHeadOutputList(cabHeadOutput);
        readDataDeviceOutputList(dataDeviceOutput);
        readPbxOutputList(pbxOutput);
        readTelExchangeHwPositionsList(hwPosition);
    }

    private static void deleteHwPosition (Integer id) {
        Hwposition hwPositionToDelete = moje.appLayer.HwPositionBO.findHwPositionById(id);
        if(hwPositionToDelete==null) {
            System.out.println("    ZADAL SI NEPLATNÉ ID  "+id+"  !!!!!!!!!!!!!!!!!!!!");
        } else { 
            Telexchange telExchange = TelExchangeBO.getTelExchangeByID(hwPositionToDelete.getTelechangeId().getId());
            Integer telExId = telExchange.getId();
            System.out.println("_______________VÝPIS TelExchange PŘED VYMAZÁNÍM HW pozice_____________________");
            readTelExchangeAllParam(telExId);
            HwPositionBO.deleteHwPosition(hwPositionToDelete);
            if(HwPositionBO.findHwPositionById(id)==null) {
              System.out.println("-HW pozice ID: '"+id+"' BYLA VYMAZÁNA-");
              System.out.println("_______________VÝPIS PBX PO VYMAZÁNÍM HW pozice_____________________");
              readTelExchangeAllParam(telExId);
            } else { 
                System.out.println("    VYMAZÁNÍ SE NEPOVEDLO   \n\n");
                readTelExchangeAllParam(telExId);
            }
        }
    }
   
}
