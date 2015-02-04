/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superlaskuttaja.kayttoliittyma.suoritteet.lisaaValitusta;

import superlaskuttaja.kayttoliittyma.suoritteet.lisaa.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import superlaskuttaja.kayttoliittyma.ComboBoxKuuntelija;
import superlaskuttaja.kayttoliittyma.NappulaLukko;
import superlaskuttaja.kayttoliittyma.suoritteet.SuoritteetTaulukko;
import superlaskuttaja.logiikka.Asiakas;
import superlaskuttaja.logiikka.DataDeliver;
import superlaskuttaja.logiikka.MerkkiJaMerkkijonoTarkistin;
import superlaskuttaja.logiikka.Suorite;

/**
 *
 * @author Augustus58
 */
public class LisaaSuoriteValitustaIkkunaLisaaKuuntelija implements ActionListener {

    private final JComboBox tilaajaComboBox;
    private final JComboBox vastaanottajaComboBox;
    private final TilaajaComboBoxKuuntelija tilaajaComboBoxKuuntelija;
    private final ComboBoxKuuntelija vastaanottajaComboBoxKuuntelija;
    private final JTextField kuvausKentta;
    private final JTextField aloitusAikaKentta;
    private final JTextField lopetusAikaKentta;
    private final JTextField maaraKentta;
    private final JTextField maaranYksikotKentta;
    private final JTextField aHintaVerotonKentta;
    private final JTextField alvProsKentta;
    private final DataDeliver lataaja;
    private final SuoritteetTaulukko taulukko;
    private final JFrame frame;
    private final NappulaLukko lukko;
    private final MerkkiJaMerkkijonoTarkistin tarkistin;
    private final DateFormat pvmFormaatti1;
    private final DateFormat pvmFormaatti2;
    private final DateFormat pvmFormaatti3;

    public LisaaSuoriteValitustaIkkunaLisaaKuuntelija(JComboBox tilaajaComboBox, JComboBox vastaanottajaComboBox, TilaajaComboBoxKuuntelija tilaajaComboBoxKuuntelija, ComboBoxKuuntelija vastaanottajaComboBoxKuuntelija, JTextField kuvausKentta, JTextField aloitusAikaKentta, JTextField lopetusAikaKentta, JTextField maaraKentta, JTextField maaranYksikotKentta, JTextField aHintaVerotonKentta, JTextField alvProsKentta, DataDeliver lataaja, SuoritteetTaulukko taulukko, JFrame frame, NappulaLukko lukko) {
        this.tilaajaComboBox = tilaajaComboBox;
        this.vastaanottajaComboBox = vastaanottajaComboBox;
        this.tilaajaComboBoxKuuntelija = tilaajaComboBoxKuuntelija;
        this.vastaanottajaComboBoxKuuntelija = vastaanottajaComboBoxKuuntelija;
        this.kuvausKentta = kuvausKentta;
        this.aloitusAikaKentta = aloitusAikaKentta;
        this.lopetusAikaKentta = lopetusAikaKentta;
        this.maaraKentta = maaraKentta;
        this.maaranYksikotKentta = maaranYksikotKentta;
        this.aHintaVerotonKentta = aHintaVerotonKentta;
        this.alvProsKentta = alvProsKentta;
        this.lataaja = lataaja;
        this.taulukko = taulukko;
        this.frame = frame;
        this.lukko = lukko;
        this.tarkistin = new MerkkiJaMerkkijonoTarkistin();
        this.pvmFormaatti1 = new SimpleDateFormat("dd.MM.yyyy");
        this.pvmFormaatti2 = new SimpleDateFormat("dd.MM.yyyy HH.mm");
        this.pvmFormaatti3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try {

            if (tarkistin.onkoMerkkijonoMuotoaNnPisteNnPisteNnnnValiNnPisteNnOlevaPvm(aloitusAikaKentta.getText())) {
                if (!tarkistin.onkoMerkkijonoMuotoaNnPisteNnPisteNnnnValiNnPisteNnOlevaPvm(lopetusAikaKentta.getText())) {
                    throw new IllegalArgumentException("Lopetusaika on virheellinen");
                }
            } else if (tarkistin.onkoMerkkijonoMuotoaNnPisteNnPisteNnnnOlevaPvm(aloitusAikaKentta.getText())) {
                if (!lopetusAikaKentta.getText().isEmpty()) {
                    throw new IllegalArgumentException("Lopetusaika on virheellinen");
                }
            } else {
                throw new IllegalArgumentException("Aloitusaika on virheellinen");
            }

            Integer vuosi = Integer.parseInt(aloitusAikaKentta.getText().substring(6, 10));
            Integer kuukausi = Integer.parseInt(aloitusAikaKentta.getText().substring(3, 5));
            Integer paiva = Integer.parseInt(aloitusAikaKentta.getText().substring(0, 2));
            GregorianCalendar date = new GregorianCalendar(vuosi, kuukausi - 1, paiva);

            Asiakas suoritteenAsiakas = new Asiakas("1234", "Testi Asiakas", "Testikatu 17 B 45", "00345", "Testicity", 100000, "testi.testi@testi.fi");
            Suorite suorite = new Suorite(suoritteenAsiakas,
                    kuvausKentta.getText(),
                    date,
                    Double.parseDouble(maaraKentta.getText()),
                    maaranYksikotKentta.getText(),
                    Double.parseDouble(aHintaVerotonKentta.getText()),
                    Integer.parseInt(alvProsKentta.getText()));

            if (!suorite.onkoTiedotOikeanlaisetPaitsiPvm()) {
                throw new IllegalArgumentException("Jokin muu syöte, kuin päivämäärä on virheellinen.");
            }

            // Haetaan tilaaja asiakasnumeron perusteella
            String tilaaja = (String) tilaajaComboBox.getItemAt(tilaajaComboBoxKuuntelija.getValinta());
            int indeksi = 0;
            for (int i = tilaaja.length() - 1; i > -1; i--) {
                if (tilaaja.charAt(i) == ' ') {
                    indeksi = i + 1;
                    break;
                }
            }
            tilaaja = tilaaja.substring(indeksi);
            int tilaajaInt = Integer.parseInt(tilaaja);

            ResultSet rs = lataaja.getDbc().executeQuery("select max(versio) from Asiakas\n"
                    + "where asiakasnumero = " + tilaajaInt + "\n"
                    + "");
            try {
                rs.first();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                System.out.println(ex.getSQLState());
            }
            int tilaajaVersio = rs.getInt(1);

            // Haetaan vastaanottaja asiakasnumeron perusteella
            String vastaanottaja = (String) vastaanottajaComboBox.getItemAt(vastaanottajaComboBoxKuuntelija.getValinta());
            indeksi = 0;
            for (int i = vastaanottaja.length() - 1; i > -1; i--) {
                if (vastaanottaja.charAt(i) == ' ') {
                    indeksi = i + 1;
                    break;
                }
            }
            vastaanottaja = vastaanottaja.substring(indeksi);
            int vastaanottajaInt = Integer.parseInt(vastaanottaja);

            rs = lataaja.getDbc().executeQuery("select max(versio) from Asiakas\n"
                    + "where asiakasnumero = " + vastaanottajaInt + "\n"
                    + "");
            try {
                rs.first();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                System.out.println(ex.getSQLState());
            }
            int vastaanottajaVersio = rs.getInt(1);

            String ajatLiteraali;
            //Muodostetaan alkuaikaa ja lopppuaikaa koskeva literaali insert-komentoon.
            if (tarkistin.onkoMerkkijonoMuotoaNnPisteNnPisteNnnnOlevaPvm(aloitusAikaKentta.getText())) {
                ajatLiteraali = "'" + pvmFormaatti3.format(pvmFormaatti1.parse(aloitusAikaKentta.getText())) + "', NULL)\n";
            } else {
                ajatLiteraali = "'" + pvmFormaatti3.format(pvmFormaatti2.parse(aloitusAikaKentta.getText())) + "', '" + pvmFormaatti3.format(pvmFormaatti2.parse(lopetusAikaKentta.getText())) + "')\n";
            }

            lataaja.getDbc().executeUpdate("INSERT INTO Suorite () VALUES (NULL,\n"
                    + "NULL,\n"
                    + "'" + kuvausKentta.getText() + "',\n"
                    + tilaajaInt + ",\n"
                    + tilaajaVersio + ",\n"
                    + vastaanottajaInt + ",\n"
                    + vastaanottajaVersio + ",\n"
                    + maaraKentta.getText() + ",\n"
                    + "'" + maaranYksikotKentta.getText() + "',\n"
                    + aHintaVerotonKentta.getText() + ",\n"
                    + alvProsKentta.getText() + ",\n"
                    + ajatLiteraali
                    + "");

            rs = lataaja.getDbc().executeQuery("select last_insert_id();");
            try {
                rs.first();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                System.out.println(ex.getSQLState());
            }
            int suoritteenNumero = rs.getInt(1);

            rs = lataaja.getDbc().executeQuery("select nimi, vastaanottaja, kuvaus, alkuaika, maara, maaranYksikot,\n"
                    + "((1+0.01*alvProsentti)*aHintaVeroton*maara) as yht, !isnull(lasku) as onkoLaskutettu,\n"
                    + "suoritteenNumero\n"
                    + "from Suorite, Asiakas\n"
                    + "where vastaanottaja = asiakasnumero\n"
                    + "and vastaanottajanVersio = versio\n"
                    + "and suoritteenNumero = " + suoritteenNumero + "\n"
                    + "");

            taulukko.addSuoritteetTaulukkoRiveja(rs);
            suljeIkkuna();
        } catch (Exception e) {
            e.printStackTrace();
            LisaaSuoriteValitustaIkkunaLisaaSuoritePoikkeusIkkuna poikkeusIkkuna = new LisaaSuoriteValitustaIkkunaLisaaSuoritePoikkeusIkkuna();
            SwingUtilities.invokeLater(poikkeusIkkuna);
        }
    }

    private void suljeIkkuna() {
        frame.dispose();
        lukko.avaa();
    }
}
