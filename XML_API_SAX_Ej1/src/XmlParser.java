/* PROCESAR XML CON EL API SAX
@vxndett
A partir del documento cd_catalog.xml calcular la media del precio de los CDs en el catálogo, el programa al final
también muestre la desviación estándar (DS) de los precios. Algunas recomendaciones:
-Para calcular la DS se requiere primero obtener la media. Por lo tanto, es conveniente que los precios de cada CD
se almacenen en un ArrayList<Double>. De esta manera, se podrá calcular la diferencia de cada valor con respecto a la
media como se indica en la fórmula.
 */

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.ArrayList;

public class XmlParser extends DefaultHandler {
    private boolean enPrecios = false;
    private StringBuilder contenido = new StringBuilder();
    private ArrayList<String> precioContenido = new ArrayList<>();

    //valida la entrada a la etiqueta
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("PRICE")) {
            enPrecios = true;
        }
    }

    public void characters(char[] cont, int start, int length) throws SAXException {
        if (enPrecios) {
            contenido.append(cont, start, length);
        }
    }

    //valida la salida de la etiqueta
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("PRICE")) {
            enPrecios = false;
            precioContenido.add(contenido.toString().trim());
            contenido.setLength(0);
        }
    }
    //metodo para calcular media
    public static double Media(ArrayList<Double> precios) {
        if (precios.isEmpty()) {
            throw new IllegalArgumentException("No hay ningun registro de precios.");
        }

        int tam = precios.size();
        double sumaPrecios = 0.0;

        for (Double num : precios) {
            sumaPrecios += num;
        }

        return (sumaPrecios / tam);
}

    //metodo para calcular desviacion estandar
    public static double desviacionEstandar(double media, ArrayList<Double> precios) {
        if (precios.isEmpty()) {
            throw new IllegalArgumentException("No hay ningun registro de precios.");
        }

        int tam = precios.size();
        double sumaCuadrados = 0.0;

        for (Double numero : precios) {
            double diferencia = numero - media;
            sumaCuadrados += (diferencia * diferencia); //suma la dif al cuadrado
        }

        double varianza = sumaCuadrados / tam;
        double desviacionEstandar = Math.sqrt(varianza); //raiz de la varianza

        return desviacionEstandar;
    }

    public static void main(String[] args) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            XmlParser handler = new XmlParser();

            parser.parse("D:\\Downloads\\cd_catalog.xml", handler);

            //recibe el contenido de las etiquetas como Strings
            ArrayList<String> precios = handler.precioContenido;
            ArrayList<Double> preciosDouble = new ArrayList<>();

            //pasar el contenido a Doubles para poder manipular los valores
            for (String precio: precios) {
                try{
                    Double valor = Double.parseDouble(precio);
                    preciosDouble.add(valor);
                } catch (NumberFormatException e){
                System.err.println("El contenido  " + precio + "no es valido como Double.");
                }
            }

            double media = Media(preciosDouble);
            double desviacionEstandar = desviacionEstandar(media, preciosDouble);

            //imprimir los valores
            System.out.printf("La media de los precios es: %,8.4f\n", media);
            System.out.printf("La desviacion estandar de los precios es: %,8.4f\n", desviacionEstandar);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



