package com.company;

import java.time.LocalDateTime;
import java.util.List;

public class fgweekData {
    String dateFrom;

    public String getDateFrom() {
        return dateFrom;
    }
    public fgweekData(){

    }
    public fgweekData(String dateFrom, String dateTo, List<Integer> vendorContentFileIds) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.vendorContentFileIds = vendorContentFileIds;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public void setVendorContentFileIds(List<Integer> vendorContentFileIds) {
        this.vendorContentFileIds = vendorContentFileIds;
    }

    public String getDateTo() {
        return dateTo;
    }

    public List<Integer> getVendorContentFileIds() {
        return vendorContentFileIds;
    }

    String dateTo;



    List<Integer> vendorContentFileIds;
}
