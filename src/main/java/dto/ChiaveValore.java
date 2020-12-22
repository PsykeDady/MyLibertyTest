package dto;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;



/**
 * ChiaveValore
 */
@XmlRootElement(name = "ChiaveValore")
public class ChiaveValore  implements Serializable {

    private static final long serialVersionUID = 1L;

    private String chiave;

    private String valore;

	public String getChiave() {
		return this.chiave;
	}

	public void setChiave(String chiave) {
		this.chiave = chiave;
	}

	public String getValore() {
		return this.valore;
	}

	public void setValore(String valore) {
		this.valore = valore;
	}

}