package com.certis_base_app.model;

import com.certis_base_app.enums.OfficerTaskListType;

public class OfficerTaskWrapper {

    OfficerTaskListType officerTaskListType;
    Officer officer;


    public OfficerTaskWrapper()
    {}

    public OfficerTaskWrapper(OfficerTaskListType officerTaskListType, Officer officer) {
        this.officerTaskListType = officerTaskListType;
        this.officer = officer;
    }

    public OfficerTaskListType getOfficerTaskListType() {
        return officerTaskListType;
    }

    public void setOfficerTaskListType(OfficerTaskListType friendsItemType) {
        this.officerTaskListType = friendsItemType;
    }

    public Officer getofficer() {
        return officer;
    }

    public void setofficer(Officer officer) {
        this.officer = officer;
    }

}
