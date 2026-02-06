package ac4y.service.domain;

import ac4y.service.algebra.Ac4yServiceResponseAlgebra;

public class Ac4yServiceResponse extends Ac4yServiceResponseAlgebra {

    public Ac4yServiceResponse(){
        if (!hasResult())
            createResult();
    }

    public Ac4yServiceResponse(String aCode, String aMessage, String aDescription) {
/*
        code = aCode;
        message = aMessage;
        description = aDescription;
*/
    } // Ac4yServiceResponse

    private class Ac4yUserText {
        public String text(String text){
            return text;
        }
    }

    public Ac4yProcessResult getErrorProcessResult(String description){return new Ac4yProcessResult().error(description);}

    public Ac4yProcessResult getTimeoutProcessResult(){return new Ac4yProcessResult().error(new Ac4yUserText().text("timeout!"));}

    public Ac4yProcessResult getSuccessProcessResult(){return new Ac4yProcessResult().success();}

    public Ac4yProcessResult getNothingHappenedProcessResult(String narrative){
            return new Ac4yProcessResult().nothingHappened(
                    new Ac4yUserText().text("nothing happened")
                    ,narrative
            );
    }

    public Ac4yServiceResponse getSuccessServiceResponse(){

        Ac4yServiceResponse response = new Ac4yServiceResponse();
        response.setResult(response.getSuccessProcessResult());
        return response;

    } // getSuccessServiceResponse

    public Ac4yServiceResponse getErrorServiceResponse(String description){

        Ac4yServiceResponse response = new Ac4yServiceResponse();
        response.setResult(this.getErrorProcessResult(description));
        return response;

    } // getErrorServiceResponse

    public Ac4yServiceResponse getNothingHappenedServiceResponse(String narrativa){

        Ac4yServiceResponse response = new Ac4yServiceResponse();
        response.setResult(response.getNothingHappenedProcessResult(narrativa));
        return response;

    } // getNothingHappenedServiceResponse

    public Ac4yServiceResponse error(String description){

        this.setResult(new Ac4yProcessResult().error(description));
        return this;

    } // error

    public Ac4yServiceResponse success(){

        this.setResult(new Ac4yProcessResult().success());
        return this;

    } // success

    public Ac4yServiceResponse nothingHappened(String narrative){

        this.setResult(new Ac4yProcessResult().nothingHappened(
                new Ac4yUserText().text("nothing happened")
                ,narrative
        ));

        return this;

    } // nothingHappened


    public boolean itWasFailed(){return this.getResult().itWasFailed();}
    public boolean itWasNothingToDo(){return this.getResult().itWasNothingToDo();}
    public boolean itWasSuccessful(){return this.getResult().itWasSuccessful();}

    public Ac4yServiceResponse addedRequestId(String requestId){

        this.setResult(this.getResult().addedRequestId(requestId));

        return this;

    } // addedRequestId

    public Ac4yServiceResponse addedDescription(String description){

        this.setResult(this.getResult().addedDescription(description));

        return this;

    } // addedDescription

} // Ac4yServiceResponse