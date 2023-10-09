package xmlapisax;

import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.table.DefaultTableModel;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxHandler extends DefaultHandler { //SAX API
            
    private DefaultTableModel modelo; //Modelo de tabla
    private StringBuilder valorActual;
    private String elementoActual;
    private LinkedList<String> keys; //LinkedList para columnas
    private ArrayList<String> datos; //Para meter datos del XML

    public SaxHandler(DefaultTableModel model) { //constructor SaxHandler
        this.modelo = model;
        this.keys = new LinkedList<>();
        this.datos = new ArrayList<>();
        this.valorActual = new StringBuilder();
    }

    @Override 
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        this.elementoActual = qName; //Inicio de una etiqueta
        this.valorActual.setLength(0);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        this.valorActual.append(ch, start, length); //Información dentro de una etiqueta
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (this.elementoActual != null && this.valorActual.length() > 0) {
            String valor = this.valorActual.toString().trim(); 
            if (!valor.isEmpty()) {
                this.datos.add(valor); //Agregar datos al ArrayList
                if (!keys.contains(this.elementoActual)) {
                    keys.add(this.elementoActual);
                }
            }
        }
        this.elementoActual = null;
        this.valorActual.setLength(0);
    }

    public LinkedList<String> getKeys() { //Getter de LinkedList keys
        return keys;
    }

    public ArrayList<String> getDatos() { //Getter de el ArrayList de los datos del XML
        return datos;
    }
}
   

