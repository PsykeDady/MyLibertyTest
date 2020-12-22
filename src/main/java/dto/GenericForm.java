package dto;

import java.util.List;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "GenericForm")
public class GenericForm implements Serializable{

    private static final long serialVersionUID = 1L;

    private List<ChiaveValore> valori;

	public List<ChiaveValore> getValori() {
		return this.valori;
	}

	public void setValori(List<ChiaveValore> valori) {
		this.valori = valori;
	}

    
    



}