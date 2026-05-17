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
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * Classe encarregada de gestionar fitxers XML.
 *
 * Permet validar usuaris a partir d'un fitxer XML.
 *
 * @author Usuari
 */
public class GestioFitxersXML {

    /**
     * Valida les credencials d'un usuari llegint la informació des d'un fitxer
     * XML.
     *
     * @param rutaNomFitxer ruta del fitxer XML
     * @param nom nom de l'usuari
     * @param pass contrasenya de l'usuari
     *
     * @return true si l'usuari existeix i les dades són correctes
     */
    public static Boolean validaUsuari(String rutaNomFitxer,
            String nom, String pass) {

        Boolean usuariTrobat = false;

        try {

            File fXmlFile = new File(rutaNomFitxer);

            DocumentBuilderFactory dbFactory
                    = DocumentBuilderFactory.newInstance();

            DocumentBuilder dBuilder
                    = dbFactory.newDocumentBuilder();

            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            System.out.println("Root element :"
                    + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("usuari");

            System.out.println("----------------------------");

            String pnom = "";
            String ppass = "";

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    pnom = eElement.getElementsByTagName("nom")
                            .item(0).getTextContent();

                    ppass = eElement.getElementsByTagName("contrassenya")
                            .item(0).getTextContent();

                    if (pnom.equals(nom) && ppass.equals(pass)) {

                        CONTROLLER.Principal.rol
                                = eElement.getElementsByTagName("rol")
                                        .item(0).getTextContent();

                        CONTROLLER.Principal.usuariLoginat = pnom;

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
        }

        return usuariTrobat;
    }

    /**
     * Afegeix un nou usuari al fitxer XML.
     *
     * @param id Identificador únic de l'usuari.
     * @param nom Nom de l'usuari.
     * @param contrassenya Contrasenya de l'usuari.
     * @param rol Rol de l'usuari dins del sistema.
     * @param rutaNomFitxer Ruta del fitxer XML on es guardaran les dades.
     */
    public static void afegirUsuari(int id, String nom, String contrassenya, String rol, String rutaNomFitxer) {
        try {
            File fitxer = new File(rutaNomFitxer);

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(fitxer);

            Element arrel = doc.getDocumentElement();

            // Crear nou usuari
            Element usuari = doc.createElement("usuari");
            usuari.setAttribute("id", String.valueOf(id));

            Element nomElement = doc.createElement("nom");
            nomElement.setTextContent(nom);

            Element passElement = doc.createElement("contrassenya");
            passElement.setTextContent(contrassenya);

            Element rolElement = doc.createElement("rol");
            rolElement.setTextContent(rol);

            // Afegir nodes
            usuari.appendChild(nomElement);
            usuari.appendChild(passElement);
            usuari.appendChild(rolElement);

            arrel.appendChild(usuari);

            guardarXML(doc, rutaNomFitxer);

            System.out.println("Usuari afegit correctament.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Modifica les dades d'un usuari existent dins del fitxer XML.
     *
     * @param id Identificador de l'usuari a modificar.
     * @param nouNom Nou nom de l'usuari.
     * @param novaPass Nova contrasenya de l'usuari.
     * @param nouRol Nou rol de l'usuari.
     * @param rutaNomFitxer Ruta del fitxer XML on es troben les dades.
     */
    public static void modificarUsuari(int id, String nouNom, String novaPass, String nouRol, String rutaNomFitxer) {
        try {
            File fitxer = new File(rutaNomFitxer);

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(fitxer);

            NodeList llista = doc.getElementsByTagName("usuari");

            for (int i = 0; i < llista.getLength(); i++) {

                Element usuari = (Element) llista.item(i);

                if (usuari.getAttribute("id").equals(String.valueOf(id))) {

                    usuari.getElementsByTagName("nom").item(0).setTextContent(nouNom);

                    usuari.getElementsByTagName("contrassenya").item(0).setTextContent(novaPass);

                    usuari.getElementsByTagName("rol").item(0).setTextContent(nouRol);

                    guardarXML(doc, rutaNomFitxer);

                    System.out.println("Usuari modificat correctament.");
                    return;
                }
            }

            System.out.println("No s'ha trobat l'usuari.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Elimina un usuari del fitxer XML segons el seu identificador.
     *
     * @param id Identificador de l'usuari a eliminar.
     * @param rutaNomFitxer Ruta del fitxer XML on es troben les dades.
     */
    public static void eliminarUsuari(int id, String rutaNomFitxer) {
        try {
            File fitxer = new File(rutaNomFitxer);

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(fitxer);

            NodeList llista = doc.getElementsByTagName("usuari");

            for (int i = 0; i < llista.getLength(); i++) {

                Element usuari = (Element) llista.item(i);

                if (usuari.getAttribute("id").equals(String.valueOf(id))) {

                    usuari.getParentNode().removeChild(usuari);

                    guardarXML(doc, rutaNomFitxer);

                    System.out.println("Usuari eliminat correctament.");
                    return;
                }
            }

            System.out.println("No s'ha trobat l'usuari.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Guarda els canvis realitzats al document XML en el fitxer especificat.
     *
     * @param doc Document XML amb les modificacions.
     * @param rutaNomFitxer Ruta del fitxer on es guardar? el XML.
     * @throws TransformerException si es produeix un error durant la
     * transformació del document XML.
     */
    private static void guardarXML(Document doc, String rutaNomFitxer) throws TransformerException {

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        DOMSource source = new DOMSource(doc);

        StreamResult resultat = new StreamResult(new File(rutaNomFitxer));

        transformer.transform(source, resultat);
    }

}
