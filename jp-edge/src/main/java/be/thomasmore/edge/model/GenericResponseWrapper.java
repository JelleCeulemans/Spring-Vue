package be.thomasmore.edge.model;

import springfox.documentation.annotations.ApiIgnore;

import java.util.LinkedHashMap;

@ApiIgnore
public class GenericResponseWrapper {
    private LinkedHashMap _embedded;

    public LinkedHashMap get_embedded() {
        return _embedded;
    }

    public void set_embedded(LinkedHashMap _embedded) {
        this._embedded = _embedded;
    }
}
