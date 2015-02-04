/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superlaskuttaja.kayttoliittyma.laskut.lisaa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import superlaskuttaja.kayttoliittyma.NappulaLukko;
import superlaskuttaja.kayttoliittyma.asiakkaat.AsiakkaatTaulukko;
import superlaskuttaja.kayttoliittyma.laskut.LaskutTaulukko;
import superlaskuttaja.kayttoliittyma.suoritteet.SuoritteetTaulukko;
import superlaskuttaja.kayttoliittyma.laskuttaja.LaskuttajaOsioJPanel;
import superlaskuttaja.logiikka.Lasku;
import superlaskuttaja.logiikka.LaskunSumma;
import superlaskuttaja.logiikka.DataDeliver;
import superlaskuttaja.logiikka.MerkkiJaMerkkijonoTarkistin;
import superlaskuttaja.logiikka.Pankkiviivakoodi;
import superlaskuttaja.logiikka.Tilinumero;
import superlaskuttaja.logiikka.Viite;

/**
 *
 * @author Augustus58
 */
public class LisaaLaskuIkkunaLisaaKuuntelija implements ActionListener {

    private final JComboBox tilaajaComboBox;
    private final LisaaLaskuIkkunaTilaajaComboBoxKuuntelija tilaajaComboBoxkuuntelija;
    private final JComboBox vastaanottajaComboBox;
    private final LisaaLaskutIkkunaVastaanottajaComboBoxKuuntelija vastaanottajaComboBoxkuuntelija;
    private final LisaaLaskuIkkunaSuoritteetList suoritteetLista;
    private final JTextField paivaysKentta;
    private final JTextField maksuaikaKentta;
    private final JTextField erapaivaKentta;
    private final JTextField viivastyskorkoKentta;
    private final JTextField maksuehtoKentta;
    private final JTextField lisatiedotKentta;

    private final SuoritteetTaulukko suoritteetTaulukko;
    private final AsiakkaatTaulukko asiakkaatTaulukko;
    private final LaskuttajaOsioJPanel laskuttajaOsioJPanel;
    private final DataDeliver lataaja;
    private final LaskutTaulukko taulukko;
    private final JFrame frame;
    private final NappulaLukko lukko;
    private final MerkkiJaMerkkijonoTarkistin tarkistin;
    private final DateFormat pvmFormaatti1;
    private final DateFormat pvmFormaatti2;
    private final DateFormat pvmFormaatti3;
    private final DateFormat pvmFormaatti4;

    public LisaaLaskuIkkunaLisaaKuuntelija(JComboBox tilaajaComboBox, LisaaLaskuIkkunaTilaajaComboBoxKuuntelija tilaajaComboBoxkuuntelija, JComboBox vastaanottajaComboBox, LisaaLaskutIkkunaVastaanottajaComboBoxKuuntelija vastaanottajaComboBoxkuuntelija, LisaaLaskuIkkunaSuoritteetList suoritteetLista, JTextField paivaysKentta, JTextField maksuaikaKentta, JTextField erapaivaKentta, JTextField viivastyskorkoKentta, JTextField maksuehtoKentta, JTextField lisatiedotKentta, SuoritteetTaulukko suoritteetTaulukko, AsiakkaatTaulukko asiakkaatTaulukko, LaskuttajaOsioJPanel laskuttajaOsioJPanel, DataDeliver lataaja, LaskutTaulukko taulukko, JFrame frame, NappulaLukko lukko) {
        this.tilaajaComboBox = tilaajaComboBox;
        this.tilaajaComboBoxkuuntelija = tilaajaComboBoxkuuntelija;
        this.vastaanottajaComboBox = vastaanottajaComboBox;
        this.vastaanottajaComboBoxkuuntelija = vastaanottajaComboBoxkuuntelija;
        this.suoritteetLista = suoritteetLista;
        this.paivaysKentta = paivaysKentta;
        this.maksuaikaKentta = maksuaikaKentta;
        this.erapaivaKentta = erapaivaKentta;
        this.viivastyskorkoKentta = viivastyskorkoKentta;
        this.maksuehtoKentta = maksuehtoKentta;
        this.lisatiedotKentta = lisatiedotKentta;
        this.suoritteetTaulukko = suoritteetTaulukko;
        this.asiakkaatTaulukko = asiakkaatTaulukko;
        this.laskuttajaOsioJPanel = laskuttajaOsioJPanel;
        this.lataaja = lataaja;
        this.taulukko = taulukko;
        this.frame = frame;
        this.lukko = lukko;
        this.tarkistin = new MerkkiJaMerkkijonoTarkistin();
        this.pvmFormaatti1 = new SimpleDateFormat("dd.MM.yyyy");
        this.pvmFormaatti2 = new SimpleDateFormat("dd.MM.yyyy HH.mm");
        this.pvmFormaatti3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.pvmFormaatti4 = new SimpleDateFormat("yyyy-MM-dd");
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            if (!tarkistin.onkoMerkkijonoMuotoaNnPisteNnPisteNnnn(paivaysKentta.getText())) {
                throw new IllegalArgumentException("Syöte päiväys on virheellinen.");
            }
            if (!tarkistin.onkoPvmMerkkijonoMuotoaNnPisteNnPisteNnnnValidi(paivaysKentta.getText())) {
                throw new IllegalArgumentException("Syöte päiväys on virheellinen.");
            }
            GregorianCalendar paivays = new GregorianCalendar();
            paivays.setTime(pvmFormaatti1.parse(paivaysKentta.getText()));

            Integer maksuaika = Integer.parseInt(maksuaikaKentta.getText());

            if (maksuaika < 0 || maksuaika > 365) {
                throw new IllegalArgumentException("Syöte maksuaika on virheellinen.");
            }

            if (!tarkistin.onkoMerkkijonoMuotoaNnPisteNnPisteNnnn(erapaivaKentta.getText())) {
                throw new IllegalArgumentException("Syöte eräpäivä on virheellinen.");
            }
            if (!tarkistin.onkoPvmMerkkijonoMuotoaNnPisteNnPisteNnnnValidi(erapaivaKentta.getText())) {
                throw new IllegalArgumentException("Syöte eräpäivä virheellinen.");
            }
            GregorianCalendar erapaiva = new GregorianCalendar();
            erapaiva.setTime(pvmFormaatti1.parse(erapaivaKentta.getText()));
            
            

            //Tilaajan asiakasnumero + tilaajan laskujalähetty plus yksi.
            //Ensin tilaajan asiakasnumero.
            String tilaaja = (String) tilaajaComboBox.getItemAt(tilaajaComboBoxkuuntelija.getValinta());
            int indeksi = 0;
            for (int i = tilaaja.length() - 1; i > -1; i--) {
                if (tilaaja.charAt(i) == ' ') {
                    indeksi = i + 1;
                    break;
                }
            }
            tilaaja = tilaaja.substring(indeksi);

            //Sitten tilaajan laskujalähetetty.
            ResultSet rs = lataaja.getDbc().executeQuery("select distinct laskujaLahetetty\n"
                    + "from Asiakas\n"
                    + "where asiakasnumero = " + tilaaja + "\n"
                    + "and versio = \n"
                    + "(\n"
                    + "select max(versio)\n"
                    + "from Asiakas\n"
                    + "where asiakasnumero = " + tilaaja + "\n"
                    + ")\n"
                    + "");

            rs.first();

            int tilaajanLaskujalahetetty = rs.getInt(1);

            Viite viite = new Viite(tilaaja + (tilaajanLaskujalahetetty + 1));

            //Haetaan laskutettavat suoritteet.
            if (suoritteetLista.valitutRivit().length == 0) {
                throw new IllegalArgumentException("Ei valittuja suoritteita.");
            }
            String[] listaaVastaavatSuoritteet = suoritteetLista.getListaaVastSuoritteet();
            String[] valitutSuoritteet = new String[suoritteetLista.valitutRivit().length];
            for (int i = 0; i < suoritteetLista.valitutRivit().length; i++) {
                valitutSuoritteet[i] = listaaVastaavatSuoritteet[suoritteetLista.valitutRivit()[i]];
            }

            BigDecimal s = new BigDecimal("0.0");

            for (int i = 0; i < valitutSuoritteet.length; i++) {
                rs = lataaja.getDbc().executeQuery("select ((1+0.01*alvProsentti)*aHintaVeroton*maara) as yht\n"
                        + "from Suorite\n"
                        + "where suoritteenNumero = " + valitutSuoritteet[i] + "\n"
                        + "");
                rs.first();
                s = s.add(rs.getBigDecimal(1));
            }

            Double summa = s.doubleValue();

            Integer eurot = summa.intValue();
            
            Integer sentit = (int) Math.round((summa - summa.intValue()) * 100.0);
            
            if (sentit == 100) {
                eurot++;
                sentit = 0;
            }
            
            if (!LaskunSumma.tarkistaEurot(eurot)) {
                throw new IllegalArgumentException("Laskun summa on epäkelpo.");
            }
            
            if (!LaskunSumma.tarkistaSentit(sentit)) {
                throw new IllegalArgumentException("Laskun summa on epäkelpo.");
            }
            
            LaskunSumma laskunSumma = new LaskunSumma(eurot, sentit);

            //Haetaan laskuttajan tilinumero.
            rs = lataaja.getDbc().executeQuery("select tilinumero, tilinumeronPankki, tilinumeronSwiftBic\n"
                    + "from Laskuttaja\n"
                    + "where versio = \n"
                    + "(\n"
                    + "select max(versio)\n"
                    + "from Laskuttaja\n"
                    + ")\n"
                    + "");
            
            rs.first();

            Tilinumero tilinumero = new Tilinumero(rs.getString(1), rs.getString(2), rs.getString(3));

            Pankkiviivakoodi pankkiviivakoodi = new Pankkiviivakoodi(tilinumero, laskunSumma, viite, erapaiva);

            try {
                if (!Lasku.onkoViivastyskorkoOikeanlainen(Integer.parseInt(viivastyskorkoKentta.getText()))) {
                    throw new IllegalArgumentException("Syöte viivästyskorko on virheellinen.");
                }
            } catch (NumberFormatException ex) {
                throw new IllegalArgumentException("Syöte viivästyskorko on virheellinen.");
            }
            if (!Lasku.onkoMaksuehtoOikeanlainen(maksuehtoKentta.getText())) {
                throw new IllegalArgumentException("Syöte maksuehto on virheellinen.");
            }

            lataaja.getDbc().executeUpdate("insert into Pankkiviivakoodi () values\n"
                    + "('" + pankkiviivakoodi.pankkiviivakoodiIlmanAloitustaJaLopetusta() + "',\n"
                    + "'" + viite.toString() + "',\n"
                    + laskunSumma.toString() + ",\n"
                    + "'" + pvmFormaatti4.format(erapaiva.getTime()) + "')\n"
                    + "");

            //Selvitetään laskuttajan tietoja.
            rs = lataaja.getDbc().executeQuery("select laskujaLahetetty, yrityksenNimi, versio\n"
                    + "from Laskuttaja\n"
                    + "where versio =\n"
                    + "(\n"
                    + "select max(versio)\n"
                    + "from Laskuttaja\n"
                    + ")\n"
                    + "");

            rs.first();

            int laskujaLahetetty = rs.getInt(1);
            String yrityksenNimi = rs.getString(2);
            int versio = rs.getInt(3);

            lataaja.getDbc().executeUpdate("insert into Lasku () values\n"
                    + "(" + (laskujaLahetetty + 1) + ",\n"
                    + "null,\n"
                    + "'" + yrityksenNimi + "',\n"
                    + versio + ",\n"
                    + maksuaika + ",\n"
                    + "'" + pvmFormaatti4.format(paivays.getTime()) + "',\n"
                    + viivastyskorkoKentta.getText() + ",\n"
                    + "'" + maksuehtoKentta.getText() + "',\n"
                    + "'" + lisatiedotKentta.getText() + "',\n"
                    + "b'0',\n"
                    + "'" + pankkiviivakoodi.pankkiviivakoodiIlmanAloitustaJaLopetusta() + "')\n"
                    + "");

            //Vastaanottajan asiakasnumero.
            String vastaanottaja = (String) vastaanottajaComboBox.getItemAt(vastaanottajaComboBoxkuuntelija.getValinta());
            indeksi = 0;
            for (int i = vastaanottaja.length() - 1; i > -1; i--) {
                if (vastaanottaja.charAt(i) == ' ') {
                    indeksi = i + 1;
                    break;
                }
            }
            vastaanottaja = vastaanottaja.substring(indeksi);

            rs = lataaja.getDbc().executeQuery("select max(versio)\n"
                    + "from Asiakas\n"
                    + "where asiakasnumero = " + tilaaja + "\n"
                    + "");
            rs.first();
            int tilaajanMaxVersio = rs.getInt(1);
                    
            rs = lataaja.getDbc().executeQuery("select max(versio)\n"
                    + "from Asiakas\n"
                    + "where asiakasnumero = " + vastaanottaja + "\n"
                    + "");
            rs.first();
            int vastaanottajanMaxVersio = rs.getInt(1);

            //Tässä myös yhteinäistetään mukana olevien suoritteiden asiakasversiot uusimpiin. Tällöin laskulle tulevat osoitteet jne. ovat yksikäsitteisiä.
            for (int i = 0; i < valitutSuoritteet.length; i++) {
                lataaja.getDbc().executeUpdate("update Suorite\n"
                        + "set lasku = " + (laskujaLahetetty + 1) + ",\n"
                        + "tilaajanVersio = " + tilaajanMaxVersio + ",\n"
                        + "vastaanottajanVersio = " + vastaanottajanMaxVersio + "\n"
                        + "where suoritteenNumero = " + valitutSuoritteet[i] + "\n"
                        + "");
            }
            
            //Laskuttajan uusin versio.
            rs = lataaja.getDbc().executeQuery("select max(versio)\n"
                    + "from Laskuttaja\n"
                    + "");
            rs.first();
            int laskuttajanMaxVersio = rs.getInt(1);

            lataaja.getDbc().executeUpdate("update Laskuttaja\n"
                    + "set laskujaLahetetty = " + (laskujaLahetetty + 1) + "\n"
                    + "where versio = " + laskuttajanMaxVersio + "\n"
                    + "");

            lataaja.getDbc().executeUpdate("update Asiakas\n"
                    + "set laskujaLahetetty = " + (tilaajanLaskujalahetetty + 1) + "\n"
                    + "where asiakasnumero = " + tilaaja + "\n"
                    + "and versio = "+ tilaajanMaxVersio + "\n"
                    + "");

            rs = lataaja.getDbc().executeQuery("select distinct nimi,\n"
                    + "asiakasnumero,\n"
                    + "viiteTarkisteella,\n"
                    + "laskunNumero,\n"
                    + "laskunSumma,\n"
                    + "paivays,\n"
                    + "erapaiva,\n"
                    + "onkoMaksettu\n"
                    + "from Pankkiviivakoodi, Lasku, Suorite, Asiakas\n"
                    + "where Lasku.pankkiviivakoodi = Pankkiviivakoodi.pankkiviivakoodi\n"
                    + "and Suorite.lasku = Lasku.laskunNumero\n"
                    + "and Suorite.tilaaja = Asiakas.asiakasnumero\n"
                    + "and Suorite.tilaajanVersio = Asiakas.versio\n"
                    + "and laskunNumero = " + (laskujaLahetetty + 1) + "\n"
                    + "and tilaaja = " + tilaaja + "\n"
                    + "and tilaajanVersio = " + tilaajanMaxVersio + "\n"
                    + "");
            
            taulukko.addLaskutTaulukkoRiveja(rs);
            
            laskuttajaOsioJPanel.paivitaSisalto();

            asiakkaatTaulukko.paivitaAsiakkaanTiedot(tilaaja);
            
            for (int i = 0; i < valitutSuoritteet.length; i++) {
                suoritteetTaulukko.paivitaSuoritteenTiedot(valitutSuoritteet[i]);
            }

            suljeIkkuna();
        } catch (Exception e) {
            e.printStackTrace();
            LisaaLaskuIkkunaLisaaLaskuPoikkeusIkkuna poikkeusIkkuna = new LisaaLaskuIkkunaLisaaLaskuPoikkeusIkkuna();
            SwingUtilities.invokeLater(poikkeusIkkuna);
        }
    }

    private void suljeIkkuna() {
        frame.dispose();
        lukko.avaa();
    }
}
