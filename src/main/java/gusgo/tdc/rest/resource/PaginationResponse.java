package gusgo.tdc.rest.resource;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaginationResponse {
    private Long total;
    private int page;
    private int limit;
}
