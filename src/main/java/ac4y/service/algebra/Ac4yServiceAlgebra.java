package ac4y.service.algebra;

import ac4y.base.domain.Ac4y;
import ac4y.service.domain.Ac4yServiceRequest;
import ac4y.service.domain.Ac4yServiceResponse;

public class Ac4yServiceAlgebra extends Ac4y {

    public Ac4yServiceRequest getRequest() {
        return request;
    }

    public void setRequest(Ac4yServiceRequest request) {
        this.request = request;
    }

    public void createRequest(){
        setRequest(new Ac4yServiceRequest());
    }

    public boolean hasRequest(){
        return getRequest()!= null;
    }

    public Ac4yServiceResponse getResponse() {
        return response;
    }

    public void setResponse(Ac4yServiceResponse response) {
        this.response = response;
    }

    public void createResponse(){
        setResponse(new Ac4yServiceResponse());
    }

    public boolean hasResponse(){
        return getResponse()!= null;
    }



    protected Ac4yServiceRequest request;
    protected Ac4yServiceResponse response;

}
