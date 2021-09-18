/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

/**
 *
 * @author muuyi
 */
public class JSerialCommLib {

    //GET PORTS
    public static ObservableList<String> getPorts() {
        ObservableList<String> portList = FXCollections.observableArrayList();
        SerialPort[] ports = SerialPort.getCommPorts();
        for (SerialPort p : ports) {
            portList.add(p.getSystemPortName()+" "+p.getDescriptivePortName());
        }
        return portList;
    }

    public static Boolean openPort(SerialPort selectedPort) {
        try {
            selectedPort.setBaudRate(9600);
            selectedPort.setNumDataBits(8);
            selectedPort.setNumStopBits(1);
            selectedPort.setParity(SerialPort.NO_PARITY);
//            selectedPort.openPort();
            if (selectedPort.openPort()) {
                 JSerialCommLib.displayInfo("Hello", "Hello");
//                Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                alert.setTitle("OPEN PORT");
//                alert.setHeaderText
                return true;
            } else {
                 JSerialCommLib.displayError("Hello", "Hell222o");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //READ READER ID
    public static void readReaderId(SerialPort selectedPort) {
//        if(selectedPort.isOpen()){

        try {
            Thread thread = new Thread() {
                @Override
                public void run() {
                    try {                        
//                        if (selectedPort.openPort()) {
                            System.out.println("Reader ID");
                            selectedPort.openPort();
                            if(selectedPort.isOpen())
                                System.out.println("Port is open");
//                            selectedPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
                            OutputStream outputStream = selectedPort.getOutputStream();
                            Integer dataToSend = 0xA0;
                            outputStream.write(dataToSend.byteValue());
                            outputStream.flush();

                            Scanner scanner = new Scanner(selectedPort.getInputStream());
//                        inputStream.read();
//                            System.out.println(selectedPort.getInputStream().read());
//                        } else {
//                            Platform.runLater(new Runnable() {
//                                @Override
//                                public void run() {
//                                    JSerialCommLib.displayError("Port closed", "Open the port to connect to the reader!");
//                                }
//                            });
//                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            thread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        selectedPort.addDataListener(new SerialPortDataListener() {
            @Override
            public int getListeningEvents() {
                return SerialPort.LISTENING_EVENT_DATA_RECEIVED;
            }

            @Override
            public void serialEvent(SerialPortEvent event) {
                String dataBuffer = "";
                byte[] newData = event.getReceivedData();
                for (int i = 0; i < newData.length; i++) {
                    dataBuffer += (char) newData[i];
                    System.out.println(dataBuffer);
                }
            }
        });
//        }else{
//            JSerialCommLib.openPort(selectedPort);
//            System.out.println("Opened");
//        }
    }

    public static void displayInfo(String title, String header) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.show();
    }

    public static void displayError(String title, String header) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
//			alert.setContentText(
//					"An error occured during the process! Please checkout if you have a good internet connection or contact your system administrator to resolve the issue ?");
        alert.show();
    }
}
