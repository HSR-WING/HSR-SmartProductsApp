package ch.ost.wing.smartproducts.shoppingcartapp.views.settings;

public class SummaryEntry {
    private final String _defaultSummary;
    private final String _withValueSummay;

    public SummaryEntry(String defaultSummary, String withValueSummary){
        this._defaultSummary = defaultSummary;
        this._withValueSummay = withValueSummary;
    }

    public String getDefaultSummary(){
        return this._defaultSummary;
    }

    public String getSummaryWithValue(String value){
        return this._withValueSummay + " " + value;
    }
}
