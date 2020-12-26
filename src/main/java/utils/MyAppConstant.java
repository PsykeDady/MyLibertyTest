package utils;

public enum MyAppConstant{

    // MY APP PROPERTY
    /** path of property file, you can find into liberty dir */
    PROPERTY_FILE(".myapp_settings"),
    /** name of db */
    DB_NAME("DB_NAME"),
    /** name of db user */
    DB_USER("DB_USER"),
    /** password of db user */
    DB_PASSWORD("DB_PASSWORD"),
    /** path of debug file */
    DEBUG_LOG("DEBUG_LOG"),
    /** 1 to enable debug */
    DEBUG_GLOBAL("DEBUG_GLOBAL"),

    //OTHER
    ;

    private Object value;
    private MyAppConstant(Object value){
        this.value=value;
    }
    
    public Object getValue () { return value; }
}