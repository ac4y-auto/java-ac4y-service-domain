package ac4y.service.domain;

import ac4y.service.algebra.Ac4yProcessResultAlgebra;

public class Ac4yProcessResult extends Ac4yProcessResultAlgebra {

    public Ac4yProcessResult(){
        super();
    }

    public Ac4yProcessResult(int code, String message, String description){
        this();
        setCode(code);
        setMessage(message);
        setDescription(description);
    }

    private class Ac4yUserText {
        public String text(String text){
            return text;
        }
    }

    public Ac4yProcessResult error(String description){

        this.setCode(-1);
        this.setMessage(new Ac4yUserText().text("error!"));
        this.setDescription(description);

        return this;

    } // error

    public Ac4yProcessResult success(){

        this.setCode(1);

        this.setMessage(new Ac4yUserText().text("success!"));

        return this;

    } // success

    public Ac4yProcessResult nothingHappened(String message, String narrative){

        this.setCode(0);

        if (message != null)
            this.setMessage(message);
        else
            this.setMessage(new Ac4yUserText().text("nothing happened!"));

        if (narrative != null)
            this.setDescription(narrative);

        return this;

    } // nothingHappened

    public boolean itWasFailed(){return this.getCode()==-1;};
    public boolean itWasNothingToDo(){return this.getCode()==0;};
    public boolean itWasSuccessful(){return this.getCode()==1;};

    public Ac4yProcessResult addedRequestId(String requestId){

        this.setRequestId(requestId);
        return this;

    } // addedRequestId

    public Ac4yProcessResult addedDescription(String description){

        this.setDescription(description);

        return this;

    } // addedDescription

} // Ac4yProcessResult