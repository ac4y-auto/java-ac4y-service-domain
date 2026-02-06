package ac4y.service.domain;

import ac4y.service.algebra.Ac4yServiceAlgebra;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
public class Ac4yService extends Ac4yServiceAlgebra {

    public Ac4yService() {

        if (!hasRequest())
            createRequest();

        if (!hasResponse())
            createResponse();

    }
}
