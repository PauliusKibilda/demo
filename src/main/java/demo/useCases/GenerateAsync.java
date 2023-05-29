package demo.useCases;

import demo.interceptors.LoggedInvocation;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SessionScoped
@Named
public class GenerateAsync implements Serializable {
    @Inject
    AsyncClass async;

    private CompletableFuture<Integer> task = null;

    public String getNumber() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        task = CompletableFuture.supplyAsync(()->async.asyncMethod());

        return  "/CustomerEdit.xhtml?faces-redirect=true&id=" + requestParameters.get("id");
    }

    public boolean isTaskRunning() {
        return task != null && !task.isDone();
    }

    public String getStatus() throws ExecutionException, InterruptedException {
        if (task == null) {
            return null;
        } else if (isTaskRunning()) {
            return "Generation in progress";
        }
        return "Number: " + task.get();
    }
}
