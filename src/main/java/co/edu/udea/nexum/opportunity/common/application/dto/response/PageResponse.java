package co.edu.udea.nexum.profile.common.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageResponse <T>{
    private Integer page;
    private Integer pageSize;
    private Integer totalPages;
    private Integer count;
    private Long totalCount;
    private List<T> content;
}
