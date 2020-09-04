package ch.ost.wing.smartproducts.smartproductbrowser.viewmodels;

public interface IAdapterBinding<T> {
    void refresh(Iterable<T> items);
}
