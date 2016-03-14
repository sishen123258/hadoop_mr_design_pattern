package netty.NettySerializable.pojo;

import java.io.Serializable;

/**
 * Created by Yue on 2015/7/13.
 */
public class SubscribeResp implements Serializable {

    private int subReqID;
    private int respCode;
    private String desc;

    public int getRespCode() {
        return respCode;
    }

    public void setRespCode(int respCode) {
        this.respCode = respCode;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getSubReqID() {

        return subReqID;
    }

    public void setSubReqID(int subReqID) {
        this.subReqID = subReqID;
    }

    @Override
    public String toString() {
        return "SubscribeResp{" +
                "subReqID='" + subReqID + '\'' +
                ", respCode='" + respCode + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
