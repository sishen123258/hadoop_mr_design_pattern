package netty.NettySerializable.testprotobuf;

import NettySerializable.protobuf.SubscribeReqProto;
import com.google.protobuf.InvalidProtocolBufferException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yue on 2015/7/14.
 */
public class TestSubscribeReqProto {

    private static byte[] encode(SubscribeReqProto.SubscribeReq req){
        return req.toByteArray();
    }

    private static SubscribeReqProto.SubscribeReq decode(byte[] body) throws InvalidProtocolBufferException {
        return SubscribeReqProto.SubscribeReq.parseFrom(body);
    }

    private static SubscribeReqProto.SubscribeReq createSubscribeReq(){
        SubscribeReqProto.SubscribeReq.Builder builder=SubscribeReqProto.SubscribeReq.newBuilder();
        builder.setSubReqID(0);
        builder.setUserName("YueTong");
        builder.setProductName("Netty Book!");
        builder.setAddress("ShangHai");
        return builder.build();
    }

    public static void main(String[] args) throws InvalidProtocolBufferException {
        SubscribeReqProto.SubscribeReq req=createSubscribeReq();
        System.out.println("req to byte = " + req.toByteString());
        System.out.println("req encode = " + req.toString());
        SubscribeReqProto.SubscribeReq req2=decode(encode(req));
        System.out.println("req2 decode = " + req2.toString());
        System.out.println("req2 equals req " + req2.equals(req));

    }

}
