package org.kstopa.atm;

public record AtmRequest(
        int region,
        RequestType requestType,
        int atmId
) {

    public boolean isValid() {
        return region > 0 && atmId > 0;
    }

}