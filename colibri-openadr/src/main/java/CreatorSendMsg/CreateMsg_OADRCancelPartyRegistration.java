package CreatorSendMsg;

import OADRMsgInfo.MsgInfo_OADRCreatePartyRegistration;
import OADRMsgInfo.*;
import Utils.OADRConInfo;
import Utils.OADRMsgObject;
import Utils.XMPPConInfo;
import com.enernoc.open.oadr2.model.v20b.OadrCancelPartyRegistration;
import com.enernoc.open.oadr2.model.v20b.OadrCreatePartyRegistration;
import com.enernoc.open.oadr2.model.v20b.OadrTransportType;

import java.util.HashMap;

/**
 * Created by georg on 07.06.16.
 * This class is used to create oadrCancelPartyRegistration messages.
 */
public class CreateMsg_OADRCancelPartyRegistration extends CreateSendMsg {

    /**
     * Creates a message object with an openADR payload OadrCancelPartyRegistration in it.
     * @param info message info: contains the needed information to create a openADR payload
     * @param receivedMsgMap contains all received messages
     * @return
     */
    @Override
    public OADRMsgObject genSendMsg(OADRMsgInfo info, HashMap<String, OADRMsgInfo> receivedMsgMap) {
        MsgInfo_OADRCancelPartyRegistration con_info = (MsgInfo_OADRCancelPartyRegistration) info;

        OadrCancelPartyRegistration msg = new OadrCancelPartyRegistration();
        String reqID = OADRConInfo.getUniqueRequestId();
        msg.setRequestID(reqID);

        msg.setSchemaVersion("2.0b");
        msg.setVenID(OADRConInfo.getVENId());
        msg.setRegistrationID(OADRConInfo.getRegistrationId());

        OADRMsgObject obj = new OADRMsgObject(info.getMsgType(), reqID, msg);

        return obj;
    }

    /**
     * This method returns the message type name for an oadrCancelPartyRegistration message
     * @return supported messege type
     */
    @Override
    public String getMsgType() {
        return new MsgInfo_OADRCancelPartyRegistration().getMsgType();
    }

    /**
     * {@inheritDoc}
     */
    public boolean doSendMsgViolateMsgOrderAndUpdateRecMap(OADRMsgInfo info, HashMap<String, OADRMsgInfo> receivedMsgMap){
        if(OADRConInfo.getVENId() == null){
            return true;
        }
        return false;
    }
}
