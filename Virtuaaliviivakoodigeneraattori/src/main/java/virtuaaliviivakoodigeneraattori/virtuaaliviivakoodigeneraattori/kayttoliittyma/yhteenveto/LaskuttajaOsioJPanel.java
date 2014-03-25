/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kayttoliittyma.yhteenveto;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.kayttoliittyma.NappulaLukko;
import virtuaaliviivakoodigeneraattori.virtuaaliviivakoodigeneraattori.logiikka.Lataaja;

/**
 *
 * @author Augustus58
 */
public class LaskuttajaOsioJPanel extends JPanel {

    private Boolean laskuttajaOlemassa;
    private Lataaja lataaja;
    private NappulaLukko lukko;

    public LaskuttajaOsioJPanel(Boolean laskuttajaOlemassa, Lataaja lataaja, NappulaLukko lukko) {
        this.laskuttajaOlemassa = laskuttajaOlemassa;
        this.lataaja = lataaja;
        this.lukko = lukko;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    public void paivitaSisalto() {
        if (laskuttajaOlemassa) {
            this.removeAll();

            this.add(Box.createRigidArea(new Dimension(10, 100)));
            JLabel tervetuloaTeksti = new JLabel("Tervetuloa " + lataaja.getLadattuTietovarasto().getLaskuttaja().getNimi() + "!");
            tervetuloaTeksti.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.add(tervetuloaTeksti);
            this.add(Box.createRigidArea(new Dimension(10, 40)));
            JLabel yrityksenNimi = new JLabel(lataaja.getLadattuTietovarasto().getLaskuttaja().getYrityksenNimi());
            yrityksenNimi.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.add(yrityksenNimi);
            this.add(Box.createRigidArea(new Dimension(10, 10)));
            JLabel laskujaLahetetty = new JLabel("Laskuja lähetetty " + lataaja.getLadattuTietovarasto().getLaskuttaja().getLaskujaLahetetty().toString());
            laskujaLahetetty.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.add(laskujaLahetetty);
            this.add(Box.createRigidArea(new Dimension(10, 40)));
            JButton muokkaaLaskuttajanTietoja = new JButton("Muokkaa laskuttajan tietoja");
            muokkaaLaskuttajanTietoja.setAlignmentX(Component.CENTER_ALIGNMENT);
            LaskuttajaOsioJFrameMuokkaaLaskuttajanTietojaKuuntelija muokkaaTietojaKuuntelija = new LaskuttajaOsioJFrameMuokkaaLaskuttajanTietojaKuuntelija(lataaja, lukko, this);
            muokkaaLaskuttajanTietoja.addActionListener(muokkaaTietojaKuuntelija);
            this.add(muokkaaLaskuttajanTietoja);
            
            this.revalidate();
            this.repaint();
        } else {
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
        }
    }

    public void setLaskuttajaOlemassa(Boolean laskuttajaOlemassa) {
        this.laskuttajaOlemassa = laskuttajaOlemassa;
    }

}
