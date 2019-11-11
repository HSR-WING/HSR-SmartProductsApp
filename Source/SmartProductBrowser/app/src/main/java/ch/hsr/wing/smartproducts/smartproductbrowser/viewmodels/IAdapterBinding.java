package ch.hsr.wing.smartproducts.smartproductbrowser.viewmodels;

public interface IAdapterBinding<T> {
    void refresh(Iterable<T> items);
}
