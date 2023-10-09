package xmlapisax;

import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.JOptionPane;

public class XMLApiSaxMain {
    
    /*
    b) Desarrollar una aplicación Java Swing en la que se muestre el contenido 
    de un archivo XML en un componente JTable. La aplicación debe permitir 
    cargar y visualizar cualquier documento XML. Usar los nombres de los 
    elementos como encabezados de las columnas. 
    */
    
    public static void main(String[] args) {
        
        VentanaTable vT = new VentanaTable(); //instanciar clase VentanaTable
        vT.setLocationRelativeTo(null); //centrarlo
        vT.setResizable(false); //que el frame no sea resizable
        vT.setVisible(true); //que sea visible 
        
        
    }//end main
    
    //metodo para que cualquier XML pueda verse en el jTable
    public File archivo () throws FileNotFoundException{ 
        File archivoXML = null;
        boolean seguir = false; //boolean seguir
        
        do{
               
        String nombreArchivo = JOptionPane.showInputDialog("Escribe el nombre de un archivo XML\n"
                + "El archivo XML debe de estar en su disco local (C:\\)");
        
            if (nombreArchivo == null) {
                JOptionPane.showMessageDialog(null,"Hasta Luego\n"
                        + "Recuerde poner su archivo XML en su disco local (C:\\)");
                System.exit(0);
            }
            
           archivoXML = new File("C:\\" + nombreArchivo + ".xml"); //Archivo donde esta XML 
           
           //PARA PROFESOR PARA TESTEAR EL PROGRAMA MAS RAPIDO Y FACIL
           //Comentar el archivoXML de arriba y descomentar el archivoXML de abajo
           
           //archivoXML = new File("src\\archivos\\cd_catalog.xml"); //Archivo donde esta XML
        
            if (!archivoXML.exists()) {
                JOptionPane.showMessageDialog(null,"Escriba un archivo que sí exista");
                seguir = true;
            }else{
                JOptionPane.showMessageDialog(null,"Archivo Encontrado");
                seguir = false;
            }
        
        
        }while(seguir);
        
        return archivoXML;
    }
    
}
