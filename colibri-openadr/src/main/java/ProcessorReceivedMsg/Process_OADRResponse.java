package ProcessorReceivedMsg;

import OADRHandling.OADRParty;
import OADRMsgInfo.*;
import Utils.OADRConInfo;
import Utils.OADRMsgObject;
import com.enernoc.open.oadr2.model.v20b.OadrResponse;

import java.util.HashMap;

/**
 * Created by georg on 07.06.16.
 * This class is used to handle the receipt of openADR message type oadrResponse.
 */
public class Process_OADRResponse extends ProcessorReceivedMsg {

    /**
     * This method generates the proper reply for a openADR message OadrResponse.
     * Return null, because there is no need to reply to this type of message.
     * @param obj generate reply for this message. The contained message type has to be OadrResponse.
     * @return proper reply
     */
    @Override
    public OADRMsgObject genResponse(OADRMsgObject obj) {
        return null;
    }

    /**
     * This method returns an MsgInfo_OADRResponse object.
     * This object contains all needful information for a engery consumer from an OadrResponse message.
     * @param obj extract inforation out of this message object. The contained message type has to be OadrResponse.
     * @param party
     * @return  The OADRMsgInfo object contains all needful information for a engery consumer.
     */
    @Override
    public OADRMsgInfo extractInfo(OADRMsgObject obj, OADRParty party) {
        OadrResponse msg = (OadrResponse)obj.getMsg();
        MsgInfo_OADRResponse info = new MsgInfo_OADRResponse();

        info.setCorrespondingRequestID(msg.getEiResponse().getRequestID());
        info.setResponseCode(Integer.parseInt(msg.getEiResponse().getResponseCode().getValue()));
        info.setResponseDescription(msg.getEiResponse().getResponseDescription());

        return info;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean doRecMsgViolateConstraintsAndUpdateSendMap(OADRMsgObject obj, HashMap<String, OADRMsgObject> sendedMsgMap){
        if(OADRConInfo.getVENId() == null){
            return true;
        }

        OadrResponse recMsg = (OadrResponse)obj.getMsg();

        if(sendedMsgMap.get(recMsg.getEiResponse().getRequestID()) == null){
            return true;
        }
        sendedMsgMap.remove(recMsg.getEiResponse().getRequestID());

        if(!recMsg.getVenID().equals(OADRConInfo.getVENId())){
            return true;
        }

        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMsgType() {
        return new MsgInfo_OADRResponse().getMsgType();
    }
}
