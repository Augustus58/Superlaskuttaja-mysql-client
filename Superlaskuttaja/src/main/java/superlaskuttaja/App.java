package superlaskuttaja;

import javax.swing.SwingUtilities;
import superlaskuttaja.kayttoliittyma.Kayttoliittyma;

public class App
{
    public static void main( String[] args )
    {
     
        Kayttoliittyma kayttoliittyma = new Kayttoliittyma();
        SwingUtilities.invokeLater(kayttoliittyma);
        
    }
}
