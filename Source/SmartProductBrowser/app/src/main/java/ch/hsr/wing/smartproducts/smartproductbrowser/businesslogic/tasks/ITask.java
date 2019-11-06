package ch.hsr.wing.smartproducts.smartproductbrowser.businesslogic.tasks;

public interface ITask<Params> {
    void run(Params... params);
}
