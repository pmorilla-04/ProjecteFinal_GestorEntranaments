/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLLER;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Usuari
 */
public class GestioFitxersXML {

    //VALIDAR USUARI
    public static Boolean validaUsuari(String rutaNomFitxer, String nom, String pass) {

        Boolean usuariTrobat = false;
        try {
            File fXmlFile = new File(rutaNomFitxer);

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("usuari");
            System.out.println("----------------------------");

            String pnom = "";
            String ppass = "";

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);
                //System.out.println("\nCurrent Element :" + nNode.getNodeName());
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    pnom = eElement.getElementsByTagName("nom").item(0).getTextContent();
                    ppass = eElement.getElementsByTagName("contrassenya").item(0).getTextContent();

                    if (pnom.equals(nom) && ppass.equals(pass)) {
                        CONTROLLER.Principal.rol = eElement.getElementsByTagName("rol").item(0).getTextContent();
                        return true;
                    }

                    if (pnom.equals(nom) && (ppass.equals(pass))) {
                        usuariTrobat = true;
                        return usuariTrobat;
                    } else {
                        usuariTrobat = false;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            //GestiorFitxersTXT.escripturaAFitxerLog("Error en la validacio de l'usuari");
        }
        return usuariTrobat;
    }

    //LLEGIR FITXER XML
    public static void lecturaDeFitxerXML(String rutaNomFitxer) {
        try {
            File fXmlFile = new File(rutaNomFitxer);

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("usuari");
            System.out.println("----------------------------");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                System.out.println("\nCurrent Element :" + nNode.getNodeName());
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;
                    System.out.println("nif : "
                            + eElement.getAttribute("nif"));
                    System.out.println("Nom : "
                            + eElement.getElementsByTagName("nom")
                                    .item(0).getTextContent());
                    System.out.println("pass : "
                            + eElement.getElementsByTagName("pass")
                                    .item(0).getTextContent());
                    System.out.println("rol: "
                            + eElement.getElementsByTagName("rol")
                                    .item(0).getTextContent());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
}
