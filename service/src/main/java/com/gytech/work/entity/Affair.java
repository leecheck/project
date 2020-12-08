package com.gytech.work.entity;

import com.gytech.entity.admin.SysOrganization;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by LQ on 2019/9/25.
 * com.gytech.work.entity
 */
public class Affair {

    /**
     * 站点id
     */
    private Long oid;

    private StationDuty stationDuty;

    private List<Action> stationAction;

    private SysOrganization station;

    public Affair(SysOrganization station) {
        this.stationDuty = new StationDuty(null,station);
        this.station = station;
        this.stationAction = new CopyOnWriteArrayList<>();
        this.oid = station.getId();
    }

    public Long getOid() {
        return oid;
    }

    public void setOid(Long oid) {
        this.oid = oid;
    }

    public StationDuty getStationDuty() {
        return stationDuty;
    }

    public void setStationDuty(StationDuty stationDuty) {
        this.stationDuty = stationDuty;
    }

    public List<Action> getStationAction() {
        return stationAction;
    }

    public void setStationAction(List<Action> stationAction) {
        this.stationAction = stationAction;
    }

    public SysOrganization getStation() {
        return station;
    }

    public void setStation(SysOrganization station) {
        this.station = station;
    }
}
