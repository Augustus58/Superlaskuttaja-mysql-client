package virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori;

import javax.swing.SwingUtilities;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kauttoliittyma.Kayttoliittyma;

public class App
{
    public static void main( String[] args )
    {
     
        Kayttoliittyma kayttoliittyma = new Kayttoliittyma();
        SwingUtilities.invokeLater(kayttoliittyma);
        
    }
}
