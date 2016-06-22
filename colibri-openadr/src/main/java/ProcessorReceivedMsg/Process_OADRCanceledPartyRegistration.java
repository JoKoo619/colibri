package ProcessorReceivedMsg;

import OADRHandling.OADRParty;
import OADRMsgInfo.*;
import Utils.OADRConInfo;
import Utils.OADRMsgObject;
import com.enernoc.open.oadr2.model.v20b.OadrCanceledPartyRegistration;

import java.util.HashMap;

/**
 * Created by georg on 07.06.16.
 * This class is used to handle the receipt of openADR message type oadrCanceledPartyRegistration.
 */
public class Process_OADRCanceledPartyRegistration extends ProcessorReceivedMsg {

    /**
     * This method generates the proper reply for a openADR message OadrCanceledPartyRegistration.
     * Return null, because there is no need to reply to this type of message.
     * @param obj generate reply for this message. The contained message type has to be OadrCanceledPartyRegistration.
     * @return proper reply
     */
    @Override
    public OADRMsgObject genResponse(OADRMsgObject obj) {
        return null;
    }

    /**
     * This method returns an MsgInfo_OADRCanceledPartyRegistration object.
     * This object contains all needful information for a engery consumer from an OadrCanceledPartyRegistration message.
     * @param obj extract inforation out of this message object. The contained message type has to be OadrCanceledPartyRegistration.
     * @param party
     * @return  The OADRMsgInfo object contains all needful information for a engery consumer.
     */
    @Override
    public OADRMsgInfo extractInfo(OADRMsgObject obj, OADRParty party) {
        OadrCanceledPartyRegistration msg = (OadrCanceledPartyRegistration)obj.getMsg();
        MsgInfo_OADRCanceledPartyRegistration info = new MsgInfo_OADRCanceledPartyRegistration();

        OADRConInfo.setRegistrationId(null);


        info.setRegistrationID(msg.getRegistrationID());

        // TODO Conformance Rule 407
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

        OadrCanceledPartyRegistration recMsg = (OadrCanceledPartyRegistration)obj.getMsg();
        if(sendedMsgMap.get(recMsg.getEiResponse().getRequestID()) == null){
            return true;
        }

        OADRMsgObject originMsg = sendedMsgMap.get(recMsg.getEiResponse().getRequestID());
        if(!originMsg.getMsgType().equals("oadrCancelPartyRegistration")){
            return true;
        }

        if(!recMsg.getVenID().equals(OADRConInfo.getVENId())){
            return true;
        }

        if(OADRConInfo.getRegistrationId() != null && !recMsg.getRegistrationID().equals(OADRConInfo.getRegistrationId())){
            return true;
        }

        sendedMsgMap.remove(recMsg.getEiResponse().getRequestID());

        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMsgType() {
        return new MsgInfo_OADRCanceledPartyRegistration().getMsgType();
    }
}
