package serverEnums;

public enum DatumizeURI {

    BASEURI("http://127.0.0.1:8091"),
    BASEURILOCAL("http://localhost:8091"),
    BASEGETVAL("datumizeAPI/getvalue"),
    BASESETVAL("datumizeAPI/setvalue/"),
    BASESETVALASYNC("datumizeAPI/setvalueasync/");

    private String useURI;

    DatumizeURI(String useURI){ this.useURI = useURI; }

    public String getUseURI() {
        return useURI;
    }
}
