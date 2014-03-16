package virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori;

import java.util.Date;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.logiikka.LaskunSumma;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.logiikka.Pankkiviivakoodi;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.logiikka.Tilinumero;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.logiikka.Viite;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Huraa!" );
        LaskunSumma summa=new LaskunSumma(99,99);
        Tilinumero tilinumero=new Tilinumero("FI8617453000169906");
        Viite viite = new Viite("10024");
        Date date=new Date(2014, 3, 29);
        Pankkiviivakoodi pankkiviivakoodi=new Pankkiviivakoodi(tilinumero, summa, viite, date);
        System.out.println(pankkiviivakoodi.getString());
    }
}
