package ch.ost.wing.smartproducts.shoppingcartapp.viewmodels;

public interface IAdapterBinding<T> {
    void refresh(Iterable<T> items);
}
