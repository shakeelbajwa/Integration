package HttpRequest;

//import AppLogic.Helper;

//import AppLogic.Helper;

import AppLogic.Helper;

public class UUID_updateUuidRecordVersion implements Helper{

    private String Uuid;
    private Helper.SourceType Source;

    public UUID_updateUuidRecordVersion(String UUID, Helper.SourceType Source) {

        this.Uuid = UUID;
        this.Source = Source;
    }

    //    GETTERS & SETTERS

    public String getUUID() {
        return Uuid;
    }

    public Helper.SourceType getSource() {
        return Source;
    }
    public void setSource(Helper.SourceType source) {
        Source = source;
    }

    public String toJSONString() {

        String s = "{\"Uuid\":\""+this.getUUID()+ "\","
                + "\"Source\":\""+this.getSource()+ "\""
                + "}";

        return s;
    }
}
