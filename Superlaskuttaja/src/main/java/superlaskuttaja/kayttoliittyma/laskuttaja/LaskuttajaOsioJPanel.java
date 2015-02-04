/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superlaskuttaja.kayttoliittyma.laskuttaja;

import java.awt.Component;
import java.awt.Dimension;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import superlaskuttaja.kayttoliittyma.NappulaLukko;
import superlaskuttaja.logiikka.DataDeliver;

/**
 *
 * @author Augustus58
 */
public class LaskuttajaOsioJPanel extends JPanel {

    private Boolean laskuttajaOlemassa;
    private final DataDeliver lataaja;
    private final NappulaLukko lukko;

    public LaskuttajaOsioJPanel(Boolean laskuttajaOlemassa, DataDeliver lataaja, NappulaLukko lukko) {
        this.laskuttajaOlemassa = laskuttajaOlemassa;
        this.lataaja = lataaja;
        this.lukko = lukko;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    public void paivitaSisalto() {
        if (laskuttajaOlemassa && lataaja.getDbc().isYhdistetty()) {
            this.removeAll();

            //select1
            ResultSet rs = lataaja.getDbc().executeQuery("select distinct nimi, yrityksenNimi, laskujaLahetetty\n"
                    + "from Laskuttaja\n"
                    + "where Laskuttaja.versio = (select max(versio) from Laskuttaja)\n"
                    + "");

            try {
                rs.first();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                System.out.println(ex.getSQLState());
            }

            try {
                this.add(Box.createRigidArea(new Dimension(10, 100)));
                JLabel tervetuloaTeksti = new JLabel("Tervetuloa " + rs.getString("nimi") + "!");
                tervetuloaTeksti.setAlignmentX(Component.CENTER_ALIGNMENT);
                this.add(tervetuloaTeksti);
                this.add(Box.createRigidArea(new Dimension(10, 40)));
                JLabel yrityksenNimi = new JLabel(rs.getString("yrityksenNimi"));
                yrityksenNimi.setAlignmentX(Component.CENTER_ALIGNMENT);
                this.add(yrityksenNimi);
                this.add(Box.createRigidArea(new Dimension(10, 10)));
                JLabel laskujaLahetetty = new JLabel("Laskuja lähetetty " + rs.getInt("laskujaLahetetty"));
                laskujaLahetetty.setAlignmentX(Component.CENTER_ALIGNMENT);
                this.add(laskujaLahetetty);
                this.add(Box.createRigidArea(new Dimension(10, 40)));
                JButton muokkaaLaskuttajanTietoja = new JButton("Muokkaa laskuttajan tietoja");
                muokkaaLaskuttajanTietoja.setAlignmentX(Component.CENTER_ALIGNMENT);
                LaskuttajaOsioJFrameMuokkaaLaskuttajanTietojaKuuntelija muokkaaTietojaKuuntelija = new LaskuttajaOsioJFrameMuokkaaLaskuttajanTietojaKuuntelija(lataaja, lukko, this);
                muokkaaLaskuttajanTietoja.addActionListener(muokkaaTietojaKuuntelija);
                this.add(muokkaaLaskuttajanTietoja);
            } catch (SQLException sQLException) {
                System.out.println(sQLException.getMessage());
                System.out.println(sQLException.getSQLState());
            }

            this.revalidate();
            this.repaint();
        } else if (!laskuttajaOlemassa && lataaja.getDbc().isYhdistetty()) {
            this.removeAll();

            this.add(Box.createRigidArea(new Dimension(10, 100)));
            JLabel ilmoitus = new JLabel("Laskuttajan tietoja ei ole lisätty.");
            ilmoitus.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.add(ilmoitus);
            this.add(Box.createRigidArea(new Dimension(10, 40)));
            JButton lisaaLaskuttajanTiedot = new JButton("Lisää laskuttajan tiedot");
            LaskuttajaOsioJFrameSyotaLaskuttajanTiedotKuuntelija lisaaTiedotKuuntelija = new LaskuttajaOsioJFrameSyotaLaskuttajanTiedotKuuntelija(lataaja, lukko, this);
            lisaaLaskuttajanTiedot.addActionListener(lisaaTiedotKuuntelija);
            lisaaLaskuttajanTiedot.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.add(lisaaLaskuttajanTiedot);

            this.revalidate();
            this.repaint();
        } else {
            this.removeAll();

            this.add(Box.createRigidArea(new Dimension(10, 100)));
            JLabel ilmoitus = new JLabel("Tietokantaa ei ole asetettu. Aseta tietokanta alhaalta.");
            ilmoitus.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.add(ilmoitus);

            this.revalidate();
            this.repaint();
        }
    }

    public void setLaskuttajaOlemassa(Boolean laskuttajaOlemassa) {
        this.laskuttajaOlemassa = laskuttajaOlemassa;
    }

}
