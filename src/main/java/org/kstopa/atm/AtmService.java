package org.kstopa.atm;

import java.util.Comparator;
import java.util.List;

public class AtmService {
    public List<AtmResponse> calculate(List<AtmRequest> atmRequests) {
        final Comparator<AtmRequest> atmRequestComparator = Comparator.comparing(AtmRequest::region).thenComparing(AtmRequest::requestType);

        return atmRequests
                .stream()
                .sorted(atmRequestComparator)
                .map(request -> new AtmResponse(request.region(), request.atmId()))
                .distinct()
                .toList();
    }

}
