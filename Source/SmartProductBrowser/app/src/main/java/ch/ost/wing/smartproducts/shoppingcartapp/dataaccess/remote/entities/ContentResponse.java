package ch.ost.wing.smartproducts.shoppingcartapp.dataaccess.remote.entities;

public class ContentResponse<T> {
    private final ResponseTypes _type;
    private final T _content;

    public ContentResponse(ResponseTypes type, T content){
        this._type = type;
        this._content = content;
    }

    public ContentResponse(ResponseTypes type){
        this._type = type;
        this._content = null;
    }

    public ResponseTypes getResponseType(){
        return this._type;
    }

    public boolean hasContent(){
        return this._content != null;
    }

    public T getContent(){
        return this._content;
    }
}
