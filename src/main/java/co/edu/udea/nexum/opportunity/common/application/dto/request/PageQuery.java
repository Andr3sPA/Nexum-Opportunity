package co.edu.udea.nexum.profile.common.application.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PageQuery implements BaseRequest{
    private String sortBy;
    private Integer page;
    private Boolean asc;
    private Integer pageSize;
}