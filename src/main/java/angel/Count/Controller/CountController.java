package angel.Count.Controller;

import angel.Count.Entity.Count;
import angel.Count.Service.CountService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import java.util.List;

@Controller
@Data
@ViewScoped
public class CountController {

    @Autowired
    private CountService countService;
    private List<Count> counts;
    private Count countSelected;
    private static final Logger logger = LoggerFactory.getLogger(CountController.class);


    @PostConstruct
    public void init() {
        loadData();
    }

    public void loadData() {
        this.counts = countService.listCounts();
        counts.forEach(count -> logger.info(count.toString()));
    }

    public void addCount(){
        this.countSelected= new Count();
    }

    public void saveCount(){
        logger.info("Count:" + this.countSelected);
        //Agregar cuenta
        if(this.countSelected.getId()== null) {
            this.countService.saveCount(this.countSelected);
            this.counts.add(this.countSelected);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("the account has been added"));
       // modificar cuenta
        } else{
            this.countService.saveCount(this.countSelected);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("the account has been updated"));

        }
        //ocultamos la ventama
        PrimeFaces.current().executeScript("PF('windowModelCount').hide()");
        // actualizamos la tabla
        PrimeFaces.current().ajax().update("form-count:messages", "form-count:count-table");
        //Hacemos un reset del objeto seleccionado de la table asi nuestro objeto cuentaSeleccionada siempre queda limpio
        this.countSelected=null;
    }
    public void removeCount(){
        logger.info("The account has been remove: "+ this.countSelected.toString());
        this.countService.deleteCount(this.countSelected);
        //eliminamos el registtro de la lista de cuentas, ya que estamos enviando actualizaciones parciales a la table
        this.counts.remove(this.countSelected);
        //Hacemos un reset del objeto seleccionado de la table
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("the account has been remove: "+this.countSelected));
        this.countSelected= null;
        PrimeFaces.current().ajax().update("form-count:messages", "form-count:count-table");

    }
}
