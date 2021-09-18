/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usbreader;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
/////////////JSSS
//import jssc.SerialPort;
//import static jssc.SerialPort.MASK_RXCHAR;
//import jssc.SerialPortEvent;
//import jssc.SerialPortException;
//import jssc.SerialPortList;
//import main.JSerialCommLib;
////////////JSERIALCOMM
import com.fazecast.jSerialComm.SerialPort;
import java.io.OutputStream;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import main.JSerialCommLib;


/**
 * FXML Controller class
 *
 * @author muuyi
 */
public class MainPanelController implements Initializable {
    @FXML
    private ComboBox comPortCombobox;
    SerialPort rFIDPort = null;
     @FXML
    private TextField baudRateTxt;

    @FXML
    private TextField dataBitsTxt;

    @FXML
    private TextField stopBitsTxt;

    @FXML
    private TextField parityBitsTxt;
    @FXML
    private Pane portStatusLbl;
    @FXML
    private Button openPortBtn;

    @FXML
    private Button closePortBtn;
    ObservableList<String> portList;
    OutputStream outputStream;
//    private void detectPort(){
//        portList = FXCollections.observableArrayList();
//        String[] serialPortNames = SerialPortList.getPortNames();
//        for(String name : serialPortNames){
//            portList.add(name);
//        }
//    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        baudRateTxt.setText("9600");
        baudRateTxt.setDisable(true);
        
        dataBitsTxt.setText("8");
        dataBitsTxt.setDisable(true);
        
        stopBitsTxt.setText("1");
        stopBitsTxt.setDisable(true);
        
        parityBitsTxt.setText("None");
        parityBitsTxt.setDisable(true);
        // TODO
//        detectPort();
        comPortCombobox.setItems(JSerialCommLib.getPorts());
    }    
      @FXML
    void openPort(ActionEvent event) {
//        SerialPort []portLists = SerialPort.getCommPorts();
        SerialPort selectedPort =SerialPort.getCommPort(comPortCombobox.getValue().toString());
        
        if(JSerialCommLib.openPort(selectedPort)){
            JSerialCommLib.displayInfo("Hello", "Hello");
//            SerialPort selectedPort = (SerialPort)comPortCombobox.getValue();
            JSerialCommLib.displayInfo("OPEN PORT", selectedPort.getDescriptivePortName());
            comPortCombobox.setDisable(true);
            openPortBtn.setDisable(true);
            portStatusLbl.setStyle("-fx-background-color:#008000");
            
        }else{
//            JSerialCommLib.displayError("Hello", "Hello");
            portStatusLbl.setStyle("-fx-background-color:#FF0000");
        }
    }
      @FXML
    void closePort(ActionEvent event) {
         SerialPort selectedPort =SerialPort.getCommPort(comPortCombobox.getValue().toString());
         if(selectedPort.isOpen()){
             openPortBtn.setDisable(true);
         }else{
             selectedPort.closePort();
             openPortBtn.setDisable(false);
             comPortCombobox.setDisable(false);
             portStatusLbl.setStyle("-fx-background-color:#FF0000");
         }
    }
     @FXML
    void readReaderID(ActionEvent event) {
         SerialPort selectedPort =SerialPort.getCommPort(comPortCombobox.getValue().toString());
           if(selectedPort.openPort()){
               JSerialCommLib.readReaderId(selectedPort);
           }else{
               JSerialCommLib.displayError("USB Ports", "The port appears to have been shutdown or disconnected");
           }
        
    }
}
