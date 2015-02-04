/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superlaskuttaja.kayttoliittyma.asiakkaat.lisaa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import superlaskuttaja.kayttoliittyma.asiakkaat.AsiakkaatTaulukko;
import superlaskuttaja.kayttoliittyma.NappulaLukko;
import superlaskuttaja.logiikka.Asiakas;
import superlaskuttaja.logiikka.DataDeliver;

/**
 *
 * @author Augustus58
 */
public class LisaaAsiakasIkkunaLisaaKuuntelija implements ActionListener {

    private final JTextField nimiKentta;
    private final JTextField katuosoiteKentta;
    private final JTextField postinumeroKentta;
    private final JTextField kaupunkiKentta;
    private final JTextField emailKentta;
    private final JTextField asiakasnumeroKentta;
    private final JTextField laskujaLahetettyKentta;
    private final DataDeliver lataaja;
    private final AsiakkaatTaulukko taulukko;
    private final JFrame frame;
    private final NappulaLukko lukko;

    public LisaaAsiakasIkkunaLisaaKuuntelija(JTextField nimiKentta, JTextField katuosoiteKentta, JTextField postinumeroKentta, JTextField kaupunkiKentta, JTextField emailKentta, JTextField asiakasnumeroKentta, JTextField laskujaLahetettyKentta, DataDeliver lataaja, AsiakkaatTaulukko taulukko, JFrame frame, NappulaLukko lukko) {
        this.nimiKentta = nimiKentta;
        this.katuosoiteKentta = katuosoiteKentta;
        this.postinumeroKentta = postinumeroKentta;
        this.asiakasnumeroKentta = asiakasnumeroKentta;
        this.laskujaLahetettyKentta = laskujaLahetettyKentta;
        this.kaupunkiKentta = kaupunkiKentta;
        this.emailKentta = emailKentta;
        this.lataaja = lataaja;
        this.taulukko = taulukko;
        this.frame = frame;
        this.lukko = lukko;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            Asiakas asiakas = new Asiakas(asiakasnumeroKentta.getText(), nimiKentta.getText(), katuosoiteKentta.getText(), postinumeroKentta.getText(), kaupunkiKentta.getText(), Integer.parseInt(laskujaLahetettyKentta.getText()), emailKentta.getText());

            if (!asiakas.onkoTiedotOikeanlaiset()) {
                throw new IllegalArgumentException("Jokin syöte on virheellinen.");
            }

            ResultSet rs = lataaja.getDbc().executeQuery("select distinct asiakasnumero\n"
                    + "from Asiakas\n"
                    + "where asiakasnumero = " + asiakasnumeroKentta.getText() + "\n"
                    + "");
            if (rs.first()) {
                throw new IllegalArgumentException("Asiakasnumero on käytössä.");
            }

            lataaja.getDbc().executeUpdate("INSERT INTO Asiakas () VALUES ('" + asiakasnumeroKentta.getText() + "',\n"
                    + "1,\n"
                    + "'" + nimiKentta.getText() + "',\n"
                    + "'" + katuosoiteKentta.getText() + "',\n"
                    + "'" + postinumeroKentta.getText() + "',\n"
                    + "'" + kaupunkiKentta.getText() + "',\n"
                    + "'" + laskujaLahetettyKentta.getText() + "',\n"
                    + "'" + emailKentta.getText() + "'\n"
                    + ")");

            rs = lataaja.getDbc().executeQuery("select distinct nimi, katuosoite, postinumero, kaupunki, email, asiakasnumero, laskujaLahetetty, versio\n"
                    + "from Asiakas\n"
                    + "where versio = 1\n"
                    + "and asiakasnumero = " + asiakasnumeroKentta.getText() + "\n"
                    + "");

            taulukko.addAsiakkaatTaulukkoRiveja(rs);

            suljeIkkuna();
        } catch (Exception e) {
            LisaaAsiakasIkkunaLisaaKuuntelijaPoikkeusIkkuna poikkeusIkkuna = new LisaaAsiakasIkkunaLisaaKuuntelijaPoikkeusIkkuna();
            SwingUtilities.invokeLater(poikkeusIkkuna);
        }
    }

    private void suljeIkkuna() {
        frame.dispose();
        lukko.avaa();
    }
}