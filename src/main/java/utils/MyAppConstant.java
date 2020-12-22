public enum MyAppConstant{

    PROPERTY_FILE("~/.myapp_settings"),
    ;

    private String value;
    private MyAppConstant(String value){
        this.value=value;
    }
}