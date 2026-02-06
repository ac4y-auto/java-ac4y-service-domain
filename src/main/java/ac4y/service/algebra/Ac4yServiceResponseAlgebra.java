package ac4y.service.algebra;

import ac4y.base.domain.Ac4y;
import ac4y.service.domain.Ac4yProcessResult;

public class Ac4yServiceResponseAlgebra extends Ac4y {

    private String threadId;

    public Ac4yProcessResult getResult() {
        return result;
    }

    public void setResult(Ac4yProcessResult result) {
        this.result = result;
    }

    public void createResult(){
        setResult(new Ac4yProcessResult());
    }

    public boolean hasResult() {
        return getResult() != null;
    }

    private Ac4yProcessResult result;

    public String getThreadId() {
        return threadId;
    }

    public void setThreadId(String threadId) {
        this.threadId = threadId;
    }

} // Ac4yServiceResponseAlgebra