package openADR.OADRMsgInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by georg on 07.06.16.
 * This class holds the important information for an oadrCancelReport message.
 */
public class MsgInfo_OADRCancelReport implements OADRMsgInfo {

    // Identifiers for report requests
    private List<String> reportRequestIDs;

    // Indicates if one report (in the form of UpdateReport) is returned after cancellation of Report.
    private boolean reportToFollow;

    public MsgInfo_OADRCancelReport(){
        this.reportRequestIDs = new ArrayList<>();
    }

    public boolean isReportToFollow() {
        return reportToFollow;
    }

    public void setReportToFollow(boolean reportToFollow) {
        this.reportToFollow = reportToFollow;
    }

    public List<String> getReportRequestIDs() {
        return reportRequestIDs;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMsgType() {
        return "oadrCancelReport";
    }
}
